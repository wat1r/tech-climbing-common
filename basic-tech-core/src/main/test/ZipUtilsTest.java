import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.ZipUtils;


@Slf4j
public class ZipUtilsTest {


    @Test
    public void testZipDecompress() {

        String zipFilePath = "E:\\test\\test.zip";
        String targetDirPath = "E:\\test\\decompress\\";
        ZipUtils.decompressZip2Files(zipFilePath, targetDirPath);
        log.info("success targetDirPath:{}", targetDirPath);
//


    }


    @Test
    public void test() {


    }


}
