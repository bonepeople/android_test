package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_offer_auto;
import com.shownest.android.fragment.Fragment_offer_auto_show;
import com.shownest.android.model.OfferBill;
import com.shownest.android.model.UserInfo;

import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_offer_auto extends DEBUG_Activity
{
	public static final int NEXT_FAILED = 0;
	public static final int NEXT_SUCCESSFUL = 1;
	private static Activity_offer_auto _instance;
	private static OfferBill _data;// 需要一个标记，代表目前是否正在更新

	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			String _string_result = "";
			switch (msg.what)
			{
			case NEXT_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case NEXT_SUCCESSFUL:
				_string_result = (String) msg.obj;
				handle_string(_string_result);
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
		_relativelayout_wait = (RelativeLayout) findViewById(R.id.relativelayout_wait);
		setTitle("智能报价");

		add_fragment(this, new Fragment_offer_auto(), false);
	}

	private static void handle_string(String str)
	{
		handle_msg(_instance, str);
		try
		{
			JSONObject _obj = new JSONObject(str);
			String _result = _obj.getString("msg");
			Toast.makeText(_instance, _result, Toast.LENGTH_SHORT).show();
			if (_result.equals("获取结果"))
			{
				_data = new OfferBill(_obj.getJSONObject("data"));
				add_fragment(_instance, new Fragment_offer_auto_show(), true);
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public static OfferBill get_data()
	{
		return _data;
	}

	public static Activity_offer_auto get_instance()
	{
		return _instance;
	}
}
