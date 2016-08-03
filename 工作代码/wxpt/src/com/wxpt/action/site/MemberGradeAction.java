package com.wxpt.action.site;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.FileUploadBean;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.MamberGrade;
import com.wxpt.site.service.MemberService;
@Results({
	@Result(name="success" ,location="/WEB-INF/content/site/successs.jsp")
	
})
public class MemberGradeAction extends ParentAction {
	int count;
	int page=1;
	int pageSize=5;
	int gradeId;
	int state=1;
	String gradeJf;
	String gradeName;
	File gradeImage;
	static String fileName;
	@Autowired
	MemberService memberService;
	JSONArray jsonArrayFromList;
	MamberGrade mamberGrade;
	List<MamberGrade> gradeList = new ArrayList<MamberGrade>();
	List<MamberGrade>mamberList=new ArrayList<MamberGrade>();
	TimeUtil time= new TimeUtil();
	FileUploadBean bean=new FileUploadBean();
	public void getGrade() {
		String sql = "SELECT * FROM wxpt" + super.getCookieByEnterID() + ".mamber_grade";
		try {
			gradeList = memberService.getAll(sql,page,pageSize);
			if(gradeList.size()!=0){
			for(int i=0;i<gradeList.size();i++){
				MamberGrade grade=new MamberGrade();
				grade.setGradeImage("<img  width='80' height='86' src='..\\\\..\\web\\images\\"+super.getCookieByEnterID()+"\\"+gradeList.get(i).getGradeImage()+"' ///>\"");
				grade.setGradeJifen(gradeList.get(i).getGradeJifen());
				grade.setMemberGradeId(gradeList.get(i).getMemberGradeId());
				grade.setMemberGradeName(gradeList.get(i).getMemberGradeName());
				mamberList.add(grade);
			}
			count = gradeList.size();
			}else{
				count=0;
			}
			
			JsonConfig jsonConfig = new JsonConfig();
			jsonArrayFromList = JSONArray.fromObject(mamberList);
			super.out.print("{\"total\":" + count + ",\"rows\":"
					+ jsonArrayFromList + "}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.out.flush();
		super.out.close();
	}
	
	//添加
	public void addGrade(){
		String sql="INSERT INTO wxpt"+super.getCookieByEnterID()+".mamber_grade( `member_grade_name`, `grade_jifen`, `grade_image`) " +
				"VALUES ('"+gradeName+"',"+gradeJf+",'"+fileName+"')";
		try {
			memberService.addGrade(sql);
			fileName="";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//修改
	public void updateGrade(){
		System.out.println(fileName);
		String sql;
		if(fileName==""||fileName.equals("")){
			 sql="UPDATE wxpt"+super.getCookieByEnterID()+".mamber_grade SET `member_grade_name`='"+gradeName+"',`grade_jifen`="+gradeJf+" WHERE `member_grade_id`="+gradeId;
		}else{
			String sql0="SELECT * FROM wxpt"+super.getCookieByEnterID()+".mamber_grade WHERE `member_grade_id`="+gradeId;
			mamberGrade=memberService.getById(sql0);
			String image=mamberGrade.getGradeImage();
			bean.deletefile(super.getCookieByEnterID()+"/"+image);
			 sql="UPDATE wxpt"+super.getCookieByEnterID()+".mamber_grade SET `member_grade_name`='"+gradeName+"',`grade_jifen`="+gradeJf+",`grade_image`='"+fileName+"' WHERE `member_grade_id`="+gradeId;
		}
		
		try {
			memberService.updateGrade(sql,super.getCookieByEnterID());
			fileName="";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//删除
	public void deleteGrade(){
		String sql="DELETE FROM wxpt"+super.getCookieByEnterID()+".mamber_grade WHERE `member_grade_id`="+gradeId;
		try {
			memberService.deleteGrade(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//上传图片
	public String  uploadeImage(){
		System.out.println(gradeImage);
		if(gradeImage!=null){
			String name=time.getPayTime();
			fileName=name+".jpg";
			bean.upLoad(fileName, gradeImage,super.getCookieByEnterID()+"");
		}else{
			state=0;
		}
		return "success";
	}
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getGradeJf() {
		return gradeJf;
	}

	public void setGradeJf(String gradeJf) {
		this.gradeJf = gradeJf;
	}

	public File getGradeImage() {
		return gradeImage;
	}

	public void setGradeImage(File gradeImage) {
		this.gradeImage = gradeImage;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public static String getFileName() {
		return fileName;
	}

	public static void setFileName(String fileName) {
		MemberGradeAction.fileName = fileName;
	}

}
