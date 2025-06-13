package com.monique.sectgeon.mocha;

import org.mocha.Application;
import org.mocha.annotations.Window;

@Window(width = 1280, height = 720, fullScreen = true, title = "Sectgeon-Mocha")
public class App extends Application{

    public App() {
        

        init();
    }

    public static void main(String[] args) {
        new App();
    }
}
