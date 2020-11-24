package com.sh2004.controller;

import com.sh2004.base.bean.ResultVo;
import com.sh2004.base.exception.CrmException;
import com.sh2004.bean.ActivityRemark;
import com.sh2004.bean.ClueRemark;
import com.sh2004.bean.User;
import com.sh2004.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @ProjectName: dylcrm
 * @Package: com.sh2004.controller
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/11/21 21:04
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class ClueRemarkController {
    @Autowired
    private ClueRemarkService clueRemarkService;

    @RequestMapping("/workbench/clue/updateClueRemark")
    @ResponseBody
    public ResultVo updateClueRemark(ClueRemark clueRemark){
        ResultVo resultVo = new ResultVo();
        try{
            clueRemarkService.updateClueRemark(clueRemark);
            resultVo.setOk(true);
            resultVo.setMessage("修改备注成功");
        }catch (CrmException e){
            resultVo.setOk(false);
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }

    @RequestMapping("/workbench/clue/addClueRemark")
    @ResponseBody
    public ResultVo addClueRemark(ClueRemark clueRemark, HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        clueRemark.setCreateBy(user.getName());
        ResultVo resultVo = new ResultVo();
        try{
            String newId = clueRemarkService.insertClueRemark(clueRemark);

            resultVo.setOk(true);
            resultVo.setMessage("添加线索备注成功"+","+newId);
        }catch (CrmException e){
            resultVo.setOk(false);
            resultVo.setMessage(e.getMessage());
        }
        return resultVo;
    }
}
