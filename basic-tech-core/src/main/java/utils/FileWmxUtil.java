package utils;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by FrankCooper
 * Date 2019/1/20 14:43
 * Description 自定义文件工具类
 */
public class FileWmxUtil {

    public static void main(String[] args) {
        File fileDir = new File("E:\\gxg\\resources\\docs");
    }

    /**
     * 遍历目录下的所有文件--方式1
     *
     * @param targetDir
     */
    public static Collection<File> localListFiles(File targetDir) {
        Collection<File> fileCollection = new ArrayList<>();
        if (targetDir != null && targetDir.exists() && targetDir.isDirectory()) {
            /**
             * targetDir：不要为 null、不要是文件、不要不存在
             * 第二个 文件过滤 参数如果为 FalseFileFilter.FALSE ，则不会查询任何文件
             * 第三个 目录过滤 参数如果为 FalseFileFilter.FALSE , 则只获取目标文件夹下的一级文件，而不会迭代获取子文件夹下的文件
             */
            fileCollection = FileUtils.listFiles(targetDir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        }
        return fileCollection;
    }
}




