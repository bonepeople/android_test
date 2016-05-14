package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_forget_set;
import com.shownest.android.fragment.Fragment_my_center;
import com.shownest.android.model.UserInfo;
import com.shownest.android.utils.HttpUtil;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.Toast;

public class Activity_my_center extends DEBUG_Activity
{
	public static final int LOGIN_FAILED = 0;
	public static final int LOGIN_SUCCESSFUL = 1;
	private static Activity_my_center _instance;
	private FrameLayout _framelayout_content;
	private static Context _context;
	private static UserInfo _info;

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
				_instance.finish();
				break;
			case LOGIN_SUCCESSFUL:
				_string_result = (String) msg.obj;
				handle_string(_string_result);
			}
			System.out.println(_string_result);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_center);
		_instance = this;
		_context = this.getApplicationContext();
		_framelayout_content = (FrameLayout) findViewById(R.id.framelayout_content);

		HttpUtil.get_userinfo(_handler, LOGIN_SUCCESSFUL, LOGIN_FAILED);
	}

	private static void handle_string(String str)
	{
		handle_msg(_instance, str);
		try
		{
			JSONObject _obj = new JSONObject(str);
			String _result = _obj.getString("msg");
			if (_result.equals("用户已经登陆"))
			{
				JSONObject _data = _obj.getJSONArray("data").getJSONObject(0);
				_info = new UserInfo(_data);
//				System.out.println(_info.toString());
				
				Fragment_my_center _fragment_my_center = new Fragment_my_center();
				FragmentManager fm = _instance.getFragmentManager();
				FragmentTransaction tx = fm.beginTransaction();
				tx.add(R.id.framelayout_content, _fragment_my_center, "center");
				tx.commit();

			}
			else
				Toast.makeText(_context, _result, Toast.LENGTH_SHORT).show();

		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Toast.makeText(_context, "网络连接异常", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected String get_class()
	{
		return this.getClass().toString();
	}

	public static UserInfo get_userinfo()
	{
		return _info;
	}
}
