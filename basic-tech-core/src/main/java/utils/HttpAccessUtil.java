package utils;

import okhttp3.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;


/**
 * Http 请求访问工具类
 *
 * @author will_awoke
 * @version 2014-6-26
 * @see HttpAccessUtil
 */
public class HttpAccessUtil {


    public static final String X_CORRELATION_ID = "X-Correlation-ID";
    /**
     * 日志
     */
    private static Logger log = Logger.getLogger(HttpAccessUtil.class);
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient client;

    static {
        ConnectionPool pool = new ConnectionPool();
        client = new OkHttpClient().newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .connectionPool(pool).build();
    }

    public static String get(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String post(String url, String json) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .addHeader(X_CORRELATION_ID, "CONSUMER")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException ie) {
            throw new RuntimeException(ie);
        }
    }

    public static void download(String url, String desc, Headers headers) throws IOException {
        Request.Builder builder = new Request.Builder()
                .url(url);
        if (null != headers) {
            builder.headers(headers);
        }
        Request request = builder.build();
        Response response = client.newCall(request).execute();
        InputStream is = null;
        byte[] buf = new byte[10140];
        int len = 0;
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            long total = response.body().contentLength();
            File file = new File(desc);
            fos = new FileOutputStream(file);
            long sum = 0;
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
                sum += len;
                int progress = (int) (sum * 1.0f / total * 100);
                log.info("progress=" + progress);
            }
            fos.flush();
            log.info("文件下载成功");
        } catch (Exception e) {
            log.error("Failed to download file:" + desc);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public static void asynDownload(String url, String desc) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Cookie", "JSESSIONID=zKICCGyx89X-hsH-ylXJO30eWSWPV3jqfY55EZvMVSC8_MtuL0g5!1228024843; _pk_id.109.a0c2=10bdaff96b831383.1516154213.2.1516157650.1516157650.; _pk_ses.109.a0c2=*")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[10140];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    File file = new File(desc);
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        log.info("progress=" + progress);
                    }
                    fos.flush();
                    log.info("文件下载成功");
                } catch (Exception e) {
                    log.error("Failed to download file:" + desc);
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                log.error("Failed to download file:" + desc);
            }
        });
    }
}
