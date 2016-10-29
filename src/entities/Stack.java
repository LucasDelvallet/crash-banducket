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

	public int getMostSignificantStackFrameIndex() {
		int index = 0;
		int lastScore = 0;
		for (int i = 0; i < frames.size(); i++) {

			StackFrame e = frames.get(i);

			if (e.score > lastScore) {
				index = i;
				lastScore = e.score;
			}
		}

		return index;
	}

	public int getStackFrameWithSpecificScore(int score) {
		for (int i = 0; i < frames.size(); i++) {
			StackFrame e = frames.get(i);
			if (e.score == score) {
				return i;
			}
		}

		return -1;
	}
}
