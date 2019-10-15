package com.xebia.xtable.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Xtable {

    private String[] headers;
    private List<String[]> rows;
    private static final String HORIZONTAL_SEP = "-";
    private String verticalSep="|";
    private String joinSep="+";

    public Xtable() {
        rows = new ArrayList<>();
    }


    public  Xtable createBlankTable(int row,int column)
    {
        String blankSpace="   ";

        for (int i = 0; i < row; i++) {
            String[] blankSpaceArr=new String[column];
            for (int j = 0; j < column; j++) {
               blankSpaceArr[j]=blankSpace;
            }
            withRow(blankSpaceArr);
        }
     return this;
    }

    public  Xtable withHeader(String... headers) {
        this.headers = headers;
        return  this;
    }

    public Xtable withRow(String... cells) {
        rows.add(cells);
        return this;
    }


    public void print() {
        int[] maxWidths = headers != null ?
                Arrays.stream(headers).mapToInt(String::length).toArray() : null;


        for (String[] cells : rows) {
            if (maxWidths == null) {
                maxWidths = new int[cells.length];
            }
            if (cells.length != maxWidths.length) {
                throw new IllegalArgumentException("Number of row-cells and headers should be consistent");
            }

            for (int i = 0; i < cells.length; i++) {
                maxWidths[i] = Math.max(maxWidths[i], cells[i].length());
            }
        }

        if (headers != null) {
            printLine(maxWidths);
            printRow(headers, maxWidths);
            printLine(maxWidths);
        }


        for (int i = 0; i < rows.size(); i++) {
            printLine(maxWidths);
            printRow(rows.get(i), maxWidths);
            if(i==rows.size()-1)
            {
                printLine(maxWidths);
            }
        }

    /*    for (String[] cells : rows) {
            printRow(cells, maxWidths);
        }*/
     /*   if (headers != null) {
            printLine(maxWidths);
        }*/
    }

    private void printLine(int[] columnWidths) {
        for (int i = 0; i < columnWidths.length; i++) {
            String line = String.join("", Collections.nCopies(columnWidths[i] +
                    verticalSep.length() + 1, HORIZONTAL_SEP));
            System.out.print(joinSep + line + (i == columnWidths.length - 1 ? joinSep : ""));
        }
        System.out.println();
    }

    private void printRow(String[] cells, int[] maxWidths) {
        for (int i = 0; i < cells.length; i++) {
            String s = cells[i];
            String verStrTemp = i == cells.length - 1 ? verticalSep : "";
                System.out.printf("%s %-" + maxWidths[i] + "s %s", verticalSep, s, verStrTemp);
        }
        System.out.println();
    }



}


