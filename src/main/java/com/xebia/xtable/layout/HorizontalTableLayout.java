package com.xebia.xtable.layout;

import com.xebia.xtable.Elements;

import java.util.List;

class HorizontalTableLayout implements TableLayout {


    @Override
    public String create(List<String[]> rowsData, int[] columnWidth) {
        StringBuilder table = new StringBuilder();
        int numberOfRows = rowsData.size();
        int numberOfColumns = rowsData.get(0).length;
        for (int row = 0; row < numberOfRows; row++) {
            table.append(Elements.createLine(numberOfColumns, columnWidth));
            table.append(Elements.createRow(rowsData.get(row), columnWidth));
            if (row == numberOfRows - 1) {
                table.append(Elements.createLine(numberOfColumns, columnWidth));
            }
        }
        return table.toString();
    }


}
