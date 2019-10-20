package com.xebia.xtable;

import java.util.Collections;

public class TableCreator {

    public String createLine(int numberOfColumns, int[] columnWidths) {
        StringBuilder lineString = new StringBuilder();
        for (int i = 0; i < numberOfColumns; i++) {
            String line = String.join("", Collections.nCopies(columnWidths[i] +
                    TableConstants.VERTICAL_SEP.length() + 1, TableConstants.HORIZONTAL_SEP));
            lineString.append(TableConstants.JOIN_SEP).append(line);
            if (i == numberOfColumns - 1) {
                lineString.append(TableConstants.JOIN_SEP);
            }

        }
        lineString.append("\n");
        return lineString.toString();
    }


    public String createRow(String[] row, int[] columnWidths) {
        StringBuilder rowString = new StringBuilder();

        for (int i = 0; i < row.length; i++) {
            String cell = row[i];
            String verStrTemp = i == row.length - 1 ? TableConstants.VERTICAL_SEP : "";
            rowString.append(TableConstants.VERTICAL_SEP).append(String.format("%1$-" + (columnWidths[i] + TableConstants.VERTICAL_SEP.length() + 1) + "s",
                    " " + cell)).append(verStrTemp);
        }
        rowString.append("\n");

        return rowString.toString();
    }

}
