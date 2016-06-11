package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_basicinfo;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.utils.JsonUtil;

import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 个人资料
 * 
 * @author bonepeople
 */
public class Activity_basicinfo extends DEBUG_Activity
{
	public static final int UPLOAD_FAILED = 0;
	public static final int UPLOAD_SUCCESSFUL = 1;
	public static final int CHANGE_FAILED = 2;
	public static final int CHANGE_SUCCESSFUL = 3;
	private static Activity_basicinfo _instance;
	private static String _image_name = "";
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case UPLOAD_FAILED:
			case CHANGE_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				_instance.close_wait();
				break;
			case UPLOAD_SUCCESSFUL:
			case CHANGE_SUCCESSFUL:
				handle_string(msg.what, (String) msg.obj);
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
		_relativelayout_wait = (RelativeLayout) findViewById(R.id.relativelayout_wait);
		_instance = this;
		setTitle("个人资料");

		add_fragment(this, new Fragment_basicinfo(), false);
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
				case UPLOAD_SUCCESSFUL:
					ContentValues _value = new ContentValues();
					_value.put("HeaderIconUrl", _image_name);
					HttpUtil.set_headericon(_handler, _value, CHANGE_SUCCESSFUL, CHANGE_FAILED);
					break;
				case CHANGE_SUCCESSFUL:
					Toast.makeText(_instance, "头像修改成功，请退出刷新", Toast.LENGTH_SHORT).show();
					_instance.close_wait();
					_instance.finish();
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

	public static String get_image_name()
	{
		return _image_name;
	}

	public static void set_image_name(String _image_name)
	{
		Activity_basicinfo._image_name = _image_name;
	}

	public static Activity_basicinfo get_instance()
	{
		return _instance;
	}
}
