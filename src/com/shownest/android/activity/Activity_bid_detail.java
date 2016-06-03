package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.utils.HttpUtil;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_bid_detail extends DEBUG_Activity
{
	public static final int GET_FAILED = 0;
	public static final int GET_SUCCESSFUL = 1;
	private static Activity_bid_detail _instance;
	private static Intent _intent;
	private static String _bidID = "";
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			String _string_result = "";
			switch (msg.what)
			{
			case GET_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case GET_SUCCESSFUL:
				_string_result = (String) msg.obj;
				handle_string(_string_result);
				break;
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
		_intent = getIntent();
		_relativelayout_wait = (RelativeLayout) findViewById(R.id.relativelayout_wait);
		// setTitle("发布招标");

		_bidID = _intent.getStringExtra("bidID");
		if (!_bidID.equals(""))
		{
			ContentValues _value = new ContentValues();
			_value.put("homeId", _bidID);
			_value.put("page", 1);
			show_wait();
			HttpUtil.get_bid(_handler, _value, GET_SUCCESSFUL, GET_FAILED);
		}
		else
		{
			//start ERROR_Activity
		}
	}

	private static void handle_string(String str)
	{
		handle_msg(_instance, str);

		try
		{
			JSONObject _obj = new JSONObject(str);
			String _result = _obj.getString("msg");

			if (_result.equals("报价单详细项修改成功"))
			{
			}
			else if (_result.contains("要修改的项目不存在"))
			{

			}
			else if (_result.contains("报价单对应的报价模板中"))
			{
			}
			else
			{
				Toast.makeText(_instance, _result, Toast.LENGTH_SHORT).show();
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public static Activity_bid_detail get_instance()
	{
		return _instance;
	}
}
