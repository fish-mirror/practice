package com.zjicm.common.lang.excel;

import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XlsWrite {

	private HSSFWorkbook workbook;
	private HSSFSheet sheet;

	public XlsWrite() {
		this.workbook = new HSSFWorkbook();
	}

	public void createNewSheet(String name) {
		this.sheet = workbook.createSheet(name);
	}

	public void createNewSheet() {
		this.sheet = workbook.createSheet();
	}

	public HSSFSheet getSheet() {
		return sheet;
	}

	public void setSheet(HSSFSheet sheet) {
		this.sheet = sheet;
	}

	public HSSFWorkbook getWorkbook() {
		return workbook;
	}

	public void setLineValue(List<String> values, short rowNumber, boolean isTitle) {
		HSSFRow row = this.sheet.createRow(rowNumber);
		int i = 0;
		HSSFCell cell;
		for (String value : values) {
			cell = row.createCell( i);
			if (isTitle) {
				HSSFCellStyle cellStyle = this.workbook.createCellStyle();
				HSSFFont font = this.workbook.createFont();
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				cellStyle.setFont(font);
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cell.setCellStyle(cellStyle);
			}
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(value);
			i++;
		}
	}

	public void writeXls(String filePath) throws IOException {
		FileOutputStream fOut = new FileOutputStream(filePath);
		this.workbook.write(fOut);
		fOut.flush();
		fOut.close();
	}

	public static void main(String[] args) throws IOException {
		XlsWrite xlsWrite = new XlsWrite();
		List<String> titleList = new ArrayList<String>();
		titleList.add("1");
		titleList.add("2");
		titleList.add("3");
		titleList.add("4");
		for (int i = 0; i < 10000; i++) {
			if (i % 1000 == 0) {
				xlsWrite.createNewSheet();
				xlsWrite.setLineValue(titleList, (short)0, true);
			}
			List<String> values = new ArrayList<String>();
			values.add("111111111");
			values.add("222222222");
			values.add("333333333");
			values.add("444444444");
			xlsWrite.setLineValue(values, (short)(i % 1000 + 1), false);
		}
		xlsWrite.writeXls("/var/www/biomart/test.xls");
	}

}
