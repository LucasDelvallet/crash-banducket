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
			
			StackElement e = elements.get(i);
			
			if(e.score > lastScore){
				index = i;
				lastScore = e.score;
			}
		}
		
		return index;
	}
	
	public int getStackElementWithSpecificScore(int score){
		for(int i = 0; i < elements.size(); i++){
			StackElement e = elements.get(i);
			if(e.score == score){
				return i;
			}
		}
		
		return -1;
	}
}
