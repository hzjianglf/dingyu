package com.handany.bm.service;

import com.github.pagehelper.PageInfo;
import java.util.Map;

import com.handany.bm.model.BmSalesPromotion;


/**
 * 
 * @author Administrator
 *
 */
public interface BmSalesPromotionService {
    /**
     * 保存活动
     * @param salesPromotion
     * @return 
     */
    int saveSalesPromotion(BmSalesPromotion salesPromotion);
    
    /**
     * 查询活动
     * @param queryMap 查询条件
     * @return 
     */
    PageInfo<BmSalesPromotion> querySalesPromotion(Map<String, Object> queryMap);
    
    /**
     * 根据id查询活动信息
     * @param id
     * @return 
     */
    BmSalesPromotion queryById(String id); 
}
