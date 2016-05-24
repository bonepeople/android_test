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

	private int layoutHeight = -1;
	private int OPEN_LAYOUT_TIMES = 20;
	private int SLEEP_TIME = 15;
	private float layoutStep = 8;

	private OnExpandFinishListener onExpand = null;
	private OnCollapseFinishListener onCollapse = null;

	public interface OnExpandFinishListener
	{
		public void onExpandFinish();
	}

	public interface OnCollapseFinishListener
	{
		public void onCollapseFinish();
	}

	public void setOnExpandFinishListener(OnExpandFinishListener oeListener)
	{
		onExpand = oeListener;
	}

	public void setOnCollapseFinishListener(OnCollapseFinishListener ocListener)
	{
		onCollapse = ocListener;
	}

	public void setLayoutHeight(int height)
	{
		layoutHeight = height;
		layoutStep = 1.0f * layoutHeight / OPEN_LAYOUT_TIMES;
	}

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
			int totalHeight = 0;
			int adaptCount = _adapter.getCount();
			for (int i = 0; i < adaptCount; i++)
			{
				View temp = _adapter.getView(i, null, _list);
				temp.measure(0, 0);
				totalHeight += temp.getMeasuredHeight();
			}
			ViewGroup.LayoutParams params = _list.getLayoutParams();
			if (_collapse)
			{
				// 展开
				params.height = totalHeight + _list.getDividerHeight() * (_adapter.getCount() - 1);
				_list.setLayoutParams(params);
				_list.setVisibility(ListView.VISIBLE);
			}
			else
			{
				// 收起
				_list.setVisibility(ListView.GONE);
			}
			_collapse = !_collapse;

			break;

		case R.id.button_commit:
			// 可以在这里检测输入的合理性
			String _temp_str = _edittext_dialog.getText().toString();
			if (_temp_str.length() < 7)
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

		_button_commit.setOnClickListener(this);
		_button_cancel.setOnClickListener(this);
		_dialog.show();
	}

	public void collapse()
	{
		new Thread()
		{
			public void run()
			{
				for (int i = 0; i < OPEN_LAYOUT_TIMES + 1; i++)
				{
					final int ii = i;
					post(new Thread()
					{
						public void run()
						{
							LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, (int) ((OPEN_LAYOUT_TIMES - ii) * layoutStep));
							setLayoutParams(params);
							if (ii == OPEN_LAYOUT_TIMES)
							{
								setVisibility(View.GONE);
								if (null != onCollapse)
								{
									onCollapse.onCollapseFinish();
								}
							}
						}
					});

					try
					{
						Thread.sleep(SLEEP_TIME);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	public void expand()
	{
		if (layoutHeight <= 0)
		{
			return;
		}

		android.widget.LinearLayout.LayoutParams params = new android.widget.LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0);
		setLayoutParams(params);
		setVisibility(View.VISIBLE);

		new Thread()
		{
			public void run()
			{
				for (int i = 0; i < OPEN_LAYOUT_TIMES + 1; i++)
				{
					final int ii = i;
					post(new Thread()
					{
						public void run()
						{
							LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, (int) (ii * layoutStep));
							setLayoutParams(params);

							if (ii == OPEN_LAYOUT_TIMES)
							{
								if (null != onExpand)
								{
									onExpand.onExpandFinish();
								}
							}
						}
					});

					try
					{
						Thread.sleep(SLEEP_TIME);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		}.start();
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
			this.onClick(_title);
			break;
		}
	}
}
