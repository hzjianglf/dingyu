/*     */ package net.risesoft.soa.filecube.service.commons.impl;
/*     */ 
/*     */ import egov.appservice.file.exporter.FileQueryService;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.persistence.EntityManager;
/*     */ import javax.persistence.EntityManagerFactory;
/*     */ import javax.persistence.Query;
/*     */ import net.risesoft.soa.filecube.adapter.FileXMLParser;
/*     */ import net.risesoft.soa.filecube.dao.AttachmentDao;
/*     */ import net.risesoft.soa.filecube.model.FileAttachment;
/*     */ import net.risesoft.soa.filecube.model.FileInfo;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentHelper;
/*     */ import org.dom4j.Element;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("fileQueryService")
/*     */ public class FileQueryServiceImpl
/*     */   implements FileQueryService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private EntityManagerFactory emf;
/*     */ 
/*     */   @Autowired
/*     */   private AttachmentDao attachmentDao;
/*     */ 
/*     */   public String findByProperty(String property, String value)
/*     */   {
/*  32 */     String q = property + " = " + value;
/*  33 */     return findByProperty(q, 0, 2147483647);
/*     */   }
/*     */ 
/*     */   public String findByAttchment(String property, String value)
/*     */   {
/*  39 */     return findByAttchment(property, 0, 2147483647);
/*     */   }
/*     */ 
/*     */   public String findByResponsiblePerson(String property, String value)
/*     */   {
/*  45 */     return findByProperty(property, value);
/*     */   }
/*     */ 
/*     */   public String findByProperty(String query, int start, int rows)
/*     */   {
/*  50 */     EntityManager em = this.emf.createEntityManager();
/*  51 */     String sql = " from FileInfo where " + query;
/*  52 */     Query q = em.createQuery(sql);
/*  53 */     q.setFirstResult(start);
/*  54 */     q.setMaxResults(rows);
/*     */ 
/*  56 */     List fileInfos = q.getResultList();
/*     */ 
/*  58 */     Document doc = DocumentHelper.createDocument();
/*  59 */     doc.setXMLEncoding("utf-8");
/*  60 */     Element element = DocumentHelper.createElement("files");
/*  61 */     doc.setRootElement(element);
/*     */ 
/*  63 */     FileXMLParser xmlParser = new FileXMLParser();
/*  64 */     for (FileInfo file : fileInfos) {
/*  65 */       List attachments = this.attachmentDao.findByFileUid(file.getFileUid(), false);
/*  66 */       Set attachmentsSet = new HashSet();
/*  67 */       for (FileAttachment fileAttachment : attachments) {
/*  68 */         attachmentsSet.add(fileAttachment);
/*     */       }
/*  70 */       file.setFileAttachments(attachmentsSet);
/*  71 */       element.add(xmlParser.getFileXML(file));
/*     */     }
/*     */ 
/*  74 */     return doc.asXML();
/*     */   }
/*     */ 
/*     */   public String findByAttchment(String query, int start, int rows)
/*     */   {
/*  79 */     EntityManager em = this.emf.createEntityManager();
/*  80 */     String sql = " from fileInfo where fileUid in( select fileInfo.fileUid from FileAttachment where " + 
/*  82 */       query + ")";
/*  83 */     Query q = em.createQuery(sql);
/*  84 */     q.setFirstResult(start);
/*  85 */     q.setMaxResults(rows);
/*     */ 
/*  87 */     List fileInfos = q.getResultList();
/*     */ 
/*  89 */     Document doc = DocumentHelper.createDocument();
/*  90 */     doc.setXMLEncoding("utf-8");
/*  91 */     Element element = DocumentHelper.createElement("files");
/*  92 */     doc.setRootElement(element);
/*     */ 
/*  94 */     FileXMLParser xmlParser = new FileXMLParser();
/*  95 */     for (FileInfo file : fileInfos) {
/*  96 */       List attachments = this.attachmentDao.findByFileUid(file.getFileUid(), false);
/*  97 */       Set attachmentsSet = new HashSet();
/*  98 */       for (FileAttachment fileAttachment : attachments) {
/*  99 */         attachmentsSet.add(fileAttachment);
/*     */       }
/* 101 */       file.setFileAttachments(attachmentsSet);
/* 102 */       element.add(xmlParser.getFileXML(file));
/*     */     }
/*     */ 
/* 105 */     return doc.asXML();
/*     */   }
/*     */ 
/*     */   public String findByResponsiblePerson(String query, int start, int rows)
/*     */   {
/* 111 */     return findByResponsiblePerson(query, start, rows);
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.commons.impl.FileQueryServiceImpl
 * JD-Core Version:    0.6.1
 */