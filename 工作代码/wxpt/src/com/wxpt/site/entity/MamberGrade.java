package com.wxpt.site.entity;

/**
 * MamberGrade entity. @author MyEclipse Persistence Tools
 */

public class MamberGrade implements java.io.Serializable {

	// Fields

	private Integer memberGradeId;
	private String memberGradeName;
	private Integer gradeJifen;
	private String gradeImage;

	// Constructors

	/** default constructor */
	public MamberGrade() {
	}

	/** full constructor */
	public MamberGrade(String memberGradeName, Integer gradeJifen,
			String gradeImage) {
		this.memberGradeName = memberGradeName;
		this.gradeJifen = gradeJifen;
		this.gradeImage = gradeImage;
	}

	// Property accessors

	public Integer getMemberGradeId() {
		return this.memberGradeId;
	}

	public void setMemberGradeId(Integer memberGradeId) {
		this.memberGradeId = memberGradeId;
	}

	public String getMemberGradeName() {
		return this.memberGradeName;
	}

	public void setMemberGradeName(String memberGradeName) {
		this.memberGradeName = memberGradeName;
	}

	public Integer getGradeJifen() {
		return this.gradeJifen;
	}

	public void setGradeJifen(Integer gradeJifen) {
		this.gradeJifen = gradeJifen;
	}

	public String getGradeImage() {
		return this.gradeImage;
	}

	public void setGradeImage(String gradeImage) {
		this.gradeImage = gradeImage;
	}

}