package struts2scan.payload;

import javafx.scene.control.TextArea;

import java.net.URL;

public interface Check {
    boolean check(String protocol,String host,int port,String path) throws Exception;

    String printInfo();

    boolean check(URL url) throws Exception;
}
