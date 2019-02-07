package importNew.bio.nio2;

import lombok.Data;
import lombok.ToString;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * Created by FrankCooper
 * Date 2019/1/22 22:07
 * Description
 */
@Data
@ToString
public class Attachment {
    private AsynchronousServerSocketChannel server;
    private AsynchronousSocketChannel client;
    private boolean isReadMode;
    private ByteBuffer buffer;
}
