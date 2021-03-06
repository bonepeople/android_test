package com.shownest.android.activity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_quota_list;
import com.shownest.android.model.QuotaInfo;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.utils.JsonUtil;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 投标记录页面
 * <p>
 * <b>intent:</b>"id"-要查看的招标的ID，"type"-招标类型(12，13，14)，"have_detail"-是否拥有顶部的详细信息链接(true/false)
 * 
 * @author bonepeople
 */
public class Activity_quota_list extends DEBUG_Activity
{
	public static final int GET_FAILED = 0;
	public static final int GET_SUCCESSFUL = 1;
	private static Activity_quota_list _instance;
	private Intent _intent;
	private static boolean _have_detail;
	private static QuotaInfo _winner;
	private static ArrayList<QuotaInfo> _data = new ArrayList<QuotaInfo>();
	private static String _bidID = "";
	private static int _type;
	private static int _startPage = 0;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case GET_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				System.out.println((String) msg.obj);
				_instance.finish();
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
		setTitle("招标详情");

		_bidID = _intent.getStringExtra("id");
		_type = _intent.getIntExtra("type", 12);
		_have_detail = _intent.getBooleanExtra("have_detail", false);

		ContentValues _value = new ContentValues();
		_value.put("homeId", _bidID);
		_value.put("startPage", _startPage);
		show_wait();
		HttpUtil.get_quota_list(_handler, _value, GET_SUCCESSFUL, GET_FAILED);
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
					if (_obj.getJSONObject("data").has("bidRespWinner"))
						_winner = new QuotaInfo(_obj.getJSONObject("data").getJSONObject("bidRespWinner"));
					else
						_winner = null;

					JSONArray _array = _obj.getJSONObject("data").getJSONArray("bidRespUsers");
					_data.clear();
					for (int _temp_i = 0; _temp_i < _array.length(); _temp_i++)
					{
						QuotaInfo _temp_quota = new QuotaInfo(_array.getJSONObject(_temp_i));
						_data.add(_temp_quota);
					}
					add_fragment(_instance, new Fragment_quota_list(), false);
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

	public static ArrayList<QuotaInfo> get_data()
	{
		return _data;
	}

	public static QuotaInfo get_winner()
	{
		return _winner;
	}

	public static String get_bidID()
	{
		return _bidID;
	}

	public static int get_type()
	{
		return _type;
	}

	public static boolean have_detail()
	{
		return _have_detail;
	}

	public static Activity_quota_list get_instance()
	{
		return _instance;
	}
}
