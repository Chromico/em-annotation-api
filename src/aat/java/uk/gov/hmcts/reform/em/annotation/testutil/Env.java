package uk.gov.hmcts.reform.em.annotation.testutil;

import org.apache.commons.lang3.Validate;

import java.util.Properties;

public class Env {

    static Properties defaults = new Properties();

    static {
        defaults.setProperty("TEST_URL", "http://localhost:8080");
        defaults.setProperty("S2S_TOKEN", "AAAAAAAAAAAAAAAA");
        defaults.setProperty("S2S_SERVICE_NAME", "em_gw");
        defaults.setProperty("S2S_URL", "http://localhost:4502"); // output.tf = S2S_URL, main.tf = S2S_BASE_URI
        defaults.setProperty("IDAM_API_USER_ROLE", "caseworker");
        defaults.setProperty("IDAM_API_USER", "test@test.com");
        defaults.setProperty("IDAM_API_URL", "http://localhost:4501");  // output.tf = IDAM_API_URL, main.tf = IDAM_API_BASE_URI
    }

    public static String getTestUrl() {
        return require("TEST_URL");
    }

    public static String getIdamURL() {
        return require("IDAM_API_URL");
    }

    public static String getS2SURL() {
        return require("S2S_URL");
    }

    public static String getS2SToken() {
        return require("S2S_TOKEN");
    }

    public static String getS2SServiceName() {
        return require("S2S_SERVICE_NAME");
    }

    public static String require(String name) {
        return Validate.notNull(System.getenv(name) == null ? defaults.getProperty(name) : System.getenv(name), "Environment variable `%s` is required", name);
    }
}
