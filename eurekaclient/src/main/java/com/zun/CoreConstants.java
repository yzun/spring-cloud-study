package com.zun;

/**
 * 公用常量定义
 */
public class CoreConstants {
	/**
	 * 日期格式字符串
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd";	
	
	/**
	 * 时间格式字符串
	 */
	public static final String TIME_FORMAT = "HH:mm:ss";	
	
	/**
	 * 日期时间格式字符串
	 */
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";	
	
	/**
	 * 日期时间格式字符串毫秒
	 */
	public static final String DATETIME_MS_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";		
	
	/**
	 * 客户端有两种方式传递令牌: Header中X-Token, 或Cookie中X-Token
	 */
	public static final String X_TOKEN = "X-Token";
	/**
	 * 密钥
	 */
	public static final String SECRET = "secret";
	/**
	 * 数据分页默认页码
	 */
	public static final String PAGE_NUMBER = "1";
	/**
	 * 数据分页默认笔数
	 */
	public static final String PAGE_SIZE = "10";
	/**
	 * 分隔符
	 */
	public static final String SEPARATE_CHAR = "|";
}
