package com.deloitte.ievolve.utility;

public class QueryConstants {
	
	public static final String GET_KEYWORD_QUERY = "select keyword from ievolve_master_table";
	public static final String GET_CATEGORY_QUERY = "select distinct concat(c.category, ':') as category from ievolve_master_table c where category is not null";
	public static final String GET_CATEGORY_KEYWORD_QUERY = "select distinct concat(c.category, ':', c.keyword) as category from ievolve_master_table c where category is not null";

}
