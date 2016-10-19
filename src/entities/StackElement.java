package entities;

import java.util.List;

public class StackElement {
    private String source;
    public String addr;
    public String method;
    public List<Argument> arguments;
    public String path;
    public String vars;
    
    public StackElement(String source, String addr, String method, List<Argument> arguments, String path, String vars) {
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
