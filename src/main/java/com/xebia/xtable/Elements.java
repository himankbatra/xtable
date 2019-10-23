package com.xebia.xtable;

import java.util.Collections;

public class Elements  {

    public static String createLine(int numberOfColumns, int[] columnWidth) {
        StringBuilder lineString = new StringBuilder();
        for (int i = 0; i < numberOfColumns; i++) {
            String line = String.join("", Collections.nCopies(columnWidth[i] +
                    TableConstants.VERTICAL_SEP.length() + 1, TableConstants.HORIZONTAL_SEP));
            lineString.append(TableConstants.JOIN_SEP).append(line);
            if (i == numberOfColumns - 1) {
                lineString.append(TableConstants.JOIN_SEP);
            }

        }
        lineString.append("\n");
        return lineString.toString();
    }


    public static String createRow(String[] row, int[] columnWidth) {
        StringBuilder rowString = new StringBuilder();

        for (int i = 0; i < row.length; i++) {
            String cell = row[i];
            if (columnWidth[i] < TableConstants.MIN_COLUMN_WIDTH) {
                throw new IllegalArgumentException("column width should be greater or equal to " + TableConstants.MIN_COLUMN_WIDTH);
            }
            if (cell.length() > columnWidth[i]) {
                cell = new StringBuilder(cell.substring(0, columnWidth[i] - 3)).append(TableConstants.TRUNCATE_STRING).toString();
            }
            String verStrTemp = i == row.length - 1 ? TableConstants.VERTICAL_SEP : "";
            rowString.append(TableConstants.VERTICAL_SEP).append(String.format("%1$-" + (columnWidth[i] + TableConstants.VERTICAL_SEP.length() + 1) + "s",
                    " " + cell)).append(verStrTemp);
        }
        rowString.append("\n");

        return rowString.toString();
    }

}
