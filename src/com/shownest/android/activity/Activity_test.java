package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_test;
import com.shownest.android.model.UserInfo;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

public class Activity_test extends DEBUG_Activity
{
	private Intent _intent;
	private AlertDialog _dialog;
	private NumberPicker _room, _parlour, _kitchen, _toilet, _balcony;
	public static final int TEST_FAILED = 0;
	public static final int TEST_SUCCESSFUL = 1;
	private static Activity_test _instance;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			String _string_result = "";
			switch (msg.what)
			{
			case TEST_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				_string_result = (String) msg.obj;
				break;
			case TEST_SUCCESSFUL:
				_string_result = (String) msg.obj;
				handle_string(_string_result);
			}
			System.out.println(_string_result);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
		_instance = this;
		setTitle("测试");
		_intent = getIntent();

		add_fragment(this, new Fragment_test(), false);
	}

	private static void handle_string(String str)
	{
		handle_msg(_instance, str);
		try
		{
			JSONObject _obj = new JSONObject(str);
			String _result = _obj.getString("msg");
			Toast.makeText(_instance, _result, Toast.LENGTH_SHORT).show();

			JSONObject _data = _obj.getJSONArray("data").getJSONObject(0);
			UserInfo _info = new UserInfo(_data);
			System.out.println(_info.toString());
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public void test(View v)
	{
		String _str = "result";
		show_dialog();
	}

	private void show_dialog()
	{
		View _view = View.inflate(this, R.layout.dialog_house, null);
		AlertDialog.Builder _builder = new Builder(this);
		_dialog = _builder.create();
		_dialog.setView(_view, 0, 0, 0, 0);

		Button _button_commit = (Button) _view.findViewById(R.id.button_commit);

		_room = (NumberPicker) _view.findViewById(R.id.number_room);
		_parlour = (NumberPicker) _view.findViewById(R.id.number_parlour);
		_kitchen = (NumberPicker) _view.findViewById(R.id.number_kitchen);
		_toilet = (NumberPicker) _view.findViewById(R.id.number_toilet);
		_balcony = (NumberPicker) _view.findViewById(R.id.number_balcony);

		_room.setMinValue(1);
		_room.setMaxValue(9);

		_parlour.setMinValue(1);
		_parlour.setMaxValue(9);

		_kitchen.setMinValue(1);
		_kitchen.setMaxValue(9);

		_toilet.setMinValue(1);
		_toilet.setMaxValue(9);

		_balcony.setMinValue(1);
		_balcony.setMaxValue(9);

		_button_commit.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				String _result = _room.getValue() + "," + _parlour.getValue() + "," + _kitchen.getValue() + "," + _toilet.getValue() + "," + _balcony.getValue();
				Toast.makeText(Activity_test.this, _result, Toast.LENGTH_SHORT).show();
			}
		});
		_dialog.show();
	}

	public static Activity_test get_instance()
	{
		return _instance;
	}
}
