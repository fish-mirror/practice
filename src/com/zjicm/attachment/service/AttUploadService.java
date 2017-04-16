package com.zjicm.attachment.service;

import com.zjicm.attachment.dao.AttachmentDao;
import com.zjicm.attachment.domain.Attachment;
import com.zjicm.attachment.enums.AttEnums;
import com.zjicm.common.lang.http.util.WebUtil;
import com.zjicm.common.lang.io.ExecUtil;
import com.zjicm.common.lang.io.FileUtil;
import com.zjicm.common.lang.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文件上传处理
 * <p>
 * Created by yujing on 2017/4/15.
 */
@Component
public class AttUploadService {

    private static String publicPath;

    private static AttachmentDao attachmentDao;


    @Value("${file.upload.base}/public")
    public void setPublicPath(String publicPath) {
        this.publicPath = publicPath;
    }

    @Autowired
    public void setAttachmentService(AttachmentDao attachmentService) {
        this.attachmentDao = attachmentService;
    }

    /**
     * 传进上传对象.和上传地址.返回一个附件对象.附件的URL和大小已经set好了.
     * <p/>
     *
     * @param upload      FileItem 对象.
     * @param uploadPath
     * @param isPub
     * @param allowRepeat
     * @return 返回一个附件的访问地址.
     */
    public static Attachment uploadAttachment(MultipartFile upload,
                                              String uploadPath,
                                              boolean isPub,
                                              boolean allowRepeat) {
        if (upload == null || StringUtils.isBlank(uploadPath)) return null;

        try {
            if (!FileUtil.Extension.isAllowed(upload.getBytes())) {
                return null;
            }

            Attachment att = new Attachment();
            att.setOriginalPath(new SimpleDateFormat("yyyy/MM/dd/mm").format(new Date()));
            att.setUid(StringUtil.random(8).toLowerCase());
            att.setPub(isPub);
            att.setSize((int) upload.getSize());

            String filename = upload.getOriginalFilename();
            String extension = FileUtil.getExtension(filename);
            att.setExt(extension);
            att.setMime(WebUtil.getContentType(extension));
            att.setOriginalName(filename);
            att.setName(att.getUid() + "." + extension);
            att.setPath(att.getOriginalPath() + "/" + att.getUid() + "." + extension);

            String md5 = FileUtil.getMD5(upload.getBytes());
            att.setMd5(md5);

            if (!allowRepeat) {
                // 不允许重复，则使用md5进行对比判断
                Attachment attachment = attachmentDao.getByMd5(md5);
                if (attachment != null) {
                    // 只在同一私有级别比较
                    // 考虑情况：上传公共附件，该附件在私有附件目录下已存在
                    // 此公共附件仍可以上传
                    if (attachment.isPub() == isPub) {
                        return attachment;
                    }
                }
            }

            // 文件保存
            File pub = new File(uploadPath, att.getPath());
            File dir = pub.getParentFile();
            if (dir.exists() || dir.mkdirs()) {
                upload.transferTo(pub);
            }

            if (FileUtil.Extension.isEmbedable(filename)) { // 可嵌入的资源文件处理，得到长宽信息
                att.setEmbed(true);
                if (FileUtil.Extension.isImage(filename)) { // 图片长宽处理

                    // 根据图片 exif 方向信息自动修正图片方向
                    ExecUtil.autoOrient(pub.getAbsolutePath(), pub.getAbsolutePath());

                    BufferedImage bi = ImageIO.read(pub);
                    att.setWidth(bi.getWidth());
                    att.setHeight(bi.getHeight());

                    // 原始大小调整处理
                    int width = att.getWidth();
                    int height = att.getHeight();
                    int maxWidth = 800;
                    if (att.getObjectType() == AttEnums.Type.avatar.getValue()) {
                        maxWidth = 200;
                    }
                    if (width > maxWidth) {
                        height = (int) (((float) maxWidth / width) * height);
                        width = maxWidth;
                    }
                    ExecUtil.makeSnap(pub.getAbsolutePath(), pub.getAbsolutePath(), width, height);
                }
            } else if (FileUtil.Extension.isStreamingMedia(filename)) {
                StringBuilder info = ExecUtil.getFileInfo(pub.getAbsolutePath());
                att.setInfo(info != null ? info.toString() : "");
            }
            return att;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件上传
     *
     * @param files     MultipartFile
     * @param attType   附件类型
     * @param isPrivate 是否私有
     * @param userId    用户ID
     */
    public List<Attachment> upload(MultipartFile[] files,
                                   AttEnums.Type attType,
                                   boolean isPrivate,
                                   int userId) {
        Attachment att;
        String filePath;
        switch (attType) {
            case avatar:
            case news_picture:
                filePath = publicPath + "/image";
                break;
            default:
                filePath = publicPath;
                break;
        }
        List<Attachment> atts = new ArrayList<>();
        for (MultipartFile file : files) {
            Attachment tmpAtt = uploadAttachment(file,
                                                 filePath,
                                                 !isPrivate,
                                                 false);
            if (tmpAtt == null) continue;

            att = new Attachment();
            att.setId(tmpAtt.getId());
            att.setPath(tmpAtt.getPath());
            att.setEmbed(tmpAtt.isEmbed());
            att.setExt(tmpAtt.getExt());
            att.setHeight(tmpAtt.getHeight());
            att.setSize(tmpAtt.getSize());
            att.setMd5(tmpAtt.getMd5());
            att.setName(tmpAtt.getName());
            att.setObjectId(tmpAtt.getObjectId());
            att.setPub(!isPrivate);
            att.setUid(tmpAtt.getUid());
            att.setWidth(tmpAtt.getWidth());
            att.setObjectType(attType.getValue());
            att.setInfo(tmpAtt.getInfo());

            att.setUserId(userId);
            att.setCreateTime(new Date());
            att.setModifyTime(new Date());

            att.setMime(tmpAtt.getMime());
            att.setCropImg(tmpAtt.getCropImg());
            att.setOriginalName(tmpAtt.getOriginalName());
            att.setOriginalPath(tmpAtt.getOriginalPath());

            if (att.getId() == null || att.getId() == 0) {
                attachmentDao.save(att);
            }

            atts.add(att);
        }

        return atts;
    }

}
