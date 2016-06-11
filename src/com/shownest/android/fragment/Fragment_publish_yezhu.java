package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_publish_yezhu extends DEBUG_Fragment implements OnClickListener
{
	private LinearLayout _body;
	private Button _button_commit;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("下一步");
		_button_commit.setOnClickListener(this);

		ImageView _image_title = new ImageView(getActivity());
		_image_title.setImageDrawable(getResources().getDrawable(R.drawable.book_house_1));
		_body.addView(_image_title);

		return _view;
	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == R.id.button_commit)
		{
			Toast.makeText(getActivity(), "next", Toast.LENGTH_SHORT).show();
		}
		// else if (_id == _region.get_id())
		// {
		// Intent _location = new Intent(getActivity(), Activity_location.class);
		// startActivityForResult(_location, LOCATION);
		// }
		// else if (_id == _house.get_id())
		// {
		// new AlertDialog_rooms(getActivity(), _house.getData(), this);
		// }
	}
}
