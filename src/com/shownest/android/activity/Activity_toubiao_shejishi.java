package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.utils.JsonUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_toubiao_shejishi extends DEBUG_Activity
{
	public static final int GET_FAILED = 0;
	public static final int GET_SUCCESSFUL = 1;
	private static Activity_toubiao_shejishi _instance;
	private static Intent _intent;
	private static int _index = 0;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case GET_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case GET_SUCCESSFUL:
				handle_string(msg.what, (String) msg.obj);
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
		_relativelayout_wait = (RelativeLayout) findViewById(R.id.relativelayout_wait);
		_instance = this;
		_intent = getIntent();
		setTitle("我要投标");

		_index = _intent.getIntExtra("index", 0);
		System.out.println("_index :" + _index);

		// show_wait();
		// ContentValues _value = new ContentValues();
		// _value.put("quotationId", _quotationId);
		// _value.put(_room, _number);
		// HttpUtil.get_quotation_item(_handler, _room, _value, GET_SUCCESSFUL, GET_FAILED);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == -1)
		{
			switch (requestCode)
			{
			case 1:

				break;

			case 2:

				break;
			}
		}
		else
			System.out.println("resultCode=" + resultCode);
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
				case GET_SUCCESSFUL:
					// _data = new RoomDetail(_obj.getJSONObject("data"), _intent.getStringExtra("room"));
					// _fragment_detail = new Fragment_quotation_detail();
					// add_fragment(_instance, _fragment_detail, false);
					break;
				}
			else
			{
				Toast.makeText(_instance, JsonUtil.get_string(_obj, "msg", "连接服务器失败。"), Toast.LENGTH_SHORT).show();
				_instance.finish();
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
			_instance.finish();
		}
	}

	public static Activity_toubiao_shejishi get_instance()
	{
		return _instance;
	}
}
