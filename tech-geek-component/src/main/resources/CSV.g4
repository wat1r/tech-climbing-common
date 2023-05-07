grammar CSV;

@header {
    package com.com.frankcooper.antlr4.csv;
}

// 一个CSV由标题行和一个或多个的常规行组成
file : header row+;
// 标题行与常规行并没有区别
header : row;
// 常规行由一系列由逗号分隔的字段组成，并以换行符结束
row : field (',' field)* '\r'? '\n';

field : TEXT        # text
      | STRING      # string
      |             # empty
      ;

// 除,\r\n"之外的任意字符
TEXT : ~[,\r\n"]+;
// 两个双引号是对双引号的转义
STRING : '"' ('""'|~'"')* '"';