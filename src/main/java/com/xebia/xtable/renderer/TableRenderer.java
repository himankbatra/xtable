package com.xebia.xtable.renderer;

public interface TableRenderer {

    void render(String table);

    static TableRenderer consoleBasedRender() {
        return new ConsoleBasedRenderer();
    }

}
