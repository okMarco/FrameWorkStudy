package com.hochan.frameworkstudy;

import com.hochan.core.activities.ProxyActivity;
import com.hochan.core.delegates.HoChanDelegate;
import com.hochan.ec.launcher.LauncherDelegate;

public class ExampleActivity extends ProxyActivity {

	@Override
	public HoChanDelegate getRootDelegate() {
		return new LauncherDelegate();
	}
}
