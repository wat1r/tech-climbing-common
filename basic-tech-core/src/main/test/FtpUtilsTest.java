import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.FtpUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FrankCooper
 * Date 2019/1/20 14:19
 * Description
 */
@Slf4j
public class FtpUtilsTest {

    FTPClient ftpClient = null;


    @Before
    public void init() {
        log.info("[----------init--------]");
        ftpClient = FtpUtils.connectFtpServer("192.168.1.104", 21, "anonymous", "", "utf-8");
    }

    @After
    public void destory() {
        FtpUtils.closeFTPConnect(ftpClient);
        log.info("[----------destory--------]");
    }


    @Test
    public void testFtpSingle() {
//        FtpUtils.downloadSingleFile(ftpClient, "C:\\Users\\FrankCooper\\Downloads", "\\decompress\\new 2.txt");
        FtpUtils.downloadSingleFile(ftpClient, "C:\\Users\\FrankCooper\\Downloads", "\\test.zip");

    }


    @Test
    public void testFtpList() {
        List<String> relativePathList = new ArrayList<>();
        relativePathList = FtpUtils.loopServerPath(ftpClient, "\\decompress", relativePathList);
        for (String relativePath : relativePathList) {
            System.out.println("准备下载的服务器文件：" + relativePath);
            FtpUtils.downloadSingleFile(ftpClient, "C:\\Users\\FrankCooper\\Downloads", relativePath);
        }
    }

    @Test
    public void testFtpUpload() {
        FtpUtils.uploadFiles(ftpClient, new File("E:\\tmp"));

    }

    @Test
    public void testFtpSync() throws IOException {
        FtpUtils.syncLocalDir(ftpClient, "E:\\test1");
    }


}
