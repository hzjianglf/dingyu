<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	    
	       
	        <table id="liuyanban" class="liuyanban"  cellpadding="0" cellspacing="0" style="*position:relative;*left:4%;*visibility:visible;">
	          <tr>
	            <td align="center"  style="width:20%;background:#E5F0F4;">商家</td>
	            <td align="center"  style="width:30%;background:#E5F0F4;">留言标题</td>
	            <td align="center"  style="width:40%;background:#E5F0F4;">留言内容</td>
	            <td align="center"  style="width:10%;background:#E5F0F4;">操作</td>
	          </tr>
	          
	          <s:iterator value="listmessage2" var="lms">
	          
	         <tr>
	            <td ><s:property value="#lms.member.cardId"/></td>
	            <td ><s:property value="#lms.messageTitle"/></td>
	            <td ><s:property value="#lms.messageContent"/></td>
	           <td align="center" ><input type="button" id="huifu" name="huifu" value="回复" onclick="huifu('<s:property value="#lms.messageId"/>')"/></td>
	        </tr>
	        
	         <s:iterator value="#lms.listhui" var="lmshui">
	         <tr>
	            <td ><s:property value="#lmshui.member.cardId"/></td>
	            <td ><s:property value="#lmshui.messageTitle"/></td>
	            <td ><s:property value="#lmshui.messageContent"/></td>
	            <td align="center" >商家已回复</td>
	        </tr>
	        </s:iterator>
	        
	           <tr id="<s:property value='#lms.messageId'/>"   style="display:none;" >
		           <td colspan="3"><input type="text" id="<s:property value="#lms.messageId"/>tt" name="<s:property value="#lms.messageId"/>" style="width:600px;"/></td>
		           <td align="center"><input type="button" class="spb" id="tijiao" name="tijiao" value="提交" onclick="tijiao('<s:property value="#lms.messageId"/>tt','<s:property value="#lms.messageId"/>')" /><input class="sp2" type="button" id="quxiao" name="quxiao" value="取消" onclick="quxiao('<s:property value="#lms.messageId"/>')"/></td>
	           </tr>

	        </s:iterator>
	       </table>
	       
	<center>
	共${pb.pageCnt}页，当前是${pb.curPage}页 <a
		href="javascript:testfirstPage()">首页</a> <a
		href="javascript:selectPre()">上一页</a><input type="hidden" id="sss" name="sss" value="${memberId}">
		
		 <a href="javascript:selectnext()">下一页</a> <a
		href="javascript:selectlast()">尾页</a>
</center>
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
	       
	       
	       
	       
	       
	       
	       
	       
	       