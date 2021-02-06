package struts2scan.controller;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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

    public void setOutput(TextArea output) {
        this.output = output;
    }

    public ComboBox getCombobox() {
        return combobox;
    }

    public void setCombobox(ComboBox combobox) {
        this.combobox = combobox;
    }

    public TextField getUrl() {
        return url;
    }

    public void setUrl(TextField url) {
        this.url = url;
    }

    @FXML
    private void runVulTest(MouseEvent actionEvent) throws Exception {
        //检测url
        new vulCheck().run(getUrl(),getCombobox(),getOutput());
        //获取选择的结果
        //System.out.println(combobox.getSelectionModel().getSelectedItem());
    }

    //检测函数
    public void run(String url,String vulName){

    }

}
