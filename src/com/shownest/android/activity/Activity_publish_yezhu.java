package com.shownest.android.activity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_publish_yezhu_setp1;
import com.shownest.android.model.HouseInfo;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.utils.JsonUtil;
import com.shownest.android.utils.UserManager;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 业主-发布招标
 * 
 * @author bonepeople
 *
 */
public class Activity_publish_yezhu extends DEBUG_Activity
{
	public static final int GET_FAILED = 0;
	public static final int GET_SUCCESSFUL = 1;
	private static Activity_publish_yezhu _instance;
	private static ArrayList<HouseInfo> _house = new ArrayList<HouseInfo>();
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
		setTitle("发布招标");

		if (UserManager.is_login())
		{
			show_wait();
			ContentValues _value = new ContentValues();
			_value.put("houseId", "");
			HttpUtil.get_houseinfo(_handler, _value, GET_SUCCESSFUL, GET_FAILED);
		}
		else
		{
			Intent _login = new Intent(this, Activity_login.class);
			startActivity(_login);
			finish();
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
					JSONArray _array = _obj.getJSONArray("data");
					for (int _temp_i = 0; _temp_i < _array.length(); _temp_i++)
					{
						HouseInfo _temp_house = new HouseInfo(_array.getJSONObject(_temp_i));
						_house.add(_temp_house);
					}
					if (_house.size() == 0)
					{
						Toast.makeText(_instance, "您还没有房屋，请先创建一个房屋", Toast.LENGTH_SHORT).show();
						Intent _add = new Intent(_instance, Activity_addhouse.class);
						_instance.startActivity(_add);
						_instance.finish();
					}
					add_fragment(_instance, new Fragment_publish_yezhu_setp1(), false);
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

	public static ArrayList<HouseInfo> get_house()
	{
		return _house;
	}

	public static Activity_publish_yezhu get_instance()
	{
		return _instance;
	}
}
