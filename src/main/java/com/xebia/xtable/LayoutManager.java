package com.xebia.xtable;

import java.util.List;

public interface LayoutManager {


   String create(List<String[]> rows, int[] columnWidth);

   String shape(int numberOfRows,int numberOfColumns);

}
