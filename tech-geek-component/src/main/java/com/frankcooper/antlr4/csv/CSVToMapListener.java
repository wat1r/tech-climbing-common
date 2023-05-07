package com.frankcooper.antlr4.csv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVToMapListener extends CSVBaseListener {
    private static final String EMPTY = "";
    private List<Map<String, String>> rows = new ArrayList<Map<String, String>>(16);
    List<String> header = new ArrayList<String>(16);
    List<String> currentRow = new ArrayList<String>(16);


    public List<Map<String, String>> getRows() {
        return rows;
    }

    @Override
    public void exitHeader(CSVParser.HeaderContext ctx) {
        header.addAll(currentRow);
    }

    @Override
    public void enterRow(CSVParser.RowContext ctx) {
        currentRow.clear();
    }


    @Override
    public void exitRow(CSVParser.RowContext ctx) {
        if (ctx.getParent() instanceof CSVParser.HeaderContext) {
            return;
        }
        Map<String, String> line = new HashMap<String, String>(16);
        for (int i = 0; i < header.size(); i++) {
            line.put(header.get(i), currentRow.get(i));
        }
        rows.add(line);
    }

    @Override
    public void exitFile(CSVParser.FileContext ctx) {
        super.exitFile(ctx);
    }

    @Override
    public void exitText(CSVParser.TextContext ctx) {
        currentRow.add(ctx.getText());
    }

    @Override
    public void exitString(CSVParser.StringContext ctx) {
        currentRow.add(ctx.getText());
    }

    @Override
    public void exitEmpty(CSVParser.EmptyContext ctx) {
        currentRow.add(EMPTY);
    }
}
