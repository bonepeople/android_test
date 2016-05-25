package com.shownest.android.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.utils.HttpUtil;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_quotation extends DEBUG_Activity
{
	public static final int GET_FAILED = 0;
	public static final int GET_SUCCESSFUL = 1;
	public static final int SEND_FAILED = 2;
	public static final int SEND_SUCCESSFUL = 3;
	public static final int CHECK_FAILED = 4;
	public static final int CHECK_SUCCESSFUL = 5;
	private static Activity_quotation _instance;
	private static Intent _intent;
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
		setTitle("加入秀巢网");

		String _type = _intent.getStringExtra("part");
		int _id = _intent.getIntExtra("id", 1);
		int _number = _intent.getIntExtra("index", 1);

		System.out.println("select from :" + _type + "-" + _number);

		show_wait();
		ContentValues _value = new ContentValues();
		_value.put("quotationId", _id);
		_value.put("room", _number);
		HttpUtil.get_quotation_room(_handler, _value, GET_SUCCESSFUL, GET_FAILED);
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

			if (_result.equals("用户名不存在"))
			{
			}
			else if (_result.equals("注册成功"))
			{
				Toast.makeText(_instance, _result, Toast.LENGTH_SHORT).show();
				_instance.finish();
			}
			else if (_result.equals("手机验证码发送成功"))
			{
			}
			else
				Toast.makeText(_instance, _result, Toast.LENGTH_SHORT).show();
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public static Activity_quotation get_instance()
	{
		return _instance;
	}
}
