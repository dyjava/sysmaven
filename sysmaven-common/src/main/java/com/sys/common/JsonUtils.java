package com.sys.common;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {

	public static String bean2json(Object bean) {
		Gson gson = new GsonBuilder().create();
		//registerTypeAdapter(java.sql.Date.class,
		//		new SQLDateSerializer()).registerTypeAdapter(
		//		java.util.Date.class, new UtilDateSerializer()).setDateFormat(
		//		DateFormat.LONG).setPrettyPrinting().create();
		return gson.toJson(bean);
	}

	public static Object json2bean(String json, Type type) {
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(json, type);
	}
	
	public static <T> T json2bean(String json, Class<T> type) {
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(json, type);
	}
}
