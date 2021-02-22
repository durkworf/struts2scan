package struts2scan.payload;


import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;

import java.net.URL;

public  class VulCheckThread extends Service{
    public static URL U;
    public static String VULNAME;
    public static  TextArea OUTPUT;

    public VulCheckThread(URL url, String vulname, TextArea output) {
        U = url;
        VULNAME = vulname;
        OUTPUT = output;
    }


    protected Task createTask(){
        return new Task() {
            @Override
            protected Object call() throws Exception {
                try {
                    vulCheck vc = new vulCheck();
                    vc.check(U,VULNAME,OUTPUT);
                }catch (Exception e){
                    e.printStackTrace();
                }


                return null;
            }
        };
    }

}
