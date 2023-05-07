package com.frankcooper.antlr4.json;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JSONMain {
    public static void main(String[] args) throws IOException {
        //https://blog.csdn.net/qq_37771475/article/details/106553162
        /**
         * {
         *     "id" : 1,
         *     "name" : "Li",
         *     "scores" : {
         *         "Chinese" : "95",
         *         "English" : "85"
         *     },
         *     "array" : [1.2, 2.0e1, -3]
         * }
         */

        BufferedReader reader = new BufferedReader(new FileReader("D:\\Dev\\Documents\\GFile\\dev\\antlr4\\json\\data.json"));
        ANTLRInputStream inputStream = new ANTLRInputStream(reader);
        JSONLexer lexer = new JSONLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        JSONParser parser = new JSONParser(tokenStream);
        ParseTree parseTree = parser.json();
        System.out.println(parseTree.toStringTree());

        ParseTreeWalker walker = new ParseTreeWalker();
        JSONToXMLListener listener = new JSONToXMLListener();
        walker.walk(listener, parseTree);

        String xml = listener.getXml(parseTree);
        System.out.println(xml);

    }
}
