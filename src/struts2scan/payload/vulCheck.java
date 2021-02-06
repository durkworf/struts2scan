package struts2scan.payload;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import struts2scan.controller.Controller;
import struts2scan.controller.Log;
import struts2scan.ui.PromptMessageUI;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;


public class vulCheck{


    public void run(TextField url, ComboBox combobox, TextArea output) throws Exception {
        int port = 80;
        String ip = "127.0.0.1";
        String protocol = "http";
        String path;
        //获取url
        try {
            URL u = new URL(url.getText());
            protocol = u.getProtocol().toLowerCase();
            ip =u.getHost();
            if(protocol.equals("https")){
                port = 443;
            }
            if(u.getPort() != -1){
                port = u.getPort();
            }
            path = u.getPath();
        }catch (MalformedURLException e) {
            PromptMessageUI.getAlert("输入错误", "不是一个有效的url");
            return;
        }

        //获取要检查的漏洞
        String vulname = (String) combobox.getSelectionModel().getSelectedItem();
        boolean res = false;
        if("ALL".equals(vulname)){
            Log.info("[*]========开始检测所有漏洞========",output);
            for(String name:Store.VUL_LIST.keySet()){

                Check check = Store.VUL_LIST.get(name);


                res = check.check(protocol,ip,port,path);


                if(res){
                    Log.info("[+]"+name+"漏洞存在",output);
                    Log.info("[+] S2-001:影响版本Struts 2.0.0-2.0.8",output);
                }else {
                    Log.info("[-]"+name+"漏洞不存在，或URL地址无法打开",output);
                }
            }
            Log.info("[*]========检测完成========",output);
        }else{
            switch (vulname){
                case "S2_001":
                    Log.info("[*]========开始检S2_001========",output);
                    Check check = new S2_001();

                    res = check.check(protocol,ip,port,path);

                    if(res){
                        Log.info("[+]"+vulname+"漏洞存在",output);
                        Log.info("[+] S2-001:影响版本Struts 2.0.0-2.0.8",output);
                    }else {
                        Log.info("[-]"+vulname+"漏洞不存在，或URL地址无法打开",output);
                    }
                    Log.info("[*]========检测完成========",output);
            }
        }






    }
}
