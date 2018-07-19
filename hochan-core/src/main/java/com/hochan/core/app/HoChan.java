package com.hochan.core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * .
 * <p>
 * Created by hochan on 2018/5/14.
 */

public final class HoChan {

	public static Configurator init(Context context) {
		getConfigurations().put(ConfigType.APPLICATION_CONTEXT, context.getApplicationContext());
		return Configurator.getInstance();
	}

	public static HashMap<Object, Object> getConfigurations() {
		return Configurator.getInstance().getHochanConfigs();
	}

	public static Context getApplicationContext() {
		return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT);
	}
}
