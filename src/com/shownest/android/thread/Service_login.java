package com.shownest.android.thread;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.model.UserInfo;
import com.shownest.android.utils.DataUtil;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.utils.UserManager;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

/**
 * 自动登陆的服务
 * <p>
 * 此服务主要用于app账号的自动登陆业务，开启此服务之前先确认config文件中已有保存的ukey字段
 * 
 * @author bonepeople
 */
public class Service_login extends Service
{
	public static final int LOGIN_FAILED = 0;
	public static final int LOGIN_SUCCESSFUL = 1;
	public static final int GET_FAILED = 2;
	public static final int GET_SUCCESSFUL = 3;
	public static Service_login _instance;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case GET_FAILED:
			case LOGIN_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				_instance.stopSelf();
				break;
			case GET_SUCCESSFUL:
			case LOGIN_SUCCESSFUL:
				handle_string(msg.what, (String) msg.obj);
			}
		};
	};

	private static void handle_string(int _what, String _str)
	{
		System.out.println("Service_login-handle msg:");
		System.out.println(_str);
		try
		{
			JSONObject _obj = new JSONObject(_str);
			if (get_code(_obj))
				switch (_what)
				{
				case LOGIN_SUCCESSFUL:
					HttpUtil.get_userinfo(_handler, GET_SUCCESSFUL, GET_FAILED);
					break;
				case GET_SUCCESSFUL:
					JSONObject _data = _obj.getJSONArray("data").getJSONObject(0);
					UserManager.set_ukey(DataUtil.get_ukey(_instance));
					UserManager.set_user_info(new UserInfo(_data));
					_instance.stopSelf();
					break;
				}
			else
			{
				// 验证失败
				DataUtil.set_ukey(_instance, "");
				_instance.stopSelf();
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
			_instance.stopSelf();
		}
	}

	private static boolean get_code(JSONObject _obj) throws JSONException
	{
		int _code = 2;
		_code = _obj.getInt("state");
		return _code == 1 ? true : false;
	}

	@Override
	public void onCreate()
	{
		_instance = this;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		HttpUtil.user_checkUkey(_handler, DataUtil.get_ukey(this), LOGIN_SUCCESSFUL, LOGIN_FAILED);
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy()
	{
		_instance = null;
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onUnbind(Intent intent)
	{
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}
}
