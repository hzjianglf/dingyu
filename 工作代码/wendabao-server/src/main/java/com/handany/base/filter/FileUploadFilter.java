package com.handany.base.filter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.handany.base.common.FileUploader;
import com.handany.base.common.JsonObject;
import com.handany.base.util.ImageCompressUtil;
//import com.handany.bm.model.BmPicture;

public class FileUploadFilter implements Filter{

	
	private static Logger logger = LoggerFactory.getLogger(FileUploadFilter.class); 
	
	private HttpServletRequest request;
	
	private static DiskFileItemFactory factory; 
	
	private static String FILE_SAVE_PATH="/data/image";
	
	private static SimpleDateFormat dateFormate = new SimpleDateFormat("yyyyMM");
	
	
	public static ExecutorService executor = Executors.newCachedThreadPool();
	
	private int sizeMax;
	
	private static String TMP_FILE_PATH;
	
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		
		logger.info("初始化文件上传参数 ...");
		
		TMP_FILE_PATH = config.getInitParameter("tmpFilePath");
		FILE_SAVE_PATH = config.getInitParameter("fileSavePath");
		String strSizeMax =  config.getInitParameter("sizeMax");
		try{
			sizeMax = Integer.parseInt(strSizeMax);
		}catch(Exception ex){
			sizeMax = 10;
		}
		
		
	}
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		
		try{
			this.request = (HttpServletRequest)req;
			
			String contentType = request.getContentType();
			
			if(contentType != null && contentType.indexOf("multipart/form-data")>-1){
				logger.debug("接收到multipart/form-data提交");
				recieveMutipart();
				
				processImages();
			}
			chain.doFilter(req, resp);
		}catch(Exception ex){
			
			if(ex instanceof FileUploadException){
				JsonObject jo = new JsonObject();
				jo.getHeader().setSuccess(false);
				jo.getHeader().setMessage("上传失败：图片大小超出限制");
				
				HttpServletResponse response = (HttpServletResponse)resp;
				
				response.setContentType("text/json;charset=utf-8");
				response.getWriter().print(JSON.toJSONString(jo.toJsonObj(),SerializerFeature.DisableCircularReferenceDetect));
				logger.error("上传失败：图片大小超出限制",ex);
			}
		}
	}

	

	
	private void processImages(){
		
		
		if(FileUploader.getFileItemList().size()>0){
			FileUploader.FileItem item = FileUploader.getFileItemList().get(0);
			String realPath = FILE_SAVE_PATH+item.filePath;
			
			//================生成缩略图============================
			String fileType = getFileType(item.filePath);
			File file = new File(realPath);
			
			if(	"jpg".equalsIgnoreCase(fileType)
					|| "png".equalsIgnoreCase(fileType)
					|| "bmp".equalsIgnoreCase(fileType)
					|| "jpeg".equalsIgnoreCase(fileType)
					|| "gif".equalsIgnoreCase(fileType)){
						
				
			
			//大于30k生成缩略图
			if(file.length()>=30000){
//				executor.submit(new ImageCompressTask(realPath, BmPicture.SMALL_IMG_FLAG,400,false));
//				item.smallPath = transName(item.filePath,BmPicture.SMALL_IMG_FLAG);
			}
			//大于600k对原图压缩
			if(file.length()>=100000){
				executor.submit(new ImageCompressTask(realPath,"_c",800,true));
				item.filePath = transName(item.filePath,"_c");
			}
			}
		}
		
	}
	
	
	private String recieveMutipart() throws Exception{
		
		ServletFileUpload upload = new ServletFileUpload(getDiskFileItemFactory());
		
		upload.setSizeMax(sizeMax * 1024 * 1024);
		
		StringBuilder textParams  = new StringBuilder();
		//接收到的文件信息
		try {				
			
			List<FileItem> items = upload.parseRequest(request);
			
			for(FileItem item:items){
				
				if(!item.isFormField()){
					
					String fileName = item.getName();
					if(fileName!=null && fileName.trim().length()>0
							&& item.getSize()>0){
						String[] tmpStr = fileName.split(File.separator+File.separator);
						fileName = tmpStr[tmpStr.length-1];
						String relatePath = createTmpFile(fileName);
						String realPath =FILE_SAVE_PATH+ relatePath; 
						
						
						File file = new File(realPath);
						String smallPath = relatePath;
						item.write(file);
						
//						String fileType = getFileType(realPath);
//						//大于30k的图片文件生成缩略图
//						if(file.length()>=30000 &&
//								("jpg".equalsIgnoreCase(fileType)
//								|| "png".equalsIgnoreCase(fileType)
//								|| "bmp".equalsIgnoreCase(fileType))
//								|| "jpeg".equalsIgnoreCase(fileType)
//								|| "gif".equalsIgnoreCase(fileType)){
//							executor.submit(new ImageCompressTask(realPath));
//							smallPath = toSmallImageUrl(relatePath);
//						}
						
						FileUploader.addFileItem(fileName, relatePath,smallPath);
						
						logger.debug("保存文件："+realPath);
					}
				}else{
					FileUploader.addParam(item.getFieldName(), item.getString());	
					textParams.append("&").append(item.getFieldName()).append("=").append(item.getString()); 
				}
			}
			
		} catch (FileUploadException e) {
			logger.error("上传失败1",e);
			throw e;
		} catch (Exception e) {
			logger.error("上传失败2",e);
			throw e;
		}
		
		
		return textParams.toString();
	}
	

	private static DiskFileItemFactory getDiskFileItemFactory(){
		
		if(factory == null){
			factory = new DiskFileItemFactory();		
			//文件最大占用内存数 512k 
			factory.setSizeThreshold(512*1024);
			//临时文件目录
			factory.setRepository(new File(TMP_FILE_PATH));
		}
		return factory;
	}

	


	/**
	 * 获得要保存的临时文件	
	 * @param fileName 文件名
	 * @return
	 */
	private String createTmpFile(String fileName){
		
		
		String surffix = "";
		
		if(fileName != null){
			surffix = fileName.substring(fileName.lastIndexOf("."));
		}
				
		
		String saveDir = FILE_SAVE_PATH+File.separator+dateFormate.format(Calendar.getInstance().getTime());
		
		File tmpdir = new File(saveDir);
		
		if(!tmpdir.exists()){
			tmpdir.mkdirs();
		}
		
		String saveFileName = UUID.randomUUID()+surffix;;
		
		//相对路径
		String path = File.separator+dateFormate.format(Calendar.getInstance().getTime())+File.separator+saveFileName;
		
//		//绝对路径
//		path[0] = saveDir+File.separator+saveFileName;
		return path;
		
	}
	

	private String getUrl(HttpServletRequest req){
		
		String url =  req.getRequestURL().toString();
		
		if(req.getQueryString() != null){
			url += "?"+req.getQueryString();
		}
		
		return url;
	}
	
	
	class ImageCompressTask implements Runnable{
		private String imagePath = null;
		private String flag = null;
		private int width;
		boolean deleteOld;
		public ImageCompressTask(String imagePath, String flag,int width,boolean deleteOld){
			
			this.width = width;
	
			this.imagePath = imagePath;
			
			this.deleteOld = deleteOld;
			
			if(flag != null){
				this.flag = flag;
			}else{
				this.flag = "";
			}
		}

		@Override
		public void run() {
			
			if(imagePath != null){
				
				try {
					
					String desUrl = transName(imagePath,flag);
					
					ImageCompressUtil.saveMinPhoto(imagePath, desUrl, width, 0.9d,deleteOld);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
	
	
	/**
	 * 转换为压缩url
	 * @param url
	 * @return
	 */
	private static String transName(String url,String flag){
		if(url == null || url.trim().length() ==  0){
			return "";
		}
		
		String part1 = url.substring(0,url.lastIndexOf("."));
		String part2 = url.substring(url.lastIndexOf(".")+1);
		
		
		String smallUrl = part1+flag+"."+part2;
		return smallUrl;
	}
	
	/**
	 * 获得文件类型
	 * @param path
	 * @return
	 */
	private static String getFileType(String path){
		if(path == null || path.trim().length() == 0){
			return "";
		}
		
		return path.substring(path.lastIndexOf(".")+1);
	}
	public static void main(String[] args) {
	}
}
