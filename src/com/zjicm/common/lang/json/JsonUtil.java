package com.zjicm.common.lang.json;

import com.dxy.commons.Server;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.fasterxml.jackson.databind.*;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

@SuppressWarnings("unchecked")
public final class JsonUtil {
    private static final CustomObjectMapper OBJECT_MAPPER = new CustomObjectMapper();

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        OBJECT_MAPPER.setCamelCaseToLowerCaseWithUnderscores(true);
        OBJECT_MAPPER.setDateFormatPattern("yyyy-MM-dd HH:mm:ss");
        OBJECT_MAPPER.init();
    }

    public static CustomObjectMapper getInstance() {
        return OBJECT_MAPPER;
    }

    public static boolean is(String json) {
        if (json != null && json.length() > 1) {
            try {
                int count = 0;
                final JsonParser parser = new CustomObjectMapper().getJsonFactory().createJsonParser(json);
                while (parser.nextToken() != null && count < 10000) {
                    count++;
                }
                return true;
            } catch (Throwable e) {
            }
        }

        return false;
    }

    public static boolean not(String json) {
        return !is(json);
    }

    public static String quote(String source) {
        if (StringUtils.isNotEmpty(source)) {
            return new String(JsonStringEncoder.getInstance().quoteAsString(source));
        }
        return source;
    }

    public static <T> T toObject(Class<T> clazz, String json) {
        if (clazz != null && StringUtils.isNotEmpty(json)) {
            try {
                return OBJECT_MAPPER.readValue(json, clazz);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static <T> List<T> toList(Class<T> clazz, String json) {
        if (clazz != null && StringUtils.isNotEmpty(json)) {
            try {
                return OBJECT_MAPPER.readValue(json,
                                               OBJECT_MAPPER.getTypeFactory()
                                                            .constructCollectionType(ArrayList.class, clazz));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        return Collections.emptyList();
    }

    public static <T> Map<String, T> toMap(String json) {
        if (StringUtils.isNotEmpty(json)) {
            try {
                return OBJECT_MAPPER.readValue(json, Map.class);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        return Collections.emptyMap();
    }

    public static Map<String, String> toStringMap(String json) {
        if (StringUtils.isNotEmpty(json)) {
            try {
                JsonNode rootNode = OBJECT_MAPPER.getFactory().createParser(json).readValueAsTree();
                if (rootNode != null) {
                    Map<String, String> data = new HashMap<String, String>();
                    Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
                    while (fields.hasNext()) {
                        Map.Entry<String, JsonNode> field = fields.next();
                        if (field != null && StringUtils.isNotEmpty(field.getKey())) {
                            if (field.getValue() == null) {
                                data.put(field.getKey(), ConstStrings.EMPTY);
                            } else {
                                String value = field.getValue().asText();
                                if (StringUtils.isEmpty(value)) {
                                    data.put(field.getKey(), field.getValue().toString());
                                } else {
                                    data.put(field.getKey(), value);
                                }
                            }
                        }
                    }
                    return data;
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        return Collections.emptyMap();
    }

    public static String toString(Object obj) {
        if (obj != null) {
            try {
                return OBJECT_MAPPER.writeValueAsString(obj);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        return ConstStrings.EMPTY;
    }

    public static byte[] toBytes(Object obj) {
        if (obj != null) {
            try {
                return OBJECT_MAPPER.writeValueAsBytes(obj);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return ConstArrays.EMPTY_PRIMITIVE_BYTE;
    }

    public static void append(StringBuilder buff, String name, Object value, boolean quote, boolean startWithComma) {
        if (buff != null && StringUtils.isNotEmpty(name)) {
            if (startWithComma) {
                buff.append(",");
            }
            buff.append("\"");
            buff.append(name);
            buff.append("\":");
            if (value == null) {
                buff.append("null");
            } else if (quote) {
                buff.append("\"");
                buff.append(quote(value.toString()));
                buff.append("\"");
            } else {
                buff.append(value);
            }
        }
    }

    public static final class StringBase64Serializer extends JsonSerializer<String> {
        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws
                                                                                            IOException,
                                                                                            JsonProcessingException {
            gen.writeString(Base64Util.encode(value));
        }
    }

    public static final class StringBase64Deserializer extends JsonDeserializer<String> {
        @Override
        public String deserialize(JsonParser parser, DeserializationContext context) throws
                                                                                     IOException,
                                                                                     JsonProcessingException {
            String value = parser.getText();
            if (value != null) {
                return new String(Base64Util.decode(value));
            }
            return null;
        }
    }

    public static final class StringURLSerializer extends JsonSerializer<String> {
        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws
                                                                                            IOException,
                                                                                            JsonProcessingException {
            gen.writeString(URLEncoder.encode(StringUtils.nullToEmpty(value), Server.CHARSET));
        }
    }

    public static final class StringRawDeserializer extends JsonDeserializer<String> {
        @Override
        public String deserialize(JsonParser parser, DeserializationContext context) throws
                                                                                     IOException,
                                                                                     JsonProcessingException {
            JsonNode node = parser.readValueAsTree();
            if (node != null) {
                if (node.isObject()) {
                    return node.toString();
                }
                return node.asText();
            }
            return parser.getText();
        }
    }

    public static final class StringURLDeserializer extends JsonDeserializer<String> {
        @Override
        public String deserialize(JsonParser parser, DeserializationContext context) throws
                                                                                     IOException,
                                                                                     JsonProcessingException {
            String value = parser.getText();
            if (value != null) {
                return new String(URLDecoder.decode(value, Server.CHARSET));
            }
            return null;
        }
    }

    public static final class DateFormatDeserializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser parser, DeserializationContext context) throws
                                                                                   IOException,
                                                                                   JsonProcessingException {
            String value = parser.getText();
            if (value != null) {
                return TimeUtil.parse(value);
            }
            return null;
        }
    }

    public static class LongAsStringSerializer extends JsonSerializer<Long> {
        @Override
        public void serialize(Long _long, JsonGenerator jgen, SerializerProvider sp) throws
                                                                                     IOException,
                                                                                     JsonProcessingException {
            if (_long != null) {
                jgen.writeString(_long.toString());
            }
        }
    }

}
