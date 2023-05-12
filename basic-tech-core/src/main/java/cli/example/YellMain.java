package cli.example;


import cli.one.CommandSet;

public class YellMain {

    public static void main(String[] args) {
        CommandSet app = new CommandSet("yellmain");
        app.addSubCommands(Yell.class);
        app.invoke(args);
    }
}
