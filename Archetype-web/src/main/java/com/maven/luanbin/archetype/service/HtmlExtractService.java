package com.maven.luanbin.archetype.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.maven.luanbin.archetype.model.Node;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HtmlExtractService {

	private static final Logger LOGGER = LoggerFactory.getLogger(HtmlExtractService.class);

	public String extractFromUrl(String str_url, String encode) {
		HttpClient httpclient = new DefaultHttpClient();
		//httpclient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,  "Mozilla/5.0 (Windows NT 6.1; rv:8.0.1) Gecko/20100101 Firefox/8.0.1");
        HttpGet httpget = new HttpGet(str_url);
		HttpResponse res = null;
		try {
			res = httpclient.execute(httpget);
			HttpEntity entity = res.getEntity();
			String loginEntityContent = EntityUtils.toString(entity, encode);
			return loginEntityContent;
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("无法获取HTTP请求的响应,e={}", e);
		}
		return null;
	}
	
	public List<Node> getNode(String html, String id, String[] strs, String type)
	{
		Document doc=  Jsoup.parse(html);
		List<Node> list=new ArrayList<Node>();
		switch(type)
		{
			case "class":
				Elements elements=doc.getElementsByClass(id);
		        for(Element e: elements)
		        {
		        	List<Element> list_element= getElementList(e, strs);
		        	for(Element ele: list_element)
		        	{
		        		Node node=new Node();
		        		node.setLink(ele.attr("href"));
		        		node.setText(ele.html());
		        		list.add(node);
		        	}
		        }
		        break;
			case "id":
				Element element=doc.getElementById(id);
		        List<Element> list_element= getElementList(element, strs);
		    	for(Element ele: list_element)
		    	{
		    		Node node=new Node();
		    		node.setLink(ele.attr("href"));
		    		node.setText(ele.html());
		    		list.add(node);
		    	}
		}
		return list;
	}
	
	public List<Node> getNodeContent(String html, String id, String[] strs, String type)
	{
		Document doc=  Jsoup.parse(html);
		List<Node> list=new ArrayList<Node>();
		switch(type)
		{
			case "class":
				Elements elements=doc.getElementsByClass(id);
		        for(Element e: elements)
		        {
		        	List<Element> list_element= getElementList(e, strs);
		        	for(Element ele: list_element)
		        	{
		        		Node node=new Node();
		        		node.setText(ele.html());
		        		list.add(node);
		        	}
		        }
		        break;
			case "id":
				Element element=doc.getElementById(id);
		        List<Element> list_element= getElementList(element, strs);
		    	for(Element ele: list_element)
		    	{
		    		Node node=new Node();
		    		node.setText(ele.html());
		    		list.add(node);
		    	}
		}
		return list;
	}
	
	private List<Element> getElementList(Element e, String[] tags)
	{
		List<Element> list=new ArrayList<Element>();
		if(tags.length==0)
		{
			list.add(e);
		}
		else
		{
			Elements temp=e.getElementsByTag(tags[0]);
			if(temp==null)
			{
				list.add(e);
				return list;
			}
			for(int i=1; i<tags.length; i++)
			{
				String str=tags[i];
				Elements temp1=new Elements();
				for(Element e1:temp)
				{
					temp1.addAll(e1.getElementsByTag(str));
				}
				if(temp1.size()==0)
				{
					list.add(e);
					return list;
				}
				temp=temp1;
			}
			for(Element e1:temp)
			{
				list.add(e1);
			}
		}
		return list;
	}

	//获取元素的内部信息,从element逐层剥离tag
	private List<Element> getElementListByTags(Element element, String[] tags){

		List<Element> operateElements = new ArrayList<>();
		operateElements.add(element);

		if(tags.length == 0){
			return operateElements;
		}

		List<Element> nextOperateElements = new ArrayList<>();

		for(String tag : tags){
			for(Element operateElement : operateElements){
				Elements tmp = operateElement.getElementsByTag(tag);
				nextOperateElements.addAll(tmp);
			}
			operateElements = nextOperateElements;
		}

		return operateElements;
	}

	//获取小说列表页信息,包括初始链接以及相应文字
	public List<Node> getListPage(String html, String id, String[] tags, String type) {
		Document doc=  Jsoup.parse(html);
		List<Node> list=new ArrayList<Node>();
		Elements elements = new Elements();
		//获取初始节点
		switch(type) {
			case "class":
				elements=doc.getElementsByClass(id);
				break;
			case "id":
				Element element=doc.getElementById(id);
				elements.add(element);
		}

		if(tags.length == 0){
			List<Node> nodeList = transfer2Node(elements);
			return nodeList;
		}

		//获取根据初始节点,逐层获取列表页的核心信息,包括章节名与章节连接
		for(Element element : elements){
			List<Element> detailElements = getElementListByTags(element, tags);
			List<Node> nodeList = transfer2Node(detailElements);
			list.addAll(nodeList);
		}

		return list;
	}

	//获取小说详情页信息,包括具体内容
	public List<Node> getNovelDetail(String html, String id, String[] tags, String type){
		Document doc=  Jsoup.parse(html);
		List<Node> list=new ArrayList<Node>();
		Elements elements = new Elements();
		//获取初始节点
		switch(type) {
			case "class":
				elements=doc.getElementsByClass(id);
				break;
			case "id":
				Element element=doc.getElementById(id);
				elements.add(element);
		}

		if(tags.length == 0){
			List<Node> nodeList = transfer2Node(elements);
			return nodeList;
		}

		//获取根据初始节点,逐层获取小说详情页信息
		for(Element element : elements){
			List<Element> detailElements = getElementListByTags(element, tags);
			List<Node> nodeList = transfer2Node(detailElements);
			list.addAll(nodeList);
		}

		return list;
	}

	private static List<Node> transfer2Node(List<Element> elementList){
		List<Node> nodeList = new ArrayList<>();

		for(Element ele: elementList) {
			Node node=new Node();
			node.setLink(ele.attr("href"));
			node.setText(ele.html());
			nodeList.add(node);
		}

		return nodeList;
	}
}
