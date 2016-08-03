package com.wxpt.action.site;

import java.io.File;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.*;
import com.wxpt.site.dao.impl.UserDaoImpl;
import com.wxpt.site.entity.Keywordexplicit;
import com.wxpt.site.entity.Keywords;
import com.wxpt.site.service.UserService;

@Results({
		@Result(name = "success", type = "json", params = { "root", "result" }),
		@Result(name = "error", location = "success.jsp") })
@ParentPackage("json-default")
public class ManageKeywordsexplicitAction extends ActionSupport {
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "add";
	}

	private int type;
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
	private List<Keywords> listKeywords;
	private FileUploadBean bean = new FileUploadBean();

	public String getKeyList() {
		listKeywords = userService.getkeyList(enterId);
		return "add";
	}

	private int keywordId;
	private String vidoName;
	private String vodioTitle;
	private String vodioValue;
	private int keywordID;
	GetCookie cookies=new GetCookie();
	int enterId=cookies.getCookie();
	public int getKeywordID() {
		return keywordID;
	}

	public void setKeywordID(int keywordID) {
		this.keywordID = keywordID;
	}

	public String save() {
		if (keywordID != 0) {
			Keywords keywords = userService.getByID(enterId,keywordID);
			if (word1 != null && !word1.equals("")) {
				Keywordexplicit keywordexplicit = new Keywordexplicit();
				keywordexplicit.setAddTime(TimeUtil.getTime());
				keywordexplicit.setKeywords(keywords);
				keywordexplicit.setEkey("word");
				keywordexplicit.setEcontent(word1);
				keywordexplicit.setEinstruction("");
				keywordexplicit.setType(0);
				try {
					userService.saveKeywordsexplicit(enterId,keywordexplicit);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
			if (vodio != null) {
				String name = TimeUtil.getImagesTime();
				bean.upLoad(
						name+ vidoName.substring(vidoName.lastIndexOf("."),
										vidoName.length()), vodio,enterId+"");
				Keywordexplicit keywordexplicit = new Keywordexplicit();
				keywordexplicit.setAddTime(TimeUtil.getWeixinTime());
				keywordexplicit.setKeywords(keywords);
				keywordexplicit.setEkey("vidio");
				keywordexplicit.setEcontent(name
						+ vidoName.substring(vidoName.lastIndexOf("."),
								vidoName.length()));
				if (vodioValue != null) {
					keywordexplicit.setEinstruction(vodioValue);
				}
				if (vodioTitle != null) {
					keywordexplicit.setTitle(vodioTitle);
				}
				keywordexplicit.setType(0);
				try {

					userService.saveKeywordsexplicit(enterId,keywordexplicit);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

			if (images1 != null) {
				String name = TimeUtil.getImagesTime();
				bean.upLoad(name + ".jpg", images1,enterId+"");
				Keywordexplicit keywordexplicit = new Keywordexplicit();
				keywordexplicit.setAddTime(TimeUtil.getWeixinTime());
				keywordexplicit.setKeywords(keywords);
				keywordexplicit.setEkey("images");
				keywordexplicit.setEcontent(name + ".jpg");
				if (imageValue != null) {
					keywordexplicit.setEinstruction(imageValue);
				}
				if (title != null) {
					keywordexplicit.setTitle(title);
				}
				if (url != null) {
					keywordexplicit.setUrl(url);
				}
				keywordexplicit.setType(type);
				try {

					userService.saveKeywordsexplicit(enterId,keywordexplicit);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			if (images2 != null) {
				String name = TimeUtil.getImagesTime();
				bean.upLoad(name + ".jpg", images2,enterId+"");
				Keywordexplicit keywordexplicit = new Keywordexplicit();
				keywordexplicit.setAddTime(TimeUtil.getWeixinTime());
				keywordexplicit.setKeywords(keywords);
				keywordexplicit.setEkey("images");
				keywordexplicit.setEcontent(name + ".jpg");
				if (imageValue != null) {
					keywordexplicit.setEinstruction(imageValue);
				}
				if (title != null) {
					keywordexplicit.setTitle(title);
				}
				if (title != null) {
					keywordexplicit.setTitle(title);
				}
				if (url != null) {
					keywordexplicit.setUrl(url);
				}
				userService.saveKeywordsexplicit(enterId,keywordexplicit);
			}
			message = "添加成功!";
		} else {
			message = "添加失败!";
		}

		return "error";
	}
private int explicitId;
	
	public int getExplicitId() {
		return explicitId;
	}

	public void setExplicitId(int explicitId) {
		this.explicitId = explicitId;
	}
	public String update(){
		if (keywordID != 0 && explicitId != 0) {
			Keywords keywords = userService.getByID(enterId,keywordID);
			
			if (word1 != null && !word1.equals("")) {
				Keywordexplicit keywordexplicit = userService.getkExplicitBy(enterId,explicitId);
				keywordexplicit.setAddTime(TimeUtil.getTime());
				keywordexplicit.setKeywords(keywords);
				keywordexplicit.setEkey("word");
				keywordexplicit.setEcontent(word1);
				keywordexplicit.setEinstruction("");
				keywordexplicit.setType(0);
				try {
					userDao.updateKeywordsexplicit(enterId,keywordexplicit);
					//userDao.saveKeywordsexplicit(enterId,keywordexplicit);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
			if (vodio != null) {
				String name = TimeUtil.getImagesTime();
				bean.upLoad(name + vidoName.substring(vidoName.lastIndexOf("."),vidoName.length()), vodio,enterId+"");
				Keywordexplicit keywordexplicit = userService.getkExplicitBy(enterId,explicitId);
				if(keywordexplicit.getEkey().equals("vidio")){
					bean.deletefile(enterId+"/" + keywordexplicit.getEcontent());
				}
				keywordexplicit.setAddTime(TimeUtil.getWeixinTime());
				keywordexplicit.setKeywords(keywords);
				keywordexplicit.setEkey("vidio");
				keywordexplicit.setEcontent(name + vidoName.substring(vidoName.lastIndexOf("."),vidoName.length()));
				if (vodioValue != null) {
					keywordexplicit.setEinstruction(vodioValue);
				}
				if (vodioTitle != null) {
					keywordexplicit.setTitle(vodioTitle);
				}
				keywordexplicit.setType(0);
				try {
					userDao.updateKeywordsexplicit(enterId,keywordexplicit);
					/*userService.saveKeywordsexplicit(enterId,keywordexplicit);*/
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}	
			}

			if (images1 != null) {
				String name = TimeUtil.getImagesTime();
				bean.upLoad(name + ".jpg", images1,enterId+"");
				Keywordexplicit keywordexplicit = userService.getkExplicitBy(enterId,explicitId);
				if(keywordexplicit.getEkey().equals("images")){
					bean.deletefile(enterId+"/" + keywordexplicit.getEcontent());
				}
				keywordexplicit.setAddTime(TimeUtil.getWeixinTime());
				keywordexplicit.setKeywords(keywords);
				keywordexplicit.setEkey("images");
				keywordexplicit.setEcontent(name + ".jpg");
				if (imageValue != null) {
					keywordexplicit.setEinstruction(imageValue);
				}
				if (title != null) {
					keywordexplicit.setTitle(title);
				}
				if (url != null) {
					keywordexplicit.setUrl(url);
				}
				keywordexplicit.setType(type);
				try {
					userDao.updateKeywordsexplicit(enterId,keywordexplicit);
					/*userService.saveKeywordsexplicit(enterId,keywordexplicit);*/
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			if (images2 != null) {
				String name = TimeUtil.getImagesTime();
				bean.upLoad(name + ".jpg", images2,enterId+"");
				Keywordexplicit keywordexplicit = new Keywordexplicit();
				keywordexplicit.setAddTime(TimeUtil.getWeixinTime());
				keywordexplicit.setKeywords(keywords);
				keywordexplicit.setEkey("images");
				keywordexplicit.setEcontent(name + ".jpg");
				if (imageValue != null) {
					keywordexplicit.setEinstruction(imageValue);
				}
				if (title != null) {
					keywordexplicit.setTitle(title);
				}
				if (title != null) {
					keywordexplicit.setTitle(title);
				}
				if (url != null) {
					keywordexplicit.setUrl(url);
				}
				userDao.updateKeywordsexplicit(enterId,keywordexplicit);
				/*userService.saveKeywordsexplicit(enterId,keywordexplicit);*/
				
			}
			message = "修改成功!";
		}else{
			message = "修改失败!";
		}
		return "error";
	}
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Keywords> getListKeywords() {
		return listKeywords;
	}

	public void setListKeywords(List<Keywords> listKeywords) {
		this.listKeywords = listKeywords;
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

	public int getKeywordId() {
		return keywordId;
	}

	public void setKeywordId(int keywordId) {
		this.keywordId = keywordId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getVidoName() {
		return vidoName;
	}

	public void setVidoName(String vidoName) {
		this.vidoName = vidoName;
	}

	public String getVodioTitle() {
		return vodioTitle;
	}

	public void setVodioTitle(String vodioTitle) {
		this.vodioTitle = vodioTitle;
	}

	public String getVodioValue() {
		return vodioValue;
	}

	public void setVodioValue(String vodioValue) {
		this.vodioValue = vodioValue;
	}

}
