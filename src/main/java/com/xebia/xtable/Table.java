package com.xebia.xtable;


import com.xebia.xtable.renderer.TableRenderer;

import java.util.*;

public class Table {

    private List<String[]> rows;
    private int[] columnWidth;
    private int numberOfRows;
    private int numberOfColumns;
    private TableRenderer tableRenderer;
    private TableElementCreator tableElementCreator;
    private String result;


    private Table(Builder builder) {
        rows = builder.rows;
        columnWidth = builder.columnWidth;
        numberOfRows = builder.numberOfRows;
        numberOfColumns = builder.numberOfColumns;
        tableRenderer = builder.tableRenderer;
        tableElementCreator = builder.tableElementCreator;
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
        if (differenceInRows < 0) {
            throw new InputMismatchException("Data rows exceeded the number of rows");
        }
        if (differenceInRows != 0) {
            createEmpty(differenceInRows);
        }

        for (int i = 0; i < numberOfRows; i++) {
            table.append(this.tableElementCreator.createLine(numberOfColumns, this.columnWidth));
            table.append(this.tableElementCreator.createRow(this.rows.get(i), columnWidth));
            if (i == numberOfRows - 1) {
                table.append(this.tableElementCreator.createLine(numberOfColumns, this.columnWidth));
            }
        }
        this.result = table.toString();
        return table.toString();
    }

    private void createEmpty(int differenceInRows) {
        for (int i = 0; i < differenceInRows; i++) {
            String[] row = new String[numberOfColumns];
            for (int j = 0; j < numberOfColumns; j++) {
                row[j] = " ";
            }
            this.rows.add(row);
        }
    }


    public static final class Builder {

        private int numberOfRows;
        private int numberOfColumns;
        private List<String[]> rows;
        private int[] columnWidth;
        private TableRenderer tableRenderer;
        private TableElementCreator tableElementCreator;


        public Builder() {
            this.rows = new ArrayList<>();
            this.tableRenderer = TableRenderer.consoleBasedRender();
            this.tableElementCreator = new TableElementCreator();
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
            if (Objects.isNull(val) || (this.numberOfColumns != val.length)) {
                throw new IllegalArgumentException("rows data is invalid");
            }
            this.rows.add(val);
            return this;
        }

        public Builder withColumnWidth(int... val) {
            if (Objects.isNull(val) || (val.length > 1 && val.length != this.numberOfColumns)) {
                throw new IllegalArgumentException("number of columns width should be equal to number of columns");
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

        public Builder withTableElementCreator(TableElementCreator val) {
            this.tableElementCreator = val;
            return this;
        }

        public Table build() {
            return new Table(this);
        }
    }
}


