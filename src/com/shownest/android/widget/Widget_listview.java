package com.shownest.android.widget;

import com.shownest.android.R;
import com.shownest.android.model.OnChangeListener;
import com.shownest.android.utils.NumberUtil;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 带标题，可点击可收缩的ListView控件
 * 
 * @author bonepeople
 */
public class Widget_listview implements View.OnClickListener
{
	private static boolean DEBUG = false;
	private Context _context;
	private ViewGroup _rootview;
	private String _tag;
	private LinearLayout _title;
	private TextView _text_name, _text_hint, _text_change;
	private Listview_maxheight _list;
	private ListAdapter _adapter;
	private ImageView _image_flag;
	private OnChangeListener _listener;
	private boolean _enabled = true;
	private boolean _collapse = true;// 折叠:true折叠状态，false展开状态

	/**
	 * 初始化一个可收缩的ListView控件
	 * 
	 * @param root
	 *            父容器(必需)
	 * @param args
	 *            控件的标题：args[0] 左侧的名字，args[1] 名字右侧的提示
	 * @param _adapter
	 *            ListView的 ListAdapter
	 */
	public Widget_listview(Context context, ViewGroup root, String[] args, ListAdapter _adapter)
	{
		if (DEBUG)
			System.out.println("Linearlayout_listview super");
		this._context = context;
		this._rootview = root;
		this._adapter = _adapter;
		setContentView(args);
	}

	private void setContentView(String[] args)
	{
		ViewGroup _view;
		View _childview = null;
		_view = (ViewGroup) View.inflate(_context, R.layout.widget_listview, this._rootview);
		_childview = _view.getChildAt(_view.getChildCount() - 1);
		_title = (LinearLayout) _childview.findViewById(R.id.linearlayout_title);
		_text_name = (TextView) _childview.findViewById(R.id.textview_name);
		_text_hint = (TextView) _childview.findViewById(R.id.textview_hint);
		_text_change = (TextView) _childview.findViewById(R.id.textview_change);
		_image_flag = (ImageView) _childview.findViewById(R.id.imageview_more);
		_list = (Listview_maxheight) _childview.findViewById(R.id.listview_widget);

		_text_name.setText(args[0]);
		_text_hint.setText(args[1]);
		_list.setAdapter(_adapter);
		_list.setDivider(new ColorDrawable(_context.getResources().getColor(R.color.background_main)));

		_title.setOnClickListener(this);
	}

	/**
	 * 设置标题栏中的文字
	 * 
	 * @param _name
	 *            all,name,hint,change
	 * @param _text
	 *            要改变的文字
	 */
	public void set_text(String _name, String _text)
	{
		switch (_name)
		{
		case "all":
			if (_text_hint != null)
				_text_hint.setText(_text);
			if (_text_change != null)
				_text_change.setText(_text);
		case "name":
			if (_text_name != null)
				_text_name.setText(_text);
			break;

		case "hint":
			if (_text_hint != null)
				_text_hint.setText(_text);
			break;

		case "change":
			if (_text_change != null)
				_text_change.setText(_text);
			break;
		}
	}

	/**
	 * 设置右侧文字的点击事件及名称
	 * 
	 * @param _change
	 *            右侧可点击文字的名字
	 * @param _tag
	 *            用于回调时的标签
	 * @param _listener
	 *            监听器
	 */
	public void set_change(String _change, String _tag, OnChangeListener _listener)
	{
		_text_change.setText(_change);
		set_change(_tag, _listener);
	}

	/**
	 * 设置右侧文字的点击事件,使用此函数请先调用 {@link #set_text(String, String)} 设置可点击文字的名字，默认为空
	 * 
	 * @param _tag
	 *            用于回调时的标签
	 * @param _listener
	 *            监听器
	 */
	public void set_change(String _tag, OnChangeListener _listener)
	{
		_text_change.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		_text_change.setVisibility(TextView.VISIBLE);
		_text_change.setOnClickListener(this);
		this._tag = _tag;
		this._listener = _listener;
	}

	/**
	 * 修改文本状态条中文本的颜色
	 * 
	 * @param _name
	 *            all,name,hint,change
	 * @param _color
	 *            getResources().getColor(R.color.text_gray)
	 */
	public void set_textcolor(String _name, int _color)
	{
		switch (_name)
		{
		case "all":
			if (_text_hint != null)
				_text_hint.setTextColor(_color);
			if (_text_change != null)
				_text_change.setTextColor(_color);
		case "name":
			if (_text_name != null)
				_text_name.setTextColor(_color);
			break;

		case "hint":
			if (_text_hint != null)
				_text_hint.setTextColor(_color);
			break;

		case "change":
			if (_text_change != null)
				_text_change.setTextColor(_color);
			break;
		}
	}

	/**
	 * 设置背景颜色
	 * 
	 * @param _color
	 *            getResources().getColor(R.color.text_gray)
	 */
	public void set_backgroundColor(int _color)
	{
		_title.setBackgroundColor(_color);
	}

	/**
	 * 设置ListView中的间距(px)
	 */
	public void set_dividerheight(int _height)
	{
		if (_list != null)
			_list.setDividerHeight(_height);
	}

	/**
	 * 设置ListView中的间距(dp)
	 */
	public void set_dividerheight(float _height)
	{
		if (_list != null)
			_list.setDividerHeight(NumberUtil.get_px(_context, _height));
	}

	/**
	 * 设置ListView间距的颜色
	 * <p>
	 * 不推荐使用此函数，使用此函数会导致ListView的间距变回0px，若要使用请在设置完颜色后调用 {@link #set_dividerheight(int)}或{@link #set_dividerheight(float)} 进行间距的设置
	 * 
	 * @param _color
	 *            getResources().getColor(R.color.text_gray)
	 */
	@Deprecated
	public void set_dividercolor(String _color)
	{
		_list.setDivider(new ColorDrawable(Color.parseColor(_color)));
	}

	/**
	 * 设置ListView是否收起
	 * 
	 * @param _collapse
	 *            <b>true</b>-收起ListView，<b>false</b>-展开ListView
	 */
	public void set_collapse(boolean _collapse)
	{
		if (_collapse)
		{
			// 收起
			_list.setVisibility(ListView.GONE);
			_image_flag.setImageResource(R.drawable.arrow_down);
		}
		else
		{
			// 展开
			_list.setVisibility(ListView.VISIBLE);
			_image_flag.setImageResource(R.drawable.arrow_up);
		}
		this._collapse = _collapse;
	}

	/**
	 * 设置折叠功能是否可用
	 */
	public void set_enabled(boolean _enabled)
	{
		this._enabled = _enabled;
		if (_enabled)
		{
			_image_flag.setVisibility(ImageView.VISIBLE);
		}
		else
		{
			_image_flag.setVisibility(ImageView.INVISIBLE);
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.linearlayout_title:
			if (_enabled)
				set_collapse(!_collapse);
			break;
		case R.id.textview_change:
			if (_listener != null)
				_listener.onChange(_tag, null);
			break;

		}

	}

}
