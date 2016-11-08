package entities;

import java.util.List;

public class Stack {
	private List<StackFrame> frames;
	private String content;

	public Stack(String content, List<StackFrame> frames) {
		this.content = content;
		this.frames = frames;
	}

	public List<StackFrame> getFrames() {
		return frames;
	}
}
