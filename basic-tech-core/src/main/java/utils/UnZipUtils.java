package utils;

import net.sf.sevenzipjbinding.*;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Administrator on 2018/1/4.
 */
public class UnZipUtils {
    private FileOutputStream fos;  //输出流

    /**
     * @param filepath       7z文件
     * @param destinationDir 解压输出目标目录
     * @param password       7z文件密码
     */
    public void extractFile(String filepath, final String destinationDir) {
        RandomAccessFile randomAccessFile = null;
        IInArchive inArchive = null;
        try {
            randomAccessFile = new RandomAccessFile(filepath, "r");  //只读模式
            inArchive = SevenZip.openInArchive(null, //已7zip的文件形式开发
                    new RandomAccessFileInStream(randomAccessFile));  //2种模式一种有密码，一种无密码

            // 获得SimpleInArchive接口
            ISimpleInArchive simpleInArchive = inArchive.getSimpleInterface();

            for (final ISimpleInArchiveItem item : simpleInArchive.getArchiveItems()) {

                if (!item.isFolder()) {
                    ExtractOperationResult result;
                    // final long[] sizeArray = new long[1];

                    //判断是否存在目录
                    String subdir = item.getPath();
                    if (subdir.lastIndexOf("\\") > 0) {
                        subdir = subdir.substring(0, subdir.lastIndexOf("\\"));
                        File myfile = new File(destinationDir + subdir);
                        if (!myfile.exists()) {
                            myfile.mkdirs();
                        }
                        // System.out.println("创建文件夹"+destinationDir+subdir);
                    } else {
                        File myfile = new File(destinationDir);
                        if (!myfile.exists()) {
                            myfile.mkdirs();
                        }
                    }

                    File file = new File(destinationDir + item.getPath());

                    fos = new FileOutputStream(file);

                    //解压缩处理带密码的程序
                    result = item.extractSlow(new ISequentialOutStream() {
                                                  @Override
                                                  public int write(byte[] data) throws SevenZipException {

                                                      try {
                                                          fos.write(data);
                                                      } catch (IOException e) {

                                                          e.printStackTrace();
                                                      }


                                                      return data.length;
                                                  }
                                              }   //密码关键这里
                    );

                    if (result == ExtractOperationResult.OK) {
                        fos.close();  //写完才关闭输出流
                        System.out.println();

                    } else {
                        System.err.println("Error extracting item: " + result);
                    }

                } //if 结束
            } //for 循环

        } catch (Exception e) {
            System.err.println("Error occurs: " + e);
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (inArchive != null) {
                try {
                    inArchive.close();
                } catch (SevenZipException e) {
                    System.err.println("Error closing archive: " + e);
                }
            }

            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    System.err.println("Error closing file: " + e);
                }
            }

        }
    }
}
