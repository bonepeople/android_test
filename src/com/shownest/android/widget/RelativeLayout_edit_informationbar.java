package com.shownest.android.widget;

import com.shownest.android.R;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
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
	private boolean _clickable;
	private RadioButton _radiobutton_left, _radiobutton_right;
	private AlertDialog _dialog;
	EditText _edittext_dialog;

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
	 * @param root
	 *            父容器
	 * @param style
	 *            类型（1-6）
	 * @param name
	 *            信息条内容 1-3,2-2,3-3,4-6,5-2,6-4
	 * 
	 */
	public RelativeLayout_edit_informationbar(Context context, ViewGroup root, int style, String[] args, boolean _clickable)
	{
		super(context);
		if (DEBUG)
			System.out.println("relativelayout super:" + style);
		this._context = context;
		this._style = style;
		this._rootview = root;
		this._clickable = _clickable;
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
		case 5:
			if (args.length != 2)
			{
				System.out.println("relativelayout super:5-" + args.toString());
				break;
			}
			_view = (ViewGroup) View.inflate(this._context, R.layout.widget_edit_informationbar_style5, this._rootview);
			_childview = _view.getChildAt(_view.getChildCount() - 1);

			_textview_name = (TextView) _childview.findViewById(R.id.textview_widget_name);
			_textview_right = (TextView) _childview.findViewById(R.id.textview_widget_right);

			_textview_name.setText(args[0]);
			_textview_right.setText(args[1]);
			break;
		case 6:
			if (args.length != 4)
			{
				System.out.println("relativelayout super:6-" + args.toString());
				break;
			}
			_view = (ViewGroup) View.inflate(this._context, R.layout.widget_edit_informationbar_style6, this._rootview);
			_childview = _view.getChildAt(_view.getChildCount() - 1);

			_textview_name = (TextView) _childview.findViewById(R.id.textview_widget_name);
			_radiobutton_left = (RadioButton) _childview.findViewById(R.id.radiobutton_widget_left);
			_radiobutton_right = (RadioButton) _childview.findViewById(R.id.radiobutton_widget_right);

			_textview_name.setText(args[0]);
			_radiobutton_left.setText(args[1]);
			_radiobutton_right.setText(args[2]);
			if (Integer.parseInt(args[3]) == 1)
			{
				_radiobutton_left.setChecked(true);
			}
			else
			{
				_radiobutton_right.setChecked(true);
			}

			break;
		}
		if (this._style != 6)
			if (!this._clickable)
			{
				_textview_right.setTextColor(getResources().getColor(R.color.text_gray));
				if (_textview_left != null)
					_textview_left.setTextColor(getResources().getColor(R.color.text_gray));
			}
			else if (_childview != null)
				_childview.setOnClickListener(this);
	}

	public void setData(String[] args)
	{
		switch (this._style)
		{
		case 1:
			_textview_left.setText(args[0]);
			_textview_right.setText(args[1]);
			break;
		case 2:
			_textview_right.setText(args[0]);
			break;
		case 3:
			_textview_left.setText(args[0]);
			break;
		case 4:
			_textview_right.setText(args[0] + "室" + args[1] + "厅" + args[2] + "厨" + args[3] + "卫" + args[4] + "阳台");
			break;
		case 5:
			_textview_right.setText(args[0]);
			break;
		case 6:
			if (Integer.parseInt(args[0]) == 1)
				_radiobutton_left.setChecked(true);
			else
				_radiobutton_right.setChecked(true);
			break;
		}

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
		case 5:
			_data = this._textview_right.getText().toString();
			break;
		case 6:
			_data = _radiobutton_left.isChecked() ? _radiobutton_left.getText().toString() : _radiobutton_right.getText().toString();
			_radiobutton_right.setChecked(true);
			break;
		}
		return _data;
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.button_commit:
			setData(new String[] { _edittext_dialog.getText().toString() });
			_dialog.dismiss();
			break;

		case R.id.button_cancel:
			_dialog.dismiss();
			break;

		default:
			switch (this._style)
			{
			case 1:
				Toast.makeText(_context, "跳转", Toast.LENGTH_SHORT).show();
				break;
			case 2:
				Toast.makeText(_context, "跳转", Toast.LENGTH_SHORT).show();
				break;
			case 3:
				show_dialog(getData());
				break;
			case 4:
				Toast.makeText(_context, "显示滚轮", Toast.LENGTH_SHORT).show();
				break;
			case 5:
				show_dialog(getData());
				break;
			}
		}

		// Toast.makeText(_context, "clicked", Toast.LENGTH_SHORT).show();
	}

	private void show_dialog(String _value)
	{
		View _view = View.inflate(_context, R.layout.dialog_edit, null);
		AlertDialog.Builder _builder = new Builder(_context);
		_dialog = _builder.create();
		_dialog.setView(_view, 0, 0, 0, 0);

		Button _button_commit = (Button) _view.findViewById(R.id.button_commit);
		Button _button_cancel = (Button) _view.findViewById(R.id.button_cancel);
		_edittext_dialog = (EditText) _view.findViewById(R.id.edittext_dialog);
		_edittext_dialog.setText(_value);

		_button_commit.setOnClickListener(this);
		_button_cancel.setOnClickListener(this);
		_dialog.show();
	}

}
