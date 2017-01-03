package com.zjicm.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.zjicm.entity.Company;
import com.zjicm.entity.ShortTermProject;
import com.zjicm.service.IShortTermService;
import com.zjicm.util.MyStringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.ActionSupport;

public class ShortTermProjectAddAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;
	private IShortTermService sts;
	@Override
	public String execute() throws Exception {
		ShortTermProject stp = null;
		Company company = null;
		Integer pid = null;
		String temp = request.getParameter("pid");
		if(temp != null && !temp.equals("")){
			pid = Integer.parseInt(temp);
		}
		
		String term = request.getParameter("term");
		String com = request.getParameter("com");
		String name = request.getParameter("name");
		String majorNeed = request.getParameter("majorNeed");
		String gradeNeed = request.getParameter("gradeNeed");
		String topNum = request.getParameter("topNum");
		String unmajorNum = request.getParameter("unmajorNum");
		String purpose = request.getParameter("purpose");
		String content = request.getParameter("content");
		String time = request.getParameter("time");
		String place = request.getParameter("place");
		String institute = (String) request.getSession().getAttribute("institute");
		
		if(com != null && !com.equals("")){
			company = new Company(com);
		}
		if(pid == null ||pid == 1){
			stp = new ShortTermProject(pid, company, name, institute, 
					purpose, content, majorNeed,gradeNeed, null, null, 
					term, place, time, (short) 0);
			if(MyStringUtils.isNumeric(topNum)){
				stp.setTopNum(Integer.valueOf(topNum));
			}
			if(MyStringUtils.isNumeric(unmajorNum)){
				stp.setUnmajorNum(Integer.valueOf(unmajorNum));
			}
			sts.addProject(stp);
		}else{
			stp = sts.getProject(pid);
			stp.setTerm(term);
			stp.setCompany(company);
			stp.setContent(content);
			stp.setInstitute(institute);
			stp.setMajorNeed(majorNeed);
			stp.setGradeNeed(gradeNeed);
			stp.setName(name);
			stp.setPlace(place);
			stp.setTime(time);
			stp.setPurpose(purpose);
			if(MyStringUtils.isNumeric(topNum)){
				stp.setTopNum(Integer.valueOf(topNum));
			}
			if(MyStringUtils.isNumeric(unmajorNum)){
				stp.setUnmajorNum(Integer.valueOf(unmajorNum));
			}
			sts.updateProject(stp);
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
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	
}
