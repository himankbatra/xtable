package com.xebia.xtable;

import com.xebia.xtable.layout.LayoutManager;

import java.util.Scanner;

public class XtableApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of Rows: ");
        int noOfRows = scanner.nextInt();
        System.out.println("Enter number of Columns: ");
        int noOfColumns = scanner.nextInt();
        System.out.println("Enter column width for each Column: ");
        int[] columnWidth = new int[noOfColumns];
        for (int i = 0; i < noOfColumns; i++) {
            columnWidth[i] = scanner.nextInt();
        }
        scanner.nextLine();
        System.out.println("Enter Table Layout : ");

        String tableLayout = scanner.nextLine();
        LayoutManager layoutManager = LayoutManager.HorizontalLayout();
        if (tableLayout.equalsIgnoreCase("vertical")) {
            layoutManager = LayoutManager.VerticalLayout();
        }

        System.out.println("Enter Header Row : ");
        String[] headerRow = new String[noOfColumns];
        for (int i = 0; i < noOfColumns; i++) {
            headerRow[i] = scanner.nextLine();
        }
        Table table = new Table.Builder()
                .withNumberOfRows(noOfRows)
                .withNumberOfColumns(noOfColumns)
                .withColumnWidth(columnWidth)
                .withHeaderRow(headerRow)
                .withTableLayout(layoutManager)
                .build();

        System.out.println("Enter Rows Data : ");

        for (int i = 1; i < noOfRows; i++) {
            String[] row = new String[noOfColumns];
            for (int j = 0; j < noOfColumns; j++) {
                row[j] = scanner.nextLine();
            }
            table.insertRow(row);
        }

        table.create();

        table.render();

        String shape = table.shape();
        System.out.println("Shape of the Table is :" + shape);


    }

}
