package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexOne {

    static RegexOne handler = new RegexOne();

    public static void main(String[] args) {
        handler.hasSensitiveSymbol(" ddd");
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
