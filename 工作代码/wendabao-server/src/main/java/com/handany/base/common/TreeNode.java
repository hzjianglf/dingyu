package com.handany.base.common;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class TreeNode<T extends TreeElement> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7251150967828372883L;

	private T element;
	
	private TreeNode<T> parent;
	
	private List<String> path = new ArrayList<String>();
	
	private List<TreeNode<T>> subNodes = new ArrayList<TreeNode<T>>();
	
	public List<String> getPath(){
		return this.path;
	}
	
	public boolean isLeaf(){
		return subNodes.size()==0;
	}
	
	public void addChild(TreeNode<T> node){
		node.appendPath(path);
		subNodes.add(node);
	}

	public void appendPath(List<String> parentPath){
		this.path.addAll(parentPath);
	}
	
	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
		path.add(element.getId());
	}

	public TreeNode<T> parent() {
		return parent;
	}

	public void setParent(TreeNode<T> parent) {
		this.parent = parent;
	}

	public List<TreeNode<T>> getSubNodes() {
		return subNodes;
	}

	public void setSubNodes(List<TreeNode<T>> subNodes) {
		this.subNodes = subNodes;
	}


}
