package view.appHome;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.authentication.UserAuthentication;
import util.utility.CacheHandler;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Scene scene = new Scene(new AnchorPane());
        CacheHandler.createConfig();

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setScene(new Scene(root, 1280, 900));
        primaryStage.setTitle("B O O K   M A R T  -  S A L E S   B U D D Y   S E R V E R");
        primaryStage.getIcons().add(new Image("/lib/images/logo.png"));
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        //To change body of generated methods, choose Tools | Templates.
        if(CacheHandler.getServerInfo().getServerStatus().equals("on")){
            UserAuthentication.rmiServer.stopRMIServer();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
