package com.shownest.android.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

/**
 * 图片工具类
 * 
 * @author bonepeople
 */
public class ImageUtil
{
	public static Bitmap getSmallBitmap(String _filePath, int _width, int _height)
	{
		final BitmapFactory.Options _options = new BitmapFactory.Options();
		_options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(_filePath, _options);
		// Calculate inSampleSize
		_options.inSampleSize = calculateInSampleSize(_options, _width, _height);

		// Decode bitmap with inSampleSize set
		_options.inJustDecodeBounds = false;

		return BitmapFactory.decodeFile(_filePath, _options);
	}

	private static int calculateInSampleSize(BitmapFactory.Options _options, int _reqWidth, int _reqHeight)
	{
		final int _height = _options.outHeight;
		final int _width = _options.outWidth;
		int _inSampleSize = 1;

		if (_height > _reqHeight || _width > _reqWidth)
		{
			final int _heightRatio = Math.round((float) _height / (float) _reqHeight);
			final int _widthRatio = Math.round((float) _width / (float) _reqWidth);
			_inSampleSize = _heightRatio < _widthRatio ? _heightRatio : _widthRatio;
		}
		return _inSampleSize;
	}

	/**
	 * 把bitmap通过Base64转换成String
	 */
	public static String bitmapToString(Bitmap _bitmap)
	{
		String _encode = "";
		ByteArrayOutputStream _out_stream = new ByteArrayOutputStream();
		_bitmap.compress(Bitmap.CompressFormat.PNG, 100, _out_stream);// 40是压缩值，100为不压缩
		byte[] _byte = _out_stream.toByteArray();
		try
		{
			_encode = URLEncoder.encode(Base64.encodeToString(_byte, Base64.DEFAULT), "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return _encode;
	}

	@Deprecated
	public static String bitmapToString(String _path)
	{
		String _encode = "";
		try
		{
			File _file = new File(_path);
			FileInputStream _fin = new FileInputStream(_file);
			byte[] buffer = new byte[(int) _file.length()];
			_fin.read(buffer);
			_encode = Base64.encodeToString(buffer, Base64.DEFAULT);
			_fin.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return _encode;
	}

	public void onDecodeClicked(String _str)
	{
		byte[] decode = Base64.decode(_str, Base64.DEFAULT);
		Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
		// save to image on sdcard
		saveBitmap(bitmap);
	}

	private void saveBitmap(Bitmap bitmap)
	{
		try
		{
			String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/shownest_cache/test_image.jpg";
			OutputStream stream = new FileOutputStream(path);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
			stream.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
