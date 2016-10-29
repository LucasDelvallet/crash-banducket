package entities;

public class Method implements Comparable<Method> {
	public String name;
	public int position;

	public Method(String name, int position) {
		this.name = name;
		this.position = position;
	}

	@Override
	public int compareTo(Method o) {
		return name.compareTo(o.name);
	}
}
