package com.shownest.android.activity;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_quota_list;
import com.shownest.android.model.QuotaInfo;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.utils.JsonUtil;
import com.shownest.android.utils.UserManager;

import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 投标记录页面
 * 
 * @author bonepeople
 */
public class Activity_quota_list extends DEBUG_Activity
{
	public static final int GET_FAILED = 0;
	public static final int GET_SUCCESSFUL = 1;
	private static Activity_quota_list _instance;
	private static boolean _has_detail = false;
	private static ArrayList<QuotaInfo> _data = new ArrayList<QuotaInfo>();
	private static String _bidID = "";
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
		setTitle("招标详情");

		_bidID = getIntent().getStringExtra("id");
		_has_detail = getIntent().getBooleanExtra("has_detail", false);
		
		ContentValues _value = new ContentValues();
		_value.put("homeId", _bidID);
		_value.put("userId", UserManager.get_user_info().get_userId());
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
					// JSONArray _array = _obj.getJSONObject("data").getJSONArray("items");
					// for (int _temp_i = 0; _temp_i < _array.length(); _temp_i++)
					// {
					// BidInfo_common _temp_bid = new BidInfo_common(_array.getJSONObject(_temp_i));
					// _data.add(_temp_bid);
					// }
					for (int _temp_i = 0; _temp_i < 10; _temp_i++)
					{
						QuotaInfo _temp_quota = new QuotaInfo(_obj);
						_data.add(_temp_quota);
					}
					add_fragment(_instance, new Fragment_quota_list(), false);
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

	public static ArrayList<QuotaInfo> get_data()
	{
		return _data;
	}

	public static boolean is_has_detail()
	{
		return _has_detail;
	}

	public static Activity_quota_list get_instance()
	{
		return _instance;
	}
}
