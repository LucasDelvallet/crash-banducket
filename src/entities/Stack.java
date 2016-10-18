package entities;

import java.util.List;

public class Stack {
	private List<StackElement> elements;
	private String content;
	
	public Stack(String content){
		this.content = content;
	}
}
