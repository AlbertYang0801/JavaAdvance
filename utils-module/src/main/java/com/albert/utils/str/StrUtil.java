package com.albert.utils.str;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class StrUtil {
	
	public static String jointParam(String rootId) {
		log.debug("rootId is {}", rootId);
		StringBuilder builder = new StringBuilder();
		String[] rootArr = rootId.split(",");
		for (String root : rootArr) {
			builder.append("'").append(root).append("'").append(",");
		}
		if (builder.length() != 0) {
			builder.deleteCharAt(builder.length()-1);
		}
		return builder.toString();
	}
	public static String jointParam(String str, String regex) {
		StringBuilder builder = new StringBuilder();
		String[] rootArr = str.split(",");
		for (String root : rootArr) {
			builder.append(regex).append(root).append(regex).append(",");
		}
		if (builder.length() != 0) {
			builder.deleteCharAt(builder.length()-1);
		}
		return builder.toString();
	}

	public static String unicodeToString(String str) {
		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
			ch = (char) Integer.parseInt(matcher.group(2), 16);
			str = str.replace(matcher.group(1), ch+"" );
		}
		return str;
	}
	public static String jointParam(List<String> list) {
		log.debug("list size is {}", list.size());
		StringBuilder builder = new StringBuilder();
		for (String root : list) {
			String[] rootArr = root.split(",");
			for (String root1 : rootArr) {
				builder.append("'").append(root1).append("'").append(",");
			}
		}
		if (builder.length() != 0) {
			builder.deleteCharAt(builder.length()-1);
		}
		return builder.toString();
	}
	public static String jointParam(Collection<String> list) {
		log.info("list size is {}", list.size());
		StringBuilder builder = new StringBuilder();
		for (String root : list) {
			String[] rootArr = root.split(",");
			for (String root1 : rootArr) {
				builder.append("'").append(root1).append("'").append(",");
			}
		}
		if (builder.length() != 0) {
			builder.deleteCharAt(builder.length()-1);
		}
		return builder.toString();
	}
	public static String jointParamSimple(Collection<String> list) {
		StringBuilder builder = new StringBuilder();
		for (String root : list) {
			builder.append("'").append(root).append("'").append(",");
		}
		if (builder.length() != 0) {
			builder.deleteCharAt(builder.length()-1);
		}
		return builder.toString();
	}
	public static String connectParam(List<String> list) {
		log.info("list size is {}", list.size());
		StringBuilder builder = new StringBuilder();
		for (String root : list) {
			String[] rootArr = root.split(",");
			for (String root1 : rootArr) {
				builder.append(root1).append(",");
			}
		}
		if (builder.length() != 0) {
			builder.deleteCharAt(builder.length()-1);
		}
		return builder.toString();
	}
	
	public static <T> String connectData(List<T> list) {
		StringBuilder builder = new StringBuilder();
		for (T t : list) {
			builder.append("\"").append(t).append("\"").append(",");
		}
		if (builder.length() != 0) {
			builder.deleteCharAt(builder.length()-1);
		}
		return builder.toString();
	}
	
	public static String jointParam(Set<String> appIdSet) {
		log.info("list size is {}", appIdSet.size());
		StringBuilder builder = new StringBuilder();
		for (String root : appIdSet) {
			builder.append("'").append(root).append("'").append(",");
		}
		if (builder.length() != 0) {
			builder.deleteCharAt(builder.length()-1);
		}
		return builder.toString();
	}
	public static String jointParamSimple(Set<Long> appIdSet) {
		log.debug("list size is {}", appIdSet.size());
		StringBuilder builder = new StringBuilder();
		for (Long root : appIdSet) {
			builder.append(root).append(",");
		}
		if (builder.length() != 0) {
			builder.deleteCharAt(builder.length()-1);
		}
		return builder.toString();
	}
	
	public static Double stringToDouble(String dataStr) {
		Double data;
		try {
			data = Double.valueOf(dataStr);
		} catch (Exception e) {
			data = 0.0;
		}
		return data;
	}
	public static Integer stringToInt(String dataStr) {
		Integer data;
		try {
			data = Integer.valueOf(dataStr);
		} catch (Exception e) {
			data = 0;
		}
		return data;
	}
	
	public static long strToLongMultiplyThousand(String data) {
		return Long.valueOf(data)*1000;
	}
	
	public static String trimStr(String data) {
		int index = data.indexOf("G");
		if (index > -1) {
			data = data.substring(0, index);
			return data;
		}
		return data;
	}
	public static String trimStr(String data, String regex) {
		if (data != null) {
			int index = data.indexOf(regex);
			if (index > -1) {
				data = data.substring(0, index);
				return data;
			}
		}
		return data;
	}

	public static String getSuffix(String data, String regex) {
		if (data != null) {
			int index = data.lastIndexOf(regex);
			if (index > -1) {
				return data.substring(index+1);
			}
		}
		return data;
	}

}
