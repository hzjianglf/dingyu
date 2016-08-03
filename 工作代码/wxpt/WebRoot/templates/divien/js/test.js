// JavaScript Document
var images = new Array();  
var cIndex = 0;  
var speed = 5;  
var context;  
var canvas;  
var currentImage;  
var width=300;  
var height=300;  
var stopX = 95;  
var stopY = 0;  
var autoTimeout;  
var manuTimeout;  
var interval;  
var img1;  
var img2;  
var img3;  
var img4;  
var timeoutInterval = 5;  
  
function slideImage(id,x,y,imgObj){  
    this.speed = speed;  
    this.preImage = null;  
    this.nextImage = null;  
    this.imgObj=imgObj;  
    this.x=x;  
    this.y=y;  
    this.direction="right";  
    this.id = id;  
}  
  
function buttonNext(x,y,bwidth,bheight){  
    this.x = x;  
    this.y = y;  
    this.width = bwidth;  
    this.height = bheight;  
}  
  
$(document).ready(  
    function(){  
        InitImages();  
        canvas = document.getElementById("imgs");  
        context = canvas.getContext("2d");  
        //移动图片  
        context.drawImage(currentImage.imgObj,currentImage.x,currentImage.y,width,height);  
        context.drawImage(currentImage.preImage.imgObj,currentImage.preImage.x,currentImage.preImage.y,width,height);  
        context.drawImage(currentImage.nextImage.imgObj,currentImage.nextImage.x,currentImage.nextImage.y,width,height);  
        context.fillStyle="rgba(100,150,185,0.5)";  
        context.fillRect(0,0,100,height);  
        context.fillRect(400,0,100,height);  
        interval = setTimeout("intervalSlide()", 20);  
    }  
);  
  
function drawFrame(){  
    context.clearRect(0,0,canvas.width,canvas.height);  
    //调用beginPath()确保不会接着上次绘制的图形绘制  
    context.beginPath();  
    context.drawImage(currentImage.imgObj,currentImage.x,currentImage.y,width,height);  
    context.drawImage(currentImage.preImage.imgObj,currentImage.preImage.x,currentImage.preImage.y,width,height);  
    context.drawImage(currentImage.nextImage.imgObj,currentImage.nextImage.x,currentImage.nextImage.y,width,height);  
    context.drawImage(currentImage.preImage.preImage.imgObj,currentImage.preImage.preImage.x,currentImage.preImage.preImage.y,width,height);  
    //遮罩  
    context.fillStyle="rgba(100,150,185,0.5)";  
    context.fillRect(0,0,100,height);  
    context.fillRect(400,0,100,height);  
    //每一帧的位置变动  
    currentImage.x -= speed;  
    currentImage.preImage.x -= speed;  
    currentImage.nextImage.x -= speed;  
    currentImage.preImage.preImage.x -= speed;  
      
    if(currentImage.x == 200){  
        currentImage.nextImage.x = 500;  
    }  
    //到达指定位置停止变动  
    if(currentImage.x != stopX){  
        autoTimeout = setTimeout("drawFrame()",timeoutInterval);  
    }  
    else{  
          
    }  
}  
  
function InitImages(){  
    img1 = new slideImage("img1",-200,0,document.getElementById("img1"));  
    img2 = new slideImage("img2",100,0,document.getElementById("img2"));  
    img3 = new slideImage("img3",400,0,document.getElementById("img3"));  
    img4 = new slideImage("img4",700,0,document.getElementById("img4"));  
    img1.preImage = img4;  
    img1.nextImage = img2;  
    img2.preImage= img1;  
    img2.nextImage= img3;  
    img3.preImage=img2;  
    img3.nextImage=img4;  
    img4.preImage = img3;  
    img4.nextImage = img1;  
    currentImage=img2;  
    hilightSelectedImg();  
}  
  
function canvasClick(){  
    currentImage = currentImage.nextImage;  
    manuTimeout = setTimeout("drawFrame()",timeoutInterval);  
}  
  
function intervalSlide(){  
    currentImage = currentImage.nextImage;  
    hilightSelectedImg();  
    autoTimeout = setTimeout("drawFrame()", timeoutInterval);  
    setTimeout("intervalSlide()", 5000)  
}  
  
function iconClick(obj){  
    if(obj == "img1"){  
        currentImage = img1;  
    }  
    else if(obj == "img2"){  
        currentImage = img2;  
    }  
    else if(obj == "img3"){  
        currentImage = img3;  
    }  
    else if(obj == "img4"){  
        currentImage = img4;  
    }  
    currentImage.preImage.x = 100;  
    currentImage.preImage.preImage.x = -200;  
    currentImage.x = 400;  
    hilightSelectedImg();  
    manuTimeout = setTimeout("drawFrame()",timeoutInterval);  
}  
  
function hilightSelectedImg(){  
    img1.imgObj.width = 125;  
    img1.imgObj.height = 150;  
    img1.imgObj.style.opacity = 0.5;  
    img2.imgObj.width = 125;  
    img2.imgObj.height = 150;  
    img2.imgObj.style.opacity = 0.5;  
    img3.imgObj.width = 125;  
    img3.imgObj.height = 150;  
    img3.imgObj.style.opacity = 0.5;  
    img4.imgObj.width = 125;  
    img4.imgObj.height = 150;  
    img4.imgObj.style.opacity = 0.5  
    currentImage.imgObj.width = 150;  
    currentImage.imgObj.height = 175;  
    currentImage.imgObj.style.opacity = 1;  
}  