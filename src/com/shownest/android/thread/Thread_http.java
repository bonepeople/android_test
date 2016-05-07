package com.shownest.android.thread;

import com.shownest.android.utils.HttpUtil;

import android.os.Handler;

public class Thread_http extends Thread
{
	private Handler _handler;
	private String _address, _message;
	private int _successful, _failed;

	public Thread_http(Handler _handler, String _address, String _massage, int _successful, int _failed)
	{
		this._handler = _handler;
		this._address = _address;
		this._message = _massage;
		this._successful = _successful;
		this._failed = _failed;
	}

	@Override
	public void run()
	{
		HttpUtil.send_post(_handler, _address, _message, _successful, _failed);
	}

}
