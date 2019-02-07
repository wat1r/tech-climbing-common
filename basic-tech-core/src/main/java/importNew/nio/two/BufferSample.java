package importNew.nio.two;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * Created by FrankCooper
 * Date 2019/2/4 13:37
 * Description
 */
public class BufferSample {




    public static void  initFile() throws IOException {
        InputStream is = ClassLoader.getSystemResourceAsStream("data/nio-data.txt");
        FileUtils.copyInputStreamToFile(is, new File("tmp.txt"));
        RandomAccessFile aFile = new RandomAccessFile("tmp.txt", "rw");
    }




    public static void main(String[] args) {

    }
}
