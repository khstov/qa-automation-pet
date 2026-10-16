package config;

public final class TestConfig {

    public static final String FAKE_STORE_BASE_URL = valueOrDefault(
            "FAKE_STORE_BASE_URL",
            "https://fakestoreapi.com"
    );
    public static final String DEMOQA_BASE_URL = valueOrDefault(
            "DEMOQA_BASE_URL",
            "https://demoqa.com"
    );

    private TestConfig() {
    }

    private static String valueOrDefault(String variableName, String defaultValue) {
        String value = System.getenv(variableName);
        return value == null || value.isBlank() ? defaultValue : value;
    }
}
