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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Linearlayout_listview extends LinearLayout implements View.OnClickListener
{
	private static boolean DEBUG = false;
	private ViewGroup _rootview;
	private LinearLayout _title;
	private TextView _text_name, _text_hint, _text_change;
	private ListView _list;
	private ListAdapter _adapter;
	private ImageView _image_flag;
	private boolean _collapse = true;

	public Linearlayout_listview(Context context)
	{
		super(context);
	}

	public Linearlayout_listview(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public Linearlayout_listview(Context context, ViewGroup root, String[] args, ListAdapter _adapter)
	{
		super(context);
		if (DEBUG)
			System.out.println("Linearlayout_listview super");
		this._rootview = root;
		this._adapter = _adapter;
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
		_list.setAdapter(_adapter);

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

		}

	}

}
