/*     */ package net.risesoft.soa.filecube.adapter.impl;
/*     */ 
/*     */ import com.sun.star.uno.RuntimeException;
/*     */ import egov.appservice.file.importer.ImporterService;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.StringReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.risesoft.soa.filecube.adapter.FileXMLParser;
/*     */ import net.risesoft.soa.filecube.adapter.ImporterDataTarget;
/*     */ import net.risesoft.soa.filecube.model.FileAdapter;
/*     */ import net.risesoft.soa.filecube.model.FileAttachment;
/*     */ import net.risesoft.soa.filecube.model.FileFolder;
/*     */ import net.risesoft.soa.filecube.model.FileInfo;
/*     */ import net.risesoft.soa.filecube.model.FileOfficialDocument;
/*     */ import net.risesoft.soa.filecube.service.AdapterService;
/*     */ import net.risesoft.soa.filecube.service.AttachmentService;
/*     */ import net.risesoft.soa.filecube.service.FileService;
/*     */ import net.risesoft.soa.filecube.service.FolderService;
/*     */ import net.risesoft.soa.filecube.util.DateUtil;
/*     */ import net.risesoft.soa.filecube.util.GlobalInfo;
/*     */ import net.risesoft.soa.filecube.util.OperationType;
/*     */ import net.risesoft.soa.framework.util.Base64;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentException;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.io.SAXReader;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ @Component("importerPush")
/*     */ public class ImporterServiceImpl
/*     */   implements ImporterService
/*     */ {
/*  38 */   public static final Logger log = LoggerFactory.getLogger(ImporterServiceImpl.class);
/*     */ 
/*     */   @Autowired
/*     */   private FileService fileService;
/*     */ 
/*     */   @Autowired
/*     */   private AttachmentService attachmentService;
/*     */ 
/*     */   @Autowired
/*     */   private FolderService folderService;
/*     */ 
/*     */   @Autowired
/*     */   private AdapterService adapterService;
/*     */ 
/*  49 */   /* （非 Javadoc）
 * @see egov.appservice.file.importer.ImporterService#importData(java.lang.String, java.lang.String)
 */
public boolean importData(String xml, String rootFolderUid) { FileFolder rootFolder = this.folderService.findById(rootFolderUid);
/*  50 */     if (rootFolder == null) {
/*  51 */       throw new RuntimeException("导入数据指定的文件夹不存在！");
/*     */     }
/*  53 */     log.info("开始解析推送数据！");
/*     */     try {
/*  55 */       Document document = new SAXReader().read(new StringReader(xml));
/*  56 */       String appName = document.getRootElement().element("appName").getTextTrim();
/*  57 */       FileAdapter adapter = this.adapterService.findByName(appName);
/*  58 */       if (adapter == null) {
/*  59 */         adapter = new FileAdapter();
/*  60 */         adapter.setName(appName);
/*  61 */         this.adapterService.save(adapter);
/*     */       }
/*     */ 
/*  64 */       FileXMLParser xmlParser = new FileXMLParser();
/*  65 */       List datas = xmlParser.parse(xml);
/*     */ 
/*  67 */       for (ImporterDataTarget importerDataModel : datas) {
/*  68 */         FileInfo fileInfo = importerDataModel.getFileInfo();
/*     */ 
/*  70 */         List files = this.fileService.findByProperty(0, 2147483647, "importUid", fileInfo.getImportUid());
/*     */ 
/*  72 */         FileInfo hasFileInfo = null;
/*  73 */         if (!files.isEmpty())
/*     */         {
/*  76 */           hasFileInfo = (FileInfo)files.get(0);
/*     */         }
/*     */ 
/*  79 */         fileInfo.setAdapter(adapter);
/*     */ 
/*  81 */         if (importerDataModel.getOperateType().equals(OperationType.FC_ADD.toString()))
/*     */         {
/*  84 */           String departmentName = fileInfo.getDepartmentName();
/*  85 */           if ((departmentName == null) || (departmentName.trim().equals("")) || (departmentName.trim().equals("null"))) {
/*  86 */             fileInfo.setDepartmentName("其它");
/*     */           }
/*     */ 
/*  93 */           FileFolder folder = createFolders(rootFolder, fileInfo.getDirectory());
/*  94 */           fileInfo.setFileFolder(folder);
/*  95 */           if (hasFileInfo != null) {
/*  96 */             String forderUid = folder.getFolderUid();
/*  97 */             String fileExtFolderUid = hasFileInfo.getExtendFolderUids();
/*  98 */             if (fileExtFolderUid != null) {
/*  99 */               if (fileExtFolderUid.contains(forderUid)) continue;
/* 100 */               fileExtFolderUid = fileExtFolderUid + "," + forderUid;
/*     */             }
/*     */             else
/*     */             {
/* 105 */               fileExtFolderUid = forderUid;
/*     */             }
/* 107 */             hasFileInfo.setExtendFolderUids(fileExtFolderUid);
/* 108 */             this.fileService.save(hasFileInfo);
/*     */           }
/*     */           else
/*     */           {
/* 112 */             String filePath = GlobalInfo.getInstance().getUploadPath(fileInfo.getDepartmentName());
/*     */ 
/* 114 */             Base64.decodeToFile(fileInfo.getContent(), filePath);
/* 115 */             fileInfo.getFod().setYear(DateUtil.getDate("yyyy", fileInfo.getCreatedate()));
/* 116 */             fileInfo.setYear(DateUtil.getDate("yyyy", fileInfo.getCreatedate()));
/* 117 */             String kind = fileInfo.getFod().getKind();
/* 118 */             if ((kind == null) || (kind.trim().equals("")) || (kind.trim().equals("null"))) {
/* 119 */               fileInfo.setKind("其它");
/* 120 */               fileInfo.getFod().setKind("其它");
/*     */             } else {
/* 122 */               fileInfo.setKind(kind);
/* 123 */               fileInfo.getFod().setKind(kind);
/*     */             }
/* 125 */             fileInfo.getFod().setFileInfo(fileInfo);
/* 126 */             if (!fileInfo.isZipDoc())
/*     */             {
/* 128 */               this.fileService.save(fileInfo, new ByteArrayInputStream(Base64.decode(fileInfo.getContent())));
/*     */             }
/* 130 */             else this.fileService.save(fileInfo);
/*     */ 
/* 132 */             for (FileAttachment attachment : fileInfo.getFileAttachments()) {
/* 133 */               attachment.setFileInfo(fileInfo);
/* 134 */               if (!attachment.isZipDoc())
/* 135 */                 this.attachmentService.save(attachment, new ByteArrayInputStream(Base64.decode(attachment.getContent())));
/*     */               else
/* 137 */                 this.attachmentService.save(attachment);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 142 */       return true;
/*     */     } catch (DocumentException e) {
/* 144 */       log.error("解析文件xml失败！", e);
/*     */     } catch (Exception e) {
/* 146 */       log.error("导入数据失败！", e);
/*     */     }
/* 148 */     return false;
/*     */   }
/*     */ 
/*     */   private FileFolder createFolders(FileFolder rootFolder, String directory)
/*     */   {
/* 156 */     String[] folderNames = directory.split("/");
/* 157 */     List fileFolders = new ArrayList();
/* 158 */     for (int i = 0; i < folderNames.length; i++) {
/* 159 */       String folderName = folderNames[i];
/* 160 */       FileFolder folder = new FileFolder();
/* 161 */       folder.setCreateDate(new Date());
/* 162 */       folder.setFolderUid(UUID.randomUUID());
/* 163 */       folder.setDeleted(Boolean.valueOf(false));
/* 164 */       folder.setName(folderName);
/* 165 */       if (i == 0)
/* 166 */         folder.setParentUid(rootFolder.getFolderUid());
/*     */       else {
/* 168 */         folder.setParentUid(((FileFolder)fileFolders.get(i - 1)).getFolderUid());
/*     */       }
/* 170 */       this.folderService.saveFolderAndResource(folder);
/* 171 */       fileFolders.add(folder);
/*     */     }
/* 173 */     return this.folderService.findById(((FileFolder)fileFolders.get(fileFolders.size() - 1)).getFolderUid());
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.adapter.impl.ImporterServiceImpl
 * JD-Core Version:    0.6.1
 */