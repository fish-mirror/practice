package com.zjicm.student.service;

import java.io.File;
import java.io.FileInputStream;

import com.zjicm.shortterm.dao.ShortTermProjectDao;
import com.zjicm.shortterm.dao.ShortTermReportDao;
import com.zjicm.entity.Student;
import com.zjicm.entity.User;
import com.zjicm.shortterm.service.ShortTermService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.zjicm.student.dao.StudentDao;
import com.zjicm.auth.dao.UserDao;
import com.zjicm.entity.ShortTermProject;

@Component
public class ImportService implements IImportService {


    @Autowired
    private UserDao userDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ShortTermProjectDao shortTermProjectDao;
    @Autowired
    private ShortTermReportDao shortTermReportDao;
    @Autowired
    private ShortTermService shortTermService;

    /*
     * 获取excel文件数据，插入数据库
     */
    @Override
    public String insertStu(File file) {
        StringBuilder info = new StringBuilder("success");
        User u;
        try {
            //文件流指向excel文件
            FileInputStream fin = new FileInputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook(fin);//创建工作薄
            int length = workbook.getNumberOfSheets();//得到工作表数
            for (int i = 0; i < length; i++) {
                HSSFSheet sheet = workbook.getSheetAt(i);
                int totalRow = sheet.getLastRowNum();//得到excel的总记录条数

                for (int rowNum = 2; rowNum < totalRow; rowNum++) {
                    HSSFRow row = sheet.getRow(rowNum);
                    if (row == null) {
                        // This whole row is empty
                        // Handle it as needed
                        continue;
                    }
                    /*处理一行记录*/
                    Student dataStu = new Student();
                    //学号
                    HSSFCell cell = row.getCell(2);
                    if (cell == null) {
                        continue;
                    }
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    String str = cell.getStringCellValue();

                    User dataUser = new User(str, str, (short) 2);

                    
                   /* 
                    dataStu.setName(row.getCell(1).getStringCellValue());
                    dataStu.setSex(row.getCell(2).getStringCellValue());
                    dataStu.setInstitute(row.getCell(3).getStringCellValue());
                    dataStu.setMajor(row.getCell(4).getStringCellValue());
                    dataStu.setClassname(row.getCell(5).getStringCellValue());
                    dataStu.setTel(row.getCell(6).getStringCellValue());
                    dataStu.setBirth(DateUtils.timeStrToDate(row.getCell(7).getStringCellValue()));
                    dataStu.setPolitics(row.getCell(8).getStringCellValue());
                    */


                    dataStu.setId(cell.getStringCellValue());
                    dataStu.setName(row.getCell(3).getStringCellValue());
                    dataStu.setSex(row.getCell(4).getStringCellValue());
                    dataStu.setClassname(row.getCell(1).getStringCellValue());
                    dataStu.setImgUrl("/practice/image/default-head.png");
                    System.out.println(dataStu);
                    if (dataStu.getId() == null || dataStu.getId().equals("")) {
                        if (info.indexOf("success") != -1) {
                            info = new StringBuilder(dataStu.getName() + "添加失败！学号为空！<br>");
                        }
                        info.append(dataStu.getName() + "添加失败！学号为空！<br>");
                    } else {
                        userDao.save(dataUser);
                        studentDao.save(dataStu);
                    }


                }
            }


        } catch (DuplicateKeyException e) {
            info = new StringBuilder("请检查学号不重复！");
            e.printStackTrace();
        } catch (Exception e) {
            info = new StringBuilder("添加失败！<br>请检查文件格式是否正确，是否重复导入学生信息！");
            e.printStackTrace();
        }

        return info.toString();
    }

    @Override
    public String insertShortTermSelected(File file, String institute) {
        StringBuilder info = new StringBuilder("success");
        try {
            //文件流指向excel文件
            FileInputStream fin = new FileInputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook(fin);//创建工作薄
            int length = workbook.getNumberOfSheets();//得到工作表数
            for (int i = 0; i < length; i++) {
                HSSFSheet sheet = workbook.getSheetAt(i);
                int totalRow = sheet.getLastRowNum();//得到excel的总记录条数

                for (int rowNum = 2; rowNum < totalRow; rowNum++) {
                    HSSFRow row = sheet.getRow(rowNum);
                    if (row == null) {
                        // This whole row is empty
                        // Handle it as needed
                        continue;
                    }
                    /*处理一行记录*/

                    //学号
                    HSSFCell cell = row.getCell(3);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    String stuId = cell.getStringCellValue();
                    //专业
                    String major = row.getCell(1).getStringCellValue();
                    User u = userDao.getById(stuId);
                    if (u == null) {
                        User user = new User(stuId, stuId, (short) 2);
                        userDao.save(user);

                        Student stu = new Student(stuId);
                        stu.setName(row.getCell(4).getStringCellValue());
                        stu.setClassname(row.getCell(2).getStringCellValue());
                        stu.setImgUrl("/practice/image/default-head.png");
                        studentDao.save(stu);
                    }
                    //项目序号
                    cell = row.getCell(5);
                    String pname = cell.getStringCellValue();
                    ShortTermProject stp = shortTermProjectDao.getId(pname);


                    if (stuId == null || stuId.equals("")) {
                        if (info.indexOf("success") != -1) {
                            info = new StringBuilder("添加失败！信息不全：学号：" + stuId + " 项目名 ：" + pname + "<br>");
                        }
                        info.append("添加失败！信息不全：学号：" + stuId + " 项目名 ：" + pname + "<br>");
                    } else {


                        String result = shortTermService.addSelectProject(stp.getId(), stuId, major, institute, null);
                        result = stuId + " " + stp.getName() + result;
                        System.out.println(result);
                        if (info.indexOf("success") != -1) {
                            info = new StringBuilder(result);
                        }
                        info.append(result);

                    }


                }
            }


        } catch (DuplicateKeyException e) {
            info = new StringBuilder("请检查选课记录不重复！");
            e.printStackTrace();
        } catch (Exception e) {
            info = new StringBuilder("添加失败！<br>请检查文件格式是否正确，是否重复导入选课信息！");
            e.printStackTrace();
        }

        return info.toString();
    }

}
