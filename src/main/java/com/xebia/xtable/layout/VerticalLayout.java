package com.xebia.xtable.layout;

import com.xebia.xtable.Elements;
import com.xebia.xtable.TableConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

 class VerticalLayout implements LayoutManager {


    @Override
    public String create(List<String[]> rowsData, int[] columnWidth) {
        StringBuilder table = new StringBuilder();
        int numberOfRows=rowsData.size();
        int numberOfColumns=rowsData.get(0).length;
        Arrays.sort(columnWidth);
        int maximumColumnWidth = columnWidth[columnWidth.length - 1];
        columnWidth = new int[numberOfRows];
        Arrays.fill(columnWidth,maximumColumnWidth);
        rowsData=transformToVertical(rowsData);
        for (int col = 0; col < numberOfColumns; col++) {
            table.append(Elements.createLine(numberOfRows, columnWidth));
            table.append(Elements.createRow(rowsData.get(col), columnWidth));
            if (col == numberOfColumns - 1) {
                table.append(Elements.createLine(numberOfRows, columnWidth));
            }
        }
        return table.toString();
    }

    private List<String[]> transformToVertical(List<String[]> rowsToTransform) {
        int numberOfRows=rowsToTransform.size();
        int numberOfColumns=rowsToTransform.get(0).length;
        List<String[]> verticalRowsData = new ArrayList<>();
        for (int col = 0; col < numberOfColumns; col++) {
            String[] verticalRow = new String[numberOfRows];
            for (int row = 0; row < numberOfRows; row++) {
                verticalRow[row] = rowsToTransform.get(row)[col];
            }
            verticalRowsData.add(verticalRow);
        }
        return verticalRowsData;
    }

    @Override
    public String shape(int numberOfRows,int numberOfColumns) {
        StringBuilder shape = new StringBuilder();
        shape.append(numberOfColumns).append(TableConstants.CROSS_SIGN).append(numberOfRows);
        return shape.toString();
    }

}
