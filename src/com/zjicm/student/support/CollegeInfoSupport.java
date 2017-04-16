package com.zjicm.student.support;

import com.zjicm.common.beans.KeyValue;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.util.*;

/**
 * 学院相关信息
 * <p>
 * Created by yujing on 2017/4/12.
 */
public class CollegeInfoSupport {
    private static Map<Integer, String> INSTITUTE_MAP = new HashMap<>();
    private static Map<String, String> MAJOR_MAP = new HashMap<>();
    private static Map<Integer, String> INSTITUTE_MAJOR_MAP = new HashMap<>();
    private static Map<String, String> ALIAS_MAP = new HashMap<>();


    static {

        INSTITUTE_MAP.put(1, "播音主持艺术学院");
        INSTITUTE_MAP.put(2, "电视艺术学院");
        INSTITUTE_MAP.put(3, "电影学院");
        INSTITUTE_MAP.put(4, "电子信息学院");
        INSTITUTE_MAP.put(5, "动画学院");
        INSTITUTE_MAP.put(6, "管理学院");
        INSTITUTE_MAP.put(7, "国际文化传播学院");
        INSTITUTE_MAP.put(8, "设计艺术学院");
        INSTITUTE_MAP.put(9, "文化创意学院");
        INSTITUTE_MAP.put(10, "文学院");
        INSTITUTE_MAP.put(11, "新媒体学院");
        INSTITUTE_MAP.put(12, "新闻学院");
        INSTITUTE_MAP.put(13, "音乐学院");

        INSTITUTE_MAJOR_MAP.put(1, "0301,0308,0307");
        INSTITUTE_MAJOR_MAP.put(2, "0407,0409,4903,4901,4902,0507");
        INSTITUTE_MAJOR_MAP.put(3, "4904,0417,0000");
        INSTITUTE_MAJOR_MAP.put(4, "0701,0705,0706,0710");
        INSTITUTE_MAJOR_MAP.put(5, "0205,0208,0000");
        INSTITUTE_MAJOR_MAP.put(6, "0905,0000,0908");
        INSTITUTE_MAJOR_MAP.put(7, "1101,1106,1107,0000,1105");
        INSTITUTE_MAJOR_MAP.put(8, "3906,3903,3904,0000,0209,0210,3905");
        INSTITUTE_MAJOR_MAP.put(9, "4201,0907,0306,42020");
        INSTITUTE_MAJOR_MAP.put(10, "0608,4302,0112,1111,4301,0000");
        INSTITUTE_MAJOR_MAP.put(11, "0207,0708,0806");
        INSTITUTE_MAJOR_MAP.put(12, "4102,0108,0104,4101,0000");
        INSTITUTE_MAJOR_MAP.put(13, "1002,1004,1005,0000");


        MAJOR_MAP.put("0301", "播音与主持艺术专业");
        MAJOR_MAP.put("0308", "播音与主持艺术专业（影视配音）");
        MAJOR_MAP.put("0307", "播音与主持艺术专业（礼仪文化）");

        MAJOR_MAP.put("0407", "广播电视编导");
        MAJOR_MAP.put("0409", "广播电视编导（文艺编导）");
        MAJOR_MAP.put("4903", "影视摄影与制作（电视节目制作）");
        MAJOR_MAP.put("4901", "影视摄影与制作（电视摄像）");
        MAJOR_MAP.put("4902", "影视摄影与制作（照明艺术）");
        MAJOR_MAP.put("0507", "影视摄影与制作（录音艺术）");

        MAJOR_MAP.put("4904", "影视摄影与制作（电影制作）");
        MAJOR_MAP.put("0417", "表演");
        MAJOR_MAP.put("0000", "戏剧影视导演");     //

        MAJOR_MAP.put("0701", "广播电视工程");
        MAJOR_MAP.put("0705", "电子信息工程");
        MAJOR_MAP.put("0706", "电子科学与技术");
        MAJOR_MAP.put("0710", "通信工程");

        MAJOR_MAP.put("0205", "动画");
        MAJOR_MAP.put("0000", "动画（漫插画）");  //
        MAJOR_MAP.put("0208", "数字媒体艺术");

        MAJOR_MAP.put("0905", "文化产业管理");
        MAJOR_MAP.put("0000", "经济学");          //
        MAJOR_MAP.put("0908", "会展经济与管理");

        MAJOR_MAP.put("1101", "播音与主持艺术专业（英汉双语播音）");
        MAJOR_MAP.put("1106", "播音与主持艺术专业（法汉双语播音）");
        MAJOR_MAP.put("1107", "播音与主持艺术专业（日汉双语播音）");
        MAJOR_MAP.put("0000", "播音与主持艺术专业（双语播音）");  //
        MAJOR_MAP.put("1105", "英语");

        MAJOR_MAP.put("3906", "摄影");
        MAJOR_MAP.put("3903", "视觉传达设计");
        MAJOR_MAP.put("3904", "环境设计");
        MAJOR_MAP.put("0000", "产品设计");         //
        MAJOR_MAP.put("0209", "戏剧影视美术设计");
        MAJOR_MAP.put("0210", "戏剧影视美术设计（人物形象）");
        MAJOR_MAP.put("3905", "服装与服饰设计");

        MAJOR_MAP.put("0708", "网络工程");
        MAJOR_MAP.put("0806", "信息管理与信息系统");
        MAJOR_MAP.put("0207", "数字媒体技术");

        MAJOR_MAP.put("4102", "广播电视学");
        MAJOR_MAP.put("0108", "新闻学");
        MAJOR_MAP.put("0104", "编辑出版学");
        MAJOR_MAP.put("4101", "传播学");
        MAJOR_MAP.put("0000", "数字出版学");     //

        MAJOR_MAP.put("4201", "广播电视编导（媒体创意）");
        MAJOR_MAP.put("0907", "广告学");
        MAJOR_MAP.put("0306", "公共关系学");
        MAJOR_MAP.put("4202", "网络与新媒体");

        MAJOR_MAP.put("0608", "戏剧影视文学");
        MAJOR_MAP.put("4302", "戏剧影视文学（编剧与策划）");
        MAJOR_MAP.put("0112", "汉语言文学");
        MAJOR_MAP.put("1111", "汉语言文学（涉外文秘）");
        MAJOR_MAP.put("4301", "汉语国际教育");
        MAJOR_MAP.put("0000", "秘书学");

        MAJOR_MAP.put("1002", "音乐表演");
        MAJOR_MAP.put("1004", "舞蹈编导");
        MAJOR_MAP.put("1005", "舞蹈编导（音乐剧）");
        MAJOR_MAP.put("0000", "艺术与科技");


        ALIAS_MAP.put("播音与主持艺术专业", "0301");
        ALIAS_MAP.put("播本", "0301");
        ALIAS_MAP.put("播音", "0301");
        ALIAS_MAP.put("播音与主持艺术专业（影视配音）", "0308");
        ALIAS_MAP.put("配音", "0308");
        ALIAS_MAP.put("播音与主持艺术专业（礼仪文化）", "0307");
        ALIAS_MAP.put("礼仪", "0307");

        ALIAS_MAP.put("广播电视编导", "0407");
        ALIAS_MAP.put("电编", "0407");
        ALIAS_MAP.put("广播电视编导（文艺编导）", "0409");
        ALIAS_MAP.put("文编", "0409");
        ALIAS_MAP.put("影视摄影与制作（电视节目制作）", "4903");
        ALIAS_MAP.put("电制", "4903");
        ALIAS_MAP.put("电视节目制作", "4903");
        ALIAS_MAP.put("影视摄影与制作（电视摄像）", "4901");
        ALIAS_MAP.put("摄像", "4901");
        ALIAS_MAP.put("电摄", "4901");
        ALIAS_MAP.put("影视摄影与制作（照明艺术）", "4902");
        ALIAS_MAP.put("灯光", "4902");
        ALIAS_MAP.put("影视摄影与制作（录音艺术）", "0507");
        ALIAS_MAP.put("录音", "0507");

        ALIAS_MAP.put("影视摄影与制作（电影制作）", "4904");
        ALIAS_MAP.put("电影", "4904");
        ALIAS_MAP.put("电影制作", "4904");
        ALIAS_MAP.put("表演", "0417");
        ALIAS_MAP.put("戏剧影视导演", "0000");
        ALIAS_MAP.put("导演", "0000");

        ALIAS_MAP.put("广播电视工程", "0701");
        ALIAS_MAP.put("广电工程", "0701");
        ALIAS_MAP.put("电子信息工程", "0705");
        ALIAS_MAP.put("电信", "0705");
        ALIAS_MAP.put("电子科学与技术", "0706");
        ALIAS_MAP.put("电科", "0706");
        ALIAS_MAP.put("通信工程", "0710");
        ALIAS_MAP.put("通信", "0710");


        ALIAS_MAP.put("动画", "0205");
        ALIAS_MAP.put("动画（漫插画）", "0000");
        ALIAS_MAP.put("漫插", "0000");
        ALIAS_MAP.put("数字媒体艺术", "0208");
        ALIAS_MAP.put("数艺", "0208");

        ALIAS_MAP.put("文化产业管理", "0905");
        ALIAS_MAP.put("文产", "0905");
        ALIAS_MAP.put("经济学", "0000");
        ALIAS_MAP.put("经济", "0000");
        ALIAS_MAP.put("会展经济与管理", "0908");
        ALIAS_MAP.put("会展", "0908");

        ALIAS_MAP.put("播音与主持艺术专业（英汉双语播音）", "1101");
        ALIAS_MAP.put("英汉双语", "1101");
        ALIAS_MAP.put("英汉", "1101");
        ALIAS_MAP.put("播音与主持艺术专业（法汉双语播音）", "1106");
        ALIAS_MAP.put("法汉双语", "1106");
        ALIAS_MAP.put("法汉", "1106");
        ALIAS_MAP.put("播音与主持艺术专业（日汉双语播音）", "1107");
        ALIAS_MAP.put("日汉双语", "1107");
        ALIAS_MAP.put("日汉", "1107");

        ALIAS_MAP.put("播音与主持艺术专业（双语播音）", "0000");
        ALIAS_MAP.put("双语", "0000");
        ALIAS_MAP.put("英语", "1105");

        ALIAS_MAP.put("摄影", "3906");
        ALIAS_MAP.put("视觉传达设计", "3903");
        ALIAS_MAP.put("视传", "3903");
        ALIAS_MAP.put("环境设计", "3904");
        ALIAS_MAP.put("环设", "3904");
        ALIAS_MAP.put("产品设计", "0000");
        ALIAS_MAP.put("产设", "0000");
        ALIAS_MAP.put("戏剧影视美术设计", "0209");
        ALIAS_MAP.put("戏美", "0209");
        ALIAS_MAP.put("戏剧影视美术设计（人物形象）", "0210");
        ALIAS_MAP.put("形设", "0210");
        ALIAS_MAP.put("服装与服饰设计", "3905");
        ALIAS_MAP.put("服设", "3905");

        ALIAS_MAP.put("网络工程", "0708");
        ALIAS_MAP.put("网工", "0708");
        ALIAS_MAP.put("信息管理与信息系统", "0806");
        ALIAS_MAP.put("信管", "0806");
        ALIAS_MAP.put("数字媒体技术", "0207");
        ALIAS_MAP.put("数技", "0207");

        ALIAS_MAP.put("广播电视学", "4102");
        ALIAS_MAP.put("广电新闻", "4102");
        ALIAS_MAP.put("广电", "4102");
        ALIAS_MAP.put("新闻学", "0108");
        ALIAS_MAP.put("新闻", "0108");
        ALIAS_MAP.put("编辑出版学", "0104");
        ALIAS_MAP.put("编辑", "0104");
        ALIAS_MAP.put("传播学", "4101");
        ALIAS_MAP.put("传播", "4101");
        ALIAS_MAP.put("数字出版学", "0000");

        ALIAS_MAP.put("广播电视编导（媒体创意）", "4201");
        ALIAS_MAP.put("媒创", "4201");
        ALIAS_MAP.put("广告学", "0907");
        ALIAS_MAP.put("广告", "0907");
        ALIAS_MAP.put("公共关系学", "0306");
        ALIAS_MAP.put("公关", "0306");
        ALIAS_MAP.put("网络与新媒体", "4202");
        ALIAS_MAP.put("网新", "4202");

        ALIAS_MAP.put("戏剧影视文学", "0608");
        ALIAS_MAP.put("戏文", "0608");
        ALIAS_MAP.put("戏剧影视文学（编剧与策划）", "4302");
        ALIAS_MAP.put("编剧", "4302");
        ALIAS_MAP.put("汉语言文学", "0112");
        ALIAS_MAP.put("汉文", "0112");
        ALIAS_MAP.put("汉语言文学（涉外文秘）", "1111");
        ALIAS_MAP.put("文秘", "1111");
        ALIAS_MAP.put("汉语国际教育", "4301");
        ALIAS_MAP.put("汉教", "4301");
        ALIAS_MAP.put("秘书学", "0000");
        ALIAS_MAP.put("秘书", "0000");

        ALIAS_MAP.put("音乐表演", "1002");
        ALIAS_MAP.put("音表", "1002");
        ALIAS_MAP.put("舞蹈编导", "1004");
        ALIAS_MAP.put("舞编", "1004");
        ALIAS_MAP.put("舞蹈编导（音乐剧）", "1005");
        ALIAS_MAP.put("音乐剧", "1005");
        ALIAS_MAP.put("艺术与科技", "0000");


    }

