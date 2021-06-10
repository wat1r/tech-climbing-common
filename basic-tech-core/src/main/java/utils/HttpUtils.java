package utils;

import okhttp3.*;

import java.util.concurrent.TimeUnit;

/**
 * 封装 OkHttpClient
 */
public class HttpUtils {

    private static final OkHttpClient client;
    private static final int HTTP_SUCCESS_CODE = 200;

    static {
        client = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    public static String get(String url) throws Exception {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        return execute(request);
    }

    public static String post(String url, RequestBody requestBody) throws Exception {
        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();
        return execute(request);
    }

    private static String execute(Request request) throws Exception {
        try (Response response = client.newCall(request).execute()) {
            int responseCode = response.code();
            if (responseCode == HTTP_SUCCESS_CODE) {
                ResponseBody body = response.body();
                if (body == null) {
                    return null;
                } else {
                    return body.string();
                }
            }
            throw new Exception(String.format("http request failed,code=%d", responseCode));
        }
    }

}
