package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_setinfo_yezhu;

import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_setinfo_yezhu extends DEBUG_Activity
{
	public static final int CHANGE_FAILED = 0;
	public static final int CHANGE_SUCCESSFUL = 1;
	private static RelativeLayout _relativelayout_wait;
	private static Activity_setinfo_yezhu _instance;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			String _string_result = "";
			switch (msg.what)
			{
			case CHANGE_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case CHANGE_SUCCESSFUL:
				_string_result = (String) msg.obj;
				handle_string(CHANGE_SUCCESSFUL, _string_result);
				break;
			}
			close_wait();
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
		_instance = this;
		_relativelayout_wait = (RelativeLayout) findViewById(R.id.relativelayout_wait);

		add_fragment(this, new Fragment_setinfo_yezhu(), false);
	}

	private static void handle_string(int _message, String _str)
	{
		handle_msg(_instance, _str);
		try
		{
			JSONObject _obj = new JSONObject(_str);
			String _result = _obj.getString("msg");

			if (_result.equals("提交成功"))
			{
				Toast.makeText(_instance, "提交成功", Toast.LENGTH_SHORT).show();
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

	public static void show_wait()
	{
		if (_relativelayout_wait != null && _relativelayout_wait.getVisibility() != RelativeLayout.VISIBLE)
			_relativelayout_wait.setVisibility(RelativeLayout.VISIBLE);
	}

	public static void close_wait()
	{
		if (_relativelayout_wait != null && _relativelayout_wait.getVisibility() == RelativeLayout.VISIBLE)
			_relativelayout_wait.setVisibility(RelativeLayout.INVISIBLE);
	}
}
