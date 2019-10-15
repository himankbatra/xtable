package com.xebia.xtable.domain;

public class Application {

    public static void main(String[] args) {
      Xtable xtable = new Xtable();

       xtable.createBlankTable(2,3).print();
             /*   .withHeader("sno","name")
                .withRow("1","himank")
                .withRow("2","amit").print();*/



    }
}
