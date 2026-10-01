package portfolio.config;

import java.io.IOException;
import java.util.Properties;

public final class Settings {
    private static final Properties VALUES = new Properties();
    static {
        try (var input = Settings.class.getResourceAsStream("/config.properties")) {
            if (input == null) throw new IllegalStateException("Missing config.properties");
            VALUES.load(input);
        } catch (IOException error) { throw new ExceptionInInitializerError(error); }
    }
    private Settings() {}
    public static String get(String key) {
        return System.getProperty(key, System.getenv().getOrDefault("QA_" + key.toUpperCase().replace('.', '_'), VALUES.getProperty(key, "")));
    }
}
