package com.hochan.core.delegates;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hochan.core.activities.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * .
 * <p>
 * Created by hochan on 2018/5/16.
 */

public abstract class BaseDelegate extends SwipeBackFragment {

	private Unbinder mUnbinder = null;

	public abstract Object getLayout();

	public abstract void onViewBound(@Nullable Bundle savedInstanceState, View rootView);

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		final View rootView;
		if (getLayout() instanceof Integer) {
			rootView = inflater.inflate((Integer) getLayout(), container, false);
		} else if (getLayout() instanceof View) {
			rootView = (View) getLayout();
		} else {
			throw new RuntimeException("setLayout() type must be int or View");
		}
		if (rootView != null) {
			mUnbinder = ButterKnife.bind(this, rootView);
			onViewBound(savedInstanceState, rootView);
		}

		return rootView;
	}

	public final ProxyActivity getProxyActivity() {
		return (ProxyActivity) _mActivity;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (mUnbinder != null) {
			mUnbinder.unbind();
		}
	}
}
