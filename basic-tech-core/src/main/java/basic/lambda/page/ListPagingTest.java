package basic.lambda.page;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * @Date 2020/5/28
 * @Author Frank Cooper
 * @Description
 */
public class ListPagingTest {

    static ListPagingTest handler = new ListPagingTest();

    public static void main(String[] args) throws IOException {
        handler.pageTest();


    }


    private void pageTest() throws IOException {

        JSONObject jsonObject = JSONObject.parseObject(FileUtils.readFileToString(new File("D:\\Dev\\Documents\\GFile\\dev\\page_test\\page_test.json"), Charset.defaultCharset()));
        JSONArray body = jsonObject.getJSONArray("body");
        List<Object[]> list = JSONObject.parseArray(body.toJSONString(), Object[].class);
        PagingList<Object[]> paging = new PagingList<>(list, 10);
        stepOne(paging);
    }


    private void stepOne(PagingList<Object[]> paging) {
        System.out.println("---2--");
        List<Object[]> byPageNum = paging.getByPageNum(2);
        print(byPageNum);
        System.out.println("---3--");
        byPageNum = paging.getByPageNum(3);
        print(byPageNum);
        System.out.println("---4--");
        byPageNum = paging.getByPageNum(4);
        print(byPageNum);
        System.out.println("---5--");
        byPageNum = paging.getByPageNum(6000);
        print(byPageNum);
//        while (paging.hasNext()) {
//            List<Object[]> next = paging.next();
//            for (Object[] objects : next) {
//                System.out.println(JSONObject.toJSONString(objects));
//            }
//            System.out.println("-----");
//        }
    }


    private void print(List<Object[]> byPageNum) {
        for (Object[] objects : byPageNum) {
            System.out.println(JSONObject.toJSONString(objects));
        }
    }

}
