/*     */ package net.risesoft.soa.filecube.adapter;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.StringReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.risesoft.soa.filecube.model.FileAdapter;
/*     */ import net.risesoft.soa.filecube.model.FileAttachment;
/*     */ import net.risesoft.soa.filecube.model.FileInfo;
/*     */ import net.risesoft.soa.filecube.model.FileMetaAudio;
/*     */ import net.risesoft.soa.filecube.model.FileMetaDoc;
/*     */ import net.risesoft.soa.filecube.model.FileMetaImage;
/*     */ import net.risesoft.soa.filecube.model.FileMetaVideo;
/*     */ import net.risesoft.soa.filecube.model.FileOfficialDocument;
/*     */ import net.risesoft.soa.filecube.util.DateUtil;
/*     */ import net.risesoft.soa.filecube.util.FileType;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentException;
/*     */ import org.dom4j.DocumentHelper;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.io.SAXReader;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class FileXMLParser
/*     */ {
/*  33 */   private static final Logger log = LoggerFactory.getLogger(FileXMLParser.class);
/*     */ 
/*     */   public List<ImporterDataTarget> parse(String fileXML)
/*     */     throws DocumentException
/*     */   {
/*  42 */     Document document = new SAXReader().read(new StringReader(fileXML));
/*  43 */     List importerDatas = new ArrayList();
/*     */ 
/*  45 */     List files = document.getRootElement().elements("file");
/*  46 */     for (Element file : files) {
/*  47 */       importerDatas.add(parseFileElement(file));
/*     */     }
/*  49 */     return importerDatas;
/*     */   }
/*     */ 
/*     */   public List<ImporterDataTarget> parse(FileAdapter adapter, InputStream is)
/*     */     throws DocumentException
/*     */   {
/*  59 */     Document document = new SAXReader().read(is, "utf-8");
/*  60 */     List importerDatas = new ArrayList();
/*     */ 
/*  62 */     List files = document.getRootElement().elements();
/*  63 */     for (Element file : files) {
/*  64 */       importerDatas.add(parseFileElement(file));
/*     */     }
/*  66 */     return importerDatas;
/*     */   }
/*     */ 
/*     */   private ImporterDataTarget parseFileElement(Element file)
/*     */   {
/*  74 */     ImporterDataTarget importerData = new ImporterDataTarget();
/*  75 */     String operateType = file.attributeValue("operate-type");
/*  76 */     String fileType = file.attributeValue("file-type");
/*     */ 
/*  78 */     FileInfo fileInfo = new FileInfo();
/*  79 */     fileInfo.setImportUid(file.elementText("uid"));
/*     */ 
/*  81 */     fileInfo.setName(file.elementText("name"));
/*  82 */     fileInfo.setExtension(file.elementText("extension"));
/*  83 */     fileInfo.setContent(file.elementText("content"));
/*  84 */     fileInfo.setType(FileType.getFileType(file.elementText("extension")));
/*  85 */     fileInfo.setDirectory(file.elementText("directory"));
/*  86 */     fileInfo.setDeleted(Boolean.valueOf(false));
/*  87 */     fileInfo.setUrl(file.elementText("url"));
/*  88 */     fileInfo.setZipDoc(Boolean.parseBoolean(file.elementText("zipDoc")));
/*     */     try {
/*  90 */       fileInfo.setSize(Long.valueOf(Long.parseLong(file.elementText("size"))));
/*     */     } catch (NumberFormatException e) {
/*  92 */       log.error("解析xml中文件的元素出现异常！", e);
/*     */     }
/*  94 */     if ((file.elementText("createDate") == null) || ("".equals(file.elementText("createDate"))))
/*  95 */       fileInfo.setCreatedate(null);
/*     */     else {
/*  97 */       fileInfo.setCreatedate(DateUtil.parse(file.elementText("createDate")));
/*     */     }
/*  99 */     if ((file.elementText("modifiedDate") == null) || ("".equals(file.elementText("modifiedDate"))))
/* 100 */       fileInfo.setModifieddate(null);
/*     */     else {
/* 102 */       fileInfo.setModifieddate(DateUtil.parse(file.elementText("modifiedDate")));
/*     */     }
/* 104 */     fileInfo.setKeyWords(file.elementText("keyWords"));
/*     */ 
/* 106 */     Element metaData = file.element("meta-type");
/* 107 */     parseMetaDataElement(fileInfo, metaData);
/*     */ 
/* 109 */     Element attachments = file.element("attachments");
/* 110 */     if (attachments != null)
/* 111 */       parseAttachmentsElement(fileInfo, attachments);
/*     */     else {
/* 113 */       fileInfo.setFileAttachments(new HashSet());
/*     */     }
/*     */ 
/* 116 */     Element personLiable = file.element("person-liable");
/* 117 */     parsePersonLiableElement(fileInfo, personLiable);
/*     */ 
/* 119 */     Element metaBusiness = file.element("meta-business");
/* 120 */     if (metaBusiness != null) {
/* 121 */       parseMetaBusinessElement(fileInfo, metaBusiness);
/*     */     }
/*     */ 
/* 124 */     Element metaBusinessType = file.element("meta-business-type");
/* 125 */     if (metaBusinessType != null) {
/* 126 */       parseMetaBusinessTypesElement(fileInfo, metaBusinessType);
/*     */     }
/* 128 */     importerData.setFileInfo(fileInfo);
/* 129 */     importerData.setFileType(fileType);
/* 130 */     importerData.setOperateType(operateType);
/* 131 */     return importerData;
/*     */   }
/*     */ 
/*     */   private void parseMetaBusinessTypesElement(FileInfo fileInfo, Element metaBusinessType)
/*     */   {
/* 141 */     Element fodElement = metaBusinessType;
/*     */ 
/* 143 */     Map fodKV = new HashMap();
/* 144 */     parsePropertyElement(fodKV, fodElement.elements("property"));
/* 145 */     FileOfficialDocument fod = fileInfo.getFod();
/* 146 */     fod.setFileNumber((String)fodKV.get("fileNumber"));
/* 147 */     fod.setKeyWord((String)fodKV.get("keyWord"));
/* 148 */     fod.setKind((String)fodKV.get("kind"));
/* 149 */     fod.setYear((String)fodKV.get("year"));
/* 150 */     fod.setIssueDate(DateUtil.parse((String)fodKV.get("issueDate")));
/* 151 */     fod.setEnmergency((String)fodKV.get("enmergency"));
/* 152 */     fod.setSecretLevel((String)fodKV.get("secretLevel"));
/* 153 */     fod.setSecretLife((String)fodKV.get("secretLife"));
/*     */     try {
/* 155 */       if (fodKV.get("pagination") != null) {
/* 156 */         fod.setPagination(Integer.valueOf(Integer.parseInt((String)fodKV.get("pagination"))));
/*     */       }
/* 158 */       if (fodKV.get("copies") != null)
/* 159 */         fod.setCopies(Integer.valueOf(Integer.parseInt((String)fodKV.get("copies"))));
/*     */     }
/*     */     catch (NumberFormatException e)
/*     */     {
/* 163 */       log.error("解析文件公文类数据的Element出现异常！", e);
/*     */     }
/* 165 */     fod.setUnderSigned((String)fodKV.get("underSigned"));
/* 166 */     fod.setUnitSender((String)fodKV.get("unitSender"));
/* 167 */     fod.setMainReciever((String)fodKV.get("mainReciever"));
/* 168 */     fod.setCopyReciever((String)fodKV.get("copyReciever"));
/* 169 */     fod.setIssuedAuthority((String)fodKV.get("issuedAuthority"));
/* 170 */     fod.setIssueDate(DateUtil.parse((String)fodKV.get("issuedDate")));
/* 171 */     fod.setSubmittedDate(DateUtil.parse((String)fodKV.get("submittedDate")));
/* 172 */     fod.setHandlingUnits((String)fodKV.get("handlingUnits"));
/* 173 */     fod.setProcessedLife(DateUtil.parse((String)fodKV.get("processedLife")));
/* 174 */     fod.setTransactor((String)fodKV.get("transactor"));
/*     */   }
/*     */ 
/*     */   private void parseMetaBusinessElement(FileInfo fileInfo, Element metaBusiness)
/*     */   {
/* 182 */     Map pvs = new HashMap();
/* 183 */     parsePropertyElement(pvs, metaBusiness.elements("property"));
/* 184 */     fileInfo.setApp((String)pvs.get("app"));
/* 185 */     fileInfo.setSubApp((String)pvs.get("subApp"));
/* 186 */     FileAdapter adapter = new FileAdapter();
/* 187 */     adapter.setAdapterUid((String)pvs.get("adapterUid"));
/* 188 */     fileInfo.setAdapter(adapter);
/* 189 */     fileInfo.setKind((String)pvs.get("kind"));
/* 190 */     fileInfo.setYear((String)pvs.get("year"));
/* 191 */     fileInfo.getFod().setKind((String)pvs.get("kind"));
/* 192 */     fileInfo.getFod().setYear((String)pvs.get("year"));
/* 193 */     fileInfo.setDescription((String)pvs.get("description"));
/* 194 */     fileInfo.setRemarks((String)pvs.get("remarks"));
/* 195 */     fileInfo.setEssences((String)pvs.get("essences"));
/* 196 */     fileInfo.setSmallIcon((String)pvs.get("smallIcon"));
/* 197 */     fileInfo.setLargeIcon((String)pvs.get("largeIcon"));
/*     */   }
/*     */ 
/*     */   private void parsePersonLiableElement(FileInfo fileInfo, Element personLiable)
/*     */   {
/* 205 */     Map pvs = new HashMap();
/* 206 */     parsePropertyElement(pvs, personLiable.elements("property"));
/* 207 */     fileInfo.setCreatorUid((String)pvs.get("createUserUid"));
/* 208 */     fileInfo.setCreatorName((String)pvs.get("creatorName"));
/* 209 */     fileInfo.setLastModifiedUserUid((String)pvs.get("lastModifiedUserUid"));
/* 210 */     fileInfo.setDepartmentUid((String)pvs.get("departmentUid"));
/* 211 */     fileInfo.setDepartmentName((String)pvs.get("departmentName"));
/*     */ 
/* 213 */     FileOfficialDocument fod = new FileOfficialDocument();
/* 214 */     fod.setDepartmentUid(fileInfo.getDepartmentUid());
/* 215 */     fod.setDepartmentName(fileInfo.getDepartmentName());
/* 216 */     fileInfo.setFod(fod);
/*     */   }
/*     */ 
/*     */   private void parseAttachmentsElement(FileInfo fileInfo, Element attachments)
/*     */   {
/* 224 */     List attachmentElements = attachments.elements();
/* 225 */     Set fileAttachments = new HashSet();
/* 226 */     for (Element element : attachmentElements) {
/* 227 */       FileAttachment attachment = new FileAttachment();
/*     */ 
/* 229 */       attachment.setImportUid(element.elementText("attachmentId"));
/* 230 */       attachment.setName(element.elementText("name"));
/* 231 */       attachment.setDirectory(element.elementText("directory"));
/* 232 */       attachment.setContent(element.elementText("content"));
/* 233 */       attachment.setExtension(element.elementText("extension"));
/* 234 */       attachment.setType(element.elementText("type"));
/* 235 */       attachment.setCreateDate(DateUtil.parse(element.elementText("createDate")));
/* 236 */       attachment.setModifiedDate(DateUtil.parse(element.elementText("modifiedDate")));
/* 237 */       attachment.setCreatorUid(element.elementText("creatorUid"));
/* 238 */       attachment.setModifierUid(element.elementText("modifierUid"));
/* 239 */       attachment.setAdapterUid(element.elementText("adapterUid"));
/* 240 */       attachment.setRemarks(element.elementText("remarks"));
/* 241 */       attachment.setSmallIcon(element.elementText("smallIcon"));
/* 242 */       attachment.setLargeIcon(element.elementText("largeIcon"));
/* 243 */       attachment.setZipDoc(Boolean.parseBoolean(element.elementText("zipDoc")));
/*     */       try {
/* 245 */         attachment.setSize(Long.valueOf(Long.parseLong(element.elementText("size"))));
/* 246 */         attachment.setDeleted(Boolean.parseBoolean(element.elementText("deleted")));
/*     */       } catch (NumberFormatException e) {
/* 248 */         log.error("解析文件附件数据的Element出现异常！", e);
/*     */       }
/*     */ 
/* 251 */       fileAttachments.add(attachment);
/*     */     }
/* 253 */     fileInfo.setFileAttachments(fileAttachments);
/*     */   }
/*     */ 
/*     */   private void parseMetaDataElement(FileInfo fileInfo, Element metaData)
/*     */   {
/* 261 */     String fileType = fileInfo.getType();
/*     */ 
/* 263 */     Element curFileMetaData = metaData;
/*     */ 
/* 265 */     if (curFileMetaData == null) {
/* 266 */       log.debug("导入的文件没有元数据！导入文件标识：" + fileInfo.getImportUid());
/* 267 */       return;
/*     */     }
/*     */ 
/* 270 */     Map propertyValues = new HashMap();
/*     */ 
/* 272 */     if (fileType.equals("document")) {
/* 273 */       parsePropertyElement(propertyValues, curFileMetaData.elements("property"));
/*     */ 
/* 275 */       FileMetaDoc fileMetaDoc = new FileMetaDoc();
/*     */ 
/* 277 */       fileMetaDoc.setTitle((String)propertyValues.get("title"));
/* 278 */       fileMetaDoc.setImportUid((String)propertyValues.get("filedocId"));
/* 279 */       fileMetaDoc.setKeyWord((String)propertyValues.get("keyWord"));
/* 280 */       fileMetaDoc.setSummary((String)propertyValues.get("summary"));
/* 281 */       fileMetaDoc.setRemarks((String)propertyValues.get("remarks"));
/* 282 */       fileInfo.setFileMetaDoc(fileMetaDoc);
/*     */     }
/*     */ 
/* 285 */     if (fileType.equals("image")) {
/* 286 */       parsePropertyElement(propertyValues, curFileMetaData.elements("property"));
/* 287 */       FileMetaImage fileMetaImage = new FileMetaImage();
/*     */ 
/* 289 */       fileMetaImage.setImportUid((String)propertyValues.get("fileimageId"));
/*     */       try {
/* 291 */         fileMetaImage.setHeight(Integer.valueOf(Integer.parseInt((String)propertyValues.get("height"))));
/* 292 */         fileMetaImage.setWidth(Integer.valueOf(Integer.parseInt((String)propertyValues.get("width"))));
/* 293 */         fileMetaImage.setSaturation(Double.valueOf(Double.parseDouble((String)propertyValues.get("saturation"))));
/* 294 */         fileMetaImage.setContrast(Double.valueOf(Double.parseDouble((String)propertyValues.get("contrast"))));
/*     */       } catch (NumberFormatException e) {
/* 296 */         log.error(" 解析文件元数据的Element出现异常", e);
/*     */       }
/* 298 */       fileInfo.setFileMetaImage(fileMetaImage);
/*     */     }
/*     */ 
/* 301 */     if (fileType.equals("audio")) {
/* 302 */       parsePropertyElement(propertyValues, curFileMetaData.elements("property"));
/* 303 */       FileMetaAudio fileMetaAudio = new FileMetaAudio();
/*     */ 
/* 305 */       fileMetaAudio.setImportUid((String)propertyValues.get("fileAudioId"));
/*     */       try {
/* 307 */         fileMetaAudio.setLength(Integer.valueOf(Integer.parseInt((String)propertyValues.get("length"))));
/*     */       } catch (NumberFormatException e) {
/* 309 */         log.error(" 解析文件元数据的Element出现异常", e);
/*     */       }
/* 311 */       fileInfo.setFileMetaAudio(fileMetaAudio);
/*     */     }
/*     */ 
/* 314 */     if (fileType.equals("video")) {
/* 315 */       parsePropertyElement(propertyValues, curFileMetaData.elements("property"));
/* 316 */       FileMetaVideo fileMetaVideo = new FileMetaVideo();
/*     */ 
/* 318 */       fileMetaVideo.setImportUid((String)propertyValues.get("fileVedioID"));
/*     */       try {
/* 320 */         fileMetaVideo.setHeight(Integer.valueOf(Integer.parseInt((String)propertyValues.get("height"))));
/* 321 */         fileMetaVideo.setWidth(Integer.valueOf(Integer.parseInt((String)propertyValues.get("width"))));
/* 322 */         fileMetaVideo.setLength(Integer.valueOf(Integer.parseInt((String)propertyValues.get("length"))));
/*     */       } catch (NumberFormatException e) {
/* 324 */         log.error(" 解析文件元数据的Element出现异常", e);
/*     */       }
/* 326 */       fileInfo.setFileMetaVideo(fileMetaVideo);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void parsePropertyElement(Map<String, String> propertyValues, List<Element> propertyElements)
/*     */   {
/* 337 */     for (Element element : propertyElements)
/* 338 */       propertyValues.put(element.attributeValue("name"), element.getText());
/*     */   }
/*     */ 
/*     */   public Element getImporterDataXML(ImporterDataTarget importerData)
/*     */   {
/* 347 */     if (importerData == null) {
/* 348 */       return null;
/*     */     }
/* 350 */     Element fileElementRoot = DocumentHelper.createElement("file");
/* 351 */     fileElementRoot.addAttribute("operate-type", importerData.getOperateType());
/* 352 */     fileElementRoot.addAttribute("file-type", importerData.getFileType());
/*     */ 
/* 354 */     fileXML(fileElementRoot, importerData.getFileInfo());
/*     */ 
/* 356 */     return fileElementRoot;
/*     */   }
/*     */ 
/*     */   public Element getFileXML(FileInfo fileInfo)
/*     */   {
/* 365 */     Element fileElementRoot = DocumentHelper.createElement("file");
/* 366 */     fileXML(fileElementRoot, fileInfo);
/* 367 */     return fileElementRoot;
/*     */   }
/*     */ 
/*     */   private void fileXML(Element fileElementRoot, FileInfo fileInfo)
/*     */   {
/* 376 */     fileElementRoot.addElement("uid").addText(fileInfo.getImportUid());
/* 377 */     fileElementRoot.addElement("name").addText(fileInfo.getName());
/* 378 */     fileElementRoot.addElement("extension").addText(fileInfo.getExtension());
/* 379 */     fileElementRoot.addElement("content").addCDATA(fileInfo.getContent());
/* 380 */     fileElementRoot.addElement("type").addText(fileInfo.getType());
/* 381 */     fileElementRoot.addElement("directory").addText(fileInfo.getDirectory());
/* 382 */     fileElementRoot.addElement("url").addText(fileInfo.getUrl());
/* 383 */     fileElementRoot.addElement("zipDoc").addText(fileInfo.isZipDoc());
/* 384 */     fileElementRoot.addElement("size").addText(fileInfo.getSize());
/* 385 */     fileElementRoot.addElement("createDate").addText(DateUtil.getDate(fileInfo.getCreatedate()));
/* 386 */     fileElementRoot.addElement("modifiedDate").addText(DateUtil.getDate(fileInfo.getModifieddate()));
/* 387 */     fileElementRoot.addElement("keyWords").addText(fileInfo.getKeyWords());
/*     */ 
/* 389 */     addMetaTypeElement(fileElementRoot, fileInfo);
/*     */ 
/* 391 */     addAttachmentsElement(fileElementRoot, fileInfo);
/*     */ 
/* 393 */     addPersonLiableElement(fileElementRoot, fileInfo);
/*     */ 
/* 395 */     addMetaBusinessElement(fileElementRoot, fileInfo);
/*     */ 
/* 397 */     addMetaBusinessTypesElement(fileElementRoot, fileInfo);
/*     */   }
/*     */ 
/*     */   private void addMetaBusinessTypesElement(Element fileElementRoot, FileInfo fileInfo)
/*     */   {
/* 406 */     Element mbtes = fileElementRoot.addElement("meta-business-types");
/*     */ 
/* 408 */     FileOfficialDocument fod = fileInfo.getFod();
/* 409 */     if (fod == null) return;
/* 410 */     Element fode = mbtes.addElement("meta-business-type").addAttribute("type", "officialDocument");
/* 411 */     fode.addElement("property").addAttribute("name", "fileNumber").addText(fod.getFileNumber());
/* 412 */     fode.addElement("property").addAttribute("name", "keyWord").addText(fod.getKeyWord());
/* 413 */     fode.addElement("property").addAttribute("name", "kind").addText(fod.getKind());
/* 414 */     fode.addElement("property").addAttribute("name", "issueDate")
/* 415 */       .addText(DateUtil.getDate(fod.getIssueDate()));
/* 416 */     fode.addElement("property").addAttribute("name", "enmergency").addText(fod.getEnmergency());
/* 417 */     fode.addElement("property").addAttribute("name", "secretLevel").addText(fod.getSecretLevel());
/* 418 */     fode.addElement("property").addAttribute("name", "secretLife").addText(fod.getSecretLife());
/* 419 */     fode.addElement("property").addAttribute("name", "pagination")
/* 420 */       .addText(fod.getPagination());
/* 421 */     fode.addElement("property").addAttribute("name", "underSigned").addText(fod.getUnderSigned());
/* 422 */     fode.addElement("property").addAttribute("name", "unitSender").addText(fod.getUnitSender());
/* 423 */     fode.addElement("property").addAttribute("name", "mainReciever").addText(fod.getMainReciever());
/* 424 */     fode.addElement("property").addAttribute("name", "copyReciever").addText(fod.getCopyReciever());
/* 425 */     fode.addElement("property").addAttribute("name", "issuedAuthority").addText(fod.getIssuedAuthority());
/* 426 */     fode.addElement("property").addAttribute("name", "copies")
/* 427 */       .addText(fod.getCopies());
/* 428 */     fode.addElement("property").addAttribute("name", "issuedDate").addText(DateUtil.getDate(fod.getIssuedDate()));
/* 429 */     fode.addElement("property").addAttribute("name", "submittedDate").addText(DateUtil.getDate(fod.getSubmittedDate()));
/* 430 */     fode.addElement("property").addAttribute("name", "handlingUnits").addText(fod.getHandlingUnits());
/* 431 */     fode.addElement("property").addAttribute("name", "processedLife").addText(DateUtil.getDate(fod.getProcessedLife()));
/* 432 */     fode.addElement("property").addAttribute("name", "transactor").addText(fod.getHandlingUnits());
/*     */   }
/*     */ 
/*     */   private void addMetaBusinessElement(Element fileElementRoot, FileInfo fileInfo)
/*     */   {
/* 442 */     Element mbe = fileElementRoot.addElement("meta-business");
/* 443 */     mbe.addElement("property").addAttribute("name", "app").addText(fileInfo.getApp());
/* 444 */     mbe.addElement("property").addAttribute("name", "subApp").addText(fileInfo.getSubApp());
/* 445 */     String adapterUid = "";
/* 446 */     if (fileInfo.getAdapter() != null) {
/* 447 */       adapterUid = fileInfo.getAdapter().getAdapterUid();
/*     */     }
/* 449 */     mbe.addElement("property").addAttribute("name", "adapterUid").addText(adapterUid);
/* 450 */     mbe.addElement("property").addAttribute("name", "kind").addText(fileInfo.getKind());
/* 451 */     mbe.addElement("property").addAttribute("name", "description").addText(fileInfo.getDescription());
/* 452 */     mbe.addElement("property").addAttribute("name", "remarks").addText(fileInfo.getRemarks());
/* 453 */     mbe.addElement("property").addAttribute("name", "essences").addText(fileInfo.getEssences());
/* 454 */     mbe.addElement("property").addAttribute("name", "smallIcon").addText(fileInfo.getSmallIcon());
/* 455 */     mbe.addElement("property").addAttribute("name", "largeIcon").addText(fileInfo.getLargeIcon());
/*     */   }
/*     */ 
/*     */   private void addPersonLiableElement(Element fileElementRoot, FileInfo fileInfo)
/*     */   {
/* 465 */     Element personLiableElement = fileElementRoot.addElement("person-liable");
/* 466 */     personLiableElement.addElement("property").addAttribute("name", "createUserUid")
/* 467 */       .addText(fileInfo.getCreatorUid());
/* 468 */     personLiableElement.addElement("property").addAttribute("name", "creatorName")
/* 469 */       .addText(fileInfo.getCreatorName());
/* 470 */     personLiableElement.addElement("property").addAttribute("name", "lastModifiedUserUid")
/* 471 */       .addText(fileInfo.getLastModifiedUserUid());
/* 472 */     personLiableElement.addElement("property").addAttribute("name", "departmentUid")
/* 473 */       .addText(fileInfo.getDepartmentUid());
/* 474 */     personLiableElement.addElement("property").addAttribute("name", "departmentName")
/* 475 */       .addText(fileInfo.getDepartmentName());
/*     */   }
/*     */ 
/*     */   private void addAttachmentsElement(Element fileElementRoot, FileInfo fileInfo)
/*     */   {
/* 483 */     Element attachmentRoot = fileElementRoot.addElement("attachments");
/* 484 */     Set fileAttachments = fileInfo.getFileAttachments();
/* 485 */     if ((fileAttachments == null) || (fileAttachments.isEmpty())) return;
/* 486 */     for (FileAttachment fa : fileAttachments) {
/* 487 */       Element attachmentElement = attachmentRoot.addElement("attachment");
/* 488 */       attachmentElement.addElement("attachmentId").addText(fa.getAttachmentUid());
/* 489 */       attachmentElement.addElement("name").addText(fa.getName());
/* 490 */       attachmentElement.addElement("size").addText(fa.getSize());
/* 491 */       attachmentElement.addElement("directory").addText(fa.getDirectory());
/* 492 */       attachmentElement.addElement("content").addText(fa.getContent());
/* 493 */       attachmentElement.addElement("extension").addText(fa.getExtension());
/* 494 */       attachmentElement.addElement("type").addText(fa.getType());
/* 495 */       attachmentElement.addElement("createDate").addText(DateUtil.getDate(fa.getCreateDate()));
/* 496 */       attachmentElement.addElement("modifiedDate").addText(DateUtil.getDate(fa.getModifiedDate()));
/* 497 */       attachmentElement.addElement("creatorUid").addText(fa.getCreatorUid());
/* 498 */       attachmentElement.addElement("modifierUid").addText(fa.getModifierUid());
/* 499 */       attachmentElement.addElement("adapterUid").addText(fa.getAdapterUid());
/* 500 */       attachmentElement.addElement("remarks").addText(fa.getRemarks());
/* 501 */       attachmentElement.addElement("deleted").addText(fa.getDeleted());
/* 502 */       attachmentElement.addElement("smallIcon").addText(fa.getSmallIcon());
/* 503 */       attachmentElement.addElement("largeIcon").addText(fa.getLargeIcon());
/* 504 */       attachmentElement.addElement("zipDoc").addText(fa.isZipDoc());
/*     */     }
/*     */   }
/*     */ 
/*     */   private void addMetaTypeElement(Element fileElementRoot, FileInfo fileInfo)
/*     */   {
/* 513 */     Element metaTypeRoot = fileElementRoot.addElement("meta-types");
/*     */ 
/* 515 */     FileMetaDoc md = fileInfo.getFileMetaDoc();
/* 516 */     if (md != null) {
/* 517 */       Element metaType_document = metaTypeRoot.addElement("meta-type")
/* 518 */         .addAttribute("file-type", "document");
/* 519 */       metaType_document.addElement("property").addAttribute("name", "filedocId").addText(
/* 520 */         fileInfo.getFileMetaDoc().getImportUid());
/* 521 */       metaType_document.addElement("property").addAttribute("name", "title").addText(md.getTitle());
/* 522 */       metaType_document.addElement("property").addAttribute("name", "keyWord").addText(md.getKeyWord());
/* 523 */       metaType_document.addElement("property").addAttribute("name", "summary").addText(md.getSummary());
/* 524 */       metaType_document.addElement("property").addAttribute("name", "remarks").addText(md.getRemarks());
/*     */     }
/*     */ 
/* 528 */     FileMetaImage mi = fileInfo.getFileMetaImage();
/* 529 */     if (mi != null) {
/* 530 */       Element metaType_image = metaTypeRoot.addElement("meta-type")
/* 531 */         .addAttribute("file-type", "image");
/* 532 */       metaType_image.addElement("property").addAttribute("name", "fileimageId").addText(mi.getMetaImageUid());
/* 533 */       metaType_image.addElement("property").addAttribute("name", "width").addText(mi.getWidth());
/* 534 */       metaType_image.addElement("property").addAttribute("name", "height").addText(mi.getHeight());
/* 535 */       metaType_image.addElement("property").addAttribute("name", "saturation").addText(mi.getSaturation());
/* 536 */       metaType_image.addElement("property").addAttribute("name", "contrast").addText(mi.getContrast());
/*     */     }
/*     */ 
/* 540 */     FileMetaAudio ma = fileInfo.getFileMetaAudio();
/* 541 */     if (ma != null) {
/* 542 */       Element metaType_audio = metaTypeRoot.addElement("meta-type").addAttribute("file-type", "audio");
/* 543 */       metaType_audio.addElement("property").addAttribute("name", "fileAudioId").addText(ma.getMetaAudioUid());
/* 544 */       metaType_audio.addElement("property").addAttribute("name", "length").addText(ma.getLength());
/*     */     }
/*     */ 
/* 548 */     FileMetaVideo mv = fileInfo.getFileMetaVideo();
/* 549 */     if (mv != null) {
/* 550 */       Element metaType_video = metaTypeRoot.addElement("meta-type").addAttribute("file-type", "video");
/* 551 */       metaType_video.addElement("property").addAttribute("name", "fileVedioID").addText(mv.getMetaVideoUid());
/* 552 */       metaType_video.addElement("property").addAttribute("name", "width").addText(mv.getWidth());
/* 553 */       metaType_video.addElement("property").addAttribute("name", "height").addText(mv.getHeight());
/* 554 */       metaType_video.addElement("property").addAttribute("name", "length").addText(mv.getLength());
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 559 */     FileXMLParser parser = new FileXMLParser();
/* 560 */     List importerDatas = new ArrayList();
/*     */     try {
/* 562 */       importerDatas = parser.parse(new FileAdapter(), 
/* 563 */         FileXMLParser.class.getResourceAsStream("files.xml"));
/*     */     } catch (DocumentException e) {
/* 565 */       e.printStackTrace();
/*     */     }
/* 567 */     Document doc = DocumentHelper.createDocument();
/* 568 */     doc.setXMLEncoding("utf-8");
/* 569 */     doc.setName("files");
/* 570 */     for (ImporterDataTarget importerData : importerDatas) {
/* 571 */       doc.add(parser.getImporterDataXML(importerData));
/*     */     }
/*     */ 
/* 574 */     System.out.println(doc.asXML());
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.adapter.FileXMLParser
 * JD-Core Version:    0.6.1
 */