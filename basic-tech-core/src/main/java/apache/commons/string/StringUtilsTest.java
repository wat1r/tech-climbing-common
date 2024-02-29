package apache.commons.string;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author Frank Cooper(wang zhou)
 * @Date: 2024/02/29/ 14:15
 * @description
 */
public class StringUtilsTest {
    public static void main(String[] args) {
        strip();
    }

    private static void strip() {
        String oldStr = "{[Hello,word!!!]}";
//返回结果：[Hello,word!!!]
        String newStr = StringUtils.strip(oldStr, "{}");
        System.out.println(newStr);
         oldStr = " {[Hello,word!!!]} ";
        newStr = StringUtils.strip(StringUtils.strip(oldStr), "{}");
        System.out.println(newStr);
    }
}
