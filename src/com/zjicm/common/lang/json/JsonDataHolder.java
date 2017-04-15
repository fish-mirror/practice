package com.zjicm.common.lang.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.zjicm.common.lang.page.PageResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.io.IOException;
import java.util.*;

/**
 * 按接口文档规定 封装的JSON格式
 * <p>
 * 返回的成功信息以data为根节点，data节点是一个object键值对。必须包含且仅包含一个array节点items，通过items获得数组信息
 * data节点中可以包含一些简单数据类型的键值对
 * 主要数据（list，object等）放在items列表中,self节点中方以前前后端对接的信息
 * 当items和self为empty时，不将empty节点输出
 * <p>
 * 返回的错误信息以error为根节点，error节点是一个object键值对。
 * error中包含两个String属性code和message
 * <p>
 * 提供对data数据灵活封装的方法：
 * 包含在data对象中，与items同级的属性（基本数据类型，非对象和数组），通过putToData方法放入data中
 * 包含在slef对象中的属性，通过putToSelf方法放入self中
 * 包含在items数组中，数据必须是对象或者是数组，含有键名的属性必须封装在一个对象中
 * object通过addToItems方法添加到items数组中
 * array通过addListToItems方法，遍历数组将array中的数据添加到items中
 * <p>
 * 提供对error数据封装的方法：
 * 调用putToError(String code, String message)，提供状态码和错误消息即可
 * <p>
 * 除此之外：
 * 提供错误信息的输出和常见错误信息的封装
 * 提供增删改之后返回的简单信息的封装
 * 提供常见工具数据对象的JSON信息的封装（PageResult）
 * <p>
 * 输出时，只可以输出data或erro中的一个节点，以此区别响应输出的json是成功状态还是错误状态
 * Controller中直接返回JsonDataHolder即可，自定义的JsonDataHolderHttpMessageConverter类定义了在输出时，对toJson方法的调用
 * toJson方法中，通过判断error节点中是否有数据来判断是输出error节点还是data节点
 * getDataJsonStr和getErrorJson方法封装了输出data或error中的信息
 * <p>
 * <p>
 * Created by yujing on 16-7-27.
 */

public class JsonDataHolder {
    //正确信息返回结点
    @JsonView(JsonDataHolder.Data.class)
    private Map<String, Object> data;
    @JsonIgnore
    private List items;
    @JsonIgnore
    private Map<String, Object> self;

    //错误信息返回结点
    @JsonView(JsonDataHolder.Error.class)
    private Map<String, Object> error;

    public JsonDataHolder() {
        data = new HashMap<>();
        error = new HashMap<>();
        items = new ArrayList<>();
        self = new HashMap<>();
        data.put("self", self);
        data.put("items", items);
    }

    //*******一些data信息的简单操作方法*******

    /**
     * 将list数据遍历添加到data.items列表中
     *
     * @param list
     * @return
     */
    public JsonDataHolder addListToItems(List list) {
        if (list != null) {
            for (Object o : list) {
                this.addToItems(o);
            }
        }
        return this;
    }

    /**
     * 将object对象添加到data.items列表中
     *
     * @param value
     * @return
     */
    public JsonDataHolder addToItems(Object value) {
        if (value != null) {
            this.items.add(value);
        }
        return this;
    }

    /**
     * 将object对象添加到self中
     *
     * @param key
     * @param value
     * @return
     */
    public JsonDataHolder putToSelf(String key, Object value) {
        if (value != null) {
            this.self.put(key, value);
        }
        return this;
    }

    /**
     * 将object对象添加到data中
     *
     * @param key
     * @param value
     * @return
     */
    public JsonDataHolder putToData(String key, Object value) {
        if (key != null) {

            this.data.put(key, value);
        }
        return this;
    }
    //********错误信息的简单封装********

    public JsonDataHolder putToError(int code, String message) {
        this.error.put("code", code);
        this.error.put("message", message);
        return this;
    }

    public JsonDataHolder error101() {
        return this.putToError(101, "暂无数据");
    }

    public JsonDataHolder error404() {
        return this.putToError(404, "不存在该数据");
    }

    public JsonDataHolder error403() {
        return this.putToError(403, "请求不能被接受");
    }

    public JsonDataHolder error400() {
        return this.putToError(400, "参数错误");
    }

    /**
     * 打包错误信息
     *
     * @param code
     * @param message
     * @param errorInfoList
     * @return
     */
    public JsonDataHolder packErrors(int code, String message, List<JsonErrorInfo> errorInfoList) {
        putToError(code, message);
        this.error.put("errors", errorInfoList);
        return this;
    }

    /**
     * 从BindingResult中获取绑定的错误信息，并封装具体的打包逻辑，返回打包对应错误信息后的JsonDataHolder
     *
     * @param code
     * @param message
     * @param errorInfoList errorInfoList由外部传入，便于兼容原有的错误信息，能够一起打包输出
     * @param results
     * @return 有错误信息则返回JsonDataHolder本身，没有错误信息则返回null
     */
    public JsonDataHolder packErrorsFromResult(int code,
                                               String message,
                                               List<JsonErrorInfo> errorInfoList,
                                               BindingResult results) {
        if (results != null) {
            List<ObjectError> errors = results.getAllErrors();
            for (ObjectError error : errors) {
                errorInfoList.add(new JsonErrorInfo(((FieldError) error).getField(),
                                                    null,
                                                    error.getDefaultMessage(),
                                                    null,
                                                    null,
                                                    null,
                                                    null));
            }
            if (CollectionUtils.isNotEmpty(errorInfoList)) {
                this.packErrors(code, message, errorInfoList);
                return this;
            }
        }
        return null;

    }


