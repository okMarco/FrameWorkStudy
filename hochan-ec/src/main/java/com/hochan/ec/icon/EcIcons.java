package com.hochan.ec.icon;

import android.graphics.drawable.Icon;

/**
 * .
 * <p>
 * Created by hochan on 2018/5/16.
 */

public enum EcIcons implements com.joanzapata.iconify.Icon {
	ICON_ACCESSORY('\ue6dd'),
	ICON_ACTIVITY('\ue6de');

	char character;

	EcIcons(char character) {
		this.character = character;
	}

	@Override
	public String key() {
		return name().replace('_', '-');
	}

	@Override
	public char character() {
		return character;
	}
}
