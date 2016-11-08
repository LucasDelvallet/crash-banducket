package entities;

public class StackFrame {
	private String source;
	public String addr;
	public String method;
	public String arguments;
	public String path;
	public String vars;

	public StackFrame(String source, String addr, String method, String arguments, String path, String vars) {
		this.source = source;
		this.addr = addr;
		this.method = method;
		this.arguments = arguments;
		this.path = path;
		this.vars = vars;
	}


	public String getSource() {
		return source;
	}
}
