package com.xebia.xtable;


import com.xebia.xtable.layout.TableLayout;
import com.xebia.xtable.renderer.TableRenderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;

public class Table {

    private List<String[]> rowsData;
    private int[] columnWidth;
    private int numberOfRows;
    private int numberOfColumns;
    private TableRenderer tableRenderer;
    private TableLayout tableLayout;
    private String result;


    private Table(Builder builder) {
        rowsData = builder.rows;
        columnWidth = builder.columnWidth;
        numberOfRows = builder.numberOfRows;
        numberOfColumns = builder.numberOfColumns;
        tableRenderer = builder.tableRenderer;
        tableLayout = builder.tableLayout;
    }


    public Table insertRow(String... rowCells) {
        if (Objects.isNull(rowCells) || rowCells.length == 0
                || this.numberOfColumns != rowCells.length) {
            throw new IllegalArgumentException("row data is invalid");
        }
        this.rowsData.add(rowCells);
        return this;
    }


    public String shape() {
        StringBuilder shape = new StringBuilder();
        shape.append(numberOfRows).append(TableConstants.CROSS_SIGN).append(numberOfColumns);
        return shape.toString();
    }


    public void render() {
        this.tableRenderer.render(result);
    }


    public String create() {


        int differenceInRows = this.numberOfRows - this.rowsData.size();
        if (differenceInRows < 0) {
            throw new InputMismatchException("Data rows exceeded the number of rows");
        }
        if (differenceInRows != 0) {
            createEmptyRows(differenceInRows);
        }

        this.result = this.tableLayout.create(this.rowsData, this.columnWidth);
        return this.result;
    }

    private void createEmptyRows(int rowsToAdd) {
        for (int i = 0; i < rowsToAdd; i++) {
            String[] row = new String[numberOfColumns];
            Arrays.fill(row, TableConstants.EMPTY_CELL);
            this.rowsData.add(row);
        }
    }


    public static final class Builder {

        private int numberOfRows;
        private int numberOfColumns;
        private List<String[]> rows;
        private int[] columnWidth;
        private TableRenderer tableRenderer;
        private TableLayout tableLayout;


        public Builder() {
            this.rows = new ArrayList<>();
            this.tableRenderer = TableRenderer.consoleBasedRender();
            this.tableLayout = TableLayout.horizontalLayout();
        }


        public Builder withNumberOfRows(int val) {
            this.numberOfRows = val;
            return this;
        }

        public Builder withNumberOfColumns(int val) {
            this.numberOfColumns = val;
            this.columnWidth = new int[numberOfColumns];
            Arrays.fill(this.columnWidth, TableConstants.DEFAULT_COLUMN_WIDTH);
            return this;
        }

        public Builder withHeaderRow(String... val) {
            if (Objects.isNull(val) || val.length == 0  || (this.numberOfColumns != val.length)) {
                throw new IllegalArgumentException("row data is invalid");
            }
            this.rows.add(val);
            return this;
        }

        public Builder withColumnWidth(int... val) {
            if (Objects.isNull(val) || val.length == 0
                    || (val.length > 1 && val.length != this.numberOfColumns)) {
                throw new IllegalArgumentException("number of columns width "
                        + "should be equal to number of columns");
            }
            if (val.length == 1) {
                Arrays.fill(columnWidth, val[0]);
            } else {
                this.columnWidth = val;
            }
            return this;
        }

        public Builder withTableRender(TableRenderer val) {
            this.tableRenderer = val;
            return this;
        }


        public Builder withTableLayout(TableLayout val) {
            this.tableLayout = val;
            return this;
        }

        public Table build() {
            return new Table(this);
        }
    }
}
