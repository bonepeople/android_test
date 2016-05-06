package com.shownest.android.widget;

import com.shownest.android.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RelativeLayout_edit_informationbar extends RelativeLayout implements View.OnClickListener
{
	private static boolean DEBUG = true;
	private Context _context;
	private int _style;
	private ViewGroup _rootview;
	private TextView _textview_name, _textview_left, _textview_right;
	private ImageView _imageview_right;

	public RelativeLayout_edit_informationbar(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
	}

	public RelativeLayout_edit_informationbar(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public RelativeLayout_edit_informationbar(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 通过代码生成一个信息条控件
	 * 
	 * @param context
	 * @param root
	 *            父容器
	 * @param style
	 *            类型（1-4）
	 * @param name
	 *            信息条内容
	 * 
	 */
	public RelativeLayout_edit_informationbar(Context context, ViewGroup root, int style, String[] args)
	{
		super(context);
		if (DEBUG)
			System.out.println("relativelayout super:" + style);
		this._context = context;
		this._style = style;
		this._rootview = root;
		setContentView(args);

	}

	private void setContentView(String[] args)
	{
		ViewGroup _view;
		View _childview = null;
		switch (this._style)
		{
		case 1:
			if (args.length != 3)
			{
				System.out.println("relativelayout super:1-" + args.toString());
				break;
			}

			_view = (ViewGroup) View.inflate(this._context, R.layout.widget_edit_informationbar_style1, this._rootview);
			_childview = _view.getChildAt(_view.getChildCount() - 1);

			_textview_name = (TextView) _childview.findViewById(R.id.textview_widget_name);
			_textview_left = (TextView) _childview.findViewById(R.id.textview_widget_left);
			_textview_right = (TextView) _childview.findViewById(R.id.textview_widget_right);
			_imageview_right = (ImageView) _childview.findViewById(R.id.imageview_widget_right);

			_textview_name.setText(args[0]);
			_textview_left.setText(args[1]);
			_textview_right.setText(args[2]);
			break;
		case 2:
			if (args.length != 2)
			{
				System.out.println("relativelayout super:2-" + args.toString());
				break;
			}
			_view = (ViewGroup) View.inflate(this._context, R.layout.widget_edit_informationbar_style2, this._rootview);
			_childview = _view.getChildAt(_view.getChildCount() - 1);

			_textview_name = (TextView) _childview.findViewById(R.id.textview_widget_name);
			_textview_right = (TextView) _childview.findViewById(R.id.textview_widget_right);
			_imageview_right = (ImageView) _childview.findViewById(R.id.imageview_widget_right);

			_textview_name.setText(args[0]);
			_textview_right.setText(args[1]);
			break;
		case 3:
			if (args.length != 3)
			{
				System.out.println("relativelayout super:3-" + args.toString());
				break;
			}
			_view = (ViewGroup) View.inflate(this._context, R.layout.widget_edit_informationbar_style3, this._rootview);
			_childview = _view.getChildAt(_view.getChildCount() - 1);

			_textview_name = (TextView) _childview.findViewById(R.id.textview_widget_name);
			_textview_left = (TextView) _childview.findViewById(R.id.textview_widget_left);
			_textview_right = (TextView) _childview.findViewById(R.id.textview_widget_right);

			_textview_name.setText(args[0]);
			_textview_left.setText(args[1]);
			_textview_right.setText(args[2]);
			break;
		case 4:
			if (args.length != 6)
			{
				System.out.println("relativelayout super:4-" + args.toString());
				break;
			}
			_view = (ViewGroup) View.inflate(this._context, R.layout.widget_edit_informationbar_style4, this._rootview);
			_childview = _view.getChildAt(_view.getChildCount() - 1);

			_textview_name = (TextView) _childview.findViewById(R.id.textview_widget_name);
			_textview_right = (TextView) _childview.findViewById(R.id.textview_widget_right);

			_textview_name.setText(args[0]);
			_textview_right.setText(args[1] + "室" + args[2] + "厅" + args[3] + "厨" + args[4] + "卫" + args[5] + "阳台");
			break;
		}
		if (_childview != null)
			_childview.setOnClickListener(this);
	}

	public String getData()
	{
		String _data = "";
		switch (this._style)
		{
		case 1:
			_data = this._textview_left.getText().toString() + this._textview_right.getText().toString();
			break;
		case 2:
			_data = this._textview_right.getText().toString();
			break;
		case 3:
			_data = this._textview_left.getText().toString();
			break;
		case 4:
			_data = this._textview_right.getText().toString();
			break;

		}
		return _data;
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		 Toast.makeText(_context, this.getData(), Toast.LENGTH_SHORT).show();
//		Toast.makeText(_context, "clicked", Toast.LENGTH_SHORT).show();
	}

}
