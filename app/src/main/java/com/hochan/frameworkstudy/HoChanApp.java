package com.hochan.frameworkstudy;

import android.support.multidex.MultiDexApplication;

import com.hochan.core.app.HoChan;
import com.hochan.core.net.interceptors.DebugInterceptor;
import com.hochan.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * .
 * <p>
 * Created by hochan on 2018/5/14.
 */

public class HoChanApp extends MultiDexApplication {

	@Override
	public void onCreate() {
		super.onCreate();
		HoChan.init(this)
				.withIcon(new FontEcModule())
				.withIcon(new FontAwesomeModule())
				.withApiHost("http://127.0.0.1/")
				.withInterceptor(new DebugInterceptor("index", R.raw.test))
				.configure();
	}
}
