package com.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public final class TestConfig {

    private static final Properties PROPERTIES = load();

    private TestConfig() {
    }

    private static Properties load() {
        Properties properties = new Properties();
        try (InputStream in = TestConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in == null) {
                throw new IllegalStateException("config.properties not found on the classpath");
            }
            properties.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Could not read config.properties", e);
        }
        return properties;
    }

    // A -Dkey=value system property overrides the file value.
    public static String get(String key) {
        String value = System.getProperty(key, PROPERTIES.getProperty(key));
        if (value == null) {
            throw new IllegalStateException("Missing config property: " + key);
        }
        return value.trim();
    }

    public static List<String> getList(String key) {
        return Arrays.stream(get(key).split(",")).map(String::trim).toList();
    }

    public static String baseUrl() {
        return get("base.url");
    }

    public static String username() {
        return get("login.username");
    }

    public static String password() {
        return get("login.password");
    }
}
