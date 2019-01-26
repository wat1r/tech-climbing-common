package utils;

import org.apache.commons.exec.*;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RuntimeExecUtils {
    private final static Logger LOGGER = LoggerFactory.getLogger(RuntimeExecUtils.class);
    private File bufferedWriterHandler;

    public RuntimeExecUtils(String path) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        bufferedWriterHandler = new File(path + "/" + simpleDateFormat.format(new Date()));
    }

    public void execCmdWithoutResult(String command) throws Exception {
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        ExecuteWatchdog watchdog = new ExecuteWatchdog(60 * 1000);
        executor.setWatchdog(watchdog);
        DefaultExecuteResultHandler handler = new DefaultExecuteResultHandler();
        CommandLine commandline = CommandLine.parse(command);
        executor.execute(commandline, handler);
        handler.waitFor();
    }

    public int execCmdWithResult(File workSpace, String command) throws IOException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
            CommandLine commandline = CommandLine.parse(command);
            DefaultExecutor exec = new DefaultExecutor();
            exec.setExitValues(null);
            exec.setWorkingDirectory(workSpace);
            ExecuteWatchdog watchdog = new ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT);
            exec.setWatchdog(watchdog);
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream, errorStream);
            exec.setStreamHandler(streamHandler);
            int code = exec.execute(commandline);
            LOGGER.info("execCmdWithResult code:[{}]", code);
            FileUtils.writeStringToFile(bufferedWriterHandler, outputStream.toString(), "utf-8", true);
            FileUtils.writeStringToFile(bufferedWriterHandler, errorStream.toString(), "utf-8", true);
            return code;
        } catch (Exception e) {
            FileUtils.writeStringToFile(bufferedWriterHandler, "exception:" + e.getMessage(), "utf-8", true);
            LOGGER.error("return -1, execCmdWithResult exception:{}", e);
            return -1;
        }
    }

    public static void main(String[] args) throws Exception {
        //System.out.println(execCmdWithResult("aws s3 cp /Users/qumingxing/Downloads/patsnap_innovation_rd_h2.pdf s3://s-chemical-fda-prod/qumingxing/patsnap_innovation_rd_h2.pdf>/dev/null"));
        //System.out.println(execCmdWithResult("cp /Users/qumingxing/Downloads/patsnap_innovation_rd_h2.pdf /Users/qumingxing"));
        //System.out.println(execCmdWithResult("rm /Users/qumingxing/patsnap_innovation_rd_h2.pdf -f"));

        final CommandLine cmdLine = CommandLine.parse("ping www.baidu.com -t");
        final ExecuteWatchdog watchdog = new ExecuteWatchdog(Integer.MAX_VALUE);
        final DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();

        executor.setWatchdog(watchdog);
        executor.execute(cmdLine, resultHandler);

        Thread.sleep(10000);//等进程执行一会，再终止它
        System.out.println("--> Watchdog is watching ? " + watchdog.isWatching());
        watchdog.destroyProcess();//终止进程
        System.out.println("--> destroyProcess done.");
        System.out.println("--> Watchdog is watching ? " + watchdog.isWatching());
        System.out.println("--> Watchdog should have killed the process : " + watchdog.killedProcess());
        System.out.println("--> wait result is : " + resultHandler.hasResult());
        System.out.println("--> exit value is : " + resultHandler.getExitValue());
        System.out.println("--> exception is : " + resultHandler.getException());

        resultHandler.waitFor(5000);//等待5秒。下面加上上面的几个System.out，看看进程状态是什么。

    }
}
