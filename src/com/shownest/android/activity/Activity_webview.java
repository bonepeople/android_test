package com.shownest.android.activity;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.model.WebInterface;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * 网页Activity
 * <p>
 * 所有需要调用网页的页面均由这个activity实现
 * 
 * @author bonepeople
 */
public class Activity_webview extends DEBUG_Activity
{
	public static final int RESULT_WEB = 199;
	private FrameLayout _body;
	private WebView _webview;
	private WebViewClient _client;
	private WebChromeClient _chrome;
	private Intent _intent;
	private boolean _have_title;
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
		_have_title = _intent.getBooleanExtra("have_title", true);
		if (!_have_title)
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
				if (url.startsWith("tel:"))
				{
					Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
					startActivity(intent);
				}
				else
				{
					view.loadUrl(url);
				}
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
		_chrome = new WebChromeClient()
		{
			@Override
			public boolean onJsAlert(WebView view, String url, String message, JsResult result)
			{
				final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

				builder.setMessage(message).setPositiveButton("确定", null);

				// 不需要绑定按键事件
				// 屏蔽keycode等于84之类的按键
				builder.setOnKeyListener(new OnKeyListener()
				{
					public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
					{
						System.out.println("keyCode==" + keyCode + "event=" + event);
						return true;
					}
				});
				// 禁止响应按back键的事件
				builder.setCancelable(false);
				AlertDialog dialog = builder.create();
				dialog.show();
				result.confirm();// 因为没有绑定事件，需要强行confirm,否则页面会变黑显示不了内容。
				return true;
			}
		};
		_webview.setWebViewClient(_client);
		_webview.setWebChromeClient(_chrome);
		_webview.addJavascriptInterface(new WebInterface(this), "JsInterface");
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == -1)
		{
			switch (requestCode)
			{
			case RESULT_WEB:
				boolean _refresh = data.getBooleanExtra("refresh", false);
				if (_refresh && _webview != null)
					_webview.reload();
				break;
			}
		}
		else
			System.out.println("resultCode=" + resultCode);
	}

	public void close(boolean _refresh)
	{
		Intent _intent = new Intent();
		_intent.putExtra("refresh", _refresh);
		setResult(RESULT_OK, _intent);
		if (_webview != null)
			_webview.destroy();
		finish();
	}

	/**
	 * 将返回事件传递给WebView
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (_webview != null && keyCode == KeyEvent.KEYCODE_BACK)
		{
			System.out.println("webview can goback = " + _webview.canGoBack());
			if (_webview.canGoBack())
			{
				_webview.goBack();
				return true;
			}
			else
				_webview.destroy();

		}
		return super.onKeyDown(keyCode, event);
	}
}
