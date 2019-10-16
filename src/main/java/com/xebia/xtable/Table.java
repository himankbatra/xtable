package com.xebia.xtable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Table {

    private List<String[]> rows;
    private int columnWidth;
    private int numberOfRows;
    private int numberOfColumns;
    private static final String HORIZONTAL_SEP = "-";
    private static final String VERTICAL_SEP = "|";
    private static final String JOIN_SEP = "+";
    private ConsoleRenderer tableRenderer;
    private String result;


    private Table(Builder builder) {
        rows = new ArrayList<>();
        columnWidth = builder.columnWidth;
        numberOfRows = builder.numberOfRows;
        numberOfColumns = builder.numberOfColumns;
        tableRenderer = builder.tableRenderer;
    }


    public String createEmpty() {
        String blankSpace = String.join("", Collections.nCopies(this.columnWidth, " "));
        for (int i = 0; i < this.numberOfRows; i++) {
            String[] newRow = new String[this.numberOfColumns];
            for (int j = 0; j < this.numberOfColumns; j++) {
                newRow[j] = blankSpace;
            }
            insertRows(newRow);
        }
        create();
        return result;
    }


    public Table insertRows(String... newRow) {
        if (Objects.isNull(newRow)) {
            throw new IllegalArgumentException("rows should not be null");
        }
        this.numberOfColumns = this.numberOfColumns == 0 ? newRow.length : this.numberOfColumns;
        this.rows.add(newRow);
        return this;
    }


    public void render() {

        this.tableRenderer.render(result);

    }

    public String create() {
        this.numberOfRows = numberOfRows == 0 ? rows.size() : numberOfRows;
        StringBuilder table = new StringBuilder();

        for (int i = 0; i < numberOfRows; i++) {
            table.append(createLine(numberOfColumns, columnWidth));
            table.append(createRow(this.rows.get(i), columnWidth));
            if (i == numberOfRows - 1) {
                table.append(createLine(numberOfColumns, columnWidth));
            }
        }
        this.result = table.toString();
        return table.toString();
    }


    private String createLine(int numberOfColumns, int columnWidth) {
        StringBuilder lineString = new StringBuilder();
        for (int i = 0; i < numberOfColumns; i++) {
            String line = String.join("", Collections.nCopies(columnWidth +
                    VERTICAL_SEP.length() + 1, HORIZONTAL_SEP));
            lineString.append(JOIN_SEP).append(line);
            if (i == numberOfColumns - 1) {
                lineString.append(JOIN_SEP);
            }

        }
        lineString.append("\n");
        return lineString.toString();
    }

    private String createRow(String[] cells, int maxWidths) {
        StringBuilder rowString = new StringBuilder();
        if (cells.length != numberOfColumns) {
            throw new IllegalArgumentException("number of columns are not correct");
        }
        maxWidths = maxWidths + VERTICAL_SEP.length() + 1;
        for (int i = 0; i < cells.length; i++) {
            String cell = cells[i];
            String verStrTemp = i == cells.length - 1 ? VERTICAL_SEP : "";
            rowString.append(VERTICAL_SEP).append(String.format("%1$-" + maxWidths + "s",
                    " " + cell)).append(verStrTemp);
        }
        rowString.append("\n");

        return rowString.toString();
    }


    public static final class Builder {

        private int columnWidth;
        private int numberOfRows;
        private int numberOfColumns;
        private ConsoleRenderer tableRenderer;

        public Builder() {
            this.columnWidth = 10;
            this.tableRenderer = new ConsoleRenderer();
        }


        public Builder withColumnWidth(int val) {
            columnWidth = val;
            return this;
        }

        public Builder withNumberOfRows(int val) {
            numberOfRows = val;
            return this;
        }

        public Builder withNumberOfColumns(int val) {
            numberOfColumns = val;
            return this;
        }

        public Builder withTableRender(ConsoleRenderer val) {
            tableRenderer = val;
            return this;
        }

        public Table build() {
            return new Table(this);
        }
    }
}


