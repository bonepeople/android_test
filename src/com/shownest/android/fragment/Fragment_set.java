package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_set;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.widget.InformationBar;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_set extends DEBUG_Fragment implements View.OnClickListener
{
	private LinearLayout _body;
	private Button _button_commit;
	private InformationBar _back, _about;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("退出登录");
		_button_commit.setOnClickListener(this);

		return _view;
	}

	@Override
	public void setContent()
	{
		_back = new InformationBar(getActivity(), _body, 2, new String[] { "意见反馈", "" }, true, this);
		_about = new InformationBar(getActivity(), _body, 2, new String[] { "关于秀巢", "" }, true, this);

	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		// Intent _web = new Intent(getActivity(), Activity_webview.class);
		// String _url;
		if (_id == R.id.button_commit)
		{
			Activity_set.get_instance().setResult(Activity.RESULT_OK);
			Activity_set.get_instance().finish();
		}
		else if (_id == _back.get_id())
		{
			Toast.makeText(getActivity(), "back", Toast.LENGTH_SHORT).show();
		}
		else if (_id == _about.get_id())
		{
			Toast.makeText(getActivity(), "about", Toast.LENGTH_SHORT).show();
		}
	}
}
