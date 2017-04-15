package com.zjicm.common.lang.util;

import com.zjicm.common.lang.consts.StringConsts;
import com.zjicm.common.lang.io.CloseUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;

public final class ObjectUtil {
    public static <T> T nullToDefault(T value, T def) {
        return value == null ? def : value;
    }

    /**
     * 判断obj不为null
     * @param objs
     * @return
     */
    public static boolean isNotNull(Object... objs) {
        if (ArrayUtils.isNotEmpty(objs)) {
            for (Object obj : objs) {
                if (obj == null) {
                    return false;
                }
            }

            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromFile(File file) {
        if (file != null && file.exists()) {
            ObjectInputStream oi = null;
            FileInputStream fi = null;
            try {
                fi = new FileInputStream(file);
                oi = new ObjectInputStream(fi);
                return (T) oi.readObject();
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                CloseUtil.closeQuiet(oi);
                CloseUtil.closeQuiet(fi);
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromMap(Class<T> clazz, Map<String, Object> map) {
        if (clazz != null && map != null) {
            try {
                Object object = clazz.newInstance();
                for (Entry<String, Object> entry : map.entrySet()) {
                    try {
                        Field field = clazz.getDeclaredField(entry.getKey());
                        if (field != null) {
                            field.setAccessible(true);
                            field.set(object, entry.getValue());
                        }
                    } catch (Throwable e) {
                    }
                }
                return (T) object;
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromBytes(byte[] bytes) {
        if (bytes != null && bytes.length > 0) {
            ObjectInputStream oi = null;
            ByteArrayInputStream bi = null;
            try {
                bi = new ByteArrayInputStream(bytes);
                oi = new ObjectInputStream(bi);
                return (T) oi.readObject();
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                CloseUtil.closeQuiet(oi);
                CloseUtil.closeQuiet(bi);
            }
        }

        return null;
    }

    public static <T> T fromString(String str) {
        if (str != null && str.length() > 0) {
            return fromBytes(Base64Util.decodeFast(str));
        }

        return null;
    }

    public static void toFile(Object obj, File file) {
        if (obj != null) {
            FileOutputStream bo = null;
            ObjectOutputStream oo = null;
            try {
                bo = new FileOutputStream(file);
                oo = new ObjectOutputStream(bo);
                oo.writeObject(obj);
                oo.flush();
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                CloseUtil.closeQuiet(oo);
                CloseUtil.closeQuiet(bo);
            }
        }
    }

    public static String toString(Object obj) {
        if (obj != null) {
            byte[] bytes = toBytes(obj);
            if (ArrayUtils.isNotEmpty(bytes)) {
                return new String(Base64.encodeBase64String(bytes));
            }
        }
        return StringConsts.EMPTY;
    }

    public static byte[] toBytes(Object obj) {
        if (obj != null) {
            ByteArrayOutputStream bo = null;
            ObjectOutputStream oo = null;
            try {
                bo = new ByteArrayOutputStream();
                oo = new ObjectOutputStream(bo);
                oo.writeObject(obj);
                oo.flush();
                return bo.toByteArray();
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(oo);
                IOUtils.closeQuietly(bo);
            }
        }
        return null;
    }

    public static <T> T toObject(byte[] bytes){
        if(ArrayUtils.isNotEmpty(bytes)){
            ObjectInputStream oi = null;
            try{
                oi = new ObjectInputStream(new ByteArrayInputStream(bytes));
                return (T)oi.readObject();
            }
            catch(Throwable e){
                e.printStackTrace();
            }
            finally{
                IOUtils.closeQuietly(oi);
            }
        }

        return null;
    }

    public static <T> T toObject(String str){
        if(StringUtils.isNotEmpty(str)){
            return toObject(Base64.decodeBase64(str));
        }

        return null;
    }
    public static <T> T toObject(Class<T> clazz,Map<?,?> map){
        if(clazz != null && MapUtils.isNotEmpty(map)){
            T obj = (T) BeanUtils.instantiateClass(clazz);
            if(obj != null){
                BeanWrapper beanWrapper = new BeanWrapperImpl(obj);
                beanWrapper.setPropertyValues(map);
                return obj;
            }
        }

        return null;
    }

    public static boolean hasField(Class<?> clazz, String name) {
        if (clazz != null && StringUtils.isNotEmpty(name)) {
            try {
                return clazz.getDeclaredField(name) != null;
            } catch (Throwable e) {
            }
        }
        return false;
    }

    public static Object getFieldValue(Object object, String name) {
        if (object != null && StringUtils.isNotEmpty(name)) {
            try {
                Field field = getClassField(object.getClass(), name);
                if (field != null) {
                    field.setAccessible(true);
                    return field.get(object);
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static boolean setFieldValue(Object object, String name, Object value) {
        if (object != null && StringUtils.isNotEmpty(name)) {
            try {
                Field field = getClassField(object.getClass(), name);
                if (field != null) {
                    field.setAccessible(true);
                    Object old = field.get(object);
                    if (old == null || !old.equals(value)) {
                        field.set(object, value);
                        return true;
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static Field getClassField(Class<?> clazz, String name) {
        Field[] fields = clazz.getDeclaredFields();
        if (ArrayUtils.isNotEmpty(fields)) {
            for (Field field : fields) {
                if (field.getName().equals(name)) {
                    return field;// define in this class
                }
            }
        }

        clazz = clazz.getSuperclass();
        if (clazz != null) {
            return getClassField(clazz, name);
        }
        return null;
    }


    public static <T> T copy(T dist, Object orig, Predicate<String> predicate, Class<?>... classes) {
        if (dist != null && orig != null && ArrayUtils.isNotEmpty(classes)) {
            for (Class<?> clazz : classes) {
                if (clazz != null) {
                    Field[] fields = clazz.getDeclaredFields();
                    if (ArrayUtils.isNotEmpty(fields)) {
                        for (Field field : fields) {
                            if (field != null && !Modifier.isStatic(field.getModifiers())) {
                                try {
                                    if (predicate == null || predicate.test(field.getName())) {
                                        Field of = getClassField(orig.getClass(), field.getName());
                                        if (of != null) {
                                            Field df = getClassField(dist.getClass(), field.getName());
                                            if (df != null) {
                                                of.setAccessible(true);
                                                df.setAccessible(true);
                                                df.set(dist, of.get(orig));
                                            }
                                        }
                                    }
                                } catch (Throwable e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
        return dist;
    }

    public static <T> T copy(T dist, Object orig, Class<?>... classes) {
        return copy(dist, orig, null, classes);
    }

    public static <T> T copyProperties(T dist, Object orig) {
        if (dist != null && orig != null) {
            Class<?> distClass = dist.getClass();
            Class<?> origClass = orig.getClass();
            Field[] fields = distClass.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    if (field != null && !Modifier.isStatic(field.getModifiers())) {
                        try {
                            Field of = origClass.getDeclaredField(field.getName());
                            if (of != null) {
                                of.setAccessible(true);
                                Object value = of.get(orig);
                                if (value != null) {
                                    field.setAccessible(true);
                                    field.set(dist, value);
                                }
                            }
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return dist;
    }

    public static String sign(Object obj) {
        return sign(obj, null, obj.getClass());
    }

    public static String sign(Object obj, Predicate<String> predicate, Class<?>... classes) {
        if (obj != null && ArrayUtils.isNotEmpty(classes)) {
            StringBuilder buff = new StringBuilder();
            for (Class<?> clazz : classes) {
                if (clazz != null) {
                    Field[] fields = clazz.getDeclaredFields();
                    if (ArrayUtils.isNotEmpty(fields)) {
                        for (Field field : fields) {
                            if (field != null) {
                                try {
                                    if (predicate == null || predicate.test(field.getName())) {
                                        Field of = getClassField(obj.getClass(), field.getName());
                                        if (of != null) {
                                            of.setAccessible(true);
                                            Object value = of.get(obj);
                                            if (value != null) {
                                                buff.append(value);
                                            }
                                        }
                                    }
                                } catch (Throwable e) {
                                }
                            }
                        }
                    }
                }
            }

            if (buff.length() > 0) {
                return HashUtil.md5(buff.toString());
            }
            return StringConsts.EMPTY;
        }
        return null;
    }

    public static Map<String, Object> toMap(Object obj) {
        return toMap(obj, null);
    }

    public static Map<String, Object> toMap(Object obj, Predicate<String> predicate) {
        if (obj != null) {
            Field[] fields = obj.getClass().getDeclaredFields();
            if (fields != null && fields.length > 0) {
                try {
                    Map<String, Object> data = new HashMap<String, Object>();
                    for (Field field : fields) {
                        if (field != null) {
                            if (predicate == null || predicate.test(field.getName())) {
                                field.setAccessible(true);
                                data.put(field.getName(), field.get(obj));
                            }
                        }
                    }
                    return data;
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
        return Collections.emptyMap();
    }

    public static Map<String, Object> toMap(String[] names, Object obj, Function<String, String> renameFunc) {
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        if (obj != null && ArrayUtils.isNotEmpty(names)) {
            for (String name : names) {
                if (renameFunc != null) {
                    name = renameFunc.apply(name);
                }
                if (StringUtils.isNotEmpty(name)) {
                    data.put(name, getFieldValue(obj, name));
                }
            }
        }
        return data;
    }

    public static Map<String, Object> toMap(String[] names, Object obj) {
        return toMap(names, obj, null);
    }

    public static void execute(final Object obj,String method,boolean async){
        execute(obj, method, null, null, async);
    }
    public static void execute(final Object obj,String method,Class[] types,final Object[] params,boolean async){
        if(obj != null && StringUtils.isNotEmpty(method)){
            try {
                Class clazz = obj.getClass();
                final Method _method = clazz.getDeclaredMethod(method, types);
                if(_method != null){
                    _method.setAccessible(true);
                    if(async){
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    _method.invoke(obj, params);
                                } catch (Throwable e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        thread.start();
                    }
                    else{
                        _method.invoke(obj, params);
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}
