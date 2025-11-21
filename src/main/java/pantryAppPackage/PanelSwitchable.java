package pantryAppPackage;

import java.io.FileNotFoundException;

public interface PanelSwitchable {

    void showDashboard(User user) throws FileNotFoundException;

    void showLogin();

}
