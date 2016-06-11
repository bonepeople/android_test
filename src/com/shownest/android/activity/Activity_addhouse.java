package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_addhouse;
import com.shownest.android.utils.JsonUtil;

import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 添加房屋信息
 * 
 * @author bonepeople
 */
public class Activity_addhouse extends DEBUG_Activity
{
	public static final int ADD_FAILED = 0;
	public static final int ADD_SUCCESSFUL = 1;
	private static Activity_addhouse _instance;
	private static String _houseId = "";
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case ADD_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case ADD_SUCCESSFUL:
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
		_instance = this;
		_relativelayout_wait = (RelativeLayout) findViewById(R.id.relativelayout_wait);
		setTitle("添加房屋");

		add_fragment(this, new Fragment_addhouse(), false);
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
				case ADD_SUCCESSFUL:
					// 06-11 14:34:28.383: I/System.out(18358): {"state":"1","msg":"房屋信息操作成功","data":"137"}
					Toast.makeText(_instance, "房屋信息操作成功", Toast.LENGTH_SHORT).show();
					_instance.finish();
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

	public static String get_houseId()
	{
		return _houseId;
	}

	public static Activity_addhouse get_instance()
	{
		return _instance;
	}

}
