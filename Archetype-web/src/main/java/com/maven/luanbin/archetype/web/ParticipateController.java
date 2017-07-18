package com.maven.luanbin.archetype.web;

import com.maven.luanbin.archetype.common.*;
import com.maven.luanbin.archetype.dataobject.ActivityDo;
import com.maven.luanbin.archetype.dataobject.ClueDo;
import com.maven.luanbin.archetype.dataobject.ProjectCommandDo;
import com.maven.luanbin.archetype.dataobject.UserDo;
import com.maven.luanbin.archetype.mapper.ActivityMappper;
import com.maven.luanbin.archetype.mapper.ClueMappper;
import com.maven.luanbin.archetype.mapper.ProjectMapper;
import com.maven.luanbin.archetype.mapper.UserMappper;
import com.maven.luanbin.archetype.vo.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by luanbin on 17/6/17.
 * 加盟商提交合作信息入口
 */
@RestController
@RequestMapping("/participate")
public class ParticipateController {

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private ClueMappper clueMappper;

    @Resource
    private ActivityMappper activityMappper;

    @RequestMapping(value = "/getProjectCommand", method = RequestMethod.GET)
    @ResponseBody
    public TResponse getProjectCommand(Integer channelId, Integer projectId, Integer userId) {

        if(channelId == null){
            return new TErrorResult("注册失败，渠道id错误");
        }

        if(projectId == null){
            return new TErrorResult("注册失败，项目id错误");
        }

        if(userId == null){
            return new TErrorResult("注册失败，用户id错误");
        }

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

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public TResponse participate(@RequestBody ParticipateRequestVo requestVo, Integer userId){
        if(requestVo == null){
            return new TErrorResult("上传合作信息失败，参数映射失败");
        }
        if(requestVo.getChannelId() == null){
            return new TErrorResult("上传合作信息失败，获取渠道信息失败");
        }
        if(requestVo.getProjectId() == null){
            return new TErrorResult("上传合作信息失败，获取项目信息失败");
        }
        if(requestVo.getRequestVoList() == null || requestVo.getRequestVoList().size() == 0){
            return new TErrorResult("上传合作信息失败，获取合作信息失败");
        }

        Integer channelId = requestVo.getChannelId();
        Integer projectId = requestVo.getProjectId();
        List<ParticipateCommandRequestVo> requestVos = requestVo.getRequestVoList();
        Date date = new Date();

        ClueDo clueDo = new ClueDo();
        clueDo.setProjectId(projectId);
        clueDo.setCreateTime(date);
        clueDo.setStatus(AuditEnum.SUSPEND.getId());
        clueDo.setProjectChannelId(channelId);

        clueMappper.insert(clueDo);

        Integer clueId = clueDo.getId();


        for(ParticipateCommandRequestVo commandRequestVo : requestVos){
            ActivityDo activityDo = new ActivityDo();

            activityDo.setCommandId(commandRequestVo.getCommandId());
            activityDo.setCommandInfo(commandRequestVo.getCommandInfo());
            activityDo.setCreateTime(date);
            activityDo.setProjectChannelId(channelId);
            activityDo.setProjectId(projectId);
            activityDo.setClueId(clueId);

            activityMappper.insertActivity(activityDo);

        }

        return new TSuccessResult<>("上传合作信息成功");
    }
}
