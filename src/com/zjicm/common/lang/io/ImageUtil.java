package com.zjicm.common.lang.io;

import com.zjicm.common.lang.character.CharacterNatures;
import com.zjicm.common.lang.consts.StringConsts;
import com.zjicm.common.lang.util.ByteUtil;
import com.zjicm.common.lang.util.RuntimeUtil;
import com.zjicm.common.lang.util.StringUtil;
import org.apache.commons.lang.math.NumberUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ImageUtil {
    public static final String BBS_IMG_PREFIX = "http://img.dxycdn.com/upload";
    public static final Pattern BBS_IMG_PATTERN = Pattern.compile(
            BBS_IMG_PREFIX + "(/[0-9]{4}/[0-9]{2}/[0-9]{2}/[0-9/]+\\.[a-z]{3,4})");

    public static boolean isImage(String image) {
        return FileUtil.Extension.isImage(image);
    }

    public static String extractFirstImageUrlFromText(String text, Pattern pattern, int group) {
        if (StringUtil.isNotEmpty(text) && pattern != null) {
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                return group > 0 ? matcher.group(group) : matcher.group();
            }
        }

        return StringConsts.EMPTY;
    }

    public static Collection<String> extractAllImageUrlFromText(String text, Pattern pattern, int group) {
        if (StringUtil.isNotEmpty(text) && pattern != null) {
            List<String> urls = new ArrayList<String>(5);
            Matcher matcher = pattern.matcher(text);
            int index = 0;
            while (matcher.find(index)) {
                urls.add(group > 0 ? matcher.group(group) : matcher.group());
                index = matcher.end();
            }
            return urls;
        }

        return Collections.emptyList();
    }

    public static boolean checkImage(Path path) {
        if (path == null) {
            return false;
        }
        String fileName = path.getFileName().toString();
        if (StringUtil.isNotEmpty(fileName) && fileName.endsWith("dcm")) {
            InputStream inputStream = null;
            BufferedInputStream bis = null;
            try {
                inputStream = new FileInputStream(path.toFile());
                bis = new BufferedInputStream(inputStream);
                bis.mark(132);
                byte[] a = new byte[132];
                if (bis.read(a) == 132) {
                    bis.reset();
                    String test = new String(a, "UTF-8");
                    return ("\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                            "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                            "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                            "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                            "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                            "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                            "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                            "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                            "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                            "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000DICM").equals(test);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        try {
            BufferedImage img = ImageIO.read(path.toUri().toURL());
            return !(img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isImageMagickSafe(File file) {
        if (file != null && file.exists()) {
            String type = getImageRealFormat(file);
            return ("jpg".equals(type) || "png".equals(type) || "gif".equals(type)) && checkImage(file.toPath());
        }
        return false;
    }

    public static String urlToHtml(String imageSrc) {
        if (StringUtil.isNotEmpty(imageSrc)) {
            return "<img src=\"" + imageSrc + "\"/>";
        }
        return StringConsts.EMPTY;
    }

    public static String getImageRealFormat(File file) {
        if (file != null && file.exists()) {
            byte[] b = new byte[4];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                fis.read(b, 0, b.length);
                String type = ByteUtil.toHex(b).toUpperCase();
                if (type.contains("FFD8FF")) {
                    return "jpg";
                } else if (type.contains("89504E47")) {
                    return "png";
                } else if (type.contains("47494638")) {
                    return "gif";
                } else if (type.contains("424D")) {
                    return "bmp";
                } else {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    bis.mark(132);
                    byte[] a = new byte[132];
                    if (bis.read(a) == 132) {
                        bis.reset();
                        String test = new String(a, "UTF-8");
                        if (("\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                             "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                             "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                             "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                             "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                             "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                             "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                             "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                             "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000" +
                             "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000DICM").equals(test)) {
                            return "dcm";
                        }
                    }
                    return StringConsts.UNKOWN;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    public static boolean thumbnail(String sourcePath, String targetPath, int imageWidth, int imageHeight) {
        if (!checkImage(new File(sourcePath).toPath())) {
            return false;
        }
        if (imageWidth > 0 && imageHeight > 0 && StringUtil.isNotEmpty(targetPath, sourcePath)) {
            return StringConsts.SUCCESS.equals(RuntimeUtil.exec(new String[]{"/usr/bin/convert",
                                                                             sourcePath + "[0]",
                                                                             "-thumbnail",
                                                                             imageWidth + "x" + imageHeight + ">",
                                                                             targetPath}, 30));
        }
        return false;
    }

    public static boolean applyTextWatermark(File input, File output, String text) {
        return applyTextWatermark(input, output, text, 48, 0.08f, Color.decode("#3E3E3E"), 315);
    }

    public static boolean applyTextWatermark(File input, File output, String text, int fontSize) {
        return applyTextWatermark(input, output, text, fontSize, 0.08f, Color.decode("#3E3E3E"), 315);
    }

    public static boolean applyTextWatermark(File input,
                                             File output,
                                             String text,
                                             int fontSize,
                                             float alpha,
                                             Color color,
                                             int degree) {
        try {
            Image image = new ImageIcon(input.getAbsolutePath()).getImage();
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            if (width > 0 && height > 0) {
                BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = bufferedImage.createGraphics();
                graphics.setColor(Color.gray);
                graphics.setBackground(Color.white);
                graphics.drawImage(image, 0, 0, null);
                AttributedCharacterIterator acIter = buildWatermark(text,
                                                                    CharacterNatures.hasChineseCharacter(text)
                                                                            ? "宋体"
                                                                            : "arial",
                                                                    fontSize);
                graphics.setColor(color);
                //graphics.setStroke(new BasicStroke(1,10,10));
                graphics.rotate(Math.toRadians(degree),
                                (double) bufferedImage.getWidth() / 2,
                                (double) bufferedImage.getHeight() / 2);
                graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
                int rows = (height / fontSize / 2);
                int cols = (int) (((double) width / fontSize / (text.length() + 1)) * 1.4f) + 2;
                if (rows > 1 && cols > 1) {
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            graphics.drawString(acIter,
                                                (int) (width - j * fontSize * (text.length() + 1)),
                                                (int) (height - (height * i / (rows - 1))));
                        }
                    }
                }
                graphics.dispose();// 画笔结束
                ImageIO.write(bufferedImage, "JPG", output);
                return true;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Dimension getDimension(String path) {
        if (StringUtil.isNotEmpty(path)) {
            StringBuilder buff = new StringBuilder();
            RuntimeUtil.exec(new String[]{"/usr/bin/identify", path}, null, buff, 3);
            if (buff.length() > 0) {
                String info = buff.toString();
                String[] tokens = info.split("\\s+");
                if (tokens.length >= 3) {
                    String token = tokens[2];
                    if (StringUtil.isNotEmpty(token)) {
                        int idx = token.indexOf('x');
                        if (idx != -1) {
                            Dimension dimension = new Dimension();
                            dimension.setSize(NumberUtils.toInt(token.substring(0, idx)),
                                              NumberUtils.toInt(token.substring(idx + 1)));
                            return dimension;
                        }
                    }
                }
            }
        }
        return new Dimension(0, 0);
    }

    private static AttributedCharacterIterator buildWatermark(String text, String fontType, int fontSize) {
        AttributedString ats = new AttributedString(text);
        Font f = new Font(fontType, Font.PLAIN, fontSize);
        ats.addAttribute(TextAttribute.FONT, f, 0, text.length());
        AttributedCharacterIterator iter = ats.getIterator();
        return iter;
    }

    public static boolean mosaic(File file, File mosaic, int denominator) throws Exception {
        if (file != null && file.exists() && file.isFile()) {
            BufferedImage bi = ImageIO.read(file); // 读取该图片
            BufferedImage spinImage = new BufferedImage(bi.getWidth(),
                                                        bi.getHeight(), bi.TYPE_INT_RGB);
            final int size = Math.min(bi.getWidth(), bi.getHeight()) / Math.max(2, denominator);
            int xcount = 0; // 方向绘制个数
            int ycount = 0; // y方向绘制个数
            if (bi.getWidth() % size == 0) {
                xcount = bi.getWidth() / size;
            } else {
                xcount = bi.getWidth() / size + 1;
            }
            if (bi.getHeight() % size == 0) {
                ycount = bi.getHeight() / size;
            } else {
                ycount = bi.getHeight() / size + 1;
            }
            int x = 0;   //坐标
            int y = 0;
            // 绘制马赛克(绘制矩形并填充颜色)
            Graphics gs = spinImage.getGraphics();
            for (int i = 0; i < xcount; i++) {
                for (int j = 0; j < ycount; j++) {
                    //马赛克矩形格大小
                    int mwidth = size;
                    int mheight = size;
                    if (i == xcount - 1) {   //横向最后一个比较特殊，可能不够一个size
                        mwidth = bi.getWidth() - x;
                    }
                    if (j == ycount - 1) {  //同理
                        mheight = bi.getHeight() - y;
                    }
                    // 矩形颜色取中心像素点RGB值
                    int centerX = x;
                    int centerY = y;
                    if (mwidth % 2 == 0) {
                        centerX += mwidth / 2;
                    } else {
                        centerX += (mwidth - 1) / 2;
                    }
                    if (mheight % 2 == 0) {
                        centerY += mheight / 2;
                    } else {
                        centerY += (mheight - 1) / 2;
                    }
                    Color color = new Color(bi.getRGB(centerX, centerY));
                    gs.setColor(color);
                    gs.fillRect(x, y, mwidth, mheight);
                    y = y + size;// 计算下一个矩形的y坐标
                }
                y = 0;// 还原y坐标
                x = x + size;// 计算x坐标
            }
            gs.dispose();
            ImageIO.write(spinImage, "jpg", mosaic); // 保存图片
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(getImageRealFormat(new File("/Users/fenglong/Downloads/20090706211803287.bmp")));
    }
}