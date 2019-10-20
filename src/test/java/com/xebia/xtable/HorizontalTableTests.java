package com.xebia.xtable;

import org.junit.Test;

import java.util.InputMismatchException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class HorizontalTableTests {

    @Test
    public void should_create_blank_table_with_given_no_of_row_and_no_of_column() {
        // Arrange
        Table horizontalTable = new HorizontalTable.Builder()
                .withDefaultColumnWidth(20)
                .withNumberOfRows(4)
                .withNumberOfColumns(4).build();
        // Act
        String result = horizontalTable.create();


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

        Table horizontalTable = new HorizontalTable.Builder()
                .withDefaultColumnWidth(20)
                .withNumberOfRows(4)
                .withNumberOfColumns(4).build();
        horizontalTable.create();
        String shape = horizontalTable.shape();

        assertThat(shape).isEqualTo("4x4");

    }

    @Test
    public void should_create_table_with_header_when_i_provide_valid_data() {

        Table horizontalTable = new HorizontalTable.Builder().withDefaultColumnWidth(10)
                .withNumberOfRows(4)
                .withNumberOfColumns(2).withHeader(new Header("sno"),
                        new Header("name", 20)).build();
        String tableWithHeader = horizontalTable.create();
        String expected = "+------------+----------------------+\n" +
                "| sno        | name                 |\n" +
                "+------------+----------------------+\n" +
                "|            |                      |\n" +
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

        Table horizontalTable = new HorizontalTable.Builder().withDefaultColumnWidth(10)
                .withNumberOfRows(4)
                .withNumberOfColumns(2).withHeader(new Header("sno"),
                        new Header("name", 20)).build();
        String table = horizontalTable.insertRows("1", "himank").insertRows("2", "akash").insertRows("3", "varun").
                insertRows("4", "vasu").create();
        String expected = "+------------+----------------------+\n" +
                "| sno        | name                 |\n" +
                "+------------+----------------------+\n" +
                "| 1          | himank               |\n" +
                "+------------+----------------------+\n" +
                "| 2          | akash                |\n" +
                "+------------+----------------------+\n" +
                "| 3          | varun                |\n" +
                "+------------+----------------------+\n" +
                "| 4          | vasu                 |\n" +
                "+------------+----------------------+\n";
        assertThat(table).isEqualTo(expected);
    }


    @Test
    public void should_throw_exception_when_data_rows_are_exceeding_provided_no_of_rows() {

        Table horizontalTable = new HorizontalTable.Builder().withDefaultColumnWidth(10)
                .withNumberOfRows(4)
                .withNumberOfColumns(2)
                .withHeader(new Header("sno"),
                        new Header("name", 20)).build();
        try {
            horizontalTable
                    .insertRows("1", "himank")
                    .insertRows("2", "akash")
                    .insertRows("3", "varun")
                    .insertRows("4", "vasu")
                    .insertRows("5", "amit").create();

            fail("Should throw exception");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(InputMismatchException.class);
            assertThat(e.getMessage()).isEqualTo("Data rows exceeded the provided number of rows");
        }

    }

    @Test
    public void should_create_table_and_get_its_shape_when_valid_data_rows_and_header_are_provided() {

        Table horizontalTable = new HorizontalTable.Builder().withDefaultColumnWidth(5)
                .withHeader(new Header("sno"),
                        new Header("name", 15))
                .build();
        String table = horizontalTable.
                insertRows("1", "himank")
                .insertRows("2", "akash")
                .insertRows("3", "varun")
                .insertRows("4", "vasu")
                .insertRows("5", "amit").create();

        String shape = horizontalTable.shape();
        String expected = "+-------+-----------------+\n" +
                "| sno   | name            |\n" +
                "+-------+-----------------+\n" +
                "| 1     | himank          |\n" +
                "+-------+-----------------+\n" +
                "| 2     | akash           |\n" +
                "+-------+-----------------+\n" +
                "| 3     | varun           |\n" +
                "+-------+-----------------+\n" +
                "| 4     | vasu            |\n" +
                "+-------+-----------------+\n" +
                "| 5     | amit            |\n" +
                "+-------+-----------------+\n";
        assertThat(table).isEqualTo(expected);
        assertThat(shape).isEqualTo("6x2");

    }


}