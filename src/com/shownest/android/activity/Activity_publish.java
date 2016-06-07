package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_publish;
import com.shownest.android.fragment.Fragment_publish_ok;
import com.shownest.android.utils.JsonUtil;

import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 发布招标
 * 
 * @author bonepeople
 */
public class Activity_publish extends DEBUG_Activity
{
	public static final int PUBLISH_FAILED = 0;
	public static final int PUBLISH_SUCCESSFUL = 1;
	private static Activity_publish _instance;
	private static String _bidID = "";
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case PUBLISH_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case PUBLISH_SUCCESSFUL:
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
		setTitle("发布招标");

		add_fragment(this, new Fragment_publish(), false);
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
				case PUBLISH_SUCCESSFUL:
					_bidID = _obj.getString("data");
					Activity_offer_auto.get_instance().finish();
					add_fragment(_instance, new Fragment_publish_ok(), false);
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

	public static String get_bidID()
	{
		return _bidID;
	}

	public static Activity_publish get_instance()
	{
		return _instance;
	}
}
