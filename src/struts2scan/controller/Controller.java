package struts2scan.controller;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import struts2scan.payload.VulCheckThread;
import struts2scan.payload.urlCheck;
import struts2scan.payload.vulCheck;
import struts2scan.ui.PromptMessageUI;

import java.net.MalformedURLException;
import java.net.URL;


public class Controller {

    @FXML
    private ComboBox combobox;
    @FXML
    private TextField url;
    @FXML
    private TextArea output;


    public TextArea getOutput() {
        return output;
    }


    public ComboBox getCombobox() {
        return combobox;
    }


    public TextField getUrl() {
        return url;
    }

    @FXML
    private void runVulTest(MouseEvent actionEvent) throws Exception {
        //获取url检测的对象，任务启动后检测
        urlCheck uc = new urlCheck();
        //获取url
        URL u = uc.urlCheck(getUrl().getText().trim());
        //获取输出窗口对象实时输出
        TextArea output = getOutput();
        //检测漏洞
        String vulname = (String) getCombobox().getSelectionModel().getSelectedItem();
        VulCheckThread vt = new VulCheckThread(u,vulname,output);
        vt.start();

    }
    

}