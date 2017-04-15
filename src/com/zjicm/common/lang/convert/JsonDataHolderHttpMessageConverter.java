package com.zjicm.common.lang.convert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjicm.common.lang.http.util.WebUtil;
import com.zjicm.common.lang.io.IoUtil;
import com.zjicm.common.lang.json.JsonDataHolder;
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

/**
 * Created by yujing on 16-8-23.
 */
public class JsonDataHolderHttpMessageConverter implements HttpMessageConverter<JsonDataHolder> {
    private static List<MediaType> SUPPORTED_MEDIATYPES = new ArrayList(1);
    public ObjectMapper objectMapper;

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean canRead(Class<?> aClass, MediaType mediaType) {
        return this.canWrite(aClass, mediaType);
    }

    @Override
    public boolean canWrite(Class<?> aClass, MediaType mediaType) {
        return JsonDataHolder.class.equals(aClass);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return SUPPORTED_MEDIATYPES;
    }

    @Override
    public JsonDataHolder read(Class<? extends JsonDataHolder> aClass, HttpInputMessage httpInputMessage) throws
                                                                                                                  IOException,
                                                                                                                  HttpMessageNotReadableException {
        return  JsonUtil.toObject(aClass, IoUtil.readToString(httpInputMessage.getBody()));
    }

    @Override
    public void write(JsonDataHolder jsonDataholder,
                      MediaType mediaType,
                      HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        httpOutputMessage.getHeaders().set("content-type", WebUtil.getContentTypeJson());
        httpOutputMessage.getBody().write(jsonDataholder.toJson().getBytes());
    }
}
