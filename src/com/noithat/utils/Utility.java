package com.noithat.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class Utility {
	private static final Logger logger = Logger.getLogger(Utility.class);
	public static final String DATE_FORMAT_SHORT_VI_DEFAULT = "dd/MM/yyyy";
	public static final String DATE_FORMAT_FULL_VI_DEFAULT = "dd/MM/yyyy HH:mm:ss";
	public static final String DATE_FORMAT_SHORT_MYSQL = "yyyy-MM-dd";
	public static final String DATE_FORMAT_MYSQL = "yyyy-MM-dd HH:mm:ss";
	public static final DateFormat formatShortDate = new SimpleDateFormat(
			DATE_FORMAT_SHORT_VI_DEFAULT);
	public static final DateFormat df_ddMMyyy = new SimpleDateFormat(
			DATE_FORMAT_FULL_VI_DEFAULT);
	public static final DateFormat dateFromDB = new SimpleDateFormat(
			DATE_FORMAT_MYSQL);
	public static final DateFormat shortDateFromDB = new SimpleDateFormat(
			DATE_FORMAT_SHORT_MYSQL);
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	private static final String[] unicodePattern = {
			"(Ã¡|Ã |áº£|Ã£|áº¡|Äƒ|áº¯|áº±|áº³|áºµ|áº·|Ã¢|áº¥|áº§|áº©|áº«|áº­)", "Ä‘",
			"(Ã©|Ã¨|áº»|áº½|áº¹|Ãª|áº¿|á»�|á»ƒ|á»…|á»‡)", "(Ã­|Ã¬|á»‰|Ä©|á»‹)",
			"(Ã³|Ã²|á»�|Ãµ|á»�|Ã´|á»‘|á»“|á»•|á»—|á»™|Æ¡|á»›|á»�|á»Ÿ|á»¡|á»£)", "(Ãº|Ã¹|á»§|Å©|á»¥|Æ°|á»©|á»«|á»­|á»¯|á»±)",
			"(Ã½|á»³|á»·|á»¹|á»µ)" };
	private static final String[] replaceCharNonUnicode = { "a", "d", "e", "i",
			"o", "u", "y", "A", "D", "E", "I", "O", "U", "Y" };

	private static Properties properties = new Properties();

	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		Utility.properties = properties;
	}
	
	public static void writeFileProperties(Properties properties, String key, String value){	
		try {																
			// Ghi vÃ o file property
			properties.setProperty(key, value);				
			//properties.
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static boolean validateEmailFormat(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
	}
	
	public static boolean writeDataProperty(Properties properties, String key, String data, String pathName) {
		try {
			try {
				properties.setProperty(key, data);
				properties.store(new FileOutputStream(pathName), null);
				return true;
			} catch (Exception e) {
				System.out
						.println("Exception: " + e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/***
	 * Convert string to java.util.Date
	 * 
	 * @author DongDP
	 * @param dateInput
	 * @param dateFormat
	 *            : ext dd/MM/yyyy or dd/MM/yyyy HH:mm:ss
	 * @return
	 */
	public static java.util.Date convertStringToDate(String dateInput,
			String dateFormat) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			java.util.Date date = sdf.parse(dateInput);
			return date;
		} catch (Exception ex) {
			logger.error("Loi ham convertStringToDate() - Exception : "
					+ ex.getMessage());
			return null;
		}
	}

	/***
	 * Convert java.util.Date to string
	 * 
	 * @author DongDP
	 * @param date
	 * @param dateFormat
	 *            : ext dd/MM/yyyy or dd/MM/yyyy HH:mm:ss
	 * @return
	 */
	public static String convertDateToString(java.util.Date date,
			String dateFormat) {
		try {
			DateFormat df6 = new SimpleDateFormat(dateFormat);
			return df6.format(date);
		} catch (Exception ex) {
			logger.error("Loi ham convertDateToString() - Exception : "
					+ ex.getMessage());
			return "";
		}
	}

	/***
	 * Convert string to java.sql.Date
	 * 
	 * @author DongDP
	 * @param dateInput
	 * @param dateFormat
	 *            : ext dd/MM/yyyy or dd/MM/yyyy HH:mm:ss
	 * @return
	 */
	public static java.sql.Date convertStringToSqlDate(String dateInput,
			String dateFormat) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

			java.sql.Date sqlDate = new java.sql.Date(sdf.parse(dateInput)
					.getTime());

			return sqlDate;
		} catch (Exception ex) {
			logger.error("Loi ham convertStringToSqlDate() - Exception : "
					+ ex.getMessage());
			return null;
		}
	}

	/***
	 * Convert java.sql.Date to string
	 * 
	 * @author DongDP
	 * @param date
	 * @param dateFormat
	 *            : ext dd/MM/yyyy or dd/MM/yyyy HH:mm:ss
	 * @return
	 */
	public static String convertSqlDateToString(java.sql.Date date,
			String dateFormat) {
		try {
			DateFormat df6 = new SimpleDateFormat(dateFormat);
			return df6.format(date);
		} catch (Exception ex) {
			logger.error("Loi ham convertSqlDateToString() - Exception : "
					+ ex.getMessage());
			return "";
		}
	}

	/***
	 * Convert java.util.Date to java.sql.Date
	 * 
	 * @author DongDP
	 * @param utilDate
	 * @return java.sql.Date if utilDate valid, else return null
	 */
	public static java.sql.Date convertUtilDateToSqlDate(java.util.Date utilDate) {
		try {
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			return sqlDate;
		} catch (Exception e) {
			logger.error("Loi ham convertUtilDateToSqlDate() - Exception : "
					+ e.getMessage());
			return null;
		}
	}

	/***
	 * Convert java.sql.Date to java.util.Date
	 * 
	 * @author DongDP
	 * @param utilDate
	 * @return java.util.Date if sqlDate valid, else return null
	 */
	public static java.util.Date convertSqlDateToUtilDate(java.sql.Date sqlDate) {
		try {
			java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
			return utilDate;
		} catch (Exception e) {
			logger.error("Loi ham convertSqlDateToUtilDate() - Exception : "
					+ e.getMessage());
			return null;
		}
	}

	public static String getStackTrace(Exception e) {
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		e.printStackTrace(printWriter);
		return writer.toString();
	}

	public static String getCause(Exception e) {
		try {
			return e.getMessage().toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "Lá»—i khi getCauseException";
	}

	public static String getXmlTagFromTagName(String tagName, boolean isOpenTag) {
		if (isOpenTag) {
			return "<" + tagName + ">";
		} else {
			return "</" + tagName + ">";
		}
	}

	public static String getXmlDocumentString(String tagName, String content) {
		return "<" + tagName + ">" + handleXmlContent(content) + "</" + tagName
				+ ">";
	}

	public static Date convertStringToDateJavaUtil(String dateInput,
			String dateFormat) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			Date date = sdf.parse(dateInput);
			return date;
		} catch (Exception ex) {
			logger.error("Loi ham convertStringToDateJavaUtil() - Exception : "
					+ ex.getMessage());
			return null;
		}
	}

	public static String removeChar(String originString, String strToRemove) {
		return originString.replace(strToRemove, "");
	}

	/***
	 * 
	 * @param strContent
	 *            chuá»—i cáº§n cáº¯t
	 * @param delimiter
	 *            kÃ½ tá»± phÃ¢n cáº¯t
	 * @return danh sÃ¡ch Ä‘Ã£ Ä‘Æ°á»£c cáº¯t
	 * @author Nguyen Van Cuong
	 */
	public static ArrayList<String> split(String strContent, String delimiter) {
		String[] strSplit;
		ArrayList<String> listVal = new ArrayList<String>();
		try {
			if (strContent == null)
				return listVal;
			else {
				strSplit = strContent.split(delimiter);
				for (int i = 0; i < strSplit.length; ++i) {
					listVal.add(strSplit[i]);
				}
			}

		} catch (Exception e) {
			logger.error("Loi ham split() - Exception : " + e.getMessage());
		}
		return listVal;
	}

	/***
	 * 
	 * @param strContent
	 *            chuá»—i cáº§n cáº¯t
	 * @param delimiter
	 *            kÃ½ tá»± phÃ¢n cáº¯t
	 * @return danh sÃ¡ch Ä‘Ã£ Ä‘Æ°á»£c cáº¯t
	 * @author Nguyen Van Cuong
	 */
	public static ArrayList<Integer> splitToListInteger(String strContent,
			String delimiter) {
		String[] strSplit;
		ArrayList<Integer> listVal = new ArrayList<Integer>();
		try {
			if (strContent == null)
				return listVal;
			else {
				strSplit = strContent.split(delimiter);
				for (int i = 0; i < strSplit.length; ++i) {
					int result = Integer.parseInt(strSplit[i].trim());
					listVal.add(result);
				}
			}

		} catch (Exception e) {
			logger.error("Loi ham split() - Exception : " + e.getMessage());
		}
		return listVal;
	}

	/**
	 * @author DongDP
	 * @param classLoaderName
	 *            : example com.bkavca.raoperation.business.entity.Info.class or
	 *            Info.class
	 * @param xmlString
	 * @return
	 */
	private static StringBuilder strError;

	public static Object convertXmlToObject(Class<?> classLoaderName,
			String xmlString) {
		try {
			System.out.println("xmlString: "+xmlString);
			JAXBContext jaxbContext = JAXBContext
					.newInstance(new Class[] { classLoaderName });
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			InputSource inputSource = new InputSource(new StringReader(
					xmlString));
			return jaxbUnmarshaller.unmarshal(inputSource);
		} catch (Exception e) {
			System.out.println("Loi convert: " + e.getMessage());
			e.printStackTrace();
			logger.error("Loi ham convertXmlToObject() - Exception : "
					+ e.getMessage());
		}
		return null;
	}

	public static String convertObjectToXml(Class<?> classLoaderName,
			Object object) {
		ByteArrayOutputStream out = null;
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(new Class[] { classLoaderName });
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			out = new ByteArrayOutputStream();
			jaxbMarshaller.marshal(object, out);
			return out.toString();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Loi ham convertObjectToXml() - Exception : "
					+ e.getMessage());
		} 
		return "";
	}

	public static String getStrError() {
		return strError.toString();
	}

	/***
	 * 
	 * @param aFile
	 * @return
	 */
	public static String getContents(File aFile) {
		StringBuilder contents = new StringBuilder();
		try {
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			try {
				String line = null;
				while ((line = input.readLine()) != null) {
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			logger.error("Loi ham getContents() - Exception : "
					+ ex.getMessage());
		}
		return contents.toString();
	}

	/***
	 * 
	 * @param input
	 * @return
	 */
	public static ByteArrayInputStream stringToInputStream(String input) {
		ByteArrayInputStream is = null;
		try {
			is = new ByteArrayInputStream(input.getBytes("UTF-8"));
			return is;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				logger.error("Loi ham stringToInputStream() - Exception : "
						+ e.getMessage());
			}
		}
		return null;
	}

	public static String strim() {
		return "";
	}

	public static String filter(String strContent,
			ArrayList<String> dataToRemove) {
		int startIndex;
		for (String item : dataToRemove) {
			startIndex = -1;
			startIndex = strContent.toLowerCase().indexOf(item.toLowerCase());
			if (startIndex > -1
					&& startIndex < (strContent.length() - item.length())) {
				strContent = strContent.substring(startIndex + item.length());
				break;
			}
		}
		return strContent.trim();
	}

	public static String standerlizeString(String data) {
		String[] tmp = data.split(" ");
		StringBuilder result = new StringBuilder();
		for (String item : tmp) {
			if (item.length() > 0) {
				result.append(item.trim());
				result.append(" ");
			}
		}
		return result.toString().trim();
	}

	public static String handleXmlContent(String input) {
		String[] orgChar = new String[] { "&", "<", ">", "\"", "'" };
		String[] newChar = new String[] { "&amp;", "&lt;", "&gt;", "&quot;",
				"&#039;" };
		// iterate through HashMap values iterator
		String tmp = input;
		for (int i = 0; i < orgChar.length; i++)
			tmp = tmp.replace(orgChar[i], newChar[i]);
		return tmp;
	}

	public static String hexToBigInt(String hex) {
		String extraCode = "";
		try {
			BigInteger decCode = new BigInteger(hex, 16);
			extraCode = decCode.toString(10);
		} catch (NumberFormatException e) {
			logger.error("Loi ham hexToBigInt() - Exception : "
					+ e.getMessage());
		}
		return extraCode;
	}
	
	public static String bigIntToHex(String bigInt) {
		BigInteger result;
		String hex = "";
		try {
			result = new BigInteger(bigInt);
			hex = result.toString(16);
		} catch (NumberFormatException e) {
			logger.error("Loi ham bigIntToHex() - Exception : "
					+ e.getMessage());
		}
		return hex;
	}

	private static final Pattern checkEmail = Pattern
			.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-_+]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	public static boolean isEmailValid(String email) {
		if (checkEmail.matcher(email).matches()) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		/*String test = "MIIEMjCCAxqgAwIBAgIQVANaIn5pxiHrqf1h1AQ90jANBgkqhkiG9w0BAQUFADBJMQswCQYDVQQGEwJWTjEOMAwGA1UEBxMFSGFub2kxGTAXBgNVBAoTEEJrYXYgQ29ycG9yYXRpb24xDzANBgNVBAMTBkJrYXZDQTAeFw0xMjEyMDQwOTQ5NTJaFw0xNDEyMDUwOTQ5NTJaMIGVMSowKAYKCZImiZPyLGQBAQwaTVNUOjAxMDEzNjA2OTctKi5ia2F2Y2Eudm4xFDASBgNVBAMMCyouYmthdmNhLnZuMRkwFwYDVQQKDBBCa2F2IENvcnBvcmF0aW9uMRUwEwYDVQQHDAxD4bqndSBHaeG6pXkxEjAQBgNVBAgMCUjDoCBO4buZaTELMAkGA1UEBhMCVk4wgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAKqK4nTXhIaNvAjvbccV8VZAN9affm+NvC8tTVEiz1639xJp4LblIiNY3jONA6uJgoJE2V8AujrbfvLsdVZBpFhNb6E27XFEKiIXpeVPuKcmspgpG8vQtifdQMx+Sde0Gt0sktAVbfi6sN8F/+M8IQECBSbRaRjaDMb68Rtt6ErZAgMBAAGjggFLMIIBRzAxBggrBgEFBQcBAQQlMCMwIQYIKwYBBQUHMAGGFWh0dHA6Ly9vY3NwLmJrYXZjYS52bjAdBgNVHQ4EFgQUdAZl/5bCIlzjYqKW/Ud7BCWUuvkwDAYDVR0TAQH/BAIwADAfBgNVHSMEGDAWgBQ96COZf0jtiOd5t6dVPCuwzAOo3TB/BgNVHR8EeDB2MHSgI6Ahhh9odHRwOi8vY3JsLmJrYXZjYS52bi9Ca2F2Q0EuY3Jsok2kSzBJMQ8wDQYDVQQDDAZCa2F2Q0ExGTAXBgNVBAoMEEJrYXYgQ29ycG9yYXRpb24xDjAMBgNVBAcMBUhhbm9pMQswCQYDVQQGEwJWTjAOBgNVHQ8BAf8EBAMCBaAwMwYDVR0lBCwwKgYIKwYBBQUHAwEGCCsGAQUFBwMCBggrBgEFBQcDBAYKKwYBBAGCNwoDDDANBgkqhkiG9w0BAQUFAAOCAQEAZLYtyrs9TLoqb+v27mbgp4RDMFyR2gfO5/5+/P3jLckwd5I7OfouzpI3j3FZOw6MhHrClMxiuUkMF+9Vyv/I6UhaPuJU1EcjXZiZQR5BwjxjPXK6gwp6kqSbqLdGcnn2S8if7pL+GtHCR57OIuYXprombXz4Ov0piDK9OQJK3QtgVBRZEtIm8L3JtzjSlxoUL0JI4zOr1FRLhJz5dPzHzbw7jw27D4EWtXE60jsv+tjSJOuadEfkDYuO6L1KZnvC3o6Q5Acvw9WgHSZSZ2CbQqLjNJFLh2VDPbfLo4Lbto42f+nAbmL8Eje7jW8olODk1/4POd0pnvRkIj2RO194mA==";
		X509Certificate xc = Utility.b64ToX509Certificate(test);
		System.out.println(xc.getSubjectDN());*/
		System.out.println(Utility.isEmailValid("ha-thanh.nguyen@sc.com "));
	}


	public static String digestPassword(String password,
			PasswordAlgorithm passwordAlgorithm) {
		MessageDigest m = null;
		String hashedPassword = null;
		try {
			m = MessageDigest.getInstance(passwordAlgorithm.toString());
			m.update(password.getBytes(), 0, password.length());
			hashedPassword = new BigInteger(1, m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hashedPassword;
	}

	public enum PasswordAlgorithm {
		MD5, SHA1
	}

	public static enum LogInOption {
		DigitalSignature, UserNamePassword
	}

	public enum SessionName {
		Employee, Menu
	}
	
	/**
	 * 
	 * @param minlength
	 *            Do dai be nhat cua pass word
	 * @param maxlength
	 *            Do dai lon nhat cua pass word
	 * @param arrChar
	 *            mang cac ky tu se dung de sinh random
	 * @return tra ve 1 chuoi cac ky tu.
	 */
	/*
	 * public static void main(String[] args) { String str =
	 * Utility.getNewPassword(6, 6, ConstantValue.CM_DIGITS_CHARS);
	 * System.out.println(str); }
	 */
	public static String getNewPassword(int minlength, int maxlength,
			char[] arrChar) {
		int difference = maxlength - minlength;
		char[] password = null;
		Random ran = new Random();
		int passlen = maxlength;
		if (minlength != maxlength)
			passlen = minlength + ran.nextInt(difference);

		password = new char[passlen];
		for (int i = 0; i < passlen; i++) {
			password[i] = arrChar[ran.nextInt(arrChar.length)];
		}
		return new String(password);
	}
	
	public String generateRandomChar(int minlength, int maxlength,
			char[] arrChar) {
		int difference = maxlength - minlength;
		char[] password = null;
		Random ran = new Random();
		int passlen = maxlength;
		if (minlength != maxlength)
			passlen = minlength + ran.nextInt(difference);

		password = new char[passlen];
		for (int i = 0; i < passlen; i++) {
			password[i] = arrChar[ran.nextInt(arrChar.length)];
		}
		return new String(password);
	}

	/**
	 * Ä�á»�c XML Ä‘á»ƒ láº¥y sá»‘ serialNumber trong chuá»—i String kÃ½ sá»‘
	 * 
	 * @param xmlsign
	 * @return
	 */
	public static String readXMLString(String xmlsign) {
		try {
			String mesType = "";
			DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
			dbfac.setNamespaceAware(true);
			Document doc = dbfac.newDocumentBuilder().parse(
					new InputSource(new StringReader(xmlsign)));
			NodeList nMesType = doc.getElementsByTagName("X509SerialNumber");
			if (nMesType.getLength() < 1) {
				return "";
			} else {
				mesType = String.valueOf(nMesType.item(0).getFirstChild()
						.getNodeValue());
				mesType = Utility.bigIntToHex(mesType);
				return mesType;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * @author Nguyá»…n VÄƒn CÆ°á»�ng
	 * @param typeCode
	 *            mÃ£ lá»—i
	 * @param strMessage
	 *            ThÃ´ng bÃ¡o Ä‘i kÃ¨m
	 * @param idRA
	 *            ID cá»§a RA
	 * @param codeCustomer
	 *            mÃ£ khÃ¡ch hÃ ng (mÃ£ sá»‘ thuáº¿, chá»©ng minh thÆ°)
	 * @return
	 */


	public static void writeLog(Logger log, String infomation, String method,
			String className) {
		try {
			log.error(className + " - " + method + " - " + infomation);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Check a character is Hex
	 * 
	 * @author DongDP
	 * @param c
	 * @return
	 */
	public static final boolean isHexStringChar(char c) {
		return (Character.isDigit(c) || Character.isWhitespace(c) || (("0123456789abcdefABCDEF"
				.indexOf(c)) >= 0));
	}

	/**
	 * Check a string is hex string
	 * 
	 * @author DongDP
	 * @param hexData
	 * @return
	 */
	public static final boolean isHex(String hexData) {
		for (int i = 0; i < hexData.length(); i++) {
			if (!isHexStringChar(hexData.charAt(i)))
				return false;
		}
		return true;
	}

	/**
	 * @description HÃ m nÃ y sáº½ replace kÃ½ tá»± oldChar báº±ng kÃ½ tá»± newChar trong
	 *              khoáº£ng tá»« vá»‹ trÃ­ firstIndex Ä‘áº¿n lastIndex
	 * @param str
	 *            Chuá»—i String cáº§n replace
	 * @param firstIndex
	 *            vá»‹ trÃ­ Ä‘áº§u tiÃªn cáº§n replace
	 * @param lastIndex
	 *            Vá»‹ trÃ­ cuá»‘i cáº§n replace kÃ½ tá»±
	 * @param oldChar
	 *            KÃ½ tá»± cáº§n thay tháº¿
	 * @param newChar
	 *            KÃ½ tá»± thay tháº¿
	 * @return tráº£ láº¡i chuá»—i Ä‘áº§u vÃ o Ä‘Ã£ Ä‘Æ°á»£c replace kÃ½ tá»± má»›i trong khoáº£ng Ä‘áº§u
	 *         vÃ o.
	 */
	public static String replaceEx(String str, int firstIndex, int lastIndex,
			String oldChar, String newChar) {
		try {
			String tmp = str.substring(firstIndex, lastIndex);
			tmp = tmp.replace(oldChar, newChar);
			str = str.substring(0, firstIndex) + tmp
					+ str.substring(lastIndex, str.length());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String replaceExRenew(String str, int firstIndex, int lastIndex,
			String oldChar, String newChar) throws Exception{
			String tmp = str.substring(firstIndex, lastIndex);
			tmp = tmp.replace(oldChar, newChar);
			str = str.substring(0, firstIndex) + tmp
					+ str.substring(lastIndex, str.length());
		return str;
	}
	
	/**
	 * HÃ m chuyá»ƒn Ä‘á»•i tiáº¿ng viá»‡t cÃ³ dáº¥u sang khÃ´ng dáº¥u
	 * 
	 * @author DongDP
	 * @param data
	 * @return
	 */
	public static String convertToNonUnicode(String data) {
		for (int i = 0; i < unicodePattern.length; i++) {
			data = Pattern.compile(unicodePattern[i]).matcher(data)
					.replaceAll(replaceCharNonUnicode[i]);
		}
		return data;
	}



	
	static final byte[] HEX_CHAR_TABLE = {
	    (byte)'0', (byte)'1', (byte)'2', (byte)'3',
	    (byte)'4', (byte)'5', (byte)'6', (byte)'7',
	    (byte)'8', (byte)'9', (byte)'a', (byte)'b',
	    (byte)'c', (byte)'d', (byte)'e', (byte)'f'
	  };

	 // private StringUtils () {}

	  public static String getHexString(byte[] raw)
	    throws UnsupportedEncodingException
	  {
	    byte[] hex = new byte[2 * raw.length];
	    int index = 0;

	    for (byte b : raw) {
	      int v = b & 0xFF;
	      hex[index++] = HEX_CHAR_TABLE[v >>> 4];
	      hex[index++] = HEX_CHAR_TABLE[v & 0xF];
	    }
	    return new String(hex, "ASCII");
	  }
	  
	  public String genAuthenticationCode(int customerType, char[] arrChar)
	  {
		  String authCode = "540" + customerType;
		  authCode += "-" + Utility.getNewPassword(4, 4,
					CM_DIGITS_CHARS);
		  authCode += "-" + Utility.getNewPassword(4, 4,
					CM_DIGITS_CHARS);
		  return authCode;
		  
	  }
	  	  
	  
	// Converting a string of hex character to bytes
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
					.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	// Converting a bytes array to string of hex character
	public static String byteArrayToHexString(byte[] b) {
		int len = b.length;
		String data = new String();

		for (int i = 0; i < len; i++) {
			data += Integer.toHexString((b[i] >> 4) & 0xf);
			data += Integer.toHexString(b[i] & 0xf);
		}
		return data;
	}
	
	
	public static String convertDateToTimestamp(Date date) {
		java.sql.Timestamp sq = new java.sql.Timestamp(date.getTime());  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(sq);
	}

	// Cac mang ky tu de sinh pass word
	public static char[] CM_DIGITS_CHARS = { '1', '2', '3', '4', '5', '6', '7',
			'8', '9', '0', 'q', 'Q', 'w', 'W', 'e', 'E', 'r', 'R', 't', 'T',
			'y', 'Y', 'u', 'U', 'i', 'I', 'o', 'O', 'p', 'P', 'a', 'A', 's',
			'S', 'd', 'D', 'f', 'F', 'g', 'G', 'h', 'H', 'j', 'J', 'k', 'K',
			'l', 'L', 'z', 'Z', 'x', 'X', 'c', 'C', 'v', 'V', 'b', 'B', 'n',
			'N', 'm', 'M' };
	
	public static char[] CM_DIGITS = { '1', '2', '3', '4', '5', '6', '7',
		'8', '9', '0'};
	
	public static char[] CM_CHARS = {'q', 'Q', 'w', 'W', 'e', 'E', 'r', 'R', 't', 'T',
		'y', 'Y', 'u', 'U', 'i', 'I', 'o', 'O', 'p', 'P', 'a', 'A', 's',
		'S', 'd', 'D', 'f', 'F', 'g', 'G', 'h', 'H', 'j', 'J', 'k', 'K',
		'l', 'L', 'z', 'Z', 'x', 'X', 'c', 'C', 'v', 'V', 'b', 'B', 'n',
		'N', 'm', 'M' };
	
	public static char[] CM_CHARS_REGISCODE = {'q', 'Q', 'w', 'W', 'e', 'E', 'r', 'R', 't', 'T',
		'y', 'Y', 'u', 'U', 'o', 'O', 'p', 'P', 'a', 'A', 's',
		'S', 'd', 'D', 'f', 'F', 'g', 'G', 'h', 'H', 'j', 'J', 'k', 'K',
		'L', 'z', 'Z', 'x', 'X', 'c', 'C', 'v', 'V', 'b', 'B', 'n',
		'N', 'm', 'M', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
}
