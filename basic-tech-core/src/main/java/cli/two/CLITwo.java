package cli.two;

import org.apache.commons.cli.*;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CLITwo {
    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        // 创建一个 option，并添加到 Options
        options.addOption("a", "all", false, "do not hide entries starting with .");
        // 使用构造器创建 option
        options.addOption(new Option("n", "name", true, "print info."));
        // 使用 Option.builder 创建，新版本不推荐使用 OptionBuilder 来创建 option
        options.addOption(Option.builder("f") //短key
                .longOpt("file") //长key
                .hasArg(true) //是否含有参数
                .argName("filePath") //参数值的名称
                .required(true) //命令行中必须包含此 option
                .desc("文件的路径") //描述
                .optionalArg(false) //参数的值是否可选
                .numberOfArgs(3) //指明参数有多少个参数值
                //.hasArgs() //无限制参数值个数
                .valueSeparator(',')// 参数值的分隔符
                .type(String.class) //参数值的类型
                .build());
        String[] arguments = new String[]{"-a",
//                "--file=/public/log,/public/config,/public/data",
                "--file", "/public/log,/public/config", "/public/data",
                "-n", "no one"};
        try {
            CommandLine line = parser.parse(options, arguments);
            // 验证解析结果
            assertEquals("有三个命令行参数", 3, line.getOptions().length);
            assertTrue("命令行参数中包含 a", line.hasOption("a"));
            assertTrue("命令行参数中包含 n", line.hasOption("name"));// 可通过 长key 获取 option
            assertTrue("命令行参数中包含 f", line.hasOption("f"));

            assertEquals("Option n 的参数值等于 no one", "no one", line.getOptionValue("n"));
// 没有直接获取到 Option 的方法，这里按照解析的顺序获取到 Option f
            Option option_f = line.getOptions()[1];
            assertEquals("Option f 有三个参数值", 3, option_f.getValues().length);
            assertEquals("Option f 的参数值类型为 String", String.class, option_f.getType());
            assertEquals("Option f 第一个参数值为 /public/log ", "/public/log", option_f.getValue(0));

            // 校验是否含有 f 这个 option
            if (line.hasOption("f")) {
                // 打印 file 参数值中的第三个参数
                System.out.println(line.getOptionValues("f")[2]);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
