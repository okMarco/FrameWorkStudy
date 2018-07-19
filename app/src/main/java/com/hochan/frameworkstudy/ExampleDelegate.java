package com.hochan.frameworkstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.hochan.core.delegates.HoChanDelegate;
import com.hochan.core.net.RestClient;
import com.hochan.core.net.callback.IError;
import com.hochan.core.net.callback.IFailure;
import com.hochan.core.net.callback.ISuccess;

/**
 * .
 * <p>
 * Created by hochan on 2018/5/16.
 */

public class ExampleDelegate extends HoChanDelegate {

	@Override
	public Object getLayout() {
		return R.layout.delegate_example;
	}

	@Override
	public void onViewBound(@Nullable Bundle savedInstanceState, View rootView) {
		testRestClient();
	}

	private void testRestClient() {
		RestClient.builder()
				.url("http://127.0.0.1/index")
				.loader(getContext())
				.success(new ISuccess() {
					@Override
					public void onSuccess(String response) {
						Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
					}
				})
				.failure(new IFailure() {
					@Override
					public void onFailure() {

					}
				})
				.error(new IError() {
					@Override
					public void onError(int code, String msg) {

					}
				})
				.build()
				.get();
	}
}
