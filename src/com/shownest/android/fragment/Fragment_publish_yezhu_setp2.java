package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.UserInfo;
import com.shownest.android.utils.UserManager;
import com.shownest.android.widget.Linearlayout_edittext;
import com.shownest.android.widget.InformationBar;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_publish_yezhu_setp2 extends DEBUG_Fragment implements OnClickListener
{
	private LinearLayout _body;
	private Button _button_commit;
	private InformationBar _type, _service, _cons, _money, _name, _phone;
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

		_type = new InformationBar(getActivity(), _body, 2, new String[] { "选择招标类型", "设计标" }, true, this);
		_service = new InformationBar(getActivity(), _body, 2, new String[] { "服务类型", "设计图服务" }, true, this);
		_cons = new InformationBar(getActivity(), _body, 2, new String[] { "装修方式", "半包" }, true, this);
		_cons.setVisibility(View.GONE);
		_money = new InformationBar(getActivity(), _body, 7, new String[] { "预算费用", "0.0", "元" }, true);
		_name = new InformationBar(getActivity(), _body, 5, new String[] { "您的称呼", "" }, true);
		_phone = new InformationBar(getActivity(), _body, 5, new String[] { "联系电话", "" }, true);
		_edit = new Linearlayout_edittext(getActivity(), _body, new String[] { "描述需求", "说说你的装修要求", "" });
		
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
			if (_money.getData().equals("0.0"))
			{

			}
		}
		else if (_id == _type.get_id())
		{
			String[] _types = new String[] { "设计标", "施工标", "监理标" };
			AlertDialog.Builder _builder = new Builder(getActivity());
			_builder.setTitle("招标类型");
			_builder.setItems(_types, new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					switch (which)
					{
					case 0:
						_service.setVisibility(View.VISIBLE);
						_cons.setVisibility(View.GONE);
						_type.setData(new String[] { "设计标" });
						break;
					case 1:
						_service.setVisibility(View.GONE);
						_cons.setVisibility(View.VISIBLE);
						_type.setData(new String[] { "施工标" });
						break;
					case 2:
						_service.setVisibility(View.GONE);
						_cons.setVisibility(View.GONE);
						_type.setData(new String[] { "监理标" });
						break;
					}
				}
			});
			_builder.show();
		}
		else if (_id == _service.get_id())
		{
			String[] _types = new String[] { "设计图服务", "硬装全程设计服务", "硬装软装全程设计服务" };
			AlertDialog.Builder _builder = new Builder(getActivity());
			_builder.setTitle("服务类型");
			_builder.setItems(_types, new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					switch (which)
					{
					case 0:
						_service.setData(new String[] { "设计图服务" });
						break;
					case 1:
						_service.setData(new String[] { "硬装全程设计服务" });
						break;
					case 2:
						_service.setData(new String[] { "硬装软装全程设计服务" });
						break;
					}
				}
			});
			_builder.show();
		}
		else if (_id == _cons.get_id())
		{
			String[] _types = new String[] { "半包", "全包", "清包" };
			AlertDialog.Builder _builder = new Builder(getActivity());
			_builder.setTitle("装修方式");
			_builder.setItems(_types, new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					switch (which)
					{
					case 0:
						_cons.setData(new String[] { "半包" });
						break;
					case 1:
						_cons.setData(new String[] { "全包" });
						break;
					case 2:
						_cons.setData(new String[] { "清包" });
						break;
					}
				}
			});
			_builder.show();
		}
	}

}
