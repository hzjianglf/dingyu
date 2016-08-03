/*     */ package net.risesoft.soa.filecube.service.commons.impl;
/*     */ 
/*     */ import egov.appservice.file.exporter.FileListService;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.risesoft.soa.filecube.adapter.FileXMLParser;
/*     */ import net.risesoft.soa.filecube.dao.AttachmentDao;
/*     */ import net.risesoft.soa.filecube.dao.FileDao;
/*     */ import net.risesoft.soa.filecube.dao.FolderDao;
/*     */ import net.risesoft.soa.filecube.model.FileAttachment;
/*     */ import net.risesoft.soa.filecube.model.FileFolder;
/*     */ import net.risesoft.soa.filecube.model.FileInfo;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentHelper;
/*     */ import org.dom4j.Element;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("fileListService")
/*     */ public class FileListServiceImpl
/*     */   implements FileListService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private FolderDao folderDao;
/*     */ 
/*     */   @Autowired
/*     */   private FileDao fileDao;
/*     */ 
/*     */   @Autowired
/*     */   private AttachmentDao attachmentDao;
/*     */ 
/*     */   public String getFolderById(String folderId)
/*     */   {
/*  33 */     FileFolder folder = (FileFolder)this.folderDao.findOne(folderId);
/*     */ 
/*  35 */     Document doc = DocumentHelper.createDocument();
/*  36 */     doc.setXMLEncoding("utf-8");
/*  37 */     Element rootElement = DocumentHelper.createElement("folders");
/*  38 */     doc.setRootElement(rootElement);
/*     */ 
/*  40 */     if (folder == null) return doc.asXML();
/*     */ 
/*  42 */     Element folderElement = rootElement.addElement("folder");
/*  43 */     setFolderElement(folderElement, folder);
/*     */ 
/*  45 */     return doc.asXML();
/*     */   }
/*     */ 
/*     */   public String getFolderByName(String folderName)
/*     */   {
/*  50 */     List folders = this.folderDao.findByName(folderName);
/*  51 */     Document doc = DocumentHelper.createDocument();
/*  52 */     doc.setXMLEncoding("utf-8");
/*  53 */     Element rootElement = DocumentHelper.createElement("folders");
/*  54 */     doc.setRootElement(rootElement);
/*     */ 
/*  56 */     for (FileFolder folder : folders) {
/*  57 */       Element folderElement = rootElement.addElement("folder");
/*  58 */       setFolderElement(folderElement, folder);
/*     */     }
/*  60 */     return doc.asXML();
/*     */   }
/*     */ 
/*     */   public String getSubFolders(String folderId)
/*     */   {
/*  65 */     List folders = this.folderDao.findByParentUid(folderId, false);
/*  66 */     Document doc = DocumentHelper.createDocument();
/*  67 */     doc.setXMLEncoding("utf-8");
/*  68 */     Element rootElement = DocumentHelper.createElement("folders");
/*  69 */     doc.setRootElement(rootElement);
/*     */ 
/*  71 */     for (FileFolder folder : folders) {
/*  72 */       Element folderElement = rootElement.addElement("folder");
/*  73 */       setFolderElement(folderElement, folder);
/*     */     }
/*  75 */     return doc.asXML();
/*     */   }
/*     */ 
/*     */   public String getSubFiles(String folderId)
/*     */   {
/*  80 */     List files = this.fileDao.findByFolderUid(folderId);
/*     */ 
/*  82 */     FileXMLParser xmlParser = new FileXMLParser();
/*  83 */     Document doc = DocumentHelper.createDocument();
/*  84 */     doc.setXMLEncoding("utf-8");
/*  85 */     Element rootElement = DocumentHelper.createElement("files");
/*  86 */     doc.setRootElement(rootElement);
/*     */ 
/*  88 */     for (FileInfo file : files) {
/*  89 */       List attachments = this.attachmentDao.findByFileUid(file.getFileUid(), false);
/*  90 */       Set attachmentsSet = new HashSet();
/*  91 */       for (FileAttachment fileAttachment : attachments) {
/*  92 */         attachmentsSet.add(fileAttachment);
/*     */       }
/*  94 */       file.setFileAttachments(attachmentsSet);
/*  95 */       rootElement.add(xmlParser.getFileXML(file));
/*     */     }
/*  97 */     return doc.asXML();
/*     */   }
/*     */ 
/*     */   private void setFolderElement(Element folderElement, FileFolder folder) {
/* 101 */     folderElement.addElement("uid").addText(folder.getFolderUid());
/* 102 */     folderElement.addElement("name").addText(folder.getName());
/* 103 */     folderElement.addElement("description").addText(folder.getDescription());
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.commons.impl.FileListServiceImpl
 * JD-Core Version:    0.6.1
 */