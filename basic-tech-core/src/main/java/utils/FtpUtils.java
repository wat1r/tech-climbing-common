package utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by FrankCooper
 * Date 2019/1/20 14:19
 * Description:
 * 1.1.Quick Easy FTP Server V4.0.0
 * 1.2.https://blog.csdn.net/wangmx1993328/article/details/82150290
 */
public class FtpUtils {

    /**
     * 连接 FTP 服务器
     *
     * @param addr     FTP 服务器 IP 地址
     * @param port     FTP 服务器端口号
     * @param username 登录用户名
     * @param password 登录密码
     * @return
     * @throws Exception
     */
    public static FTPClient connectFtpServer(String addr, int port, String username, String password, String controlEncoding) {
        FTPClient ftpClient = new FTPClient();
        try {
            /**设置文件传输的编码*/
            ftpClient.setControlEncoding(controlEncoding);

            /**连接 FTP 服务器
             * 如果连接失败，则此时抛出异常，如ftp服务器服务关闭时，抛出异常：
             * java.net.ConnectException: Connection refused: connect*/
            ftpClient.connect(addr, port);
            /**登录 FTP 服务器
             * 1）如果传入的账号为空，则使用匿名登录，此时账号使用 "Anonymous"，密码为空即可*/
            if (StringUtils.isBlank(username)) {
                ftpClient.login("Anonymous", "");
            } else {
                ftpClient.login(username, password);
            }

            /** 设置传输的文件类型
             * BINARY_FILE_TYPE：二进制文件类型
             * ASCII_FILE_TYPE：ASCII传输方式，这是默认的方式
             * ....
             */
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            /**
             * 确认应答状态码是否正确完成响应
             * 凡是 2开头的 isPositiveCompletion 都会返回 true，因为它底层判断是：
             * return (reply >= 200 && reply < 300);
             */
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                /**
                 * 如果 FTP 服务器响应错误 中断传输、断开连接
                 * abort：中断文件正在进行的文件传输，成功时返回 true,否则返回 false
                 * disconnect：断开与服务器的连接，并恢复默认参数值
                 */
                ftpClient.abort();
                ftpClient.disconnect();
            } else {
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(">>>>>FTP服务器连接登录失败，请检查连接参数是否正确，或者网络是否通畅*********");
        }
        return ftpClient;
    }

