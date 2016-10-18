package entities;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
	private int id;
	private List<Stack> stacks;
	
	public Bucket(int id){
		this.id = id;
		stacks = new ArrayList<Stack>();
	}
}
