package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.UserInfo;
import com.shownest.android.utils.UserManager;
import com.shownest.android.widget.Linearlayout_edittext;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Fragment_publish_yezhu_setp2 extends DEBUG_Fragment implements OnClickListener
{
	private LinearLayout _body;
	private Button _button_commit;
	private RelativeLayout_edit_informationbar _type, _service, _cons, _money, _name, _phone;
	private Linearlayout_edittext _edit;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("发布招标");
		_button_commit.setOnClickListener(this);

		ImageView _image_title = new ImageView(getActivity());
		_image_title.setImageDrawable(getResources().getDrawable(R.drawable.book_house_2));
		_body.addView(_image_title);

		_type = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "选择招标类型", "设计标" }, true, this);
		_service = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "服务类型", "" }, true, this);
		_cons = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "装修方式", "半包" }, true, this);
		_cons.setVisibility(RelativeLayout.INVISIBLE);
		_money = new RelativeLayout_edit_informationbar(getActivity(), _body, 7, new String[] { "预算费用", "0.0", "元" }, true);
		_name = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "您的称呼", "" }, true);
		_phone = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "联系电话", "" }, true);

		return _view;
	}

	@Override
	public void setContent()
	{
		UserInfo _data = UserManager.get_user_info();
		if (_data != null)
		{
			_name.setData(new String[] { _data.get_userShowName() });
			_phone.setData(new String[] { _data.get_userPhone() });
		}
	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == R.id.button_commit)
		{
			Toast.makeText(getActivity(), "next", Toast.LENGTH_SHORT).show();
			if(_money.getData().equals("0.0"))
			{
				
			}
		}
		else if (_id == _type.get_id())
		{
			// show dialog
		}
		else if (_id == _service.get_id())
		{
			// show dialog
		}
		else if (_id == _cons.get_id())
		{
			// show dialog
		}
	}

}
