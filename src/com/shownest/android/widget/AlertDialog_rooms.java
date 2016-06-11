package com.shownest.android.widget;

import com.shownest.android.R;
import com.shownest.android.model.OnChangeListener;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

/**
 * 户型结构对话框
 * 
 * @author bonepeople
 */
public class AlertDialog_rooms implements View.OnClickListener
{
	private OnChangeListener _listener;
	private AlertDialog _dialog;
	private NumberPicker _room, _parlour, _kitchen, _toilet, _balcony;

	/**
	 * 创建一个户型选择器
	 * 
	 * @param _data
	 *            当前户型：2，1，1，1，2
	 */
	public AlertDialog_rooms(Context context, String _data, OnChangeListener _listener)
	{
		this._listener = _listener;
		View _view = View.inflate(context, R.layout.dialog_house, null);
		AlertDialog.Builder _builder = new Builder(context);

		_dialog = _builder.create();
		_dialog.setView(_view, 0, 0, 0, 0);

		Button _button_commit = (Button) _view.findViewById(R.id.button_commit);

		_room = (NumberPicker) _view.findViewById(R.id.number_room);
		_parlour = (NumberPicker) _view.findViewById(R.id.number_parlour);
		_kitchen = (NumberPicker) _view.findViewById(R.id.number_kitchen);
		_toilet = (NumberPicker) _view.findViewById(R.id.number_toilet);
		_balcony = (NumberPicker) _view.findViewById(R.id.number_balcony);
		String[] _num = _data.split(",");

		_room.setMinValue(1);
		_room.setMaxValue(9);
		_room.setValue(Integer.parseInt(_num[0]));

		_parlour.setMinValue(1);
		_parlour.setMaxValue(9);
		_parlour.setValue(Integer.parseInt(_num[1]));

		_kitchen.setMinValue(1);
		_kitchen.setMaxValue(9);
		_kitchen.setValue(Integer.parseInt(_num[2]));

		_toilet.setMinValue(1);
		_toilet.setMaxValue(9);
		_toilet.setValue(Integer.parseInt(_num[3]));

		_balcony.setMinValue(1);
		_balcony.setMaxValue(9);
		_balcony.setValue(Integer.parseInt(_num[4]));

		_button_commit.setOnClickListener(this);
		_dialog.show();
	}

	public void setOnChangeListener(OnChangeListener _listener)
	{
		this._listener = _listener;
	}

	@Override
	public void onClick(View v)
	{
		String _result = _room.getValue() + "," + _parlour.getValue() + "," + _kitchen.getValue() + "," + _toilet.getValue() + "," + _balcony.getValue();
		if (_listener != null)
			_listener.onChange("house", new String[] { _result });
		_dialog.dismiss();
	}
}
