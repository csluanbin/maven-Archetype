package com.maven.luanbin.archetype.web;


import javax.servlet.ServletContext;

import com.maven.luanbin.archetype.model.Node;
import com.maven.luanbin.archetype.service.HtmlExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/query.do")
public class QueryController {
	
	@Autowired
	private HtmlExtractService htmlExtractService;
	@Autowired 
	private ServletContext servletContext;
	
	@RequestMapping
	public ModelAndView add(@RequestParam(value = "queryid") int id){
		System.out.println("you are going to query "+id);
		List<Node> list=(List<Node>) servletContext.getAttribute("query_list");
		ModelAndView mav = new ModelAndView("query");
		String url=servletContext.getAttribute("url")+list.get(id).getLink();
		String encode=(String) servletContext.getAttribute("encode");
		try 
		{
			String str = htmlExtractService.extractFromUrl(url, encode);
			String[] strs=new String[0];
			List<Node> nodes=htmlExtractService.getNode(str, (String) servletContext.getAttribute("next_tag"), (String[]) servletContext.getAttribute("next_elements"), (String) servletContext.getAttribute("next_id"));
			String content="";
			for(Node node:nodes)
			{
				content=content+node.getText();
				mav.addObject("id", content);
			}
		} catch (Exception e) {
			mav.addObject("id", "error");
			e.printStackTrace();
		}
		return mav;
	}
}
