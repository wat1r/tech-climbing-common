package basic.effective.stepone;

import java.util.*;

/**
 * Created by FrankCooper
 * Date 2019/5/31 23:38
 * Description
 */
public class PhoneNumber {
    private final int areaCode;
    private final int prefix;
    private final int lineNumber;

    public PhoneNumber(int areaCode, int prefix, int lineNumber) {
        rangeCheck(areaCode, 999, "area code");
        rangeCheck(prefix, 999, "prefix");
        rangeCheck(lineNumber, 999, "line number");
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNumber = lineNumber;
    }

    private static void rangeCheck(int arg, int max, String name) {
        if (arg < 0 || arg > max) {
            throw new IllegalArgumentException(name + " " + arg);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof PhoneNumber))
            return false;
        PhoneNumber p = (PhoneNumber) obj;
        return p.areaCode == areaCode &&
                p.prefix == prefix &&
                p.lineNumber == lineNumber;

    }

    @Override
    public int hashCode() {
        return 42;
    }

    public static void main(String[] args) {
        Map<PhoneNumber,String> map = new HashMap<PhoneNumber, String>();
        map.put(new PhoneNumber(707,867,77),"Jenny");
        System.out.println(map.get(new PhoneNumber(707,867,77)));

    }
}
