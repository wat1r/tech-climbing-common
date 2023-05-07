package com.frankcooper.antlr4.csv;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class CSVMain {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("D:\\Dev\\Documents\\GFile\\dev\\antlr4\\csv\\data.csv"));

        ANTLRInputStream inputStream = new ANTLRInputStream(reader);
        CSVLexer lexer = new CSVLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        CSVParser parser = new CSVParser(tokenStream);
        ParseTree parseTree = parser.file();
        System.out.println(parseTree.toStringTree(parser));

        CSVToMapListener listener = new CSVToMapListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, parseTree);

        List<Map<String, String>> rows = listener.getRows();
        System.out.println(rows);

    }
}
