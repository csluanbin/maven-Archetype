package com.maven.luanbin.archetype.web;

import com.maven.luanbin.archetype.model.Next;
import com.maven.luanbin.archetype.model.Node;
import com.maven.luanbin.archetype.model.Root;
import com.maven.luanbin.archetype.service.HtmlExtractService;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/spiderindex")
public class SpiderIndexController {
	
	private int num=5;
	
	@Autowired
	private HtmlExtractService htmlExtractService;
	@Autowired 
	private ServletContext servletContext;
	@Autowired 
	private HttpServletRequest request;
	
	@InitBinder("root")  
    public void initBinder1(WebDataBinder binder) {  
		binder.setFieldDefaultPrefix("root.");  
    }
	@InitBinder("next")  
    public void initBinder2(WebDataBinder binder) {  
		binder.setFieldDefaultPrefix("next.");
    }

	@RequestMapping("/getList")
	public List<Node> getList(@ModelAttribute("root") Root root, @ModelAttribute("next") Next next){
		String pageContent = htmlExtractService.extractFromUrl(root.getUrl(), root.getEncode());
		String sonListStr = root.getElements();
		String[] tags = sonListStr.split(";");
		List<Node> list=htmlExtractService.getListPage(pageContent, root.getTag(), tags, root.getType());
		return list;
	}

	@RequestMapping("/getDetail")
	public List<Node> getDetail(String url, String tag, String tagStr, String encode){
		url = "http://www.23wx.com/html/53/53734/21696271.html";
		String pageContent = htmlExtractService.extractFromUrl(url, encode);

		String[] tags = tagStr.split(";");
		List<Node> list=htmlExtractService.getNovelDetail(pageContent, tag, tags, "id");
		return list;
	}

	@RequestMapping
	public ModelAndView add(@ModelAttribute("root") Root root, @ModelAttribute("next") Next next){

		ModelAndView mav = new ModelAndView("spider");
		String str;
		try 
		{
			str = htmlExtractService.extractFromUrl(root.getUrl(), root.getEncode());
			
			String top_elements=root.getElements();
			String[] strs=top_elements.split(";");
			
			List<Node> list=htmlExtractService.getNode(str, root.getTag(), strs, root.getType());
			int len=list.size();
			int num=(len/5);
			if((len%5)!=0)
			{
				num=num+1;
			}
			mav.addObject("num", num);
			mav.addObject("size", len);
			mav.addObject("list", list);
			
			String next_elements=next.getElements();
			String next_tag=next.getTag();
			String next_id=next.getType();
			
			servletContext.setAttribute("query_list", list);
			servletContext.setAttribute("url", root.getUrl());
			servletContext.setAttribute("next_id", next_id);
			servletContext.setAttribute("next_tag", next_tag);
			servletContext.setAttribute("encode", root.getEncode());
			
			if((next_elements==null)||(next_elements.trim().isEmpty()))
			{
				servletContext.setAttribute("next_elements", new String[0]);
			}
			else
			{
				servletContext.setAttribute("next_elements", next_elements.split(";"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
}
