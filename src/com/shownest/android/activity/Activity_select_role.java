package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_select_role;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class Activity_select_role extends DEBUG_Activity
{
	public static final int CHANGE_FAILED = 0;
	public static final int CHANGE_SUCCESSFUL = 1;
	private static Activity_select_role _instance;
	private static Fragment_select_role _fragment_select_role;
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
				handle_string(_string_result);
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
		_instance = this;
		setTitle("选择身份类型");

		_fragment_select_role = new Fragment_select_role();
		add_fragment(this, _fragment_select_role, false);
	}

	private static void handle_string(String str)
	{
		handle_msg(_instance, str);
		try
		{
			JSONObject _obj = new JSONObject(str);
			String _result = _obj.getString("msg");
			Toast.makeText(_instance, _result, Toast.LENGTH_SHORT).show();
			if (_result.equals("用户身份类型修改成功"))
			{
				if (_fragment_select_role.get_intent_class() != null)
				{
					Intent _intent_set = new Intent(_instance, _fragment_select_role.get_intent_class());
					_instance.startActivity(_intent_set);
				}
				_instance.finish();
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public static Activity_select_role get_instance()
	{
		return _instance;
	}
}
