package com.xebia.xtable;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TableTests {

    @Test
    public void should_create_blank_table_with_given_no_of_row_and_no_of_column() {
        // Arrange
        Table table = new Table.Builder()
                .withColumnWidth(20)
                .withNumberOfRows(4)
                .withNumberOfColumns(4).build();
        // Act
        String result = table.createEmpty();


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
    public void should_get_the_shape_of_the_table_when_i_provide_valid_table_data() {

        Table table = new Table.Builder()
                .withColumnWidth(20)
                .withNumberOfRows(4)
                .withNumberOfColumns(4).build();

        table.createEmpty();
        String shape = table.shape();

        assertThat(shape).isEqualTo("4*4");

    }


}