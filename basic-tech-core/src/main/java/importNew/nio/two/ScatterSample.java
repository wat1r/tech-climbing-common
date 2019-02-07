package importNew.nio.two;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by FrankCooper
 * Date 2019/2/4 13:48
 * Description
 */
public class ScatterSample {


    public static void main(String[] args) {
        try {

            ByteBuffer header = ByteBuffer.allocate(128);
            ByteBuffer body = ByteBuffer.allocate(1024);
            ByteBuffer[] bufferArray = {
                    header, body
            };
            RandomAccessFile aFile = new RandomAccessFile("tmp.txt", "rw");
            FileChannel readChannel = aFile.getChannel();
            readChannel.read(bufferArray);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
