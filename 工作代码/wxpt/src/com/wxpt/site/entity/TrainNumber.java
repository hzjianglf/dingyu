package com.wxpt.site.entity;

/**
 * TrainNumber entity. @author MyEclipse Persistence Tools
 */

public class TrainNumber implements java.io.Serializable {

	// Fields

	private Integer trainNumberId;
	private String trainNumberNo;
	private String trainNumberName;
	private String trainNumberDai;
	private String trainNumberZhong;
	private String trainNumberJian;
	private String trainNumberQuhao;

	// Constructors

	/** default constructor */
	public TrainNumber() {
	}

	/** full constructor */
	public TrainNumber(String trainNumberNo, String trainNumberName,
			String trainNumberDai, String trainNumberZhong,
			String trainNumberJian, String trainNumberQuhao) {
		this.trainNumberNo = trainNumberNo;
		this.trainNumberName = trainNumberName;
		this.trainNumberDai = trainNumberDai;
		this.trainNumberZhong = trainNumberZhong;
		this.trainNumberJian = trainNumberJian;
		this.trainNumberQuhao = trainNumberQuhao;
	}

	// Property accessors

	public Integer getTrainNumberId() {
		return this.trainNumberId;
	}

	public void setTrainNumberId(Integer trainNumberId) {
		this.trainNumberId = trainNumberId;
	}

	public String getTrainNumberNo() {
		return this.trainNumberNo;
	}

	public void setTrainNumberNo(String trainNumberNo) {
		this.trainNumberNo = trainNumberNo;
	}

	public String getTrainNumberName() {
		return this.trainNumberName;
	}

	public void setTrainNumberName(String trainNumberName) {
		this.trainNumberName = trainNumberName;
	}

	public String getTrainNumberDai() {
		return this.trainNumberDai;
	}

	public void setTrainNumberDai(String trainNumberDai) {
		this.trainNumberDai = trainNumberDai;
	}

	public String getTrainNumberZhong() {
		return this.trainNumberZhong;
	}

	public void setTrainNumberZhong(String trainNumberZhong) {
		this.trainNumberZhong = trainNumberZhong;
	}

	public String getTrainNumberJian() {
		return this.trainNumberJian;
	}

	public void setTrainNumberJian(String trainNumberJian) {
		this.trainNumberJian = trainNumberJian;
	}

	public String getTrainNumberQuhao() {
		return this.trainNumberQuhao;
	}

	public void setTrainNumberQuhao(String trainNumberQuhao) {
		this.trainNumberQuhao = trainNumberQuhao;
	}

}