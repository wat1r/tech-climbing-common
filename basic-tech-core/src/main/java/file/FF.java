package file;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class FF {
    public static void main(String[] args) throws IOException {
        process();
    }

    private static void process() throws IOException {
        String path = "C:\\Users\\wangzhou\\Downloads\\000000_0_1";
//        String path = "C:\\Users\\wangzhou\\Downloads\\000000_0";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {


                System.out.println();
            }

        }


//        List<String> lines = FileUtils.readLines(new File(path), Charset.defaultCharset());
//        for (String line : lines) {
//            String[] arr = line.split("\t");
//
//            System.out.println(JSON.toJSON(arr));
//
//        }

    }
}
