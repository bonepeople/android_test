package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_regist;
import com.shownest.android.thread.Thread_time;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.utils.JsonUtil;

import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
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
	private static Fragment_regist _fragment_regist;
	private static Thread_time _timer = null;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case CHECK_FAILED:
			case SEND_FAILED:
			case REGIST_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case CHECK_SUCCESSFUL:
			case SEND_SUCCESSFUL:
			case REGIST_SUCCESSFUL:
				handle_string(msg.what, (String) msg.obj);
				break;
			case BUTTON_CHANGE:
				_fragment_regist.mobilcode_change();
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
		setTitle("加入秀巢网");

		_fragment_regist = new Fragment_regist();
		add_fragment(this, _fragment_regist, false);
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
				case CHECK_SUCCESSFUL:
					HttpUtil.send_mobilecode(_handler, _fragment_regist.get_regist_phone(), SEND_SUCCESSFUL, SEND_FAILED);
					break;
				case SEND_SUCCESSFUL:
					_timer = new Thread_time(_handler, BUTTON_CHANGE, 61, 1);
					_timer.start();
					Toast.makeText(_instance, "手机验证码发送成功", Toast.LENGTH_SHORT).show();
					break;
				case REGIST_SUCCESSFUL:
					Toast.makeText(_instance, "注册成功", Toast.LENGTH_SHORT).show();
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

	public static Activity_regist get_instance()
	{
		return _instance;
	}
}
