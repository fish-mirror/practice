package com.zjicm.action;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;
import com.zjicm.service.IShortTermService;

public class ShortTermProjectSelectAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	private IShortTermService sts;
	private Integer pid;
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
			if(sts.getProjectSelected(stuId, term)!=null){
				result = "该学期已选择项目！请勿重复选。";
				return SUCCESS;
			}
			
			result = sts.addSelectProject(pid, stuId, major,institute,term);
			
			
		}catch(Exception e){
			e.printStackTrace();
			result = "选择失败，请重试。";
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
	
	
	@JSON(serialize=false)
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
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
