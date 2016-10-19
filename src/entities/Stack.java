package entities;

import java.util.List;

public class Stack {
	private List<StackElement> elements;
	private String content;
	
	public Stack(String content, List<StackElement> elements){
		this.content = content;
		this.elements = elements;
	}
	
	public List<StackElement> getElements() {
	    return elements;
	}
}
