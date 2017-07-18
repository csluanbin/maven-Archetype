package com.maven.luanbin.archetype.model;

public class Next {
	private String type;
	private String tag;
	private String elements;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getElements() {
		return elements;
	}
	public void setElements(String elements) {
		this.elements = elements;
	}
	public String toString()
	{
		return "type---"+type+"---tage---"+tag+"---elements---"+elements;
	}
}
