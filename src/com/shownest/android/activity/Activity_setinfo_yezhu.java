package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_setinfo_yezhu;
import com.shownest.android.utils.JsonUtil;

import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 身份认证-业主
 * 
 * @author bonepeople
 */
public class Activity_setinfo_yezhu extends DEBUG_Activity
{
	public static final int CHANGE_FAILED = 0;
	public static final int CHANGE_SUCCESSFUL = 1;
	private static Activity_setinfo_yezhu _instance;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case CHANGE_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case CHANGE_SUCCESSFUL:
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
		setTitle("身份认证");

		add_fragment(this, new Fragment_setinfo_yezhu(), false);
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
				case CHANGE_SUCCESSFUL:
					Toast.makeText(_instance, "修改基本信息成功", Toast.LENGTH_SHORT).show();
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

	public static Activity_setinfo_yezhu get_instance()
	{
		return _instance;
	}
}
