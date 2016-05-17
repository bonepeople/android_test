package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_change_phone;
import com.shownest.android.thread.Thread_time;

import android.os.Bundle;
import android.os.Handler;
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
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			String _string_result = "";
			switch (msg.what)
			{
			case SEND_FAILED:
			case CHANGE_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case SEND_SUCCESSFUL:
			case CHANGE_SUCCESSFUL:
				_string_result = (String) msg.obj;
				handle_string(CHANGE_SUCCESSFUL, _string_result);
				break;
			case BUTTON_CHANGE:
				_fragment_change_phone.mobilcode_change();
			}
			_fragment_change_phone.close_wait();
			System.out.println(_string_result);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
		_instance = this;

		_fragment_change_phone = new Fragment_change_phone();
		add_fragment(this, _fragment_change_phone, false);
		if (_timer != null)
			_timer.interrupt();
	}

	private static void handle_string(int _message, String _str)
	{
		handle_msg(_instance, _str);
		try
		{
			JSONObject _obj = new JSONObject(_str);
			String _result = _obj.getString("msg");

			if (_result.equals("手机验证码发送成功"))
			{
				_timer = new Thread_time(_handler, BUTTON_CHANGE, 61, 1);
				_timer.start();
				Toast.makeText(_instance, _result, Toast.LENGTH_SHORT).show();
			}
			else if (_result.equals("操作成功"))
			{
				Toast.makeText(_instance, "认证手机修改成功", Toast.LENGTH_SHORT).show();
				_instance.finish();
			}
			else
				Toast.makeText(_instance, _result, Toast.LENGTH_SHORT).show();

		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}

	}

	public static Activity_change_phone get_instance()
	{
		return _instance;
	}

}
