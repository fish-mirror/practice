package com.zjicm.common.lang.convert;

import com.zjicm.common.lang.http.util.WebUtil;
import com.zjicm.common.lang.io.IoUtil;
import com.zjicm.common.lang.json.JsonUtil;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapHttpMessageConverter implements HttpMessageConverter<Map<String, Object>> {
    private static List<MediaType> SUPPORTED_MEDIATYPES = new ArrayList<MediaType>(1);

    static {
        SUPPORTED_MEDIATYPES.add(MediaType.ALL);
    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return canWrite(clazz, mediaType);
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        if (Map.class.isAssignableFrom(clazz)) {
            return true;
        }
        return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return SUPPORTED_MEDIATYPES;
    }

    @Override
    public Map<String, Object> read(Class<? extends Map<String, Object>> clazz, HttpInputMessage inputMessage) throws
                                                                                                               IOException,
                                                                                                               HttpMessageNotReadableException {
        return JsonUtil.toObject(clazz, IoUtil.readToString(inputMessage.getBody()));
    }

    @Override
    public void write(Map<String, Object> model, MediaType mediaType, HttpOutputMessage outputMessage) throws
                                                                                                       IOException,
                                                                                                       HttpMessageNotWritableException {
        outputMessage.getHeaders().set("content-type", WebUtil.getContentTypeJson());
        outputMessage.getBody().write(JsonUtil.toBytes(model));
    }
}
