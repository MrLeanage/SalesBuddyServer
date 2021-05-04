package services.navigation;

import javafx.scene.layout.AnchorPane;
import view.appHome.DesktopStageController;
import view.serverManagement.ServerSettingDetailController;

public interface NavigationInterface {
    void logoutAccount(AnchorPane baseAnchorPane);

    void loadHomeBaseStage(AnchorPane baseAnchorPane);

    void loadStartupHome(AnchorPane detailAnchorPane);

    void loadBookHome(AnchorPane detailAnchorPane);

    void loadRequestHome(AnchorPane detailAnchorPane);

    void loadFeedbackHome(AnchorPane detailAnchorPane);

    void loadShopInfoHome(AnchorPane detailAnchorPane);

    void loadUserSettings(AnchorPane detailAnchorPane);

    void loadServerSetting(AnchorPane detailAnchorPane);

    ServerSettingDetailController getServerController();

    DesktopStageController getDesktopController();

}
