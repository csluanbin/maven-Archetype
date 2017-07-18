package com.maven.luanbin.archetype.web;

import com.maven.luanbin.archetype.common.CoopCommandConstants;
import com.maven.luanbin.archetype.common.TErrorResult;
import com.maven.luanbin.archetype.common.TResponse;
import com.maven.luanbin.archetype.common.TSuccessResult;
import com.maven.luanbin.archetype.dataobject.ProjectChannelDo;
import com.maven.luanbin.archetype.dataobject.ProjectCommandDo;
import com.maven.luanbin.archetype.dataobject.ProjectDo;
import com.maven.luanbin.archetype.mapper.ProjectMapper;
import com.maven.luanbin.archetype.mapper.UserMappper;
import com.maven.luanbin.archetype.vo.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by luanbin on 17/6/17.
 * 用户提交项目信息入口
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

    private static String BASE_URL = "123.56.233.173/jiameng.html";

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private UserMappper userMappper;

    @Resource
    private HttpServletRequest request;

    @RequestMapping(value = "/getCoopCommandList", method = RequestMethod.GET)
    @ResponseBody
    public TResponse getCoopCommandList(){
        List<CoopCommandVo> coopCommandVoList = new ArrayList<>();

        CoopCommandConstants.CoopCommand[] coopCommands = CoopCommandConstants.CoopCommand.values();

        for(CoopCommandConstants.CoopCommand coopCommand : coopCommands){
            CoopCommandVo coopCommandVo = new CoopCommandVo();

            coopCommandVo.setId(coopCommand.getId());
            coopCommandVo.setName(coopCommand.getName());
            coopCommandVoList.add(coopCommandVo);
        }

        return new TSuccessResult<>(coopCommandVoList);
    }

    @RequestMapping(value = "/insertProject", method = RequestMethod.POST)
    @ResponseBody
    public TResponse insertProject(@RequestBody ProjectRequestVo projectRequestVo, Integer userId){

        if(projectRequestVo == null){
            return new TErrorResult("新增项目信息失败，参数为空");
        }

        if(projectRequestVo.getCoopCommandIdList() == null || projectRequestVo.getCoopCommandIdList().size() == 0){
            return new TErrorResult("新增项目信息失败，合作要求信息为空");
        }

        if(userId == null){
            return new TErrorResult("新增项目信息失败，用户信息为空");
        }

        if(projectRequestVo.getChannelName() == null){
            return new TErrorResult("新增项目信息失败，渠道信息为空");
        }

        if(projectRequestVo.getName() == null){
            return new TErrorResult("新增项目信息失败，项目名称为空");
        }

        //新增项目信息
        ProjectDo projectDo = new ProjectDo();
        projectDo.setUserId(userId);
        projectDo.setName(projectRequestVo.getName());
        projectDo.setCreateTime(new Date());
        projectDo.setModifyTime(new Date());

        projectMapper.insertProject(projectDo);

        //新增项目要求信息

        List<Integer> coopCommandIdList = projectRequestVo.getCoopCommandIdList();

        Integer projectId = projectDo.getId();

        if(projectId == null){
            return new TErrorResult("新增项目信息失败，新增项目失败，主键不存在");
        }

        for(Integer coopCommandId : coopCommandIdList){
            ProjectCommandDo projectCommandDo = new ProjectCommandDo();
            projectCommandDo.setProjectId(projectId);
            projectCommandDo.setProjectCommandId(coopCommandId);
            projectCommandDo.setCreateTime(new Date());
            projectCommandDo.setModifyTime(new Date());
            projectMapper.insertProjectCommand(projectCommandDo);
        }

        ProjectChannelDo projectChannelDo = new ProjectChannelDo();

        projectChannelDo.setProjectId(projectId);
        projectChannelDo.setProjectChannel(projectRequestVo.getChannelName());
        projectChannelDo.setCreateTime(new Date());
        projectChannelDo.setModifyTime(new Date());

        projectMapper.insertProjectChannel(projectChannelDo);


        return new TSuccessResult<>("新增项目信息成功");
    }

    @RequestMapping(value = "/getProjectInfo", method = RequestMethod.GET)
    @ResponseBody
    public TResponse getProjectInfo(Integer userId){

        if(userId == null){
            return new TErrorResult("获取项目要求信息失败，参数为空");
        }

        List<ProjectInfoResponseVo> coopCommandVoList = new ArrayList<>();

        List<ProjectDo> projectDoList = projectMapper.getProjectByUserId(userId);

        if(projectDoList != null){
            for(ProjectDo projectDo : projectDoList){
                ProjectInfoResponseVo infoResponseVo = new ProjectInfoResponseVo();

                infoResponseVo.setProjectId(projectDo.getId());
                infoResponseVo.setProjectName(projectDo.getName());

                coopCommandVoList.add(infoResponseVo);
            }
        }


        return new TSuccessResult<>(coopCommandVoList);
    }

    @RequestMapping(value = "/getProjectCoopCommand", method = RequestMethod.GET)
    @ResponseBody
    public TResponse getProjectCoopCommand(Integer projectId){
        List<ProjectCommandResponseVo> coopCommandVoList = new ArrayList<>();

        List<ProjectCommandDo> projectCommandDoList = projectMapper.getProjectCommandByProjectId(projectId);


        if(projectCommandDoList != null){
            for(ProjectCommandDo projectCommandDo : projectCommandDoList){
                ProjectCommandResponseVo commandResponseVo = new ProjectCommandResponseVo();

                commandResponseVo.setProjectId(projectId);

                Integer commandId = projectCommandDo.getProjectCommandId();
                commandResponseVo.setProjectCommandId(commandId);

                CoopCommandConstants.CoopCommand coopCommand = CoopCommandConstants.CoopCommand.enumOf(commandId);

                if(coopCommand != null){
                    commandResponseVo.setProjectCommandName(coopCommand.getName());
                    coopCommandVoList.add(commandResponseVo);
                }


            }
        }
        return new TSuccessResult<>(coopCommandVoList);
    }

    @RequestMapping(value = "/modifyProjectCommand", method = RequestMethod.POST)
    @ResponseBody
    public TResponse modifyProjectCommand(@RequestBody ProjectRequestVo projectRequestVo, Integer userId){

        if(projectRequestVo == null){
            return new TErrorResult("修改项目要求信息失败，参数为空");
        }

        if(projectRequestVo.getCoopCommandIdList()   == null){
            return new TErrorResult("修改项目要求信息失败，参数为空");
        }

        if(projectRequestVo.getProjectId() == null){
            return new TErrorResult("修改项目要求信息失败，项目信息为空");
        }

        Integer projectId = projectRequestVo.getProjectId();
        //删除现有的合作要求信息
        projectMapper.deleteCommandByProjectId(projectId);

        List<Integer> coopCommandIdList = projectRequestVo.getCoopCommandIdList();

        //新增新的合作要求信息
        for(Integer coopCommandId : coopCommandIdList){
            ProjectCommandDo projectCommandDo = new ProjectCommandDo();
            projectCommandDo.setProjectId(projectId);
            projectCommandDo.setProjectCommandId(coopCommandId);
            projectCommandDo.setModifyTime(new Date());
            projectCommandDo.setCreateTime(new Date());
            projectMapper.insertProjectCommand(projectCommandDo);
        }

        return new TSuccessResult<>("修改项目要求信息成功");
    }

    @RequestMapping(value = "/getProjectChannel", method = RequestMethod.GET)
    @ResponseBody
    public TResponse getProjectChannel(Integer userId, Integer projectId){
        List<ProjectChannelResponseVo> channelResponseVoList = new ArrayList<>();

        List<ProjectChannelDo> projectChannelDoList = projectMapper.getProjectChannelByProjectId(projectId);

        if(projectChannelDoList != null){

            for(ProjectChannelDo projectChannelDo : projectChannelDoList){
                ProjectChannelResponseVo  responseVo = new ProjectChannelResponseVo();

                responseVo.setChannelId(projectChannelDo.getId());
                responseVo.setChannelName(projectChannelDo.getProjectChannel());
                responseVo.setUrl(formatUrl(userId, projectId, projectChannelDo.getId()));

                channelResponseVoList.add(responseVo);
            }

        }

        return new TSuccessResult<>(channelResponseVoList);
    }

    private static String formatUrl(Integer userId, Integer projectId, Integer channelId){
        return BASE_URL+"?userId="+userId+"&projectId="+projectId+"&channelId="+channelId;
    }

    @RequestMapping(value = "/addProjectChannel", method = RequestMethod.POST)
    @ResponseBody
    public TResponse addProjectChannel(@RequestBody ProjectChannelRequestVo requestVo){

        if(requestVo == null){
            return new TErrorResult("新增项目渠道信息失败，参数为空");
        }

        if(requestVo.getChannelName() == null){
            return new TErrorResult("新增项目渠道信息失败，渠道信息为空");
        }

        if(requestVo.getProjectId() == null){
            return new TErrorResult("新增项目渠道信息失败，项目id为空");
        }

        ProjectChannelDo projectChannelDo= new ProjectChannelDo();
        projectChannelDo.setProjectId(requestVo.getProjectId());
        projectChannelDo.setProjectChannel(requestVo.getChannelName());
        projectChannelDo.setCreateTime(new Date());
        projectChannelDo.setModifyTime(new Date());

        projectMapper.insertProjectChannel(projectChannelDo);

        return new TSuccessResult<>("新增项目渠道信息成功");
    }

    @RequestMapping(value = "/getProjectById", method = RequestMethod.GET)
    @ResponseBody
    public TResponse getProjectById(Integer projectId){

        if(projectId == null){
            return new TErrorResult("获取项目信息失败，渠道信息为空");
        }

        ProjectDo projectDo = projectMapper.getProjectById(projectId);
        return new TSuccessResult<>(projectDo);
    }
}
