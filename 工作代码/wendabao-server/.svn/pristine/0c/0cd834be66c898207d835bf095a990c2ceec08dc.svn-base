package com.handany.base.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 带缓存的树形结构
 * @author lhb
 *
 * @param <T>
 */
public class CachedTree<T extends TreeElement> implements Serializable{

	
	private static Logger logger = LoggerFactory.getLogger(CachedTree.class);
	
	private String treeName;
	
	public CachedTree(String treeName){
		this.treeName = treeName;
	}
	
	
	private TreeNode<T> root = new TreeNode<T>();
	
	
	
	
	public TreeNode<T> getTree() {
		return root;
	}




	/**
	 * 初始化缓存树
	 * @param list
	 */
	public TreeNode<T> init(List<T> list){
		
		Map<String ,List<String>> childrens = new HashMap<String ,List<String>>();
		//树节点映射
		Map<String,TreeNode<T>> treeNodeMap = new HashMap<String,TreeNode<T>>();
		
		
		treeNodeMap.put("0",root);
		
		
		 for(T t:list){
			
			//CacheContainer.put(treeName+":childMap:"+t.getId(), t);
			 
			 String parentId = t.getParentId(); 
			
			 parentId = parentId==null?"0":parentId;
			
			List<String> childList = childrens.get(parentId);
			
			if(childList == null){
				childList = new ArrayList<String>();
				childrens.put(parentId, childList);
				childList.add(t.getId());
			}
			
			
			TreeNode<T> node = new TreeNode<T>();
			node.setElement(t);			
			treeNodeMap.put(t.getId(), node);
			
	
			

			TreeNode<T> parentNode = treeNodeMap.get(parentId);
			
			if(parentNode != null){
				parentNode.addChild(node);
				node.setParent(parentNode);
			}
				
			
		}
		 
		 //根节点缓存，根节点包含整个树的所有信息
//		 CacheContainer.put(treeName+":treeRootNode", root);
//		 //节点映射缓存，根据id直接找到一个具体节点
//		 CacheContainer.put(treeName+":treeNodeMap", treeNodeMap);
//		 //直接子节点缓存，根据id返回直接子节点的集合
//		 CacheContainer.put(treeName+":childrens", childrens);
		
			return root;
	
	}
	
	
	

	
}
