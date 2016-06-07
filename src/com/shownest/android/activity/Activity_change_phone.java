package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_change_phone;
import com.shownest.android.thread.Thread_time;
import com.shownest.android.utils.JsonUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_change_phone extends DEBUG_Activity
{
	public static final int CHANGE_FAILED = 0;
	public static final int CHANGE_SUCCESSFUL = 1;
	public static final int SEND_FAILED = 2;
	public static final int SEND_SUCCESSFUL = 3;
	public static final int BUTTON_CHANGE = 6;
	private static Activity_change_phone _instance;
	private static Fragment_change_phone _fragment_change_phone;
	private static Thread_time _timer = null;
	private String _phone;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case SEND_FAILED:
			case CHANGE_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case SEND_SUCCESSFUL:
			case CHANGE_SUCCESSFUL:
				handle_string(msg.what, (String) msg.obj);
				break;
			case BUTTON_CHANGE:
				_fragment_change_phone.mobilcode_change();
			}
			_instance.close_wait();
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
		_instance = this;
		_relativelayout_wait = (RelativeLayout) findViewById(R.id.relativelayout_wait);
		setTitle("更换手机号");

		_fragment_change_phone = new Fragment_change_phone();
		add_fragment(this, _fragment_change_phone, false);
		if (_timer != null)
			_timer.interrupt();
	}

	private static void handle_string(int _what, String _str)
	{
		handle_msg(_instance, _str);
		try
		{
			JSONObject _obj = new JSONObject(_str);
			if (get_code(_obj))
				switch (_what)
				{
				case SEND_SUCCESSFUL:
					_timer = new Thread_time(_handler, BUTTON_CHANGE, 61, 1);
					_timer.start();
					Toast.makeText(_instance, "手机验证码发送成功", Toast.LENGTH_SHORT).show();
					break;
				case CHANGE_SUCCESSFUL:
					Toast.makeText(_instance, "认证手机修改成功", Toast.LENGTH_SHORT).show();
					Intent _intent = new Intent();
					_intent.putExtra("phone", _instance._phone);
					_instance.setResult(RESULT_OK, _intent);
					_instance.finish();
					break;
				}
			else
				Toast.makeText(_instance, JsonUtil.get_string(_obj, "msg", "连接服务器失败。"), Toast.LENGTH_SHORT).show();
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
		}
	}

	public static Activity_change_phone get_instance()
	{
		return _instance;
	}

	public void set_phone(String _phone)
	{
		this._phone = _phone;
	}

}
