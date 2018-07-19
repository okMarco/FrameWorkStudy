package com.hochan.core.net;

import com.hochan.core.app.ConfigType;
import com.hochan.core.app.HoChan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * .
 * <p>
 * Created by hochan on 2018/5/17.
 */

public class RestCreator {

	private static final class ParamsHolder {
		public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
	}

	public static WeakHashMap<String, Object> getParams() {
		return ParamsHolder.PARAMS;
	}

	public static RestService getRestService() {
		return RestServiceHolder.REST_SERVICE;
	}

	private static final class RetrofitHolder {
		private static final String BASE_URL = (String) HoChan.getConfigurations().get(ConfigType.API_HOST);
		private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.client(OkHttpHolder.OK_HTTP_CLIENT)
				.addConverterFactory(ScalarsConverterFactory.create())
				.build();
	}

	private static final class OkHttpHolder {
		private static final int TIME_OUT = 60;
		private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
		private static final ArrayList<Interceptor> INTERCEPTORS = (ArrayList<Interceptor>) HoChan.getConfigurations().get(ConfigType.INTERCEPTOR);

		private static OkHttpClient.Builder addInterceptor() {
			if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
				for (Interceptor interceptor : INTERCEPTORS) {
					BUILDER.addInterceptor(interceptor);
				}
			}
			return BUILDER;
		}

		private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
				.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
				.addInterceptor(new Interceptor() {
					@Override
					public Response intercept(Chain chain) throws IOException {
						return null;
					}
				})
				.build();
	}

	private static final class RestServiceHolder {
		private static final RestService REST_SERVICE =
				RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
	}
}
