package com.zjicm.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zjicm.service.IImportService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

/*
 * 网页上传excel文件
 * 解析并在数据库中插入数据
 * 注意读取流程的耦合和易配置程度
 */
public class ImportShortTermSelectedAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	String info;
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

	//上传文件的参数格式
	private File xls;
    private String xlsFileName;
    private String xlsContentType;
	public File getXls() {
		return xls;
	}
	public void setXls(File xls) {
		this.xls = xls;
	}
	public String getXlsFileName() {
		return xlsFileName;
	}
	public void setXlsFileName(String xlsFileName) {
		this.xlsFileName = xlsFileName;
	}
	public String getXlsContentType() {
		return xlsContentType;
	}
	public void setXlsContentType(String xlsContentType) {
		this.xlsContentType = xlsContentType;
	}

	@Autowired
	IImportService importService;
	
   
	public String execute() throws Exception{
		HttpSession session = request.getSession();
		String institute = (String)session.getAttribute("institute");
		
        //创建输入流
        InputStream is = new FileInputStream(xls);
        //创建父目录
        String root = ServletActionContext.getServletContext().getRealPath("/WEB-INF/shortTermSelected");
        if (!new File(root).exists()) { // 如果路径不存在，创建  
        	new File(root).mkdirs();
        }  
        
        //创建输出流以及目的文件
        System.out.println("XlsFileName:"+xlsFileName);
        String suffix = xlsFileName.substring(xlsFileName.lastIndexOf('.'));
        System.out.println("suffix:"+suffix);
        String pre = Long.valueOf(new Date().getTime()).toString();
        String saveFileName = pre+suffix;
        System.out.println(saveFileName);
        OutputStream os = new FileOutputStream(new File(root,saveFileName));
        System.out.println("fileFileName: " + xlsFileName);
      
        byte[] buffer = new byte[1024];
        int length = 0;
        
        while(-1 != (length = is.read(buffer)))
        {
            os.write(buffer,0,length);
        }
        
        os.close();
        is.close();
        info = importService.insertShortTermSelected(xls,institute);
        
        if(info==null){
        	return ERROR;
        }else {
			return SUCCESS;
		}
        
    }
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}


