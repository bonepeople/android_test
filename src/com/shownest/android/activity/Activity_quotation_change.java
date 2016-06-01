package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_quotation_change;
import com.shownest.android.model.ItemDetail;
import com.shownest.android.model.RoomDetail;
import com.shownest.android.utils.HttpUtil;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_quotation_change extends DEBUG_Activity
{
	public static final int CHANGE_FAILED = 0;
	public static final int CHANGE_SUCCESSFUL = 1;
	public static final int GET_FAILED = 2;
	public static final int GET_SUCCESSFUL = 3;
	private static Activity_quotation_change _instance;
	private static Intent _intent;
	private static RoomDetail _data, _all_item;
	private static ItemDetail _new_item;
	private static String _room;
	private static int _room_index, _total_commit = 0;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			String _string_result = "";
			switch (msg.what)
			{
			case GET_FAILED:
			case CHANGE_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				_instance.close_wait();
				break;
			case GET_SUCCESSFUL:
			case CHANGE_SUCCESSFUL:
				_string_result = (String) msg.obj;
				handle_string(_string_result);
				break;
			}
			check_commit();
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

		_data = Activity_quotation_detail.get_data();
		if (_data != null)
		{
			if (_intent.getStringExtra("type").equals("change"))
			{
				ItemDetail _temp_item = _data.get_details(_intent.getStringExtra("part")).get(_intent.getIntExtra("part_index", 0));
				_new_item = new ItemDetail(_temp_item);
				setTitle(_new_item.get_itemName());
				add_fragment(_instance, new Fragment_quotation_change(), false);
			}
			else
			{
				setTitle("增减工艺");
				ContentValues _value = new ContentValues();
				_value.put("quotationId", "");
				_room = _intent.getStringExtra("room");
				_room_index = _intent.getIntExtra("room_index", 1);
				String _str_part = _intent.getStringExtra("part");
				switch (_str_part)
				{
				case "ground":
					_value.put("assortment", 1);
					_value.put(_room, _room_index);
					break;
				case "wall":
					_value.put("assortment", 3);
					_value.put(_room, _room_index);
					break;
				case "roof":
					_value.put("assortment", 2);
					_value.put(_room, _room_index);
					break;
				}
				_value.put("data", "all");

				show_wait();
				HttpUtil.get_quotation_item(_handler, _room, _value, GET_SUCCESSFUL, GET_FAILED);
			}
		}
	}

	private static void handle_string(String str)
	{
		handle_msg(_instance, str);

		try
		{
			JSONObject _obj = new JSONObject(str);
			String _result = _obj.getString("msg");

			if (_result.equals("报价单详细项修改成功") || _result.equals("工队报价模板详细项修改成功"))
			{
				if (_intent.getStringExtra("type").equals("change"))
				{
					_data.get_details(_intent.getStringExtra("part")).setValueAt(_intent.getIntExtra("part_index", 0), _new_item);
					_data.fresh_totals(_intent.getStringExtra("part"));
					_instance.close_wait();
					Intent _intent = new Intent();
					_intent.putExtra("result", "successful");
					_instance.setResult(RESULT_OK, _intent);
					_instance.finish();
				}
			}
			else if (_result.contains("要修改的项目不存在"))
			{

			}
			else if (_result.contains("报价单对应的报价模板中"))
			// 报价单对应的报价模板中--所有的地面数据
			// 报价单对应的报价模板中--所有的墙面数据
			// 报价单对应的报价模板中--所有的顶面数据
			// 报价单对应的报价模板中--所有的水电数据
			// 报价单对应的报价模板中--所有的安装数据
			// 报价单对应的报价模板中--所有的杂费数据
			// 报价单对应的报价模板中--所有的税费数据
			{
				_all_item = new RoomDetail(_obj.getJSONObject("data"), _room);
				add_fragment(_instance, new Fragment_quotation_change(), false);
				_instance.close_wait();
			}
			else
			{
				Toast.makeText(_instance, _result, Toast.LENGTH_SHORT).show();
				_instance.close_wait();
			}
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

	public static RoomDetail get_all_item()
	{
		return _all_item;
	}

	public static ItemDetail get_item()
	{
		return _new_item;
	}

	public static void set_total_commit(int _total)
	{
		_total_commit = _total;
		System.out.println("total is:" + _total_commit);
	}

	public static Activity_quotation_change get_instance()
	{
		return _instance;
	}

	private static void check_commit()
	{
		_total_commit--;
		System.out.println("total last:" + _total_commit);
		if (_total_commit == 0)
		{
			_instance.close_wait();
			Intent _intent = new Intent();
			_intent.putExtra("result", "successful");
			_instance.setResult(RESULT_OK, _intent);
			_instance.finish();
		}
	}
}
