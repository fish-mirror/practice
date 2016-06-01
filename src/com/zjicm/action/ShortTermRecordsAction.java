package com.zjicm.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.entity.ShortTermReport;
import com.zjicm.service.IShortTermService;

public class ShortTermRecordsAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	private IShortTermService sts;
	private List<ShortTermReport> strs;
	
	@Override
	public String execute() throws Exception {
		String stuId = (String) request.getSession().getAttribute("ID");
		strs = sts.getReportList(stuId);
		
		return SUCCESS;
	}
	

	public List<ShortTermReport> getStrs() {
		return strs;
	}


	public void setStrs(List<ShortTermReport> strs) {
		this.strs = strs;
	}





	@JSON(serialize=false)
	public IShortTermService getSts() {
		return sts;
	}
	@Resource(name="shortTermService")
	public void setSts(IShortTermService sts) {
		this.sts = sts;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	
}
