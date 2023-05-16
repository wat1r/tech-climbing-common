package cli.three;

import org.apache.commons.cli.*;

import java.util.Arrays;
import java.util.Properties;

public class CLIThree {

    //https://blog.csdn.net/yamaxifeng_132/article/details/87822812
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("h", "help", false, "print options' information");
        options.addOption("d", "database", true, "name of a database");
        options.addOption("t", true, "name of a table");

        // withValueSeparator(char sep)指定参数值之间的分隔符
        Option filesOption = OptionBuilder.withArgName("args")
                .withLongOpt("files")
                .hasArgs()
                .withValueSeparator(',')
                .withDescription("file names")
                .create("f");
        options.addOption(filesOption);

        Option property = OptionBuilder.withArgName("property=name")
                .hasArgs()
                .withValueSeparator()
                .withDescription("use value for a property")
                .create("D");
        options.addOption(property);

        CommandLineParser parser = new GnuParser();
        try {
            CommandLine cli = parser.parse(options, args);
            if(cli.hasOption("h")){
                HelpFormatter hf = new HelpFormatter();
                hf.printHelp("Options", options);
            }
            else {
                String database = cli.getOptionValue("database");
                System.out.println("database: " + database);
                String table = cli.getOptionValue("t");
                System.out.println("table: " + table);
                String[] files = cli.getOptionValues("f");
                System.out.println("files: " + Arrays.asList(files));

                Properties properties = cli.getOptionProperties("D");
                String ext = properties.getProperty("ext");
                String dir = properties.getProperty("dir");
                System.out.println("property ext: " + ext + "\tdir:" + dir);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
