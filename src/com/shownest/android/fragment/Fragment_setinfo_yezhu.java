package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_my_center;
import com.shownest.android.activity.Activity_setinfo_yezhu;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.UserInfo;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.widget.LinearLayout_checkbox;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_setinfo_yezhu extends DEBUG_Fragment
{
	private LinearLayout _body;
	private Button _button_commit;
	private RelativeLayout_edit_informationbar _showname, _realname, _sex;
	private LinearLayout_checkbox _style;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_setinfo, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("完成认证");

		new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "身份类型", "业主" }, false);
		_showname = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "秀巢昵称", "" }, true);
		_realname = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "真是姓名", "" }, true);
		_sex = new RelativeLayout_edit_informationbar(getActivity(), _body, 6, new String[] { "性别", "男", "女", "1" }, false);

		String[] _items = new String[] { "简约", "现代", "中式", "欧式", "美式", "日式", "东南亚", "地中海", "混搭", "新古典", "田园", "其他" };
		_style = new LinearLayout_checkbox(getActivity(), "倾向风格(可多选)", _items, 12, "");
		_body.addView(_style);

		_button_commit.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String _string_showname = _showname.getData();
				String _string_realname = _realname.getData();
				String _string_sex = _sex.getData().equals("男") ? "1" : "0";
				String _string_style = _style.getData();

				if (_string_showname.length() < 4 || _string_showname.length() > 20)
				{
					Toast.makeText(getActivity(), "显示昵称长度为4-20位", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(getActivity(), _string_showname + _string_realname + _string_sex + _string_style, Toast.LENGTH_SHORT).show();
					Activity_setinfo_yezhu.get_instance().show_wait();
					HttpUtil.change_bsaeinfo(Activity_setinfo_yezhu._handler, _string_showname, _string_realname, _string_sex, _string_style, Activity_setinfo_yezhu.CHANGE_SUCCESSFUL,
							Activity_setinfo_yezhu.CHANGE_FAILED);
				}
			}
		});
		return _view;
	}

	@Override
	public void setContent()
	{
		UserInfo _info = Activity_my_center.get_userinfo();
		if (_info != null)
		{
			_showname.setData(new String[] { _info.get_userShowName() });
			_realname.setData(new String[] { _info.get_realName() });
			_sex.setData(new String[] { _info.get_realSex() == 1 ? "1" : "2" });
			_style.setData(_info.get_likeStyle());
		}
	}
}
