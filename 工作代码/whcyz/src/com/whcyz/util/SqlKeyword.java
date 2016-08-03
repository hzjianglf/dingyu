package com.whcyz.util;

/**
 * 定义常用的 sql关键字
 * @author 牟孟孟
 *
 */
public class SqlKeyword {
	public static final String SELECT="select ";
	public static final String ALL="*";
	public static final String UPDATE="update ";
	public static final String DELETE_FROM="delete from ";
	public static final String SET=" set ";
	public static final String SELECT_COUNT_ALL_FROM="select count(*) from";
	public static final String SELECT_ALL="select * ";
	public static final String SELECT_ALL_FROM="select * from ";
	public static final String FROM=" from ";
	
	public static final String WHERE=" where ";
	public static final String WHERE_ID=" where id=? ";
	public static final String WHERE_ID_IN=" where id in(?) ";
	public static final String ID_IN=" id in(?) ";
	public static final String OR=" or ";
	public static final String AND=" and ";
	public static final String AND_DISABLE_FALSE=" and disable=0 ";
	public static final String WHERE_DISABLE_FALSE=" where disable=0 ";
	public static final String WHERE_DISABLE_AND=" where disable=0 and ";
	public static final String LIMIT_ONE=" limit 1";
	public static final String DISABLE=" disable=0";
	public static final String SET_DISABLE_TRUE=" set disable=1";
	public static final String ORDER_BY =" order by ";
	
	/**
	 * 不等于
	 */
	public final static String UE = "!=";
	/**
	 * 大于
	 */
	public final static String GT = ">";
	/**
	 * 等于
	 */
	public final static String EW = "=";
	/**
	 * 小于
	 */
	public final static String ST = "<";
	/**
	 * 两值之间
	 */
	public final static String BW = "between";
	/**
	 * 从大到小排序
	 */
	public final static String DESC = "desc";
	/**
	 * 从小到大排序
	 */
	public final static String ASC = "asc";
	/**
	 * 集合内
	 */
	public final static String IN = "in";
	/**
	 * IS查询
	 */
	public final static String IS = "is";
	/**
	 * IS not 查询
	 */
	public final static String ISNOT = "is not";
	/**
	 * 模糊查询
	 */
	public final static String LIKE = "like";
	/**
	 * 模糊查询
	 */
	public final static String LIKELEFT = "likeleft";
	/**
	 * 模糊查询
	 */
	public final static String LIKERIGHT = "likeright";

	/**
	 * 按组查询
	 */
	public final static String GROUP = "group";
}
