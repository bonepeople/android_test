package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_card_add_step1;
import com.shownest.android.fragment.Fragment_card_add_step2;
import com.shownest.android.fragment.Fragment_card_add_step3;
import com.shownest.android.thread.Thread_time;
import com.shownest.android.utils.JsonUtil;

import android.app.Activity;
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
	private static Thread_time _timer = null;
	private static String _bank_name = "", _bank_type = "", _bank_number, _bank_logo, _phone;
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
				_fragment_step2.mobilcode_change();
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

		add_fragment(this, new Fragment_card_add_step1(), false);
		if (_timer != null)
			_timer.interrupt();
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
					_bank_name = _data.getString("bankName");
					_bank_type = _data.getString("cardType");
					_bank_logo = _data.getString("logoUrl");
					add_fragment(_instance, _fragment_step2 = new Fragment_card_add_step2(), true);
					break;
				case SEND_SUCCESSFUL:
					_timer = new Thread_time(_handler, BUTTON_CHANGE, 61, 1);
					_timer.start();
					Toast.makeText(_instance, "手机验证码发送成功", Toast.LENGTH_SHORT).show();
					break;
				case ADD_SUCCESSFUL:
					_instance.setResult(Activity.RESULT_OK);
					add_fragment(_instance, new Fragment_card_add_step3(), false);
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

	public static String get_phone()
	{
		return _phone;
	}

	public static void set_phone(String _phone)
	{
		Activity_card_add._phone = _phone;
	}

}
