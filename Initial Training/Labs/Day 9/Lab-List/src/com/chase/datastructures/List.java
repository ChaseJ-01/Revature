package com.chase.datastructures;

public class List {
	private String[] elements = new String[8];
	
	public String get(int index) {
		return this.elements[index];
	}
	
	public void set(int index, String value) {
		this.elements[index] = value;
	}
	
	public int add(String value) {
		int index = -1;
		
		for(int i = 0; i < elements.length; i++) {
			if(null == elements[i]) {
				elements[i] = value;
				return i;
			}
		}
		
		String[] temp = new String[elements.length*2];
		
		for(int i = 0; i < elements.length; i++) {
			temp[i] = elements[i];
		}
		
		index = elements.length;
		temp[elements.length] = value;
		
		elements = temp;
		
		return index;
	}
	
	public String remove() {
		int index = -1;
		String value = "";
		
		for(int i = elements.length-1; i >= 0; i--) {
			if(null != elements[i]) {
				index = i;
				value = elements[i];
				elements[i] = null;
				break;
			}
		}
		
		if(index == -1) {
			return value;
		}
		
		if(index % 8 == 0 && elements.length > 8 && elements.length > index) {
			String[] temp = new String[index];
			
			for (int i = 0; i < temp.length; i++) {
				temp[i] = elements[i];
			}
			
			elements = temp;
		}
		
		return value;
	}
}
