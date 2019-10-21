package com.xebia.xtable;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class TableElementCreatorTests {

    @Test
    public void should_create_line_when_i_provide_array_of_column_width_and_no_of_columns() {

        TableElementCreator tableElementCreator = new TableElementCreator();
        String line = tableElementCreator.createLine(2, new int[]{10, 20});
        String expected = "+------------+----------------------+\n";
        assertThat(line).isEqualTo(expected);
    }

    @Test
    public void should_create_a_row_when_i_provide_a_row_data_and_array_of_column_width() {

        TableElementCreator tableElementCreator = new TableElementCreator();
        String row = tableElementCreator.createRow(new String[]{"1", "himank"}, new int[]{10, 20});
        String expected = "| 1          | himank               |\n";
        assertThat(row).isEqualTo(expected);

    }

    @Test
    public void should_truncate_the_column_value_if_exceeding_the_given_column_width() {

        TableElementCreator tableElementCreator = new TableElementCreator();
        String row = tableElementCreator.createRow(new String[]{"1", "himank"},
                new int[]{5, 5});
        String expected = "| 1     | hi... |\n";
        assertThat(row).isEqualTo(expected);


    }

    @Test
    public void should_throw_exception_if_provided_column_width_is_less_than_minimum_column_width() {

        TableElementCreator tableElementCreator = new TableElementCreator();
        try {
            tableElementCreator.createRow(new String[]{"1", "himank"},
                    new int[]{TableConstants.MIN_COLUMN_WIDTH - 1, 20});
            fail("Should throw exception");
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
            assertThat(e.getMessage()).isEqualTo("column width should be greater or equal to " + TableConstants.MIN_COLUMN_WIDTH);
        }


    }


}