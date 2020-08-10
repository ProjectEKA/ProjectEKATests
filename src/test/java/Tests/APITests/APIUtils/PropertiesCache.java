package Tests.APITests.APIUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class PropertiesCache {
    private final Properties configProp = new Properties();

    private PropertiesCache() {
        //Private constructor to restrict new instances
        InputStream in;
        System.out.println(System.getenv("env") + "asdasdas");
        if ("ncg".equals(System.getenv("env"))) {
            in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        } else
            in = this.getClass().getClassLoader().getResourceAsStream("config-dev.properties");
        System.out.println("Read all properties from file");
        try {
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertiesCache getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String getProperty(String key) {
        return configProp.getProperty(key);
    }

    public Set<String> getAllPropertyNames() {
        return configProp.stringPropertyNames();
    }

    public boolean containsKey(String key) {
        return configProp.containsKey(key);
    }

    //Singleton pattern
    private static class LazyHolder {
        private static final PropertiesCache INSTANCE = new PropertiesCache();
    }
}
