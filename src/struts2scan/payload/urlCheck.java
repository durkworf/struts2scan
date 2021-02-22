package struts2scan.payload;

import struts2scan.ui.PromptMessageUI;

import java.net.MalformedURLException;
import java.net.URL;

public class urlCheck {

    public URL urlCheck(String url){
        int port = 80;
        String ip = "127.0.0.1";
        String protocol = "http";
        String path;
        //获取url
        try {
            URL u = new URL(url);
            protocol = u.getProtocol().toLowerCase();
            ip =u.getHost();
            if(protocol.equals("https")){
                port = 443;
            }
            if(u.getPort() != -1){
                port = u.getPort();
            }
            path = u.getPath();
            return u;
        }catch (MalformedURLException e) {
            System.out.println("error");
            PromptMessageUI.getAlert("错误","不是一个有效的url");
            return null;
        }
    }
}
