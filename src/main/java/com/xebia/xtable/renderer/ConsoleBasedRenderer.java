package com.xebia.xtable.renderer;

 class ConsoleBasedRenderer implements TableRenderer {


    @Override
    public void render(String table) {
        System.out.println(table);
    }


}
