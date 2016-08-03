package com.wxpt.action.site;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.FileUploadBean;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.Card;
import com.wxpt.site.entity.Imageroll;
import com.wxpt.site.entity.Keywordexplicit;
import com.wxpt.site.entity.Move;
import com.wxpt.site.entity.pojo.CardPojo;
import com.wxpt.site.entity.pojo.KeyListPojo;
import com.wxpt.site.service.CardService;
import com.wxpt.site.service.ImageService;
import com.wxpt.site.service.MoveService;
@SuppressWarnings("serial")
@Results({ @Result(name = "success", type = "json", params = { "root", "result" }) })
@ParentPackage("json-default")
public class ManageCard2Action extends ParentAction {

	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	Imageroll image;
	ImageService imageservice;
	private File images1;
	String result;
	public int imageId;
	private int moveState;
	JSONArray jsonls;
	private String cardType;
	private File cardImage;
	private Card card;
	private List<Card> cardList;
	private int prizeCountNum;
	private int onePrizeCount;
	private int twoPrizeCount;
	private int threePrizeCount;
	private int fourPrizeCount;
	private int fivePrizeCount;
	private int sixPrizeCount;
	private int sevenPrizeCount;
	private int eightPrizeCount;
	private int ninePrizeCount;
	private int tenPrizeCount;
	private int count;// 总记录数
	private int rows;
	private int page;
	private List<CardPojo> cardPojoList;
	private StringBuffer sb;
	GetCookie cookies= new GetCookie();
	int enterId=cookies.getCookie();
	 @Autowired
	 CardService cardService;
	 @Autowired
	 MoveService moveService;
	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	private FileUploadBean bean = new FileUploadBean();

	private List<Imageroll> ImagerollList;
	private List<Imageroll> ImagerollList1;
	private List<Imageroll> ImagerollList2;

	public List<Imageroll> getImagerollList() {
		return ImagerollList;
	}

	public void setImagerollList(List<Imageroll> imagerollList) {
		ImagerollList = imagerollList;
	}
	
	public List<Imageroll> getImagerollList1() {
		return ImagerollList1;
	}

	public void setImagerollList1(List<Imageroll> imagerollList1) {
		ImagerollList1 = imagerollList1;
	}

	public List<Imageroll> getImagerollList2() {
		return ImagerollList2;
	}

