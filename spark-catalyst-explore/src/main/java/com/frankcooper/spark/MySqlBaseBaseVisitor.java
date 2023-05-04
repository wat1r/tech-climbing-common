package com.frankcooper.spark;

import org.antlr.v4.runtime.tree.ParseTree;
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

    @Override
    public Void visitInsertOverwriteTable(SqlBaseParser.InsertOverwriteTableContext ctx) {
        System.out.println("==============5==============");
        System.out.println(ctx.getText());
        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree child = ctx.getChild(i);
            System.out.println(child.getText());
            visit(child);
        }
        return null;
    }

    @Override
    public Void visitCreateTable(SqlBaseParser.CreateTableContext ctx) {
        System.out.println("==============6==============");
        System.out.println(ctx.getText());
        return null;
    }

    @Override
    public Void visitCreateHiveTable(SqlBaseParser.CreateHiveTableContext ctx) {
        System.out.println("==============7==============");
        System.out.println(ctx.getText());
//        for (int i = 0; i < ctx.getChildCount(); i++) {
//            ParseTree child = ctx.getChild(i);
//            System.out.println(child.getText());
//            visit(child);
//
//        }
        visit(ctx.query());
        return null;
    }

    @Override
    public Void visitCreateView(SqlBaseParser.CreateViewContext ctx) {
        System.out.println("==============8==============");
        System.out.println(ctx.getText());
        return null;
    }

    @Override
    public Void visitNamedQuery(SqlBaseParser.NamedQueryContext ctx) {
        System.out.println("==============9==============");
        System.out.println(ctx.getText());
        return null;
    }

    @Override
    public Void visitQuery(SqlBaseParser.QueryContext ctx) {
        System.out.println("==============10==============");
        System.out.println(ctx.getText());
        visit(ctx.queryNoWith());
        return null;
    }
}
