package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_card_add_step1;
import com.shownest.android.fragment.Fragment_card_add_step2;
import com.shownest.android.utils.JsonUtil;

import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 添加银行卡
 * 
 * @author bonepeople
 */
public class Activity_card_add extends DEBUG_Activity
{
	public static final int ADD_FAILED = 0;
	public static final int ADD_SUCCESSFUL = 1;
	public static final int GET_FAILED = 2;
	public static final int GET_SUCCESSFUL = 3;
	public static final int SEND_FAILED = 4;
	public static final int SEND_SUCCESSFUL = 5;
	public static final int BUTTON_CHANGE = 6;
	private static Activity_card_add _instance;
	private static Fragment_card_add_step2 _fragment_step2;
	private static String _bank_name, _bank_type, _bank_number, _bank_logo;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case SEND_FAILED:
			case GET_FAILED:
			case ADD_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case SEND_SUCCESSFUL:
			case GET_SUCCESSFUL:
			case ADD_SUCCESSFUL:
				handle_string(msg.what, (String) msg.obj);
				break;
			case BUTTON_CHANGE:
				// _fragment_regist.mobilcode_change();
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
		setTitle("添加银行卡");

//		add_fragment(this, new Fragment_card_add_step1(), false);
		add_fragment(this, new Fragment_card_add_step2(), false);
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
					JSONObject _data = _obj.getJSONObject("data");
					String _result = _data.getString("reason");
					if (_result.equals("查询成功"))
					{
						_bank_name = _data.getJSONObject("result").getString("bank");
						_bank_type = _data.getJSONObject("result").getString("nature");
						_bank_logo = _data.getJSONObject("result").getString("logo");
						// 添加第二个界面
						add_fragment(_instance, _fragment_step2 = new Fragment_card_add_step2(), true);
					}
					else
					{
						Toast.makeText(_instance, _result, Toast.LENGTH_SHORT).show();
					}
					break;
				case SEND_SUCCESSFUL:
					// 06-11 14:34:28.383: I/System.out(18358): {"state":"1","msg":"房屋信息操作成功","data":"137"}
					Toast.makeText(_instance, "房屋添加成功", Toast.LENGTH_SHORT).show();
					break;
				case ADD_SUCCESSFUL:
					// 06-11 14:34:28.383: I/System.out(18358): {"state":"1","msg":"房屋信息操作成功","data":"137"}
					Toast.makeText(_instance, "房屋添加成功", Toast.LENGTH_SHORT).show();
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

	public static Activity_card_add get_instance()
	{
		return _instance;
	}

	public static String get_bank_number()
	{
		return _bank_number;
	}

	public static void set_bank_number(String _bank_number)
	{
		Activity_card_add._bank_number = _bank_number;
	}

	public static String get_bank_name()
	{
		return _bank_name;
	}

	public static String get_bank_type()
	{
		return _bank_type;
	}

	public static String get_bank_logo()
	{
		return _bank_logo;
	}

}