	public void setImagerollList2(List<Imageroll> imagerollList2) {
		ImagerollList2 = imagerollList2;
	}
	/*检测活动时间是否结束*/
	public String checkMoveTime(){
		try {
			Move move =moveService.findByMoveName("刮刮乐",enterId).get(0);
			moveState=move.getMoveState();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "merror";
		}
		return "mtime";
	}
	
	
	/*中奖概率自定义设置开始*/
	public void setPrizeChance() throws Exception{
		List<Card> list=new ArrayList<Card>();
		list=cardService.findQuery(enterId);
		try {
			for(int i=0;i<list.size();i++){
				if(list.get(i).getCardType()==1){
					list.get(i).setCardCount(onePrizeCount);
					cardService.updateCard(list.get(i),enterId);
				}
				if(list.get(i).getCardType()==2){
					list.get(i).setCardCount(twoPrizeCount);
					cardService.updateCard(list.get(i),enterId);
				}
				if(list.get(i).getCardType()==3){
					list.get(i).setCardCount(threePrizeCount);
					cardService.updateCard(list.get(i),enterId);
				}
				if(list.get(i).getCardType()==4){
					list.get(i).setCardCount(fourPrizeCount);
					cardService.updateCard(list.get(i),enterId);
				}
				if(list.get(i).getCardType()==5){
					list.get(i).setCardCount(fivePrizeCount);
					cardService.updateCard(list.get(i),enterId);
				}
				if(list.get(i).getCardType()==6){
					list.get(i).setCardCount(sixPrizeCount);
					cardService.updateCard(list.get(i),enterId);
				}
				if(list.get(i).getCardType()==7){
					list.get(i).setCardCount(sevenPrizeCount);
					cardService.updateCard(list.get(i),enterId);
				}
				if(list.get(i).getCardType()==8){
					list.get(i).setCardCount(eightPrizeCount);
					cardService.updateCard(list.get(i),enterId);
				}
				if(list.get(i).getCardType()==9){
					list.get(i).setCardCount(ninePrizeCount);
					cardService.updateCard(list.get(i),enterId);
				}
				if(list.get(i).getCardType()==10){
					list.get(i).setCardCount(tenPrizeCount);
					cardService.updateCard(list.get(i),enterId);
				}
//				if(list.get(i).getCardType()==10){
//					list.get(i).setCardCount(tenPrizeCount);
//					cardService.updateCard(list.get(i));
//				}
//				if(list.get(i).getCardType()==10){
//					list.get(i).setCardCount(tenPrizeCount);
//					cardService.updateCard(list.get(i));
//				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*中奖概率自定义设置结束*/
	//六格图片查询
	public String queryImage() {
		cardList=cardService.findAllQuery(page, rows,enterId);
		ImagerollList = imageservice.getImageList(enterId);
		
		 if(cardList==null||cardList.size()==0){
				out.print("");
			}else{
	          
				//list集合转换json格式
				JsonConfig jsonConfig=new JsonConfig(); 
				jsonConfig.setExcludes(new String[] { "cardRecordses"});
				JSONArray jsonArrayFromList = JSONArray.fromObject(cardList,jsonConfig);
				StringBuffer sb = new StringBuffer();
				//循环遍历获取角色和部门
				String str =jsonArrayFromList.toString();
				String strpath =ServletActionContext.getServletContext().getRealPath("/");
		
			
				for(int i=0;i<cardList.size();i++){
					String temp = "";
				
				
					temp =str.substring(0,str.indexOf("}"));
				
					sb.append(temp);	
					if(cardList.get(i).getCardImage()!=null){
						sb.append(",\"imageTemp\":\"<img  width='80' height='86' src='\\\\wxpt\\\\web\\\\images\\\\"+cardList.get(i).getCardImage().replace("/", "\\\\\\\\")+"' ///>\"");
						if(cardList.get(i).getCardType()==1){
							sb.append(",\"cardTypeName\":\"一等奖\"");
						}
						if(cardList.get(i).getCardType()==2){
							sb.append(",\"cardTypeName\":\"二等奖\"");
						}
						if(cardList.get(i).getCardType()==3){
							sb.append(",\"cardTypeName\":\"三等奖\"");
						}
						if(cardList.get(i).getCardType()==4){
							sb.append(",\"cardTypeName\":\"四等奖\"");
						}
						if(cardList.get(i).getCardType()==5){
							sb.append(",\"cardTypeName\":\"五等奖\"");
						}
						if(cardList.get(i).getCardType()==6){
							sb.append(",\"cardTypeName\":\"六等奖\"");
						}
						if(cardList.get(i).getCardType()==-1){
							sb.append(",\"cardTypeName\":\"重复刮奖\"");
						}
						if(cardList.get(i).getCardType()==0){
							sb.append(",\"cardTypeName\":\"未中奖\"");
						}
						
						}
					
					
					sb.append("}");
					
					str =str.substring(str.indexOf("}")+1);
				}
				sb.append("]");
				
				
				System.out.println(sb.toString());
			 out.print("{\"total\":"+0+",\"rows\":" + sb.toString() + "}");
			System.out.println("{\"total\":"+0+",\"rows\":" + sb.toString() + "}");
			
			out.flush();
			out.close();
			
		}

		return "prize";
	}

	public String queryImage2() {
		// TODO Auto-generated method stub
		cardList=cardService.findAllQuery(page, rows,enterId);
		cardPojoList = new ArrayList<CardPojo>();
		int cardType=0;
		
		for (Card k : cardList) {
			CardPojo cp =new CardPojo();
			cp.setCardCount(k.getCardCount());
			cp.setCardId(k.getCardId());
			cp.setCardImage(k.getCardImage());
			cp.setCardName(k.getCardName());
			cp.setCardType(k.getCardType());
			cardType=k.getCardType();
			if(cardType==1){
				cp.setCardTypeDesc("一等奖");
			}
			if(cardType==2){
				cp.setCardTypeDesc("二等奖");
			}
			if(cardType==3){
				cp.setCardTypeDesc("三等奖");
			}
			if(cardType==4){
				cp.setCardTypeDesc("四等奖");
			}
			if(cardType==5){
				cp.setCardTypeDesc("五等奖");
			}
			if(cardType==6){
				cp.setCardTypeDesc("六等奖");
			}
			if(cardType==7){
				cp.setCardTypeDesc("七等奖");
			}
			if(cardType==8){
				cp.setCardTypeDesc("八等奖");
			}
			if(cardType==9){
				cp.setCardTypeDesc("九等奖");
			}
			if(cardType==10){
				cp.setCardTypeDesc("十等奖");
			}
			if(cardType==-1){
				cp.setCardTypeDesc("重复刮奖");
			}
			if(cardType==0){
				cp.setCardTypeDesc("未中奖");
			}
			if(cardType==-2){
				cp.setCardTypeDesc("回复图片");
			}
			cardPojoList.add(cp);
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "user" });
			jsonls = JSONArray.fromObject(cardPojoList);
			 sb = new StringBuffer();
			//循环遍历获取角色和部门
			String str =jsonls.toString();
			String strpath =ServletActionContext.getServletContext().getRealPath("/");
			for(int i=0;i<cardPojoList.size();i++){
				String temp = "";
				temp =str.substring(0,str.indexOf("}"));
				sb.append(temp);	
				if(cardPojoList.get(i).getCardImage()!=null){
					sb.append(",\"imageTemp\":\"<img  width='80' height='86' src='\\\\wxpt\\\\web\\\\images\\\\"+enterId+"\\\\"+cardList.get(i).getCardImage().replace("/", "\\\\\\\\")+"' ///>\"");
					}else{
						sb.append(",\"imageTemp\":\"<img  width='80' height='86' src='\\\\wxpt\\\\web\\\\images\\\\"+enterId+"\\\\"+"card-wutu.jpg"+"' ///>\"");
					}
				
				
				sb.append("}");
				
				str =str.substring(str.indexOf("}")+1);
			}
			sb.append("]");
			System.out.println(sb.toString());
			count = cardService.findQuery(enterId).size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.out.print("{\"total\":" + count + ",\"rows\":" + sb.toString() + "}");
		super.out.flush();
		super.out.close();
		return "prize";
	}
	
	// 上传六格图片
	public String getuploadImage() {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if (cardImage != null) {
				String cardName=enterId+"card-prize"+cardType + ".jpg";
				try {
					card = cardService.findByCardImageEntity(cardName,enterId);
				} catch (Exception e) {
					card=null;
					e.printStackTrace();
					
				}
				if(card==null){
					card=new Card();
					card.setCardId(Integer.parseInt(cardType));
					card.setCardImage(enterId+"card-prize"+cardType + ".jpg");
					card.setCardName(enterId+"card-prize"+cardType + ".jpg");
					card.setCardType(Integer.parseInt(cardType));
					cardService.save(card,enterId);
					bean.upLoad(enterId+"card-prize"+cardType + ".jpg", cardImage,enterId+"");
					result = "<script>alert(\"成功添加了一等奖图片!\");</script>";
				}else{
					//bean.deletefile("images/card-prize" + (i - 1)+".jpg");
					card.setCardImage(enterId+"card-prize"+cardType + ".jpg");
					card.setCardName(enterId+"card-prize"+cardType + ".jpg");
					card.setCardType(Integer.parseInt(cardType));
					cardService.updateCard(card,enterId);
					bean.upLoad(enterId+"card-prize"+cardType + ".jpg", cardImage,enterId+"");
					result = "<script>alert(\"成功修改了一等奖图片!\");</script>";
				}
				
				
				
			} else {
				result = "<script>alert(\"修改失败!\");</script>";
				out.println(result);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			result = "<script>alert(\"系统正在维护 请稍后再试!\");</script>";
		}

		return "uploadImage";
	}
	//四格图片查询
	public String queryImage1() {

		ImagerollList1 = imageservice.getImageList1(enterId);
		
		 if(ImagerollList1==null||ImagerollList1.size()==0){
				out.print("");
			}else{
	          
				//list集合转换json格式
				JsonConfig jsonConfig=new JsonConfig(); 
				jsonConfig.setExcludes(new String[] { "user"});
				JSONArray jsonArrayFromList = JSONArray.fromObject(ImagerollList1,jsonConfig);
				StringBuffer sb = new StringBuffer();
				//循环遍历获取角色和部门
				String str =jsonArrayFromList.toString();
				String strpath =ServletActionContext.getServletContext().getRealPath("/");
		
			
				for(int i=0;i<ImagerollList1.size();i++){
					String temp = "";
				
				
					temp =str.substring(0,str.indexOf("}"));
				
					sb.append(temp);	
					if(ImagerollList1.get(i).getUploadImage()!=null){
						sb.append(",\"imageTemp\":\"<img  width='80' height='86' src='..\\\\..\\\\wxpt\\\\web\\\\images\\\\"+ImagerollList1.get(i).getUploadImage().replace("/", "\\\\\\\\")+"' ///>\"");
						
						}
					
					sb.append("}");
					
					str =str.substring(str.indexOf("}")+1);
				}
				sb.append("]");
				
				System.out.println(sb.toString());
			 out.print("{\"total\":"+0+",\"rows\":" + sb.toString() + "}");
			System.out.println("{\"total\":"+0+",\"rows\":" + sb.toString() + "}");
			
			out.flush();
			out.close();
			
		}

		return "prize1";
	}
	
	// 上传四格图片
		public String getuploadImage1() {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				image = imageservice.getImageList1(enterId).get(0);
				if (images1 != null) {
					int i = 2;

					bean.upLoad(i + ".jpg", images1);
					System.out.println(TimeUtil.getTime());
					image.setUploadTime(TimeUtil.getTime());
					image.setUploadImage(i + ".jpg");
					image.setTemplateCount(2);
					imageservice.updateImage(enterId,image);
					result = "<script>alert(\"修改成功!\");</script>";
					bean.deletefile("images/" + (i - 1));
					i++;
				} else {
					result = "<script>alert(\"修改失败!\");</script>";
					out.println(result);
				}
			} catch (Exception e) {
				// TODO: handle exception
				if (images1 != null) {
					image = new Imageroll();
					int i = 2;
					bean.upLoad(i + ".jpg", images1);
					System.out.println(TimeUtil.getTime());
					image.setUploadTime(TimeUtil.getTime());
					image.setUploadImage(i + ".jpg");

					image.setTemplateCount(2);;

					imageservice.addImage(enterId,image);
					result = "<script>alert(\"添加成功!\");</script>";
				} else {
					result = "<script>alert(\"添加失败!\");</script>";
					out.println(result);
				}
			}

			return "uploadImage";
		}
	
	
		
		//三格图片查询
		
		
		// 上传三格图片
			public String getuploadImage2() {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = null;
				try {
					out = response.getWriter();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					image = imageservice.getImageList2(enterId).get(0);
					if (images1 != null) {
						int i = 3;

						bean.upLoad(i + ".jpg", images1);
						System.out.println(TimeUtil.getTime());
						image.setUploadTime(TimeUtil.getTime());
						image.setUploadImage(i + ".jpg");
						image.setTemplateCount(3);
						imageservice.updateImage(enterId,image);
						result = "<script>alert(\"修改成功!\");</script>";
						bean.deletefile("images/" + (i - 1));
						i++;
					} else {
						result = "<script>alert(\"修改失败!\");</script>";
						out.println(result);
					}
				} catch (Exception e) {
					// TODO: handle exception
					if (images1 != null) {
						image = new Imageroll();
						int i = 3;
						bean.upLoad(i + ".jpg", images1);
						System.out.println(TimeUtil.getTime());
						image.setUploadTime(TimeUtil.getTime());
						image.setUploadImage(i + ".jpg");
						image.setTemplateCount(3);
						imageservice.addImage(enterId,image);
						result = "<script>alert(\"添加成功!\");</script>";
					} else {
						result = "<script>alert(\"添加失败!\");</script>";
						out.println(result);
					}
				}

				return "uploadImage";
			}
		

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Imageroll getImage() {
		return image;
	}

	public void setImage(Imageroll image) {
		this.image = image;
	}

	public ImageService getImageservice() {
		return imageservice;
	}

	public void setImageservice(ImageService imageservice) {
		this.imageservice = imageservice;
	}

	public File getImages1() {
		return images1;
	}

	public void setImages1(File images1) {
		this.images1 = images1;
	}

	public FileUploadBean getBean() {
		return bean;
	}

	public void setBean(FileUploadBean bean) {
		this.bean = bean;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public File getCardImage() {
		return cardImage;
	}

	public void setCardImage(File cardImage) {
		this.cardImage = cardImage;
	}

	public List<Card> getCardList() {
		return cardList;
	}

	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}

	public int getPrizeCountNum() {
		return prizeCountNum;
	}

	public void setPrizeCountNum(int prizeCountNum) {
		this.prizeCountNum = prizeCountNum;
	}

	public int getOnePrizeCount() {
		return onePrizeCount;
	}

	public void setOnePrizeCount(int onePrizeCount) {
		this.onePrizeCount = onePrizeCount;
	}

	public int getTwoPrizeCount() {
		return twoPrizeCount;
	}

	public void setTwoPrizeCount(int twoPrizeCount) {
		this.twoPrizeCount = twoPrizeCount;
	}

	public int getThreePrizeCount() {
		return threePrizeCount;
	}

	public void setThreePrizeCount(int threePrizeCount) {
		this.threePrizeCount = threePrizeCount;
	}

	public int getFourPrizeCount() {
		return fourPrizeCount;
	}

	public void setFourPrizeCount(int fourPrizeCount) {
		this.fourPrizeCount = fourPrizeCount;
	}

	public int getFivePrizeCount() {
		return fivePrizeCount;
	}

	public void setFivePrizeCount(int fivePrizeCount) {
		this.fivePrizeCount = fivePrizeCount;
	}

	public int getSixPrizeCount() {
		return sixPrizeCount;
	}

	public void setSixPrizeCount(int sixPrizeCount) {
		this.sixPrizeCount = sixPrizeCount;
	}

	public int getSevenPrizeCount() {
		return sevenPrizeCount;
	}

	public void setSevenPrizeCount(int sevenPrizeCount) {
		this.sevenPrizeCount = sevenPrizeCount;
	}

	public int getEightPrizeCount() {
		return eightPrizeCount;
	}

	public void setEightPrizeCount(int eightPrizeCount) {
		this.eightPrizeCount = eightPrizeCount;
	}

	public int getNinePrizeCount() {
		return ninePrizeCount;
	}

	public void setNinePrizeCount(int ninePrizeCount) {
		this.ninePrizeCount = ninePrizeCount;
	}

	public int getTenPrizeCount() {
		return tenPrizeCount;
	}

	public void setTenPrizeCount(int tenPrizeCount) {
		this.tenPrizeCount = tenPrizeCount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getMoveState() {
		return moveState;
	}

	public void setMoveState(int moveState) {
		this.moveState = moveState;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public List<CardPojo> getCardPojoList() {
		return cardPojoList;
	}

	public void setCardPojoList(List<CardPojo> cardPojoList) {
		this.cardPojoList = cardPojoList;
	}
	
	

}
