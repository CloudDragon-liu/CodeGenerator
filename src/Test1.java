import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date = getBirthday("1213");
		if (date == null) {
			System.out.println("1");
		}
		System.out.println(getBirthday("360121198711265212"));

	}

	/**
	 * @param IDCardNum
	 * @desc 通过身份证号码取出生日期
	 * @return Date
	 */
	static Date getBirthday(String IDCardNum) {
		String year = "", month = "", day = "";
		int idLength = IDCardNum.length();
		Date date = new Date();
		if (idLength == 18) {
			year = IDCardNum.substring(6, 10);
			month = IDCardNum.substring(10, 12);
			day = IDCardNum.substring(12, 14);
		} else if (idLength == 15) {
			year = Integer.parseInt(IDCardNum.substring(6, 8)) + 1900 + "";
			month = IDCardNum.substring(8, 10);
			day = IDCardNum.substring(10, 12);
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		try {
			date = sf.parse(year + month + day);
		} catch (ParseException e) {
			return null;
		}
		return date;
	}

	/**
	 * @param IDCardNum
	 * @desc 通过身份证号码取性别
	 * @return int
	 */
	static int getSex(String IDCardNum) {
		int idLength = IDCardNum.length();
		int sex; // 1:男；2：女
		if (idLength == 18) {
			sex = Integer.parseInt(IDCardNum.substring(14, 17)) % 2 == 0 ? 2 : 1;
		} else if (idLength == 15) {
			sex = Integer.parseInt(IDCardNum.substring(12, 15)) % 2 == 0 ? 2 : 1;
		} else {
			return -1;
		}
		return sex;
	}

}
