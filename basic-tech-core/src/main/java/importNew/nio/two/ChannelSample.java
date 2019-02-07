package importNew.nio.two;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by FrankCooper
 * Date 2019/2/4 12:01
 * Description
 */
public class ChannelSample {


    public static void stepOne() {
        try {
            InputStream is = ClassLoader.getSystemResourceAsStream("data/nio-data.txt");
            FileUtils.copyInputStreamToFile(is, new File("tmp.txt"));
            RandomAccessFile aFile = new RandomAccessFile("tmp.txt", "rw");
            FileChannel inChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(48);
            int bytesRead = inChannel.read(buf);
            while (bytesRead != -1) {
                System.out.println("Read " + bytesRead);
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                buf.clear();
                bytesRead = inChannel.read(buf);
            }
            aFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.deleteQuietly(new File("tmp.txt"));
        }
    }


    public static void main(String[] args) {
        stepOne();
    }
}
