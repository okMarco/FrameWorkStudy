package com.hochan.core.net.download;

import android.text.TextUtils;

import com.hochan.core.net.RestCreator;
import com.hochan.core.net.callback.IError;
import com.hochan.core.net.callback.IFailure;
import com.hochan.core.net.callback.IRequest;
import com.hochan.core.net.callback.ISuccess;
import com.hochan.core.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * .
 * <p>
 * Created by hochan on 2018/5/22.
 */

public class DownloadHandler {

	private final String URL;
	private static final Map<String, Object> PARAMS = RestCreator.getParams();
	private final IRequest REQUEST;
	private final String DOWNLOAD_DIR;
	private final String EXTENSION;
	private final String NAME;
	private final ISuccess SUCCESS;
	private final IFailure FAILURE;
	private final IError ERROR;

	public DownloadHandler(String URL,
						   IRequest REQUEST,
						   String DOWNLOAD_DIR,
						   String EXTENSION,
						   String NAME,
						   ISuccess SUCCESS,
						   IFailure FAILURE,
						   IError ERROR) {
		this.URL = URL;
		this.REQUEST = REQUEST;
		this.DOWNLOAD_DIR = DOWNLOAD_DIR;
		this.EXTENSION = EXTENSION;
		this.NAME = NAME;
		this.SUCCESS = SUCCESS;
		this.FAILURE = FAILURE;
		this.ERROR = ERROR;
	}

	public final void handleDownload() {
		if (REQUEST != null) {
			REQUEST.onRequestStart();
		}
		RestCreator.getRestService()
				.download(URL, PARAMS)
				.enqueue(new Callback<ResponseBody>() {
					@Override
					public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
						if (response.isSuccessful()) {
							writeFileToDisk(response);
						} else {
							if (ERROR != null) {
								ERROR.onError(response.code(), response.message());
							}
						}
					}

					@Override
					public void onFailure(Call<ResponseBody> call, Throwable t) {
						if (FAILURE != null) {
							FAILURE.onFailure();
							RestCreator.getParams().clear();
						}
					}
				});
	}

	private void writeFileToDisk(final Response<ResponseBody> response) {
		Observable.create(new ObservableOnSubscribe<File>() {
			@Override
			public void subscribe(ObservableEmitter<File> e) throws Exception {
				final ResponseBody responseBody = response.body();
				if (responseBody != null) {
					final InputStream is = responseBody.byteStream();
					String downloadDir = DOWNLOAD_DIR;
					if (TextUtils.isEmpty(downloadDir)) {
						downloadDir = "down_loads";
					}
					if (NAME == null) {
						e.onNext(FileUtil.writeToDisk(is, downloadDir, EXTENSION.toUpperCase()));
					} else {
						e.onNext(FileUtil.writeToDisk(is, downloadDir, NAME));
					}
				}
			}
		}).subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<File>() {
					@Override
					public void accept(File file) throws Exception {
						if (SUCCESS != null) {
							SUCCESS.onSuccess(file.getPath());
						}
						if (REQUEST != null) {
							REQUEST.onRequestEnd();
						}
					}
				}, new Consumer<Throwable>() {
					@Override
					public void accept(Throwable throwable) throws Exception {
						if (FAILURE != null) {
							FAILURE.onFailure();
						}
					}
				});
	}
}
