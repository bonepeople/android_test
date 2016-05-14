package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.widget.LinearLayout_style;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class Fragment_basicinfo_yezhu extends DEBUG_Fragment
{
	private LinearLayout _body;
	private Button _button_commit;
	private RelativeLayout_edit_informationbar _role, _showname, _realname, _sex;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_setinfo_yezhu, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);

		_role = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "身份类型", "业主" }, false);
		_showname = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "秀巢昵称", "sn1234" }, true);
		_realname = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "真是姓名", "秀巢" }, true);
		_sex = new RelativeLayout_edit_informationbar(getActivity(), _body, 6, new String[] { "性别", "男", "女", "1" }, false);

		String[] _items = new String[] { "简约", "现代", "中式", "欧式", "美式", "日式", "东南亚", "地中海", "混搭", "新古典", "田园", "其他" };
		LinearLayout_style _choose = new LinearLayout_style(getActivity(), "倾向风格(可多选)", _items, 12, new int[] {});
		_body.addView(_choose);

		return _view;
	}

	@Override
	protected String get_class()
	{
		return this.getClass().toString();
	}

}
