package com.shownest.android.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_quotation_detail;
import com.shownest.android.model.OnChangeListener;
import com.shownest.android.model.RoomDetail;
import com.shownest.android.utils.HttpUtil;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_quotation_detail extends DEBUG_Activity implements OnChangeListener
{
	public static final int GET_FAILED = 0;
	public static final int GET_SUCCESSFUL = 1;
	public static final int SEND_FAILED = 2;
	public static final int SEND_SUCCESSFUL = 3;
	public static final int CHECK_FAILED = 4;
	public static final int CHECK_SUCCESSFUL = 5;
	private static Activity_quotation_detail _instance;
	private static Intent _intent;
	private static RoomDetail _data;
	private static String _quotationId;
	private static String _room;
	private static int _number;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			String _string_result = "";
			switch (msg.what)
			{
			case CHECK_FAILED:
			case SEND_FAILED:
			case GET_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case CHECK_SUCCESSFUL:
			case SEND_SUCCESSFUL:
			case GET_SUCCESSFUL:
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

		_room = _intent.getStringExtra("part");
		_quotationId = _intent.getStringExtra("id");
		_number = _intent.getIntExtra("index", 1);
		System.out.println("select from :" + _room + "-" + _number);

		setTitle(_intent.getStringExtra("name"));
		show_wait();
		ContentValues _value = new ContentValues();
		_value.put("quotationId", _quotationId);
		_value.put(_room, _number);
		HttpUtil.get_quotation_item(_handler, _room, _value, GET_SUCCESSFUL, GET_FAILED);
	}

	private static void handle_string(String str)
	{
		handle_msg(_instance, str);

		File _file_dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/shownest_cache");
		File _file;
		if (_file_dir.mkdirs() || _file_dir.isDirectory())
		{
			_file = new File(_file_dir, "out.txt");
			try
			{
				FileOutputStream _fout = new FileOutputStream(_file);
				byte[] bytes = str.getBytes();
				_fout.write(bytes);
				_fout.close();

			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

		}

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

	@Override
	public void onChange(String tag, String[] args)
	{
		Intent _change;
		switch (tag)
		{
		case "adapter change":
			System.out.println("修改工艺：" + args[0] + "-" + args[1]);
			_change = new Intent(this, Activity_quotation_change.class);
			_change.putExtra("type", "change");
			_change.putExtra("room", _room);
			_change.putExtra("room_index", _number);
			_change.putExtra("part", args[0]);
			_change.putExtra("part_index", Integer.parseInt(args[1]));
			startActivity(_change);

			break;
		case "listview change":
			System.out.println("增减工艺：" + args[0]);
			_change = new Intent(this, Activity_quotation_change.class);
			_change.putExtra("type", "fix");
			_change.putExtra("room", _room);
			_change.putExtra("room_index", _number);
			_change.putExtra("part", args[0]);
			startActivity(_change);
			break;
		}
	}

	public static RoomDetail get_data()
	{
		return _data;
	}

	public static String get_quotationId()
	{
		return _quotationId;
	}

	public static String get_room()
	{
		return _room;
	}

	public static int get_number()
	{
		return _number;
	}

	public static Activity_quotation_detail get_instance()
	{
		return _instance;
	}
}
