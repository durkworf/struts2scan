package struts2scan.ui;

import javafx.scene.control.Alert;

public class PromptMessageUI {
    public static void getAlert(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
