package view.userManagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.User;
import services.daoServices.UserService;
import util.utility.AlertPopUp;
import util.validation.DataValidation;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserDetailController implements Initializable {

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> userNameColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> passwordColumn;

    @FXML
    private TableColumn<User, String> userTypeColumn;

    @FXML
    private ComboBox<String> actionTypeComboBox;

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private ChoiceBox<String> userTypeChoiceBox;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label userTypeLabel;

    @FXML
    private Label actionTypeLabel;

    @FXML
    private Label passwordValidationLabel;

    @FXML
    private TextField SearchTextField;

    @FXML
    private Button updateButton;

    @FXML
    private Button addButton;

    private User selectedUser = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setComponentData();
        loadData();
    }

    private void loadData() {
        UserService userService = new UserService();
        ObservableList<User> userObservableList = userService.loadAllUserData();

        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("uName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("uEmmail"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("uPassword"));
        userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("uType"));

        userTable.setItems(null);
        userTable.setItems(userObservableList);
    }

    @FXML
    void addUser(ActionEvent event) {
        if (userValidation()) {
            User user = new User();
            UserService userService = new UserService();

            user.setuName(userNameTextField.getText());
            user.setuEmmail(emailTextField.getText());
            user.setuPassword(passwordField.getText());
            user.setuType(userTypeChoiceBox.getValue());

            if (userService.insertUserData(user)) {
                AlertPopUp.insertSuccesfully("User Added Successfully");
                loadData();
                clearFields(event);
            } else
                AlertPopUp.insertionFailed("Failed to Add User");

        } else
            dataValidationMessage();
    }

    @FXML
    void updateUser(ActionEvent event) {
        if (userValidation()) {
            User user = new User();
            UserService userService = new UserService();

            user.setuName(userNameTextField.getText());
            user.setuEmmail(emailTextField.getText());
            user.setuPassword(passwordField.getText());
            user.setuType(userTypeChoiceBox.getValue());

            if (actionTypeComboBox.getValue().equals("Update User Info")) {
                if (userService.updateUserData(user)) {
                    AlertPopUp.insertSuccesfully("User Updates Successfully");
                    loadData();
                    clearFields(event);
                } else
                    AlertPopUp.insertionFailed("Failed to Update User");
            }
            if (actionTypeComboBox.getValue().equals("Update Password")) {
                if (userService.updateUserPassword(user)) {
                    AlertPopUp.insertSuccesfully("Password Updates Successfully");
                    loadData();
                    clearFields(event);
                } else
                    AlertPopUp.insertionFailed("Failed to Update Password");
            }
        } else
            dataValidationMessage();
    }

    @FXML
    private void deleteUser() {
        if (!(selectedUser.getuName().isEmpty())) {
            UserService userService = new UserService();
            Optional<ButtonType> action = AlertPopUp.deleteConfirmation("User");
            if (action.get() == ButtonType.OK) {
                if (userService.deleteUserData(selectedUser.getuEmmail())) {
                    AlertPopUp.deleteSuccesfull("User");
                    loadData();
                } else
                    AlertPopUp.deleteFailed("User");
            } else
                loadData();
        } else {
            AlertPopUp.selectRowToDelete("User");
        }
    }

    @FXML
    void setSelectedUserDataToFields(MouseEvent event) {
        try {
            selectedUser = userTable.getSelectionModel().getSelectedItem();
            userNameTextField.setText(selectedUser.getuName());
            emailTextField.setText(selectedUser.getuEmmail());
            userTypeChoiceBox.setValue(selectedUser.getuType());
        } catch (NullPointerException exception) {

        }
    }

    @FXML
    void clearFields(ActionEvent event) {
        userNameTextField.setText("");
        emailTextField.setText("");
        userTypeChoiceBox.setValue("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        clearLabels();
    }

    private void clearLabels() {
        userNameLabel.setText("");
        emailLabel.setText("");
        passwordValidationLabel.setText("");
        userTypeLabel.setText("");
        actionTypeLabel.setText("");
    }

    private void setComponentData() {
        ObservableList<String> actionList = FXCollections.observableArrayList("Add User Info", "Update User Info", "Update Password");
        ObservableList<String> userTypeList = FXCollections.observableArrayList("Admin", "Guest");

        userTypeChoiceBox.setValue("Admin");
        userTypeChoiceBox.setItems(userTypeList);

        actionTypeComboBox.setValue("Add User Info");
        actionTypeComboBox.setItems(actionList);
    }

    private boolean userValidation() {
        if (actionTypeComboBox.getValue().equals("Add User Info")) {
            return DataValidation.TextFieldNotEmpty(userNameTextField.getText())
                    && DataValidation.TextFieldNotEmpty(emailTextField.getText())
                    && DataValidation.TextFieldNotEmpty(passwordField.getText())
                    && DataValidation.TextFieldNotEmpty(confirmPasswordField.getText())

                    && DataValidation.isValidMaximumLength(userNameTextField.getText(), 45)
                    && DataValidation.isValidMaximumLength(emailTextField.getText(), 45)
                    && DataValidation.isValidMaximumLength(passwordField.getText(), 45)
                    && DataValidation.isValidMaximumLength(confirmPasswordField.getText(), 45)

                    && DataValidation.isValidEmail(emailTextField.getText())
                    && DataValidation.PasswordFieldMatch(passwordField, confirmPasswordField);
        } else if (actionTypeComboBox.getValue().equals("Update User Info")) {
            return DataValidation.TextFieldNotEmpty(userNameTextField.getText())
                    && DataValidation.TextFieldNotEmpty(emailTextField.getText())

                    && DataValidation.isValidMaximumLength(userNameTextField.getText(), 45)
                    && DataValidation.isValidMaximumLength(emailTextField.getText(), 45)

                    && DataValidation.isValidEmail(emailTextField.getText());
        } else {
            return DataValidation.TextFieldNotEmpty(passwordField.getText())
                    && DataValidation.TextFieldNotEmpty(confirmPasswordField.getText())

                    && DataValidation.isValidMaximumLength(passwordField.getText(), 45)
                    && DataValidation.isValidMaximumLength(confirmPasswordField.getText(), 45)

                    && DataValidation.PasswordFieldMatch(passwordField, confirmPasswordField);
        }
    }

    private void dataValidationMessage() {
        if (actionTypeComboBox.getValue().equals("Add User Info")) {
            if (!(DataValidation.TextFieldNotEmpty(userNameTextField.getText())
                    && DataValidation.TextFieldNotEmpty(emailTextField.getText())
                    && DataValidation.TextFieldNotEmpty(passwordField.getText())
                    && DataValidation.TextFieldNotEmpty(confirmPasswordField.getText()))) {
                DataValidation.TextFieldNotEmpty(userNameTextField.getText(), userNameLabel, "");
                DataValidation.TextFieldNotEmpty(emailTextField.getText(), emailLabel, "");
                DataValidation.TextFieldNotEmpty(passwordField.getText(), passwordValidationLabel, "");
                DataValidation.TextFieldNotEmpty(confirmPasswordField.getText(), passwordValidationLabel, "");
            }
            if (!(DataValidation.isValidMaximumLength(userNameTextField.getText(), 45)
                    && DataValidation.isValidMaximumLength(emailTextField.getText(), 45)
                    && DataValidation.isValidMaximumLength(passwordField.getText(), 45)
                    && DataValidation.isValidMaximumLength(confirmPasswordField.getText(), 45))) {

                DataValidation.isValidMaximumLength(userNameTextField.getText(), 45, userNameLabel, "");
                DataValidation.isValidMaximumLength(emailTextField.getText(), 45, emailLabel, "");
                DataValidation.isValidMaximumLength(passwordField.getText(), 45);
                DataValidation.isValidMaximumLength(confirmPasswordField.getText(), 45);
            }
            if (!(DataValidation.isValidEmail(emailTextField.getText())
                    && DataValidation.PasswordFieldMatch(passwordField, confirmPasswordField))) {
                DataValidation.isValidEmail(emailTextField.getText(), emailLabel, "");
                DataValidation.PasswordFieldMatch(passwordField, confirmPasswordField, passwordValidationLabel, passwordValidationLabel, "");

            }
        } else if (actionTypeComboBox.getValue().equals("Update User Info")) {
            if (!(DataValidation.TextFieldNotEmpty(userNameTextField.getText())
                    && DataValidation.TextFieldNotEmpty(emailTextField.getText()))) {
                DataValidation.TextFieldNotEmpty(userNameTextField.getText(), userNameLabel, "");
                DataValidation.TextFieldNotEmpty(emailTextField.getText(), emailLabel, "");
            }
            if (!(DataValidation.isValidMaximumLength(userNameTextField.getText(), 45)
                    && DataValidation.isValidMaximumLength(emailTextField.getText(), 45))) {
                DataValidation.isValidMaximumLength(userNameTextField.getText(), 45, userNameLabel, "");
                DataValidation.isValidMaximumLength(emailTextField.getText(), 45, emailLabel, "");
            }
            if (!(DataValidation.isValidEmail(emailTextField.getText()))) {
                DataValidation.isValidEmail(emailTextField.getText(), emailLabel, "");
            }
        } else {
            if (!(DataValidation.TextFieldNotEmpty(passwordField.getText())
                    && DataValidation.TextFieldNotEmpty(confirmPasswordField.getText()))) {
                DataValidation.TextFieldNotEmpty(passwordField.getText(), passwordValidationLabel, "");
                DataValidation.TextFieldNotEmpty(confirmPasswordField.getText(), passwordValidationLabel, "");
            }
            if (!(DataValidation.isValidMaximumLength(passwordField.getText(), 45)
                    && DataValidation.isValidMaximumLength(confirmPasswordField.getText(), 45))) {
                DataValidation.isValidMaximumLength(passwordField.getText(), 45, passwordValidationLabel, "");
                DataValidation.isValidMaximumLength(confirmPasswordField.getText(), 45, passwordValidationLabel, "");
            }
            if (!DataValidation.PasswordFieldMatch(passwordField, confirmPasswordField)) {
                DataValidation.PasswordFieldMatch(passwordField, confirmPasswordField, passwordValidationLabel, passwordValidationLabel, "");
            }
        }
    }

    @FXML
    private void checkUserNameAvailability() {
        ObservableList<User> modelList = userTable.getItems();
        ArrayList<String> userList = new ArrayList<>();
        for (User user : modelList) {
            userList.add(user.getuEmmail().toLowerCase());
        }
        if (emailTextField.getText().isEmpty()) {
            emailLabel.setStyle("-fx-text-fill: #ff0000 ");
            emailLabel.setText("Email Cannot be empty");
        } else if (userList.contains(emailTextField.getText().toLowerCase())) {
            emailLabel.setStyle("-fx-text-fill: #ff0000 ");
            emailLabel.setText("Email Already Taken!!");
        } else {
            emailLabel.setStyle("-fx-text-fill: #00B605 ");
            emailLabel.setText("Email Available");
        }
    }

    @FXML
    private void setActionType() {
        if (actionTypeComboBox.getValue().equals("Add User Info")) {
            resetComponentEditable();
        } else if (actionTypeComboBox.getValue().equals("Update User Info")) {
            resetComponentEditable();
            addButton.setVisible(false);
            passwordField.setEditable(false);
            confirmPasswordField.setEditable(false);
        } else {
            resetComponentEditable();
            addButton.setVisible(false);
            userNameTextField.setEditable(false);
            emailTextField.setEditable(false);
            userTypeChoiceBox.setDisable(true);
        }
    }

    private void resetComponentEditable() {
        addButton.setVisible(true);
        userNameTextField.setEditable(true);
        emailTextField.setEditable(true);
        userTypeChoiceBox.setDisable(false);
        passwordField.setEditable(true);
        confirmPasswordField.setEditable(true);
    }

}
