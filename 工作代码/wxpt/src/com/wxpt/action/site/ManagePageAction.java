package com.wxpt.action.site;

import java.io.File;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.FileUploadBean;
import com.wxpt.common.PageCommon;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.Page;
import com.wxpt.site.service.WebSiteService;

@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({
		@Result(name = "success", type = "json", params = { "root", "result" }),
		@Result(name = "page", location = "success.jsp") })
public class ManagePageAction extends ParentAction {
	JSONArray jsonls;
	private int count;
	private int page;
	private int rows;
	private List<Page> pageList;
	@Autowired
	WebSiteService webSiteService;
	private String sql;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		sql = "select * from wxpt" + super.getCookieByEnterID() + ".page";
		pageList = webSiteService.getPageLists(sql, page, rows);
		count = webSiteService.getCountBySql(sql);
		try {
			jsonls = JSONArray.fromObject(pageList);
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	private String message;
	private int pageId;
	private String pageTitle;
	private String pageAuthor;
	private String pageNoName;
	private String pageTime;
	private String pageImage;
	private String pageMetaValue;
	private String pageContent;
	private String pageUrl;
	private String pageJumpUrl;
	private File pageImageFile;
	private int result;
	private FileUploadBean fileUploadBean = new FileUploadBean();

	public String add() {
		String imageName = "page_" + super.getCookieByEnterID() + "_"
				+ TimeUtil.getImagesTime() + ".jpg";
		fileUploadBean.upLoad(imageName, pageImageFile,
				super.getCookieByEnterID() + "/page");
		sql = "insert into wxpt"
				+ super.getCookieByEnterID()
				+ ".page (`page_title`, `page_author`, `page_no_name`, `page_time`, `page_image`, `page_metaValue`, `page_content`, `page_url`, `page_jump_url`) VALUES('"
				+ pageTitle + "','" + pageAuthor + "','" + pageNoName + "','"
				+ TimeUtil.getTimes() + "','" + imageName + "','"
				+ pageMetaValue + "','" + pageContent + "','" + pageUrl + "','"
				+ pageJumpUrl + "')";
		result = webSiteService.executeSql(sql);
		if (result == 0) {
			message = "添加成功";
		} else {
			message = "添加失败";
		}
		return "page";
	}

	public String addPage1(){
		String imageName = "page_" + super.getCookieByEnterID() + "_"
				+ TimeUtil.getImagesTime() + ".jpg";
		fileUploadBean.upLoad(imageName, pageImageFile,
				super.getCookieByEnterID() + "/page");
		try {
			Document doc = Jsoup.connect(pageJumpUrl).get();
			pageTitle = doc.title();
		} catch (Exception e) {
			// TODO: handle exception
			message = "添加失败";
			return "page";
		}
		sql = "insert into wxpt"
				+ super.getCookieByEnterID()
				+ ".page (`page_title`, `page_author`, `page_no_name`, `page_time`, `page_image`, `page_metaValue`, `page_content`, `page_url`, `page_jump_url`) VALUES('"
				+ pageTitle + "','" + pageAuthor + "','" + pageNoName + "','"
				+ TimeUtil.getTimes() + "','" + imageName + "','"
				+ pageMetaValue + "','" + pageContent + "','" + pageUrl + "','"
				+ pageJumpUrl + "')";
		result = webSiteService.executeSql(sql);
		if (result == 0) {
			message = "添加成功";
		} else {
			message = "添加失败";
		}
		return "page";
	}
	public String addPage(){
		String imageName = "page_" + super.getCookieByEnterID() + "_"
				+ TimeUtil.getImagesTime() + ".jpg";
		fileUploadBean.upLoad(imageName, pageImageFile,
				super.getCookieByEnterID() + "/page");
		
		sql = "insert into wxpt"
				+ super.getCookieByEnterID()
				+ ".page (`page_title`, `page_author`, `page_no_name`, `page_time`, `page_image`, `page_metaValue`, `page_content`, `page_url`, `page_jump_url`,`weixin_no`) VALUES('"
				+ PageCommon.getTitle(pageJumpUrl) + "','" + PageCommon.getAutorName(pageJumpUrl) + "','" + PageCommon.getNoName(pageJumpUrl) + "','"
				+ PageCommon.getPageTime(pageJumpUrl) + "','" + PageCommon.getImage(pageJumpUrl) + "','"
				+ PageCommon.getMedail(pageJumpUrl) + "','" + PageCommon.getContent(pageJumpUrl) + "','" + PageCommon.getUrl(pageJumpUrl) + "','"
				+ pageJumpUrl + "','"+PageCommon.getWeixinID(pageJumpUrl)+"')";
		result = webSiteService.executeSql(sql);
		if (result == 0) {
			message = "添加成功";
		} else {
			message = "添加失败";
		}
		return "page";
	}
	
	public String update1(){
		try {
			Document doc = Jsoup.connect(pageJumpUrl).get();
			pageTitle = doc.title();
		} catch (Exception e) {
			// TODO: handle exception
			message = "修改失败";
			return "page";
		}
		sql = "select * from wxpt"
				+ super.getCookieByEnterID()
				+ ".page where `page_id` = " + pageId;
		Page page = webSiteService.getPageLists(sql, 1, 1).get(0);
		String imageName = "page_" + super.getCookieByEnterID() + "_"
				+ TimeUtil.getImagesTime() + ".jpg";
		if(pageImageFile != null){
			fileUploadBean.deletefile(super.getCookieByEnterID()+"/page/"+page.getPageImage());
			fileUploadBean.upLoad(imageName, pageImageFile,
					super.getCookieByEnterID() + "/page");
		}else{
			imageName = page.getPageImage();
		}
		sql = "UPDATE wxpt"
				+ super.getCookieByEnterID()
				+ ".page SET `page_title`='"+pageTitle+"',`page_author`='"+pageAuthor+"',`page_no_name`='"+pageNoName+"',`page_image`='"+imageName+"',`page_metaValue`='"+pageMetaValue+"',`page_content`='"+pageContent+"',`page_url`='"+pageUrl+"',`page_jump_url`='"+pageJumpUrl+"'  WHERE `page_id` = " +pageId;
		result = webSiteService.executeSql(sql);
		if(result == 0){
			message = "修改成功";
		}else{
			message = "修改失败";
		}
		return "page";
	}
	public String update(){
		
		sql = "select * from wxpt"
				+ super.getCookieByEnterID()
				+ ".page where `page_id` = " + pageId;
		Page page = webSiteService.getPageLists(sql, 1, 1).get(0);
		String imageName = "page_" + super.getCookieByEnterID() + "_"
				+ TimeUtil.getImagesTime() + ".jpg";
		if(pageImageFile != null){
			fileUploadBean.deletefile(super.getCookieByEnterID()+"/page/"+page.getPageImage());
			fileUploadBean.upLoad(imageName, pageImageFile,
					super.getCookieByEnterID() + "/page");
		}else{
			imageName = page.getPageImage();
		}
		sql = "UPDATE wxpt"
				+ super.getCookieByEnterID()
				+ ".page SET `page_title`='"+PageCommon.getTitle(pageJumpUrl)+"',`page_author`='"+PageCommon.getAutorName(pageJumpUrl)+"',`page_no_name`='"+PageCommon.getNoName(pageJumpUrl)+"',`page_image`='"+PageCommon.getImage(pageJumpUrl)+"',`page_metaValue`='"+PageCommon.getMedail(pageJumpUrl)+"',`page_content`='"+PageCommon.getContent(pageJumpUrl)+"',`page_url`='"+PageCommon.getUrl(pageJumpUrl)+"',`page_jump_url`='"+pageJumpUrl+"',`weixin_no` = '"+PageCommon.getWeixinID(pageJumpUrl)+"' WHERE `page_id` = " +pageId;
		result = webSiteService.executeSql(sql);
		if(result == 0){
			message = "修改成功";
		}else{
			message = "修改失败";
		}
		return "page";
	}
	
	private String pageIds;
	
	public String delete(){
		String[] pId = pageIds.split(",");
		for (int i = 0; i < pId.length; i++) {
			webSiteService.executeSql("DELETE FROM wxpt"
				+ super.getCookieByEnterID()
				+ ".page where `page_id` = "+Integer.parseInt(pId[i]));
		}
		return "success";
	}
	
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public List<Page> getPageList() {
		return pageList;
	}

	public void setPageList(List<Page> pageList) {
		this.pageList = pageList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getPageAuthor() {
		return pageAuthor;
	}

	public void setPageAuthor(String pageAuthor) {
		this.pageAuthor = pageAuthor;
	}

	public String getPageNoName() {
		return pageNoName;
	}

	public void setPageNoName(String pageNoName) {
		this.pageNoName = pageNoName;
	}

	public String getPageTime() {
		return pageTime;
	}

	public void setPageTime(String pageTime) {
		this.pageTime = pageTime;
	}

	public String getPageImage() {
		return pageImage;
	}

	public void setPageImage(String pageImage) {
		this.pageImage = pageImage;
	}

	public String getPageMetaValue() {
		return pageMetaValue;
	}

	public void setPageMetaValue(String pageMetaValue) {
		this.pageMetaValue = pageMetaValue;
	}

	public String getPageContent() {
		return pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getPageJumpUrl() {
		return pageJumpUrl;
	}

	public void setPageJumpUrl(String pageJumpUrl) {
		this.pageJumpUrl = pageJumpUrl;
	}

	public File getPageImageFile() {
		return pageImageFile;
	}

	public void setPageImageFile(File pageImageFile) {
		this.pageImageFile = pageImageFile;
	}

	public String getPageIds() {
		return pageIds;
	}

	public void setPageIds(String pageIds) {
		this.pageIds = pageIds;
	}

}
