package com.zjicm.service;

import java.io.File;
import java.io.FileInputStream;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.zjicm.dao.StudentDao;
import com.zjicm.dao.UserDao;
import com.zjicm.entity.Student;
import com.zjicm.entity.User;
import com.zjicm.util.DateUtils;
import com.zjicm.util.StringFormatUtils;

public interface IImportService {
	
	
	public String insertStu(File file);
	public String insertShortTermSelected(File file,String institute);
}
