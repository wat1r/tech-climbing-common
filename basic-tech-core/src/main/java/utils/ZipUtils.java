package utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class ZipUtils {


    private static Logger LOGGER = LoggerFactory.getLogger(ZipUtils.class);


    /**
     * 将文件打包成zip压缩包文件
     *
     * @param files              要压缩的文件
     * @param zipFile            压缩文件
     * @param deleteFileAfterZip 压缩文件后删除原来的文件，临时文件时记得删除
     * @return 是否压缩成功
     */
    public static boolean compressFiles2Zip(List<File> files, File zipFile, boolean deleteFileAfterZip) {

        InputStream inputStream = null;
        ZipArchiveOutputStream zipArchiveOutputStream = null;
        try {
            zipArchiveOutputStream = new ZipArchiveOutputStream(zipFile);
            //Use Zip64 extensions for all entries where they are required
            zipArchiveOutputStream.setUseZip64(Zip64Mode.AsNeeded);
            for (File file : files) {
                //将每个文件用ZipArchiveEntry封装，使用ZipArchiveOutputStream写到压缩文件
                ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, file.getName());
                zipArchiveOutputStream.putArchiveEntry(zipArchiveEntry);

                inputStream = new FileInputStream(file);
                byte[] buffer = new byte[1024 * 5];
                int len = -1;
                while ((len = inputStream.read(buffer)) != -1) {
                    //把缓冲区的字节写入到ZipArchiveEntry
                    zipArchiveOutputStream.write(buffer, 0, len);
                }
            }
            zipArchiveOutputStream.closeArchiveEntry();
            zipArchiveOutputStream.finish();

            if (deleteFileAfterZip) {
                for (File file : files) {
                    file.deleteOnExit();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                //关闭输入流
                if (null != inputStream) {
                    inputStream.close();
                }
                //关闭输出流
                if (null != zipArchiveOutputStream) {
                    zipArchiveOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 将zip压缩包解压成文件到指定文件夹
     *
     * @param zipFilePath   要解压的文件
     * @param targetDirPath 解压的目的路径
     * @return 是否成功
     */
    public static boolean decompressZip2Files(String zipFilePath, String targetDirPath) {

        InputStream inputStream = null;
        OutputStream outputStream = null;
        //zip文件输入流
        ZipArchiveInputStream zipArchiveInputStream = null;
        ArchiveEntry archiveEntry = null;
        try {
            File zipFile = new File(zipFilePath);
            inputStream = new FileInputStream(zipFile);
            zipArchiveInputStream = new ZipArchiveInputStream(inputStream, "UTF-8");

            while (null != (archiveEntry = zipArchiveInputStream.getNextEntry())) {
                //获取文件名
                String archiveEntryFileName = archiveEntry.getName();
                //构造解压后文件的存放路径
                String archiveEntryPath = targetDirPath + archiveEntryFileName;
                //把解压出来的文件写到指定路径
                File entryFile = new File(archiveEntryPath);
                if (!entryFile.exists()) {
                    boolean mkdirs = entryFile.getParentFile().mkdirs();

                }
                byte[] buffer = new byte[1024 * 5];
                outputStream = new FileOutputStream(entryFile);
                int len = -1;
                while ((len = zipArchiveInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
                if (null != zipArchiveInputStream) {
                    zipArchiveInputStream.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    public static void main(String[] args) {
        List<File> files = new ArrayList<File>() {
            {
                add(new File("E:\\test\\new 2.txt"));
                add(new File("E:\\test\\W020180724050657.doc"));
                add(new File("E:\\test\\W020180724050636.doc"));
            }
        };
        File zipFile = new File("E:\\test\\test.zip");
        boolean deleteFileAfterZip = false;
        compressFiles2Zip(files, zipFile, deleteFileAfterZip);
        log.info("|-------main end------|");
    }


}
