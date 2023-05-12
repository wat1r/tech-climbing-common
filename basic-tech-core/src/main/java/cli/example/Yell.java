package cli.example;

import cli.one.*;

public class Yell implements Command {

    @Arg(name="Text to yell", optional=true)
    public String text = "Hey there!";

    @Opt(opt="n", longOpt="repeat", description="Number of times to yell the text")
    public Number yells = 1;

    @Override
    public void exec(CommandContext commandLine) throws CommandError, Exception {
        for(int i = 0; i < yells.intValue(); i++) System.out.println(text);
    }
}
