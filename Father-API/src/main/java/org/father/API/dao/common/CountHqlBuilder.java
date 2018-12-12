package org.father.API.dao.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 取查询条数的帮助类
 * 模块编号：pcitc_wm_dal_common_class_CountHqlBuilder
 * 作       者：pcitc
 * 创建时间：2017/09/17
 * 修改编号：1
 * 描       述：取查询条数的帮助类
 */

public class CountHqlBuilder {
	
	public static String toCountHql(String hql) {
		String arg = CountHqlBuilder.trimStartFrom(CountHqlBuilder.trimEndOrderBy(hql.replace("\r\n", " ").replace("fetch"," ")));
		Pattern r = Pattern.compile("^select\\s+distinct\\s+(.*?)\\s+from.*", Pattern.CASE_INSENSITIVE);
		Matcher m = r.matcher(hql);
		return m.matches()
				? String.format("select nvl(sum((count(*)+1)/(count(*)+1)),0) from %s group by %s", arg, m.group(1))
				: String.format("select count(*) from %s", arg);
	}

	private static String trimEndOrderBy(String hql) {
		Pattern r = Pattern.compile("(.*)\\s+order by.*?", Pattern.CASE_INSENSITIVE);
		Matcher m = r.matcher(hql);
		return m.matches() ? m.group(1).trim() : hql;
	}

	private static String trimStartFrom(String hql) {
		Pattern r = Pattern.compile(".*?from\\s(.*)", Pattern.CASE_INSENSITIVE);
		Matcher m = r.matcher(hql);
		return m.matches() ? m.group(1).trim() : hql;
	}
}
