package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.OnChangeListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Fragment_webview extends DEBUG_Fragment implements View.OnClickListener
{
	private String _tag;
	private OnChangeListener _listener;
	private WebView _webview;
	private WebViewClient _client;
	private String _url;

	public Fragment_webview(String _url, String _tag, OnChangeListener _listener)
	{
		this._url = _url;
		this._tag = _tag;
		this._listener = _listener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_webview, container, false);
		_webview = (WebView) _view.findViewById(R.id.webview);
		return _view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		System.out.println("requestCode=" + requestCode + "resultCode=" + resultCode);
		if (resultCode == -1)
		{
			if (requestCode == 2)
			{
				// _image_uri[_image_where[3]] = data.getData();
			}
			// _idcard.setData(_image_uri[_image_where[3]], _image_where[3]);
			// _image_where[_image_where[3]] = 1;
		}
	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == R.id.button_commit)
		{
		}
		else if (_id == R.id.imageview_widget_left)
		{
		}
		else if (_id == R.id.imageview_widget_right)
		{
		}
		else if (_id == R.id.imageview_widget_bottom)
		{
		}
	}

	@Override
	public void setContent()
	{
		System.out.println("connecting:" + _url);
		_webview.loadUrl(_url);
		WebSettings settings = _webview.getSettings();
		settings.setJavaScriptEnabled(true);

		_webview.setWebChromeClient(new WebChromeClient()
		{
			@Override
			public void onProgressChanged(WebView view, int newProgress)
			{
				if (newProgress == 100)
				{
					// 网页加载完成
					_listener.onChange(_tag, new String[] { "finish" });
				}
				else
				{
					// 加载中

				}
			}
		});
	}
}
