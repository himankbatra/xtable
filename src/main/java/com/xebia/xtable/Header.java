package com.xebia.xtable;

public class Header {

    private String name;
    private int ColumnWidth;

    public Header(String name, int columnWidth) {
        this.name = name;
        ColumnWidth = columnWidth;
    }

    public Header(String name) {
        this(name, TableConstant.DEFAULT_TABLE_WIDTH);
    }


    public int getColumnWidth() {
        return ColumnWidth;
    }

    public String getName() {
        return name;
    }
}
