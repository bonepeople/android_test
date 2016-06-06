package com.shownest.android.fragment;

import java.io.File;

import com.loopj.android.image.SmartImageView;
import com.shownest.android.R;
import com.shownest.android.activity.Activity_bid_detail;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.BidInfo;
import com.shownest.android.model.OnChangeListener;
import com.shownest.android.model.Package;
import com.shownest.android.utils.NumberUtil;
import com.shownest.android.widget.LinearLayout_picture;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;
import com.shownest.android.widget.View_split_h;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_bid_detail extends DEBUG_Fragment implements OnClickListener, OnChangeListener
{
	private LinearLayout _body;
	private Button _button_commit;
	private RelativeLayout_edit_informationbar _quotation;
	private LinearLayout_picture _picture;
	private Uri _image_uri;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("查看全部投标");
		_button_commit.setOnClickListener(this);

		return _view;
	}

	@Override
	public void setContent()
	{
		BidInfo _data = Activity_bid_detail.get_data();
		if (_data != null)
		{
			new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "小区名称", _data.get_areaName() }, false);
			new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "小区地址", _data.get_homeRegionName() }, false);
			new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "房屋类型", _data.get_houseType_name() }, false);
			new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "房屋状态", _data.get_houseState_name() }, false);
			new RelativeLayout_edit_informationbar(getActivity(), _body, 7, new String[] { "建筑面积", String.valueOf(_data.get_homeSq()), " m²" }, false);
			new RelativeLayout_edit_informationbar(getActivity(), _body, 4, new String[] { "户型结构", _data.get_rooms() }, false);
			_picture = new LinearLayout_picture(getActivity(), "户型图（选填）", false);
			_picture.setOnChangeListener(this);
			_picture.add_image("http://t.shownest.com:86/_resources/upload/headerIcon/6dfdd424ea49110400eba7dceb7.jpg");
			_picture.add_image("http://t.shownest.com:86/_resources/upload/headerIcon/6dfdd424ea49110400eba7dceb7.jpg");
			_picture.add_image("http://t.shownest.com:86/_resources/upload/headerIcon/6dfdd424ea49110400eba7dceb7.jpg");
			_body.addView(_picture);
			new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "业主称呼", _data.get_contacts() }, false);
			new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "联系方式", _data.get_phone() }, false);
			new View_split_h(getActivity(), _body, 5f);
			new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "招标类型", _data.get_bookType_name() }, false);
			new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "装修方式", _data.get_consType_name() }, false);
			new RelativeLayout_edit_informationbar(getActivity(), _body, 7, new String[] { "装修预算", String.valueOf(_data.get_constructerMoney()), "元" }, false);
			_quotation = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "业主智能报价单", "" }, true, this);
			new View_split_h(getActivity(), _body, 5f);
			new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "招标状态", _data.get_bidsStateCon_name() }, false);
			new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "发布时间", _data.get_createDateCon_name() }, false);
			new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "需求描述", "" }, false);

			TextView _idea = new TextView(getActivity());
			_idea.setText(_data.get_ownerIdea());
			int padding;
			padding = NumberUtil.get_px(getActivity(), 5);
			_idea.setPadding(padding, 0, padding, 0);
			_body.addView(_idea);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		System.out.println("requestCode=" + requestCode + "resultCode=" + resultCode);
		if (resultCode == -1)
		{
			if (requestCode == 2)
			{
				_image_uri = data.getData();
			}
			_picture.add_image(_image_uri);
		}
	}

	private void show_dialog()
	{
		final String[] _temp_str = new String[] { "拍摄照片", "选取照片", "取消" };

		AlertDialog.Builder _builder = new Builder(getActivity());
		_builder.setItems(_temp_str, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				switch (which)
				{
				case 0:
					try
					{
						Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
						intent.putExtra(MediaStore.EXTRA_OUTPUT, _image_uri);
						startActivityForResult(intent, 1);
					}
					catch (Exception e)
					{
						e.printStackTrace();
						Toast.makeText(getActivity(), "您的手机不具备拍照功能", Toast.LENGTH_SHORT).show();
					}
					break;
				case 1:
					try
					{
						Intent intent = new Intent();
						intent.setType("image/*");
						intent.setAction(Intent.ACTION_GET_CONTENT);
						startActivityForResult(intent, 2);// data.getExtras()
					}
					catch (ActivityNotFoundException e)
					{
						Toast.makeText(getActivity(), "您的手机不具备选择图片的功能", Toast.LENGTH_SHORT).show();
					}
					break;
				case 2:
					break;
				}
			}
		});
		_builder.show();
	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == _quotation.get_id())
		{
			Toast.makeText(getActivity(), "查看报价单", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(getActivity(), "commit", Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onChange(String tag, String[] args)
	{
		if (args != null)
		{
			// see
			Toast.makeText(getActivity(), tag + "-" + args[0], Toast.LENGTH_SHORT).show();
			int _id = Integer.parseInt(args[0]);
			Package _package = _picture.get_package(_id);

			if (_package._data1 instanceof SmartImageView)
			{
				// url
				String _url = _package._tag1;
				System.out.println("pic url =" + _url);
			}
			else
			{
				// uri
				Uri _uri = (Uri) _package._data2;
				System.out.println("pic uri =" + _uri);
			}
		}
		else
		{
			// add
			Toast.makeText(getActivity(), tag, Toast.LENGTH_SHORT).show();
			File _file_dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/shownest_cache");
			File _file;
			if (_file_dir.mkdirs() || _file_dir.isDirectory())
			{
				_file = new File(_file_dir, "left.tmp");
				_image_uri = Uri.fromFile(_file);
				show_dialog();
			}
			else
				Toast.makeText(getActivity(), "获取缓存失败", Toast.LENGTH_SHORT).show();
		}

	}
}
