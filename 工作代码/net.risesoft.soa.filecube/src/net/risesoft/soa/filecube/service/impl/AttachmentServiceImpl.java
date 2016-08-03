/*     */ package net.risesoft.soa.filecube.service.impl;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.List;
/*     */ import net.risesoft.soa.filecube.dao.AttachmentDao;
/*     */ import net.risesoft.soa.filecube.model.FileAttachment;
/*     */ import net.risesoft.soa.filecube.model.FileInfo;
/*     */ import net.risesoft.soa.filecube.service.AttachmentService;
/*     */ import net.risesoft.soa.filecube.util.FileType;
/*     */ import net.risesoft.soa.filecube.util.FileUploader;
/*     */ import net.risesoft.soa.filecube.util.FileUtils;
/*     */ import net.risesoft.soa.filecube.util.GlobalInfo;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Transactional
/*     */ @Service("attachmentService")
/*     */ public class AttachmentServiceImpl
/*     */   implements AttachmentService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   public AttachmentDao attachmentDao;
/*     */ 
/*     */   public FileAttachment findById(String attachmentUid)
/*     */   {
/*  32 */     return (FileAttachment)this.attachmentDao.findOne(attachmentUid);
/*     */   }
/*     */   public void convertFile(String attachmentUid) {
/*  35 */     FileAttachment attachment = findById(attachmentUid);
/*  36 */     String filePath = GlobalInfo.getInstance().getUploadRoot() + attachment.getDirectory();
/*  37 */     if (attachment.isZipDoc()) {
/*  38 */       String zipFilePath = GlobalInfo.getInstance().getHD_fileShareDir() + filePath;
/*  39 */       if (!new File(zipFilePath).exists())
/*  40 */         return;
/*  41 */       byte[] fileBytes = FileUtils.zipEntityBytes(zipFilePath + ".zip", "risedata");
/*  42 */       FileOutputStream fos = null;
/*     */       try {
/*  44 */         File f = new File(zipFilePath + attachment.getAttachmentUid() + "." + attachment.getExtension());
/*  45 */         fos = new FileOutputStream(f);
/*  46 */         fos.write(fileBytes);
/*  47 */         String pathName = filePath + attachment.getAttachmentUid() + "." + attachment.getExtension();
/*  48 */         FileUploader.convertFile(pathName);
/*  49 */         f.delete();
/*     */       } catch (IOException e) {
/*  51 */         e.printStackTrace();
/*     */         try
/*     */         {
/*  54 */           if (fos == null) return;
/*  55 */           fos.close();
/*     */         } catch (IOException e) {
/*  57 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */       finally
/*     */       {
/*     */         try
/*     */         {
/*  54 */           if (fos != null)
/*  55 */             fos.close();
/*     */         } catch (IOException e) {
/*  57 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */       try
/*     */       {
/*  54 */         if (fos == null) return;
/*  55 */         fos.close();
/*     */       } catch (IOException e) {
/*  57 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */     else {
/*  61 */       String pathName = filePath + attachment.getAttachmentUid() + "." + attachment.getExtension();
/*  62 */       FileUploader.convertFile(pathName);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void save(FileAttachment attachment, InputStream is)
/*     */   {
/*  71 */     String departMentName = attachment.getFileInfo().getDepartmentName();
/*  72 */     if ((departMentName == null) || ("".equals(departMentName))) {
/*  73 */       departMentName = "其它";
/*     */     }
/*  75 */     String uploadDir = GlobalInfo.getInstance().getUploadPath(departMentName);
/*  76 */     String path = GlobalInfo.getInstance().getUploadRoot() + uploadDir;
/*  77 */     File pFile = new File(path);
/*  78 */     if (!pFile.exists()) {
/*  79 */       pFile.mkdirs();
/*     */     }
/*     */ 
/*  82 */     String extension = attachment.getExtension();
/*     */ 
/*  84 */     attachment.setDirectory(uploadDir);
/*  85 */     attachment.setType(FileType.getFileType(extension));
/*  86 */     this.attachmentDao.save(attachment);
/*     */ 
/*  88 */     String pathName = GlobalInfo.getInstance().getUploadRoot() + uploadDir + attachment.getAttachmentUid() + "." + extension;
/*  89 */     FileUploader.createFile(pathName, is);
/*     */ 
/*  91 */     File f = new File(pathName);
/*  92 */     String content = FileUploader.fileToTxt(f);
/*  93 */     attachment.setContent(content);
/*  94 */     attachment.setSize(Long.valueOf(f.length()));
/*  95 */     this.attachmentDao.save(attachment);
/*     */   }
/*     */ 
/*     */   public FileAttachment save(FileAttachment attachment)
/*     */   {
/* 102 */     if (attachment.isZipDoc()) {
/* 103 */       String zipFilePath = GlobalInfo.getInstance().getHD_fileShareDir() + 
/* 104 */         attachment.getDirectory() + ".zip";
/* 105 */       File f = new File(zipFilePath);
/* 106 */       if (f.exists()) {
/* 107 */         attachment.setSize(Long.valueOf(f.length()));
/*     */       }
/* 109 */       attachment.setDirectory(GlobalInfo.getInstance().getHD_fileShareDir() + attachment.getDirectory());
/*     */     }
/* 111 */     return (FileAttachment)this.attachmentDao.save(attachment);
/*     */   }
/*     */   public List<FileAttachment> findByFileUid(String fileUid) {
/* 114 */     return this.attachmentDao.findByFileUid(fileUid, false);
/*     */   }
/*     */ 
/*     */   public void delete(String attachmentUid)
/*     */   {
/* 121 */     FileAttachment attachment = findById(attachmentUid);
/* 122 */     File f = new File(GlobalInfo.getInstance().getUploadRoot() + attachment.getDirectory() + "/" + attachmentUid + "." + attachment.getExtension());
/* 123 */     f.delete();
/* 124 */     f = new File(GlobalInfo.getInstance().getUploadRoot() + attachment.getDirectory() + "/" + attachmentUid + ".swf");
/* 125 */     f.delete();
/* 126 */     this.attachmentDao.delete(attachment);
/*     */   }
/*     */ 
/*     */   public void update(FileAttachment attachment)
/*     */   {
/* 132 */     this.attachmentDao.save(attachment);
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.impl.AttachmentServiceImpl
 * JD-Core Version:    0.6.1
 */