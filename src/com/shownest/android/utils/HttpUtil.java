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
import java.util.Map.Entry;

import com.shownest.android.thread.Thread_http;

import android.content.ContentValues;
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
	// private static String BASEADDRESS = "http://192.168.1.109:10000/shownest/";

	// http://192.168.1.112:10000/shownest/html/test1.html
	// http://192.168.1.112:10000/shownest/websubmitreg

	public static void get_bid(Handler _handler, ContentValues _value, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "/bid/webGetRespBid";
		String _message = "";

		_message = values(_value);

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	/**
	 * 发布招标
	 */
	public static void publish_bid(Handler _handler, ContentValues _value, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "webOwnerPublishBid";
		String _message = "";

		_message = values(_value);

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	/**
	 * 保存当前报价单
	 */
	public static void save_quotation(Handler _handler, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "webOwnerSaveQuotation";
		String _message = "";

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	/**
	 * 提交数据
	 * <p>
	 * 报价单详细项目的增加，修改，删除
	 * 
	 * @param _item
	 *            room,parlour,kitchen,toilet,balcony,hydropower,mount,cost,tax
	 */
	public static void update_quotation_item(Handler _handler, String _item, ContentValues _value, int _successful, int _failed)
	{
		String _address = "", _message;
		switch (_item)
		{
		case "room":
		case "parlour":
		case "kitchen":
		case "toilet":
		case "balcony":
			_address = BASEADDRESS + "webOwnerUpdateQuotationItem";
			break;
		case "hydropower":
			_address = BASEADDRESS + "webOwnerUpdateQuotationHydropower";
			break;
		case "mount":
			_address = BASEADDRESS + "webOwnerUpdateQuotationMount";
			break;
		case "cost":
		case "tax":
			_address = BASEADDRESS + "webOwnerUpdateQuotationAssemble";
			break;

		}
		_message = values(_value);

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	/**
	 * 查询报价单各个项目信息
	 * <p>
	 * 获取当前已选项目及详细信息，获取所有可选项目及详细信息
	 * 
	 * @param _item
	 *            room,parlour,kitchen,toilet,balcony,hydropower,mount,cost,tax
	 * @param _value
	 */
	public static void get_quotation_item(Handler _handler, String _item, ContentValues _value, int _successful, int _failed)
	{
		String _address = "", _message;
		switch (_item)
		{
		case "room":
			_address = BASEADDRESS + "webOwnerViewOneQuotationItemRoom";
			break;
		case "parlour":
			_address = BASEADDRESS + "webOwnerViewOneQuotationItemParlour";
			break;
		case "kitchen":
			_address = BASEADDRESS + "webOwnerViewOneQuotationItemKitchen";
			break;
		case "toilet":
			_address = BASEADDRESS + "webOwnerViewOneQuotationItemToilet";
			break;
		case "balcony":
			_address = BASEADDRESS + "webOwnerViewOneQuotationItemBalcony";
			break;
		case "hydropower":
			_address = BASEADDRESS + "webOwnerViewOneQuotationItemHydropower";
			break;
		case "mount":
			_address = BASEADDRESS + "webOwnerViewOneQuotationItemMount";
			break;
		case "cost":
			_address = BASEADDRESS + "webOwnerViewOneQuotationItemCost";
			break;
		case "tax":
			_address = BASEADDRESS + "webOwnerViewOneQuotationItemTax";
			break;

		}
		_message = values(_value);

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	/**
	 * 获取自己的报价单
	 * 
	 * @param _value
	 *            quotationId：报价单ID
	 */
	public static void get_selfquote(Handler _handler, ContentValues _value, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "webOwnerViewSelfQuotation";
		String _message = "";

		_message = values(_value);

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	/**
	 * 获取报价单
	 */
	public static void get_ownerquote(Handler _handler, ContentValues _value, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "webOwnerQuoteMapping";
		String _message = "";

		_message = values(_value);

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	/**
	 * 认证资料提交（身份认证）
	 */
	public static void set_PersonalProve(Handler _handler, ContentValues _value, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "webPersonalProve";
		String _message = "";

		_message = values(_value);

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	/**
	 * 详细资料提交（身份认证）
	 */
	public static void set_PersonalIntroduce(Handler _handler, ContentValues _value, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "webPersonalIntroduce";
		String _message = "";

		_message = values(_value);

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	/**
	 * 基本资料提交（身份认证）
	 */
	public static void set_PersonalBaseInfor(Handler _handler, ContentValues _value, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "webPersonalBaseInfor";
		String _message = "";

		_message = values(_value);

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	/**
	 * 修改个人基本信息(我的中心-个人资料)
	 */
	public static void change_baseinfo(Handler _handler, String _showname, String _realname, String _sex, String _style, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "webPersonalBaseInfor";
		String _message = "";

		String _userShowName = "userShowName=" + _showname;
		String _realName = "realName=" + _realname;
		String _realSex = "realSex=" + _sex;
		String _likeStyle = "likeStyle=" + _style;

		_message = _userShowName + "&" + _realName + "&" + _realSex + "&" + _likeStyle;

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	/**
	 * 设置用户类型
	 * 
	 * @param _type
	 *            11: owner 业主 12: designer 设计师 13: constructor 施工队 14: supervisionner 监理 15: businesser 装修公司 100:未认证
	 */
	public static void set_UserType(Handler _handler, int _type, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "webSetUseType";
		String _message = "";

		String _userType = "userType=" + _type;

		_message = _userType;

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	/**
	 * 修改绑定手机
	 */
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

	/**
	 * 注册会员
	 */
	public static void submit_reg(Handler _handler, String _username, String _mobilecode, String _password, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "websubmitreg";
		String _message = "";

		_message = "userName=" + _username + "&pwd=" + _password + "&repwd=" + _password + "&mobilecode=" + _mobilecode;

		new Thread_http(_handler, _address, _message, _successful, _failed, "POST").start();
	}

	/**
	 * 发送手机验证码
	 */
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

	public static void check_login(Handler _handler, int _successful, int _failed)
	{
		String _address = BASEADDRESS + "webcheckloginuser";
		String _message = "";

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

	private static String values(ContentValues _value)
	{
		StringBuilder _builder = new StringBuilder();

		for (Entry<String, Object> item : _value.valueSet())
		{
			_builder.append(item.getKey());
			_builder.append('=');
			_builder.append(item.getValue());
			_builder.append('&');
		}
		if (_builder.length() > 1)
			_builder.deleteCharAt(_builder.length() - 1);

		return _builder.toString();
	}

	public static String encode(String _str)
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
