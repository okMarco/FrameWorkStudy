package com.hochan.core.net;

import android.content.Context;

import com.hochan.core.net.callback.IError;
import com.hochan.core.net.callback.IFailure;
import com.hochan.core.net.callback.IRequest;
import com.hochan.core.net.callback.ISuccess;
import com.hochan.core.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * .
 * <p>
 * Created by hochan on 2018/5/17.
 */

public class RestClientBuilder {

	private String mUrl = null;
	private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
	private IRequest mIRequest = null;
	private ISuccess mISuccess = null;
	private IFailure mIFailure = null;
	private IError mIError = null;
	private RequestBody mBody = null;
	private Context mContext = null;
	private LoaderStyle mLoaderStyle = null;
	private File mFile = null;
	private String mDownloadDir;
	private String mExtension;
	private String mName;

	RestClientBuilder() {
	}

	public final RestClientBuilder url(String url) {
		this.mUrl = url;
		return this;
	}

	public final RestClientBuilder params(WeakHashMap<String, Object> params) {
		PARAMS.putAll(params);
		return this;
	}

	public final RestClientBuilder params(String key, Object value) {
		PARAMS.put(key, value);
		return this;
	}

	public final RestClientBuilder file(File file) {
		this.mFile = file;
		return this;
	}

	public final RestClientBuilder file(String path) {
		this.mFile = new File(path);
		return this;
	}

	public final RestClientBuilder dir(String downloadDir) {
		this.mDownloadDir = downloadDir;
		return this;
	}

	public final RestClientBuilder extension(String extension) {
		this.mExtension = extension;
		return this;
	}

	public final RestClientBuilder name(String name) {
		this.mName = name;
		return this;
	}

	public final RestClientBuilder raw(String raw) {
		this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
		return this;
	}

	public final RestClientBuilder onRequest(IRequest iRequest) {
		this.mIRequest = iRequest;
		return this;
	}

	public final RestClientBuilder success(ISuccess iSuccess) {
		this.mISuccess = iSuccess;
		return this;
	}

	public final RestClientBuilder failure(IFailure iFailure) {
		this.mIFailure = iFailure;
		return this;
	}

	public final RestClientBuilder error(IError iError) {
		this.mIError = iError;
		return this;
	}

	public final RestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
		this.mContext = context;
		this.mLoaderStyle = loaderStyle;
		return this;
	}

	public final RestClientBuilder loader(Context context) {
		this.mContext = context;
		this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
		return this;
	}

	public final RestClient build() {
		return new RestClient(mUrl, PARAMS, mIRequest, mDownloadDir, mExtension, mName, mISuccess, mIFailure, mIError, mBody, mFile, mContext, mLoaderStyle);
	}
}
