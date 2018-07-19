package com.hochan.core.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.hochan.core.R;
import com.hochan.core.delegates.HoChanDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * .
 * <p>
 * Created by hochan on 2018/5/16.
 */

public abstract class ProxyActivity extends SupportActivity {

	public abstract HoChanDelegate getRootDelegate();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initContainer(savedInstanceState);
	}

	private void initContainer(@Nullable Bundle savedInstanceState) {
		@SuppressLint("RestrictedApi")        final ContentFrameLayout container = new ContentFrameLayout(this);
		container.setId(R.id.delegate_container);
		setContentView(container);
		if (savedInstanceState == null) {
			loadRootFragment(R.id.delegate_container, getRootDelegate());
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.gc();
		System.runFinalization();
	}
}
