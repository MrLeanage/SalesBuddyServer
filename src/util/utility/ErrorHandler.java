package util.utility;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ErrorHandler {
    private StringProperty errorCode = null;
    private StringProperty errorMessage = null;

    public ErrorHandler() {
    }

    public String getErrorCode() {
        return errorCode.get();
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = new SimpleStringProperty(errorCode);
    }

    public String getErrorMessage() {
        return errorMessage.get();
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = new SimpleStringProperty(errorMessage);
    }

    public boolean validateErrorCode() {
        if (getErrorCode().equals("00")) {
            return true;
        } else {
            return false;
        }
    }
}
