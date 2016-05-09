package com.shownest.android.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
	public static void check_mobcode(Handler _handler, String _phone, String _mobilecode, int _successful, int _failed)
	{
		String _address = "http://t.shownest.com:86/webCheckMobCode";
		String _message = "";
		try
		{
			_message = "telNo=" + URLEncoder.encode(_phone, "UTF-8") + "&mobilecode=" + URLEncoder.encode(_mobilecode, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		new Thread_http(_handler, _address, _message, _successful, _failed).start();
	}
	
	public static void submit_reg(Handler _handler, String _username, String _mobilecode, String _password, int _successful, int _failed)
	{
		String _address = "http://t.shownest.com:86/websubmitreg";
		String _message = "";
		try
		{
			_message = "userName=" + URLEncoder.encode(_username, "UTF-8") + "&pwd=" + URLEncoder.encode(_password, "UTF-8") + "&repwd=" + URLEncoder.encode(_password, "UTF-8") + "&mobilecode="
					+ URLEncoder.encode(_mobilecode, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		System.out.println("regist message:" + _message);
		new Thread_http(_handler, _address, _message, _successful, _failed).start();
	}

	public static void send_mobilecode(Handler _handler, String _phone, int _successful, int _failed)
	{
		String _address = "http://t.shownest.com:86/websendmobilecode";
		String _message = "";
		try
		{
			_message = "telNo=" + URLEncoder.encode(_phone, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		new Thread_http(_handler, _address, _message, _successful, _failed).start();
	}

	public static void user_login(Handler _handler, String _username, String _password, int _successful, int _failed)
	{
		String _address = "http://t.shownest.com:86/webuserlogin";
		String _message = "";
		try
		{
			_message = "userName=" + URLEncoder.encode(_username, "UTF-8") + "&userPassword=" + URLEncoder.encode(_password, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		new Thread_http(_handler, _address, _message, _successful, _failed).start();
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
		String _address = "http://t.shownest.com:86/webcheckloginname";
		String _message = "";
		try
		{
			_message = "userName=" + URLEncoder.encode(_name, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		new Thread_http(_handler, _address, _message, _successful, _failed).start();
	}

	/**
	 * 向服务器发送POST请求并将返回值传递给activity
	 * 
	 * @param _handler
	 *            activity的handler
	 * @param _address
	 *            服务器地址
	 * @param _massage
	 *            form表单
	 * @param _successful
	 *            activity的成功响应值
	 * @param _failed
	 *            activity的失败响应值
	 */
	public static void send_post(Handler _handler, String _address, String _massage, int _successful, int _failed)
	{
		Message _msg = _handler.obtainMessage();
		_msg.what = _failed;
		_msg.obj = new String("Exception");
		URL _realUrl = null;
		HttpURLConnection _connection = null;
		FileInputStream _is = null;
		int _status = 0;

		try
		{
			_realUrl = new URL(_address);
			_connection = (HttpURLConnection) _realUrl.openConnection();
			_connection.setConnectTimeout(3000);
			_connection.setReadTimeout(3000);
			_connection.setRequestMethod("POST");
			_connection.setDoOutput(true);
			_connection.setRequestProperty("connection", "keep-alive");
			_connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			_connection.setRequestProperty("Cache-Control", "max-age=0");

			OutputStream _os = null;
			try
			{
				_connection.connect();
				_os = _connection.getOutputStream();
				_os.write(_massage.getBytes());
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
			if (_is != null)
				try
				{
					_is.close();
				}
				catch (IOException _e)
				{
				}
		}
	}

}
