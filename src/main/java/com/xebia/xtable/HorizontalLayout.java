package com.xebia.xtable;

import java.util.List;

public class HorizontalLayout implements LayoutManager {

    private TableElementCreator tableElementCreator;

    public HorizontalLayout(TableElementCreator tableElementCreator) {
        this.tableElementCreator = tableElementCreator;
    }

    @Override
    public String create(List<String[]> rows,int[] columnWidth) {
        StringBuilder table = new StringBuilder();
        int numberOfRows=rows.size();
        int numberOfColumns=rows.get(0).length;
        for (int row = 0; row < numberOfRows; row++) {
            table.append(this.tableElementCreator.createLine(numberOfColumns, columnWidth));
            table.append(this.tableElementCreator.createRow(rows.get(row), columnWidth));
            if (row == numberOfRows - 1) {
                table.append(this.tableElementCreator.createLine(numberOfColumns, columnWidth));
            }
        }
        return table.toString();
    }

    @Override
    public String shape(int numberOfRows,int numberOfColumns) {
        StringBuilder shape = new StringBuilder();
        shape.append(numberOfRows).append(TableConstants.CROSS_SIGN).append(numberOfColumns);
        return shape.toString();
    }


}
