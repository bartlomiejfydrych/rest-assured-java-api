package configuration;

import io.restassured.RestAssured;
import loggers.AllureRestAssuredEnhanced;
import org.jspecify.annotations.NonNull;
import org.junit.platform.launcher.LauncherSession;
import org.junit.platform.launcher.LauncherSessionListener;
import utils.allure.UtilsAllure;

public class GlobalTestExecutionListener implements LauncherSessionListener {

    // ==========================================================================================================
    // METHODS – MAIN
    // ==========================================================================================================

    @Override
    public void launcherSessionOpened(@NonNull LauncherSession session) {

        RestAssured.reset();

        if (Config.getAllureReport()) {
            UtilsAllure.cleanAllureResultsDirectory();
            RestAssured.filters(new AllureRestAssuredEnhanced());
        }
    }
}
