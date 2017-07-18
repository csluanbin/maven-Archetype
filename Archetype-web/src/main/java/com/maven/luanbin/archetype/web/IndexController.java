package com.maven.luanbin.archetype.web;

import com.maven.luanbin.archetype.common.TErrorResult;
import com.maven.luanbin.archetype.common.TResponse;
import com.maven.luanbin.archetype.common.TSuccessResult;
import com.maven.luanbin.archetype.domain.TestDo;
import com.maven.luanbin.archetype.mapper.TestMapper;
import com.maven.luanbin.archetype.model.Chapter;
import com.maven.luanbin.archetype.service.TestService;
import com.maven.luanbin.archetype.service.chapter.ChapterService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luanbin on 10/21/16.
 */
@RestController
@RequestMapping("/")
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private ChapterService chapterService;

    @Resource(type = com.maven.luanbin.archetype.service.TestService2.class)
    private TestService testService;

    @Resource
    private TestMapper testMapper;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "q3boy");
        map.put("password", "q3girl");

        return map.toString();
    }

    @RequestMapping(value = "/testVm", method = RequestMethod.GET)
    public ModelAndView testVm() {
        ModelAndView mav= new ModelAndView();
        mav.addObject("city", "test");
        mav.setViewName("hello");
        return mav;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        List<Chapter> chapterList = chapterService.getAllChapter();
        ModelAndView mav= new ModelAndView();
        mav.setViewName("index");
        mav.addObject("chapterList", chapterList);
        return mav;
    }

    @RequestMapping(value = "/getByName", method = RequestMethod.GET)
    @ResponseBody
    public TResponse getByName(String name) {


        List<TestDo> testDoList = testMapper.getTestByName(name);
        if(!CollectionUtils.isEmpty(testDoList)){
            return new TSuccessResult<>(testDoList);
        }else {
            return new TErrorResult("不存在");
        }
    }
}
