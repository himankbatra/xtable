package com.xebia.xtable;

public interface Table {


    Table insertRows(String... newRow);

    String shape();

    void render();

    String create();
}
