package com.handany.bm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.handany.base.common.CommonUtils;
import com.handany.base.container.CacheContainer;
import com.handany.base.controller.BaseController;
import com.handany.base.model.LoginInfo;
import com.handany.base.push.ShortMessageManager;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.rbac.common.UserContextManager;
import com.handany.rbac.model.PmUser;
import com.handany.bm.model.BmFavorite;
import com.handany.bm.service.BmFavoriteService;

@Controller
@RequestMapping(value = "/bm/favorite")
public class BmFavoriteController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(BmFavoriteController.class);

	@Autowired
	private BmFavoriteService favoriteService;

	@Autowired
	private SerialNumberManager serialNumberManager;
	
	/**
	 * 添加收藏
	 * @param teacherId
	 */
	@RequestMapping("add")
	public void add(@RequestParam(required = true) String teacherId) {
		String msg = "";
		try {
			PmUser user = UserContextManager.getLoginUser();
			if (!"1".equals(user.getUserType())) {
				msg = "用户非学生用户！";
				setSuccess(false);
				setErrorCode("0002");
				setMessage(msg);
			} else {
				BmFavorite favorite = new BmFavorite();
				favorite.setStudentId(user.getId());
				favorite.setTeacherId(teacherId);
				
				favoriteService.saveFavorite(favorite);
				setSuccess(true);
				setErrorCode("0000");
			}
		} catch (Exception e) {
			msg = "添加收藏失败";
			logger.error(msg, e);
			setSuccess(false);
			setErrorCode("0002");
			setMessage(msg);
		}
	}
	
	
	/**
	 * 查询收藏
	 * @param start
	 */
	@RequestMapping("/queryFavorites")
	public void queryFavorites(@RequestParam(required = true) String start) {
		String msg = "";
		try {
			PmUser user = UserContextManager.getLoginUser();
			if (!"1".equals(user.getUserType())) {
				msg = "用户非学生用户！";
				setSuccess(false);
				setErrorCode("0002");
				setMessage(msg);
			} else {
				PageInfo<BmFavorite> page = favoriteService.queryFavorites(user.getId());
				writeData(page);
				setSuccess(true);
				setErrorCode("0000");
			}
		} catch (Exception e) {
			msg = "添加收藏失败";
			logger.error(msg, e);
			setSuccess(false);
			setErrorCode("0002");
			setMessage(msg);
		}
	}
	
	/**
	 * 取消收藏
	 * @param teacherId 教师userId
	 */
	@RequestMapping("/delete")
	public void delete(@RequestParam(required = true) String teacherId) {
		String msg = "";
		try {
			PmUser user = UserContextManager.getLoginUser();
			if (!"1".equals(user.getUserType())) {
				msg = "用户非学生用户！";
				setSuccess(false);
				setErrorCode("0002");
				setMessage(msg);
			} else {
				int iRet = favoriteService.deleteFavorite(user.getId(), teacherId);
				
				setSuccess(true);
				setErrorCode("0000");
			}
		} catch (Exception e) {
			msg = "添加收藏失败";
			logger.error(msg, e);
			setSuccess(false);
			setErrorCode("0002");
			setMessage(msg);
		}
	}
}