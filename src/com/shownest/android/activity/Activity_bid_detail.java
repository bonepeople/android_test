package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_bid_detail;
import com.shownest.android.model.BidInfo;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.utils.JsonUtil;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 招标详细信息
 * 
 * @author bonepeople
 */
public class Activity_bid_detail extends DEBUG_Activity
{
	public static final int GET_FAILED = 0;
	public static final int GET_SUCCESSFUL = 1;
	private static Activity_bid_detail _instance;
	private static Intent _intent;
	private static String _bidID = "";
	private static BidInfo _data;
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
			// start ERROR_Activity
		}
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
					_data = new BidInfo(_obj.getJSONArray("data").getJSONObject(0));
					_instance.setTitle(_data.get_areaName());
					add_fragment(_instance, new Fragment_bid_detail(), false);
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

	public static BidInfo get_data()
	{
		return _data;
	}

	public static Activity_bid_detail get_instance()
	{
		return _instance;
	}

}
