package com.wxpt.action.site;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.wxpt.common.FileUploadBean;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.dao.impl.ImageDaoImpl;
import com.wxpt.site.entity.Imageroll;
import com.wxpt.site.service.ImageService;

@SuppressWarnings("serial")
@Results({
		@Result(name = "success", type = "json", params = { "root", "result" }),
		@Result(name = "successs", location = "success.jsp") })
@ParentPackage("json-default")
public class ManagePrizeAction extends ParentAction {

	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	Imageroll image;
	ImageService imageservice;
	private File images1;
	String result;
	public int imageId;
	ImageDaoImpl imageDao = new ImageDaoImpl();
	JSONArray jsonls;
	GetCookie cookies = new GetCookie();
	int enterId = cookies.getCookie();
	private String message;

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

	// 六格图片查询
	public String queryImage() {
		ImagerollList = imageservice.getImageList(enterId);
		if (ImagerollList == null || ImagerollList.size() == 0) {
			/*out.print("");*/
			super.out.print("{\"total\":" + 0 + ",\"rows\":" + ""
					+ "}");
		} else {

			// list集合转换json格式
			JsonConfig jsonConfig = new JsonConfig();
			/* jsonConfig.setExcludes(new String[] { "user"}); */
			JSONArray jsonArrayFromList = JSONArray.fromObject(ImagerollList,
					jsonConfig);
			StringBuffer sb = new StringBuffer();
			// 循环遍历获取角色和部门
			String str = jsonArrayFromList.toString();
			String strpath = ServletActionContext.getServletContext()
					.getRealPath("/");
			for (int i = 0; i < ImagerollList.size(); i++) {
				String temp = "";

				temp = str.substring(0, str.indexOf("}"));

				sb.append(temp);
				if (ImagerollList.get(i).getUploadImage() != null) {
					sb.append(",\"imageTemp\":\"<img  width='80' height='86' src='..\\\\..\\\\wxpt\\\\web\\\\images\\\\"+enterId+"\\\\"
							+ ImagerollList.get(i).getUploadImage()
									.replace("/", "\\\\\\\\") + "' ///>\"");

				}

				sb.append("}");

				str = str.substring(str.indexOf("}") + 1);
			}
			sb.append("]");

			System.out.println(sb.toString());
			super.out.print("{\"total\":" + 0 + ",\"rows\":" + sb.toString()
					+ "}");

		}
		super.out.flush();
		super.out.close();
		return SUCCESS;
	}

	// 上传六格图片
	public String getuploadImage() {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			image = imageservice.getImageList(enterId).get(0);
			if (images1 != null) {
				int i = 1;
				String s = TimeUtil.getTime().replaceAll("/", "")
						.replaceAll(" ", "").replaceAll(":", "");
				bean.deletefile(enterId+"/" + image.getUploadImage());
				bean.upLoad(s + ".jpg", images1,enterId+"");
				System.out.println(TimeUtil.getTime());
				image.setUploadTime(TimeUtil.getTime());
				image.setUploadImage(s + ".jpg");
				image.setTemplateCount(1);
				System.out.println(image.getImageId());
				/* imageservice.updateImage(enterId,image); */
				imageDao.updateImage(enterId, image);
				message = "修改成功";
				/* result = "success"; */
				bean.deletefile(enterId+"/" + (i - 1));
				i++;
			} else {
				result = "<script>alert(\"修改失败!\");</script>";
				out.println(result);
			}
		} catch (Exception e) {
			// TODO: handle exception
			if (images1 != null) {
				image = new Imageroll();
				int i = 1;
				String s = TimeUtil.getTime().replaceAll("/", "")
						.replaceAll(" ", "").replaceAll(":", "");
				bean.upLoad(s + ".jpg", images1,enterId+"");
				System.out.println(TimeUtil.getTime());
				image.setUploadTime(TimeUtil.getTime());
				image.setUploadImage(s + ".jpg");
				image.setTemplateCount(1);
				imageservice.addImage(enterId, image);
				message = "添加成功！";
				// result = "<script>alert(\"添加成功!\");</script>";
			} else {
				message = "添加失败！";
				// result = "<script>alert(\"添加失败!\");</script>";
				out.println(result);
			}
		}

