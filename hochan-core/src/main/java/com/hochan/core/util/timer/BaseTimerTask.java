package com.hochan.core.util.timer;

import java.util.TimerTask;

/**
 * .
 * <p>
 * Created by hochan on 2018/5/31.
 */

public class BaseTimerTask extends TimerTask {

	private ITimerListener mITimerListener = null;

	public BaseTimerTask(ITimerListener timerListener) {
		mITimerListener = timerListener;
	}

	@Override
	public void run() {
		if (mITimerListener != null) {
			mITimerListener.onTimer();
		}
	}
}
