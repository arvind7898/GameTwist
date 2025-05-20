package app.Util;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class TestDataStore {
	
    private static final String FILE_PATH = "/Users/arvind/eclipse-workspace/UIAutomation/src/test/resources/testData.json";
    private static final ObjectMapper mapper = new ObjectMapper();
    
    // Save a key-value pair to the JSON file
    public static void save(String key, String value) throws IOException {
        Map<String, String> data = loadAll();
        data.put(key, value);
        mapper.writeValue(new File(FILE_PATH), data);
    }

    // Load a value by key
    public static String load(String key) throws IOException {
        Map<String, String> data = loadAll();
        return data.get(key);
    }

    // Load entire JSON as Map
    public static Map<String, String> loadAll() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            return new HashMap<>();
        }
        return mapper.readValue(file, new TypeReference<Map<String, String>>() {});
    }

    // Optional: Clear all data
    public static void clear() throws IOException {
        mapper.writeValue(new File(FILE_PATH), new HashMap<>());
    }

}
