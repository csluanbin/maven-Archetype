package com.maven.luanbin.archetype.web;

import com.maven.luanbin.archetype.common.*;
import com.maven.luanbin.archetype.dataobject.ActivityDo;
import com.maven.luanbin.archetype.dataobject.ClueDo;
import com.maven.luanbin.archetype.dataobject.ClueHistoryDo;
import com.maven.luanbin.archetype.dataobject.ProjectChannelDo;
import com.maven.luanbin.archetype.mapper.ActivityMappper;
import com.maven.luanbin.archetype.mapper.ClueHistoryMappper;
import com.maven.luanbin.archetype.mapper.ClueMappper;
import com.maven.luanbin.archetype.mapper.ProjectMapper;
import com.maven.luanbin.archetype.util.DateUtil;
import com.maven.luanbin.archetype.util.ExcelUtil;
import com.maven.luanbin.archetype.vo.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by luanbin on 17/6/21.
 * 用户提交项目信息入口
 */
@RestController
@RequestMapping("/clueManage")
public class ClueManageController {

    @Resource
    private ClueMappper clueMappper;

    @Resource
    private ActivityMappper activityMappper;

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private ClueHistoryMappper clueHistoryMappper;

    @RequestMapping(value = "/getClueList", method = RequestMethod.GET)
    @ResponseBody
    public TResponse getClueList(ClueQueryParam clueQueryParam){
        if(clueQueryParam == null){
            return new TErrorResult("获取线索信息失败，参数映射失败");
        }
        if(clueQueryParam.getProjectId() == null){
            return new TErrorResult("获取线索信息失败，项目信息失败");
        }

        Integer projectId = clueQueryParam.getProjectId();
        Integer status = clueQueryParam.getStatusId();
        Integer channelId = clueQueryParam.getChannelId();
        List<ClueDo> clueDoList =  clueMappper.getClueDoByProjectId(projectId, channelId, status);

        return new TSuccessResult<>(assemble(clueDoList));
    }

    private List<ClueResVo> assemble(List<ClueDo> clueDoList){
        List<ClueResVo> clueResVos = new ArrayList<>();

        if(clueDoList != null){
            for(ClueDo clueDo : clueDoList){
                ClueResVo clueResVo = new ClueResVo();
                clueResVo.setClueId(clueDo.getId());
                clueResVo.setStatus(clueDo.getStatus());
                clueResVo.setChannelId(clueDo.getProjectChannelId());

                String time = DateUtil.dataToString(clueDo.getCreateTime());

                clueResVo.setTime(time);

                ProjectChannelDo projectChannelDo = projectMapper.getProjectChannelByChannelId(clueDo.getProjectChannelId());
                clueResVo.setChannelName(projectChannelDo.getProjectChannel());

                List<ActivityDo> activityDoList = activityMappper.getActivityByClueId(clueDo.getId());

                List<ActivityVo> activityVoList = new ArrayList<>();

                if(activityDoList != null){
                    for(ActivityDo activityDo : activityDoList){
                        ActivityVo activityVo = new ActivityVo();

                        Integer commandId = activityDo.getCommandId();

                        activityVo.setCommandId(activityDo.getCommandId());
                        activityVo.setCommandInfo(activityDo.getCommandInfo());

                        CoopCommandConstants.CoopCommand coopCommand  = CoopCommandConstants.CoopCommand.enumOf(commandId);
                        if(coopCommand != null){
                            activityVo.setCommandName(coopCommand.getName());
                        }
                        activityVoList.add(activityVo);
                    }

                }
                clueResVo.setActivityVoList(activityVoList);
                clueResVos.add(clueResVo);
            }
        }
        return clueResVos;
    }

    @RequestMapping(value = "/saveClueHistory", method = RequestMethod.POST)
    @ResponseBody
    public TResponse saveClueHistory(@RequestBody ClueHistoryRequestVo clueHistoryRequestVo){
        if(clueHistoryRequestVo == null){
            return new TErrorResult("保存线索历史失败，参数映射失败");
        }
        if(clueHistoryRequestVo.getClueId() == null){
            return new TErrorResult("保存线索历史失败，获取线索信息失败");
        }
        clueMappper.updateStatus(clueHistoryRequestVo.getClueId(), clueHistoryRequestVo.getStatus());

        ClueHistoryDo clueHistoryDo = new ClueHistoryDo();
        Date date = new Date();

        clueHistoryDo.setClueId(clueHistoryRequestVo.getClueId());
        clueHistoryDo.setCreateTime(date);
        clueHistoryDo.setDetail(clueHistoryRequestVo.getDetail());
        clueHistoryDo.setModifyTime(date);
        clueHistoryDo.setUserName(clueHistoryRequestVo.getUserName());
        clueHistoryDo.setStatus(clueHistoryRequestVo.getStatus());

        clueHistoryMappper.insert(clueHistoryDo);

        return new TSuccessResult<>("保存线索历史成功");
    }

