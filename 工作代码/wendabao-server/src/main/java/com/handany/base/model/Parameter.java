package com.handany.base.model;


import com.handany.base.common.TreeElement;

public class Parameter implements TreeElement{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3467935079162231020L;
	private String id;
	private String parentId;
	private String classId;
	private String code;
	private String name;
	private String briefName;
	private String valid;
	private int displayOrder;
	private ParameterClass paramClass;
	
	

	public ParameterClass paramClass() {
		return paramClass;
	}

	public void setParamClass(ParameterClass paramClass) {
		this.paramClass = paramClass;
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		
		if(parentId != null){
			parentId = parentId.trim();
		}
		
		this.parentId = parentId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		
		if(classId != null){
			classId = classId.trim();
		}
		
		this.classId = classId;
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

	public String getBriefName() {
		return briefName;
	}

	public void setBriefName(String briefName) {
		this.briefName = briefName;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

}
