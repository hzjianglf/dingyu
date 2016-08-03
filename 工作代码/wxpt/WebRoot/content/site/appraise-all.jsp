<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	    
	       
	        <table id="liuyanban" class="liuyanban"  cellpadding="0" cellspacing="0" style="*position:relative;*left:4%;*visibility:visible;">
	          <tr>
	            <td align="center"  style="width:20%;background:#E5F0F4;">会员</td>
	            <td align="center"  style="width:30%;background:#E5F0F4;">商品名称</td>
	            <td align="center"  style="width:40%;background:#E5F0F4;">评论内容</td>
	            <td align="center"  style="width:40%;background:#E5F0F4;">时间</td>
	            <td align="center"  style="width:10%;background:#E5F0F4;">操作</td>
	          </tr>
	          
	          <s:iterator value="app" var="lms">
	          
	         <tr>
	            <td ><s:property value="#lms.member.username"/></td>
	            <td ><s:property value="#lms.product.productName"/></td>
	            <td ><s:property value="#lms.appraiseContent"/></td>
	             <td ><s:property value="#lms.appraiseTime"/></td>
	           <td align="center" ><input type="button" id="huifu" name="huifu" value="回复" onclick="huifu('<s:property value="#lms.appraiseId"/>')"/></td>
	        </tr>
	        <s:if test="#lms.appraiseHuifu!=null">
	         <s:iterator value="app" var="lmshui">
	         <tr>
	           <td ><s:property value="#lmshui.member.username"/></td>
	            <td ><s:property value="#lmshui.product.productName"/></td>
	            <td ><s:property value="#lmshui.appraiseHuifu"/></td>
	             <td ><s:property value="#lmshui.appraiseHuifuTime"/></td>
	            <td align="center" >商家已回复</td>
	        </tr>
	        </s:iterator>
	         </s:if>
	           <tr id="<s:property value='#lms.appraiseId'/>"   style="display:none;" >
		           <td colspan="4">
		           <textarea id="<s:property value="#lms.appraiseId"/>tt" name="<s:property value="#lms.appraiseId"/>"  rows="5" cols="100">
		           
		           </textarea>
		           <%-- <input type="text" id="<s:property value="#lms.appraiseId"/>tt" name="<s:property value="#lms.appraiseId"/>" style="width:650px; height: 100px;"/> --%>
		           
		           </td>
		           <td align="center"><input type="button" class="spb" id="tijiao" name="tijiao" value="提交" onclick="tijiao('<s:property value="#lms.appraiseId"/>tt','<s:property value="#lms.appraiseId"/>')" /><input class="sp2" type="button" id="quxiao" name="quxiao" value="取消" onclick="quxiao('<s:property value="#lms.appraiseId"/>')"/></td>
	           </tr>

	        </s:iterator>
	       </table>
	       
	<%-- <center>
	共${pb.pageCnt}页，当前是${pb.curPage}页 <a
		href="javascript:testfirstPage()">首页</a> <a
		href="javascript:selectPre()">上一页</a><input type="hidden" id="sss" name="sss" value="${memberId}">
		
		 <a href="javascript:selectnext()">下一页</a> <a
		href="javascript:selectlast()">尾页</a>
</center> --%>
<script>
	function testfirstPage() {

		henji(1);

	}
	function selectPre() {

		henji('${pb.prePage}');
	}
	function selectnext() {

		henji('${pb.nextPage}');
	}

	function selectlast() {

		henji('${pb.lastPage}');

	}
	
	
	
	  function henji(currentpage)
  {
	
	var memberId= $('#sss').val();
	  $.ajax({
		  type:'post',
		  url:'../site/member!quliuyan',
		  data:{
			  'currentpage':currentpage,
			  
			'memberId':memberId
		  },
		  success:function(text)
		  {
			  $("#liuyanban").html(text);
		  },
		  
		  
	  });
	  

  }
</script>       
	       
	       
	       
	       
	       
	       
	       
	       
	       