package com.hochan.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.hochan.core.delegates.HoChanDelegate;
import com.hochan.core.util.timer.BaseTimerTask;
import com.hochan.core.util.timer.ITimerListener;
import com.hochan.ec.R;
import com.hochan.ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * .
 * <p>
 * Created by hochan on 2018/5/31.
 */

public class LauncherDelegate extends HoChanDelegate implements ITimerListener {

	@BindView(R2.id.tv_launcher_timer)
	AppCompatTextView mTvTimer = null;

	private Timer mTimer;
	private int mCount = 5;

	@OnClick(R2.id.tv_launcher_timer)
	void onClickTimerView() {

	}

	private void initTimer() {
		mTimer = new Timer();
		final BaseTimerTask task = new BaseTimerTask(this);
		mTimer.schedule(task, 0, 1000);
	}

	@Override
	public Object getLayout() {
		return R.layout.delegate_launcher;
	}

	@Override
	public void onViewBound(@Nullable Bundle savedInstanceState, View rootView) {
		initTimer();
	}

	@Override
	public void onTimer() {
		getProxyActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (mTvTimer != null) {
					mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
					mCount--;
					if (mCount < 0) {
						if (mTimer != null) {
							mTimer.cancel();
							mTimer = null;
						}
					}
				}
			}
		});
	}
}
