package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_test;
import com.shownest.android.model.UserInfo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

public class Activity_test extends DEBUG_Activity
{
	private Intent _intent;
	public static final int TEST_FAILED = 0;
	public static final int TEST_SUCCESSFUL = 1;
	private static Activity_test _instance;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			String _string_result = "";
			switch (msg.what)
			{
			case TEST_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				_string_result = (String) msg.obj;
				break;
			case TEST_SUCCESSFUL:
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
		setContentView(R.layout.activity_basic);
		_instance = this;
		_intent = getIntent();

		add_fragment(this, new Fragment_test(), false);
	}

	private static void handle_string(String str)
	{
		handle_msg(_instance, str);
		try
		{
			JSONObject _obj = new JSONObject(str);
			String _result = _obj.getString("msg");
			Toast.makeText(_instance, _result, Toast.LENGTH_SHORT).show();

			JSONObject _data = _obj.getJSONArray("data").getJSONObject(0);
			UserInfo _info = new UserInfo(_data);
			System.out.println(_info.toString());
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public void test(View v)
	{
		String _str = "result";
		_intent.putExtra("phone",_str);
		setResult(RESULT_OK, _intent); // intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
		finish();// 此处一定要调用finish()方法
	}

	public static Activity_test get_instance()
	{
		return _instance;
	}
}
