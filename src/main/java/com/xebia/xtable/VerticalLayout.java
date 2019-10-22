package com.xebia.xtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VerticalLayout implements LayoutManager {

    private TableElementCreator tableElementCreator;

    public VerticalLayout(TableElementCreator tableElementCreator) {
        this.tableElementCreator = tableElementCreator;
    }



    @Override
    public String create(List<String[]> rows, int[] columnWidth) {
        StringBuilder table = new StringBuilder();
        int numberOfRows=rows.size();
        int numberOfColumns=rows.get(0).length;
        Arrays.sort(columnWidth);
        int maximumColumnWidth = columnWidth[columnWidth.length - 1];
        columnWidth = new int[numberOfRows];
        Arrays.fill(columnWidth,maximumColumnWidth);
        rows=transformToVertical(rows);
        for (int col = 0; col < numberOfColumns; col++) {
            table.append(this.tableElementCreator.createLine(numberOfRows, columnWidth));
            table.append(this.tableElementCreator.createRow(rows.get(col), columnWidth));
            if (col == numberOfColumns - 1) {
                table.append(this.tableElementCreator.createLine(numberOfRows, columnWidth));
            }
        }
        return table.toString();
    }

    private List<String[]> transformToVertical(List<String[]> rowsToTransform) {
        int numberOfRows=rowsToTransform.size();
        int numberOfColumns=rowsToTransform.get(0).length;
        List<String[]> transformedRows = new ArrayList<>();
        for (int col = 0; col < numberOfColumns; col++) {
            String[] verticalRow = new String[numberOfRows];
            for (int row = 0; row < numberOfRows; row++) {
                verticalRow[row] = rowsToTransform.get(row)[col];
            }
            transformedRows.add(verticalRow);
        }
        return transformedRows;
    }

    @Override
    public String shape(int numberOfRows,int numberOfColumns) {
        StringBuilder shape = new StringBuilder();
        shape.append(numberOfColumns).append(TableConstants.CROSS_SIGN).append(numberOfRows);
        return shape.toString();
    }

}
