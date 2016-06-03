package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_bid_detail;
import com.shownest.android.activity.Activity_publish;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.utils.NumberUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fragment_publish_ok extends DEBUG_Fragment implements OnClickListener
{
	private LinearLayout _body;
	private Button _button_commit, _button_cancel;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("查看我的招标");
		_button_commit.setOnClickListener(this);
		_button_cancel = (Button) _view.findViewById(R.id.button_cancel);
		_button_cancel.setText("确定");
		_button_cancel.setVisibility(Button.VISIBLE);
		_button_cancel.setOnClickListener(this);

		return _view;
	}

	@Override
	public void setContent()
	{
		ImageView _title = new ImageView(getActivity());
		_title.setImageDrawable(getResources().getDrawable(R.drawable.bid_step1));
		_body.addView(_title);

		int padding;
		LinearLayout _hint = new LinearLayout(getActivity());
		_hint.setOrientation(LinearLayout.HORIZONTAL);
		_hint.setGravity(Gravity.CENTER);
		padding = NumberUtil.get_px(getActivity(), 15);
		_hint.setPadding(padding, padding, padding, padding);

		ImageView _img = new ImageView(getActivity());
		_img.setImageDrawable(getResources().getDrawable(R.drawable.icon_ok));
		padding = NumberUtil.get_px(getActivity(), 5);
		_img.setPadding(padding, padding, padding, padding);
		_hint.addView(_img);
		TextView _text = new TextView(getActivity());
		_text.setText("成功发布招标");
		_text.setTextColor(getResources().getColor(R.color.main_color));
		_text.setTextSize(20);
		_text.setPadding(padding, padding, padding, padding);
		_hint.addView(_text);

		_body.addView(_hint);

	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == R.id.button_commit)
		{
			Intent _intent = new Intent(Activity_publish.get_instance(), Activity_bid_detail.class);
			_intent.putExtra("bidID", Activity_publish.get_bidID());
			startActivity(_intent);
		}
		else
		{
		}
		Activity_publish.get_instance().finish();
	}
}
