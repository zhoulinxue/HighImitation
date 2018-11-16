package com.zx.api;

import java.io.UnsupportedEncodingException;

public interface JsonParser {

	// 转化为json
	public String ObjectToJsonString(Object object) throws UnsupportedEncodingException;

	//解析 对象
	public Object parseObject(String json, Class<?> clazz) throws UnsupportedEncodingException;

	// 解析集合
	public Object parseArray(String json, Class<?> clazz) throws UnsupportedEncodingException;
}
