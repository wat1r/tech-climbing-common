import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.compressors.CompressorException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CompressTest {
    private static Logger LOGGER = LoggerFactory.getLogger(CompressTest.class);

    @Test
    public void testFactory() throws CompressorException, FileNotFoundException, ArchiveException {
        File file = new File("src/main/resources/test/testStream");
//        CompressorOutputStream gzippedOut = new CompressorStreamFactory()
//                .createCompressorOutputStream(CompressorStreamFactory.GZIP, null);

        InputStream is = new FileInputStream(file);
        ArchiveInputStream input = new ArchiveStreamFactory()
                .createArchiveInputStream(is);
        LOGGER.debug("[---testFactory end---]");

    }


    @Test
    public void test() {


    }

}
