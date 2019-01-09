package cn.inkroom.log.server.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * http工具类，用于发送http请求
 *
 * @author 墨盒
 * @Date 19-1-8
 */
public class HttpUtil {

    private Logger logger = LoggerFactory.getLogger(getClass());


    protected static String get(HttpClient client, String url, Map<String, String> data) {
        StringBuilder builder = new StringBuilder(url);
        builder.append("?");

        for (String temp : data.keySet()) {
            builder.append(temp).append("=").append(data.get(temp)).append("&");
        }

        HttpGet get = new HttpGet(builder.toString());


        try {
            HttpResponse response = client.execute(get);

            return EntityUtils.toString(response.getEntity(), "utf-8");

        } catch (IOException e) {
            return null;
        }

    }

    public static String get(String url, Map<String, String> data) {
        return get(HttpClients.createDefault(), url, data);
    }
}
