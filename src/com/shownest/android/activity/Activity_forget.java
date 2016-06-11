package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_forget;
import com.shownest.android.fragment.Fragment_forget_set;
import com.shownest.android.thread.Thread_time;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.utils.JsonUtil;

import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 忘记密码，找回密码
 * 
 * @author bonepeople
 */
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
	private static String _forget_phone = "";
	private static String _forget_code = "";
	private static Thread_time _timer = null;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case CHECK_FAILED:
			case SEND_FAILED:
			case NEXT_FAILED:
			case FORGET_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				_instance.close_wait();
				break;
			case CHECK_SUCCESSFUL:
			case SEND_SUCCESSFUL:
			case NEXT_SUCCESSFUL:
			case FORGET_SUCCESSFUL:
				handle_string(msg.what, (String) msg.obj);
				break;
			case BUTTON_CHANGE:
				_fragment_forget.mobilcode_change();
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
		_relativelayout_wait = (RelativeLayout) findViewById(R.id.relativelayout_wait);
		_instance = this;
		setTitle("密码找回");

		_fragment_forget = new Fragment_forget();
		add_fragment(this, _fragment_forget, false);
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
			{
				switch (_what)
				{
				case CHECK_SUCCESSFUL:
					Toast.makeText(_instance, JsonUtil.get_string(_obj, "msg", "连接服务器失败。"), Toast.LENGTH_SHORT).show();
					break;
				case SEND_SUCCESSFUL:
					_timer = new Thread_time(_handler, BUTTON_CHANGE, 61, 1);
					_timer.start();
					Toast.makeText(_instance, "手机验证码发送成功", Toast.LENGTH_SHORT).show();
					break;
				case NEXT_SUCCESSFUL:
					Toast.makeText(_instance, "验证成功，设置密码", Toast.LENGTH_SHORT).show();
					add_fragment(_instance, new Fragment_forget_set(), true);
					break;
				case FORGET_SUCCESSFUL:
					Toast.makeText(_instance, "设置成功", Toast.LENGTH_SHORT).show();
					_instance.finish();
					break;
				}
				_instance.close_wait();
			}
			else
				switch (_what)
				{
				case CHECK_SUCCESSFUL:
					HttpUtil.send_mobilecode(_handler, _forget_phone, SEND_SUCCESSFUL, SEND_FAILED);
					break;
				default:
					Toast.makeText(_instance, JsonUtil.get_string(_obj, "msg", "连接服务器失败。"), Toast.LENGTH_SHORT).show();
				}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
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

	public static Activity_forget get_instance()
	{
		return _instance;
	}
}
