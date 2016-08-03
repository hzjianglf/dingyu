/*     */ package net.risesoft.soa.filecube.service.impl;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.persistence.EntityManager;
/*     */ import javax.persistence.EntityManagerFactory;
/*     */ import javax.persistence.Query;
/*     */ import net.risesoft.soa.filecube.model.Dimension;
/*     */ import net.risesoft.soa.filecube.model.FileInfo;
/*     */ import net.risesoft.soa.filecube.service.DimensionService;
/*     */ import net.risesoft.soa.filecube.util.FileType;
/*     */ import net.risesoft.soa.filecube.util.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Transactional
/*     */ @Service("dimensionService")
/*     */ public class DimensionServiceImpl
/*     */   implements DimensionService
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private EntityManagerFactory emf;
/*     */ 
/*     */   public List<String> getDimension(String Id)
/*     */   {
/*  32 */     EntityManager em = this.emf.createEntityManager();
/*  33 */     Query query = em.createQuery("select f." + Id + 
/*  34 */       " from FileInfo f " + 
/*  35 */       " where f." + Id + 
/*  36 */       " is not null  GROUP BY f." + Id);
/*  37 */     return query.getResultList();
/*     */   }
/*     */ 
/*     */   public List<Dimension> getDimensionTree(List<Dimension> dimensions) {
/*  41 */     Collections.sort(dimensions);
/*  42 */     if (dimensions.isEmpty()) return null;
/*     */ 
/*  44 */     Dimension root = new Dimension("", "Root", 0);
/*  45 */     root.setChildren(new ArrayList());
/*     */ 
/*  47 */     int depth = 1;
/*     */ 
/*  50 */     for (int i = 0; i < dimensions.size(); i++) {
/*  51 */       Dimension dimension = (Dimension)dimensions.get(i);
/*  52 */       List tmp = new ArrayList();
/*  53 */       depth++;
/*  54 */       boolean leaf = false;
/*  55 */       if (i == dimensions.size() - 1) {
/*  56 */         leaf = true;
/*     */       }
/*  58 */       for (String text : getDimension(dimension.getId())) {
/*  59 */         Dimension dim = new Dimension();
/*     */ 
/*  61 */         String type = (String)FileType.TypesMap.get(text);
/*  62 */         if (type != null)
/*  63 */           dim.setText(type);
/*     */         else {
/*  65 */           dim.setText(text);
/*     */         }
/*  67 */         dim.setDepth(depth);
/*  68 */         dim.setLeaf(leaf);
/*  69 */         dim.setColumnName(dimension.getId());
/*  70 */         dim.setChildren(new ArrayList());
/*  71 */         tmp.add(dim);
/*     */       }
/*  73 */       dimensionTree(root, tmp);
/*     */     }
/*  75 */     return root.getChildren();
/*     */   }
/*     */ 
/*     */   private void dimensionTree(Dimension parent, List<Dimension> childDims)
/*     */   {
/*  86 */     List tmps = parent.getChildren();
/*  87 */     if (tmps.equals(childDims)) {
/*  88 */       return;
/*     */     }
/*  90 */     if (!tmps.isEmpty()) {
/*  91 */       for (Dimension dimension : tmps)
/*  92 */         dimensionTree(dimension, childDims);
/*     */     }
/*     */     else
/*  95 */       parent.setChildren(childDims);
/*     */   }
/*     */ 
/*     */   public Map<String, Object> getDimensionFiles(String dimensionSql, int start, int rows)
/*     */   {
/* 102 */     EntityManager em = this.emf.createEntityManager();
/*     */     Query query;
/*     */     Query query;
/* 104 */     if ((dimensionSql == null) || ("".equals(dimensionSql)))
/* 105 */       query = em.createQuery(" from FileInfo ");
/*     */     else {
/* 107 */       query = em.createQuery(" from FileInfo where " + dimensionSql);
/*     */     }
/* 109 */     int totalCount = query.getResultList().size();
/* 110 */     query.setFirstResult(start);
/* 111 */     query.setMaxResults(rows);
/* 112 */     Map rtnDate = new HashMap();
/* 113 */     rtnDate.put("totalCount", Integer.valueOf(totalCount));
/* 114 */     List fileInfos = query.getResultList();
/* 115 */     for (FileInfo fileInfo : fileInfos) {
/* 116 */       fileInfo.setName(StringUtils.getShortStr(fileInfo.getName(), 30));
/*     */     }
/* 118 */     rtnDate.put("datas", fileInfos);
/* 119 */     return rtnDate;
/*     */   }
/*     */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.service.impl.DimensionServiceImpl
 * JD-Core Version:    0.6.1
 */