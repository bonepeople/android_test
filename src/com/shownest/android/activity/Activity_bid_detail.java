package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_bid_detail;
import com.shownest.android.model.BidInfo_common;
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
	private static int _index = 0;
	private static BidInfo_common _data;
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

		_bidID = _intent.getStringExtra("bidID");
		_index = _intent.getIntExtra("index", 0);
		if (_bidID != null && !_bidID.equals(""))
		{
			ContentValues _value = new ContentValues();
			_value.put("homeId", _bidID);
			show_wait();
			HttpUtil.get_bid_info(_handler, _value, GET_SUCCESSFUL, GET_FAILED);
		}
		else
		{
			if (Activity_AH_bid.get_data() != null)
			{
				_data = Activity_AH_bid.get_data().get(_index);
				setTitle(_data.get_houseName());
				add_fragment(_instance, new Fragment_bid_detail(), false);
			}
			else
			{
				Toast.makeText(this, "软件异常，请联系技术人员解决", Toast.LENGTH_LONG).show();
			}
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
					_data = new BidInfo_common(_obj.getJSONObject("data"));
					_instance.setTitle(_data.get_houseName());
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

	public static BidInfo_common get_data()
	{
		return _data;
	}

	public static Activity_bid_detail get_instance()
	{
		return _instance;
	}

}
