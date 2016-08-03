/**
*本地存储类
*@author zhanghequn
*/

/**
*封装临时存储用于html之间传递参数
*/
 var TempCache = {  
     cache:function(value){  
         sessionStorage.setItem("EasyWayTempCache",value);  
     },  
     getCache:function(){  
         return sessionStorage.getItem("EasyWayTempCache");  
     },  
     setItem:function(key,value){ 
     	 //防止iPhone/iPad上有时设置setItem()时会出现诡异的QUOTA_EXCEEDED_ERR错误，固先remove
     	 sessionStorage.removeItem(key); 
         sessionStorage.setItem(key,value);  
     },  
     getItem:function(key){  
         return sessionStorage.getItem(key);  
     },  
     removeItem:function(key){  
         return sessionStorage.removeItem(key);  
     },
     clear:function(){
     	 return sessionStorage.clear(); 
     }
 };  

/**
*持久化存储用于存储部分数据
*/
 var PermanentCache = {  
     cache:function(value){  
         localStorage.setItem("EasyWayPermanentCache",value);  
     },  
     getCache:function(){  
         return localStorage.getItem("EasyWayPermanentCache");  
     },  
     setItem:function(key,value){ 
         localStorage.setItem(key,value);  
     },  
     getItem:function(key){  
         return localStorage.getItem(key);  
     },  
     removeItem:function(key){  
         return localStorage.removeItem(key);  
     },
     clear:function(){
     	 return localStorage.clear(); 
     }
 };  