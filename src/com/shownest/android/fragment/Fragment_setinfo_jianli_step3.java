package com.shownest.android.fragment;

import java.io.File;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_setinfo_jianli;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.UserInfo;
import com.shownest.android.utils.CommonUtil;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.utils.UserManager;
import com.shownest.android.widget.LinearLayout_idcard;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_setinfo_jianli_step3 extends DEBUG_Fragment implements OnClickListener
{
	private LinearLayout _body;
	private Button _button_commit;
	private RelativeLayout_edit_informationbar _type, _name, _id_number;
	private LinearLayout_idcard _idcard;
	private Uri[] _image_uri = new Uri[3];
	private int _image_where[] = new int[] { 0, 0, 0, 0 };// 前三个分别代表3个图片是否已选择，最后一个数字表示当前选择是的哪个图片

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_setinfo, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("申请认证");
		_button_commit.setOnClickListener(this);

		_type = new RelativeLayout_edit_informationbar(getActivity(), _body, 6, new String[] { "认证类型", "独立监理", "装修公司监理", "1" }, false);
		_name = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "真实姓名", "" }, true);
		_id_number = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "身份证号", "" }, true);

		_idcard = new LinearLayout_idcard(getActivity(), _body, "监理身份证", this);

		_type.setOnChangetListener(_idcard);
		return _view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		System.out.println("requestCode=" + requestCode + "resultCode=" + resultCode);
		if (resultCode == -1)
		{
			if (requestCode == 2)
			{
				_image_uri[_image_where[3]] = data.getData();
			}
			_idcard.setData(_image_uri[_image_where[3]], _image_where[3]);
			_image_where[_image_where[3]] = 1;
		}
	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == R.id.button_commit)
		{
			String _pic_name = "show" + String.valueOf(System.currentTimeMillis()) + String.valueOf(_image_where[3]) + String.valueOf((int) (Math.random() * 3000)) + ".jpg";
			System.out.println(_pic_name);

			String _str_id = _id_number.getData();
			int _str_type = _type.getData().equals("独立监理") ? 1 : 2;
			if (_str_type == 2 && _image_where[2] == 0)
			{
				Toast.makeText(getActivity(), "请选择公司名片或介绍信", Toast.LENGTH_SHORT).show();
			}
			else if (_image_where[0] == 0 || _image_where[1] == 0)
			{
				Toast.makeText(getActivity(), "请选择自己的身份证图片", Toast.LENGTH_SHORT).show();
			}
			else if (!CommonUtil.isIDNumber(_str_id))
			{
				Toast.makeText(getActivity(), "身份证号码格式不正确", Toast.LENGTH_SHORT).show();
			}
			else
			{
				ContentValues _value = new ContentValues();
				_value.put("authenticationType", _type.getData());
				_value.put("authenticationName", _name.getData());
				_value.put("authenticationCode", _str_id);
				_value.put("authenticationCardPicF", "正面路径");// 需要变更
				_value.put("authenticationCardPicB", "反面路径");// 需要变更

				Activity_setinfo_jianli.get_instance().show_wait();
				HttpUtil.set_PersonalProve(Activity_setinfo_jianli._handler, _value, Activity_setinfo_jianli.CHANGE_SUCCESSFUL, Activity_setinfo_jianli.CHANGE_FAILED);
			}
		}
		else if (_id == R.id.imageview_widget_left)
		{
			_image_where[3] = 0;
			File _file_dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/shownest_cache");
			File _file;
			if (_file_dir.mkdirs() || _file_dir.isDirectory())
			{
				_file = new File(_file_dir, "left.tmp");
				_image_uri[_image_where[3]] = Uri.fromFile(_file);
				show_dialog();
			}
			else
				Toast.makeText(getActivity(), "获取缓存失败", Toast.LENGTH_SHORT).show();
		}
		else if (_id == R.id.imageview_widget_right)
		{
			_image_where[3] = 1;
			File _file_dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/shownest_cache");
			File _file;
			if (_file_dir.mkdirs() || _file_dir.isDirectory())
			{
				_file = new File(_file_dir, "right.tmp");
				_image_uri[_image_where[3]] = Uri.fromFile(_file);
				show_dialog();
			}
			else
				Toast.makeText(getActivity(), "获取缓存失败", Toast.LENGTH_SHORT).show();
		}
		else if (_id == R.id.imageview_widget_bottom)
		{
			_image_where[3] = 2;
			File _file_dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/shownest_cache");
			File _file;
			if (_file_dir.mkdirs() || _file_dir.isDirectory())
			{
				_file = new File(_file_dir, "bottom.tmp");
				_image_uri[_image_where[3]] = Uri.fromFile(_file);
				show_dialog();
			}
			else
				Toast.makeText(getActivity(), "获取缓存失败", Toast.LENGTH_SHORT).show();
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
						intent.putExtra(MediaStore.EXTRA_OUTPUT, _image_uri[_image_where[3]]);
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
	public void setContent()
	{
		UserInfo _info = UserManager.get_user_info();
		if (_info != null)
		{
			_type.setData(new String[] { String.valueOf(_info.get_authenticationType()) });
			_name.setData(new String[] { _info.get_authenticationName() });
			_id_number.setData(new String[] { _info.get_authenticationCode() });
		}
	}
}
