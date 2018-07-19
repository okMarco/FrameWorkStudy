package com.hochan.core.net.callback;

import com.hochan.core.ui.HoChanLoader;
import com.hochan.core.ui.LoaderStyle;

import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * .
 * <p>
 * Created by hochan on 2018/5/17.
 */

public class RequestCallbacks implements Callback<String> {

	private final IRequest REQUEST;
	private final ISuccess SUCCESS;
	private final IFailure FAILURE;
	private final IError ERROR;
	private final LoaderStyle LOADER_STYLE;

	public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle style) {
		this.REQUEST = request;
		this.SUCCESS = success;
		this.FAILURE = failure;
		this.ERROR = error;
		this.LOADER_STYLE = style;
	}

	@Override
	public void onResponse(Call<String> call, Response<String> response) {
		if (response.isSuccessful()) {
			if (call.isExecuted()) {
				if (SUCCESS != null) {
					SUCCESS.onSuccess(response.body());
				}
			}
		} else {
			if (ERROR != null) {
				ERROR.onError(response.code(), response.message());
			}
		}

		if (LOADER_STYLE != null) {
			new android.os.Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					HoChanLoader.stopLoading();
				}
			}, 3000);
		}
	}

	@Override
	public void onFailure(Call<String> call, Throwable t) {
		if (FAILURE != null) {
			FAILURE.onFailure();
		}
		if (REQUEST != null) {
			REQUEST.onRequestEnd();
		}
	}
}
