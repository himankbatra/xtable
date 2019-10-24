package com.xebia.xtable.layout;

import java.util.List;

public interface TableLayout {

   static HorizontalTableLayout HorizontalLayout()
   {
      return new HorizontalTableLayout();
   }

   static VerticalTableLayout VerticalLayout()
   {
      return new VerticalTableLayout();
   }

   String create(List<String[]> rows, int[] columnWidth);

   String shape(int numberOfRows,int numberOfColumns);

}
