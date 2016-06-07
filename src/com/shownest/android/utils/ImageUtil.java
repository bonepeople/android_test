package com.shownest.android.utils;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
		ByteArrayOutputStream _out_stream = new ByteArrayOutputStream();
		_bitmap.compress(Bitmap.CompressFormat.JPEG, 40, _out_stream);// 40是压缩值，100为不压缩
		byte[] _byte = _out_stream.toByteArray();
		return Base64.encodeToString(_byte, Base64.DEFAULT);
	}
}
