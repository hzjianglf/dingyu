// �斗�臬銝箸摮�
function is_numeric(str) {
    var patn = /^[0-9]+$/;
    if(!patn.test(str)) return false;
    return true;
}
//�內撖寡�獢�
function myAlert(data) {
    var mymsg = eval('('+data+')'); //JSON頧祆
    mymsg.message = mymsg.message.replace('ERROR:','');
    if(mymsg.extra == 'login') {
        window.location.href='member-mlogin.html';
    } else {
        alert(mymsg.message);
    }
}
//�單釣
function add_favorite(sid) {
    if (!is_numeric(sid)) {alert('���D'); return;}
    $.post('item-member-ac-favorite-op-add.html', 
    { sid:sid,in_ajax:1 },
    function(result) {
        if(result == null) {
            alert('靽⊥霂餃�憭梯揖嚗�賜�蝏�蝣�霂瑞���霂�');
        } else if (result.match(/\{\s+caption:".*",message:".*".*\s*\}/)) {
            myAlert(result);
        }
    });
}
var qjflag=1;
 function showpages(){
     if(qjflag==1){
         $("#m_pages_layer").show();
         qjflag=2;
     }else{
         $("#m_pages_layer").hide();
         qjflag=1;
     }
 }
       function morepages(){
         var purl=$("#purl").val();
         $("#morepagesid").hide();
         $("#waitemin").show();
         var page=$("#nowpage").val();
         page=parseInt(page)+1;
         $("#nowpage").val(page);
         purl=purl.replace('_PAGE_',page);
           //service_page_2
        $.post(purl,{'in_ajax':1},function(data){
         $('#ulContent').append(data);
            //��敺桀�撖寡情�啁�嚗蕭�ulContent銝�
            $("#morepagesid").show();
            $("#waitemin").hide();
            if(page>=parseInt($("#allpages").val())){
                 $("#morepagesid").hide();
             }
             window.myScroll.refresh();
        
        });
     }
         //�喲
       function myclose(){
              $("#calldiv").hide();
               $("#callbg").hide();
        }
        //�孵�菔�
      function uhuacall(sid,phone){
        $("#uhcontent").html("<span>甇��蝸...</span>"); 
        $("#calldiv").show();
        $("#callbg").show();
        $.post('index.php?m=item&act=ajax&do=subject&op=showdiv', {'sid':sid,'in_ajax':1,'mphone':phone}, function(data) {
             $("#uhcontent").html(data);
          });
         
        }
        //�澆����
     function usaycall(sid,phone,ct){
         if(check_mobile($("#sphone").val())==false){
             return;
         }
         var par='';
          $.post('index.php?m=item&act=ajax&do=subject&op=uhuacall',{'in_ajax':1,'sphone':$("#sphone").val(),'calltype':ct,'sid':sid,'phone':phone},function(data){    
            par=data.split('||');
           if(par[0]=='no'){
               alert(par[1]);
               return;
           }
              //�       
               if(ct==2){
                    $("#uhcontent").html('');
                    $("#calldiv").hide();
                    $("#callbg").hide();
                    return;
                } 
                //�
              if(par[0]=='yes'){    
                   $("#umaincon").hide();
                   $("#ucallnote").html("<div class='ct'>甇�蝑��振摨�嚗窈蝔�...</div><div class='active usaycall' onclick=usaycall('"+sid+"','"+par[1]+"','2') >��祆活��</div><div class='adcall'>�晶���窈雿輻����</div>");
                 }else{
                      $("#umaincon").hide();
                      $("#ucallnote").html("�典憭梯揖嚗�3蝘�餈�");
                      setTimeout(function(){
                           $("#umaincon").show();
                           $("#ucallnote").html("");
                      },3000);
                 }
        });
     }
    function check_mobile(phone){
            if(phone==''){
                alert("霂瑁��亦��祆�瑞�嚗�");
                return false;
            }
            var partten='';
            if(phone.substring(0,1)=='1'){
                partten = /^1[3|4|5|6|7|8|9][0-9]{9}$/;
                if(!partten.test(phone)){
                   alert('�刻��亦���瑞��秤嚗�');
                   return false;
                }
            }else{
                partten =/^[0]\d{2,3}\d{7,8}$/;
                if(!partten.test(phone)){
                   alert('�刻��亦��菔��瑞��秤,�砍摨扳霂瑕��箏');
                   return false;
                }
            }

            if(phone.substring(0,2)=='00'){
                 alert("�望�嚗���舀��賡��輸�");
                 return false;
            }
           
     }
     $(function(){   
        $("#callbg").width($(document).width());   
        $('#callbg').height($(document).height());   
        $('#callbg').css('left',0);      
        $('#callbg').css('top',0);   
}); 