package com.xebia.xtable.domain;

public class XtableApp {

    public static void main(String[] args) {

        Xtable xtable = new Xtable(20);
        xtable.createEmpty(4, 4).print();

    }
}
