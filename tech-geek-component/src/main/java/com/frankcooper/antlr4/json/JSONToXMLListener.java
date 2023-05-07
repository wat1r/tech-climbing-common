package com.frankcooper.antlr4.json;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class JSONToXMLListener extends JSONBaseListener {

    private ParseTreeProperty<String> xml = new ParseTreeProperty<>();


    public void setXml(ParseTree node, String value) {
        xml.put(node, value);
    }

    public String getXml(ParseTree node) {
        return xml.get(node);
    }


    @Override
    public void exitAtom(JSONParser.AtomContext ctx) {
        xml.put(ctx, ctx.getText());
    }

    @Override
    public void exitArrayOfValues(JSONParser.ArrayOfValuesContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (JSONParser.ValueContext vc : ctx.value()) {
            sb.append("<element>");
            sb.append(getXml(vc));
            sb.append("</element>");
            sb.append("\n");
        }
        setXml(ctx, sb.toString());
    }

    @Override
    public void exitString(JSONParser.StringContext ctx) {
        setXml(ctx, stripQuotes(ctx.getText()));
    }

    @Override
    public void exitObjectValue(JSONParser.ObjectValueContext ctx) {
        setXml(ctx, getXml(ctx.object()));
    }


    @Override
    public void exitArrayValue(JSONParser.ArrayValueContext ctx) {
        setXml(ctx, getXml(ctx.array()));
    }


    @Override
    public void exitPair(JSONParser.PairContext ctx) {
        String tag = stripQuotes(ctx.STRING().getText());
        String value = String.format("<%s>%s</%s>\n", tag, getXml(ctx.value()), tag);
        setXml(ctx, value);
    }

    @Override
    public void exitAnObject(JSONParser.AnObjectContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (JSONParser.PairContext pc : ctx.pair()) {
            sb.append(getXml(pc));
        }
        setXml(ctx, sb.toString());
    }

    @Override
    public void exitEmptyObject(JSONParser.EmptyObjectContext ctx) {
        setXml(ctx, "");
    }

    @Override
    public void exitEmptyArray(JSONParser.EmptyArrayContext ctx) {
        setXml(ctx, "");
    }

    @Override
    public void exitJson(JSONParser.JsonContext ctx) {
        setXml(ctx, getXml(ctx.getChild(0)));
    }

    /**
     * 去掉字符串首尾的双引号""
     *
     * @param s
     * @return
     */
    public String stripQuotes(String s) {
        if (s == null || s.charAt(0) != '"') return s;
        return s.substring(1, s.length() - 1);
    }
}
