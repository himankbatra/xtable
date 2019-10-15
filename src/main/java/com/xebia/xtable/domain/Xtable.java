package com.xebia.xtable.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Xtable {

    private List<String[]> rows;
    private static final String HORIZONTAL_SEP = "-";
    private static final String VERTICAL_SEP = "|";
    private static final String JOIN_SEP = "+";
    private int columnWidth;

    public Xtable(int columnWidth) {
        this();
        this.columnWidth = columnWidth;

    }

    public Xtable() {
        rows = new ArrayList<>();
        this.columnWidth = 10;
    }


    public Xtable createEmpty(int row, int column) {

        String blankSpace = String.join("", Collections.nCopies(this.columnWidth, " "));

        for (int i = 0; i < row; i++) {
            String[] blankSpaceArr = new String[column];
            for (int j = 0; j < column; j++) {
                blankSpaceArr[j] = blankSpace;
            }
            withRow(blankSpaceArr);
        }
        return this;
    }


    public Xtable withRow(String... cells) {
        rows.add(cells);
        return this;
    }


    public void print() {

        int[] maxWidths = new int[rows.get(0).length];
        for (int i = 0; i < rows.get(0).length; i++) {
            maxWidths[i] = columnWidth;
        }


        for (int i = 0; i < rows.size(); i++) {
            printLine(maxWidths);
            printRow(rows.get(i), maxWidths);
            if (i == rows.size() - 1) {
                printLine(maxWidths);
            }
        }

    }

    private void printLine(int[] columnWidths) {
        for (int i = 0; i < columnWidths.length; i++) {
            String line = String.join("", Collections.nCopies(columnWidths[i] +
                    VERTICAL_SEP.length() + 1, HORIZONTAL_SEP));
            System.out.print(JOIN_SEP + line + (i == columnWidths.length - 1 ? JOIN_SEP : ""));
        }
        System.out.println();
    }

    private void printRow(String[] cells, int[] maxWidths) {
        for (int i = 0; i < cells.length; i++) {
            String s = cells[i];
            String verStrTemp = i == cells.length - 1 ? VERTICAL_SEP : "";
            System.out.printf("%s %-" + maxWidths[i] + "s %s", VERTICAL_SEP, s, verStrTemp);
        }
        System.out.println();
    }


}


