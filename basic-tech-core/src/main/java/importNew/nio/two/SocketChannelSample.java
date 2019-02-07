package importNew.nio.two;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Pipe;
import java.nio.channels.SocketChannel;

/**
 * Created by FrankCooper
 * Date 2019/2/4 14:55
 * Description
 */
public class SocketChannelSample {




    public static void stepOne(){
        try {
            Pipe pipe = Pipe.open();
            Pipe.SinkChannel sink = pipe.sink();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("http://jenkov.com", 80));



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
