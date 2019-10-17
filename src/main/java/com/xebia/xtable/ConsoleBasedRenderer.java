package com.xebia.xtable;

public class ConsoleBasedRenderer implements TableRenderer {


    @Override
    public void render(String table) {
        System.out.println(table);
    }


}
