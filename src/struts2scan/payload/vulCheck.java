package struts2scan.payload;

import javafx.scene.control.TextArea;

import java.net.URL;


public  class vulCheck  {


    public void check(URL url, String vulname, TextArea output) throws Exception { ;


        int port = 80;
        String ip = "127.0.0.1";
        String protocol = "http";
        String path;
        //获取url
        protocol = url.getProtocol().toLowerCase();
        ip =url.getHost();
        if(protocol.equals("https")){
            port = 443;
        }
        if(url.getPort() != -1){
            port = url.getPort();
        }
        path = url.getPath();

        //获取要检查的漏洞
        boolean res = false;
        if("ALL".equals(vulname)){
            output.setText("[*]========开始检测所有漏洞========\n");

            for(String name:Store.VUL_LIST.keySet()){

                Check check = Store.VUL_LIST.get(name);


                res = check.check(protocol,ip,port,path);


                if(res){
                    output.appendText("[+] "+name+"漏洞存在\n");
                    output.appendText(check.printInfo());
                }else {
                    output.appendText("[-] "+name+"漏洞不存在，或URL地址无法打开\n");
                }
            }
            output.appendText("[*]========检测完成========\n");
        }else{
            switch (vulname){
                case "S2_001":
                    output.setText("[*]========开始检S2_001========\n");
                    Check check = new S2_001();
                    res = check.check(protocol,ip,port,path);
                    if(res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_003":
                    output.setText("[*]========开始检S2_003========\n");
                    check = new S2_003();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_005":
                    output.setText("[*]========开始检S2_005========\n");
                    check = new S2_005();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_007":
                    output.setText("[*]========开始检S2_007========\n");
                    check = new S2_007();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_008":
                    output.setText("[*]========开始检S2_008========\n");
                    check = new S2_008();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_009":
                    output.setText("[*]========开始检S2_009========\n");
                    check = new S2_009();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_012":
                    output.setText("[*]========开始检S2_012========\n");
                    check = new S2_012();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_013/14":
                    output.setText("[*]========开始检S2_013/14========\n");
                    check = new S2_013();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_015":
                    output.setText("[*]========开始检S2_015========\n");
                    check = new S2_015();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_016":
                    output.setText("[*]========开始检S2_016========\n");
                    check = new S2_016();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_019":
                    output.setText("[*]========开始检S2_019========\n");
                    check = new S2_019();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_029":
                    output.setText("[*]========开始检S2_029========\n");
                    check = new S2_029();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_032":
                    output.setText("[*]========开始检S2_032========\n");
                    check = new S2_032();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_033":
                    output.setText("[*]========开始检S2_033========\n");
                    check = new S2_033();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_037":
                    output.setText("[*]========开始检S2_037========\n");
                    check = new S2_037();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_045":
                    output.setText("[*]========开始检S2_045========\n");
                    check = new S2_045();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_046":
                    output.setText("[*]========开始检S2_046，这个漏洞的检测payload有问题，java下的00截断字符无效========\n");
                    check = new S2_046();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_048":
                    output.setText("[*]========开始检S2_048========\n");
                    check = new S2_048();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_052":
                    output.setText("[*]========开始检S2_052========\n");
                    check = new S2_052();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_053":
                    output.setText("[*]========开始检S2_053========\n");
                    check = new S2_053();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_devMode":
                    output.setText("[*]========开始检S2_devMode========\n");
                    check = new S2_devMode();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
                case "S2_057":
                    output.setText("[*]========开始检S2_057========\n");
                    check = new S2_057();
                    res = check.check(protocol,ip,port,path);
                    if (res){
                        output.appendText("[+] "+vulname+"漏洞存在\n");
                        output.appendText(check.printInfo());
                    }else {
                        output.appendText("[-] "+vulname+"漏洞不存在，或URL地址无法打开\n");
                    }
                    output.appendText("[*]========检测完成========\n");
                    break;
            }
        }

    }

}
