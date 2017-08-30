package framework.justone.com.justone.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author liangming@chinaums.com 2015-8-17 下午8:11:23
 * @ClassName: DateUtils
 * @Description: 日期常用工具
 */
public class DateUtils {

	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @param inputDate
	 * @return String
	 * @Author liangming@chinaums.com 2015-8-17 下午8:10:32
	 * @Description: TODO
	 */
	public static String parseDate(String inputDate) {
		if (inputDate == null || inputDate == "") {
			return "";
		} else {
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			try {
				date = inputFormat.parse(inputDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return format.format(date);
		}
	}

	/**
	 * 获取时间yyyy-MM-dd格式
	 * 
	 * @param inputDate
	 * @return String
	 * @author spxiong 2015-8-28下午4:28:02
	 */
	public static String parseDateWithoutTime(String inputDate) {
		if (inputDate == null || inputDate == "") {
			return "";
		} else {
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy/MM/dd");
			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			try {
				date = inputFormat.parse(inputDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return outputFormat.format(date);
		}
	}

	/**
	 * 获取当前时间 yyyy-MM-dd HH:mm:ss格式
	 */
	public static String getDateTime() {
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String now = format.format(new Date());
		return now;
	}

	/**
	 * 获取当前日期 yyyy-MM-dd
	 * 
	 * @author xylu 2015-8-14 下午3:56:43
	 */
	public static String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String now = format.format(new Date());
		return now;
	}

	/**
	 * @Title: getDateBeginTime
	 * @Description: 获得当日零点时间
	 * @author: qtyin
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String getDateBeginTime() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		String dayBeginTime = format.format(c.getTime());
		return dayBeginTime;

	}

	/**
	 * @Title: getDateWeekAgo
	 * @Description: 获得一周前的日期yyyy-MM-dd 00:00:00
	 * @author: qtyin
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String getDateWeekAgo() {
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_MONTH) - 7;
		c.set(Calendar.DAY_OF_MONTH, day);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		String weekAgo = format.format(c.getTime());
		return weekAgo;

	}

	/**
	 * @Title: getDateMonthAgo
	 * @Description: 获得一周前的日期yyyy-MM-dd HH:mm:ss
	 * @author: qtyin
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String getDateMonthAgo() {
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_MONTH) - 30;
		c.set(Calendar.DAY_OF_MONTH, day);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		String weekAgo = format.format(c.getTime());
		return weekAgo;

	}

	/**
	 * 获取当前日期 yyyy/MM/dd
	 * 
	 * @author xylu 2015-8-14 下午3:56:43
	 */
	public static String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		String now = format.format(new Date());
		return now;
	}

	/**
	 * 获取当前时间 HH:mm:ss格式
	 * 
	 * @author xylu 2015-10-16 上午10:08:43
	 */
	public static String getTime() {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		return df.format(new Date());
	}

	/**
	 * 计算日期间的时间差
	 * 
	 * @author yltang 2015-8-7 下午3:40:51
	 */
	public static Long CalculateDateGap(String startDate, String endDate) {
		Long gapDay = 0l;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Long timeGap = 0l;
		try {
			timeGap = sdf.parse(endDate).getTime()
					- sdf.parse(startDate).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		gapDay = timeGap / 1000 / 60 / 60 / 24;// 天
		return gapDay;
	}

	/**
	 * 计算日期间的月份差。 n个月以内（包括日相等），n-1个月以上算作n。 不能用作比较结束日期大于等于起止日期
	 */
	public static int CalculateMonthGap(String startDate, String endDate) {
		int gapMonth = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Calendar start = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
			start.setTime(sdf.parse(startDate));
			end.setTime(sdf.parse(endDate));
			gapMonth = (end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12
					+ end.get(Calendar.MONTH) - start.get(Calendar.MONTH);

			if ((end.get(Calendar.DATE) - start.get(Calendar.DATE)) > 0)
				gapMonth++;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return gapMonth;
	}

	/**
	 * 计算日期间的时间差，以秒为单位
	 * 
	 */
	public static Long CalculateTimeGap(String startDate, String endDate) {
		return CalculateTimeGap("yyyy-MM-dd HH:mm:ss", startDate, endDate);
	}

	/**
	 * 计算日期间的时间差，以秒为单位
	 *
	 * 可自定义时间格式
	 *
	 */
	public static Long CalculateTimeGap(String format, String startDate, String endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
		Long timeGap = 0l;
		try {
			timeGap = sdf.parse(endDate).getTime()
					- sdf.parse(startDate).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		timeGap = timeGap / 1000;
		return timeGap;
	}

	/**
	 * 根据时间差和原始时间，获得结果时间
	 *
	 * @param originTime
	 * @param timeGap
	 * @return
	 */
	public static  String getTimeWithGap(String originTime, Long timeGap){
		long longStart = getTimeMillis(format, originTime);
		return format.format(new Date(longStart + timeGap * 1000));
	}


	/**
	 * 根据时间差和原始时间，获得结果时间
	 *
	 * @param originTime
	 * @param timeGap
     * @return
     */
	public static  String getTimeWithGap(String format, String originTime, Long timeGap){
		SimpleDateFormat formatSDF = new SimpleDateFormat(format);
		long longStart = getTimeMillis(formatSDF, originTime);
		return formatSDF.format(new Date(longStart + timeGap * 1000));
	}

	/**
	 * 读取时间的毫秒数
	 *
	 * @param strTime
	 * @return
     */
	public static Long getTimeMillis(SimpleDateFormat format, String strTime) {
		long returnMillis = 0;
		Date d = null;
		try {
			d = format.parse(strTime);
			returnMillis = d.getTime();
		} catch (ParseException e) {
		}
		return returnMillis;
	}

	/**
	 * parseEndDate 的实现描述：获取输入日期当日的最后时间 yyyy/MM/dd-> yyyy-MM-dd 23:59:59
	 * 
	 * @param inputDate
	 * @return 参数说明
	 * @author yltang 2015-8-18 下午5:45:16
	 */
	public static String parseEndDate(String inputDate) {
		if (inputDate == null || inputDate == "") {
			return "";
		} else {
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy/MM/dd");
			SimpleDateFormat outputFormat = new SimpleDateFormat(
					"yyyy-MM-dd 23:59:59");
			Date date = new Date();
			try {
				date = inputFormat.parse(inputDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return outputFormat.format(date);
		}
	}

	/**
	 * @Title: getEndDate
	 * @Description: 获取当前日期3个月前的日期
	 * @author ZhouZejin
	 * @date 2016-2-24 上午9:36:37
	 * @return String
	 */
	public static String getEndDate() {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -3);
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy/MM/dd");
		String endDate = inputFormat.format(calendar.getTime());
		return endDate;
	}

	/**
	 * 
	 * @Title: getDateTimeSpecified
	 * @Description: 获取指定日期的时间
	 * @author: qtyin
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String getDateSpecified(String year, String month, String day) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.parseInt(year));
		c.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		String dayBeginTime = format.format(c.getTime());
		return dayBeginTime;
	}
	
	/**
	 * 
	 * @Title: getDateTimeSpecified
	 * @Description: 获取指定日期的时间
	 * @author: qtyin
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String getEndDateSpecified(String year, String month, String day) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, Integer.parseInt(year));
		c.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);

		String dayBeginTime = format.format(c.getTime());
		return dayBeginTime;
	}

	/**
	 * @Title: getCurrentYear
	 * @Description: 获取当前年份
	 * @author: qtyin
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String getCurrentYear() {
		Calendar c = Calendar.getInstance();
		return String.valueOf(c.get(Calendar.YEAR));

	}

	/**
	 * 
	 * @Title: getCurrentMonth
	 * @Description: 获取当前的月份
	 * @author: qtyin
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String getCurrentMonth() {
		Calendar c = Calendar.getInstance();
		return String.valueOf(c.get(Calendar.MONTH) + 1);

	}

	/**
	 * 
	 * @Title: getCurrentDay
	 * @Description: 获取当前的日期（Day of Month）
	 * @author: qtyin
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public static String getCurrentDay() {
		Calendar c = Calendar.getInstance();
		return String.valueOf(c.get(Calendar.DAY_OF_MONTH));

	}

	/**
	 * 改变format格式
	 *
	 * @param originDate
	 * @param outputFormat
     * @return
     */
	public static String changeDateFormat(String originDate, String outputFormat) {
		if (StringUtils.isBlank(originDate)) {
			return "";
		}

		if(StringUtils.isBlank(outputFormat)){
			return originDate;
		}
		SimpleDateFormat outputFormatSDF = new SimpleDateFormat(outputFormat);
		Date date = new Date();
		try {
			date = format.parse(originDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return outputFormatSDF.format(date);

	}
}
