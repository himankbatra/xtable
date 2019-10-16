package com.xebia.xtable;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class TableTests {

    @Test
    public void should_able_to_create_blank_table_with_given_no_of_row_and_no_of_column() throws IOException {
        // Arrange
        Table table = new Table.Builder()
                .withColumnWidth(20)
                .withNumberOfRows(4)
                .withNumberOfColumns(4).build();
        // Act
        String result = table.createEmpty();
        table.render();

        String expected = "+----------------------+----------------------+----------------------+----------------------+\n" +
                "|                      |                      |                      |                      |\n" +
                "+----------------------+----------------------+----------------------+----------------------+\n" +
                "|                      |                      |                      |                      |\n" +
                "+----------------------+----------------------+----------------------+----------------------+\n" +
                "|                      |                      |                      |                      |\n" +
                "+----------------------+----------------------+----------------------+----------------------+\n" +
                "|                      |                      |                      |                      |\n" +
                "+----------------------+----------------------+----------------------+----------------------+\n";

        //Assert
        assertThat(result).isEqualTo(expected);

    }

    @Test
    public void should_create_and_insert_rows_in_the_table_when_i_provide_valid_data() {

        // Arrange
        Table table = new Table.Builder()
                .withColumnWidth(20)
                .build();
        table.insertRows("1", "himank");
        table.insertRows("2", "akash");
        // Act
        String result = table.create();
        table.render();
        String expected = "+----------------------+----------------------+\n" +
                "| 1                    | himank               |\n" +
                "+----------------------+----------------------+\n" +
                "| 2                    | akash                |\n" +
                "+----------------------+----------------------+\n";
        assertThat(result).isEqualTo(expected);
    }
}