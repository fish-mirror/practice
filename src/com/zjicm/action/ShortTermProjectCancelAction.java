package com.zjicm.action;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zjicm.shortterm.service.IShortTermService;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

public class ShortTermProjectCancelAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	private IShortTermService sts;
	private Integer rid;
	private String result;
	
	@Override
	public String execute() throws Exception {
		try{
			HttpSession session = request.getSession();
			String institute = (String)session.getAttribute("institute");
			String major = (String)session.getAttribute("major");
			String stuId = (String)session.getAttribute("ID");
			int year = new Date().getYear()-100;
			String term = "20"+(year-2)+"-20"+(year-1)+"学年第二学期";
			
			boolean flag = sts.cancelSelectedProject(rid, stuId, term, major);
			if(flag){
				result = "success";
			}else{
				result = "fail";
			}
		}catch(Exception e){
			e.printStackTrace();
			result = "error";
		}
		
		return SUCCESS;
	}
	@JSON(serialize=false)
	public IShortTermService getSts() {
		return sts;
	}
	@Resource(name="shortTermService")
	public void setSts(IShortTermService sts) {
		this.sts = sts;
	}
	
	
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	
}