		return "successs";
	}

	// 四格图片查询
	public String queryImage1() {

		ImagerollList1 = imageservice.getImageList1(enterId);

		if (ImagerollList1 == null || ImagerollList1.size() == 0) {
			/*out.print("");*/
			super.out.print("{\"total\":" + 0 + ",\"rows\":" + ""
					+ "}");
		} else {

			// list集合转换json格式
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "user" });
			JSONArray jsonArrayFromList = JSONArray.fromObject(ImagerollList1,
					jsonConfig);
			StringBuffer sb = new StringBuffer();
			// 循环遍历获取角色和部门
			String str = jsonArrayFromList.toString();
			String strpath = ServletActionContext.getServletContext()
					.getRealPath("/");
			for (int i = 0; i < ImagerollList1.size(); i++) {
				String temp = "";
				temp = str.substring(0, str.indexOf("}"));
				sb.append(temp);
				if (ImagerollList1.get(i).getUploadImage() != null) {
					sb.append(",\"imageTemp\":\"<img  width='80' height='86' src='..\\\\..\\\\wxpt\\\\web\\\\images\\\\"+enterId+"\\\\"
							+ ImagerollList1.get(i).getUploadImage()
									.replace("/", "\\\\\\\\") + "' ///>\"");

				}

				sb.append("}");

				str = str.substring(str.indexOf("}") + 1);
			}
			sb.append("]");

			System.out.println(sb.toString());
			out.print("{\"total\":" + 0 + ",\"rows\":" + sb.toString() + "}");
			System.out.println("{\"total\":" + 0 + ",\"rows\":" + sb.toString()
					+ "}");

		}
		out.flush();
		out.close();
		return SUCCESS;
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
				String s = TimeUtil.getTime().replaceAll("/", "")
						.replaceAll(" ", "").replaceAll(":", "");
				bean.deletefile(enterId+"/" + image.getUploadImage());
				bean.upLoad(s + ".jpg", images1,enterId+"");
				System.out.println(s);
				image.setUploadTime(TimeUtil.getTime());
				image.setUploadImage(s + ".jpg");
				image.setTemplateCount(2);
				System.out.println(image.getImageId() + "::::::::::::");
				imageDao.updateImage(enterId, image);
				/* imageservice.updateImage(enterId,image); */
				message = "修改成功";
				// result = "<script>alert(\"修改成功!\");</script>";
				/* bean.deletefile("images/" + (i - 1)); */
				i++;
			} else {
				message = "修改失败！";
				// result = "<script>alert(\"修改失败!\");</script>";
				out.println(result);
			}
		} catch (Exception e) {
			// TODO: handle exception
			if (images1 != null) {
				image = new Imageroll();
				int i = 2;
				String s = TimeUtil.getTime().replaceAll("/", "")
						.replaceAll(" ", "").replaceAll(":", "");
				bean.upLoad(s + ".jpg", images1,enterId+"");
				System.out.println(TimeUtil.getTime());
				image.setUploadTime(TimeUtil.getTime());
				image.setUploadImage(s + ".jpg");

				image.setTemplateCount(2);
				;

				imageservice.addImage(enterId, image);
				message = "添加成功！";
				// result = "<script>alert(\"添加成功!\");</script>";
			} else {
				message = "添加失败！";
				// result = "<script>alert(\"添加失败!\");</script>";
				out.println(result);
			}
		}

		return "successs";
	}

	// 三格图片查询
	public String queryImage2() {

		ImagerollList2 = imageservice.getImageList2(enterId);

		if (ImagerollList2 == null || ImagerollList2.size() == 0) {
			/*out.print("");*/
			super.out.print("{\"total\":" + 0 + ",\"rows\":" + ""
					+ "}");
		} else {

			// list集合转换json格式
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "user" });
			JSONArray jsonArrayFromList = JSONArray.fromObject(ImagerollList2,
					jsonConfig);
			StringBuffer sb = new StringBuffer();
			// 循环遍历获取角色和部门
			String str = jsonArrayFromList.toString();
			String strpath = ServletActionContext.getServletContext()
					.getRealPath("/");

			for (int i = 0; i < ImagerollList2.size(); i++) {
				String temp = "";

				temp = str.substring(0, str.indexOf("}"));

				sb.append(temp);
				if (ImagerollList2.get(i).getUploadImage() != null) {
					sb.append(",\"imageTemp\":\"<img  width='80' height='86' src='..\\\\..\\\\wxpt\\\\web\\\\images\\\\"+enterId+"\\\\"
							+ ImagerollList2.get(i).getUploadImage()
									.replace("/", "\\\\\\\\") + "' ///>\"");

				}

				sb.append("}");

				str = str.substring(str.indexOf("}") + 1);
			}
			sb.append("]");

			System.out.println(sb.toString());
			out.print("{\"total\":" + 0 + ",\"rows\":" + sb.toString() + "}");
			System.out.println("{\"total\":" + 0 + ",\"rows\":" + sb.toString()
					+ "}");

		}
		out.flush();
		out.close();
		return SUCCESS;
	}

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
				String s = TimeUtil.getTime().replaceAll("/", "")
						.replaceAll(" ", "").replaceAll(":", "");
				bean.deletefile(enterId+"/" + image.getUploadImage());
				bean.upLoad(s + ".jpg", images1,enterId+"");
				System.out.println(TimeUtil.getTime());
				image.setUploadTime(TimeUtil.getTime());
				image.setUploadImage(s + ".jpg");
				image.setTemplateCount(3);
				imageDao.updateImage(enterId, image);
				/* imageservice.updateImage(enterId,image); */
				message = "修改成功！！";
				/* result = "<script>alert(\"修改成功!\");</script>"; */
				bean.deletefile(enterId+"/" + (i - 1));
				i++;
			} else {
				message = "修改失败！！";
				/* result = "<script>alert(\"修改失败!\");</script>"; */
				out.println(result);
			}
		} catch (Exception e) {
			// TODO: handle exception
			if (images1 != null) {
				image = new Imageroll();
				int i = 3;
				String s = TimeUtil.getTime().replaceAll("/", "")
						.replaceAll(" ", "").replaceAll(":", "");
				bean.upLoad(s + ".jpg", images1,enterId+"");
				System.out.println(TimeUtil.getTime());
				image.setUploadTime(TimeUtil.getTime());
				image.setUploadImage(s + ".jpg");
				image.setTemplateCount(3);
				imageservice.addImage(enterId, image);
				message = "添加成功！";
				// result = "<script>alert(\"添加成功!\");</script>";
			} else {
				message = "添加失败！";
				// result = "<script>alert(\"添加失败!\");</script>";
				out.println(result);
			}
		}

		return "successs";
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
