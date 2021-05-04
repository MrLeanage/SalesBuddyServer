package util.utility;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertPopUp {

    public static void generalError(Exception ex) {
        String classMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error Occured!..");
        msg.setHeaderText(null);
        msg.setContentText("Error Occured, Try Again!..Check method " + classMethod + "Exception found with Mouse click :" + ex);
        msg.showAndWait();
    }

    public static void specificGeneralError(Exception ex, String className) {
        String classMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error Occured! from Class :" + className);
        msg.setHeaderText(null);
        msg.setContentText("Error Occured, Try Again!..Check method " + classMethod + "Exception found with Mouse click :" + ex);
        msg.showAndWait();
    }

    public static void successMessage(String title, String content) {
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setTitle(title);
        msg.setHeaderText(null);
        msg.setContentText(content);
        msg.showAndWait();
    }

    public static void failMessage(String title, String content) {
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle(title);
        msg.setHeaderText(null);
        msg.setContentText(content);
        msg.showAndWait();
    }

    public static void connectionError(Exception ex) {
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Database Connection Error!..");
        msg.setHeaderText(null);
        msg.setContentText("Database Connection Failed, Exception code in:" + ex);
        msg.showAndWait();
    }

    public static void sqlRecordNotFound(String text) {
        Alert successMsg = new Alert(Alert.AlertType.ERROR);
        successMsg.setTitle("Database Record not Found!..");
        successMsg.setHeaderText(null);
        successMsg.setContentText("Database Record not Found for " + text);
        successMsg.showAndWait();
    }

    public static void sqlQueryError(Exception ex) {
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("SQL Query Error!..");
        msg.setHeaderText(null);
        msg.setContentText("Query Execution Failed, Exception in :" + ex);
        msg.showAndWait();

    }

    public static void noRecordFound(ErrorHandler errorHandler) {
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("No Records Found");
        msg.setHeaderText("Error Code " + errorHandler.getErrorCode() + " Occured");
        msg.setContentText("No records found for Your Request " + errorHandler.getErrorMessage());
        msg.showAndWait();
    }

    public static Optional<ButtonType> sessionEndConfirmation(String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Do you want to Logout?");
        alert.setHeaderText(null);
        alert.setContentText(text);
        Optional<ButtonType> action = alert.showAndWait();
        return action;
    }

    public static void insertSuccesfully(String text) {
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setTitle("Successfull..");
        msg.setHeaderText(null);
        msg.setContentText(text + " Added Successfully.. ");
        msg.showAndWait();

    }

    public static void emptyInsertionFailed(String text) {
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("No Records Found");
        msg.setHeaderText(null);
        msg.setContentText(text);
        msg.showAndWait();

    }

    public static void insertionFailed(String text) {
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error Occured!..");
        msg.setHeaderText(null);
        msg.setContentText(text);
        msg.showAndWait();
    }

    public static void insertionFailed(Exception ex, String text) {
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error Occured!..");
        msg.setHeaderText(null);
        msg.setContentText(text + " not Added, Try Again!..SQL Exception found in :" + ex);
        msg.showAndWait();
    }

    public static void updateSuccesfully(String text) {
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setTitle("Successfull..");
        msg.setHeaderText(null);
        msg.setContentText(text + " Updated Successfully.. ");
        msg.showAndWait();
    }

    public static void updateFailed(String text) {
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error Occured!..");
        msg.setHeaderText(null);
        msg.setContentText(text + " not Updated, Try Again!..");
        msg.showAndWait();
    }

    public static void updateFailed(Exception ex, String text) {
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error Occured!..");
        msg.setHeaderText(null);
        msg.setContentText(text + " not Updated, Try Again!..SQL Exception found in :" + ex);
        msg.showAndWait();
    }

    public static void selectRow(String text) {
        Alert successMsg = new Alert(Alert.AlertType.INFORMATION);
        successMsg.setTitle("Please Select..");
        successMsg.setHeaderText(null);
        successMsg.setContentText("Please Select a " + text);
        successMsg.showAndWait();
    }

    public static void selectRowToUpdate(String text) {
        Alert successMsg = new Alert(Alert.AlertType.INFORMATION);
        successMsg.setTitle("Please Select..");
        successMsg.setHeaderText(null);
        successMsg.setContentText("Please Select a " + text + " record to Update..");
        successMsg.showAndWait();
    }

    public static void deleteSuccesfull(String text) {
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setTitle("Successfull..");
        msg.setHeaderText(null);
        msg.setContentText(text + " Deleted Successfully.. ");
        msg.showAndWait();

    }

    public static void deleteFailed(String text) {
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error Occured!..");
        msg.setHeaderText(null);
        msg.setContentText(text);
        msg.showAndWait();
    }

    public static void deleteFailed(Exception ex, String text) {
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error Occured!..");
        msg.setHeaderText(null);
        msg.setContentText(text + " not Deleted, Try Again!..SQL Exception found in :" + ex);
        msg.showAndWait();
    }

    public static Optional<ButtonType> deleteConfirmation(String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Your Request to Delete");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to delete selected " + text + "??...");
        Optional<ButtonType> action = alert.showAndWait();
        return action;
    }

    public static void selectRowToDelete(String text) {
        Alert successMsg = new Alert(Alert.AlertType.INFORMATION);
        successMsg.setTitle("Please Select..");
        successMsg.setHeaderText(null);
        successMsg.setContentText("Please Select a " + text + " record to Delete..");
        successMsg.showAndWait();
    }

    public static void generalError(String text) {
        String classMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error Occured!..");
        msg.setHeaderText(null);
        msg.setContentText("Error Occured While Completing " + text + " request, Try Again!..");
        msg.showAndWait();
    }

    public static void noRecordFound(String text) {
        String classMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        Alert msg = new Alert(Alert.AlertType.WARNING);
        msg.setTitle("No Records Found");
        msg.setHeaderText(null);
        msg.setContentText("No " + text + " records found for Your Request");
        msg.showAndWait();
    }

    public static void actionEventError(Exception ex) {
        String classMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error Occured!..");
        msg.setHeaderText(null);
        msg.setContentText(classMethod + " Action Event Error Occured " + ex);
        msg.showAndWait();
    }

    public static Optional<ButtonType> logoutConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Your Logout Request");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to logout now??");
        Optional<ButtonType> action = alert.showAndWait();
        return action;
    }

    public static void noAPIRecordFound(String text) {
        String classMethod = new Object() {
        }.getClass().getEnclosingMethod().getName();
        Alert msg = new Alert(Alert.AlertType.WARNING);
        msg.setTitle("No Records Found");
        msg.setHeaderText(null);
        msg.setContentText("No " + text + " records found for Your Request\nMake sure you have a active Internet Connection!");
        msg.showAndWait();
    }
}
