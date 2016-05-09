package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.fragment.Fragment_login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_login extends Activity
{
	private static boolean DEBUG = true;
	public static final int LOGIN_FAILED = 0;
	public static final int LOGIN_SUCCESSFUL = 1;
	private static Activity_login _instance;
	private static Context _context;
	private static Fragment_login _fragment_login;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			String _string_result = "";
			switch (msg.what)
			{
			case LOGIN_FAILED:
				Toast.makeText(_context, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				_string_result = (String) msg.obj;

				break;
			case LOGIN_SUCCESSFUL:
				_string_result = (String) msg.obj;
				handle_string(_string_result);

				break;
			}
			_fragment_login._relativelayout_wait.setVisibility(RelativeLayout.INVISIBLE);
			System.out.println(_string_result);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		if (DEBUG)
			System.out.println("Activity_login onCreate");
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		_instance = this;
		_context = this.getApplicationContext();
		setContentView(R.layout.activity_login);

		_fragment_login = (Fragment_login) getFragmentManager().findFragmentById(R.id.fragment_login);

	}

	private static void handle_string(String str)
	{
		if (DEBUG)
		{
			System.out.println("Activity_login handle msg:" + str);
		}
		try
		{
			JSONObject _obj = new JSONObject(str);
			String _result = _obj.getString("msg");
			Toast.makeText(_context, _result, Toast.LENGTH_SHORT).show();
			if (_result.equals("用户登录成功"))
			{
				_instance.finish();
			}

		}
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
