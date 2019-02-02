package de.novacyb.temporal.shared.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Smart Properties<br>
 * Created 22.12.2018.
 */
public class SmartProperties {
    private final Properties properties = new Properties();

    /**
     * Smart Properties Constructor
     * @param resource the resource path in current module
     */
    public SmartProperties(final String resource) {
        try(final var input = getClass().getModule().getResourceAsStream("/app.properties")) {
            load(input);
        } catch (Exception e) {
            System.err.println("Error while loading app properties!");
            e.printStackTrace();
        }
    }

    /**
     * Smart Properties Constructor
     * @param inputStream the input steam
     */
    public SmartProperties(final InputStream inputStream) throws IOException {
        load(inputStream);
    }


    /**
     * load properties from a given input stream
     * @param inputStream   the input stream
     * @throws IOException will throw exception if IO access fails
     */
    private void load(final InputStream inputStream) throws IOException {
        try(final var input = new InputStreamReader(inputStream)) {
            properties.load(input);
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
    public int getInteger(final String key) throws NumberFormatException {
        return getInteger(key,0);
    }

    /**
     * get a integer property for a given key
     * @param key           the property key
     * @param defaultValue  the default value
     * @return the property or the default value
     */
    public int getInteger(final String key, final int defaultValue) throws NumberFormatException {
        try {
            return Integer.parseInt(getString(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            handleParseError(e, key);
            throw e;
        }
    }

    /**
     * get a long property for a given key
     * @param key   the property key
     * @return the property or <i>null</i>
     */
    public long getLong(final String key) throws NumberFormatException {
        return getLong(key,0L);
    }

    /**
     * get a long property for a given key
     * @param key           the property key
     * @param defaultValue  the default value
     * @return the property or the default value
     */
    public long getLong(final String key, final long defaultValue) throws NumberFormatException {
        try {
            return Long.parseLong(getString(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            handleParseError(e, key);
            throw e;
        }
    }

    /**
     * get a float property for a given key<br>
     * <b>Note:</b> Expects a dot as decimal separator.
     * @param key   the property key
     * @return the property or <i>null</i>
     */
    public float getFloat(final String key) throws NumberFormatException {
        return getFloat(key,0F);
    }

    /**
     * get a float property for a given key<br>
     * <b>Note:</b> Expects a dot as decimal separator.
     * @param key           the property key
     * @param defaultValue  the default value
     * @return the property or the default value
     */
    public float getFloat(final String key, final float defaultValue) throws NumberFormatException {
        try {
            return Float.parseFloat(getString(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            handleParseError(e, key);
            throw e;
        }
    }

    /**
     * get a double property for a given key<br>
     * <b>Note:</b> Expects a dot as decimal separator.
     * @param key   the property key
     * @return the property or <i>null</i>
     */
    public double getDouble(final String key) throws NumberFormatException {
        return getDouble(key,0D);
    }

    /**
     * get a double property for a given key<br>
     * <b>Note:</b> Expects a dot as decimal separator.
     * @param key           the property key
     * @param defaultValue  the default value
     * @return the property or the default value
     */
    public double getDouble(final String key, final double defaultValue) throws NumberFormatException {
        try {
            return Double.parseDouble(getString(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            handleParseError(e, key);
            throw e;
        }
    }

    /**
     * handle parser exceptions
     * @param e     the exception
     * @param key   the requested key
     */
    private void handleParseError(final NumberFormatException e, final String key) throws NumberFormatException {
        System.err.println("Can't parse property for key \"" + key +"\"!");
    }
}
