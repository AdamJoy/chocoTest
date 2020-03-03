package com.chrisaj.chocotest.tool;

import android.content.SharedPreferences;

import java.util.Set;

public abstract class BaseSP {
    //	public static SharedPreferences sp = null;
    abstract SharedPreferences getSp();

    public void clear() {
        getSp().edit().clear().apply();
    }

    public String getString(String keyName, String defValue) {
        return getSp().getString(keyName, defValue);
    }

    public boolean setString(String keyName, String value) {
        return getSp().edit().putString(keyName, value).commit();
    }

    public boolean getBoolean(String keyName, boolean defValue) {
        return getSp().getBoolean(keyName, defValue);
    }

    public boolean setBoolean(String keyName, boolean value) {
        return getSp().edit().putBoolean(keyName, value).commit();
    }

    public int getInt(String keyName, int defValue) {
        return getSp().getInt(keyName, defValue);
    }

    public boolean setInt(String keyName, int value) {
        return getSp().edit().putInt(keyName, value).commit();
    }

    public long getLong(String keyName, long defValue) {
        return getSp().getLong(keyName, defValue);
    }

    public boolean setLong(String keyName, long value) {
        return getSp().edit().putLong(keyName, value).commit();
    }

    public float getFloat(String keyName, float defValue) {
        return getSp().getFloat(keyName, defValue);
    }

    public boolean setLong(String keyName, float value) {
        return getSp().edit().putFloat(keyName, value).commit();
    }

    public Set<String> getSet(String keyName, Set<String> defValue) {
        return getSp().getStringSet(keyName, defValue);
    }

    public boolean setSet(String keyName, Set<String> value) {
        return getSp().edit().putStringSet(keyName, value).commit();
    }

    public boolean remove(String key) {
        return getSp().edit().remove(key).commit();
    }

    public boolean clearAll() {
        return getSp().edit().clear().commit();
    }
}
