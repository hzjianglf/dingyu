package com.handany.bm.controller;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.handany.base.controller.BaseController;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.rbac.common.UserContextManager;
import com.handany.rbac.model.PmUser;
import com.handany.bm.service.BmQaTimeService;

import com.handany.bm.model.BmQaTime;
import java.util.List;

@Controller
@RequestMapping(value = "/bm/qa_time")
public class BmQaTimeController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(BmQaTimeController.class);

    @Autowired
    private BmQaTimeService qatimeService;

    @Autowired
    private SerialNumberManager serialNumberManager;

    /**
     * 学生端获取答疑套餐
     *
     * @param tokenId
     */
    @RequestMapping("/queryAvailableQaTimes")
    public void queryAvailableQaTimes(@RequestParam(required = true) String tokenId) {
        try {
            PmUser user = UserContextManager.getLoginUser();
            String region1 = user.getLoginInfo().getRegion1();
            String region2 = user.getLoginInfo().getRegion2();

            List<BmQaTime> times = qatimeService.queryAvailableQaTimes(region1, region2, null);
            if (times.isEmpty()) {
                times = qatimeService.queryAvailableQaTimes(region1, null, null);
            }

            writeObject("list", times);
            setSuccess(true);
            setErrorCode("0000");
        } catch (Exception e) {
            String msg = "查询套餐失败";
            logger.error(msg, e);
            setSuccess(false);
            setErrorCode("0002");
            setMessage(msg);
        }
    }

    /**
     * 查询所有套餐信息，用于管理端
     * @param start 
     */
    @RequestMapping("/queryAllQaTimes")
    public void queryAllQaTimes(@RequestParam(required = true) String start) {
        try {
            PmUser user = UserContextManager.getLoginUser();
            String region1 = null, 
                   region2 = null, 
                   region3 = null;
            if ("3".equals(user.getUserType())) {
                region1 = user.getLoginInfo().getRegion1();
                region2 = user.getLoginInfo().getRegion2();
                region3 = user.getLoginInfo().getRegion3();
            }

            PageInfo<BmQaTime> times = qatimeService.queryAllQaTimes(region1, region2, region3);

            writeData(times);
            setSuccess(true);
            setErrorCode("0000");
        } catch (Exception e) {
            String msg = "查询套餐失败";
            logger.error(msg, e);
            setSuccess(false);
            setErrorCode("0002");
            setMessage(msg);
        }
    }
    
    /**
     * 新增套餐列表
     * @return 
     */
    @RequestMapping("/addQaTime")
    public String addQaTime() {
        try {
            return transView("/manage/qa_time/addQaTime");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        
        return null;
    }
    
    /**
     * 套餐列表查询页面
     * @return 
     */
    @RequestMapping("/qaTimeList")
    public String qaTimeList() {
        try {
            return transView("/manage/qa_time/qaTimeList");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        
        return null;
    }
    
    /**
     * 保存套餐信息
     * @param qaTime 
     */
    @RequestMapping("/saveQaTime")
    public void saveQaTime(BmQaTime qaTime) {
        try {
            PmUser user = UserContextManager.getLoginUser();
            qaTime.setStatus("1");
            qatimeService.saveQaTime(qaTime);
            setSuccess(true);
            setErrorCode("0000");
        } catch (Exception e) {
            String msg = "查询套餐失败";
            logger.error(msg, e);
            setSuccess(false);
            setErrorCode("0002");
            setMessage(msg);
        }
    }
    
    /**
     * 移除套餐
     * @param id 
     */
    @RequestMapping("/deleteQaTime")
    public void deleteQaTime(@RequestParam(required = true) String id) {
        try {
            PmUser user = UserContextManager.getLoginUser();
            qatimeService.deleteQaTime(id);
            setSuccess(true);
            setErrorCode("0000");
        } catch (Exception e) {
            String msg = "查询套餐失败";
            logger.error(msg, e);
            setSuccess(false);
            setErrorCode("0002");
            setMessage(msg);
        }
    }
}
