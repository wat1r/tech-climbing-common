grammar Calculator;

@header {
    package com.frankcooper.antlr4.calculator;
}

// stmt是语法树的根
stmt: expr;

expr: '(' expr ')' # Bracket // 表达式可以被 括号包裹
    | expr MUL expr # Mul // 表达式可以是表达式和表达式运算
    | expr DIV expr # Div
    | expr ADD expr # Add
    | expr MIN expr # Min
    | INT # Int
    ;

INT: DIGIT+; //  定义一个数字类型, 提高可读性和简化定义
DIGIT: [0-9];

MUL: '*';
DIV: '/';
ADD: '+';
MIN: '-';

WS: [ \t\r\n\u000c]+ -> skip; // 跳过的字符其中 \u000c是换页符

SHEBANG: '#' '!' ~('\n'|'\r')* -> channel(HIDDEN); // 不需要处理的放入隐藏通道