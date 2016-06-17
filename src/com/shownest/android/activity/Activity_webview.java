package com.shownest.android.activity;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.model.JsInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class Activity_webview extends DEBUG_Activity
{
	private FrameLayout _body;
	private WebView _webview;
	private WebViewClient _client;
	private WebChromeClient _chrome;
	private Intent _intent;
	private boolean _had_title;
	private String _url;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
		_relativelayout_wait = (RelativeLayout) findViewById(R.id.relativelayout_wait);
		_body = (FrameLayout) findViewById(R.id.framelayout_content);
		_intent = getIntent();

		_url = _intent.getStringExtra("url");
		_had_title = _intent.getBooleanExtra("had_title", true);
		if (!_had_title)
			hideTitle(this);
		else
			setTitle(_intent.getStringExtra("title"));

		show_wait();
		init();
	}

	private void init()
	{
		_webview = new WebView(this);
		WebSettings settings = _webview.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

		_client = new WebViewClient()
		{
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url)
			{
				view.loadUrl(url);
				return true;
			}

			@Override
			public void onPageFinished(WebView view, String url)
			{
				// WebView 在Android4.4的手机上onPageFinished()回调会多调用一次
				close_wait();
				super.onPageFinished(view, url);
			}
		};
		_webview.setWebViewClient(_client);
		_webview.addJavascriptInterface(new JsInterface(this), "JsInterface");
		_webview.setOnLongClickListener(new OnLongClickListener()
		{
			@Override
			public boolean onLongClick(View v)
			{
				return true;
			}
		});

		_body.addView(_webview);
		load();
	}

	private void load()
	{
		System.out.println("connecting:" + _url);
		_webview.loadUrl(_url);
	}
}
