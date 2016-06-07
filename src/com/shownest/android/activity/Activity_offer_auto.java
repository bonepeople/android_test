package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_offer_auto_set;
import com.shownest.android.fragment.Fragment_offer_auto_show;
import com.shownest.android.model.OfferBill;
import com.shownest.android.utils.JsonUtil;

import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 智能报价
 * 
 * @author bonepeople
 */
public class Activity_offer_auto extends DEBUG_Activity
{
	public static final int NEXT_FAILED = 0;
	public static final int NEXT_SUCCESSFUL = 1;
	public static final int SAVE_FAILED = 2;
	public static final int SAVE_SUCCESSFUL = 3;
	private static Activity_offer_auto _instance;
	private static OfferBill _data;// 需要一个标记，代表目前是否正在更新
	private static String _quotationId = "";

	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case SAVE_FAILED:
			case NEXT_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case SAVE_SUCCESSFUL:
			case NEXT_SUCCESSFUL:
				handle_string(msg.what, (String) msg.obj);
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
		setTitle("智能报价");

		add_fragment(this, new Fragment_offer_auto_set(), false);
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
				case NEXT_SUCCESSFUL:
					_data = new OfferBill(_obj.getJSONObject("data"));
					FragmentManager _manager = _instance.getFragmentManager();
					int _count = _manager.getBackStackEntryCount();
					if (_count != 0)
						_manager.popBackStack();
					else
						_quotationId = "";
					add_fragment(_instance, new Fragment_offer_auto_show(), true);
					break;
				case SAVE_SUCCESSFUL:
					String _data = JsonUtil.get_string(_obj, "data", "0-0");
					String[] _ids = _data.split("-");
					Toast.makeText(_instance, "保存成功", Toast.LENGTH_SHORT).show();
					Activity_offer_auto._data.set_quotationId(_ids[0]);
					_quotationId = _ids[0];
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

	public static OfferBill get_data()
	{
		return _data;
	}

	public static String get_quotationId()
	{
		return _quotationId;
	}

	public static Activity_offer_auto get_instance()
	{
		return _instance;
	}

}
