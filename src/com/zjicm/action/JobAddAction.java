package com.zjicm.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zjicm.entity.Company;
import com.zjicm.entity.Job;
import com.zjicm.service.IJobService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

public class JobAddAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private IJobService js;
	private Integer id;
	private String info;
	
	@Override
	public String execute() throws Exception {
		String name = request.getParameter("name");
		String desc = request.getParameter("desc");
		String need = request.getParameter("need");
		String time = request.getParameter("time");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String place = request.getParameter("place");
		String needNum = request.getParameter("need_num");
		try{
			if(id!=null){
				Job j = js.get(id);
				j.setName(name);
				j.setDesc(desc);
				j.setNeed(need);
				j.setTime(time);
				j.setProvince(province);
				j.setCity(city);
				j.setPlace(place);
				j.setNeedNum(Integer.valueOf(needNum));
				
				js.update(j);
			}else{
				Job j = new Job();
				j.setName(name);
				j.setDesc(desc);
				j.setNeed(need);
				j.setTime(time);
				j.setProvince(province);
				j.setCity(city);
				j.setPlace(place);
				j.setNeedNum(Integer.valueOf(needNum));
				j.setHaveNum(0);
				j.setStatus((short)0);
				String uid = (String) request.getSession().getAttribute("ID");
				j.setCompany(new Company(uid));
				js.add(j);
			}
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			info = "保存失败！请重试。";
			return ERROR;
		}
	}
	
	
	

	
	public IJobService getJs() {
		return js;
	}
	@Resource
	public void setJs(IJobService js) {
		this.js = js;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getInfo() {
		return info;
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
