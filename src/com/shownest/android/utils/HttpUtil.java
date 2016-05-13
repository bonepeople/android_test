package com.shownest.android.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import com.shownest.android.thread.Thread_http;

import android.os.Handler;
import android.os.Message;

/**
 * 联网相关的工具类
 * 
 * @author bonepeople
 *
 */
public class HttpUtil
{
	private static boolean DEBUG = true;
	private static String SESSION = null; // 定义一个静态的字段，保存sessionID
	private static String BASEADDRESS = "http://t.shownest.com:86/";

	// http://192.168.1.112:10000/shownest/html/test1.html
	// http://192.168.1.112:10000/shownest/websubmitreg
	/**
	 * 设置用户类型
	 * 
	 * @param _handler
	 * @param _type
	 *            11: owner 业主 12: designer 设计师 13: constructor 施工队 14: supervisionner 监理 15: businesser 装修公司 100:未认证
	 * @param _successful
	 * @param _failed
	 */
	public static void set_UserType(Handler _handler, int _type, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "webSetUseType";
		String _message = "";

		String _userType = "userType=" + _type;

		_message = _userType;

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	public static void modify_Phone(Handler _handler, String _phone, String _code, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "webSetModifyOaPhone";
		String _message = "";

		String _telNo = "telNo=" + _phone;
		String _checkCode = "checkCode=" + _code;

		_message = _telNo + "&" + _checkCode;

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	public static void set_pwd(Handler _handler, String _oldPwd, String _password, String _repassword, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "webSetPwd";
		String _message = "";

		String _currentPwd = "currentPwd=" + _oldPwd;
		String _pwd = "pwd=" + _password;
		String _repwd = "repwd=" + _repassword;

		_message = _currentPwd + "&" + _pwd + "&" + _repwd;

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	public static void forget_pwd(Handler _handler, String _phone, String _code, String _password, String _repassword, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "webCheckMobCode";
		String _message = "";

		String _telNo = "telNo=" + _phone;
		String _mobilecode = "mobilecode=" + _code;
		String _pwd = "pwd=" + _password;
		String _repwd = "repwd=" + _repassword;

		_message = _telNo + "&" + _mobilecode + "&" + _pwd + "&" + _repwd;

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	public static void check_mobcode(Handler _handler, String _phone, String _mobilecode, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "webCheckMobCode";
		String _message = "";

		_message = "telNo=" + _phone + "&mobilecode=" + _mobilecode;

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	public static void submit_reg(Handler _handler, String _username, String _mobilecode, String _password, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "websubmitreg";
		String _message = "";

		_message = "userName=" + _username + "&pwd=" + _password + "&repwd=" + _password + "&mobilecode=" + _mobilecode;

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	public static void send_mobilecode(Handler _handler, String _phone, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "websendmobilecode";
		String _message = "";

		_message = "telNo=" + _phone;

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	/**
	 * 获取登录用户基本信息
	 */
	public static void get_userinfo(Handler _handler, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "getuserinfo";
		String _message = "";

		new Thread_http(_handler, _address, _message, _successful, _failed, "GET").start();
	}

	/**
	 * 用户登录的接口
	 * 
	 * @param _username
	 *            用户名
	 * @param _password
	 *            密码
	 */
	public static void user_login(Handler _handler, String _username, String _password, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "webuserlogin";
		String _message = "";

		_message = "userName=" + _username + "&userPassword=" + _password;

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	/**
	 * ukey用户登录获取SESSION
	 * 
	 * @param _ukey
	 */
	public static void user_checkUkey(Handler _handler, String _ukey, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "getUserCheckUkey";
		String _message = "";

		_message = "ukey=" + _ukey;

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	/**
	 * 连接服务器检查所给用户名是否已注册
	 * 
	 * @param _handler
	 *            接收消息的handler
	 * @param _name
	 *            电话号码
	 */
	public static void check_loginname(Handler _handler, String _name, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "webcheckloginname";
		String _message = "";

		_message = "userName=" + _name;

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	/**
	 * 向服务器发送POST请求并将返回值传递给activity
	 * 
	 * @param _handler
	 *            activity的handler
	 * @param _address
	 *            服务器地址
	 * @param _message
	 *            form表单
	 * @param _successful
	 *            activity的成功响应值
	 * @param _failed
	 *            activity的失败响应值
	 */
	public static void send_http(Handler _handler, String _address, String _message, int _successful, int _failed, String _method)
	{
		if (DEBUG)
			System.out.println(_method + " message:" + _message);

		Message _msg = _handler.obtainMessage();
		_msg.what = _failed;
		_msg.obj = new String("Exception");
		URL _realUrl = null;
		HttpURLConnection _connection = null;
		int _status = 0;

		try
		{
			_realUrl = new URL(_address);
			_connection = (HttpURLConnection) _realUrl.openConnection();
			_connection.setConnectTimeout(3000);
			_connection.setReadTimeout(3000);
			_connection.setRequestMethod(_method);
			if (_method.equals("POST"))
			{
				_connection.setDoOutput(true);
			}
			_connection.setRequestProperty("connection", "keep-alive");
			_connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			_connection.setRequestProperty("Cache-Control", "max-age=0");
			if (SESSION != null)
				_connection.setRequestProperty("Cookie", SESSION);
			// JSESSIONID=CB8A56EF79CC3A06C1A1EB4EF24415B8

			OutputStream _os = null;
			try
			{
				_connection.connect();
				if (_method.equals("POST"))
				{
					_os = _connection.getOutputStream();
					_os.write(_message.getBytes());
				}

			}
			catch (Exception _e)
			{
				throw _e;
			}
			finally
			{
				try
				{
					if (_os != null)
					{
						_os.flush();
						_os.close();
					}
				}
				catch (Exception _e)
				{
				}
			}

			_status = _connection.getResponseCode();
			if (_status == 200)
			{
				if (SESSION == null)
				{
					String _cookie = _connection.getHeaderField("Set-Cookie");
					SESSION = _cookie.split(";")[0];
					// Set-Cookie:JSESSIONID=59D090155623FADDE01819851B437C0F; Path=/shownest/; HttpOnly
				}

				BufferedReader _in = new BufferedReader(new InputStreamReader(_connection.getInputStream()));
				String _result = "";
				String _line;
				while ((_line = _in.readLine()) != null)
				{
					_result += _line;
				}
				_msg.what = _successful;
				_msg.obj = _result;
			}
			else
			{
				BufferedReader _in_err = new BufferedReader(new InputStreamReader(_connection.getErrorStream()));
				String _result_err = "";
				String _line_err;
				while ((_line_err = _in_err.readLine()) != null)
				{
					_result_err += _line_err;
				}
				_msg.what = _failed;
				_msg.obj = _result_err;
			}
		}
		catch (MalformedURLException __e3)
		{
			// from _realUrl = new URL(_address);
			__e3.printStackTrace();
		}
		catch (ProtocolException __e4)
		{
			// from _connection.setRequestMethod("POST");
			__e4.printStackTrace();
		}
		catch (IOException __e5)
		{
			// from _connection = (HttpURLConnection)_realUrl.openConnection();
			// from _in = new BufferedReader(new InputStreamReader(_connection.getInputStream()));
			__e5.printStackTrace();
		}
		catch (Exception __e)
		{
			System.out.println("other exception catched");
			__e.printStackTrace();
		}
		finally
		{
			_handler.sendMessage(_msg);
			if (_connection != null)
				_connection.disconnect();
		}
	}

	private static String encode(String _str)
	{
		String _result = "";
		try
		{
			_result = URLEncoder.encode(_str, "UTF-8");
		}
		catch (Exception e)
		{
		}
		return _result;
	}
}
