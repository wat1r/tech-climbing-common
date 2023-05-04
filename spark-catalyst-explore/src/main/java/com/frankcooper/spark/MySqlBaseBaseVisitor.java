package com.frankcooper.spark;

import org.apache.spark.sql.catalyst.parser.SqlBaseBaseVisitor;
import org.apache.spark.sql.catalyst.parser.SqlBaseParser;

public class MySqlBaseBaseVisitor extends SqlBaseBaseVisitor<Void> {


    @Override
    public Void visitSingleTableIdentifier(SqlBaseParser.SingleTableIdentifierContext ctx) {
        System.out.println("==============1==============");
        System.out.println(ctx.tableIdentifier().toString());
        System.out.println(ctx.getText());
        return null;
    }


    @Override
    public Void visitTableName(SqlBaseParser.TableNameContext ctx) {
        System.out.println("==============2==============");
        System.out.println(ctx.getText());
//        System.out.println(ctx.tableIdentifier().toString());
        return null;
    }


    @Override
    public Void visitSingleDataType(SqlBaseParser.SingleDataTypeContext ctx) {
        System.out.println("==============3==============");
        System.out.println(ctx.getText());
        return super.visitSingleDataType(ctx);
    }

    @Override
    public Void visitSingleTableSchema(SqlBaseParser.SingleTableSchemaContext ctx) {
        System.out.println("==============4==============");
        System.out.println(ctx.getText());
        return null;
    }

    @Override
    public Void visitTable(SqlBaseParser.TableContext ctx) {
        System.out.println("==============5==============");
        System.out.println(ctx.getText());
        return null;
    }


}
