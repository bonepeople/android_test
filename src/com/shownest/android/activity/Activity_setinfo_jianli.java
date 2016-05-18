package com.shownest.android.activity;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.fragment.Fragment_setinfo_jianli_step1;
import com.shownest.android.fragment.Fragment_setinfo_jianli_step2;
import com.shownest.android.fragment.Fragment_setinfo_jianli_step3;
import com.shownest.android.model.OnSelectListener;
import com.shownest.android.widget.Linearlayout_subtitle;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_setinfo_jianli extends DEBUG_Activity implements OnSelectListener
{
	public static final int CHANGE_FAILED = 0;
	public static final int CHANGE_SUCCESSFUL = 1;
	private static Activity_setinfo_jianli _instance;
	private Linearlayout_subtitle _subtitle;
	private ArrayList<DEBUG_Fragment> _array_fragment = new ArrayList<DEBUG_Fragment>(4);
	private int _selected = 1;

	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			String _string_result = "";
			switch (msg.what)
			{
			case CHANGE_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case CHANGE_SUCCESSFUL:
				_string_result = (String) msg.obj;
				handle_string(CHANGE_SUCCESSFUL, _string_result);
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
		_subtitle = (Linearlayout_subtitle) findViewById(R.id.linearlayout_subtitle);

		_subtitle.setOnSelectListener(this);
		_array_fragment.add(null);
		_array_fragment.add(null);
		_array_fragment.add(null);
		_array_fragment.add(null);
		_array_fragment.set(1, new Fragment_setinfo_jianli_step1());
		add_fragment(this, _array_fragment.get(1), false);
	}

	private static void handle_string(int _message, String _str)
	{
		handle_msg(_instance, _str);
		try
		{
			JSONObject _obj = new JSONObject(_str);
			String _result = _obj.getString("msg");

			if (_result.equals("提交成功"))
			{
				Toast.makeText(_instance, "提交成功", Toast.LENGTH_SHORT).show();
				_instance.finish();
			}
			else
				Toast.makeText(_instance, _result, Toast.LENGTH_SHORT).show();
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public static Activity_setinfo_jianli get_instance()
	{
		return _instance;
	}

	@Override
	public void onSelect(int _index)
	{
		FragmentManager _manager = getFragmentManager();
		FragmentTransaction _transaction = _manager.beginTransaction();

		_transaction.hide(_array_fragment.get(_selected));
		if (_index == 2 && _array_fragment.get(2) == null)
		{
			DEBUG_Fragment _temp_fragment = new Fragment_setinfo_jianli_step2();
			_array_fragment.set(2, _temp_fragment);
			add_fragment(this, _temp_fragment, false);
		}
		else if (_index == 3 && _array_fragment.get(3) == null)
		{
			DEBUG_Fragment _temp_fragment = new Fragment_setinfo_jianli_step3();
			_array_fragment.set(3, _temp_fragment);
			add_fragment(this, _temp_fragment, false);
		}
		else
			_transaction.show(_array_fragment.get(_index));
		_selected = _index;

		_transaction.commit();
	}
}
