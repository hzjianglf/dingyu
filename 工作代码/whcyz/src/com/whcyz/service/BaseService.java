package com.whcyz.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;


/**
 * 封装常用操作的service 简化Controller层
 * @author 牟孟孟
 * @version 0.1
 * @param <M>
 * @date 2013年7月23日9:31:14
 *
 */
public interface BaseService {
	/**
	 * 设置是否需要disable
	 */
	public static final boolean NEED_DISABLE=false;
	public String WHERE_DISABLE_FALSE();
	public String AND_DISABLE_FALSE();
	/**
	 * 判断是否存在
	 * @param where
	 * @param params
	 * @return
	 * @author 牟孟孟
	 * 2013-9-13 下午12:00:16
	 */
	public boolean isExist(String where,Object... params);
	/**
	 * 判断是否存在
	 * @param where
	 * @param params
	 * @return
	 * @author 牟孟孟
	 * 2013-9-13 下午12:00:16
	 */
	public boolean isNotExist(String where,Object... params);
	/**
	 * 得到总个数
	 * @param where
	 * @param params
	 * @return
	 * @author 牟孟孟
	 * 2013-9-13 下午12:00:54
	 */
	public int getCount(String where,Object... params);
	/**
	 * 得到总数
	 * @return
	 */
	public int getCount();
	/**
	 * 设置model对应数据库的名称
	 * 由具体service自己实现
	 * @return
	 */
	public String table();
	/**
	 * 设置model对应数据库Dao
	 * 由具体service自己实现
	 * @return
	 */
	public Model<?> dao();
	/**
	 * 得到列表全部行数据
	 * select * from table
	 * @return
	 */
	public <T> List<T> getList();
	/**
	 * 按照指定字段的指定顺序得到列表全部行数据
	 * select * from table
	 * @return
	 */
	public <T> List<T> getListOrderBy(String column,String rule);
	/**
	 * 得到列表的全部行的指定列数据
	 * select id,name from table
	 * @param columns 列组合 例如 "id,name,type"
	 * @return
	 */
	public <T> List<T> getList(String columns);
	/**
	 * 
	 * @param columns
	 * @param where
	 * @param params
	 * @return
	 */
	public <T> List<T> getList(String columns,String where, Object... params);
	/**
	 * 根据条件得到列表的全部行的指定列数据
	 * @param columns
	 * @param where
	 * @param params
	 * @return
	 */
	public <T> List<T> getListWithExtras(String columns,String where,String extras, Object... params);
	/**
	 * 保存model
	 * insert into table values()
	 * @param model
	 * @return
	 */
	public boolean save(Model<?> model);
	/**
	 * 更新model
	 * update table set what=that
	 * @param model
	 * @return
	 */
	public boolean update(Model<?> model);
	/**
	 * 在系统表中通过传递sql语句执行更新操作
	 *
	 * @param sql
	 * @param params
	 * @return
	 */
	public int updateWithSql( String sql,Object... params);
	/**
	 * 通过制定的set和where参数更新数据
	 * @param set
	 * @param where
	 * @param params
	 * @return
	 */
	public int updateWithSet(String set,String where,Object... params);
	/**
	 * 根据ID 删除数据
	 * @param id
	 * @return
	 * 2013-8-3 下午3:19:49
	 */
	public boolean deleteById(Object id);
	/**
	 * 根据条件删除
	 * @param where
	 * @param params
	 * @return
	 * @author: Jerri Liu
	 * @date: 2014年4月3日下午11:17:32
	 * @return
	 */
	public int delete(String where, Object... params);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 * 2013-8-3 下午3:21:18
	 */
	public boolean deleteByIds(Object[] ids);
	/**
	 * 隐藏数据
	 * update  table set disable=1 where id=?
	 * @param id
	 * @return
	 */
	public boolean disableById(Object id);
	/**
	 * 隐藏数据
	 * delete from table where id in()
	 * @param ids
	 * @return
	 */
	public boolean disableByIds(Object[] ids);
	/**
	 * 根据条件删除数据
	 * @param where
	 * @param params
	 * @return
	 */
	public boolean disable(String where,Object... params);
	/**
	 * 根据ID得到一条记录数据
	 * select * from table where id=?
	 * @param id
	 * @return
	 */
	public <T extends Model<?>> T findById(Object id);
	/**
	 * 根据ID得到一条记录数据
	 * select id,name from table where id=?
	 * @param id
	 * @param columns
	 * @return
	 */
	public <T extends Model<?>> T findById(Object id,String columns);
	/**
	 * 查找得到第一个符合条件的数据
	 * select * from table limit 1
	 * @return
	 */
	public <T extends Model<?>> T findFirst();
	/**
	 * 查找得到第一个符合条件的数据 取得指定列值
	 * select id,name from table limit 1
	 * @param columns
	 * @return
	 */
	public <T extends Model<?>> T findFirstWithColumns(String columns);
	/**
	 * 查找得到第一个符合条件的数据 取得指定列值
	 * select id,name from table where name=？
	 * @param columns
	 * @param where
	 * @param params
	 * @return
	 */
	public <T extends Model<?>> T findFirstByCache(String cacheKey,String columns,String where,Object... params);
	/**
	 * 查找得到第一个符合条件的数据 
	 * select * from table where name=？
	 * @param where
	 * @param params
	 * @return
	 */
	public <T extends Model<?>> T findFirst(String where,Object... params);
	/**
	 * 查找得到第一个符合条件的数据 取得指定列值
	 * select id,name from table where name=？
	 * @param columns
	 * @param where
	 * @param params
	 * @return
	 */
	public <T extends Model<?>> T findFirstWithColumns(String columns,String where,Object... params);
	/**
	 * 分页查询数据
	 * select * from table limit 2,10
	 * @return
	 */
	public Page<?> paginate(int pageNumber,int pageSize);
	/**
	 * 待排序的分页查询数据
	 * select * from table limit 2,10 order by id desc
	 * @return
	 */
	public Page<?> paginate(int pageNumber, int pageSize, String sort, String order);
	/**
	 * 待排序的分页查询数据
	 * select * from table limit 2,10 order by id desc
	 * @return
	 */
	public Page<?> paginate(int pageNumber, int pageSize, String sort, String order, String columns, String where, Object... params);
	/**
	 * 分页查询数据 指定列
	 * select id,name from table limit 2,10
	 * @return
	 */
	public Page<?> paginate(int pageNumber,int pageSize,String columns);
	/**
	 * 分页查询数据 指定列 和搜索条件
	 * select id,name from table where name='张三' limit 2,10
	 * @return
	 */
	public Page<?> paginate(int pageNumber,int pageSize,String columns,String where,Object... params);
	/**
	 * 分页查询数据 指定列 和搜索条件
	 * @return
	 */
	public Page<?> paginateWithExtras(int pageNumber,int pageSize,String columns,String where,String extras,Object... params);
	/**
	 * 分页查询数据 指定列 和搜索条件
	 * @return
	 */
	public Page<?> paginateWithExtras(int pageNumber,int pageSize,String columns,String extras);
	/**
	 * 指定排序参数、方式和where条件
	 * @param pageNumber
	 * @param pageSize
	 * @param sort
	 * @param order
	 * @param columns
	 * @param where
	 * @param params
	 * @return
	 */
	public Page<?> paginateWithWhereAndSort(int pageNumber, int pageSize, String sort,String order, String columns, String where, Object[] params);

	/**
	 * 得到列表的全部行 <br/>从cache中获得数据
	 * @param cacheKey
	 * @return
	 * @author 牟孟孟
	 * 2013-8-2 上午10:27:04
	 */
	public <T> List<T> getListByCache(String cacheKey);
	/**
	 * 得到列表的全部行的指定列数据 <br>从cache中获得数据
	 * select id,name from table
	 * @param cacheKey
	 * @param columns 列组合 例如 "id,name,type"
	 * @return
	 */
	public <T> List<T> getListByCache(String cacheKey,String columns);
	/**
	 * 根据条件得到列表的全部行的指定列数据 
	 * @param cacheKey
	 * @param columns
	 * @param columns
	 * @param where
	 * @param params
	 * @return
	 */
	public <T> List<T> getListByCache(String cacheKey,String columns,String where, Object... params);
	/**
	 * 根据Id搜索数据<br/>从缓存中取得
	 * @param cacheKey
	 * @param id
	 * @return
	 * @author 牟孟孟
	 * 2013-8-2 上午11:25:45
	 */
	public <T extends Model<?>> T findOneByCache(String cacheKey,Object id);
	
}
