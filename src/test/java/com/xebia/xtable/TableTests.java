package com.xebia.xtable;

import org.junit.Test;

import java.util.InputMismatchException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class TableTests {

    @Test
    public void should_create_blank_table_with_given_no_of_row_and_no_of_column() {
        // Arrange
        Table table = new Table.Builder()
                .withNumberOfRows(4)
                .withNumberOfColumns(4)
                .build();
        // Act
        String result = table.create();


        String expected = "+------------+------------+------------+------------+\n" +
                          "|            |            |            |            |\n" +
                          "+------------+------------+------------+------------+\n" +
                          "|            |            |            |            |\n" +
                          "+------------+------------+------------+------------+\n" +
                          "|            |            |            |            |\n" +
                          "+------------+------------+------------+------------+\n" +
                          "|            |            |            |            |\n" +
                          "+------------+------------+------------+------------+\n";

        //Assert
        assertThat(result).isEqualTo(expected);

    }


    @Test
    public void should_get_the_shape_of_the_table_when_i_provide_valid_table_data() {

        Table table = new Table.Builder()
                .withNumberOfRows(4)
                .withNumberOfColumns(4)
                .withColumnWidth(20)
                .build();
        table.create();
        String shape = table.shape();

        assertThat(shape).isEqualTo("4x4");

    }

    @Test
    public void should_create_table_with_header_when_i_provide_valid_data() {

        Table table = new Table.Builder()
                .withNumberOfRows(4)
                .withNumberOfColumns(2)
                .withColumnWidth(10, 20)
                .withHeaderRow("sno", "name")
                .build();
        String tableWithHeader = table.create();
        String expected = "+------------+----------------------+\n" +
                          "| sno        | name                 |\n" +
                          "+------------+----------------------+\n" +
                          "|            |                      |\n" +
                          "+------------+----------------------+\n" +
                          "|            |                      |\n" +
                          "+------------+----------------------+\n" +
                          "|            |                      |\n" +
                          "+------------+----------------------+\n";
        assertThat(tableWithHeader).isEqualTo(expected);
    }


    @Test
    public void should_create_table_with_header_and_data_rows_when_i_provide_valid_data() {

        Table table = new Table.Builder()
                .withNumberOfRows(4)
                .withNumberOfColumns(2)
                .withHeaderRow("sno", "name")
                .withColumnWidth(10, 20)
                .build();
        String result = table.insertRow("1", "himank")
                .insertRow("2", "akash")
                .insertRow("3", "varun")
                .create();
        String expected = "+------------+----------------------+\n" +
                          "| sno        | name                 |\n" +
                          "+------------+----------------------+\n" +
                          "| 1          | himank               |\n" +
                          "+------------+----------------------+\n" +
                          "| 2          | akash                |\n" +
                          "+------------+----------------------+\n" +
                          "| 3          | varun                |\n" +
                          "+------------+----------------------+\n";
        assertThat(result).isEqualTo(expected);
    }


    @Test
    public void should_throw_exception_when_data_rows_are_exceeding_provided_no_of_rows() {

        Table table = new Table.Builder()
                .withNumberOfRows(4)
                .withNumberOfColumns(2)
                .withHeaderRow("sno", "name")
                .withColumnWidth(10, 20)
                .build();

        try {
            table.insertRow("1", "himank")
                    .insertRow("2", "akash")
                    .insertRow("3", "varun")
                    .insertRow("4", "vasu")
                    .create();

            fail("Should throw exception");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(InputMismatchException.class);
            assertThat(e.getMessage()).isEqualTo("Data rows exceeded the number of rows");
        }

    }

    @Test
    public void should_create_table_and_get_its_shape_when_valid_data_rows_and_header_are_provided() {

        Table table = new Table.Builder()
                .withNumberOfRows(4)
                .withNumberOfColumns(2)
                .withColumnWidth(5, 15)
                .withHeaderRow("sno", "name")
                .build();
        String result = table.insertRow("1", "himank")
                .insertRow("2", "akash")
                .insertRow("3", "varun")
                .create();

        String shape = table.shape();
        String expected = "+-------+-----------------+\n" +
                          "| sno   | name            |\n" +
                          "+-------+-----------------+\n" +
                          "| 1     | himank          |\n" +
                          "+-------+-----------------+\n" +
                          "| 2     | akash           |\n" +
                          "+-------+-----------------+\n" +
                          "| 3     | varun           |\n" +
                          "+-------+-----------------+\n";
        assertThat(result).isEqualTo(expected);
        assertThat(shape).isEqualTo("4x2");

    }

    @Test
    public void should_truncate_the_column_value_if_exceeding_the_given_column_width_when_valid_data_rows_and_header_are_provided() {

        Table table = new Table.Builder()
                .withNumberOfRows(4)
                .withNumberOfColumns(2)
                .withColumnWidth(5)
                .withHeaderRow("sno","name")
                .build();
        String result = table
                .insertRow("1", "himank")
                .insertRow("2", "akash")
                .insertRow("3", "varun")
                 .create();

        String expected = "+-------+-------+\n" +
                          "| sno   | name  |\n" +
                          "+-------+-------+\n" +
                          "| 1     | hi... |\n" +
                          "+-------+-------+\n" +
                          "| 2     | akash |\n" +
                          "+-------+-------+\n" +
                          "| 3     | varun |\n" +
                          "+-------+-------+\n";
        assertThat(result).isEqualTo(expected);

    }



}