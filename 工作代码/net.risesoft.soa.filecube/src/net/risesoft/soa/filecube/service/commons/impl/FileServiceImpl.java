/*     */ package net.risesoft.soa.filecube.service.commons.impl;
/*     */ 
/*     */ import egov.appservice.file.exporter.FileService;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.persistence.EntityManagerFactory;
/*     */ import net.risesoft.soa.filecube.adapter.FileXMLParser;
/*     */ import net.risesoft.soa.filecube.dao.AttachmentDao;
/*     */ import net.risesoft.soa.filecube.dao.FileDao;
/*     */ import net.risesoft.soa.filecube.model.FileAttachment;
/*     */ import net.risesoft.soa.filecube.model.FileInfo;
/*     */ import net.risesoft.soa.filecube.util.DateUtil;
/*     */ import net.risesoft.soa.framework.util.Base64;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentHelper;
/*     */ import org.dom4j.Element;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("fileService")
/*     */ public class FileServiceImpl
/*     */   implements FileService
/*     */ {
/*  39 */   private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
/*     */ 
/*     */   @Autowired
/*     */   private FileDao fileDao;
/*     */ 
/*     */   @Autowired
/*     */   private AttachmentDao attachmentDao;
/*     */ 
/*     */   @Autowired
/*     */   private EntityManagerFactory emf;
/*     */ 
/*  48 */   public String getFile(String id) { FileInfo file = (FileInfo)this.fileDao.findOne(id);
/*  49 */     List attachments = this.attachmentDao.findByFileUid(id, false);
/*  50 */     Set attachmentsSet = new HashSet();
/*  51 */     for (FileAttachment fileAttachment : attachments) {
/*  52 */       attachmentsSet.add(fileAttachment);
/*     */     }
/*  54 */     file.setFileAttachments(attachmentsSet);
/*     */ 
/*  56 */     Document doc = DocumentHelper.createDocument();
/*  57 */     doc.setXMLEncoding("utf-8");
/*  58 */     Element element = DocumentHelper.createElement("files");
/*  59 */     doc.setRootElement(element);
/*     */ 
/*  61 */     FileXMLParser xmlParser = new FileXMLParser();
/*  62 */     element.add(xmlParser.getFileXML(file));
/*     */ 
/*  64 */     return doc.asXML();
/*     */   }
/*     */ 
/*     */   public String getFileContentBase64(String id)
/*     */   {
/*  69 */     return Base64.encodeBytes(getFileContentBytes(id));
/*     */   }
/*     */ 
/*     */   public byte[] getFileContentBytes(String id)
/*     */   {
/*  74 */     FileInfo file = (FileInfo)this.fileDao.findOne(id);
/*  75 */     String filePath = file.getDirectory() + file.getFileUid() + "." + file.getExtension();
/*  76 */     File f = new File(filePath);
/*  77 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*     */     try {
/*  79 */       FileInputStream fis = new FileInputStream(f);
/*  80 */       IOUtils.copy(fis, baos);
/*     */     } catch (Exception e) {
/*  82 */       log.error("获取文件内容出现异常！", e);
/*     */     }
/*  84 */     return baos.toByteArray();
/*     */   }
/*     */ 
/*     */   public String getFileAttchments(String id)
/*     */   {
/*  90 */     Element rootElement = DocumentHelper.createElement("attachments");
/*  91 */     Document doc = DocumentHelper.createDocument(rootElement);
/*  92 */     doc.setXMLEncoding("utf-8");
/*     */ 
/*  94 */     List attachments = this.attachmentDao.findByFileUid(id, false);
/*  95 */     for (FileAttachment fa : attachments) {
/*  96 */       Element attachmentElement = rootElement.addElement("attachment");
/*  97 */       setAttrElement(attachmentElement, fa);
/*     */     }
/*  99 */     return doc.asXML();
/*     */   }
/*     */ 
/*     */   public String getFileAttchmentBase64(String id)
/*     */   {
/* 104 */     return Base64.encodeBytes(getFileAttchmentBytes(id));
/*     */   }
/*     */ 
/*     */   public byte[] getFileAttchmentBytes(String id)
/*     */   {
/* 109 */     FileAttachment attr = (FileAttachment)this.attachmentDao.findOne(id);
/* 110 */     String filePath = attr.getDirectory() + attr.getAttachmentUid() + "." + attr.getExtension();
/* 111 */     File f = new File(filePath);
/* 112 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*     */     try {
/* 114 */       FileInputStream fis = new FileInputStream(f);
/* 115 */       IOUtils.copy(fis, baos);
/*     */     } catch (Exception e) {
/* 117 */       log.error("获取附件内容出现异常！", e);
/*     */     }
/* 119 */     return baos.toByteArray();
/*     */   }
/*     */ 
/*     */   public String getFileAttchment(String id)
/*     */   {
/* 124 */     Document doc = DocumentHelper.createDocument();
/* 125 */     doc.setXMLEncoding("utf-8");
/* 126 */     Element attachmentRoot = DocumentHelper.createElement("attachments");
/* 127 */     doc.setRootElement(attachmentRoot);
/*     */ 
/* 129 */     Element attachmentElement = attachmentRoot.addElement("attachment");
/* 130 */     FileAttachment fa = (FileAttachment)this.attachmentDao.findOne(id);
/* 131 */     setAttrElement(attachmentElement, fa);
/*     */ 
/* 133 */     return doc.asXML();
/*     */   }
/*     */ 
/*     */   private void setAttrElement(Element attachmentElement, FileAttachment fa) {
/* 137 */     attachmentElement.addElement("attachmentId").addText(fa.getAttachmentUid());
/* 138 */     attachmentElement.addElement("name").addText(fa.getName());
/* 139 */     attachmentElement.addElement("size").addText(fa.getSize());
/* 140 */     attachmentElement.addElement("directory").addText(fa.getDirectory());
/* 141 */     attachmentElement.addElement("content").addText(fa.getContent());
/* 142 */     attachmentElement.addElement("extension").addText(fa.getExtension());
/* 143 */     attachmentElement.addElement("type").addText(fa.getType());
/* 144 */     attachmentElement.addElement("createDate").addText(DateUtil.getDate(fa.getCreateDate()));
/* 145 */     attachmentElement.addElement("modifiedDate").addText(DateUtil.getDate(fa.getModifiedDate()));
/* 146 */     attachmentElement.addElement("creatorUid").addText(fa.getCreatorUid());
/* 147 */     attachmentElement.addElement("modifierUid").addText(fa.getModifierUid());
/* 148 */     attachmentElement.addElement("adapterUid").addText(fa.getAdapterUid());
/* 149 */     attachmentElement.addElement("remarks").addText(fa.getRemarks());
/* 150 */     attachmentElement.addElement("deleted").addText(fa.getDeleted());
/* 151 */     attachmentElement.addElement("smallIcon").addText(fa.getSmallIcon());
/* 152 */     attachmentElement.addElement("largeIcon").addText(fa.getLargeIcon());
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.commons.impl.FileServiceImpl
 * JD-Core Version:    0.6.1
 */