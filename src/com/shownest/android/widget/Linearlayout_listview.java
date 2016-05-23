package com.shownest.android.widget;

import com.shownest.android.R;
import com.shownest.android.model.OnChangeListener;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Linearlayout_listview extends LinearLayout implements View.OnClickListener, OnChangeListener
{
	private static boolean DEBUG = false;
	private ViewGroup _rootview;
	private LinearLayout _title;
	private TextView _text_name, _text_hint, _text_change, _body;
	private ImageView _image_flag;
	private boolean _collapse = false;

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
		_body = (TextView) _childview.findViewById(R.id.listview);

		_text_name.setText(args[0]);
		_text_hint.setText(args[1]);
		_title.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		if (_collapse)
			_body.setHeight(50);
		else
			_body.setHeight(0);
		_collapse = !_collapse;
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
			System.out.println(args[0] + "m2");
			break;

		case "style4":
			System.out.println("4444");
			break;
		}
	}
}
