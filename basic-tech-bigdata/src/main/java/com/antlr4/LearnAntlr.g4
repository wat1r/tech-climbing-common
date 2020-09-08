grammar LearnAntlr ;         // grammer是规则文件的头，要和文件名一样
@header{                    //header代表生成的代码放在哪个包里面
package com.antlr4;
}
r  : 'hello' ID;           //r代表的是语法树的根结点
ID : [a-z]+ ;
WS : [ \t\r\n]+ -> skip ;  //ID代表未知的值