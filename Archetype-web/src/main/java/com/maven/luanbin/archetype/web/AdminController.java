package com.maven.luanbin.archetype.web;

import com.maven.luanbin.archetype.model.Chapter;
import com.maven.luanbin.archetype.model.Detail;
import com.maven.luanbin.archetype.service.chapter.ChapterService;
import com.maven.luanbin.archetype.service.detail.DetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by luanbin on 17/2/14.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Resource
    private ChapterService chapterService;

    @Resource
    private DetailService detailService;

    @InitBinder("chapter")
    public void initBinderChapter(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("chapter.");
    }

    @InitBinder("detail")
    public void initBinderdetail(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("detail.");
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void importChapter(@ModelAttribute("chapter") Chapter chapter, @ModelAttribute("detail") Detail detail) {

        Long id = chapterService.addChapter(chapter);

        detail.setChapterId(id);
        detail.setEncode(chapter.getEncode());

        detailService.addDetail(detail);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView importUrl() {
        ModelAndView mav= new ModelAndView();
        mav.setViewName("importChapter");
        return mav;
    }
}
