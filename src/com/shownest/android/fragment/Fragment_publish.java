package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.UserInfo;
import com.shownest.android.utils.NumberUtil;
import com.shownest.android.utils.UserManager;
import com.shownest.android.widget.Linearlayout_edittext;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_publish extends DEBUG_Fragment implements OnClickListener
{
	private LinearLayout _body;
	private Button _button_commit;
	private RelativeLayout_edit_informationbar _budget, _phone, _contacts;
	private Linearlayout_edittext _idea;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("发布招标");
		_button_commit.setOnClickListener(this);

		return _view;
	}

	@Override
	public void setContent()
	{
		UserInfo _info = UserManager.get_user_info();
		if (_info != null)
		{
			_budget = new RelativeLayout_edit_informationbar(getActivity(), _body, 7, new String[] { "装修预算", "15000.0", "元" }, true);
			_contacts = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "您的称呼", _info.get_userShowName() }, true);
			_phone = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "联系方式", _info.get_userPhone() }, true);
			_idea = new Linearlayout_edittext(getActivity(), _body, new String[] { "您的个人意见", "可以添加一些额外的要求", "" });

			int padding = NumberUtil.get_px(getActivity(), 5);
			TextView _hint = new TextView(getActivity());
			_hint.setText("    只有在卖家被您列为备选后，才可以查看您的联系方式，请您放心招标。");
			_hint.setTextColor(getResources().getColor(R.color.text_blue));
			_hint.setPadding(padding, padding, padding, padding);
			_body.addView(_hint);
		}
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), "发布", Toast.LENGTH_SHORT).show();
	}
}
