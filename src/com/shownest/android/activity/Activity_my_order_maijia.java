package com.shownest.android.activity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.model.OrderInfo;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.utils.JsonUtil;

import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 我的订单(卖家)
 * 
 * @author bonepeople
 */
public class Activity_my_order_maijia extends DEBUG_Activity
{
	public static final int GET_FAILED = 0;
	public static final int GET_SUCCESSFUL = 1;
	private static Activity_my_order_maijia _instance;
	private static ArrayList<OrderInfo> _data = new ArrayList<OrderInfo>();
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case GET_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				_instance.close_wait();
				break;
			case GET_SUCCESSFUL:
				handle_string(msg.what, (String) msg.obj);
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
		setTitle("我的订单");

		ContentValues _value = new ContentValues();
		_value.put("startPage", 0);
		show_wait();
		HttpUtil.get_order_list(_handler, _value, GET_SUCCESSFUL, GET_FAILED);
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
					JSONArray _array = _obj.getJSONObject("data").getJSONArray("decorateOrderBases");
					_data.clear();
					for (int _temp_i = 0; _temp_i < _array.length(); _temp_i++)
					{
						OrderInfo _temp_info = new OrderInfo(_array.getJSONObject(_temp_i));
						_data.add(_temp_info);
					}
					// add_fragment(_instance, new Fragment_my_order_yezhu(), false);
					break;
				}
			else
			{
				Toast.makeText(_instance, JsonUtil.get_string(_obj, "msg", "连接服务器失败。"), Toast.LENGTH_SHORT).show();
				_instance.close_wait();
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
			_instance.close_wait();
		}
	}

	public static ArrayList<OrderInfo> get_data()
	{
		return _data;
	}

	public static Activity_my_order_maijia get_instance()
	{
		return _instance;
	}
}
