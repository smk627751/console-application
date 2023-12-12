package com.smk627751.chatbotapplication.viewmodel;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import com.smk627751.chatbotapplication.dao.Repository;
import com.smk627751.chatbotapplication.view.View;

public class ViewModel {
	private View view;
	private String page;
	StringBuilder str = new StringBuilder();
	private Stack<String> backStack = new Stack<>();
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public Stack<String> getBackStack() {
		return backStack;
	}
	public void setBackStack(Stack<String> backStack) {
		this.backStack = backStack;
	}
	private Repository repository;
	
	public ViewModel(View view)
	{
		this.view = view;
		this.repository = Repository.getInstance();
	}
	public ViewModel() {
		// TODO Auto-generated constructor stub
	}
	public Map<String,String> getMenu(String page)
	{
		Map<String,String> m = repository.getData().get(page);
		str.append("+=======================+\n");
		if(m != null)
		for(Map.Entry<String, String> e : m.entrySet())
		{
			str.append("|\s"+e.getKey()+"\s|\t"+e.getValue());
			if(e.getValue().length() <= 7)
			{
				str.append("\t\t|\n");
			}
			else
			{
				str.append("\t|\n");
			}
		}
		str.append("+=======================+\n");
		if(backStack.size() > 1)
		{
			str.append("Press - for back\n");
		}
		view.onPrint(str.toString());
		str.setLength(0);
		return m;
	}
	
	public Map<String,String> navigate(char choice, Map<String,String> map)
	{
		if(map == null) return getMenu(backStack.peek());
		
		switch(choice)
		{
			case '-':
			{
				if(backStack.size() > 1)
				{
					backStack.pop();
					this.setPage(backStack.peek());;
				}
				else {
					str.append("go next\n");
				}
				break;
			}
			default:{
				this.setPage(map.get(choice+""));
				if(backStack.peek().equals("Place order") || backStack.peek().equals("ஆர�?டர�? வைக�?கவ�?ம�?"))
				{
					Map<String,Integer> orders = repository.getOrders();
					orders.put(this.getPage(),orders.getOrDefault(this.getPage(), 0)+1);
					repository.setOrders(orders);
				}
				else if(this.getPage().equals("View cart") || this.getPage().equals("கார�?ட�?டை பார�?க�?கவ�?ம�?"))
				{
					for(Map.Entry<String, Integer> order : repository.getOrders().entrySet())
					{
						str.append(order.getKey()+" x"+order.getValue()+"\n");
					}
					view.onPrint(str.toString());
					str.setLength(0);
				}
				else if(this.getPage().equals("Payment") || this.getPage().equals("கட�?டணம�?"))
				{
					Long total = (long) 0;
					str.append("+=======================+\n");
					for(Map.Entry<String, Integer> order : repository.getOrders().entrySet())
					{
						String tab = "\t\s\s";
						if(order.getKey().length() > 7)
						{
							tab = "\s\s";
						}
						Long amount = (repository.getPrice().get(order.getKey())*order.getValue());
						str.append("|\t"+order.getKey()+tab+ amount +"\t|\n");
						total += amount;
					}
					str.append("|-----------------------|\n");
					str.append("|\ttotal:\t\s\s"+total+"\t|\n");
					str.append("+=======================+\n");
					Scanner sc = new Scanner(System.in);
					str.append("Press 0 to pay..\n");
					view.onPrint(str.toString());
					str.setLength(0);
					if(sc.next().charAt(0)== '0')
					{
						view.onPrint("Thank you for visiting....\n");
						System.exit(0);
					}
				}
				else
				{
					backStack.push(this.getPage());
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
	public Repository getRepository() {
		return repository;
	}
}
