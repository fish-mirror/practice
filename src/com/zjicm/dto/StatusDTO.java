package com.zjicm.dto;

public class StatusDTO {

	private int no_practice;
	private int practicing;
	private int practiced;
	private int practice_graded;
	
	public StatusDTO() {
		super();
	}
	
	public StatusDTO(int no_practice, int practicing, int practiced,
			int practice_graded) {
		super();
		this.no_practice = no_practice;
		this.practicing = practicing;
		this.practiced = practiced;
		this.practice_graded = practice_graded;
	}

	public int getNo_practice() {
		return no_practice;
	}
	public void setNo_practice(int no_practice) {
		this.no_practice = no_practice;
	}
	public int getPracticing() {
		return practicing;
	}
	public void setPracticing(int practicing) {
		this.practicing = practicing;
	}
	public int getPracticed() {
		return practiced;
	}
	public void setPracticed(int practiced) {
		this.practiced = practiced;
	}
	public int getPractice_graded() {
		return practice_graded;
	}
	public void setPractice_graded(int practice_graded) {
		this.practice_graded = practice_graded;
	}
	
	
}
