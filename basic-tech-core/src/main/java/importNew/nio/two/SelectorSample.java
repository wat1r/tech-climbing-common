package importNew.nio.two;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.*;
import java.util.Set;

/**
 * Created by FrankCooper
 * Date 2019/2/4 13:59
 * Description
 */
public class SelectorSample {


    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();
            RandomAccessFile aFile = new RandomAccessFile("tmp.txt", "rw");
//            SelectableChannel channel =
            Set<SelectionKey> selectionKeys = selector.selectedKeys();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
