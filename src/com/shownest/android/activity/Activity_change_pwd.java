package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_change_pwd;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class Activity_change_pwd extends DEBUG_Activity
{
	public static final int CHANGE_FAILED = 0;
	public static final int CHANGE_SUCCESSFUL = 1;
	private static Activity_change_pwd _instance;
	private static Context _context;
	private static Fragment_change_pwd _fragment_change_pwd;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			String _string_result = "";
			switch (msg.what)
			{
			case CHANGE_FAILED:
				Toast.makeText(_context, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case CHANGE_SUCCESSFUL:
				_string_result = (String) msg.obj;
				handle_string(_string_result);
			}
			_fragment_change_pwd.close_wait();
			System.out.println(_string_result);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		_instance = this;
		_context = this.getApplicationContext();

		setContentView(R.layout.activity_change_pwd);
		_fragment_change_pwd = (Fragment_change_pwd) getFragmentManager().findFragmentById(R.id.fragment_change_pwd);
	}

	private static void handle_string(String str)
	{
		handle_msg(_instance, str);
		try
		{
			JSONObject _obj = new JSONObject(str);
			String _result = _obj.getString("msg");
			Toast.makeText(_context, _result, Toast.LENGTH_SHORT).show();
			if (_result.equals("密码修改成功"))
			{
				_instance.finish();
			}

		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}
}
