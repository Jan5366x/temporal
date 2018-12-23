package de.novacyb.temporal.shared.io;

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Smart Properties
 * Created 22.12.2018.
 */
public class SmartProperties {
    private final Properties properties = new Properties();

    /**
     * Smart Properties Constructor
     * @param resource the resource path
     */
    public SmartProperties(final String resource) {
        try(final var input = new InputStreamReader(getClass().getModule()
                .getResourceAsStream("/app.properties"))) {

            properties.load(input);

        } catch (Exception e) {
            System.err.println("Error while loading app properties!");
            e.printStackTrace();
        }
    }

    /**
     * get a string property for a given key
     * @param key   the property key
     * @return the property or <i>null</i>
     */
    public String getString(final String key) {
        return getString(key,null);
    }

    /**
     * get a string property for a given key
     * @param key           the property key
     * @param defaultValue  the default value
     * @return the property or the default value
     */
    public String getString(final String key, final String defaultValue) {
        return properties.getProperty(key,defaultValue);
    }

    /**
     * get a integer property for a given key
     * @param key   the property key
     * @return the property or <i>null</i>
     */
    public int getInteger(final String key) {
        return getInteger(key,0);
    }

    /**
     * get a integer property for a given key
     * @param key           the property key
     * @param defaultValue  the default value
     * @return the property or the default value
     */
    public int getInteger(final String key, final int defaultValue) {
        try {
            return Integer.parseInt(getString(key));
        } catch (Exception e) {
            handleParseError(e, key);
            return defaultValue;
        }
    }

    /**
     * get a long property for a given key
     * @param key   the property key
     * @return the property or <i>null</i>
     */
    public long getLong(final String key) {
        return getLong(key,0L);
    }

    /**
     * get a long property for a given key
     * @param key           the property key
     * @param defaultValue  the default value
     * @return the property or the default value
     */
    public long getLong(final String key, final long defaultValue) {
        try {
            return Long.parseLong(getString(key));
        } catch (Exception e) {
            handleParseError(e, key);
            return defaultValue;
        }
    }

    /**
     * get a float property for a given key
     * @param key   the property key
     * @return the property or <i>null</i>
     */
    public float getFloat(final String key) {
        return getFloat(key,0F);
    }

    /**
     * get a float property for a given key
     * @param key           the property key
     * @param defaultValue  the default value
     * @return the property or the default value
     */
    public float getFloat(final String key, final float defaultValue) {
        try {
            return Float.parseFloat(getString(key));
        } catch (Exception e) {
            handleParseError(e, key);
            return defaultValue;
        }
    }

    /**
     * get a double property for a given key
     * @param key   the property key
     * @return the property or <i>null</i>
     */
    public double getDouble(final String key) {
        return getDouble(key,0D);
    }

    /**
     * get a double property for a given key
     * @param key           the property key
     * @param defaultValue  the default value
     * @return the property or the default value
     */
    public double getDouble(final String key, final double defaultValue) {
        try {
            return Double.parseDouble(getString(key));
        } catch (Exception e) {
            handleParseError(e, key);
            return defaultValue;
        }
    }

    /**
     * handle parser exceptions
     * @param e     the exception
     * @param key   the requested key
     */
    private void handleParseError(final Exception e, final String key) {
        System.err.println("Can't parse property for key \"" + key +"\"!");
        e.printStackTrace();
    }

}
