package com.handany.bm.controller;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.handany.base.controller.BaseController;
import com.handany.base.model.LoginInfo;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.rbac.common.UserContextManager;

import com.handany.bm.model.BmSalesPromotion;
import com.handany.bm.service.BmSalesPromotionService;
import com.handany.rbac.model.PmUser;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/bm/sales_promotion")
public class BmSalesPromotionController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BmSalesPromotionController.class);

    @Autowired
    private BmSalesPromotionService salespromotionService;

    @Autowired
    private SerialNumberManager serialNumberManager;
    
    /**
     * 保存活动信息
     * @param salesPromotion 
     */
    @RequestMapping("/save")
    public void save(BmSalesPromotion salesPromotion) {
        try {
            if (salesPromotion.getId() == null) {
                PmUser user = UserContextManager.getLoginUser();
                
                salesPromotion.setUserId(user.getId());
                salesPromotion.setStatus("1");
            }
            
            salespromotionService.saveSalesPromotion(salesPromotion);
            setSuccess(true);
            setErrorCode("0000");
        } catch (Exception e) {
            String msg = "保存活动信息失败";
            logger.error(msg, e);
            setSuccess(false);
            setErrorCode("0002");
            setMessage(msg);
        }
    }
    
    /**
     * 查询活动信息
     * @param start
     * @param region1
     * @param region2
     * @param region3
     * @param status
     * @param name 
     */
    @RequestMapping("/query")
    public void query(
            @RequestParam(required = true) String start,
            String region1, String region2, 
            String region3, String status, String name) {
        try {
            PmUser user = UserContextManager.getLoginUser();
            if ("3".equals(user.getUserType())) {
                LoginInfo info = user.getLoginInfo();
                if (info.getRegion1() != null) {
                    region1 = info.getRegion1();
                }
                
                if (info.getRegion2() != null) {
                    region2 = info.getRegion2();
                }
                
                if (info.getRegion3() != null) {
                    region3 = info.getRegion3();
                }
            }
            
            if ("".equals(status)) {
                status = null;
            }
            
            HashMap<String, Object> queryMap = new HashMap<String, Object>();
            queryMap.put("region1", region1);
            queryMap.put("region2", region2);
            queryMap.put("region3", region3);
            queryMap.put("status", status);
            queryMap.put("name", name);
            
            PageInfo<BmSalesPromotion> page = salespromotionService.querySalesPromotion(queryMap);
            writeData(page);
            setSuccess(true);
            setErrorCode("0000");
        } catch (Exception e) {
            String msg = "查询活动信息失败";
            logger.error(msg, e);
            setSuccess(false);
            setErrorCode("0002");
            setMessage(msg);
        }
    }
    
    /**
     * 将活动提交审批
     * @param id 
     */
    @RequestMapping("/submit")
    public void submit(@RequestParam(required = true) String id) {
        try {
            BmSalesPromotion salesPromotion = new BmSalesPromotion();
            salesPromotion.setId(id);
            salesPromotion.setStatus("2"); //状态设置为待审批
            salespromotionService.saveSalesPromotion(salesPromotion);
            setSuccess(true);
            setErrorCode("0000");
        } catch (Exception e) {
            String msg = "提交审批失败";
            logger.error(msg, e);
            setSuccess(false);
            setErrorCode("0002");
            setMessage(msg);
        }
    }
    
    /**
     * 保存审批结果
     * @param id
     * @param status: 1:通过 0:未通过
     * @param approvalInfo 
     */
    @RequestMapping("/saveApproval")
    public void saveApproval(@RequestParam(required = true) String id,
            @RequestParam(required = true) String status,
            String approvalInfo) {
        try {
            BmSalesPromotion salesPromotion = new BmSalesPromotion();
            salesPromotion.setId(id);
            if ("1".equals(status)) {
                salesPromotion.setStatus("3");
            } else {
                salesPromotion.setStatus("4");
            }
            
            salesPromotion.setApprovalInfo(approvalInfo);
            
            salespromotionService.saveSalesPromotion(salesPromotion);
            setSuccess(true);
            setErrorCode("0000");
        } catch (Exception e) {
            String msg = "提交审批信息失败";
            logger.error(msg, e);
            setSuccess(false);
            setErrorCode("0002");
            setMessage(msg);
        }
    }
    
    @RequestMapping("/queryById")
    public void queryById(@RequestParam(required = true) String id) {
        try {
            BmSalesPromotion salesPromotion = salespromotionService.queryById(id);
            if (salesPromotion == null) {
                String msg = "未查询到符合要求的活动信息";
                logger.error(msg, id);
                setSuccess(false);
                setErrorCode("0001");
                setMessage(msg);
            } else {
                writeObject("salesPromotion", salesPromotion);
                setSuccess(true);
                setErrorCode("0000");
            }
        } catch (Exception e) {
            String msg = "查询活动信息失败";
            logger.error(msg, e);
            setSuccess(false);
            setErrorCode("0002");
            setMessage(msg);
        }
    }
    
    @RequestMapping("/addPromotion")
    public String addPromotion() {
        try {
            String promotionId = serialNumberManager.nextSeqNo("bm_sales_promotion");
            writeObject("promotionId", promotionId);
            setSuccess(true);
            return transView("/manage/promotion/addPromotion");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        
        return null;
    }
    
    @RequestMapping("/promotionMgt")
    public String promotionMgt() {
        try {
            return transView("/manage/promotion/promotionMgt");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        
        return null;
    }
}