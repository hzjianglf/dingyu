package com.handany.base.sequence.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.handany.base.cache.redis.RedisClientFactory;
import com.handany.base.common.ComponentFactory;
import com.handany.base.sequence.Sequence;
import com.handany.base.sequence.impl.dao.SequenceMapper;

import redis.clients.jedis.Jedis;

public class Sequence_MySql implements Sequence {

    private static Logger logger = LoggerFactory.getLogger(Sequence_MySql.class);

    private int cacheDbIndex;

    @Autowired
    private SequenceMapper sequenceMapper;

    @Autowired
    private RedisClientFactory factory;

    public int getCacheDbIndex() {
        return cacheDbIndex;
    }

    public void setCacheDbIndex(int cacheDbIndex) {
        this.cacheDbIndex = cacheDbIndex;
    }

    private static final String CACHE_SEQUENCE_CURVAL = "SEQ_CURVAL:";

    private static final String CACHE_SEQUENCE_INFO = "SEQ_INFO:";
    private static final String CACHE_SEQUENCE_CACHE = "SEQ_CACHE";
    private static final String CACHE_SEQUENCE_MAXVAL = "SEQ_MAXVAL";

    @Override
    public long nextVal(String name) {
        Jedis client = factory.getRedisClient();

        client.select(cacheDbIndex);

        String sMaxval = client.hget(CACHE_SEQUENCE_INFO + name, CACHE_SEQUENCE_MAXVAL);
        if (sMaxval == null) {
            try {
                this.init(name);
                sMaxval = client.hget(CACHE_SEQUENCE_INFO + name, CACHE_SEQUENCE_MAXVAL);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        
        String sCache = client.hget(CACHE_SEQUENCE_INFO + name, CACHE_SEQUENCE_CACHE);
        long nextVal = client.incr(CACHE_SEQUENCE_CURVAL + name);

        long maxVal = Long.parseLong(sMaxval);
        int cache = Integer.parseInt(sCache);

        if (nextVal >= maxVal) {
            synchronized (this) {
                if (nextVal >= maxVal) {
                    maxVal += cache;
                    try {
                        syncDb(name, nextVal);
                    } catch (Exception e) {
                        logger.error("同步sequence[{}]最大值出错", name, e);
                        client.decr(CACHE_SEQUENCE_CURVAL + name);
                        throw new RuntimeException("更新数据库出错");
                    }
                    client.hset(CACHE_SEQUENCE_INFO + name, CACHE_SEQUENCE_MAXVAL, maxVal + "");
                }
            }
        }
        return nextVal;
    }

    private void syncDb(String seqName, long nextVal) throws Exception {
        sequenceMapper.syncDb(seqName, nextVal);
    }

    public void updateAll() {
        try {
            sequenceMapper.updateAll();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    public void initAll() throws Exception {

        logger.info("序列信息初始化 ... ");

        List<Map<String, String>> list = sequenceMapper.sequenceInfo();

        if (list == null) {
            throw new Exception("为查询到Pm_Sequence表中数据");
        }

        Jedis client = factory.getRedisClient();

        client.select(cacheDbIndex);
        logger.info("序列信息初始化 ... {}", cacheDbIndex);
        for (Map<String, String> map : list) {
            String name = map.get("name");
            String cache = String.valueOf(map.get("cache"));
            String startVal = String.valueOf(map.get("start_val"));
            long maxVal = Integer.parseInt(cache) + Long.parseLong(startVal);

            client.set(CACHE_SEQUENCE_CURVAL + name, startVal);
            client.hset(CACHE_SEQUENCE_INFO + name, CACHE_SEQUENCE_CACHE, cache);
            client.hset(CACHE_SEQUENCE_INFO + name, CACHE_SEQUENCE_MAXVAL, "" + maxVal);
        }

        factory.releaseThreadResource();
    }
    
    public void init(String sequenceName) throws Exception {
                logger.info("序列信息初始化 " + sequenceName + " ... ");

        Map<String, String> map = sequenceMapper.getSequenceInfo(sequenceName);

        if (map == null) {
            throw new Exception("为查询到Pm_Sequence表中数据");
        }

        Jedis client = factory.getRedisClient();

        client.select(cacheDbIndex);
        logger.info("序列信息初始化 ... {" + sequenceName + "}", cacheDbIndex);
        String name = map.get("name");
        String cache = String.valueOf(map.get("cache"));
        String startVal = String.valueOf(map.get("start_val"));
        long maxVal = Integer.parseInt(cache) + Long.parseLong(startVal);

        client.set(CACHE_SEQUENCE_CURVAL + name, startVal);
        client.hset(CACHE_SEQUENCE_INFO + name, CACHE_SEQUENCE_CACHE, cache);
        client.hset(CACHE_SEQUENCE_INFO + name, CACHE_SEQUENCE_MAXVAL, "" + maxVal);
        
        factory.releaseThreadResource();
    }

    public static void main(String[] args) {
        Sequence_MySql seq = ComponentFactory.getBean("sequence_mysql", Sequence_MySql.class);
        // try {
        // seq.initAll();
        //
        //
        //
        //
        // } catch (Exception e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
//		try {
//			seq.initAll();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        for (int i = 0; i < 1; i++) {
            System.out.println(seq.nextVal("bm_qa_log"));
        }
    }

}
