package utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FtpFileDownloader implements Closeable {
    private static Logger logger = Logger.getLogger(FtpFileDownloader.class);
    private FTPClient ftpClient = null;
    public ArrayList<String> arFiles;

    public static void main(String[] args) throws Exception {

        FtpFileDownloader fdownloader = new FtpFileDownloader();
//        fdownloader.connect("ftpird.wipo.int", "anonymous", "guest");
//        File file = fdownloader.download("/wipo/hague/H201550.ZIP", "/tmp/H201550.ZIP");
//        fdownloader.close();
        fdownloader.connect("s220ftp.tipo.gov.tw", "anonymous", "guest");
        String pathname = "/TrademarkModXMLA_045020/";
//        String localname = "D:\\Test\\TW\\TrademarkModXMLA_045020\\";
//        String localname = "TW/TEMP/";
//        fdownloader.downFileRecursively(pathname, localname);


//        System.setProperty("os.name", "linux");


        String localPathPrefix = "TW/TEMP3/";


//        if (System.getProperty("os.name") != null && System.getProperty("os.name").contains("Win")) {
//            localPathPrefix = "D:\\Test\\TW\\";//TODO
//        } else {
        localPathPrefix = "TW/TEMP2/Correction/";//TODO
//        }
        String mod = "TrademarkModXMLA_045020/Correction";

        List<String> remotePathList = Lists.newArrayList(mod);


        for (String remotePath : remotePathList) {
            logger.info("remotePath:" + remotePath);
            logger.info("(localPathPrefix + remotePath):" + localPathPrefix + remotePath);
            fdownloader.downFileRecursively("/" + remotePath + "/", localPathPrefix);
            fdownloader.close();
        }


    }

    public void connect(String host) throws Exception {

        ftpClient.connect(host);
        int reply = ftpClient.getReplyCode();

        if (!FTPReply.isPositiveCompletion(reply)) {
            ftpClient.disconnect();
            throw new Exception("FTP Server refused to connect!");
        }

        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

    }

    public void connect(String host, String username, String password) throws Exception {

        ftpClient = new FTPClient();
        ftpClient.connect(host);
        int reply = ftpClient.getReplyCode();

        if (!FTPReply.isPositiveCompletion(reply)) {
            ftpClient.disconnect();
            throw new Exception("FTP Server refused to connect!");
        }

        if (!ftpClient.login(username, password)) {
            ftpClient.disconnect();
            throw new Exception("Invalid username or password!");
        }

        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

    }

    public File download(String pathname, String localName) throws Exception {

        if (ftpClient.listFiles(new String(pathname.getBytes(), ftpClient.getControlEncoding())).length == 0) {
            throw new Exception("File dose not exist: " + pathname);
        }

        boolean flag = false;

        File outfile = new File(localName);
        InputStream ins = ftpClient.retrieveFileStream(pathname);
        FileUtils.copyInputStreamToFile(ins, outfile);
        return outfile;

    }

    public void downloadFile(String pathname, String localName) throws Exception {
        if (ftpClient.listFiles(new String(pathname.getBytes(), ftpClient.getControlEncoding())).length == 0) {
            throw new Exception("File dose not exist: " + pathname);
        }

        OutputStream output = new FileOutputStream(localName);
        ftpClient.retrieveFile(pathname, output);
        output.close();
    }


    public List<FTPFile> getFileList(String path) throws IOException {

        FTPFile[] ftpFiles = ftpClient.listFiles(path);

        List<FTPFile> retList = new ArrayList<>();
        if (ftpFiles == null || ftpFiles.length == 0) {
            return retList;
        }

        for (FTPFile ftpFile : ftpFiles) {
            retList.add(ftpFile);
        }

        return retList;

    }


    /**
     * download ftpfiles from remote path recursively, follow the folder structure with the remote path
     *
     * @param ftpFileName remote ftp file
     * @param localDir    local dir or file
     */
    public void downFileRecursively(String ftpFileName, String localDir) throws IOException {

        File file = new File(ftpFileName);
        File temp = new File(localDir);
        FileOutputStream fos = null;
        if (!temp.exists()) {
            temp.mkdirs();
            logger.info("--------making a dir:" + temp.getAbsolutePath());
        }
        if (isDir(ftpFileName)) {
            String[] names = ftpClient.listNames();
            logger.info(String.format("----------names:%s", JSON.toJSON(names)));
            for (int i = 0; i < names.length; i++) {
                logger.info("----------names[i]:" + names[i]);
                if (isDir(names[i])) {
                    logger.info("=========dir:" + names[i]);
                    downFileRecursively(ftpFileName + "/" + names[i], localDir + "/" + names[i]);
                    ftpClient.changeToParentDirectory();
                } else {
                    File localfile = new File(localDir + "/" + names[i]);
                    logger.info("=========file:" + names[i]);
                    logger.info("=========localfile_path:" + localfile.getPath());
                    logger.info("=========localfile_absolute_path:" + localfile.getAbsolutePath());
                    if (!localfile.exists()) {
                        logger.info("111");
                        fos = new FileOutputStream(localfile);
                        logger.info("111_1");
                        ftpClient.retrieveFile(names[i], fos);
                    } else {
                        file.delete();
                        logger.info("222");
                        fos = new FileOutputStream(localfile);
                        ftpClient.retrieveFile(ftpFileName, fos);
                    }
                }
            }
        } else {
            File localfile = new File(localDir + "/" + file.getName());
            logger.info("else-----ftpFileName:" + ftpFileName);
            if (!localfile.exists()) {
                logger.info("333");
                fos = new FileOutputStream(localfile);
                ftpClient.retrieveFile(ftpFileName, fos);
            } else {
                file.delete();
                logger.info("444");
                fos = new FileOutputStream(localfile);
                ftpClient.retrieveFile(ftpFileName, fos);
            }
            ftpClient.changeToParentDirectory();
        }
        if (fos != null) {
            fos.close();
        }

    }


    /**
     * check if it's a directory
     *
     * @param fileName
     * @return
     */
    private boolean isDir(String fileName) throws IOException {
        return ftpClient.changeWorkingDirectory(fileName);
    }


    @Override
    public void close() throws IOException {

        try {
            if (ftpClient != null) {

                ftpClient.logout();
                ftpClient.disconnect();

            }
        } catch (Exception e) {

        }

    }

}