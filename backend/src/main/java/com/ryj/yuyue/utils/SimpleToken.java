package com.ryj.yuyue.utils;

/**
 * 对应web前端的通信类
 * @author Thor
 *
 */
public class SimpleToken {
	
	private String label;
	
	private String value;
	
	public SimpleToken(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}

	public SimpleToken() {
		super();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SimpleToken [label=" + label + ", value=" + value + "]";
	}
	
}
