package configuration;

import org.jspecify.annotations.NonNull;
import org.junit.platform.launcher.LauncherSession;
import org.junit.platform.launcher.LauncherSessionListener;
import utils.allure.UtilsAllure;

public class GlobalTestExecutionListener implements LauncherSessionListener {

    @Override
    public void launcherSessionOpened(@NonNull LauncherSession session) {
        if (Config.getAllureReport()) {
            UtilsAllure.cleanAllureResultsDirectory();
        }
    }
}
