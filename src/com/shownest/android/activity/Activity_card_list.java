package com.shownest.android.activity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_card_list;
import com.shownest.android.model.BankCardInfo;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.utils.JsonUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 银行卡管理
 * 
 * @author bonepeople
 */
public class Activity_card_list extends DEBUG_Activity
{
	public static final int GET_FAILED = 0;
	public static final int GET_SUCCESSFUL = 1;
	public static final int DELETE_FAILED = 2;
	public static final int DELETE_SUCCESSFUL = 3;
	public static final int RESULT_ADD = 4;
	private static Activity_card_list _instance;
	private static Fragment_card_list _fragment;
	private static ArrayList<BankCardInfo> _data = new ArrayList<BankCardInfo>();
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case DELETE_FAILED:
			case GET_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				_instance.finish();
				break;
			case DELETE_SUCCESSFUL:
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
		setTitle("银行卡管理");
		setMenu("添加");

		_fragment = null;
		show_wait();
		HttpUtil.get_card_list(_handler, GET_SUCCESSFUL, GET_FAILED);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == -1)
		{
			switch (requestCode)
			{
			case RESULT_ADD:
				show_wait();
				HttpUtil.get_card_list(_handler, GET_SUCCESSFUL, GET_FAILED);
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
					JSONArray _array = _obj.getJSONArray("data");
					_data.clear();
					for (int _temp_i = 0; _temp_i < _array.length(); _temp_i++)
					{
						BankCardInfo _temp_info = new BankCardInfo(_array.getJSONObject(_temp_i));
						_data.add(_temp_info);
					}
					if (_fragment == null)
						add_fragment(_instance, _fragment = new Fragment_card_list(), false);
					else
						_fragment.refresh("add");
					break;
				case DELETE_SUCCESSFUL:
					Toast.makeText(_instance, "银行卡删除成功", Toast.LENGTH_SHORT).show();
					_fragment.refresh("del");
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

	@Override
	public void menu_click()
	{
		Intent _add = new Intent(this, Activity_card_add.class);
		startActivityForResult(_add, RESULT_ADD);
	}

	public static ArrayList<BankCardInfo> get_data()
	{
		return _data;
	}

	public static Activity_card_list get_instance()
	{
		return _instance;
	}
}