    @RequestMapping(value = "/getClueDetail", method = RequestMethod.GET)
    @ResponseBody
    public TResponse getClueDetail(Integer clueId){
        ClueDo clueDo = clueMappper.getClueDoByClueId(clueId);
        List<ActivityDo> activityDoList = activityMappper.getActivityByClueId(clueId);
        List<ClueHistoryDo> clueHistoryDoList = clueHistoryMappper.getClueHistoryDoByClueId(clueId);
        ClueDetailResVo clueDetailResVo = combineDetail(clueDo, activityDoList, clueHistoryDoList);

        if(clueDetailResVo != null){
            return new TSuccessResult<>(clueDetailResVo);
        }else {
            return new TErrorResult("获取线索详情失败");
        }


    }

    private ClueDetailResVo combineDetail(ClueDo clueDo, List<ActivityDo> activityDoList, List<ClueHistoryDo> clueHistoryDoList){
        if(clueDo == null){
            return null;
        }
        if(activityDoList == null){
            return null;
        }
        if(clueHistoryDoList == null){
            return null;
        }
        ClueDetailResVo clueDetailResVo = new ClueDetailResVo();

        clueDetailResVo.setClueId(clueDo.getId());

        String time = DateUtil.dataToString(clueDo.getCreateTime());
        clueDetailResVo.setCreateTime(time);
        clueDetailResVo.setStatus(clueDo.getStatus());

        List<ActivityVo> activityVoList = new ArrayList<>();
        for(ActivityDo activityDo : activityDoList){
            ActivityVo activityVo = new ActivityVo();
            Integer commandId = activityDo.getCommandId();
            activityVo.setCommandInfo(activityDo.getCommandInfo());
            activityVo.setCommandId(commandId);

            CoopCommandConstants.CoopCommand coopCommand = CoopCommandConstants.CoopCommand.enumOf(commandId);

            if(coopCommand != null){
                activityVo.setCommandName(coopCommand.getName());
            }

            activityVoList.add(activityVo);
        }

        clueDetailResVo.setActivityVoList(activityVoList);

        List<ClueHistoryResVo> clueHistoryResVoList = new ArrayList<>();

        for(ClueHistoryDo clueHistoryDo : clueHistoryDoList){
            ClueHistoryResVo clueHistoryResVo = new ClueHistoryResVo();

            clueHistoryResVo.setClueId(clueHistoryDo.getClueId());

            clueHistoryResVo.setDetail(clueHistoryDo.getDetail());
            clueHistoryResVo.setUserName(clueHistoryDo.getUserName());

            String time1 = DateUtil.dataToString(clueHistoryDo.getCreateTime());

            clueHistoryResVo.setCreateTime(time1);

            clueHistoryResVoList.add(clueHistoryResVo);
        }
        clueDetailResVo.setClueHistoryResVoList(clueHistoryResVoList);

        return clueDetailResVo;
    }

    @RequestMapping(value = "/getChannelByProject", method = RequestMethod.GET)
    @ResponseBody
    public TResponse getChannelByProject(Integer projectId){
        List<ProjectChannelDo> projectChannelDoList = projectMapper.getProjectChannelByProjectId(projectId);
        return new TSuccessResult<>(projectChannelDoList);
    }

    @RequestMapping(value = "/downloadexcel", method = RequestMethod.GET)
    @ResponseBody
    public TResponse downloadexcel(HttpServletResponse response){
        List<LinkedHashMap<String, Object>> results = new ArrayList<>();
        for (int i=0; i<10; i++) {
            LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
            responseMap.put("name", "luanbin");
            responseMap.put("status", 1);
            responseMap.put("url", "www.meituan.com");
            results.add(responseMap);
        }
        ExcelUtil.downLoadExcel(response, "wx_incentive_fund_dis_approve.xlsx", "sheet1", results);
            return new TSuccessResult<>("成功");
    }

    @RequestMapping(value = "/getChannelDistribute", method = RequestMethod.GET)
    @ResponseBody
    public TResponse getChannelDistribute(Integer projectId, HttpServletResponse response){
        List<LinkedHashMap<String, Object>> results = new ArrayList<>();

        List<ProjectChannelDo> projectChannelDoList = projectMapper.getProjectChannelByProjectId(projectId);

        if(projectChannelDoList != null){
            for(ProjectChannelDo projectChannelDo : projectChannelDoList){
                Integer channelId = projectChannelDo.getId();
                List<ClueDo> clueDoList = clueMappper.getClueDoByProjectId(projectId, channelId, null);
                LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
                responseMap.put("渠道名称", projectChannelDo.getProjectChannel());
                responseMap.put("线索数量", clueDoList.size());
                results.add(responseMap);
            }
        }


        ExcelUtil.downLoadExcel(response, "ChannelDistribute.xlsx", "sheet1", results);
        return new TSuccessResult<>("成功");
    }

    @RequestMapping(value = "/getStatusDistribute", method = RequestMethod.GET)
    @ResponseBody
    public TResponse getStatusDistribute(Integer projectId, HttpServletResponse response){
        List<LinkedHashMap<String, Object>> results = new ArrayList<>();

        for(AuditEnum auditEnum : AuditEnum.values()){

            Integer status = auditEnum.getId();

            List<ClueDo> clueDoList = clueMappper.getClueDoByProjectId(projectId, null, status);

            LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
            responseMap.put("阶段名称", auditEnum.getName());
            responseMap.put("线索数量", clueDoList.size());
            results.add(responseMap);
        }


        ExcelUtil.downLoadExcel(response, "StatusDistribute.xlsx", "sheet1", results);
        return new TSuccessResult<>("成功");
    }
}
