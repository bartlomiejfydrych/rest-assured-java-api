package configuration;

public class BaseUrlBuilder {

    public static String buildBaseUrl() {
        return String.format("%s://%s.%s.%s/%s",
                Config.getBaseUrlProtocol(),
                Config.getBaseUrlSubdomain(),
                Config.getBaseUrlDomain(),
                Config.getBaseUrlTLD(),
                Config.getBaseUrlNumber()
        );
    }
}
