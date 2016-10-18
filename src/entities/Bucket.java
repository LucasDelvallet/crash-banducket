package entities;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
	private String id;
	private List<Stack> stacks;
	
	public Bucket(String id, List<Stack> stacks){
		this.id = id;
		this.stacks = stacks;
	}
}