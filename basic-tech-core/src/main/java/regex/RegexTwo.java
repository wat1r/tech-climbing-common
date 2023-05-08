package regex;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTwo {

    final static String _VALUE = "(?<=value=\")([0-9]+)(?=\")";
    final static String _DESC = "(?<=desc=\")(.*)(?=\")";


    public static void main(String[] args) throws IOException {
        process();
    }

    private static void process() throws IOException {

        Collection<File> files = FileUtils.listFiles(new File("D:\\Dev\\Documents\\GFile\\dev\\xml\\input\\"), null, false);
        for (File file : files) {
            List<String> lines = FileUtils.readLines(file, Charset.defaultCharset());
            LinkedHashSet<String> results = new LinkedHashSet<>();
            for (String line : lines) {
                Pattern pattern = Pattern.compile(_VALUE, Pattern.MULTILINE);
                Matcher matcher = pattern.matcher(line);
                StringBuilder sb = null;
                if (matcher.find()) {
                    sb = new StringBuilder();
                    sb.append(matcher.group(0)).append("\t");
                }
                pattern = Pattern.compile(_DESC, Pattern.MULTILINE);
                matcher = pattern.matcher(line);
                if (sb == null) {
                    continue;
                }
                if (matcher.find()) {
                    sb.append(matcher.group(0).replaceAll("\t", ""));
                }
                results.add(sb.toString());
            }
            File outputPath = new File(String.format("%s\\result_%s", file.getParent().replace("input", "output"), file.getName()));
            FileUtils.deleteQuietly(outputPath);
            FileUtils.writeLines(outputPath, results);
        }


        System.out.println("");


    }
}
