package view.serverManagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.rmiService.RMIServer;
import util.utility.AlertPopUp;
import util.utility.CacheHandler;
import util.validation.DataValidation;

import java.net.URL;
import java.util.ResourceBundle;


public class ServerSettingDetailController implements Initializable {
    @FXML
    private AnchorPane detailAnchorPane;

    @FXML
    public TextField statusTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField portTextField;

    @FXML
    private Label serverLabel;

    @FXML
    private Label portValidationLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
    }

    @FXML
    private void setData() {

        RMIServer rmiServer = CacheHandler.getServerInfo();
        serverLabel.setText(rmiServer.getAddress() + rmiServer.getServerName());
        nameTextField.setText(rmiServer.getServerName());
        portTextField.setText(rmiServer.getPortNumber());
        statusTextField.setText(rmiServer.getServerStatus());
    }

    @FXML
    private void saveAndRestartServer() {
        if (dataValidation()) {
            RMIServer rmiServer = CacheHandler.getServerInfo();
            if (rmiServer.getServerStatus().equals("on"))
                rmiServer.forceStopRMIServer();
            rmiServer.setPortNumber(portTextField.getText());
            if (CacheHandler.setServerInfo(rmiServer)) {
                AlertPopUp.successMessage("Success", "Port Number " + rmiServer.getPortNumber() + " Updated Successfully!");
                rmiServer.forceStartRMI();
                clearFields();
                setData();
            }
        }
    }

    private boolean dataValidation() {
        if (DataValidation.TextFieldNotEmpty(portTextField)
                && DataValidation.isValidNumberFormat(portTextField.getText())
                && DataValidation.checkValidPortNumber(Integer.parseInt(portTextField.getText()))) {
            return true;
        } else {
            portValidationLabel.setText("Invalid Port Number, Should be in range 1024 - 65535");
            return false;
        }
    }

    @FXML
    private void clearFields() {
        portValidationLabel.setText("");
    }

}
