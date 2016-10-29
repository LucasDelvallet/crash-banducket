package entities;

import java.util.List;

public class Stack {
	private List<StackFrame> elements;
	private String content;

	public Stack(String content, List<StackFrame> elements) {
		this.content = content;
		this.elements = elements;
	}

	public List<StackFrame> getElements() {
		return elements;
	}

	public int getMostSignificantStackElementIndex() {
		int index = 0;
		int lastScore = 0;
		for (int i = 0; i < elements.size(); i++) {

			StackFrame e = elements.get(i);

			if (e.score > lastScore) {
				index = i;
				lastScore = e.score;
			}
		}

		return index;
	}

	public int getStackElementWithSpecificScore(int score) {
		for (int i = 0; i < elements.size(); i++) {
			StackFrame e = elements.get(i);
			if (e.score == score) {
				return i;
			}
		}

		return -1;
	}
}
