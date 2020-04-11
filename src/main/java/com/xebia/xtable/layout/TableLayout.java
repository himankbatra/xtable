package com.xebia.xtable.layout;

import java.util.List;

public interface TableLayout {

    static HorizontalTableLayout horizontalLayout() {
        return new HorizontalTableLayout();
    }

    static VerticalTableLayout verticalLayout() {
        return new VerticalTableLayout();
    }

    String create(List<String[]> rows, int[] columnWidth);


}
