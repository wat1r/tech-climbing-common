package picocli;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/4/23 16:24
 * @description:
 */
public class PicocliTestOne {


    // 主函数
    public static void main(String[] args) {
        new CommandLine(new PicocliTestOne.RootCommand()).execute(args);
    }


    // 将所有子命令装载在一起
    @CommandLine.Command(name = "nr-cli",
            subcommands = {
                    // command1
                    testCommand.class,
                    // command2
                    test2Command.class
                    // 添加 yourCommand.class
            },
            mixinStandardHelpOptions = true
    )
    public static class RootCommand {
    }


    @CommandLine.Command(
            subcommands = {},
            name = "info-print",
            description = "填写个人信息.",
            sortOptions = false,
            mixinStandardHelpOptions = true
    )
    public static class testCommand implements Runnable {
        @CommandLine.Parameters(
                description = "输入你的姓名.",
                index = "0"
        )
        private String name;

        @CommandLine.Option(
                names = {"-a", "--age"},
                description = "输入你的年龄.",
                defaultValue = "1",
                showDefaultValue = CommandLine.Help.Visibility.ALWAYS
        )
        private int age;

        public void run() {
            System.out.println("你的名字:" + name);
            System.out.println("你的年龄:" + age);
        		/*
        		 ...
        		 ...
        		 */
        }
    }

    @CommandLine.Command(
            subcommands = {},
            name = "info2-print",
            description = "填写个人信息.",
            sortOptions = false,
            mixinStandardHelpOptions = true
    )
    public static class test2Command implements Runnable {
        @CommandLine.Parameters(
                description = "输入你的地址.",
                index = "0"
        )
        private String addr;

        @CommandLine.Option(
                names = {"-c", "--class"},
                description = "输入你的班级.",
                defaultValue = "1",
                showDefaultValue = CommandLine.Help.Visibility.ALWAYS
        )
        private int cla;

        public void run() {
            System.out.println("你的地址:" + addr);
            System.out.println("你的班级:" + cla);
        		/*
        		 ...
        		 ...
        		 */
        }
    }


}
