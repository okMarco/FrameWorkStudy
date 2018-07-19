package com.hochan.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * .
 * <p>
 * Created by hochan on 2018/5/16.
 */

public class FontEcModule implements IconFontDescriptor {

	@Override
	public String ttfFileName() {
		return "iconfont.ttf";
	}

	@Override
	public Icon[] characters() {
		return EcIcons.values();
	}
}
