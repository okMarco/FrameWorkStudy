package com.hochan.core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.hochan.core.app.HoChan;

/**
 * .
 * <p>
 * Created by hochan on 2018/5/17.
 */

public class DimenUtil {

	public static int getScreenWidth() {
		final Resources resources = HoChan.getApplicationContext().getResources();
		final DisplayMetrics dm = resources.getDisplayMetrics();
		return dm.widthPixels;
	}

	public static int getScreenHeight() {
		final Resources resources = HoChan.getApplicationContext().getResources();
		final DisplayMetrics dm = resources.getDisplayMetrics();
		return dm.heightPixels;
	}
}
