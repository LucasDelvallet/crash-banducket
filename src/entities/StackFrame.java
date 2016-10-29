package entities;

public class StackFrame {
	private String source;
	public String addr;
	public String method;
	public String arguments;
	public String path;
	public String vars;
	public int score;

	public StackFrame(String source, String addr, String method, String arguments, String path, String vars) {
		this.source = source;
		this.addr = addr;
		this.method = method;
		this.arguments = arguments;
		this.path = path;
		this.vars = vars;
		computeScore();
	}

	private void computeScore() {
		score = 0;

		if (!this.addr.equals("")) {
			score++;
		}
		if (!this.method.equals("") && !this.method.equals("??")) {
			score++;
		}
		if (!this.path.equals("")) {
			score++;
		}
		if (!this.arguments.equals("") && !this.arguments.equals("()")) {
			// score++;
		}
		if (!this.vars.equals("")) {
			// score++;
		}
	}

	public String getSource() {
		return source;
	}
}
