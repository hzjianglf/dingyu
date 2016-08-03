package com.handany.bm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.handany.base.common.CommonUtils;
import com.handany.base.common.HttpUtil;
import com.handany.base.controller.BaseController;
import com.handany.bm.model.BmPicture;
import com.handany.bm.service.BmPictureService;
import com.handany.rbac.common.UserContextManager;
import com.handany.rbac.model.PmUser;
import com.handany.rbac.service.PmUserService;

@Controller
@RequestMapping("/bm/picture")
public class BmPictureController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(BmPictureController.class);

	@Autowired
	private BmPictureService pictureService;
	
	@Autowired
	private PmUserService userService;
	
	
	
	/**
	 * 头像上传和商铺认证图片上传
	 */
	@RequestMapping(value = "/centerPicUpload")
	public void centerPicUpload() {
		
		String type = HttpUtil.getParameter("type");
		String relateId = HttpUtil.getParameter("relateId");
		
		List<Map<String, String>> rtnList = null;
		try {
			//删除图片
			pictureService.deletePic(type, relateId);
			//添加图片
			rtnList = pictureService.batchInsert();
			//删除缓存
			if(type.equals("userHeader")){
				PmUser user = userService.queryById(relateId);
				if (user != null) {
					userService.clearCache(user);
				}
			}
			
			writeObject("data", rtnList);
			setSuccess(true);
			setErrorCode("0000");
		} catch (Exception e) {
			logger.error("图片信息保存失败",e);
			setSuccess(false);
			setErrorCode("0002");
			setMessage("图片信息保存失败");
		}
	}

	/**
	 * 图片上传
	 * @param type
	 * @param relateId
	 */
	@RequestMapping(value = "/picUpload")
	public void upload(String type, String relateId) {
		
		List<Map<String, String>> rtnList = null;
		try {
			rtnList = pictureService.batchInsert();
			setSuccess(true);
		} catch (Exception e) {
			logger.error("图片信息保存失败",e);
			setSuccess(false);
		}
	
		writeObject("data", rtnList);

	}

	// 图片查询
	@RequestMapping(params = "method=list")
	public void query(@RequestParam(value = "relateId", required = true) String relateId,
			@RequestParam(value = "type", required = true) String type) {

	
		List<BmPicture> pictureList = null;
		try {
			pictureList = pictureService.selectPicList(type,relateId);
			writeObject("data", pictureList);
			setSuccess(true);
		} catch (Exception e) {
			setSuccess(false);
			setMessage("图片查询失败");
			logger.error("",e);
		}
	}

	// 图片删除
	@RequestMapping(params = "method=delete")
	public void delete(@RequestParam(value = "pictureId", required = true) String pictureId,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "relateId", required = true) String relateId) {
		int count = pictureService.deleteByPrimaryKey(pictureId, type, relateId);
		setSuccess(count == 1);
	}

	// 生成图片预编译id
	@RequestMapping(params = "method=nextId")
	public void nextId() {
		Map<String, String> map = pictureService.getNextId();
		writeObject("data", map);
		setSuccess(true);
	}

	//根据id查看图片参数
	@RequestMapping(params = "method=descTextList")
	public void descTextList(@RequestParam(value = "id", required = true) String id) {
		
		BmPicture picture = pictureService.selectById(id);
		String descText = picture.getDescText();
		if (descText == null || descText.trim().length() == 0) {
			writeObject("descText", "");
			writeObject("id", id);
			setSuccess(true);
			return;
		}
		writeObject("descText", descText);
		writeObject("id", id);
		setSuccess(true);
	}

	//修改图片参数
	@RequestMapping(params = "method=descTextUpdate")
	public void descTextUpdate(
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "descText", required = true) String descText,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "relateId", required = true) String relateId) {
		PmUser user = UserContextManager.getLoginUser();
		BmPicture pic = new BmPicture();
		pic.setDescText(descText);
		pic.setId(id);
		pic.setType(type);
		pic.setRelateId(relateId);
		pic.setLastModified(CommonUtils.getCurrentDateStr());
		pic.setLastOperator(user.getId());
		
		int count = pictureService.updateByPrimaryKeySelective(pic);
		setSuccess(count == 1);
	}
	//修改图片参数
	@RequestMapping(params = "method=saveList")
	public void saveList(
			@RequestParam(value = "pictureIds", required = true) String pictureIds,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "relateId", required = true) String relateId) {
		PmUser user = UserContextManager.getLoginUser();
		List<BmPicture> bmPictureList = new ArrayList<BmPicture>();
		if(pictureIds.trim().length() != 0){
			String[] ids = pictureIds.split(com.handany.base.common.Constants.SPLITOR);			
			for(int i = 0; i < ids.length; i ++){
				BmPicture picture = new BmPicture();
				picture.setId(ids[i]);
				picture.setRelateId(relateId);
				picture.setType(type);
				picture.setDisplaySeq(i);		
				picture.setLastOperator(user.getId());
				picture.setLastModified(CommonUtils.getCurrentDateStr());
				//第一个为封面Header设置为T
				if(i == 0){
					picture.setHeader("T");
				}else{
					picture.setHeader("F");
				}
				bmPictureList.add(picture);
			}
		}	
		if(bmPictureList.size() != 0){
			int  count = pictureService.batchUpdate(bmPictureList);
			setSuccess(count > 0);
		}
	}
	
	@RequestMapping(value="setHeader")
	public void setHeader(@RequestParam(value = "picId", required = true)String picId
			,@RequestParam(value = "type", required = true)String type
					,@RequestParam(value = "relateId", required = true)String relateId){
		try {
			pictureService.setHeader(picId,type,relateId);
			setSuccess(true);
		} catch (Exception e) {
			setSuccess(false);
			logger.error("设置图片header失败",e);
		}
	}

}
