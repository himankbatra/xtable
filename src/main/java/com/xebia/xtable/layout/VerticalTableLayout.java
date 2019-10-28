package com.xebia.xtable.layout;

import com.xebia.xtable.Elements;

import java.util.Arrays;
import java.util.List;

class VerticalTableLayout implements TableLayout {


    @Override
    public String create(List<String[]> rowsData, int[] columnWidth) {
        StringBuilder table = new StringBuilder();
        int numberOfRows = rowsData.size();
        int numberOfColumns = rowsData.get(0).length;
        Arrays.sort(columnWidth);
        int maximumColumnWidth = columnWidth[columnWidth.length - 1];
        columnWidth = new int[numberOfRows];
        Arrays.fill(columnWidth, maximumColumnWidth);

        for (int col = 0; col < numberOfColumns; col++) {
            table.append(Elements.createLine(numberOfRows, columnWidth));
            String[] verticalRowData = new String[numberOfRows];
            for (int row = 0; row < numberOfRows; row++) {
                verticalRowData[row] = rowsData.get(row)[col];
            }
            table.append(Elements.createRow(verticalRowData, columnWidth));
            if (col == numberOfColumns - 1) {
                table.append(Elements.createLine(numberOfRows, columnWidth));
            }
        }
        return table.toString();
    }


}
