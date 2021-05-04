package services.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import util.authentication.UserAuthentication;
import util.utility.AlertPopUp;
import view.appHome.DesktopStageController;
import view.serverManagement.ServerSettingDetailController;

import java.io.IOException;
import java.util.Optional;

public class MenuNavigation implements NavigationInterface {
    private static DesktopStageController desktopStageController = null;

    public static ServerSettingDetailController serverSettingDetailController = null;

    @Override
    public void logoutAccount(AnchorPane baseAnchorPane) {
        try {
            Optional<ButtonType> action = AlertPopUp.logoutConfirmation();
            if (action.get() == ButtonType.OK) {
                UserAuthentication.setAuthenticatedUser(null);
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/appHome/login.fxml"));
                baseAnchorPane.getChildren().setAll(pane);
            }

        } catch (IOException ex) {
            AlertPopUp.generalError(ex);
        }
    }

    @Override
    public void loadHomeBaseStage(AnchorPane baseAnchorPane) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/appHome/desktopStage.fxml"));
            AnchorPane pane = loader.load();
            baseAnchorPane.getChildren().setAll(pane);
            DesktopStageController controller = loader.<DesktopStageController>getController();
            desktopStageController = controller;
        } catch (IOException ex) {
            AlertPopUp.generalError(ex);
        }
    }

    @Override
    public void loadStartupHome(AnchorPane detailAnchorPane) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/appHome/homeDetail.fxml"));
            detailAnchorPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            ex.printStackTrace();
            AlertPopUp.generalError(ex);
        }
    }

    @Override
    public void loadBookHome(AnchorPane detailAnchorPane) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/storeManagement/bookDetail.fxml"));
            detailAnchorPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            AlertPopUp.generalError(ex);
        }
    }

    @Override
    public void loadRequestHome(AnchorPane detailAnchorPane) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/requestManagement/requestDetail.fxml"));
            detailAnchorPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            AlertPopUp.generalError(ex);
        }
    }

    @Override
    public void loadFeedbackHome(AnchorPane detailAnchorPane) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/feedbackManagement/feedbackDetail.fxml"));
            detailAnchorPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            AlertPopUp.generalError(ex);
        }
    }

    @Override
    public void loadShopInfoHome(AnchorPane detailAnchorPane) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/storeManagement/shopInfoDetail.fxml"));
            detailAnchorPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            AlertPopUp.generalError(ex);
        }
    }

    @Override
    public void loadUserSettings(AnchorPane detailAnchorPane) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/userManagement/userDetail.fxml"));
            detailAnchorPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            AlertPopUp.generalError(ex);
        }
    }

    @Override
    public void loadServerSetting(AnchorPane detailAnchorPane) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/serverManagement/serverSettingDetail.fxml"));
            AnchorPane pane = loader.load();
            detailAnchorPane.getChildren().setAll(pane);
            ServerSettingDetailController controller = loader.<ServerSettingDetailController>getController();
            serverSettingDetailController = controller;
        } catch (IOException ex) {
            AlertPopUp.generalError(ex);
        }
    }

    @Override
    public ServerSettingDetailController getServerController() {
        return serverSettingDetailController;
    }

    @Override
    public DesktopStageController getDesktopController() {
        return desktopStageController;
    }
}
