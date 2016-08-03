/*     */ package net.risesoft.soa.filecube.service.util;
/*     */ 
/*     */ import egov.appservice.ac.exception.AccessManagerException;
/*     */ import egov.appservice.ac.model.Operation;
/*     */ import egov.appservice.ac.model.Resource;
/*     */ import egov.appservice.ac.service.AccessControlService;
/*     */ import egov.appservice.ac.service.AccessGrantService;
/*     */ import egov.appservice.ac.service.ResourceManager;
/*     */ import egov.appservice.asf.service.ServiceClient;
/*     */ import egov.appservice.asf.service.ServiceClientFactory;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.risesoft.soa.filecube.model.FileFolder;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ @Component("acManager")
/*     */ public class AcManager
/*     */ {
/*  22 */   public static final Logger log = LoggerFactory.getLogger(AcManager.class);
/*     */   private static ServiceClient serviceClient;
/*     */   private static ResourceManager resourceManager;
/*     */   private static AccessControlService accessControlService;
/*     */   private static AccessGrantService accessGrantService;
/*     */ 
/*     */   public AcManager()
/*     */     throws Exception
/*     */   {
/*     */   }
/*     */ 
/*     */   private void init()
/*     */   {
/*  36 */     if (serviceClient == null)
/*     */       try {
/*  38 */         serviceClient = ServiceClientFactory.getServiceClient();
/*  39 */         resourceManager = (ResourceManager)serviceClient.getServiceByName("ac.ResourceManager");
/*  40 */         accessControlService = (AccessControlService)serviceClient.getServiceByName("ac.AccessControlService");
/*  41 */         accessGrantService = (AccessGrantService)serviceClient.getServiceByName("ac.AccessGrantService");
/*     */       } catch (Exception e) {
/*  43 */         log.error("", e);
/*     */       }
/*     */   }
/*     */ 
/*     */   public Resource createResource(String name, String description, String parentResourceUID, String type)
/*     */     throws Exception
/*     */   {
/*  58 */     init();
/*  59 */     Resource resource = resourceManager.createResource(type, name, parentResourceUID);
/*  60 */     resource.setDescription(description);
/*  61 */     resourceManager.updateResource(resource);
/*  62 */     return resource;
/*     */   }
/*     */ 
/*     */   public void deleteFolderResource(String resourceUID)
/*     */     throws Exception
/*     */   {
/*  71 */     init();
/*  72 */     resourceManager.deleteResource(resourceUID);
/*     */   }
/*     */ 
/*     */   public void updateFolderResource(FileFolder folder)
/*     */     throws Exception
/*     */   {
/*  81 */     init();
/*  82 */     Resource resource = getFolderResource(folder.getResourceUid());
/*  83 */     resource.setName(folder.getName());
/*  84 */     resource.setDescription(folder.getDescription());
/*     */ 
/*  87 */     resourceManager.updateResource(resource);
/*     */   }
/*     */ 
/*     */   public Resource getFolderResource(String resourceUID)
/*     */     throws Exception
/*     */   {
/*  97 */     init();
/*  98 */     return resourceManager.getResource(resourceUID);
/*     */   }
/*     */ 
/*     */   public Map<String, List<Resource>> getResource(String actorID, String operationKey, String resourceUID, Map<String, String> properties)
/*     */     throws Exception
/*     */   {
/* 110 */     init();
/* 111 */     return accessControlService.getResources(actorID, operationKey, resourceUID, properties);
/*     */   }
/*     */ 
/*     */   public void moveFolderResource(String resourceUID, String parentResourceUID)
/*     */     throws Exception
/*     */   {
/* 120 */     init();
/* 121 */     resourceManager.move(resourceUID, parentResourceUID);
/*     */   }
/*     */ 
/*     */   public List<Resource> getSubResources(String actorUID, String operationKey, String resourceUID, Map<String, String> properties)
/*     */     throws Exception
/*     */   {
/* 133 */     init();
/* 134 */     return accessControlService.getSubResources(actorUID, operationKey, resourceUID, properties);
/*     */   }
/*     */ 
/*     */   public Operation[] getOperations(String actorUID, String resourceUID, Map<String, String> properties)
/*     */     throws Exception
/*     */   {
/* 145 */     init();
/* 146 */     return (Operation[])accessControlService.getOperations(actorUID, resourceUID, properties).toArray(new Operation[0]);
/*     */   }
/*     */ 
/*     */   public Boolean hasPermission(String actorUID, String resouceUID, String operationKey)
/*     */     throws Exception
/*     */   {
/* 158 */     init();
/* 159 */     return accessControlService.hasPermission(actorUID, resouceUID, operationKey);
/*     */   }
/*     */ 
/*     */   public void grantPermission(String[] userUids, String[] resouceUIDs, String[] operationKeys)
/*     */   {
/* 169 */     init();
/*     */     try {
/* 171 */       accessGrantService.grantActorPermission(userUids, resouceUIDs, operationKeys);
/*     */     } catch (AccessManagerException e) {
/* 173 */       log.error("授权失败！", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void grantPermission(String userUid, String resouceUID, String operationKey)
/*     */   {
/* 183 */     init();
/*     */     try {
/* 185 */       accessGrantService.grantActorPermission(userUid, resouceUID, operationKey);
/*     */     } catch (AccessManagerException e) {
/* 187 */       log.error("授权失败！", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void grantPermission(String userUid, String resouceUID, String[] operationKeys)
/*     */   {
/* 197 */     init();
/* 198 */     String[] userUids = new String[1];
/* 199 */     userUids[0] = userUid;
/* 200 */     String[] resouceUIDs = new String[1];
/* 201 */     resouceUIDs[0] = resouceUID;
/*     */     try {
/* 203 */       accessGrantService.grantActorPermission(userUids, resouceUIDs, operationKeys);
/*     */     } catch (AccessManagerException e) {
/* 205 */       log.error("授权失败！", e);
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.util.AcManager
 * JD-Core Version:    0.6.1
 */