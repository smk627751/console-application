package com.smk627751.chatbotapplication.dao;

import java.io.FileReader;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Repository {
	private static Map<String,Integer> orders = new TreeMap<>();
	private static Map<String,Long> price;
	private static JSONObject json;
	private static Map<String,Map<String,String>> map;
	private static String page;
	
	public Repository()
	{
		readData();
	}
	public void readData()
	{
		try {
			String path = "src/com/smk627751/chatbotapplication/dao/data.json";
			FileReader fileReader = new FileReader(path);
			JSONParser obj = new JSONParser();
			json = (JSONObject) obj.parse(fileReader);
			map = (Map)json.get("page1");
			setPrice((Map)map.get("price"));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Map<String,Map<String,String>> getData()
	{
		return map;
	}
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		Repository.page = page;
	}

	public Map<String,Long> getPrice() {
		return price;
	}

	public void setPrice(Map<String,Long> price) {
		Repository.price = price;
	}

	public Map<String,Integer> getOrders() {
		return orders;
	}

	public void setOrders(Map<String,Integer> orders) {
		Repository.orders = orders;
	}
}
