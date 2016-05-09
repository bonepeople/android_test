package com.shownest.android.thread;

import android.os.Handler;

public class Thread_time extends Thread
{
	private Handler _handler;
	private int _code, _max, _space;

	public Thread_time(Handler _handler, int _code, int _max, int _space)
	{
		this._handler = _handler;
		this._code = _code;
		this._max = _max;
		this._space = _space;
	}

	@Override
	public void run()
	{
		int _time = 0;
		try
		{
			while (_time < _max)
			{
				_handler.sendEmptyMessage(_code);
				sleep(_space * 1000);
				_time++;
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}

}