    /**
     * 获取学院名称
     *
     * @param id
     * @return
     */
    public static String getInstitute(int id) {
        if (id > 0 && INSTITUTE_MAP.containsKey(id)) return INSTITUTE_MAP.get(id);
        return null;
    }

    /**
     * 获取专业名称
     *
     * @param id
     * @return
     */
    public static String getMajor(String id) {
        if (StringUtils.isNotBlank(id) && MAJOR_MAP.containsKey(id)) return MAJOR_MAP.get(id);
        return null;
    }

    /**
     * 获取专业编号
     *
     * @param major
     * @return
     */
    public static String getMajorCode(String major) {
        if (StringUtils.isNotBlank(major) && ALIAS_MAP.containsKey(major)) return MAJOR_MAP.get(major);
        return null;
    }

    /**
     * 从班级名获取班级序号
     *
     * @param classname
     * @return
     */
    public static int getClassIndex(String classname) {
        if (StringUtils.isBlank(classname)) return 0;
        if (classname.lastIndexOf('1') > 2) return 1;
        if (classname.lastIndexOf('2') > 2) return 2;
        if (classname.lastIndexOf('3') > 2) return 3;
        return 0;
    }

    /**
     * 获取学院的专业列表
     *
     * @param instituteId
     * @return
     */
    public static List getMajorList(int instituteId) {
        if (instituteId < 0 || !INSTITUTE_MAJOR_MAP.containsKey(instituteId)) return null;

        String str = INSTITUTE_MAJOR_MAP.get(instituteId);
        if (StringUtils.isBlank(str)) return null;

        String majors[] = StringUtils.split(str, ",");
        if (ArrayUtils.isEmpty(majors)) return null;

        List majorList = new ArrayList();

        for (String major : majors) {
            String info = getMajor(major);
            if (StringUtils.isBlank(info)) continue;
            majorList.add(new KeyValue<>(major, info));
        }
        return majorList;
    }


