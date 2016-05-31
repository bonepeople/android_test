package com.shownest.android.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil
{

	/************************** 正则表达式 ****************************/
	public static final String REG_AT = "@[\\u4e00-\\u9fa5\\w\\-]+";
	public static final String REG_LINK = "<a[^>]*>([^<]*)</a>";
	public static final String REG_IMG_NAME = "name=['\"]?([^'\"]*)['\"]?";
	public static final String REG_IMG = "<img.*?(?:>|\\/>)";
	public static final String REG_IMG_SRC = "src=['\"]?([^'\"]*)['\"]?";
	public static final String REG_EM = "\\[em_([0-9]*)\\]";
	public static final String REG_BRACKET = "\\[(.*?)\\]";
	public static final String REG_E = "\\[e\\](.*?)\\[/e\\]";
	public static final String REG_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	public static final String REG_PHONE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	public static final String REG_PASSWORD = "\\w+";
	public static final String REG_ID_NUMBER = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
	public static final String REG_COMMENT_NAME = "「(.*)」";
	public static String REG_WEB_URL = "[^\u4e00-\u9fa5\\s|^\uFE30-\uFFA0\\s|^\\]]*?\\.(com|net|cn|me|tw|fr)[^\u4e00-\u9fa5\\s|^\uFE30-\uFFA0\\s|^\\[|^\\<|^@]*";

	public static String get_chineseName(String _name)
	{
		String _result = _name;
		switch (_name)
		{
		case "room":
			_result = "卧室";
			break;

		case "parlour":
			_result = "客厅";
			break;

		case "kitchen":
			_result = "厨房";
			break;

		case "toilet":
			_result = "卫生间";
			break;

		case "balcony":
			_result = "阳台";
			break;

		case "hydropower":
			_result = "水电";
			break;

		case "mount":
			_result = "安装";
			break;

		case "cost":
			_result = "杂费";
			break;

		case "tax":
			_result = "税费";
			break;

		case "ground":
			_result = "地面";
			break;

		case "wall":
			_result = "墙面";
			break;

		case "roof":
			_result = "顶面";
			break;
		}

		return _result;
	}

	public static boolean isIDNumber(String id)
	{
		return Pattern.matches(REG_ID_NUMBER, id);
	}

	public static boolean isPhone(String phone)
	{
		Pattern phonePattern = Pattern.compile(REG_PHONE);
		Matcher matcher = phonePattern.matcher(phone);
		if (matcher.find())
		{
			return true;
		}
		return false;
	}

	public static boolean isPassword(String password)
	{
		return Pattern.matches(REG_PASSWORD, password);
	}

	public static boolean isEmail(String email)
	{
		Pattern emailPattern = Pattern.compile(REG_EMAIL);
		Matcher matcher = emailPattern.matcher(email);
		if (matcher.find())
		{
			return true;
		}
		return false;
	}

	public static String showPhone(String _phone)
	{
		StringBuilder _builder = new StringBuilder();
		int _temp_i = 0;
		for (_temp_i = 0; _temp_i < _phone.length(); _temp_i++)
		{
			if (_temp_i < 3 || _temp_i > _phone.length() - 3)
				_builder.append(_phone.charAt(_temp_i));
			else
				_builder.append('*');
		}
		return _builder.toString();
	}

	public static String parseImgSrc(String str)
	{

		String imgSrc = null;

		Pattern imgPattern = Pattern.compile(REG_IMG);
		Pattern srcPattern = Pattern.compile(REG_IMG_SRC);
		Matcher imgMatcher = imgPattern.matcher(str);
		while (imgMatcher.find())
		{
			Matcher srcMatcher = srcPattern.matcher(imgMatcher.group());
			while (srcMatcher.find())
			{
				imgSrc = srcMatcher.group(1);
			}
		}

		return imgSrc;
	}

	public static String replace_htmlLink(String str)
	{

		Pattern pattern = Pattern.compile(REG_LINK);
		Matcher matcher = pattern.matcher(str);
		while (matcher.find())
		{
			str = str.replace(matcher.group(), matcher.group(1));
		}
		return str;
	}

	public static String ToSBC(String input)
	{
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++)
		{
			if (c[i] == ' ')
			{
				c[i] = '\u3000';
			}
			else if (c[i] < '\177')
			{
				c[i] = (char) (c[i] + 65248);
			}
		}
		return new String(c);
	}

	public static String getUserHeaderIconName(String iconUrl)
	{
		String[] arr = iconUrl.split("/");
		return arr[arr.length - 1];
	}

	/**
	 * @Title: getRoleLevel @Description: 获取角色评分，转化成“星星”值 @param @param grade @param @return 设定文件 @return int 返回类型 @throws
	 */
	public static int getRoleLevel(float gradePraise)
	{
		int star = 0;
		if (gradePraise >= 4 && gradePraise <= 50)
		{
			star = 1;
		}
		else if (gradePraise >= 51 && gradePraise <= 150)
		{
			star = 2;
		}
		else if (gradePraise >= 151 && gradePraise <= 300)
		{
			star = 3;
		}
		else if (gradePraise >= 301 && gradePraise <= 600)
		{
			star = 4;
		}
		else if (gradePraise >= 601)
		{
			star = 5;
		}

		return star;
	}

	public static String getUserHeaderIconUrl(int userType, String _headerIcon)
	{

		String headerIcon = "";
		switch (userType)
		{
		case 11:
			headerIcon = SNConfig.HTTP + "_resources/upload/o/" + _headerIcon;
			break;
		case 12:
			headerIcon = SNConfig.HTTP + "_resources/upload/d/" + _headerIcon;
			break;
		case 13:
			headerIcon = SNConfig.HTTP + "_resources/upload/c/" + _headerIcon;
			break;
		case 14:
			headerIcon = SNConfig.HTTP + "_resources/upload/s/" + _headerIcon;
			break;
		case 15:
			headerIcon = SNConfig.HTTP + "_resources/upload/b/" + _headerIcon;
			break;
		default:
			break;
		}

		return headerIcon;
	}

	public static String getConsType(int consType)
	{

		if (1 == consType)
			return "全包";
		else if (2 == consType)
			return "清工";

		return "半包";
	}

	public static String getBidType(String bidType)
	{

		String bidTypeName = "";

		if ("0".equals(bidType))
		{
			bidTypeName = "施工标、设计标、监理标、";
		}
		else
		{

			if (bidType.contains("13"))
			{
				bidTypeName += "施工标、";
			}

			if (bidType.contains("12"))
			{
				bidTypeName += "设计标、";
			}

			if (bidType.contains("14"))
			{
				bidTypeName += "监理标、";
			}
		}

		if (!"".equals(bidTypeName))
		{
			bidTypeName = bidTypeName.substring(0, bidTypeName.length() - 1);
		}

		return bidTypeName;
	}

	public static String getDesiServiceItem(String serviceItem)
	{

		String serviceItemName = "";

		if (serviceItem.contains("1"))
		{
			serviceItemName += "硬装设计、";
		}

		if (serviceItem.contains("2"))
		{
			serviceItemName += "软装设计、";
		}

		if (serviceItem.contains("3"))
		{
			serviceItemName += "产品陪购、";
		}

		if (!"".equals(serviceItemName))
		{
			serviceItemName = serviceItemName.substring(0, serviceItemName.length() - 1);
		}

		return serviceItemName;
	}

	public static String getDesiStyle(ArrayList<Integer> desiStyleIndexs)
	{
		String desiStyle = "";

		for (int desiStyleIndex : desiStyleIndexs)
		{
			desiStyle += desiStyleIndex + ",";
		}

		return desiStyle;
	}

	public static ArrayList<Integer> getDesiStyle(String desiStyle)
	{
		ArrayList<Integer> desiStyleIndexs = new ArrayList<>();

		String[] desiStyles = desiStyle.split(",");

		for (String index : desiStyles)
		{
			desiStyleIndexs.add(Integer.parseInt(index));
		}

		return desiStyleIndexs;
	}

	public static String getDesiStyleNames(ArrayList<Integer> desiStyleIndexs)
	{

		return getDesiStyleNames(getDesiStyle(desiStyleIndexs));
	}

	// 风格
	public static String getDesiStyleNames(String desiStyle)
	{

		String desiStyleNames = "";

		if (null != desiStyle)
		{
			String[] desiStyleArr = desiStyle.split(",");
			for (String desiStyleId : desiStyleArr)
			{

				if ("1".equals(desiStyleId))
				{
					desiStyleNames += "简约、";
				}
				else if ("2".equals(desiStyleId))
				{
					desiStyleNames += "现代、";
				}
				else if ("3".equals(desiStyleId))
				{
					desiStyleNames += "中式、";
				}
				else if ("4".equals(desiStyleId))
				{
					desiStyleNames += "欧式、";
				}
				else if ("5".equals(desiStyleId))
				{
					desiStyleNames += "美式、";
				}
				else if ("6".equals(desiStyleId))
				{
					desiStyleNames += "日式、";
				}
				else if ("7".equals(desiStyleId))
				{
					desiStyleNames += "东南亚、";
				}
				else if ("8".equals(desiStyleId))
				{
					desiStyleNames += "地中海、";
				}
				else if ("9".equals(desiStyleId))
				{
					desiStyleNames += "混搭、";
				}
				else if ("10".equals(desiStyleId))
				{
					desiStyleNames += "新古典、";
				}
				else if ("11".equals(desiStyleId))
				{
					desiStyleNames += "田园、";
				}
				else if ("12".equals(desiStyleId))
				{
					desiStyleNames += "其他、";
				}
			}
		}

		if (!"".equals(desiStyleNames))
		{
			desiStyleNames = desiStyleNames.substring(0, desiStyleNames.length() - 1);
		}

		return desiStyleNames;
	}

	public static String showRegion(String provinceName, String cityName, String countyName)
	{

		if (provinceName.equals(cityName))
			return cityName + countyName;

		return provinceName + cityName + countyName;
	}

	/**
	 * @Title: createSMSRandom @Description: 发送随机短信验证码 @param numberFlag @param length @param 设定文件 @return String 返回类型 @throws
	 */
	public static String createSMSRandom(boolean numberFlag, int length)
	{
		String retStr = "";
		String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do
		{
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++)
			{
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9'))
				{
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2)
			{
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}
}