    //********简单信息的封装*********

    /**
     * 新增,更新,删除等操作后的简单 json 信息
     * <p/>
     * <p>成功信息</p>
     *
     * @param id      标识ID
     * @param msgType 操作类型
     * @return
     */
    public JsonDataHolder simpleMsg(Object id, MsgType msgType) {
        Map<String, Object> msg = new HashMap<>();
        msg.put("id", id);

        if (msgType == MsgType.delete) {
            msg.put("delected", true);
        } else if (msgType == MsgType.add) {
        } else if (msgType == MsgType.update) {
            msg.put("updated", System.currentTimeMillis());
        } else if (msgType == MsgType.check) {
            msg.put("exist", true);
        }
        this.addToItems(msg);
        return this;
    }


    //********常用工具类JSON输出*****

    /**
     * 封装PageResult中相关的分页信息
     * 为了能够灵活确定items中的list信息，该方法仅对分页属性进行分装，不封装列表信息
     *
     * @param result
     */
    public void putAttrToJsonDataHolder(PageResult result) {
        if (result != null) {
            this.putToData("total_items", result.getPageBuilder().getItems());
            this.putToData("items_per_page", result.getPageBuilder().itemsPerPage());
            this.putToData("current_item_count", result.getLimit());
            this.putToData("total_pages", result.getPageBuilder().pages());
            this.putToData("page_index", result.getPageBuilder().page());
            this.putToData("start_index", result.getPageBuilder().beginIndex());
        }
    }

    //*********输出*************
    public String toJson() {
        //如果error节点为只输出data节点
        if (error.isEmpty()) {
            if (MapUtils.isEmpty((Map)data.get("self"))) {
                data.remove("self");
            }
            if (CollectionUtils.isEmpty((List)data.get("items"))) {
                data.remove("items");
            }
            return this.getDataJsonStr(this);
        } else {
            return this.getErrorJsonStr(this);
        }
    }

    //*********验证**************

    /**
     * 验证json字符串是否符合data输出
     *
     * @param json
     * @return
     */
    public static boolean validateData(String json) {
        if (StringUtils.isNotBlank(json)) {
            try {
                JsonNode jsonNode = JsonUtil.getInstance().readTree(json);
                if (jsonNode != null) {
                    if (jsonNode.isObject() && jsonNode.has("data") && jsonNode.size() == 1) {
                        JsonNode dataNode = jsonNode.findValue("data");
                        //data节点是否是Object
                        if (dataNode.isObject()) {
                            //遍历data节点中是否包含不符合规定的子节点
                            Iterator<Map.Entry<String, JsonNode>> jsonNodes = dataNode.fields();
                            while (jsonNodes.hasNext()) {
                                Map.Entry<String, JsonNode> node = jsonNodes.next();
                                //data节点中只能包含一个items数组、一个self对象基本数据类型
                                if (node.getValue().isObject()) {
                                    if (!node.getKey().equals("self")) {
                                        System.out.println("包含不为“self”的object节点,，或self节点为空");
                                        return false;
                                    }
                                } else if (node.getValue().isArray()) {
                                    if (!node.getKey().equals("items")) {
                                        System.out.println("包含不为“items”的array节点,，或array节点为空");
                                        return false;
                                    }
                                }
                            }
                            //判断items是否为数组
                            JsonNode itemsNode = dataNode.findValue("items");
                            if (!itemsNode.isArray()) {
                                System.out.println("items不为数组");
                                return false;
                            }
                            return true;
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("验证出错");
                e.printStackTrace();
                return false;
            }
        }
        System.out.println("未知错误");
        return false;
    }

    /**
     * 验证json字符串是否符合error输出
     *
     * @param json
     * @return
     */
    public static boolean validateError(String json) {
        if (StringUtils.isNotBlank(json)) {
            try {
                JsonNode jsonNode = JsonUtil.getInstance().readTree(json);
                if (jsonNode != null) {
                    if (jsonNode.isObject() && jsonNode.has("error") && jsonNode.size() == 1) {
                        JsonNode errorNode = jsonNode.findValue("error");
                        //error节点是否是Object并且有且只有code和message节点
                        if (errorNode.isObject() && errorNode.has("code") && errorNode.has("message") &&
                            errorNode.size() == 2) {
                            if (errorNode.findValue("code").isTextual() && errorNode.findValue("message").isTextual()) {
                                return true;
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    // 用于设置输出JsonDataHolder类的 data结点
    public interface Data {
    }

    ;

    // 用于设置输出JsonDataHolder类的 error结点
    public interface Error {
    }

    ;

    /**
     * 输出data为根节点的json字符串
     *
     * @param obj
     * @return
     */
    public String getDataJsonStr(JsonDataHolder obj) {
        String json = null;
        try {
            json = JsonUtil.getInstance().writerWithView(Data.class).writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 输出error为根节点的json字符串
     *
     * @param obj
     * @return
     */
    public String getErrorJsonStr(JsonDataHolder obj) {
        String json = null;
        try {
            json = JsonUtil.getInstance().writerWithView(Error.class).writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public List getItems() {
        return items;
    }

    public Map<String, Object> getSelf() {
        return self;
    }

    public Map<String, Object> getError() {
        return error;
    }
}
