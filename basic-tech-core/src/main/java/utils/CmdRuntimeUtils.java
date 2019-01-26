package utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.concurrent.*;

/**
 * 命令行、脚本执行工具类
 *
 * @author songhaifeng
 * @date 2018/3/14
 */
public class CmdRuntimeUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(CmdRuntimeUtils.class);
    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder()
            .setNameFormat("process-pool-%d").build();
    private static final ExecutorService THREAD_POOL = new ThreadPoolExecutor(10, 100,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), THREAD_FACTORY, new ThreadPoolExecutor.AbortPolicy());

    /**
     * 执行命令行脚本
     *
     * @param command 待执行命令
     * @param timeout 超时时长，单位s
     * @return 待执行命令返回值
     */
    public static String execute(String command, int timeout) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            LOGGER.warn("execute-----execute");
            return CmdRuntimeUtils.getProcessResult(process, timeout);
        } catch (IOException e) {
            LOGGER.warn("IOException now need destroy");
        } finally {
            if (process != null && process.isAlive()) {
                LOGGER.warn("destory");
                process.destroy();
            }
        }
        return null;
    }

    /**
     * 获取Process执行的返回结果
     *
     * @param process process
     * @param timeout 超时时长，单位秒
     * @return 可能为null
     */
    public static String getProcessResult(Process process, int timeout) {
        startErrorReaderThread(process.getErrorStream());
        BufferedReader reader = null;
        try {
            boolean result;
            if (timeout < 0) {
                result = process.waitFor() == 0;
                LOGGER.warn("timeout < 0 result:" + result);
            } else {
                result = process.waitFor(timeout, TimeUnit.SECONDS);
                LOGGER.warn("timeout >= 0 result:" + result);
            }
            if (result) {
                StringBuilder sb = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                LOGGER.warn("sb:" + sb.toString());
                return sb.toString();
            }
        } catch (InterruptedException e) {
            LOGGER.warn("InterruptedException now need destroy");
        } catch (UnsupportedEncodingException e) {
            LOGGER.warn("UnsupportedEncodingException now need destroy");
        } catch (IOException e) {
            LOGGER.warn("IOException now need destroy");
        } finally {
            if (process.isAlive()) {
                LOGGER.warn("time out destory");
                process.destroy();
            } else {
                LOGGER.warn("already destory,{}", process.isAlive());
            }
            IOUtils.closeQuietly(reader);
        }
        LOGGER.info("wait to return");
        return null;
    }

    /**
     * 通过异步线程打印Process执行过程中的异常信息
     *
     * @param errorStream errorStream
     */
    private static void startErrorReaderThread(InputStream errorStream) {
        THREAD_POOL.execute(() -> {
            BufferedReader errorStreamReader = null;
            try {
                errorStreamReader = new BufferedReader(new InputStreamReader(errorStream, "UTF-8"));
                String line;
                while ((line = errorStreamReader.readLine()) != null) {
                    LOGGER.error(line);
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            } finally {
                if (errorStreamReader != null) {
                    try {
                        errorStreamReader.close();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }
                }
            }
        });
    }
}