    /**
     * 解析学号获取班级名称
     *
     * @param number
     * @return
     */
    public static String getClassName(String number) {
        if (StringUtils.isBlank(number)) return null;
        if (number.length() != 9 && !NumberUtils.isNumber(number)) return null;
        String grade = number.substring(0, 2);
        String majorCode = number.substring(2, 6);
        int classCode = Integer.valueOf(number.substring(6, 7));

        return getClassName(grade, majorCode, classCode);
    }

    /**
     * 拼装班级名称
     *
     * @param grade
     * @param majorCode
     * @param classCode
     * @return
     */
    public static String getClassName(String grade, String majorCode, int classCode) {
        if (StringUtils.isBlank(grade) && classCode < 1 && StringUtils.isBlank(getMajor(majorCode))) return null;
        return grade + getMajor(majorCode) + classCode + "班";
    }

    /**
     * 获取当前时间的毕业班年级
     *
     * @return
     */
    public static int getGraduatingGrade() {
        Calendar calendar = Calendar.getInstance();
        // 下半年时，毕业班为明年毕业的班级
        if (calendar.get(Calendar.MONTH) > Calendar.JUNE) return calendar.get(Calendar.YEAR) - 2003;

        return calendar.get(Calendar.YEAR) - 2004;
    }

}
