package struts2scan.controller;

import javafx.scene.control.TextArea;

public class Log {
    public static void info(String msg){
        info(msg);
    }
    public static void info(String msg , TextArea output){

            output.appendText(msg+"\n");
    }
}
