package com.wxpt.site.entity.pojo;

import java.util.List;

import com.wxpt.site.entity.Suroption;
import com.wxpt.site.entity.Surquestion;

public class QuestionPojo {
	private Surquestion surquestion;
	public Surquestion getSurquestion() {
		return surquestion;
	}
	public void setSurquestion(Surquestion surquestion) {
		this.surquestion = surquestion;
	}
	public List<Suroption> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<Suroption> optionList) {
		this.optionList = optionList;
	}
	private List<Suroption>optionList;
	
}
