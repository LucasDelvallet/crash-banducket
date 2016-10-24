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
	
	public int getMostSignificantStackElementIndex(){
		
		int index = 0;
		int lastScore = 0;
		for(int i = 0; i < elements.size(); i++){
			int score = 0;
			StackElement e = elements.get(i);
			if(!e.addr.equals("")){
			//	score++;
			}
			if(!e.method.equals("") && !e.method.equals("??")){
				score++;
			}
			if(!e.path.equals("")){
			//	score++;
			}
			if(!e.arguments.equals("") && !e.arguments.equals("()")){
			//	score++;
			}
			if(!e.vars.equals("")){
			//	score++;
			}
			if(score > lastScore){
				index = i;
				lastScore = score;
			}
		}
		
		
		return index;
	}
}
