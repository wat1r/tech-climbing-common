package com.antlr4;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

/**
 * @Date 2020/9/8
 * @Author Frank Cooper
 * @Description
 */
public class Antlr4Test {

    public static void main(String[] args) throws IOException {
        //输入文本hello world
        ANTLRInputStream inputStream = new ANTLRInputStream(" hello world");
        //词法分析器
        LearnAntlrLexer lexer = new LearnAntlrLexer(inputStream);
        //新建词法符号缓冲区，用于存储词法分析器生成的词法符号
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        //新建语法分析器，处理词法符号缓冲区内容
        LearnAntlrParser parser = new LearnAntlrParser(tokenStream);
        //正对规则开始词法分析
        LearnAntlrParser.RContext context = parser.r();
        //构建监听器
        ListenerRewrite listener = new ListenerRewrite();
        //使用监听器初始化对词法分析树遍历
        ParseTreeWalker.DEFAULT.walk(listener, context);
    }
}
