package com.shownest.android.model;

import com.shownest.android.activity.Activity_login;
import com.shownest.android.activity.Activity_webview;
import com.shownest.android.utils.CommonUtil;

import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class WebInterface
{
	private Activity_webview _activity;

	public WebInterface(Activity_webview _activity)
	{
		this._activity = _activity;
	}

	/**
	 * 设置标题
	 * <p>
	 * 对于有原生头部的网页窗体，可以通过此方法临时改变标题栏的文字
	 * 
	 * @param _name
	 */
	@android.webkit.JavascriptInterface
	public void set_title(String _name)
	{
		_activity.setTitle(_name == null ? "" : _name);
	}

	/**
	 * 打开一个新网页（取代原有页面内部的跳转）
	 * 
	 * @param _url
	 *            http://app.shownest.com/shuttering/getDesiShutterList?ukey=xxxxxxxxxx
	 * @param _have_title
	 *            <b>true</b>-新页面包含原生头部，<b>false</b>-新页面不包含原生头部
	 * @param _name
	 *            新页面的标题，无标题可传<b>null</b>或""
	 */
	@android.webkit.JavascriptInterface
	public void open(String _url, boolean _have_title, String _name)
	{
		Intent _open = new Intent(_activity, Activity_webview.class);
		_open.putExtra("url", _url);
		_open.putExtra("have_title", _have_title);
		_open.putExtra("title", _name == null ? "" : _name);
		_activity.startActivityForResult(_open, Activity_webview.RESULT_WEB);
	}

	/**
	 * 关闭当前页面，通常是在第一个页面需要返回的时候调用
	 * 
	 * @param _refresh
	 *            <b>true</b>-关闭后刷新前一个页面，<b>false</b>-关闭后不刷新前一个页面
	 */
	@android.webkit.JavascriptInterface
	public void close(boolean _refresh)
	{
		_activity.close(_refresh);
	}

	/**
	 * 拨打电话
	 * 
	 * @param _number
	 *            需要拨打的号码
	 */
	@android.webkit.JavascriptInterface
	public void tel(String _number)
	{
		if (CommonUtil.isPhone(_number))
		{
			Intent _tel = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + _number));
			_activity.startActivity(_tel);
		}
		else
		{
			Toast.makeText(_activity, "电话号码不正确", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 打开IM聊天窗口
	 * 
	 * @param _userID
	 *            要联系的用户ID
	 */
	@android.webkit.JavascriptInterface
	public void im(String _userID)
	{

	}

	@Deprecated
	@android.webkit.JavascriptInterface
	public void home()
	{
		_activity.finish();
	}

	/**
	 * 打开登陆界面
	 */
	@android.webkit.JavascriptInterface
	public void login()
	{
		Intent _login = new Intent(_activity, Activity_login.class);
		_activity.startActivity(_login);
	}
}
