package com.hochan.core.util.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hochan.core.app.HoChan;

/**
 * .
 * <p>
 * Created by hochan on 2018/5/31.
 */

public class HoChanPreference {

	private static final SharedPreferences PREFERENCES =
			PreferenceManager.getDefaultSharedPreferences(HoChan.getApplicationContext());
	private static final String APP_PREFERENCE_KEY = "profile";

	private static SharedPreferences getAppPreference() {
		return PREFERENCES;
	}

	public static void setAppProfile(String val) {
		getAppPreference()
				.edit()
				.putString(APP_PREFERENCE_KEY, val)
				.apply();
	}

	public static String getAppProfile() {
		return getAppPreference().getString(APP_PREFERENCE_KEY, null);
	}

	public static JSONObject getAppProfileJson() {
		final String profile = getAppProfile();
		return JSON.parseObject(profile);
	}

	public static void removeAppProfile() {
		getAppPreference()
				.edit()
				.remove(APP_PREFERENCE_KEY)
				.apply();
	}

	public static void clearAppPreference() {
		getAppPreference()
				.edit()
				.clear()
				.apply();
	}

	public static void setAppFlag(String key, boolean flag) {
		getAppPreference()
				.edit()
				.putBoolean(key, flag)
				.apply();
	}

	public static boolean getAppFlag(String key) {
		return getAppPreference()
				.getBoolean(key, false);
	}

	public static void addCustomAppProfile(String key, String val) {
		getAppPreference()
				.edit()
				.putString(key, val)
				.apply();
	}

	public static String getCustomAppProfile(String key) {
		return getAppPreference().getString(key, "");
	}
}
