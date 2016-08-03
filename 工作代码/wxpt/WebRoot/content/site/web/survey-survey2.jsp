<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html><html><head>



  <title>微聚家调研问卷</title>
  <meta name="description" content="
 微聚家调研问卷微聚家调研问卷微聚家调研问卷微聚家调研问卷">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="apple-touch-icon-precomposed" href="../../web/images/9718077.png">
  
  <link rel="stylesheet" href="http://code.jquery.com/mobile/latest/jquery.mobile.min.css" />  
<!-- <link rel="stylesheet" href="../../web/css/jquery.mobile.1.3.2.min.css" /> 
<link rel="stylesheet" href="../../web/css/jquery.mobile.min.css" />   -->
<script src="../../web/js/jquery-1.7.1.min.js"></script>  
<script src="../../web/js/jquery.mobile.min.js"></script>   

  <style type="text/css">
    [data-role=header] {
        background: #59a326;
        border-color: #59a326;
    }
    .header-image[data-role=header] {
      
    }
</style>
  <meta content="authenticity_token" name="csrf-param">
<meta content="rLAe+P2HmvYbjf272ZkrSYUWlkVBCfFtrVc9JbfIieU=" name="csrf-token">
<link></head>
<body ontouchstart="">
<div data-role="page" class="page">
  <header data-role="header" class="">
      <h1>
        <i class="fontello-photo"></i>
       微聚家调研问卷
      </h1>
    
  </header>
  <div class="main" data-role="content">
    <div class="main-content">
      <form accept-charset="UTF-8" action="survey!save" class="center" id="new_entry" method="post">
      <div style="margin:0;padding:0;display:inline">
      <input name="utf8" type="hidden" value="✓">
      <input name="authenticity_token" type="hidden" value="rLAe+P2HmvYbjf272ZkrSYUWlkVBCfFtrVc9JbfIieU=">
      </div>
  
  <h1 class="form-name">微聚家调研问卷</h1>
  <div class="form-description">
  <p>微聚家调研问卷微聚家调研问卷微聚家调研问卷微聚家调研问卷微聚家调研问卷微聚家调研问卷微聚家调研问卷微聚家调研问卷微聚家调研问卷微聚家调研问卷微聚家调研问卷微聚家调研问卷微聚家调研问卷微聚家调研问卷微聚家调研问卷微聚家调研问卷微聚家调研问卷微聚家调研问卷微聚家调研问卷微聚家调研问卷微聚家调研问卷微聚家调研问卷。
  </p>
  </div>
  <fieldset>
    <div class="form-content">
        


          <div class="field    " data-api-code="field_1">
            <div class="control-group ">
  <label class="control-label field_title" data-role="collapse_toggle" for="entry_field_1">
    姓名
    
    <span style="color: red;">*</span>
    
</label>
  <div class="field_content">

    <div class="controls">
      <input id="entry_field_1" name="entry[field_1]" size="30" type="text" value="">
    </div>

  </div>
</div>

          </div>
          <div class="field    " data-api-code="field_2">
            <div class="control-group ">
  <label class="control-label field_title" data-role="collapse_toggle" for="entry_field_2">
    手机号
    
    <span style="color: red;">*</span>
    
</label>
  <div class="field_content">

    <div class="controls">
      <input id="entry_field_2" name="entry[field_2]" size="30" type="text" value="">
    </div>

  </div>
</div>

          </div>
          <div class="field    " data-api-code="field_3">
            <div class="control-group ">
  <label class="control-label field_title" data-role="collapse_toggle" for="entry_field_3">
    QQ号码
    
    
    
</label>
  <div class="field_content">

    <div class="controls">
      <input id="entry_field_3" name="entry[field_3]" size="30" type="text" value="">
    </div>

  </div>
</div>

          </div>
          <div class="field    " data-api-code="field_4">
            <div class="control-group ">
  <label class="control-label field_title" data-role="collapse_toggle" for="entry_field_4">
    邮箱
    
    
    
</label>
  <div class="field_content">

    <div class="controls">
      <input id="entry_field_4" name="entry[field_4]" size="30" type="text" value="">
    </div>

  </div>
</div>

          </div>
          <div class="field    " data-api-code="field_5">
            <div class="control-group ">
  <label class="control-label field_title" data-role="collapse_toggle" for="entry_field_5">
    性别
    
    <span style="color: red;">*</span>
    
</label>
  <div class="field_content">

    <div class="controls">
      <div class="clearfix radio-group" data-role="controlgroup">
        <label onClick="" class="radio ">
        <input name="entry[field_5]" type="radio" value="男">
        男
      </label>
      <label onClick="" class="radio ">
        <input name="entry[field_5]" type="radio" value="女">
        女
      </label>
      <label onClick="" class="radio ">
        <input name="entry[field_5]" type="radio" value="不想透露">
        不想透露
      </label>

</div>

    </div>

  </div>
</div>

          </div>
          <div class="field    " data-api-code="field_6">
            <div class="control-group ">
  <label class="control-label field_title" data-role="collapse_toggle" for="entry_field_6">
    年龄
    
    <span style="color: red;">*</span>
    
