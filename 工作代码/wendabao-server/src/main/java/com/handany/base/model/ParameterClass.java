package com.handany.base.model;


import java.io.Serializable;

public class ParameterClass implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7355372739276743236L;
	
	private String id;
	private String code;
	private String name;
	private String type;
	private String tree;

	public String getTree() {
		return tree;
	}

	public void setTree(String tree) {
		this.tree = tree;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(id != null){
			id = id.trim();
		}
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
