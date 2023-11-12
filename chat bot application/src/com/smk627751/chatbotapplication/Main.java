package com.smk627751.chatbotapplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

	private static Stack<String> backStack = new Stack<>();
	private static Map<String,Integer> orders = new TreeMap<>();
	private static Map<String,Long> price;
	private static JSONObject json;
	private static Map<String,Map<String,String>> map;
	private static String page;
	
	static {
		try {
			String path = "src/data.json";
			FileReader fileReader = new FileReader(path);
			JSONParser obj = new JSONParser();
			json = (JSONObject) obj.parse(fileReader);
			map = (Map)json.get("page1");
			price = (Map)map.get("price");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Map<String,String> getMenu(String page)
	{
		Map<String,String> m = map.getOrDefault(page,null);
		System.out.println("+=======================+");
		if(m != null)
		for(Map.Entry<String, String> e : m.entrySet())
		{
			System.out.print("|\s"+e.getKey()+"\s|\t"+e.getValue());
			if(e.getValue().length() <= 7)
			{
				System.out.println("\t\t|");
			}
			else
			{
				System.out.println("\t|");
			}
		}
		System.out.println("+=======================+");
		if(backStack.size() > 1)
		{
			System.out.println("Press - for back");
		}
		
		return m;
	}
	
	private static Map<String,String> navigate(char choice, Map<String,String> map)
	{
		if(map == null) return getMenu(backStack.peek());
		
		switch(choice)
		{
			case '-':
			{
				if(backStack.size() > 1)
				{
					backStack.pop();
					page = backStack.peek();
				}
				else {
					System.out.println("go next");
				}
				break;
			}
			default:{
				page = map.get(choice+"");
				if(backStack.peek().equals("Place order") || backStack.peek().equals("ஆர்டர் வைக்கவும்"))
				{
					orders.put(page,orders.getOrDefault(page, 0)+1);
				}
				else if(page.equals("View cart") || page.equals("கார்ட்டை பார்க்கவும்"))
				{
					for(Map.Entry<String, Integer> order : orders.entrySet())
					{
						System.out.println(order.getKey()+" x"+order.getValue());
					}
				}
				else if(page.equals("Payment") || page.equals("கட்டணம்"))
				{
					Long total = (long) 0;
					System.out.println("+=======================+");
					for(Map.Entry<String, Integer> order : orders.entrySet())
					{
						String tab = "\t\s\s";
						if(order.getKey().length() > 7)
						{
							tab = "\s\s";
						}
						Long amount = (price.get(order.getKey())*order.getValue());
						System.out.println("|\t"+order.getKey()+tab+ amount +"\t|");
						total += amount;
					}
					System.out.println("|-----------------------|");
					System.out.println("|\ttotal:\t\s\s"+total+"\t|");
					System.out.println("+=======================+");
				}
				else
				{
					backStack.push(page);
				}
				break;
			}
		}
		if(!backStack.isEmpty())
		{
			map = getMenu(backStack.peek());
		}
		return map;
	}
	
	public static void main(String[] args){
		page = "Language";
		Map<String,String> map = getMenu(page);
		backStack.push(page);
		char choice;
		Scanner sc = new Scanner(System.in);
		do {
			choice = sc.next().charAt(0);
			map = navigate(choice,map);
		}while(sc.hasNext());
	}

}
