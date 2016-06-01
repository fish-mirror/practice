package com.zjicm.entity;

// default package

public class Cooperate  implements java.io.Serializable {


    // Fields    
	private Integer id;
    private String institute;
    private String comId;
     


    // Constructors

    /** default constructor */
    public Cooperate() {
    }
    public Cooperate(Integer id) {
		this.id = id;
	}
	
    /** full constructor */

    public Cooperate(String institute, String comId) {
		this.institute = institute;
		this.comId = comId;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	public String getComId() {
		return comId;
	}


	public void setComId(String comId) {
		this.comId = comId;
	}


   
    // Property accessors
    
}