package view.appHome;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.navigation.MenuNavigation;
import services.navigation.NavigationInterface;
import util.authentication.UserAuthentication;
import util.utility.CacheHandler;
import util.validation.DataValidation;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    @FXML
    private AnchorPane baseAnchorPane;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label validationLabel;

    @FXML
    private Label serverStatusLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setdata();
    }

    private void setdata() {
        if (CacheHandler.getServerInfo().getServerStatus().equals("on")) {
            serverStatusLabel.setStyle("-fx-text-fill: #00B605 ");
            serverStatusLabel.setText("Server is Running");
        } else {
            serverStatusLabel.setStyle("-fx-text-fill: #ff0000 ");
            serverStatusLabel.setText("Server is not Running!, Please log in to Start the Server");
        }
    }

    private void loadHome(ActionEvent actionEvent) {
        NavigationInterface navigationInterface = new MenuNavigation();
        navigationInterface.loadHomeBaseStage(baseAnchorPane);
    }

    @FXML
    public void loginUser(ActionEvent actionEvent) {
        if (fieldValidation()) {
            String authenticateMessage = UserAuthentication.authenticateUser(emailTextField.getText(), passwordField.getText());

            if (authenticateMessage.equals("true"))
                loadHome(actionEvent);
            else
                validationLabel.setText(authenticateMessage);
        } else
            fieldValidationMessage();
    }

    private boolean fieldValidation() {
        return DataValidation.TextFieldNotEmpty(emailTextField.getText())
                && DataValidation.TextFieldNotEmpty(passwordField.getText())
                && DataValidation.isValidEmail(emailTextField.getText());
    }

    private void fieldValidationMessage() {
        if (!(DataValidation.TextFieldNotEmpty(emailTextField.getText())
                && DataValidation.TextFieldNotEmpty(passwordField.getText()))) {
            DataValidation.TextFieldNotEmpty(emailTextField.getText(), validationLabel, "User Name/Password Fields Cannot be Empty");
            DataValidation.TextFieldNotEmpty(passwordField.getText(), validationLabel, "User Name/Password Fields Cannot be Empty");
            DataValidation.isValidEmail(emailTextField.getText(), validationLabel, "Invalid Email Address ");
        }
    }

    @FXML
    private void clearLabel() {
        validationLabel.setText("");
    }

    @FXML
    private void clearFields() {
        emailTextField.setText("");
        passwordField.setText("");
    }

}