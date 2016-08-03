/*    */ package net.risesoft.soa.filecube.model;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class Dimension
/*    */   implements Comparable<Dimension>
/*    */ {
/*    */   private String id;
/*    */   private String text;
/*    */   private int depth;
/*    */   private boolean leaf;
/*    */   private List<Dimension> children;
/*    */   private String columnName;
/*    */ 
/*    */   public Dimension(String id, String text, int depth)
/*    */   {
/* 31 */     this.id = id;
/* 32 */     this.text = text;
/* 33 */     this.depth = depth;
/*    */   }
/*    */   public Dimension() {
/*    */   }
/*    */ 
/*    */   public String getId() {
/* 39 */     return this.id;
/*    */   }
/*    */   public void setId(String id) {
/* 42 */     this.id = id;
/*    */   }
/*    */   public String getText() {
/* 45 */     return this.text;
/*    */   }
/*    */   public void setText(String text) {
/* 48 */     this.text = text;
/*    */   }
/*    */   public int getDepth() {
/* 51 */     return this.depth;
/*    */   }
/*    */   public void setDepth(int depth) {
/* 54 */     this.depth = depth;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 58 */     return "{id:\"" + this.id + "\", text:\"" + this.text + "\", depth:\"" + this.depth + 
/* 59 */       "\"}";
/*    */   }
/*    */   public List<Dimension> getChildren() {
/* 62 */     return this.children;
/*    */   }
/*    */   public void setChildren(List<Dimension> children) {
/* 65 */     this.children = children;
/*    */   }
/*    */ 
/*    */   public int compareTo(Dimension o) {
/* 69 */     return getDepth() - o.getDepth();
/*    */   }
/*    */   public boolean isLeaf() {
/* 72 */     return this.leaf;
/*    */   }
/*    */   public void setLeaf(boolean leaf) {
/* 75 */     this.leaf = leaf;
/*    */   }
/*    */   public String getColumnName() {
/* 78 */     return this.columnName;
/*    */   }
/*    */   public void setColumnName(String columnName) {
/* 81 */     this.columnName = columnName;
/*    */   }
/*    */ }

/* Location:           D:\鲁美达工作\鲁美达桌面\需要修改的jar包\net.risesoft.soa.filecube_1.0.0.201408201001\
 * Qualified Name:     net.risesoft.soa.filecube.model.Dimension
 * JD-Core Version:    0.6.1
 */