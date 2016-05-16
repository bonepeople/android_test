package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.widget.LinearLayout_style;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Fragment_basicinfo_yezhu extends DEBUG_Fragment
{
	private LinearLayout _body;
	private Button _button_commit;
	private RelativeLayout _relativelayout_wait;
	private RelativeLayout_edit_informationbar _role, _showname, _realname, _sex;
	LinearLayout_style _style;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_setinfo_yezhu, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_relativelayout_wait = (RelativeLayout) _view.findViewById(R.id.relativelayout_wait);

		_role = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "身份类型", "业主" }, false);
		_showname = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "秀巢昵称", "sn1234" }, true);
		_realname = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "真是姓名", "秀巢" }, true);
		_sex = new RelativeLayout_edit_informationbar(getActivity(), _body, 6, new String[] { "性别", "男", "女", "1" }, false);

		String[] _items = new String[] { "简约", "现代", "中式", "欧式", "美式", "日式", "东南亚", "地中海", "混搭", "新古典", "田园", "其他" };
		_style = new LinearLayout_style(getActivity(), "倾向风格(可多选)", _items, 12, new int[] {});
		_body.addView(_style);

		_button_commit.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String _string_showname = _showname.getData();
				String _string_realname = _realname.getData();
				String _string_sex = _sex.getData();
				String _string_style = _style.getData();

				Toast.makeText(getActivity(), _string_showname + _string_realname + _string_sex + _string_style, Toast.LENGTH_SHORT).show();

			}
		});

		return _view;
	}

	public void show_wait()
	{
		if (_relativelayout_wait != null && _relativelayout_wait.getVisibility() != RelativeLayout.VISIBLE)
			_relativelayout_wait.setVisibility(RelativeLayout.VISIBLE);
	}

	public void close_wait()
	{
		if (_relativelayout_wait != null && _relativelayout_wait.getVisibility() == RelativeLayout.VISIBLE)
			_relativelayout_wait.setVisibility(RelativeLayout.INVISIBLE);
	}
}
