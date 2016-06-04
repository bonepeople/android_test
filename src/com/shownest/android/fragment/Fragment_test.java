package com.shownest.android.fragment;

import java.io.File;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.OnChangeListener;
import com.shownest.android.widget.LinearLayout_picture;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_test extends DEBUG_Fragment implements OnChangeListener
{
	private Uri _image_uri;
	private LinearLayout_picture _picture;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_test, container, false);

		LinearLayout _body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_picture = new LinearLayout_picture(getActivity(), "pictures");
		_picture.setOnChangeListener(this);

		_body.addView(_picture);
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
				_image_uri = data.getData();
			}
			else if(requestCode == 200)
			{
				Bitmap bmap = data.getParcelableExtra("data");  
                
                
				_picture.add_image(bmap);
				return;
			}
//			Intent intent = new Intent();  
//            
//            intent.setAction("com.android.camera.action.CROP");  
//            intent.setDataAndType(_image_uri, "image/*");// mUri是已经选择的图片Uri  
//            intent.putExtra("crop", "true");  
//            intent.putExtra("aspectX", 1);// 裁剪框比例  
//            intent.putExtra("aspectY", 1);  
//            intent.putExtra("outputX", 150);// 输出图片大小  
//            intent.putExtra("outputY", 150);  
//            intent.putExtra("return-data", true);  
//              
//            startActivityForResult(intent, 200); 
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
	public void onChange(String tag, String[] args)
	{
		if (args != null)
		{
			// see
			Toast.makeText(getActivity(), tag + "-" + args[0], Toast.LENGTH_SHORT).show();
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
