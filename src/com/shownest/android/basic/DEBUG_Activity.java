package com.shownest.android.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.fragment.Fragment_title;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.RelativeLayout;

public abstract class DEBUG_Activity extends Activity
{
	private static boolean DEBUG = true;
	private static String TAG = "DEBUG_Activity";
	protected Fragment_title _fragment_title;
	protected RelativeLayout _relativelayout_wait;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		if (DEBUG)
			Log.v(TAG, this.getClass().getName() + "-onCreate");
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onStart()
	{
		if (DEBUG)
			Log.v(TAG, this.getClass().getName() + "-onStart");
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onResume()
	{
		if (DEBUG)
			Log.v(TAG, this.getClass().getName() + "-onResume");
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	protected void onPause()
	{
		if (DEBUG)
			Log.v(TAG, this.getClass().getName() + "-onPause");
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop()
	{
		if (DEBUG)
			Log.v(TAG, this.getClass().getName() + "-onStop");
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onRestart()
	{
		if (DEBUG)
			Log.v(TAG, this.getClass().getName() + "-onRestart");
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onDestroy()
	{
		if (DEBUG)
			Log.v(TAG, this.getClass().getName() + "onDestroy");
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	protected void setTitle(String _title)
	{
		if (_fragment_title == null)
			_fragment_title = (Fragment_title) getFragmentManager().findFragmentById(R.id.fragment_title);
		_fragment_title.setTitle(_title);
	}

	/**
	 * 设置标题栏右侧的文字
	 * <p>
	 * 调用此函数的Activity需要重写{@link #menu_click()}函数进行点击处理
	 */
	protected void setMenu(String _menu)
	{
		if (_fragment_title == null)
			_fragment_title = (Fragment_title) getFragmentManager().findFragmentById(R.id.fragment_title);
		_fragment_title.setMenu(_menu);
	}

	/**
	 * 设置标题栏右侧的图片
	 * <p>
	 * 调用此函数的Activity需要重写{@link #menu_click()}函数进行点击处理
	 */
	protected void setMenu(int _resid)
	{
		if (_fragment_title == null)
			_fragment_title = (Fragment_title) getFragmentManager().findFragmentById(R.id.fragment_title);
		_fragment_title.setMenu(_resid);

	}

	protected void hideTitle(DEBUG_Activity _activity)
	{
		if (_fragment_title == null)
			_fragment_title = (Fragment_title) getFragmentManager().findFragmentById(R.id.fragment_title);

		FragmentManager _manager = _activity.getFragmentManager();
		FragmentTransaction _transaction = _manager.beginTransaction();
		_transaction.hide(_fragment_title);
		_transaction.commit();
	}

	protected static void handle_msg(DEBUG_Activity _activity, String _msg)
	{
		if (DEBUG)
		{
			String _class = _activity.getClass().getName();
			System.out.println(_class + "-handle msg:");
			System.out.println(_msg);

			File _file_dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/shownest_cache");
			File _file;
			if (_file_dir.mkdirs() || _file_dir.isDirectory())
			{
				_file = new File(_file_dir, "out.txt");
				try
				{
					FileOutputStream _fout = new FileOutputStream(_file);
					byte[] bytes = _msg.getBytes();
					_fout.write(bytes);
					_fout.close();

				}
				catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	protected static boolean get_code(JSONObject _obj) throws JSONException
	{
		int _code = 2;
		_code = _obj.getInt("state");
		return _code == 1 ? true : false;
	}

	public static void add_fragment(DEBUG_Activity _activity, DEBUG_Fragment _fragment, boolean _back)
	{
		FragmentManager _manager = _activity.getFragmentManager();
		FragmentTransaction _transaction = _manager.beginTransaction();
		_transaction.add(R.id.framelayout_content, _fragment, null);
		if (_back)
			_transaction.addToBackStack(null);
		_transaction.commit();
	}

	public void show_wait()
	{
		if (_relativelayout_wait != null && _relativelayout_wait.getVisibility() != RelativeLayout.VISIBLE)
			_relativelayout_wait.setVisibility(RelativeLayout.VISIBLE);
	}

	public void close_wait()
	{
		if (_relativelayout_wait != null && _relativelayout_wait.getVisibility() == RelativeLayout.VISIBLE)
			_relativelayout_wait.setVisibility(RelativeLayout.INVISIBLE);
	}

	// protected abstract void back();
	/**
	 * 标题栏右侧按钮的点击事件回调
	 * <p>
	 * 当前activity标题栏右侧按钮被点击时会调用的函数，需要进行处理的activity要重写此函数
	 */
	public void menu_click()
	{
		// 当前activity标题栏右侧按钮被点击时会调用的函数，需要进行处理的activity要重写此函数
	}
}
