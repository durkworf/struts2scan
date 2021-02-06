package struts2scan.payload;

import javafx.scene.control.TextArea;

public interface Check {
    boolean check(String protocol,String host,int port,String path) throws Exception;

    static void printInfo(TextArea output) {

    }
}
