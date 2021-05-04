package view.appHome;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import services.navigation.MenuNavigation;
import services.navigation.NavigationInterface;
import services.rmiService.RMIServer;
import util.authentication.UserAuthentication;
import util.utility.CacheHandler;
import util.utility.MenuBarController;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class DesktopStageController implements Initializable {

    @FXML
    private AnchorPane baseAnchorPane;

    @FXML
    private AnchorPane detailAnchorPane;

    @FXML
    private MenuItem userSettingMenu;

    @FXML
    private MenuItem serverMenu;

    @FXML
    private Label dateLabel;

    @FXML
    private JFXButton homeButton;

    @FXML
    private JFXButton stationaryButton;

    @FXML
    private JFXButton bookButton;

    @FXML
    private JFXButton locationButton;

    @FXML
    private JFXButton shopInfoButton;

    @FXML
    private JFXButton requestsButton;

    @FXML
    private JFXButton feedbackButton;

    @FXML
    private JFXButton userButton;

    @FXML
    private JFXButton serverButton;

    @FXML
    private Button checkRequestButton;

    @FXML
    private Button checkFeedbackButton;

    @FXML
    private Label newFeedbackLabel;

    @FXML
    private Label newRequestLabel;

    @FXML
    private TextField textField;

    @FXML
    private Label timeLabel;

    @FXML
    private JFXToggleButton serverStatusToggleButton;

    @FXML
    private Label userSessionLabel;

    private NavigationInterface navigationInterface = new MenuNavigation();

    public void initialize(URL location, ResourceBundle resources) {
        setStageData();
    }

    private void loadHome() {
        navigationInterface.loadStartupHome(detailAnchorPane);
    }

    @FXML
    private void loadHome(ActionEvent actionEvent) {
        loadHome();
    }

    @FXML
    private void loadBooks(ActionEvent actionEvent) {
        navigationInterface.loadBookHome(detailAnchorPane);
    }

    @FXML
    private void loadRequestItem(ActionEvent actionEvent) {
        navigationInterface.loadRequestHome(detailAnchorPane);
    }

    @FXML
    private void loadFeedback(ActionEvent actionEvent) {
        navigationInterface.loadFeedbackHome(detailAnchorPane);
    }

    @FXML
    private void loadShopInfo(ActionEvent actionEvent) {
        navigationInterface.loadShopInfoHome(detailAnchorPane);
    }

    @FXML
    private void loadUserSetting(ActionEvent actionEvent) {
        navigationInterface.loadUserSettings(detailAnchorPane);

    }

    @FXML
    private void loadServerSetting(ActionEvent actionEvent) {
        navigationInterface.loadServerSetting(detailAnchorPane);

    }

    @FXML
    private void logoutAccount(ActionEvent actionEvent) {
        navigationInterface.logoutAccount(baseAnchorPane);
    }

    @FXML
    private void setSelection() {
        if (homeButton.isPressed())
            MenuBarController.setMenuNumber(0);
        else if (bookButton.isPressed())
            MenuBarController.setMenuNumber(1);
        else if (requestsButton.isPressed())
            MenuBarController.setMenuNumber(2);
        else if (feedbackButton.isPressed())
            MenuBarController.setMenuNumber(3);
        else if (shopInfoButton.isPressed())
            MenuBarController.setMenuNumber(4);
        else if (userButton.isPressed())
            MenuBarController.setMenuNumber(5);
        else
            MenuBarController.setMenuNumber(6);
        setStyle();
    }

    public void setMenuNumber(int menuNumber) {
        MenuBarController.setMenuNumber(menuNumber);
    }

    public int getMenuNumber() {
        return MenuBarController.getMenuNumber();
    }

    public void setStyle() {
        String selectionColor = "-fx-background-color:  #00a8ff; ";
        resetButtons();
        switch (MenuBarController.getMenuNumber()) {
            case 0:
                homeButton.setStyle(selectionColor);
                break;
            case 1:
                bookButton.setStyle(selectionColor);
                break;
            case 2:
                requestsButton.setStyle(selectionColor);
                break;
            case 3:
                feedbackButton.setStyle(selectionColor);
                break;
            case 4:
                shopInfoButton.setStyle(selectionColor);
                break;
            case 5:
                userButton.setStyle(selectionColor);
                break;
            case 6:
                serverButton.setStyle(selectionColor);
                break;
            default:
                homeButton.setStyle(selectionColor);

        }
    }

    private void resetButtons() {
        String defaultColor = "-fx-background-color:  #01579B; ";
        homeButton.setStyle(defaultColor);
        bookButton.setStyle(defaultColor);
        shopInfoButton.setStyle(defaultColor);
        requestsButton.setStyle(defaultColor);
        feedbackButton.setStyle(defaultColor);
        userButton.setStyle(defaultColor);
        serverButton.setStyle(defaultColor);
    }

    @FXML
    private void switchServerStatus(ActionEvent actionEvent) {

        if (serverStatusToggleButton.isSelected()) {
            if (UserAuthentication.rmiServer.startRMIServer()) {
                serverStatusToggleButton.setSelected(true);
            } else
                serverStatusToggleButton.setSelected(false);
        } else {
            if (UserAuthentication.rmiServer.stopRMIServer()) {
                serverStatusToggleButton.setSelected(false);
            } else
                serverStatusToggleButton.setSelected(true);
        }
        if (navigationInterface.getServerController() != null) {

            navigationInterface.getServerController().statusTextField.setText(CacheHandler.getServerInfo().getServerStatus());
        }
    }

    private void setSwitchStatus() {
        serverStatusToggleButton.setSelected(CacheHandler.getServerInfo().getServerStatus().equals("on"));
    }

    private void setStageData() {
        UserAuthentication.rmiServer = new RMIServer();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        String strDate = formatter.format(date);
        dateLabel.setText(strDate);
        loadHome();
        setSwitchStatus();
        userSessionLabel.setText(UserAuthentication.getAuthenticatedUser().getuName());

        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        final Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(500),
                        event -> {
                            final long diff = System.currentTimeMillis();
                            timeLabel.setText(timeFormat.format(diff));
                        }
                )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
