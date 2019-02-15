package io;

import org.apache.commons.lang3.time.DateUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestOne {
    public static void main(String[] args) {

        testOne();

    }


    private static void testOne()  {
        try {

            String yyyyMMdd = String.format("/data/patsnap_chemical_bak_%s", new SimpleDateFormat("yyyyMMdd").format(new Date()));


            BufferedWriter backupWriter = new BufferedWriter(new FileWriter("D:\\Test\\20190212", true));
//            BufferedWriter backupWriter = new BufferedWriter(new FileWriter("D:\\Test\\data\\backup\\20190212", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
