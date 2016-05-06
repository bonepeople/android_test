package com.shownest.android.utils;
public class SNConfig {
	public static final double VERSION = 1.0;

    /** 错误处理广播 */
    public static final String RECEIVER_ERROR = SNConfig.class.getName() + "com.shownest.frame.error";
    /** 无网络警告广播 */
    public static final String RECEIVER_NOT_NET_WARN = SNConfig.class.getName() + "com.shownest.frame.notnet";
    /** preference键值对 */
    public static final String SETTING_FILE = "snframe_preference";
    public static final String ONLY_WIFI = "only_wifi";
    
    public static final int MAX_CHARS = 140;
    
    public static final String WEB_PREFIX = "http://";
    public static final String DOMAIN_NAME = "www.shownest.com";
    public static final String DOMAIN_NAME_TEST = "t.shownest.com";//t.shownest.com
    //public static final String DOMAIN_NAME_TEST = "192.168.1.100";
    public static final String WEB_MURL = "m.shownest.com";
    public static String WEB_IP = "103.37.150.61";//;103.227.76.134
    public static String WEB_IP_TEST = "103.37.150.61";//192.168.1.102
    public static int WEB_PORT = 80;//8080;
    public static int WEB_PORT_TEST = 86;//测试端口;
    public static final int SERVER_PORT = 9966;
    public static final int PUSH_PORT = 9999;//消息推送端口
    public static final String WEB_DOMAIN = "";//"shownest";
    public static final String APP_DOMAIN = "app.shownest.com";
    ////////////////////获取秀巢网公网IP的URL
    //public static final String GET_NET_IP = WEB_PREFIX + DOMAIN_NAME + "/net/getIP";//正式链接
    public static final String GET_NET_IP = WEB_PREFIX + DOMAIN_NAME_TEST + ":" + SNConfig.WEB_PORT_TEST + "/" + "/net/getIP";//测试链接
    
    public static final String REQUEST_SEPARATOR = "@#";//请求分离符
    /******************** HTTP ***********************/
    //public static String HTTP = SNConfig.WEB_PREFIX + SNConfig.DOMAIN_NAME + ":" + SNConfig.WEB_PORT + "/";//正式链接
    public static String HTTP = SNConfig.WEB_PREFIX + SNConfig.DOMAIN_NAME_TEST + ":" + SNConfig.WEB_PORT_TEST + "/";//测试链接

    public static String WEBAPPHTTP = SNConfig.WEB_PREFIX + SNConfig.WEB_MURL + ":" + SNConfig.WEB_PORT + "/";
    
    public static String WEBAPPUiHTTP = SNConfig.WEB_PREFIX + SNConfig.DOMAIN_NAME_TEST + ":" + SNConfig.WEB_PORT_TEST + "/";//测试地址
    
    //public static String HTTPIP = SNConfig.WEB_PREFIX + SNConfig.WEB_IP + ":" + SNConfig.WEB_PORT + "/";//正式链接
    public static String HTTPIP = SNConfig.WEB_PREFIX + SNConfig.WEB_IP_TEST + ":" + SNConfig.WEB_PORT_TEST + "/";//测试链接
    
    public static String getHTTP() {
    	//return SNConfig.WEB_PREFIX + SNConfig.WEB_IP + ":" + SNConfig.WEB_PORT + "/";//正式链接
    	return SNConfig.WEB_PREFIX + SNConfig.DOMAIN_NAME_TEST + ":" + SNConfig.WEB_PORT_TEST + "/";//测试链接
    }
    
}