package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_regist;
import com.shownest.android.thread.Thread_time;
import com.shownest.android.utils.HttpUtil;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class Activity_regist extends DEBUG_Activity
{
	public static final int REGIST_FAILED = 0;
	public static final int REGIST_SUCCESSFUL = 1;
	public static final int SEND_FAILED = 2;
	public static final int SEND_SUCCESSFUL = 3;
	public static final int CHECK_FAILED = 4;
	public static final int CHECK_SUCCESSFUL = 5;
	public static final int BUTTON_CHANGE = 6;
	private static Activity_regist _instance;
	private static Context _context;
	private static Fragment_regist _fragment_regist;
	private static Thread_time _timer = null;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			String _string_result = "";
			switch (msg.what)
			{
			case CHECK_FAILED:
			case SEND_FAILED:
			case REGIST_FAILED:
				Toast.makeText(_context, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case CHECK_SUCCESSFUL:
			case SEND_SUCCESSFUL:
			case REGIST_SUCCESSFUL:
				_string_result = (String) msg.obj;
				handle_string(_string_result);
				break;
			case BUTTON_CHANGE:
				_fragment_regist.mobilcode_change();
			}
			_fragment_regist.close_wait();
			System.out.println(_string_result);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		_instance = this;
		_context = this.getApplicationContext();
		setContentView(R.layout.activity_regist);

		_fragment_regist = (Fragment_regist) getFragmentManager().findFragmentById(R.id.fragment_regist);
		if (_timer != null)
			_timer.interrupt();
	}

	private static void handle_string(String str)
	{
		handle_msg(_instance, str);
		try
		{
			JSONObject _obj = new JSONObject(str);
			String _result = _obj.getString("msg");

			/*
			 * if (_result.equals("用户名已经存在")) Toast.makeText(_context, _result, Toast.LENGTH_SHORT).show(); if (_result.equals("用户名不存在")) { new
			 * Thread_send_code(_fragment_regist.get_regist_phone()).start(); } if (_result.equals("手机验证码发送成功")) Toast.makeText(_context, _result, Toast.LENGTH_SHORT).show();
			 */
			if (_result.equals("用户名不存在"))
			{
				HttpUtil.send_mobilecode(_handler, _fragment_regist.get_regist_phone(), SEND_SUCCESSFUL, SEND_FAILED);
			}
			else if (_result.equals("注册成功"))
			{
				Toast.makeText(_context, _result, Toast.LENGTH_SHORT).show();
				_instance.finish();
			}
			else if (_result.equals("手机验证码发送成功"))
			{
				_timer = new Thread_time(_handler, BUTTON_CHANGE, 61, 1);
				_timer.start();
				Toast.makeText(_context, _result, Toast.LENGTH_SHORT).show();
			}
			else
				Toast.makeText(_context, _result, Toast.LENGTH_SHORT).show();

		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}

	}

	@Override
	protected String get_class()
	{
		return this.getClass().toString();
	}
}
