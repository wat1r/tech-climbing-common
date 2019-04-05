package interview.two;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by FrankCooper
 * Date 2019/3/31 9:53
 * Description
 */
public class IOTest {

    public static void copyFile(String src, String dist) throws IOException {

        FileInputStream in = new FileInputStream(src);
        FileOutputStream out = new FileOutputStream(dist);
        byte[] buffer = new byte[20 * 1024];
        int cnt;
        // read() 最多读取 buffer.length 个字节
        // 返回的是实际读取的个数
        // 返回 -1 的时候表示读到 eof，即文件尾
        while ((cnt = in.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, cnt);
        }
        in.close();
        out.close();

    }


    /**
     * 实现逐行输出文本文件的内容
     *
     * @param filePath
     */
    public static void readFileContent(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        // 装饰者模式使得 BufferedReader 组合了一个 Reader 对象
        // 在调用 BufferedReader 的 close() 方法时会去调用 Reader 的 close() 方法
        // 因此只要一个 close() 调用即可
        reader.close();
    }


    /**
     * NIO 快速复制文件
     *
     * @param src
     * @param dist
     */
    public static void fastCopy(String src, String dist) throws IOException {
        /* 获得源文件的输入字节流 */
        FileInputStream fin = new FileInputStream(src);
    /* 获取输入字节流的文件通道 */
        FileChannel fcin = fin.getChannel();
    /* 获取目标文件的输出字节流 */
        FileOutputStream fout = new FileOutputStream(dist);
    /* 获取输出字节流的文件通道 */
        FileChannel fcout = fout.getChannel();
    /* 为缓冲区分配 1024 个字节 */
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (true) {
        /* 从输入通道中读取数据到缓冲区中 */
            int r = fcin.read(buffer);
        /* read() 返回 -1 表示 EOF */
            if (r == -1) {
                break;
            }
        /* 切换读写 */
            buffer.flip();
        /* 把缓冲区的内容写入输出文件中 */
            fcout.write(buffer);
        /* 清空缓冲区 */
            buffer.clear();
        }

    }


    public static void main(String[] args) {

    }
}
