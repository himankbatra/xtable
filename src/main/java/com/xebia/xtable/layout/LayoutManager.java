package com.xebia.xtable.layout;

import java.util.List;

public interface LayoutManager {

   static HorizontalLayout HorizontalLayout()
   {
      return new HorizontalLayout();
   }

   static VerticalLayout VerticalLayout()
   {
      return new VerticalLayout();
   }

   String create(List<String[]> rows, int[] columnWidth);

   String shape(int numberOfRows,int numberOfColumns);

}
