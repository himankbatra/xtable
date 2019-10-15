package com.xebia.xtable.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class XtableTests {

    @Test
    public void should_able_to_create_blank_table_with_given_no_of_row_and_no_of_column() {
        // Arrange
        Xtable xtable = new Xtable();
        // Act
        xtable.createEmpty(4, 4).print();
        //Assert
        assertThat(xtable).isNotNull();

    }
}