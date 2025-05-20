package app.Util;

import java.util.HashMap;
import java.util.Map;

public class SharedTestData {
	private Map<String, Object> data = new HashMap<>();

    public void set(String key, Object value) {
        data.put(key, value);
    }

    public Object get(String key) {
        return data.get(key);
    }

    public String getAsString(String key) {
        return (String) data.get(key);
    }
}