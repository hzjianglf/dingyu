package com.handany.base.controller;

import com.github.pagehelper.PageInfo;
import com.handany.base.common.CommonUtils;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.handany.base.common.PmSysInfoManager;
import com.handany.base.model.PmSysInfo;
import com.handany.base.service.SystemInfoService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping(value = "/sys/sysInfo")
public class SystemInfoController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(SystemInfoController.class);
    
    @Autowired
    private SystemInfoService systemInfoService;

    /**
     * 查询缓存版本信息
     * @param deviceType 
     */
    @RequestMapping(value = "/open/detail")
    public void getSysInfo(@RequestParam(value = "deviceType", required = true) String deviceType) {
        try {
            Map<String, String> sysMap = PmSysInfoManager.getSysInfo(deviceType);

            writeData(sysMap);
            setSuccess(true);
        } catch (Exception ex) {
            setSuccess(false);
            logger.error("", ex);
        }
    }

    /**
     * 刷新缓存版本信息
     * @param deviceType 
     */
    @RequestMapping(value = "refresh")
    public void refreshSysInfo(@RequestParam(value = "deviceType", required = true) String deviceType) {
        PmSysInfoManager.refresh(deviceType);
        setSuccess(true);
        setMessage("更新成功");
    }

    /**
     * 客户端版本管理
     * @return 
     */
    @RequestMapping("/sysInfoList")
    public String sysInfoList() {
        try {
            return transView("/manage/user/sysInfoList");
        } catch (Exception e) {

        }

        return null;
    }
    
    /**
     * 查询客户端版本列表
     * @param deviceType 
     */
    @RequestMapping("/query")
    public void query(String deviceType) {
        try {
            HashMap<String, Object> queryMap = new HashMap<String, Object>();
            queryMap.put("deviceType", deviceType);
            PageInfo<PmSysInfo> page = systemInfoService.querySystemInfo(queryMap);
            writeData(page);
            setSuccess(true);
            setErrorCode("0000");
        } catch (Exception e) {
            String msg = "查询版本信息列表失败";
            logger.error(msg, e);
            setSuccess(false);
            setErrorCode("0002");
            setMessage(msg);
        }
    }
    
    /**
     * 版本信息详情页面
     * @return 
     */
    @RequestMapping("/sysInfoDetail")
    public String sysInfoDetail() {
        try {
            return transView("/manage/user/sysInfoDetail");
        } catch (Exception e) {

        }
        
        return null;
    }
    
    /**
     * 查询版本信息详情
     * @param version
     * @param type 
     */
    @RequestMapping("/detail")
    public void detail(@RequestParam(required = true) String version,
            @RequestParam(required = true) String type) {
        try {
            PmSysInfo info = systemInfoService.getSystemInfo(version, type);
            writeObject("info", info);
            setSuccess(true);
            setErrorCode("0000");
        } catch (Exception e) {
            String msg = "查询版本信息失败";
            logger.error(msg, e);
            setSuccess(false);
            setErrorCode("0002");
            setMessage(msg);
        }
    }
    
    /**
     * 版本信息新增页面
     * @return 
     */
    @RequestMapping("/addSystemInfo")
    public String addSystemInfo() {
        try {
            return transView("/manage/user/addSystemInfo");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        
        return null;
    }
    
    /**
     * 保存版本信息
     * @param info 
     */
    @RequestMapping("/saveSystemInfo")
    public void saveSystemInfo(
            PmSysInfo info
    ) {
        try {
            info.setPublishdate(CommonUtils.getCurrentDateStr());
            systemInfoService.saveSystemInfo(info);
            PmSysInfoManager.refresh(info.getDevicetype());
            setSuccess(true);
            setErrorCode("0000");
        } catch (Exception e) {
            String msg = "保存版本信息失败";
            logger.error(msg, e);
            setSuccess(false);
            setErrorCode("0002");
            setMessage(msg);
        }
    }
}
