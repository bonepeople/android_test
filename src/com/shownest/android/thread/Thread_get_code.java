package com.shownest.android.thread;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import com.shownest.android.activity.Activity_regist;

import android.os.Handler;
import android.os.Message;

public class Thread_get_code extends Thread
{
	private String _phone = "";
	private Handler _handler;

	public Thread_get_code(String phone)
	{
		this._handler = Activity_regist._handler;
		this._phone = phone;
	}

	@Override
	public void run()
	{
		send_post();
	}

	private int send_post()// ("/TradeLog.txt","TradeLog.txt")
	{
		// 获取上传地址
		// 根据系统时间判断过期问题
		Message _msg = _handler.obtainMessage();
		_msg.what = Activity_regist.REGIST_FAILED;
		_msg.obj = new String("Exception");
		URL __realUrl = null;
		HttpURLConnection __connection = null;
		FileInputStream __is = null;
		int __status = 0;

		try
		{

			String __url_upload_file = "http://t.shownest.com:86/checkUserPhone";

			System.out.println(__url_upload_file);
			__realUrl = new URL(__url_upload_file);
			__connection = (HttpURLConnection) __realUrl.openConnection();
			__connection.setConnectTimeout(3000);
			__connection.setReadTimeout(3000);
			__connection.setRequestMethod("POST");
			multipartUploadData(__connection, __is);

			__status = __connection.getResponseCode();
			if (__status == 200)
			{
				BufferedReader __in = null;
				__in = new BufferedReader(new InputStreamReader(__connection.getInputStream()));
				String __result = "";
				String __line;
				while ((__line = __in.readLine()) != null)
				{
					__result += __line;
				}
				// System.out.println("result=" + __result);
				__status = 1;
				_msg.what = Activity_regist.REGIST_SUCCESSFUL;
				_msg.obj = __result;
			}
			else
			{
				BufferedReader __in_err = null;
				__in_err = new BufferedReader(new InputStreamReader(__connection.getErrorStream()));
				String __result_err = "";
				String __line_err;
				while ((__line_err = __in_err.readLine()) != null)
				{
					__result_err += __line_err;
				}
				// System.out.println("HTTP上传返回值异常：" + __status);
				// System.out.println("result=" + __result_err);
				_msg.what = Activity_regist.REGIST_FAILED;
				_msg.obj = __result_err;
			}
		}
		catch (NullPointerException __e1)
		{
			// from File __file_upload = new File(__path_file);
			__e1.printStackTrace();
		}
		catch (MalformedURLException __e3)
		{
			// from __realUrl = new URL(__url_upload_file);
			__e3.printStackTrace();
		}
		catch (ProtocolException __e4)
		{
			// from __connection.setRequestMethod("POST");
			__e4.printStackTrace();
		}
		catch (IOException __e5)
		{
			// from __connection = (HttpURLConnection)__realUrl.openConnection();
			// from __in = new BufferedReader(new InputStreamReader(__connection.getInputStream()));
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
			if (__connection != null)
				__connection.disconnect();
			if (__is != null)
				try
				{
					__is.close();
				}
				catch (IOException __e)
				{
				}
		}
		return __status;
	}

	private void multipartUploadData(HttpURLConnection __con, FileInputStream __datastream) throws Exception
	{
		__con.setDoOutput(true);
		__con.setRequestProperty("connection", "keep-alive");
		__con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		__con.setRequestProperty("Cache-Control", "max-age=0");

		StringBuffer __str_buf = new StringBuffer();
		__str_buf.append("telNo=" + URLEncoder.encode(this._phone, "UTF-8"));

		System.out.println(__str_buf);
		OutputStream __os = null;
		try
		{
			__con.connect();
			__os = __con.getOutputStream();
			__os.write(__str_buf.toString().getBytes());

			System.out.println("已经完成数据传输");
		}
		catch (Exception __e)
		{
			throw __e;
			// __e.printStackTrace();
		}
		finally
		{
			try
			{
				if (__os != null)
				{
					__os.flush();
					__os.close();
				}
			}
			catch (Exception __e)
			{
			}
		}
	}

}
