package com.dragon.codergen.utils;

import java.util.Date;

/**
 * @author Wu,ShangLong
 * @version builder 2011.04.22
 */
public class DateUtils {

	public static String getTimeInterval(Date start, Date end) {
		Long sec = (end.getTime() - start.getTime()) / 1000;
		return sec == 0 ? String.valueOf(end.getTime() - start.getTime()) + "ms" : sec.toString() + "s";
	}
}
