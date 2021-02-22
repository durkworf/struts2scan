package struts2scan.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        Image img = new Image(this.getClass().getResourceAsStream("/struts2scan/res/alpenliebe.png"));
        //System.out.println(img);
        primaryStage.getIcons().add(img);

        primaryStage.setTitle("struts2 scan by Alpenliebe");
        primaryStage.show();

    }

}