    /**
     * 使用完毕，应该及时关闭连接
     * 终止 ftp 传输
     * 断开 ftp 连接
     *
     * @param ftpClient
     * @return
     */
    public static FTPClient closeFTPConnect(FTPClient ftpClient) {
        try {
            if (ftpClient != null && ftpClient.isConnected()) {
                ftpClient.abort();
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftpClient;
    }

//    public static void main(String[] args) throws Exception {
//        System.out.println("-----------------------应用启动------------------------");
//        FTPClient ftpClient = FtpUtils.connectFtpServer("192.168.1.104", 21, "anonymous", "", "utf-8");
//        System.out.println("FTP 连接是否成功：" + ftpClient.isConnected());
//        System.out.println("FTP 连接是否有效：" + ftpClient.isAvailable());
//        closeFTPConnect(ftpClient);
//        System.out.println("-----------------------应用关闭------------------------");
//
//    }


    /**
     * 下载 FTP 服务器上指定的单个文件，而且本地存放的文件相对部分路径 会与 FTP 服务器结构保持一致
     *
     * @param ftpClient              ：连接成功有效的 FTP客户端连接
     * @param absoluteLocalDirectory ：本地存储文件的绝对路径，如 E:\gxg\ftpDownload
     * @param relativeRemotePath     ：ftpFile 文件在服务器所在的绝对路径，此方法强制路径使用右斜杠"\"，如 "\video\2018.mp4"
     * @return
     */
    public static void downloadSingleFile(FTPClient ftpClient, String absoluteLocalDirectory, String relativeRemotePath) {
        /**如果 FTP 连接已经关闭，或者连接无效，则直接返回*/
        if (!ftpClient.isConnected() || !ftpClient.isAvailable()) {
            System.out.println(">>>>>FTP服务器连接已经关闭或者连接无效*********");
            return;
        }
        if (StringUtils.isBlank(absoluteLocalDirectory) || StringUtils.isBlank(relativeRemotePath)) {
            System.out.println(">>>>>下载时遇到本地存储路径或者ftp服务器文件路径为空，放弃...*********");
            return;
        }
        try {
            /**没有对应路径时，FTPFile[] 大小为0，不会为null*/
            FTPFile[] ftpFiles = ftpClient.listFiles(relativeRemotePath);
            FTPFile ftpFile = null;
            if (ftpFiles.length >= 1) {
                ftpFile = ftpFiles[0];
            }
            if (ftpFile != null && ftpFile.isFile()) {
                /** ftpFile.getName():获取的是文件名称，如 123.mp4
                 * 必须保证文件存放的父目录必须存在，否则 retrieveFile 保存文件时报错
                 */
                File localFile = new File(absoluteLocalDirectory, relativeRemotePath);
                if (!localFile.getParentFile().exists()) {
                    localFile.getParentFile().mkdirs();
                }
                OutputStream outputStream = new FileOutputStream(localFile);
                String workDir = relativeRemotePath.substring(0, relativeRemotePath.lastIndexOf("\\"));
                if (StringUtils.isBlank(workDir)) {
                    workDir = "/";
                }
                /**文件下载前，FTPClient工作目录必须切换到文件所在的目录，否则下载失败
                 * "/" 表示用户根目录*/
                ftpClient.changeWorkingDirectory(workDir);
                /**下载指定的 FTP 文件 到本地
                 * 1)注意只能是文件，不能直接下载整个目录
                 * 2)如果文件本地已经存在，默认会重新下载
                 * 3)下载文件之前，ftpClient 工作目录必须是下载文件所在的目录
                 * 4)下载成功返回 true，失败返回 false
                 */
                ftpClient.retrieveFile(ftpFile.getName(), outputStream);

                outputStream.flush();
                outputStream.close();
                System.out.println(">>>>>FTP服务器文件下载完毕*********" + ftpFile.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 遍历 FTP 服务器指定目录下的所有文件(包含子孙文件)
     *
     * @param ftpClient        ：连接成功有效的 FTP客户端连接
     * @param remotePath       ：查询的 FTP 服务器目录，如果文件，则视为无效，使用绝对路径，如"/"、"/video"、"\\"、"\\video"
     * @param relativePathList ：返回查询结果，其中为服务器目录下的文件相对路径，如：\1.png、\docs\overview-tree.html 等
     * @return
     */
    public static List<String> loopServerPath(FTPClient ftpClient, String remotePath, List<String> relativePathList) {
        /**如果 FTP 连接已经关闭，或者连接无效，则直接返回*/
        if (!ftpClient.isConnected() || !ftpClient.isAvailable()) {
            System.out.println("ftp 连接已经关闭或者连接无效......");
            return relativePathList;
        }
        try {
            /**转移到FTP服务器根目录下的指定子目录
             * 1)"/"：表示用户的根目录，为空时表示不变更
             * 2)参数必须是目录，当是文件时改变路径无效
             * */
            ftpClient.changeWorkingDirectory(remotePath);
            /** listFiles：获取FtpClient连接的当前下的一级文件列表(包括子目录)
             * 1）FTPFile[] ftpFiles = ftpClient.listFiles("/docs/info");
             *      获取服务器指定目录下的子文件列表(包括子目录)，以 FTP 登录用户的根目录为基准，与 FTPClient 当前连接目录无关
             * 2）FTPFile[] ftpFiles = ftpClient.listFiles("/docs/info/springmvc.txt");
             *      获取服务器指定文件，此时如果文件存在时，则 FTPFile[] 大小为 1，就是此文件
             * */
            FTPFile[] ftpFiles = ftpClient.listFiles();
            if (ftpFiles != null && ftpFiles.length > 0) {
                for (FTPFile ftpFile : ftpFiles) {
                    if (ftpFile.isFile()) {
                        String relativeRemotePath = remotePath + "\\" + ftpFile.getName();
                        relativePathList.add(relativeRemotePath);
                    } else {
                        loopServerPath(ftpClient, remotePath + "\\" + ftpFile.getName(), relativePathList);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return relativePathList;
    }


    /**
     * 上传本地文件 或 目录 至 FTP 服务器----保持 FTP 服务器与本地 文件目录结构一致
     *
     * @param ftpClient  连接成功有效的 FTPClinet
     * @param uploadFile 待上传的文件 或 文件夹(此时会遍历逐个上传)
     * @throws Exception
     */
    public static void uploadFiles(FTPClient ftpClient, File uploadFile) {
        /**如果 FTP 连接已经关闭，或者连接无效，则直接返回*/
        if (!ftpClient.isConnected() || !ftpClient.isAvailable()) {
            System.out.println(">>>>>FTP服务器连接已经关闭或者连接无效*****放弃文件上传****");
            return;
        }
        if (uploadFile == null && !uploadFile.exists()) {
            System.out.println(">>>>>待上传文件为空或者文件不存在*****放弃文件上传****");
            return;
        }
        try {
            if (uploadFile.isDirectory()) {
                /**如果被上传的是目录时
                 * makeDirectory：在 FTP 上创建目录(方法执行完，服务器就会创建好目录，如果目录本身已经存在，则不会再创建)
                 * 1）可以是相对路径，即不以"/"开头，相对的是 FTPClient 当前的工作路径，如 "video"、"视频" 等，会在当前工作目录进行新建目录
                 * 2）可以是绝对路径，即以"/"开头，与 FTPCLient 当前工作目录无关，如 "/images"、"/images/2018"
                 * 3）注意多级目录时，必须确保父目录存在，否则创建失败，
                 *      如 "video/201808"、"/images/2018" ，如果 父目录 video与images不存在，则创建失败
                 * */
                ftpClient.makeDirectory(uploadFile.getName());
                /**变更 FTPClient 工作目录到新目录
                 * 1)不以"/"开头表示相对路径，新目录以当前工作目录为基准，即当前工作目录下不存在此新目录时，变更失败
                 * 2)参数必须是目录，当是文件时改变路径无效*/
                ftpClient.changeWorkingDirectory(uploadFile.getName());

                File[] listFiles = uploadFile.listFiles();
                for (int i = 0; i < listFiles.length; i++) {
                    File loopFile = listFiles[i];
                    if (loopFile.isDirectory()) {
                        /**如果有子目录，则迭代调用方法进行上传*/
                        uploadFiles(ftpClient, loopFile);
                        /**changeToParentDirectory：将 FTPClient 工作目录移到上一层
                         * 这一步细节很关键，子目录上传完成后，必须将工作目录返回上一层，否则容易导致文件上传后，目录不一致
                         * */
                        ftpClient.changeToParentDirectory();
                    } else {
                        /**如果目录中全是文件，则直接上传*/
                        FileInputStream input = new FileInputStream(loopFile);
                        ftpClient.storeFile(loopFile.getName(), input);
                        input.close();
                        System.out.println(">>>>>文件上传成功****" + loopFile.getPath());
                    }
                }
            } else {
                /**如果被上传的是文件时*/
                FileInputStream input = new FileInputStream(uploadFile);
                /** storeFile:将本地文件上传到服务器
                 * 1）如果服务器已经存在此文件，则不会重新覆盖,即不会再重新上传
                 * 2）如果当前连接FTP服务器的用户没有写入的权限，则不会上传成功，但是也不会报错抛异常
                 * */
                ftpClient.storeFile(uploadFile.getName(), input);
                input.close();
                System.out.println(">>>>>文件上传成功****" + uploadFile.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 同步本地目录与 FTP 服务器目录
     * 1）约定：FTP 服务器有，而本地没有的，则下载下来；本地有，而ftp服务器没有的，则将本地多余的删除
     * 2）始终确保本地与 ftp 服务器内容一致
     * 2）让 FTP 服务器与 本地目录保持结构一致，如 服务器上是 /docs/overview-tree.html，则本地也是 localDir/docs/overview-tree.html
     *
     * @param ftpClient        连接成功有效的 FTPClinet
     * @param localSyncFileDir ：与 FTP 目录进行同步的本地目录
     */
    public static void syncLocalDir(FTPClient ftpClient, String localSyncFileDir) throws IOException {
        /**如果 FTP 连接已经关闭，或者连接无效，则直接返回*/
        if (!ftpClient.isConnected() || !ftpClient.isAvailable() || StringUtils.isBlank(localSyncFileDir)) {
            System.out.println(">>>>>FTP服务器连接已经关闭或者连接无效*********");
            return;
        }

        /** 获取本地存储目录下的文件*/
        Collection<File> fileCollection = FileWmxUtil.localListFiles(new File(localSyncFileDir));
        System.out.println(">>>>>本地存储目录共有文件数量*********" + fileCollection.size());

        /**获取 FTP 服务器下的相对路径*/
        List<String> relativePathList = new ArrayList<>();
        relativePathList = loopServerPath(ftpClient, "", relativePathList);
        System.out.println(">>>>>FTP 服务器端共有文件数量*********" + relativePathList.size());

        /**
         * 遍历 本地存储目录下的文件
         * 1）如果本地文件在 FTP 服务器上不存在，则删除它
         * 2）如果本地文件在 FTP 服务器上存在，则比较两种大小
         *    如果大小不一致，则重新下载
         */
        for (File localFile : fileCollection) {
            String localFilePath = localFile.getPath();
            String localFileSuffi = localFilePath.replace(localSyncFileDir, "");
            if (relativePathList.contains(localFileSuffi)) {
                /**本地此文件在 FTP 服务器存在
                 * 1)比较大小，如果本地文件与服务器文件大小一致，则跳过
                 * 2）如果大小不一致，则删除本地文件，重新下载
                 * 3）最后都要删除relativePathList中的此元素，减轻后一次循环的压力*/
                FTPFile[] ftpFiles = ftpClient.listFiles(localFileSuffi);
                System.out.println(">>>>>本地文件 在 FTP 服务器已存在*********" + localFile.getPath());
                if (ftpFiles.length >= 1 && localFile.length() != ftpFiles[0].getSize()) {
                    downloadSingleFile(ftpClient, localSyncFileDir, localFileSuffi);
                    System.out.println(">>>>>本地文件与 FTP 服务器文件大小不一致，重新下载*********" + localFile.getPath());
                }
                relativePathList.remove(localFileSuffi);
            } else {
                System.out.println(">>>>>本地文件在 FTP 服务器不存在，删除本地文件*********" + localFile.getPath());
                /**本地此文件在 FTP 服务器不存在
                 * 1)删除本地文件
                 * 2）如果当前文件所在目录下文件已经为空，则将此父目录也一并删除*/
                localFile.delete();
                File parentFile = localFile.getParentFile();
                while (parentFile.list().length == 0) {
                    parentFile.delete();
                    parentFile = parentFile.getParentFile();
                }
            }
        }
        for (int i = 0; i < relativePathList.size(); i++) {
            System.out.println(">>>>> FTP 服务器存在新文件，准备下载*********" + relativePathList.get(i));
            downloadSingleFile(ftpClient, localSyncFileDir, relativePathList.get(i));
        }
    }




}
