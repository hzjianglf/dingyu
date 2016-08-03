/*     */ package net.risesoft.soa.filecube.service.util;
/*     */ 
/*     */ import java.io.StringWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.risesoft.soa.filecube.util.GlobalInfo;
/*     */ import net.risesoft.soa.framework.util.json.JSONException;
/*     */ import net.risesoft.soa.framework.util.json.JSONWriter;
/*     */ import net.risesoft.soa.org.manager.OrgUnitContainerManager;
/*     */ import net.risesoft.soa.org.manager.OrgUnitManager;
/*     */ import net.risesoft.soa.org.model.OrgType;
/*     */ import net.risesoft.soa.org.model.OrgUnit;
/*     */ import net.risesoft.soa.org.model.Person;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ @Component("orgTreeUtil")
/*     */ public class OrgTreeUtil
/*     */ {
/*  26 */   private static final Logger log = LoggerFactory.getLogger(OrgTreeUtil.class);
/*     */ 
/*     */   @Autowired
/*     */   private OrgUnitManager orgUnitManager;
/*     */ 
/*     */   @Autowired
/*     */   private OrgUnitContainerManager orgUnitContainerManager;
/*     */ 
/*  35 */   public String getFirstTree() { String roots = GlobalInfo.getInstance().getOrgRoots();
/*  36 */     StringWriter sw = new StringWriter();
/*     */     try {
/*  38 */       JSONWriter json = new JSONWriter(sw);
/*  39 */       json.array();
/*  40 */       for (String root : roots.split(",")) {
/*  41 */         OrgUnit orgUnit = this.orgUnitContainerManager.get(root);
/*  42 */         if (orgUnit != null) {
/*  43 */           json.object();
/*  44 */           getOrgUnitJSON(orgUnit, json, true);
/*  45 */           json.endObject();
/*     */         }
/*     */       }
/*  48 */       json.endArray();
/*     */     } catch (JSONException e) {
/*  50 */       log.error("生成json数据报错!", e);
/*     */     }
/*  52 */     return sw.toString(); }
/*     */ 
/*     */   private void getOrgUnitJSON(OrgUnit orgUnit, JSONWriter json, boolean expanded)
/*     */   {
/*     */     try {
/*  57 */       String type = orgUnit.getOrgType();
/*  58 */       json.key("id").value(orgUnit.getId());
/*  59 */       json.key("text").value(orgUnit.getName());
/*  60 */       json.key("iconCls").value(orgUnit.getOrgType().toLowerCase() + "-icon");
/*  61 */       if ("Person".equals(orgUnit.getOrgType())) {
/*  62 */         Person person = (Person)orgUnit;
/*  63 */         String duty = person.getDuty();
/*  64 */         if ((duty != null) && (duty.trim().length() > 0)) {
/*  65 */           json.key("duty").value(duty);
/*     */         }
/*  67 */         String mobile = person.getMobile();
/*  68 */         if ((mobile != null) && (mobile.trim().length() > 0)) {
/*  69 */           json.key("text").value(orgUnit.getName() + "(" + mobile + ")");
/*  70 */           json.key("mobile").value(person.getMobile());
/*     */         }
/*  72 */         if (person.isDisabled()) {
/*  73 */           json.key("cls").value("complete");
/*     */         }
/*  75 */         json.key("dn").value(person.getDn());
/*  76 */         if (person.getSex() == 1) {
/*  77 */           json.key("iconCls").value("person-female-icon");
/*     */         }
/*     */       }
/*  80 */       json.key("name").value(orgUnit.getName());
/*  81 */       json.key("tabIndex").value(orgUnit.getTabIndex());
/*  82 */       json.key("orgType").value(orgUnit.getOrgType());
/*  83 */       json.key("checked").value(false);
/*  84 */       if (expanded) {
/*  85 */         json.key("expanded").value(true);
/*     */       }
/*  87 */       json.key("leaf").value(true);
/*  88 */       if ((OrgType.ORGANIZATION.equals(type)) || (OrgType.DEPARTMENT.equals(type)))
/*  89 */         json.key("leaf").value(false);
/*     */     }
/*     */     catch (JSONException e) {
/*  92 */       log.error("生成json数据报错!", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getSubTree(String nodeID, String orgType) {
/*  97 */     StringWriter sw = new StringWriter();
/*     */     try {
/*  99 */       JSONWriter json = new JSONWriter(sw);
/* 100 */       json.array();
/* 101 */       if ((OrgType.ORGANIZATION.equals(orgType)) || (OrgType.DEPARTMENT.equals(orgType))) {
/* 102 */         List orgUnits = new ArrayList();
/* 103 */         orgUnits.addAll(this.orgUnitContainerManager.getDepartments(nodeID));
/* 104 */         orgUnits.addAll(this.orgUnitContainerManager.getPersons(nodeID));
/* 105 */         for (int i = 0; i < orgUnits.size(); i++) {
/* 106 */           OrgUnit orgUnit = (OrgUnit)orgUnits.get(i);
/* 107 */           json.object();
/* 108 */           getOrgUnitJSON(orgUnit, json, false);
/* 109 */           json.endObject();
/*     */         }
/*     */       }
/* 112 */       json.endArray();
/*     */     } catch (JSONException e) {
/* 114 */       log.error("生成json数据报错!", e);
/*     */     }
/* 116 */     return sw.toString();
/*     */   }
/*     */ 
/*     */   private Map<String, List<OrgUnit>> searchTree(String searchValue) {
/* 120 */     Map map = null;
/* 121 */     List orgUnits = new ArrayList();
/* 122 */     orgUnits.addAll(this.orgUnitManager.search("name like '%" + searchValue + "%'"));
/* 123 */     if (orgUnits.size() > 0) {
/* 124 */       map = new HashMap();
/* 125 */       for (OrgUnit orgUnit : orgUnits) {
/* 126 */         setDN(map, orgUnit);
/*     */       }
/*     */     }
/* 129 */     return map;
/*     */   }
/*     */ 
/*     */   public String getSearchJson(String searchValue) {
/* 133 */     Map map = searchTree(searchValue);
/* 134 */     StringWriter sw = new StringWriter();
/*     */     try {
/* 136 */       JSONWriter json = new JSONWriter(sw);
/* 137 */       json.array();
/* 138 */       if ((map != null) && (map.size() > 0)) {
/* 139 */         List roots = (List)map.get("root");
/* 140 */         for (OrgUnit orgUnit : roots) {
/* 141 */           makeJson(map, json, orgUnit);
/*     */         }
/*     */       }
/* 144 */       json.endArray();
/*     */     } catch (JSONException e) {
/* 146 */       log.error("生成json数据报错!", e);
/*     */     }
/* 148 */     return sw.toString();
/*     */   }
/*     */ 
/*     */   private void makeJson(Map<String, List<OrgUnit>> map, JSONWriter json, OrgUnit orgUnit) {
/*     */     try {
/* 153 */       String type = orgUnit.getOrgType();
/* 154 */       String scope = "Organization,Department,Person";
/* 155 */       if ((scope == null) || (scope.contains(type))) {
/* 156 */         json.object();
/* 157 */         getOrgUnitJSON(orgUnit, json, false);
/* 158 */         List list = (List)map.get(orgUnit.getUID());
/* 159 */         if (list != null) {
/* 160 */           json.key("expanded").value(true);
/* 161 */           json.key("children").array();
/* 162 */           for (OrgUnit sub : list) {
/* 163 */             makeJson(map, json, sub);
/*     */           }
/* 165 */           json.endArray();
/*     */         }
/* 167 */         json.endObject();
/*     */       }
/*     */     } catch (JSONException e) {
/* 170 */       log.error("生成json数据报错!", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void setDN(Map<String, List<OrgUnit>> map, OrgUnit orgUnit) {
/* 175 */     OrgUnit parent = this.orgUnitManager.getParent(orgUnit);
/* 176 */     if (parent != null) {
/* 177 */       String parentUID = parent.getUID();
/* 178 */       List list = (List)map.get(parentUID);
/* 179 */       if (list != null) {
/* 180 */         if (!list.contains(orgUnit))
/* 181 */           list.add(orgUnit);
/*     */       }
/*     */       else {
/* 184 */         list = new ArrayList();
/* 185 */         list.add(orgUnit);
/* 186 */         map.put(parentUID, list);
/*     */       }
/* 188 */       setDN(map, parent);
/*     */     } else {
/* 190 */       List list = (List)map.get("root");
/* 191 */       if (list == null) {
/* 192 */         list = new ArrayList();
/* 193 */         list.add(orgUnit);
/* 194 */         map.put("root", list);
/*     */       }
/* 196 */       else if (!list.contains(orgUnit)) {
/* 197 */         list.add(orgUnit);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.util.OrgTreeUtil
 * JD-Core Version:    0.6.1
 */