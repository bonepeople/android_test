package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_forget;
import com.shownest.android.fragment.Fragment_forget_set;
import com.shownest.android.thread.Thread_time;
import com.shownest.android.utils.HttpUtil;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class Activity_forget extends DEBUG_Activity
{
	public static final int FORGET_FAILED = 0;
	public static final int FORGET_SUCCESSFUL = 1;
	public static final int SEND_FAILED = 2;
	public static final int SEND_SUCCESSFUL = 3;
	public static final int CHECK_FAILED = 4;
	public static final int CHECK_SUCCESSFUL = 5;
	public static final int BUTTON_CHANGE = 6;
	public static final int NEXT_FAILED = 7;
	public static final int NEXT_SUCCESSFUL = 8;
	private static Activity_forget _instance;
	private static Fragment_forget _fragment_forget;
	private static Fragment_forget_set _fragment_forget_set = null;
	private static String _forget_phone = "";
	private static String _forget_code = "";
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
			case NEXT_FAILED:
			case FORGET_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case CHECK_SUCCESSFUL:
			case SEND_SUCCESSFUL:
			case NEXT_SUCCESSFUL:
			case FORGET_SUCCESSFUL:
				_string_result = (String) msg.obj;
				handle_string(FORGET_SUCCESSFUL, _string_result);
				break;
			case BUTTON_CHANGE:
				_fragment_forget.mobilcode_change();
			}
			_fragment_forget.close_wait();
			if (_fragment_forget_set != null && msg.what != NEXT_SUCCESSFUL)
				_fragment_forget_set.close_wait();
			System.out.println(_string_result);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
		_instance = this;

		_fragment_forget = new Fragment_forget();
		add_fragment(this, _fragment_forget, false);
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

			if (_result.equals("用户名已经存在"))
			{
				HttpUtil.send_mobilecode(_handler, _forget_phone, SEND_SUCCESSFUL, SEND_FAILED);
			}
			else if (_result.equals("手机验证码发送成功"))
			{
				_timer = new Thread_time(_handler, BUTTON_CHANGE, 61, 1);
				_timer.start();
				Toast.makeText(_instance, _result, Toast.LENGTH_SHORT).show();
			}
			else if (_result.equals("验证成功"))
			{
				if (_message == NEXT_SUCCESSFUL)
				{
					Toast.makeText(_instance, "验证成功，设置密码", Toast.LENGTH_SHORT).show();

					_fragment_forget_set = new Fragment_forget_set();
					add_fragment(_instance, _fragment_forget_set, true);
				}
				else
				{
					Toast.makeText(_instance, "设置成功", Toast.LENGTH_SHORT).show();
					_instance.finish();
				}
			}
			else
				Toast.makeText(_instance, _result, Toast.LENGTH_SHORT).show();

		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}

	}

	public static String get_forget_phone()
	{
		return _forget_phone;
	}

	public static void set_forget_phone(String _forget_phone_new)
	{
		_forget_phone = _forget_phone_new;
	}

	public static String get_forget_code()
	{
		return _forget_code;
	}

	public static void set_forget_code(String _forget_code_new)
	{
		_forget_code = _forget_code_new;
	}
}
