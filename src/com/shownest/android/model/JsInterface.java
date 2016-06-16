package com.shownest.android.model;

import com.shownest.android.activity.Activity_webview;

public class JsInterface
{
	private Activity_webview _activity;
	
	public JsInterface(Activity_webview _activity)
	{
		this._activity = _activity;
	}
	
	@android.webkit.JavascriptInterface
	public void set_title(String _name)
	{
		_activity.setTitle(_name);
	}

	@android.webkit.JavascriptInterface
	public void close()
	{
		_activity.finish();
	}
}
