package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_quotation_change;
import com.shownest.android.model.ItemDetail;
import com.shownest.android.model.RoomDetail;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.utils.JsonUtil;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 修改报价单
 * <p>
 * <b>intent:</b>"quotationId"-报价单ID，"type"-修改类型(change/fix)，"room"-房间类型({@link RoomDetail}中的tag类型)，"room_index"-房间序号，"part"-部分({@link ItemDetail}
 * 中的tag类型 )，"part_index"-需要修改的序号
 * 
 * @author bonepeople
 */
public class Activity_quotation_change extends DEBUG_Activity
{
	public static final int CHANGE_FAILED = 0;
	public static final int CHANGE_SUCCESSFUL = 1;
	public static final int FIX_FAILED = 2;
	public static final int FIX_SUCCESSFUL = 3;
	public static final int GET_FAILED = 4;
	public static final int GET_SUCCESSFUL = 5;
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
			switch (msg.what)
			{
			case GET_FAILED:
			case CHANGE_FAILED:
			case FIX_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				_instance.close_wait();
				if (msg.what == GET_FAILED)
					_instance.finish();
				break;
			case GET_SUCCESSFUL:
			case CHANGE_SUCCESSFUL:
			case FIX_SUCCESSFUL:
				handle_string(msg.what, (String) msg.obj);
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
		_relativelayout_wait = (RelativeLayout) findViewById(R.id.relativelayout_wait);
		_instance = this;
		_intent = getIntent();

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
				_value.put("quotationId", _intent.getStringExtra("quotationId"));
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
					_all_item = new RoomDetail(_obj.getJSONObject("data"), _room);
					add_fragment(_instance, new Fragment_quotation_change(), false);
					_instance.close_wait();
					break;
				case CHANGE_SUCCESSFUL:
					_data.get_details(_intent.getStringExtra("part")).setValueAt(_intent.getIntExtra("part_index", 0), _new_item);
					_data.fresh_totals(_intent.getStringExtra("part"));
					_instance.close_wait();
					Intent _intent = new Intent();
					_intent.putExtra("result", "successful");
					_instance.setResult(RESULT_OK, _intent);
					_instance.finish();
					break;
				}
			else if (_what != FIX_SUCCESSFUL)
			{
				Toast.makeText(_instance, JsonUtil.get_string(_obj, "msg", "连接服务器失败。"), Toast.LENGTH_SHORT).show();
				_instance.close_wait();
				if (_what == GET_SUCCESSFUL)
					_instance.finish();
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
			if (_what == GET_SUCCESSFUL)
				_instance.finish();
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
