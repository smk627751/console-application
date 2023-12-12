package com.smk627751.chatbotapplication.dao;

import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Repository {
	private static Repository repository;
	private Map<String,Integer> orders = new TreeMap<>();
	private Map<String,Long> price;
	private JSONObject json;
	private Map<String,Map<String,String>> map;
	
	private Repository()
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

	public Map<String,Long> getPrice() {
		return price;
	}

	public void setPrice(Map<String,Long> price) {
		this.price = price;
	}

	public Map<String,Integer> getOrders() {
		return orders;
	}

	public void setOrders(Map<String,Integer> orders) {
		this.orders = orders;
	}
	public static Repository getInstance()
	{
		if(repository == null)
		{
			repository = new Repository();
		}
		
		return repository;
	}
}