</label>
  <div class="field_content">

    <div class="controls">
      <div class="clearfix radio-group" data-role="controlgroup">
        <label onClick="" class="radio ">
        <input name="entry[field_6]" type="radio" value="18岁以下">
        18岁以下
      </label>
      <label onClick="" class="radio ">
        <input name="entry[field_6]" type="radio" value="18-25岁">
        18-25岁
      </label>
      <label onClick="" class="radio ">
        <input name="entry[field_6]" type="radio" value="26-35岁">
        26-35岁
      </label>
      <label onClick="" class="radio ">
        <input name="entry[field_6]" type="radio" value="36-45岁">
        36-45岁
      </label>
      <label onClick="" class="radio ">
        <input name="entry[field_6]" type="radio" value="45岁以上">
        45岁以上
      </label>

</div>

    </div>

  </div>
</div>

          </div>
          <div class="field    " data-api-code="field_7">
            <div class="control-group ">
  <label class="control-label field_title" data-role="collapse_toggle" for="entry_field_7">
    学历
    
    <span style="color: red;">*</span>
    
</label>
  <div class="field_content">

    <div class="controls">
      <div class="clearfix radio-group" data-role="controlgroup">
        <label onClick="" class="radio ">
        <input name="entry[field_7]" type="radio" value="初中">
        初中
      </label>
      <label onClick="" class="radio ">
        <input name="entry[field_7]" type="radio" value="高中">
        高中
      </label>
      <label onClick="" class="radio ">
        <input name="entry[field_7]" type="radio" value="大学本科">
        大学本科
      </label>
      <label onClick="" class="radio ">
        <input name="entry[field_7]" type="radio" value="硕士研究生">
        硕士研究生
      </label>
      <label onClick="" class="radio ">
        <input name="entry[field_7]" type="radio" value="博士研究生">
        博士研究生
      </label>

</div>

    </div>

  </div>
</div>

          </div>
          <div class="field    " data-api-code="field_8">
            <div class="control-group ">
  <label class="control-label field_title" data-role="collapse_toggle" for="entry_field_8">
    职业
    
    <span style="color: red;">*</span>
    
</label>
  <div class="field_content">

    <div class="controls">
      <div class="clearfix radio-group" data-role="controlgroup">
        <label onClick="" class="radio ">
        <input name="entry[field_8]" type="radio" value="全职">
        全职
      </label>
      <label onClick="" class="radio ">
        <input name="entry[field_8]" type="radio" value="兼职">
        兼职
      </label>
      <label onClick="" class="radio ">
        <input name="entry[field_8]" type="radio" value="自由职业">
        自由职业
      </label>
      <label onClick="" class="radio ">
        <input name="entry[field_8]" type="radio" value="找工作中">
        找工作中
      </label>
      <label onClick="" class="radio ">
        <input name="entry[field_8]" type="radio" value="无业">
        无业
      </label>
      <label onClick="" class="radio ">
        <input name="entry[field_8]" type="radio" value="在校学生">
        在校学生
      </label>
      <label onClick="" class="radio ">
        <input name="entry[field_8]" type="radio" value="不想说">
        不想说
      </label>

</div>

    </div>
    
    

  </div>
</div>

          </div>
          <!-- ------------------------------------------------------------- -->
          <s:iterator value="questionList" var="que">
          <div class="field    " data-api-code="field_<s:property value="#que.surquestion.surquestionId"/>">
            <div class="control-group ">
  <label class="control-label field_title" data-role="collapse_toggle" for="entry_field_9">
    <s:property value="#que.surquestion.surquestionContent"/>
    
    <span style="color: red;">*</span>
    
</label>
  <div class="field_content">

    <div class="controls">
      <div class="clearfix radio-group" data-role="controlgroup">
        <label onClick="" class="checkbox ">
        <input name="entry[field_<s:property value="#que.surquestion.surquestionId"/>]" type="radio" value="齐鲁晚报报刊1">
        齐鲁晚报报刊
      </label>
      <div class="other-choice-area ">
        <label onClick="" class="checkbox ">
          <input class="other_choice" data-field-key="field_9" name="entry[field_<s:property value="#que.surquestion.surquestionId"/>]" type="radio" value="其它1">
          其它
        </label>
        <input class="other-choice-input input-medium" data-field-key="field_<s:property value="#que.surquestion.surquestionId"/>" id="entry_field_9_other" name="entry[field_9_other]" size="30" type="text" value="">
      </div>

</div>

    </div>
    

  </div>
</div>

          </div>
       </s:iterator>   
    
          
          
          

            <div class="field submit-field ">
              
              <div class="value">
                <input id="embedded" name="embedded" type="hidden">
                <input class="submit" data-disabled-with="提交中..." name="commit" type="submit" value="提交">
              </div>
            </div>
    </div>
  </fieldset>

</form>
</div>
    <div class="footer">
  <div class="guides"></div>
  <!-- <footer class="">
    
  </footer> -->
</div>

  </div>
</div>
    


</body></html>