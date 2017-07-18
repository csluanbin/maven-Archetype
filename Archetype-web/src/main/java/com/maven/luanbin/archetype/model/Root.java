package com.maven.luanbin.archetype.model;

public class Root {
	private String url;
	private String encode;
	private String type;
	private String tag;
	private String elements;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEncode() {
		return encode;
	}
	public void setEncode(String encode) {
		this.encode = encode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tage) {
		this.tag = tage;
	}
	public String getElements() {
		return elements;
	}
	public void setElements(String elements) {
		this.elements = elements;
	}
	public String toString()
	{
		return "url---"+url+"---encode---"+encode+"---type---"+type+"---tag---"+tag+"---elements---"+elements;
	}
}
