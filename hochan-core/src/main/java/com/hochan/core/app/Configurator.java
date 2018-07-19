package com.hochan.core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Interceptor;

/**
 * .
 * <p>
 * Created by hochan on 2018/5/14.
 */

public class Configurator {

	private static final HashMap<Object, Object> HOCHAN_CONFIGS = new HashMap<>();
	private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
	private static final List<Interceptor> INTERCEPTORS = new ArrayList<>();

	private Configurator() {
		HOCHAN_CONFIGS.put(ConfigType.CONFIG_READY, false);
	}

	static Configurator getInstance() {
		return Holder.INSTANCE;
	}

	final HashMap<Object, Object> getHochanConfigs() {
		return HOCHAN_CONFIGS;
	}

	private static class Holder {
		private static final Configurator INSTANCE = new Configurator();
	}

	public final void configure() {
		initIcons();
		HOCHAN_CONFIGS.put(ConfigType.CONFIG_READY, true);
	}

	public final Configurator withApiHost(String host) {
		HOCHAN_CONFIGS.put(ConfigType.API_HOST, host);
		return this;
	}

	private void initIcons() {
		if (ICONS.size() > 0) {
			final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
			for (int i = 1; i < ICONS.size(); i++) {
				initializer.with(ICONS.get(i));
			}
		}
	}

	public final Configurator withIcon(IconFontDescriptor descriptor) {
		ICONS.add(descriptor);
		return this;
	}

	public final Configurator withInterceptor(Interceptor interceptor) {
		INTERCEPTORS.add(interceptor);
		HOCHAN_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
		return this;
	}

	public final Configurator withInerceptors(List<Interceptor> interceptorList) {
		INTERCEPTORS.addAll(interceptorList);
		HOCHAN_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
		return this;
	}

	private void checkConfiguration() {
		final boolean isReady = (boolean) HOCHAN_CONFIGS.get(ConfigType.CONFIG_READY);
		if (!isReady) {
			throw new RuntimeException("Configuration is not ready, call configure");
		}
	}

	@SuppressWarnings({"SuspiciousMethodCalls", "unchecked"})
	final <T> T getConfiguration(Enum<ConfigType> key) {
		checkConfiguration();
		return (T) HOCHAN_CONFIGS.get(key);
	}
}
