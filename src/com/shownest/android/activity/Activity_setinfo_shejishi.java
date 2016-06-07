package com.shownest.android.activity;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.fragment.Fragment_setinfo_shejishi_step1;
import com.shownest.android.fragment.Fragment_setinfo_shejishi_step2;
import com.shownest.android.fragment.Fragment_setinfo_shejishi_step3;
import com.shownest.android.model.OnChangeListener;
import com.shownest.android.utils.JsonUtil;
import com.shownest.android.widget.Linearlayout_subtitle;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_setinfo_shejishi extends DEBUG_Activity implements OnChangeListener
{
	public static final int CHANGE_FAILED = 0;
	public static final int CHANGE_SUCCESSFUL = 1;
	private static Activity_setinfo_shejishi _instance;
	private Linearlayout_subtitle _subtitle;
	private ArrayList<DEBUG_Fragment> _array_fragment = new ArrayList<DEBUG_Fragment>(4);
	private int _selected = 1;

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
		setContentView(R.layout.activity_page);
		_instance = this;
		_relativelayout_wait = (RelativeLayout) findViewById(R.id.relativelayout_wait);
		setTitle("身份认证");
		_subtitle = (Linearlayout_subtitle) findViewById(R.id.linearlayout_subtitle);

		_subtitle.setOnChangeListener(this);
		_array_fragment.add(null);
		_array_fragment.add(null);
		_array_fragment.add(null);
		_array_fragment.add(null);
		_array_fragment.set(1, new Fragment_setinfo_shejishi_step1());
		add_fragment(this, _array_fragment.get(1), false);
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
					Toast.makeText(_instance, "提交成功", Toast.LENGTH_SHORT).show();
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

	public static Activity_setinfo_shejishi get_instance()
	{
		return _instance;
	}

	@Override
	public void onChange(String tag, String[] args)
	{
		int _index = Integer.parseInt(args[0]);
		FragmentManager _manager = getFragmentManager();
		FragmentTransaction _transaction = _manager.beginTransaction();

		_transaction.hide(_array_fragment.get(_selected));
		if (_index == 2 && _array_fragment.get(2) == null)
		{
			DEBUG_Fragment _temp_fragment = new Fragment_setinfo_shejishi_step2();
			_array_fragment.set(2, _temp_fragment);
			add_fragment(this, _temp_fragment, false);
		}
		else if (_index == 3 && _array_fragment.get(3) == null)
		{
			DEBUG_Fragment _temp_fragment = new Fragment_setinfo_shejishi_step3();
			_array_fragment.set(3, _temp_fragment);
			add_fragment(this, _temp_fragment, false);
		}
		else
			_transaction.show(_array_fragment.get(_index));
		_selected = _index;

		_transaction.commit();
	}
}
