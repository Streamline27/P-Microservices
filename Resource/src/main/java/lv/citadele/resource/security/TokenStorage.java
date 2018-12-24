package lv.citadele.resource.security;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

public class TokenStorage {

    public static final String KEY_TOKEN = "KEY_TOKEN";
    public static final String KEY_USERNAME = "KEY_USERNAME";

    private static final ThreadLocal<HashMap<String, Object>> CONTEXT = new ThreadLocal<>();

    public static <T> T get(String key) {
        Assert.notNull(CONTEXT.get(), "Context is not initialized");
        return (T) CONTEXT.get().get(key);
    }

    static void put(String key, Object value) {
        if (CONTEXT.get() == null) {
            CONTEXT.set(new HashMap<>());
        }
        CONTEXT.get().put(key, value);
    }

    static void put(Map<String, Object> data) {
        if (CONTEXT.get() == null) {
            CONTEXT.set(new HashMap<>());
        }
        CONTEXT.get().putAll(data);
    }
}
