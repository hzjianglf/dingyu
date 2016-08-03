package com.handany.bm.service.impl;

import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handany.base.common.CommonUtils;
import com.handany.base.common.PageUtil;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.bm.dao.BmSalesPromotionMapper;
import com.handany.bm.model.BmSalesPromotion;
import com.handany.bm.service.BmSalesPromotionService;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class BmSalesPromotionServiceImpl implements BmSalesPromotionService {
    @Autowired
    private BmSalesPromotionMapper bmSalesPromotionMapper;

    @Autowired
    private SerialNumberManager serialNumberManager;

    @Override
    public int saveSalesPromotion(BmSalesPromotion salesPromotion) {
        if (salesPromotion.getId() == null) {
            try {
                salesPromotion.setId(serialNumberManager.nextSeqNo("bm_sales_promotion"));
            } catch (Exception ex) {
                Logger.getLogger(BmSalesPromotionServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        salesPromotion.setLastModified(CommonUtils.getCurrentDate());
        
        return bmSalesPromotionMapper.saveSalesPromotion(salesPromotion);
    }

    @Override
    public PageInfo<BmSalesPromotion> querySalesPromotion(Map<String, Object> queryMap) {
        PageUtil.startPage();
        List<BmSalesPromotion> list = bmSalesPromotionMapper.querySalesPromotion(queryMap);
        PageInfo<BmSalesPromotion> page = new PageInfo<BmSalesPromotion>(list);
        
        return page;
    }

    @Override
    public BmSalesPromotion queryById(String id) {
        return bmSalesPromotionMapper.queryById(id);
    }
}
