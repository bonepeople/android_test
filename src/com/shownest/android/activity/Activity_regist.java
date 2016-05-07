package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.fragment.Fragment_login;
import com.shownest.android.fragment.Fragment_regist;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_regist extends Activity
{
	private static boolean DEBUG = true;
	public static final int REGIST_FAILED = 0;
	public static final int REGIST_SUCCESSFUL = 1;
	private static Activity_regist _instance;
	private static Context _context;
	private static Fragment_regist _fragment_regist;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			String _string_result = "";
			switch (msg.what)
			{
			case REGIST_FAILED:
				Toast.makeText(_context, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				_string_result = (String) msg.obj;

				break;
			case REGIST_SUCCESSFUL:
				_string_result = (String) msg.obj;
				handle_string(_string_result);

				break;
			}
			_fragment_regist._relativelayout_wait.setVisibility(RelativeLayout.INVISIBLE);
			System.out.println(_string_result);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		if (DEBUG)
			System.out.println("Activity_regist onCreate");
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		_instance = this;
		_context = this.getApplicationContext();
		setContentView(R.layout.activity_regist);

		_fragment_regist = (Fragment_regist) getFragmentManager().findFragmentById(R.id.fragment_regist);

	}

	private static void handle_string(String str)
	{
		if (DEBUG)
		{
			System.out.println("Activity_regist handle msg:" + str);
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
