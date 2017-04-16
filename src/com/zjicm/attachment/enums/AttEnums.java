package com.zjicm.attachment.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件附件枚举集合
 * <p/>
 * Created by yujing on 2017/4/15.
 */
public class AttEnums {

    /**
     * 文件附件类型
     */
    public enum Type {
        avatar(1),                                         // 证件照片
        word(2),                                           // word 文件
        excel(3),                                          // EXCEL 文件
        analytic_excel(4),                                 // 数据解析的: EXCEL 文件

        news_picture(5);                                   // 资讯图片文件


        private int value;

        public int getValue() {
            return value;
        }

        Type(int v) {
            this.value = v;
        }

        /**
         * 获取 列表
         *
         * @return
         */
        public static List<Type> getList() {
            List<Type> list = new ArrayList<>();
            for (Type type : Type.values()) {
                list.add(type);
            }
            return list;
        }

        /**
         * 获取特定类型
         *
         * @param value 值信息
         * @return
         */
        public static Type is(int value) {
            for (Type type : Type.values()) {
                if (type.getValue() == value) return type;
            }
            return null;
        }
    }
}
