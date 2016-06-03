package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_publish;

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
			String _string_result = "";
			switch (msg.what)
			{
			case PUBLISH_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case PUBLISH_SUCCESSFUL:
				_string_result = (String) msg.obj;
				handle_string(_string_result);
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

	private static void handle_string(String str)
	{
		handle_msg(_instance, str);

		try
		{
			JSONObject _obj = new JSONObject(str);
			String _result = _obj.getString("msg");

			if (_result.equals("发布招标成功"))
			{
				_bidID = _obj.getString("data");
			}
			else
			{
				Toast.makeText(_instance, _result, Toast.LENGTH_SHORT).show();
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
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
