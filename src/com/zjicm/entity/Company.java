package com.zjicm.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Company entity. @author MyEclipse Persistence Tools
 */

public class Company implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String type;
	private String location;
	private String address;
	private String tel;
	private String linkman;

	// Constructors

	/** default constructor */
	public Company() {
	}
	
	public Company(String id) {
		this.id = id;
	}
	/** minimal constructor */
	public Company(String id, String name) {
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public Company(String name, String type, String location,
			String address, String tel, String linkman) {
		this.name = name;
		this.type = type;
		this.location = location;
		this.address = address;
		this.tel = tel;
		this.linkman = linkman;
	}
	public Company(String id,String name, String type, String location,
			String address, String tel, String linkman) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.location = location;
		this.address = address;
		this.tel = tel;
		this.linkman = linkman;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getLinkman() {
		return this.linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

}