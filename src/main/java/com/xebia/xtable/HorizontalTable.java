package com.xebia.xtable;


import com.xebia.xtable.renderer.TableRenderer;

import java.util.*;

public class HorizontalTable implements Table {
    private Header[] header;
    private List<String[]> rows;
    private int defaultColumnWidth;
    private int numberOfRows;
    private int numberOfColumns;
    private TableRenderer tableRenderer;
    private String result;


    private HorizontalTable(Builder builder) {
        rows = builder.rows;
        defaultColumnWidth = builder.defaultColumnWidth;
        numberOfRows = builder.numberOfRows;
        numberOfColumns = builder.numberOfColumns;
        tableRenderer = builder.tableRenderer;
        header = builder.header;
    }


    @Override
    public HorizontalTable insertRows(String... newRow) {
        if (Objects.isNull(newRow) || (this.numberOfColumns != 0 && this.numberOfColumns != newRow.length)) {
            throw new IllegalArgumentException("rows data is invalid");
        }
        this.numberOfColumns = this.numberOfColumns == 0 ? newRow.length : this.numberOfColumns;
        this.rows.add(newRow);
        return this;
    }

    @Override
    public String shape() {
        StringBuilder shape = new StringBuilder();
        if (header == null)
            shape.append(this.numberOfRows).append("x").append(this.numberOfColumns);
        else
            shape.append(this.numberOfRows + 1).append("x").append(this.numberOfColumns);
        return shape.toString();
    }


    @Override
    public void render() {

        this.tableRenderer.render(result);

    }

    @Override
    public String create() {

        this.numberOfRows = numberOfRows == 0 ? rows.size() : numberOfRows;
        StringBuilder table = new StringBuilder();
        int[] columnsWidth = new int[numberOfColumns];
        for (int i = 0; i < columnsWidth.length; i++) {
            columnsWidth[i] = defaultColumnWidth;
        }
        if (this.header != null) {
            columnsWidth = createHeader(table);
        } else {
            table.append(createLine(numberOfColumns, columnsWidth));
        }
        int differenceInRows = this.numberOfRows - this.rows.size();
        createEmpty(differenceInRows);
        for (int i = 0; i < (numberOfRows); i++) {
            table.append(createRow(this.rows.get(i), columnsWidth));
            table.append(createLine(numberOfColumns, columnsWidth));
        }
        this.result = table.toString();
        return table.toString();
    }

    private void createEmpty(int differenceInRows) {
        if (differenceInRows < 0) {
            throw new InputMismatchException("Data rows exceeded the provided number of rows");
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

    private int[] createHeader(StringBuilder table) {
        int[] columnsWidth;
        String[] headerNames =
                Arrays.stream(this.header).map(h -> h.getName()).toArray(size -> new String[header.length]);
        columnsWidth =
                Arrays.stream(this.header).mapToInt(h -> {
                    if (h.getColumnWidth() == TableConstant.DEFAULT_TABLE_WIDTH &&
                            defaultColumnWidth != TableConstant.DEFAULT_TABLE_WIDTH) {
                        return defaultColumnWidth;
                    }
                    return h.getColumnWidth();
                }).toArray();


        table.append(createLine(numberOfColumns, columnsWidth));
        table.append(createRow(headerNames, columnsWidth));
        table.append(createLine(numberOfColumns, columnsWidth));
        return columnsWidth;
    }


    private String createLine(int numberOfColumns, int[] columnWidth) {
        StringBuilder lineString = new StringBuilder();
        for (int i = 0; i < numberOfColumns; i++) {
            String line = String.join("", Collections.nCopies(columnWidth[i] +
                    TableConstant.VERTICAL_SEP.length() + 1, TableConstant.HORIZONTAL_SEP));
            lineString.append(TableConstant.JOIN_SEP).append(line);
            if (i == numberOfColumns - 1) {
                lineString.append(TableConstant.JOIN_SEP);
            }

        }
        lineString.append("\n");
        return lineString.toString();
    }

    private String createRow(String[] cells, int[] maxWidths) {
        StringBuilder rowString = new StringBuilder();
        if (cells.length != numberOfColumns) {
            throw new IllegalArgumentException("number of columns are not correct");
        }

        for (int i = 0; i < cells.length; i++) {
            String cell = cells[i];
            String verStrTemp = i == cells.length - 1 ? TableConstant.VERTICAL_SEP : "";
            rowString.append(TableConstant.VERTICAL_SEP).append(String.format("%1$-" + (maxWidths[i] + TableConstant.VERTICAL_SEP.length() + 1) + "s",
                    " " + cell)).append(verStrTemp);
        }
        rowString.append("\n");

        return rowString.toString();
    }


    public static final class Builder {

        private Header[] header;
        private int defaultColumnWidth;
        private int numberOfRows;
        private int numberOfColumns;
        private TableRenderer tableRenderer;
        private List<String[]> rows;

        public Builder() {
            this.rows = new ArrayList<>();
            this.defaultColumnWidth = TableConstant.DEFAULT_TABLE_WIDTH;
            this.tableRenderer = TableRenderer.consoleBasedRender();
        }


        public Builder withDefaultColumnWidth(int val) {
            defaultColumnWidth = val;
            return this;
        }

        public Builder withNumberOfRows(int val) {
            numberOfRows = val;
            return this;
        }

        public Builder withHeader(Header... val) {
            if (Objects.isNull(val)) {
                throw new IllegalArgumentException("table header data is invalid");
            }
            this.numberOfColumns = val.length;
            header = val;
            return this;
        }


        public Builder withRows(String... val) {
            if (Objects.isNull(val) || (this.numberOfColumns != 0 && this.numberOfColumns != val.length)) {
                throw new IllegalArgumentException("rows data is invalid");
            }
            this.numberOfColumns = this.numberOfColumns == 0 ? val.length : this.numberOfColumns;
            rows.add(val);
            return this;
        }


        public Builder withNumberOfColumns(int val) {
            numberOfColumns = val;
            return this;
        }

        public Builder withTableRender(TableRenderer val) {
            tableRenderer = val;
            return this;
        }

        public HorizontalTable build() {
            return new HorizontalTable(this);
        }
    }
}


