package com.shownest.android.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_quotation_change;
import com.shownest.android.fragment.Fragment_quotation_detail;
import com.shownest.android.model.ItemDetail;
import com.shownest.android.model.RoomDetail;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_quotation_change extends DEBUG_Activity
{
	public static final int CHANGE_FAILED = 0;
	public static final int CHANGE_SUCCESSFUL = 1;
	public static final int LOGIN_FAILED = 2;
	public static final int LOGIN_SUCCESSFUL = 3;
	public static final int CHECK_FAILED = 4;
	public static final int CHECK_SUCCESSFUL = 5;
	private static Activity_quotation_change _instance;
	private static Intent _intent;
	private static RoomDetail _data;
	private static ItemDetail _new_item;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			String _string_result = "";
			switch (msg.what)
			{
			case CHECK_FAILED:
			case LOGIN_FAILED:
			case CHANGE_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case CHECK_SUCCESSFUL:
			case LOGIN_SUCCESSFUL:
			case CHANGE_SUCCESSFUL:
				_string_result = (String) msg.obj;
				handle_string(_string_result);
				break;
			}
			_instance.close_wait();
			System.out.println(_string_result);
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
		setTitle("详细修改");

		_data = Activity_quotation_detail.get_data();
		if (_data != null)
		{
			if (_intent.getStringExtra("type").equals("change"))
			{

				ItemDetail _temp_item = _data.get_details(_intent.getStringExtra("part")).get(_intent.getIntExtra("part_index", 0));
				_new_item = new ItemDetail(_temp_item);
			}
			add_fragment(_instance, new Fragment_quotation_change(), false);
		}
	}

	private static void handle_string(String str)
	{
		handle_msg(_instance, str);


		try
		{
			JSONObject _obj = new JSONObject(str);
			String _result = _obj.getString("msg");

			if (_result.equals("智能报价单部分明细"))
			{
				_data = new RoomDetail(_obj.getJSONObject("data"), _intent.getStringExtra("part"));
				add_fragment(_instance, new Fragment_quotation_detail(), false);
			}
			else if (_result.equals("未查询到数据"))
			{
				Toast.makeText(_instance, _result, Toast.LENGTH_SHORT).show();
				_instance.finish();
			}
			else
				Toast.makeText(_instance, _result, Toast.LENGTH_SHORT).show();
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public static Intent get_intent()
	{
		return _intent;
	}

	public static RoomDetail get_data()
	{
		return _data;
	}

	public static ItemDetail get_item()
	{
		return _new_item;
	}

	public static Activity_quotation_change get_instance()
	{
		return _instance;
	}
}
