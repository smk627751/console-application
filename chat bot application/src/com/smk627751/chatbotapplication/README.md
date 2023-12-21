# chat bot application:

1. Theme - Food menu bot.
2. knowledge - Java, Files, org.simple.json, JSON.
3. Navigation - stack.
4. Data storage - JSON.

## Features:
1. Language.
2. placeorder
3. view cart
4. price
5. payment.
0. Exit.

## Design:
```
+---------------------------------+
|  1 . English                    |
|  2 . tamil                      |
+---------------------------------+
```
flow:
```
	1. English
		|__ 1. place order
		|	|__1.Sandwich
		|	|__2.Burger
		|		.
		|		.
		|		.
		|
		|__ 2. view cart
		|	|__ sandwich x1
		|	|__ burger x1
		|		.
		|		.
		|		.
		|__ 3. payment
			|
			|__ +----------------------+
			    | sandwich 	    120    |
			    | burger        140    |
			    |	.                  |
			    |	.                  |
			    |	.                  |
			    |                      |
			    +----------------------+
			    |        total:        |
			    +----------------------+		

	2. Tamil
	     |__ // same as English.
```
## Model:
```
class Main {
	Map json;
	Stack<String> stack = new Stack<>();
	static {
		String path = "yourpath/data.json";
		FileReader fileReader = new FileReader(path);
		JSONParser obj = new JSONParser();
		json = (JSONObject) obj.parse(fileReader);
		map = (Map)json.get(<key>);
	}

	getMenu(){
		// print the menu from the map;
	}

	navigate(choice){
		switch(char x){
			case '-': stack.pop(); break;
			default: {
				String page = map.get(choice);
				stack.push(page)
				getMenu(stack.peek())
				}break;
		}
	}
	
	Main(){
		navigate(choice);
	}
}
```
