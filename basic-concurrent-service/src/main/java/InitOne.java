import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitOne {

    private static Logger LOGGER = LoggerFactory.getLogger(InitOne.class);
    public static void main(String[] args) {


        LOGGER.info(String.format("test the list:%s", JSON.toJSON(Lists.newArrayList("oo"))));









    }


}
