package com.shownest.android.thread;

import com.shownest.android.utils.HttpUtil;

import android.os.Handler;

public class Thread_http extends Thread
{
	private Handler _handler;
	private String _address, _message;
	private int _successful, _failed;
	private String _method;

	public Thread_http(Handler _handler, String _address, String _massage, int _successful, int _failed, String _method)
	{
		this._handler = _handler;
		this._address = _address;
		this._message = _massage;
		this._successful = _successful;
		this._failed = _failed;
		this._method = _method;
	}

	@Override
	public void run()
	{
		HttpUtil.send_http(_handler, _address, _message, _successful, _failed, _method);
	}

}
