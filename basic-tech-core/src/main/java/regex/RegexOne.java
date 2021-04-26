package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexOne {

    static RegexOne handler = new RegexOne();

    public static void main(String[] args) {
//        handler.hasSensitiveSymbol(" ddd");
        Pattern pattern = Pattern.compile("^(?!(_|-|\\.))([a-zA-Z0-9_|-]|(\\.){0,1}).*(?<!(\\.|-|_))");
//        pattern = Pattern.compile("^[A-Za-z0-9]{1}[A-Za-z0-9_-]*?\\.?[A-Za-z0-9_-]*?[A-Za-z0-9]{1}$");
//        pattern = Pattern.compile("^(?![^.]*?\\.[^.]*?\\.)[a-zA-Z][a-zA-Z\\d_.-]*$");
        pattern = Pattern.compile("^(?![^.]*?\\.[^.]*?\\.)[a-zA-Z][a-zA-Z\\d_.-].*(?<!(\\.|-|_))");
        pattern =Pattern.compile("^(?![\\._\\-])[0-9a-zA-Z_\\-]*\\.?[0-9a-zA-Z_\\-]*(?<![\\._\\-])$");
        String[] strs = {"a123456888_", "a123456*", "Ua123456.", "Ua1.23.456", "asdf.as.dfa", "Ua1_2-3456","Ua1大23.456"};
        for (String str : strs) {
            handler.process(str, pattern);
        }

    }


    public boolean process(String str, Pattern pattern) {
//        Pattern pattern = Pattern.compile("[\\s\\\\/:\\*\\?\\\"<>\\|]");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            System.out.printf("%s--->%s\n", str, "true");
            return true;
        }
        System.out.printf("%s--->%s\n", str, "false");
        return false;
    }


    public boolean hasSensitiveSymbol(String name) {
//        Pattern pattern = Pattern.compile("[\\s\\\\/:\\*\\?\\\"<>\\|]");
        Pattern pattern = Pattern.compile(".*(\\s|\\?|\\|:|\\*|\\.|;|,|<|>|/|\").*");
        Matcher matcher = pattern.matcher(name);
        if (matcher.matches()) {
            System.out.println("dd");
        }
        return false;
    }


}
