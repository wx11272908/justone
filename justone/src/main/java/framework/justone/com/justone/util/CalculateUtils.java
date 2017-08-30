package framework.justone.com.justone.util;

import android.annotation.SuppressLint;
import java.math.BigDecimal;

/**
 * 
 * 类 CalculateUtils 的实现描述：金额计算基类
 * 
 * @author yltang 2015-8-25 下午4:39:32
 * 
 */

public class CalculateUtils {

	/**
	 * 
	 * add 的实现描述：加法
	 * 
	 * @author yltang 2015-8-25 下午4:52:01
	 * @param para1
	 *            加数
	 * @param para2
	 *            被加数
	 * @return 和
	 * 
	 */
	public static String add(String para1, String para2) {
		BigDecimal val1 = new BigDecimal(para1);
		BigDecimal val2 = new BigDecimal(para2);
		return val1.add(val2).toString();
	}

	/**
	 * 
	 * subtract 的实现描述：减法
	 * 
	 * @author yltang 2015-8-27 上午9:57:47
	 * @param para1
	 *            减数
	 * @param para2
	 *            被减数
	 * @return 参数说明
	 * 
	 */
	public static String subtract(String para1, String para2) {
		BigDecimal val1 = new BigDecimal(para1);
		BigDecimal val2 = new BigDecimal(para2);
		return val1.subtract(val2).toString();
	}

	
	/**
	 * 
	 * @Title: multiply
	 * @Description: 舍位乘法
	 * @author: qtyin
	 * @param: @param para1
	 * @param: @param para2
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String multiply(String para1, String para2) {
		BigDecimal val1 = new BigDecimal(para1);
		BigDecimal val2 = new BigDecimal(para2);
		return val1.multiply(val2).setScale(2, BigDecimal.ROUND_DOWN)
				.toString();
	}
	
	/**
	 * 
	 * multiply 的实现描述：乘法
	 * 
	 * @author yltang 2015-8-25 下午4:53:21
	 * @param para1
	 *            乘数
	 * @param para2
	 *            被乘数
	 * @return 乘积
	 * 
	 */
	public static String multiply(String para1, String para2, int type) {
		BigDecimal val1 = new BigDecimal(para1);
		BigDecimal val2 = new BigDecimal(para2);
		return val1.multiply(val2).setScale(2, type)
				.toString();
	}

	/**
	 * 
	 * divide 的实现描述：除法
	 * 
	 * @author yltang 2015-8-25 下午5:32:14
	 * @param para1
	 *            除数
	 * @param para2
	 *            被除数
	 * @return 结果
	 * 
	 */
	public static String divide(String para1, String para2) {
		// MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
		BigDecimal val1 = new BigDecimal(para1);
		BigDecimal val2 = new BigDecimal(para2);
		// 除法保留两位小数并且四舍五入
		return val1.divide(val2, 2, BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 * 
	 * compare 的实现描述：比较两个数字的大小
	 * 
	 * @author yltang 2015-9-1 下午7:52:22
	 * @param para1
	 *            第一个数字
	 * @param para2
	 *            第二个数字
	 * @return < 返回-1   大于返回1   等于返回0
	 * 
	 */
	public static int compare(String para1, String para2) {
		BigDecimal val1 = new BigDecimal(para1);
		BigDecimal val2 = new BigDecimal(para2);
		return val1.compareTo(val2);
	}

	/**
	 * @Title: strToDoubleStr
	 * @Description: 将double字符串转换为保留两位小数的double字符串
	 * @author ZhouZejin
	 * @date 2016-3-23 下午7:46:34
	 * @param str
	 * @return String
	 */
	@SuppressLint("DefaultLocale")
	public static String strToDoubleStr(String str) {
		String tmp = "0.00";
		try {
			Double num = Double.valueOf(str);
			tmp = String.format("%.2f", num);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return tmp;
	}
}
