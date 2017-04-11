package com.zjicm.common.lang.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * 下载输出
 * <p/>
 * User: yuanjq
 * Date: 15/10/29
 * Time: 下午9:29
 */
public class XlsOut {

    /**
     * 输出下载
     *
     * @param request
     * @param response
     * @param hSSFWorkbook
     * @param downloadFileName
     */
    public static void download(HttpServletRequest request,
                                HttpServletResponse response,
                                HSSFWorkbook hSSFWorkbook,
                                String downloadFileName) {
        try {
            OutputStream out = null;
            try {
                String agent = request.getHeader("User-Agent").toString();
                if (agent.indexOf("IE") != -1) {
                    response.reset(); // 设置缓存为空，这样ie6下才可以下载
                    response.addHeader("Content-Disposition",
                                       "attachment;filename=" +
                                       URLEncoder.encode(downloadFileName, "UTF-8"));// 使ie文件名下不会乱码
                } else {
                    response.addHeader("Content-Disposition",
                                       "attachment; filename=\"" +
                                       new String((downloadFileName).getBytes("UTF-8"), "iso-8859-1") + "\"");
                }
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                out = response.getOutputStream();
                hSSFWorkbook.write(out);
                out.flush();
            } catch (IOException e) {
            } finally {
                try {
                    if (out != null) out.close();
                } catch (IOException e) {
                }
            }
        } catch (Exception e) {
        }
    }
}
