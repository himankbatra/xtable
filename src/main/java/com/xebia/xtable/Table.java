package com.xebia.xtable;


import com.xebia.xtable.renderer.TableRenderer;

import java.util.*;

public class Table {

    private List<String[]> rows;
    private int[] columnsWidth;
    private int numberOfRows;
    private int numberOfColumns;
    private TableRenderer tableRenderer;
    private TableCreator tableCreator;
    private String result;


    private Table(Builder builder) {
        rows = builder.rows;
        columnsWidth = builder.columnsWidth;
        numberOfRows = builder.numberOfRows;
        numberOfColumns = builder.numberOfColumns;
        tableRenderer = builder.tableRenderer;
        tableCreator = builder.tableCreator;
    }


    public Table insertRow(String... newRow) {
        if (Objects.isNull(newRow) || this.numberOfColumns != newRow.length) {
            throw new IllegalArgumentException("row data is invalid");
        }
        this.rows.add(newRow);
        return this;
    }


    public String shape() {
        StringBuilder shape = new StringBuilder();
        shape.append(this.numberOfRows).append("x").append(this.numberOfColumns);
        return shape.toString();
    }


    public void render() {

        this.tableRenderer.render(result);

    }


    public String create() {

        StringBuilder table = new StringBuilder();

        int differenceInRows = this.numberOfRows - this.rows.size();
        createEmpty(differenceInRows);


        for (int i = 0; i < numberOfRows; i++) {
            table.append(this.tableCreator.createLine(numberOfColumns, this.columnsWidth));
            table.append(this.tableCreator.createRow(this.rows.get(i), columnsWidth));
            if (i == numberOfRows - 1) {
                table.append(this.tableCreator.createLine(numberOfColumns, this.columnsWidth));
            }
        }
        this.result = table.toString();
        return table.toString();
    }

    private void createEmpty(int differenceInRows) {
        if (differenceInRows < 0) {
            throw new InputMismatchException("Data rows exceeded the number of rows");
        }
        if (differenceInRows != 0) {
            for (int i = 0; i < differenceInRows; i++) {
                String[] row = new String[numberOfColumns];
                for (int j = 0; j < numberOfColumns; j++) {
                    row[j] = " ";
                }
                this.rows.add(row);
            }
        }
    }


    public static final class Builder {

        private int numberOfRows;
        private int numberOfColumns;
        private List<String[]> rows;
        private int[] columnsWidth;
        private TableRenderer tableRenderer;
        private TableCreator tableCreator;


        public Builder() {
            this.rows = new ArrayList<>();
            this.tableRenderer = TableRenderer.consoleBasedRender();
            this.tableCreator = new TableCreator();
        }


        public Builder withNumberOfRows(int val) {
            this.numberOfRows = val;
            return this;
        }

        public Builder withNumberOfColumns(int val) {
            this.numberOfColumns = val;
            this.columnsWidth = new int[numberOfColumns];
            Arrays.fill(this.columnsWidth,TableConstants.DEFAULT_COLUMN_WIDTH);
            return this;
        }

        public Builder withHeaderRow(String... val) {
            if (Objects.isNull(val) || (this.numberOfColumns != val.length)) {
                throw new IllegalArgumentException("rows data is invalid");
            }
            this.rows.add(val);
            return this;
        }

        public Builder withColumnsWidth(int... val) {
            if (Objects.isNull(val) || val.length != this.numberOfColumns) {
                throw new IllegalArgumentException("number of columns width should be equal to number of columns");
            }
            this.columnsWidth = val;
            return this;
        }

        public Builder withTableRender(TableRenderer val) {
            this.tableRenderer = val;
            return this;
        }

        public Builder withTableCreator(TableCreator val) {
            this.tableCreator = val;
            return this;
        }

        public Table build() {
            return new Table(this);
        }
    }
}


