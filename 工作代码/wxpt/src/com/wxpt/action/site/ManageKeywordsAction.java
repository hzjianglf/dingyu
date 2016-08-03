package com.wxpt.action.site;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.*;
import com.wxpt.site.dao.impl.UserDaoImpl;
import com.wxpt.site.entity.Keywordexplicit;
import com.wxpt.site.entity.Keywords;
import com.wxpt.site.service.UserService;

@Results({ @Result(name = "success", type = "json", params = { "root", "result" }) })
@ParentPackage("json-default")
public class ManageKeywordsAction extends ActionSupport {
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "add";
	}

	private String rule;
	private String keywordsContent;
	private String word1;
	private String word2;
	private String word3;
	private String word4;
	private String word5;
	private File vodio;
	private File images1;
	private File images2;
	private String imageValue;
	private String title;
	private String url;
	@Autowired
	UserService userService;
	UserDaoImpl userDao=new UserDaoImpl();
	GetCookie cookies=new GetCookie();
	int enterId=cookies.getCookie();
	public String save() {
		Keywords keywords = new Keywords();
		try {
			keywords.setRule(rule);

		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			keywords.setKeywordcontent(keywordsContent);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			userService.saveKeywords(enterId,keywords);
		} catch (Exception e) {
			// TODO: handle exception
		}

		message = "<script>alert(\"添加成功\");</script>";
		return "add";
	}

	private List<Keywords> listKeywords;

	public String getKeyList() {
		listKeywords = userService.getkeyList(enterId);
		return "update";
	}

	private int keywordId;

	public String updateKey() {
		Keywords k = userService.getByID(enterId,keywordId);
		if(rule!=null &&rule.equals("")==false){
			k.setRule(rule);
		}
		if(keywordsContent != null && keywordsContent.equals("") == false){
			k.setKeywordcontent(keywordsContent);
		}
		
		String sql="UPDATE keywords SET `keywordcontent`='"+k.getKeywordcontent()+"',`wordCount`='"+k.getWordCount()+"',`fileCount`='"+k.getFileCount()+"'," +
				"`imagesCount`='"+k.getImagesCount()+"',`rule`='"+k.getRule()+"' WHERE `keywordID`="+k.getKeywordId()+"";
	/*	userService.update(enterId,sql);*/
		userDao.update(enterId,sql);
		return "success";
	}

	private FileUploadBean bean = new FileUploadBean();

	public String deleteKey() {
		try {
			String ids = "";
			try {
				List<Keywordexplicit> wList = userService.getkExplicitByEkey(enterId,
						keywordId, "word", 0);
				for (int i = 0; i < wList.size(); i++) {
					if ((i + 1) == wList.size()) {
						ids += wList.get(i).getExplicitId();
					} else {
						ids += wList.get(i).getExplicitId() + ", ";
					}
				}
				userService.deleteSql(enterId,ids);
			} catch (Exception e) {
				// TODO: handle exception
			}
			ids = "";
			try {
				List<Keywordexplicit> vList = userService.getkExplicitByEkey(enterId,
						keywordId, "vidio", 0);
				for (int i = 0; i < vList.size(); i++) {
					if ((i + 1) == vList.size()) {
						ids += vList.get(i).getExplicitId();
					} else {
						ids += vList.get(i).getExplicitId() + ", ";
					}
				}
				userService.deleteSql(enterId,ids);
				for (Keywordexplicit keywordexplicit : vList) {
					bean.deletefile("images/" + keywordexplicit.getEcontent());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			ids = "";
			try {
				List<Keywordexplicit> iList = userService.getkExplicitByEkey(enterId,
						keywordId, "images", 0);
				for (int i = 0; i < iList.size(); i++) {
					if ((i + 1) == iList.size()) {
						ids += iList.get(i).getExplicitId();
					} else {
						ids += iList.get(i).getExplicitId() + ", ";
					}
				}
				userService.deleteSql(enterId,ids);
				for (Keywordexplicit keywordexplicit : iList) {
					bean.deletefile("images/" + keywordexplicit.getEcontent());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			ids = "";
			try {
				List<Keywordexplicit> dList = userService.getkExplicitByEkey(enterId,
						keywordId, "images", 1);
				for (int i = 0; i < dList.size(); i++) {
					if ((i + 1) == dList.size()) {
						ids += dList.get(i).getExplicitId();
					} else {
						ids += dList.get(i).getExplicitId() + ", ";
					}
				}
				userService.deleteSql(enterId,ids);
				for (Keywordexplicit keywordexplicit : dList) {
					bean.deletefile("images/" + keywordexplicit.getEcontent());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			userService.deleteKeyBySql(keywordId);
			message = "<script>alert(\"删除成功!\");</script>";
		} catch (Exception e) {
			// TODO: handle exception
			message = "<script>alert(\"删除失败!\");</script>";
		}
		listKeywords = userService.getkeyList(enterId);
		return "update";
	}

	public int getKeywordId() {
		return keywordId;
	}

	public void setKeywordId(int keywordId) {
		this.keywordId = keywordId;
	}

	public List<Keywords> getListKeywords() {
		return listKeywords;
	}

	public void setListKeywords(List<Keywords> listKeywords) {
		this.listKeywords = listKeywords;
	}

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public File getVodio() {
		return vodio;
	}

	public void setVodio(File vodio) {
		this.vodio = vodio;
	}

	public File getImages1() {
		return images1;
	}

	public void setImages1(File images1) {
		this.images1 = images1;
	}

	public File getImages2() {
		return images2;
	}

	public void setImages2(File images2) {
		this.images2 = images2;
	}

	public String getImageValue() {
		return imageValue;
	}

	public void setImageValue(String imageValue) {
		this.imageValue = imageValue;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getWord1() {
		return word1;
	}

	public void setWord1(String word1) {
		this.word1 = word1;
	}

	public String getWord2() {
		return word2;
	}

	public void setWord2(String word2) {
		this.word2 = word2;
	}

	public String getWord3() {
		return word3;
	}

	public void setWord3(String word3) {
		this.word3 = word3;
	}

	public String getWord4() {
		return word4;
	}

	public void setWord4(String word4) {
		this.word4 = word4;
	}

	public String getWord5() {
		return word5;
	}

	public void setWord5(String word5) {
		this.word5 = word5;
	}

	public String getKeywordsContent() {
		return keywordsContent;
	}

	public void setKeywordsContent(String keywordsContent) {
		this.keywordsContent = keywordsContent;
	}

}
