package com.zjicm.service;

import java.io.File;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

public interface IImportService {
	
	
	public String insertStu(File file);
	public String insertShortTermSelected(File file,String institute);
}
