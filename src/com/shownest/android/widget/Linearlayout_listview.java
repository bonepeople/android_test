package com.shownest.android.widget;

import com.shownest.android.R;
import com.shownest.android.adapter.Adapter_offer_auto;
import com.shownest.android.model.OnChangeListener;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Linearlayout_listview extends LinearLayout implements View.OnClickListener, OnChangeListener
{
	private static boolean DEBUG = false;
	private ViewGroup _rootview;
	private LinearLayout _title;
	private TextView _text_name, _text_hint, _text_change;
	private ListView _list;
	public Adapter_offer_auto _adapter;
	private ImageView _image_flag;
	private AlertDialog _dialog;
	private EditText _edittext_dialog;
	private int _selected = 0;
	private boolean _collapse = true;

	public Linearlayout_listview(Context context)
	{
		super(context);
	}

	public Linearlayout_listview(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public Linearlayout_listview(Context context, ViewGroup root, String[] args)
	{
		super(context);
		if (DEBUG)
			System.out.println("Linearlayout_listview super");
		this._rootview = root;
		setContentView(args);
	}

	private void setContentView(String[] args)
	{
		ViewGroup _view;
		View _childview = null;
		_view = (ViewGroup) View.inflate(getContext(), R.layout.widget_listview, this._rootview);
		_childview = _view.getChildAt(_view.getChildCount() - 1);
		_title = (LinearLayout) _childview.findViewById(R.id.linearlayout_title);
		_text_name = (TextView) _childview.findViewById(R.id.textview_name);
		_text_hint = (TextView) _childview.findViewById(R.id.textview_hint);
		_text_change = (TextView) _childview.findViewById(R.id.textview_change);
		_image_flag = (ImageView) _childview.findViewById(R.id.imageview_more);
		_list = (ListView) _childview.findViewById(R.id.listview_widget);

		_text_name.setText(args[0]);
		_text_hint.setText(args[1]);
		_adapter = new Adapter_offer_auto(getContext());
		_list.setAdapter(_adapter);
		_list.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				_selected = position;
				TextView _number = (TextView) view.findViewById(R.id.textview_widget_left);
				show_dialog(_number.getText().toString());
			}
		});

		_title.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.linearlayout_title:
			if (_collapse)
			{
				// 展开
				_list.setVisibility(ListView.VISIBLE);
				_image_flag.setImageResource(R.drawable.arrow_up);
			}
			else
			{
				// 收起
				_list.setVisibility(ListView.GONE);
				_image_flag.setImageResource(R.drawable.arrow_down);
			}
			_collapse = !_collapse;
			break;
			
		case R.id.button_commit:
			// 可以在这里检测输入的合理性
			String _temp_str = _edittext_dialog.getText().toString();
			if (_temp_str.length() < 7 && _temp_str.length() > 0)
				_adapter.set_acreage(_temp_str, _selected);
			_dialog.dismiss();
			break;
		case R.id.button_cancel:
			_dialog.dismiss();
			break;
		}

	}

	private void show_dialog(String _value)
	{
		View _view = View.inflate(getContext(), R.layout.dialog_edit, null);
		AlertDialog.Builder _builder = new Builder(getContext());
		_dialog = _builder.create();
		_dialog.setView(_view, 0, 0, 0, 0);

		Button _button_commit = (Button) _view.findViewById(R.id.button_commit);
		Button _button_cancel = (Button) _view.findViewById(R.id.button_cancel);
		_edittext_dialog = (EditText) _view.findViewById(R.id.edittext_dialog);
		_edittext_dialog.setText(_value);
		_edittext_dialog.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
		_edittext_dialog.selectAll();
		_button_commit.setOnClickListener(this);
		_button_cancel.setOnClickListener(this);
		_dialog.show();

		Window window = _dialog.getWindow();
		android.view.WindowManager.LayoutParams params = window.getAttributes();
		params.softInputMode = android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE;// 显示dialog的时候,就显示软键盘
		params.flags = android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND;// 就是这个属性导致不能获取焦点,默认的是FLAG_NOT_FOCUSABLE,故名思义不能获取输入焦点,
		window.setAttributes(params);
	}

	@Override
	public void onChange(String tag, String[] args)
	{
		switch (tag)
		{
		case "style3":
			_adapter.set_area(Float.parseFloat(args[0]));
			break;

		case "style4":
			_adapter.set_num(args[0]);
			break;
		}
	}
}
