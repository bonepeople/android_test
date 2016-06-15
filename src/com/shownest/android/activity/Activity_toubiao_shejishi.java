package com.shownest.android.activity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_webview;
import com.shownest.android.model.Template_shejishi;
import com.shownest.android.model.WebActionListener;
import com.shownest.android.utils.JsonUtil;
import com.shownest.android.utils.UserManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_toubiao_shejishi extends DEBUG_Activity implements WebActionListener
{
	public static final int GET_FAILED = 0;
	public static final int GET_SUCCESSFUL = 1;
	private static Activity_toubiao_shejishi _instance;
	private static Intent _intent;
	private static int _index = 0;
	private static ArrayList<Template_shejishi> _templates = new ArrayList<Template_shejishi>();
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case GET_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				_instance.finish();
				break;
			case GET_SUCCESSFUL:
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
		_relativelayout_wait = (RelativeLayout) findViewById(R.id.relativelayout_wait);
		_instance = this;
		_intent = getIntent();
		hideTitle(this);

		_index = _intent.getIntExtra("index", 0);
		System.out.println("_index :" + _index);

		String _bidID = Activity_AH_bid.get_data().get(_index).get_id();
		String _url = "http://app.shownest.com/bid/getDesiBidDetail?homeId=" + _bidID + "&ukey=" + UserManager.get_ukey();
		show_wait();
		add_fragment(this, new Fragment_webview(_url, "web", this), false);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == -1)
		{
			switch (requestCode)
			{
			case 1:

				break;

			case 2:

				break;
			}
		}
		else
			System.out.println("resultCode=" + resultCode);
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
					JSONArray _array = _obj.getJSONArray("data");
					_templates.clear();
					for (int _temp_i = 0; _temp_i < _array.length(); _temp_i++)
					{
						Template_shejishi _temp_template = new Template_shejishi(_array.getJSONObject(_temp_i));
						_templates.add(_temp_template);
					}

					// _data = new RoomDetail(_obj.getJSONObject("data"), _intent.getStringExtra("room"));
					// _fragment_detail = new Fragment_quotation_detail();
					// add_fragment(_instance, _fragment_detail, false);
					break;
				}
			else
			{
				Toast.makeText(_instance, JsonUtil.get_string(_obj, "msg", "连接服务器失败。"), Toast.LENGTH_SHORT).show();
				_instance.finish();
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
			_instance.finish();
		}
	}

	public static Activity_toubiao_shejishi get_instance()
	{
		return _instance;
	}

	@Override
	public void onFinished(String _tag)
	{
		this.close_wait();
	}

	@Override
	public void onClose(String _tag)
	{

	}

	@Override
	public void onAction(String _tag, String _action)
	{

	}

}
