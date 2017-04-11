package com.zjicm.common.lang.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.client.urlconnection.HttpURLConnectionFactory;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import com.zjicm.common.lang.consts.StringConsts;
import com.zjicm.common.lang.io.IoUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import javax.net.ssl.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public final class HttpClientUtil {
    static {
        System.setProperty("jsse.enableSNIExtension", "false");
    }

    private static final Client CLIENT = Client.create();
    private static final Client UPLOAD_CLIENT = Client.create();

    private static final HostnameVerifier HOSTNAME_VERIFIER = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
    private static final TrustManager[] TRUST_MANAGER = new TrustManager[]{new X509TrustManager() {
        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
        }

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }};

    static {
        HttpsURLConnection.setDefaultHostnameVerifier(HOSTNAME_VERIFIER);
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, TRUST_MANAGER, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        CLIENT.setFollowRedirects(true);
        CLIENT.setConnectTimeout(5000);
        CLIENT.setReadTimeout(5000);

        UPLOAD_CLIENT.setFollowRedirects(true);
        UPLOAD_CLIENT.setConnectTimeout(10000);
        UPLOAD_CLIENT.setReadTimeout(10000);
    }

    private static long lastCleanTime = System.currentTimeMillis();

    private static final Map<String, WebResourceCache> RESOURCES = new ConcurrentHashMap<String, WebResourceCache>();

    private static synchronized void clean() {
        if (System.currentTimeMillis() - lastCleanTime > 300000) {
            lastCleanTime = System.currentTimeMillis();
            ConcurrentUtil.execute(new Runnable() {
                @Override
                public void run() {
                    long currentTime = System.currentTimeMillis();
                    for (Entry<String, WebResourceCache> entry : RESOURCES.entrySet()) {
                        if (currentTime - entry.getValue().lastUsedTime > 60000) {
                            RESOURCES.remove(entry.getKey());
                        }
                    }
                }
            });
        }
    }

    private static <T> T response(ClientResponse clientResponse, Class<T> clazz) {
        if (clientResponse != null) {
            if (clientResponse.getStatus() == 200) {
                return clientResponse.getEntity(clazz);
            }
        }
        return null;
    }

    private static WebResource getWebResource(String url) {
        if (StringUtils.isNotEmpty(url)) {
            try {
                WebResourceCache cache = RESOURCES.get(url);
                if (cache == null) {
                    cache = new WebResourceCache(CLIENT.resource(url));
                    RESOURCES.put(url, cache);
                }
                cache.lastUsedTime = System.currentTimeMillis();
                return cache.resource;
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                if (System.currentTimeMillis() - lastCleanTime > 300000) {
                    clean();
                }
            }
        }
        return null;
    }

    public static String get(String url) {
        return get(String.class, url, null);
    }

    public static String get(String url, Map<String, String> params) {
        return get(String.class, url, params);
    }

    public static <T> T get(Class<T> clazz, String url, Map<String, String> params) {
        try {
            WebResource wr = getWebResource(url);
            if (wr != null) {
                if (params == null || params.isEmpty()) {
                    return response(wr.get(ClientResponse.class), clazz);
                }

                if (params instanceof MultivaluedMap) {
                    return response(wr.queryParams((MultivaluedMap) params).get(ClientResponse.class), clazz);
                }
                MultivaluedMap<String, String> mvm = new MultivaluedMapImpl();
                for (Entry<String, String> entry : params.entrySet()) {
                    mvm.putSingle(entry.getKey(), entry.getValue());
                }
                return response(wr.queryParams(mvm).get(ClientResponse.class), clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String post(String url, Map<String, Object> params) {
        return post(String.class, url, params);
    }

    public static <T> T post(Class<T> clazz, String url, Map<String, Object> params) {
        return post(clazz, url, params, MediaType.APPLICATION_FORM_URLENCODED);
    }

    public static <T> T post(Class<T> clazz, String url, Map<String, Object> params, String mediaType) {
        try {
            WebResource wr = getWebResource(url);
            if (wr != null) {
                if (params == null) {
                    return response(wr.post(ClientResponse.class), clazz);
                }
                Form formData = new Form();
                for (Entry<String, Object> entry : params.entrySet()) {
                    if (StringUtils.isNotEmpty(entry.getKey()) && entry.getValue() != null) {
                        formData.add(entry.getKey(), entry.getValue());
                    }
                }

                return response(wr.type(mediaType == null ? MediaType.APPLICATION_FORM_URLENCODED : mediaType)
                                  .post(ClientResponse.class, formData), clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String put(String url, Map<String, Object> params) {
        return put(String.class, url, params, MediaType.APPLICATION_FORM_URLENCODED);
    }

    public static <T> T put(Class<T> clazz, String url, Map<String, Object> params, String mediaType) {
        try {
            WebResource wr = getWebResource(url);
            if (wr != null) {
                if (params == null) {
                    return response(wr.put(ClientResponse.class), clazz);
                }
                Form formData = new Form();
                for (Entry<String, Object> entry : params.entrySet()) {
                    if (StringUtils.isNotEmpty(entry.getKey()) && entry.getValue() != null) {
                        formData.add(entry.getKey(), entry.getValue());
                    }
                }

                return response(wr.type(mediaType == null ? MediaType.APPLICATION_FORM_URLENCODED : mediaType)
                                  .put(ClientResponse.class, formData), clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String delete(String url) {
        return delete(String.class, url, null);
    }

    public static String delete(String url, Map<String, String> params) {
        return delete(String.class, url, params);
    }

    public static <T> T delete(Class<T> clazz, String url, Map<String, String> params) {
        try {
            WebResource wr = getWebResource(url);
            if (wr != null) {
                if (params == null || params.isEmpty()) {
                    return response(wr.delete(ClientResponse.class), clazz);
                }

                if (params instanceof MultivaluedMap) {
                    return response(wr.queryParams((MultivaluedMap) params).delete(ClientResponse.class), clazz);
                }
                MultivaluedMap<String, String> mvm = new MultivaluedMapImpl();
                for (Entry<String, String> entry : params.entrySet()) {
                    mvm.putSingle(entry.getKey(), entry.getValue());
                }
                return response(wr.queryParams(mvm).delete(ClientResponse.class), clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String post(String url, byte[] data) {
        return post(String.class, url, data);
    }

    public static <T> T post(Class<T> clazz, String url, byte[] data) {
        return post(clazz, url, data, MediaType.APPLICATION_OCTET_STREAM);
    }

    public static <T> T post(Class<T> clazz, String url, byte[] data, String mediaType) {
        WebResource wr = getWebResource(url);
        if (wr != null) {
            if (data == null) {
                return response(wr.post(ClientResponse.class), clazz);
            }
            return response(wr.entity(data, StringUtils.defaultIfEmpty(mediaType, MediaType.APPLICATION_OCTET_STREAM))
                              .post(ClientResponse.class), clazz);
        }
        return null;
    }


    private static final class WebResourceCache {
        private WebResource resource;
        private long lastUsedTime = System.currentTimeMillis();

        public WebResourceCache(WebResource resource) {
            this.resource = resource;
        }
    }

    public static String crawl(String url,
                               Map<String, Object> params,
                               boolean post,
                               Cookie[] cookies,
                               String host,
                               int port,
                               String username,
                               String password) {
        return crawl(url,
                     params,
                     post,
                     cookies,
                     StringUtils.isEmpty(host) ? null : new Proxy(Type.HTTP, new InetSocketAddress(host, port)),
                     username,
                     password);
    }

    public static String crawl(String url,
                               Map<String, Object> params,
                               boolean post,
                               Cookie[] cookies,
                               String host,
                               int port) {
        return crawl(url, params, post, cookies, host, port, null, null);
    }

    public static String crawl(String url, Map<String, Object> params, boolean post, Cookie[] cookies) {
        return crawl(url, params, post, cookies, null, null, null);
    }

    public static String crawl(String url, Map<String, Object> params, boolean post, Cookie[] cookies, Proxy proxy) {
        return crawl(url, params, post, cookies, proxy, null, null);
    }


    public static String crawl(String url,
                               Map<String, Object> params,
                               boolean post,
                               Cookie[] cookies,
                               final Proxy proxy,
                               final String username,
                               final String password) {
        return request(String.class, url, params, post, cookies, proxy, username, password);
    }

    public static <T> T request(Class<T> clazz,
                                String url,
                                Map<String, Object> params,
                                boolean post,
                                Cookie[] cookies,
                                final Proxy proxy,
                                final String username,
                                final String password) {
        if (StringUtils.isNotEmpty(url)) {
            Client client = proxy == null || proxy == Proxy.NO_PROXY
                    ? CLIENT
                    : new Client(new URLConnectionClientHandler(new HttpURLConnectionFactory() {
                        @Override
                        public HttpURLConnection getHttpURLConnection(URL url) throws IOException {
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);
                            if (conn != null) {
                                if (StringUtils.isNotEmpty(username)) {
                                    conn.setRequestProperty("Proxy-Authorization",
                                                            Base64Util.encode(
                                                                    username + ":" + StringUtils.defaultString(password)));
                                }
                                return conn;
                            }
                            return null;
                        }
                    }), new DefaultClientConfig());

            WebResource wr = client.resource(url);
            if (wr != null) {
                Builder builder = wr.getRequestBuilder();
                if (!post && params != null && !params.isEmpty()) {
                    MultivaluedMap<String, String> mvm = null;
                    if (params instanceof MultivaluedMap) {
                        mvm = (MultivaluedMap) params;
                    } else {
                        mvm = new MultivaluedMapImpl();
                        for (Entry<String, Object> entry : params.entrySet()) {
                            if (entry.getValue() != null) {
                                mvm.putSingle(entry.getKey(), entry.getValue().toString());
                            }
                        }
                    }
                    builder = wr.queryParams(mvm).getRequestBuilder();
                }

                if (cookies != null && cookies.length > 0) {
                    for (Cookie cookie : cookies) {
                        builder = builder.cookie(cookie);
                    }
                }

                if (post) {
                    Form formData = new Form();
                    if (params != null) {
                        for (Entry<String, Object> entry : params.entrySet()) {
                            if (StringUtils.isNotEmpty(entry.getKey()) && entry.getValue() != null) {
                                formData.add(entry.getKey(), entry.getValue());
                            }
                        }
                    }

                    return builder.type(MediaType.APPLICATION_FORM_URLENCODED).post(clazz, formData);
                }

                return builder.get(clazz);
            }
        }
        return null;
    }

    public static String uploadFile(String url, List<File> files, String name) {
        if (CollectionUtils.isEmpty(files)) return null;
        WebResource wr = UPLOAD_CLIENT.resource(url);
        if (wr != null) {
            final FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
            files.forEach(file -> {
                if (file.exists()) {
                    formDataMultiPart.bodyPart(new FileDataBodyPart(StringUtils.defaultIfEmpty(name, "file"),
                                                                    file,
                                                                    MediaType.MULTIPART_FORM_DATA_TYPE));

                }
            });
            return wr.type(MediaType.MULTIPART_FORM_DATA).post(String.class, formDataMultiPart);
        }

        return null;
    }

    public static String upload(String url, List<File> files, String name, Map<String, Object> params) {
        if (MapUtils.isEmpty(params)) {
            return uploadFile(url, files, name);
        }

        WebResource wr = UPLOAD_CLIENT.resource(url);
        if (wr != null) {
            FormDataMultiPart form = new FormDataMultiPart();
            for (Entry<String, Object> entry : params.entrySet()) {
                form.field(entry.getKey(), ObjectUtil.nullToDefault(entry.getValue(), StringConsts.EMPTY).toString());
            }
            files.forEach(file -> {
                if (file.exists()) {
                    form.bodyPart(new FileDataBodyPart(StringUtils.defaultIfEmpty(name, "file"),
                                                                    file,
                                                                    MediaType.MULTIPART_FORM_DATA_TYPE));

                }
            });
            return wr.type(MediaType.MULTIPART_FORM_DATA).post(String.class, form);
        }

        return null;
    }

    public static void getToFile(String url, Map<String, String> params, File output) {
        if (StringUtils.isNotEmpty(url) && output != null) {
            IoUtil.writeToFile(get(byte[].class, url, params), output);
        }
    }
}
