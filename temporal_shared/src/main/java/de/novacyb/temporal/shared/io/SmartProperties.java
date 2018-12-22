package de.novacyb.temporal.shared.io;

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Smart Property
 * Created 22.12.2018.
 */
public class SmartProperties {
    private final Properties properties = new Properties();


    public SmartProperties(final String resource) {
        try(final var input = new InputStreamReader(getClass().getModule()
                .getResourceAsStream("/app.properties"))) {

            properties.load(input);

        } catch (Exception e) {
            System.err.println("Error while loading app properties!");
            e.printStackTrace();
        }
    }

    public String getString(final String key) {
        return getString(key,null);
    }

    public String getString(final String key, final String defaultValue) {
        return properties.getProperty(key,defaultValue);
    }

    public int getInteger(final String key) {
        return getInteger(key,0);
    }

    public int getInteger(final String key, final int defaultValue) {
        try {
            return Integer.parseInt(getString(key));
        } catch (Exception e) {
            handleParseError(e, key);
            return defaultValue;
        }
    }

    public long getLong(final String key) {
        return getLong(key,0L);
    }

    public long getLong(final String key, final long defaultValue) {
        try {
            return Long.parseLong(getString(key));
        } catch (Exception e) {
            handleParseError(e, key);
            return defaultValue;
        }
    }

    public float getFloat(final String key) {
        return getFloat(key,0F);
    }

    public float getFloat(final String key, final float defaultValue) {
        try {
            return Float.parseFloat(getString(key));
        } catch (Exception e) {
            handleParseError(e, key);
            return defaultValue;
        }
    }

    public double getDouble(final String key) {
        return getDouble(key,0D);
    }

    public double getDouble(final String key, final double defaultValue) {
        try {
            return Double.parseDouble(getString(key));
        } catch (Exception e) {
            handleParseError(e, key);
            return defaultValue;
        }
    }

    private void handleParseError(final Exception e, final String key) {
        System.err.println("Can't parse property for key \"" + key +"\"!");
        e.printStackTrace();
    }

}
