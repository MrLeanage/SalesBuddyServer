package util.utility;

import view.appHome.DesktopStageController;

public class MenuBarController {
    private static int menuNumber = 0;
    private static DesktopStageController desktopStageController = null;

    public static DesktopStageController getHomeController() {
        return desktopStageController;
    }

    public static void setHomeController(DesktopStageController desktopStageController) {
        MenuBarController.desktopStageController = desktopStageController;
    }

    public static int getMenuNumber() {
        return menuNumber;
    }

    public static void setMenuNumber(int menuNumber) {
        MenuBarController.menuNumber = menuNumber;
    }
}
