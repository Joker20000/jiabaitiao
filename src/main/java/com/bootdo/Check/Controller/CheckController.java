package com.bootdo.Check.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.AfterLoanAccount.Service.AfterLoanAccountService;
import com.bootdo.Check.Service.CheckService;
import com.bootdo.Check.Service.ITbChackRecordServer;
import com.bootdo.Check.Service.ITbChackService;
import com.bootdo.Check.Service.ITbUserLinkmanInfoServer;
import com.bootdo.Check.domain.*;
import com.bootdo.Check.domain.dto.TbAccountInfoDto;
import com.bootdo.Check.domain.dto.TbChackDto;
import com.bootdo.Loan.service.AccountService;
import com.bootdo.Utils.BigDecimalUtil;
import com.bootdo.Utils.StringUtil;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.system.domain.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("ch/ec")
public class CheckController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(CheckController.class);
    @Autowired
    private ITbChackService tbChackService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ITbUserLinkmanInfoServer iTbUserLinkmanInfoServer;
    @Autowired
    private ITbChackRecordServer iTbChackRecordServer;
    @Autowired
    private CheckService checkService;
    @Resource
    private AfterLoanAccountService afterLoanAccountService;
    // 总览
    @GetMapping("/ec")
    String bc(Model model) {
        model.addAttribute("statu","y");
        return "Check/Check";
    }

    // 初审
    @GetMapping("/fir")
    String bc1() {
        return "Check/First";
    }

    // 复审
    @GetMapping("/sec")
    String bc2() {
        return "Check/reexamine";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:���������ֵ��false:����Ϊ��ֵ
    }

    /**
     * 查询全部
     *
     * @param params
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    PageUtils list(@RequestParam Map<String, Object> params) {
        if (!"".equals(params.get("chackNo")) && params.get("chackNo") != null) {
            params.put("chackNo", StringUtil.formatLike(String.valueOf(params.get("chackNo"))));
        }
        if (!"".equals(params.get("realname")) && params.get("realname") != null) {
            params.put("realname", StringUtil.formatLike(String.valueOf(params.get("realname"))));
        }
        if (!"".equals(params.get("mobile")) && params.get("mobile") != null) {
            params.put("mobile", String.valueOf(params.get("mobile")));
        }
        if (!"".equals(params.get("chackType")) && params.get("chackType") != null) {
            params.put("chackType", String.valueOf(params.get("chackType")));
        }
        if (!"".equals(params.get("chackState")) && params.get("chackState") != null) {
            String chackState = params.get("chackState").toString();
            if (chackState.equals("101") || chackState.equals("102")) {
                params.remove("chackState");
                if (chackState.equals("101")) {
                    params.put("chackState1", "chackState1");
                }
                if (chackState.equals("102")) {
                    params.put("chackState2", "chackState2");
                }
            } else {
                params.put("chackState", String.valueOf(params.get("chackState")));
            }
        }
        if (!"".equals(params.get("applyTimeStart")) && params.get("applyTimeStart") != null) {
            params.put("applyTimeStart", String.valueOf(params.get("applyTimeStart")));
        }
        if (!"".equals(params.get("applyTimeEnd")) && params.get("applyTimeEnd") != null) {
            params.put("applyTimeEnd", String.valueOf(params.get("applyTimeEnd")));
        }
        if (!"".equals(params.get("lastTimeStart")) && params.get("lastTimeStart") != null) {
            params.put("lastTimeStart", String.valueOf(params.get("lastTimeStart")));
        }
        if (!"".equals(params.get("lastTimeEnd")) && params.get("lastTimeEnd") != null) {
            params.put("lastTimeEnd", String.valueOf(params.get("lastTimeEnd")));
        }
        if (!"".equals(params.get("chackResult")) && params.get("chackResult") != null) {
            params.put("chackResult", String.valueOf(params.get("chackResult")));
        }
        if (!"".equals(params.get("valid")) && params.get("valid") != null) {
            params.put("valid", String.valueOf(params.get("valid")));
        }

        // 查询列表数据
        Query query = new Query(params);
        List<TbChack> sysUserList = tbChackService.list(query);
        if (sysUserList.size() > 0){
            for (TbChack sysUser:sysUserList){
                String applyTime = sysUser.getApplyTime();
                String lastTime = sysUser.getLastTime();
                if ("".equals(lastTime) || lastTime == null){
                    sysUser.setValid("");
                }else {
                    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try{
                        Date applyDate = sdf.parse(applyTime);
                        Date lastDate = sdf.parse(lastTime);
                        String valid = getDatePoor(lastDate,applyDate);
                        sysUser.setValid(valid);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        int total = tbChackService.count(query);
        PageUtils pageUtil = new PageUtils(sysUserList, total);
        return pageUtil;
    }
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long n0 = 1000;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        String timeStr = "";
        if (diff<=nm){
            timeStr = "0.02";
        }else if (nm<diff){
            // 计算差多少小时
            timeStr= BigDecimalUtil.BigDecimalDivide(diff+"",1000 * 60 * 60 + "",2);
            // 计算差多少分钟
            /*timeStr= BigDecimalUtil.BigDecimalDivide(diff+"",1000 * 60 + "",2);*/
        }
        return timeStr;
    }
    /**
     * 弹出的审核页面
     *
     * @param model
     * @param chackNo
     * @param pass
     * @return
     */
    @GetMapping("/personal/{chackNo}/{accountNo}/{pass}/{userId}")
    String personal(Model model, HttpServletRequest request, @PathVariable("chackNo") String chackNo, @PathVariable("accountNo") String accountNo, @PathVariable("pass") int pass,  @PathVariable("userId") String userId) {
        TbChackRecord en = new TbChackRecord();
        // 用户来源
        List<TbUser> tbUser = tbChackService.get(chackNo);
        if (!(tbUser.size() > 0)) {
            TbUser tbUsers = new TbUser();
            tbUser.add(tbUsers);
        }
        // 身份证
        TbAccountInfo acc = tbChackService.getCardid(chackNo);
        if (acc == null) {
            acc = new TbAccountInfo();
        }
        // 同盾贷前结果
        TbTongdunResult user = tbChackService.getResult(chackNo);
        if (user == null) {
            user = new TbTongdunResult();
        }
        // 查询当前用户
        UserDO userDO = tbChackService.getUser(getUserId());
        if (userDO == null) {
            userDO = new UserDO();
        }
        TbChackRecord se1 = tbChackService.selFSF(chackNo, "01");
        if (se1 == null) {
            se1 = new TbChackRecord();
        }

        // 查询联系人1/2信息
        List<TbUserLinkmanInfo> userLinks = tbChackService.getUserLink(accountNo);
        if (!(userLinks.size() > 0)) {
            userLinks = new ArrayList<>();
        }

        if (se1 == null) {
            model.addAttribute("se1", en);
        } else {
            model.addAttribute("se1", se1);
        }
        TbChackRecord se2 = tbChackService.selFSF(chackNo, "02");
        if (se2 == null) {
            model.addAttribute("se2", en);
        } else {
            model.addAttribute("se2", se2);
        }

        // 用户来源
        model.addAttribute("userList", tbUser);
        model.addAttribute("companyId", tbUser.get(0).getCompanyId());
        // 身份证
        model.addAttribute("acc", acc);
        // 同盾贷前结果
        model.addAttribute("use", user);
        // 查询当前用户
        model.addAttribute("users", userDO);

        // 查询联系人1/2信息
        model.addAttribute("userLink", userLinks);
        model.addAttribute("pass", pass);
        model.addAttribute("overview", request.getParameter("overview"));//区分初审、复审、总览

        //查询贷后列表
        List<Map<String,String>> loanAfterInfList = afterLoanAccountService.getLoanAfterInfo(accountNo);
        model.addAttribute("loanAfterInfList", loanAfterInfList);

        return "Check/review";
    }

    @ResponseBody
    @RequestMapping(value = "/rechJfOrderDetail")
    public String rechJfOrderDetail(@ModelAttribute("jfid") String jfid, Model model){
        Map<String, Object> monthMap = new HashMap<>();
        try{
            if(StringUtils.isBlank(jfid)){
                monthMap.put("monthNum1", "NULL");//当前月
                monthMap.put("monthNum2", "NULL");//前一个月
                monthMap.put("monthNum3", "NULL");//前2个月
                monthMap.put("monthNum4", "NULL");//前3个月
                monthMap.put("monthNum5", "NULL");//前4个月
                monthMap.put("summary", "JF_USER_ID为空");//合计
            }else{
                monthMap  = checkService.searchJFUserDataByJFid(jfid);
            }
            return JSON.toJSONString(monthMap);
        }catch (Exception ex){
            logger.error("CheckController rechJfOrderDetail 发生错误：[{}]", ex);
            monthMap.put("monthNum1", "NULL");//当前月
            monthMap.put("monthNum2", "NULL");//前一个月
            monthMap.put("monthNum3", "NULL");//前2个月
            monthMap.put("monthNum4", "NULL");//前3个月
            monthMap.put("monthNum5", "NULL");//前4个月
            monthMap.put("summary", "发生错误");//合计
            return JSON.toJSONString(monthMap);
        }
    }
    @ResponseBody
    @RequestMapping(value = "/getContacts", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Map getContacts(@ModelAttribute("accountNo") String accountNo,@ModelAttribute("chackNo") String chackNo) {
        List<TbUserLinkmanInfo> userLink = tbChackService.getUserLink(accountNo);
        if (userLink == null) {
            userLink = new ArrayList<>();
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("userLink", userLink);
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/queryMobile", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Map queryMobile(@ModelAttribute("accountNo") String accountNo){
        List<TbUserMobile> mobile = tbChackService.queryMobileList(accountNo);
        if (mobile == null ){
            mobile = new ArrayList<>();
        }
        boolean state = true;
        for (TbUserMobile mobiles : mobile){
            if ("02".equals(mobiles.getDataType())){
                state = false;
            }
        }
        //添加联系人信息
        if (mobile.size() > 0){
            if(state){
                TbUserMobile mob = new TbUserMobile();
                mob.setDataType("02");
                mob.setAccountNo(mobile.get(0).getAccountNo());
                mob.setFilePath(mobile.get(0).getFilePath());
                mob.setCardId(mobile.get(0).getCardId());
                mob.setRequesetUrl(mobile.get(0).getRequesetUrl());
                mobile.add(mob);
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mobile", mobile);
        return map;

    }

    /**
     * 联系信息
     *
     * @param tbAccountInfoDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveUserAccountInfo", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Map saveUserAccountInfo(@ModelAttribute("tbAccountInfoDto") TbAccountInfoDto tbAccountInfoDto) {
        Map hashMap = new HashMap();

        String chackType = tbChackService.findTbChack(tbAccountInfoDto.getChackno());
        /*if (StringUtils.isBlank(chackType) || !chackType.equals("02")){
            hashMap.put("code", "500");
            hashMap.put("msg", "非联系信息认证！");
            return hashMap;
        }*/

        if (tbAccountInfoDto != null && tbAccountInfoDto.getTbUserLinkmanInfos() != null  ){
            Iterator<TbUserLinkmanInfo> iterator = tbAccountInfoDto.getTbUserLinkmanInfos().iterator();
            while (iterator.hasNext()) {
                TbUserLinkmanInfo tbUserLinkmanInfo = iterator.next();
                String mobile= tbUserLinkmanInfo.getMobile();
                String realname= tbUserLinkmanInfo.getRealname();
                if (StringUtils.isBlank(realname) || StringUtils.isBlank(mobile)) {
                    iterator.remove();
                }
            }
        }else{
            hashMap.put("code", "500");
            hashMap.put("msg", "请正确填写信息！");
            return hashMap;
        }

        if (tbAccountInfoDto.getTbUserLinkmanInfos().size() <= 0) {
            hashMap.put("code", "500");
            hashMap.put("msg", "请正确填写信息！");
            return hashMap;
        }

        // 查询地址信息 accountNo
        TbAccountInfo live = tbChackService.getlive(tbAccountInfoDto.getAccountNo());
        int i = 1;
        for (TbUserLinkmanInfo tbUserLinkmanInfo : tbAccountInfoDto.getTbUserLinkmanInfos()) {
            tbUserLinkmanInfo.setAddress(live.getLiveAddress());
            tbUserLinkmanInfo.setChackNo(tbAccountInfoDto.getChackno());
            tbUserLinkmanInfo.setDataState("1");
            try {
                iTbUserLinkmanInfoServer.insert(tbUserLinkmanInfo);
            } catch (Exception e) {
                e.printStackTrace();
                hashMap.put("code", "500");
                hashMap.put("msg", "第" + i + "个联系人添加失败！");
                return hashMap;
            }
            i++;
        }

        hashMap.put("code", "200");
        hashMap.put("msg", "添加成功！");
        return hashMap;
    }

    /**
     * 修改联系信息
     *
     * @param tbUserLinkmanInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateUserAccountInfo", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Map updateUserAccountInfo(@ModelAttribute("tbUserLinkmanInfo") TbUserLinkmanInfo tbUserLinkmanInfo) {

        Map hashMap = new HashMap();
        String chackType = tbChackService.findTbChack(tbUserLinkmanInfo.getChackNo());
        /*if (StringUtils.isBlank(chackType) || !chackType.equals("02")){
            hashMap.put("code", "500");
            hashMap.put("msg", "非联系信息认证！");
            return hashMap;
        }*/
        if (tbUserLinkmanInfo == null) {
            hashMap.put("code", "500");
            hashMap.put("msg", "请正确填写信息！");
            return hashMap;
        }
        if(tbUserLinkmanInfo.getId()==null || tbUserLinkmanInfo.getId()<=0 ){
            hashMap.put("code", "500");
            hashMap.put("msg", "修改失败！");
            return hashMap;
        }
        try {
            iTbUserLinkmanInfoServer.updateUserLinkmanInfo(tbUserLinkmanInfo);
        } catch (Exception e) {
            e.printStackTrace();
            hashMap.put("code", "500");
            hashMap.put("msg", "修改失败！");
            return hashMap;
        }

        hashMap.put("code", "200");
        hashMap.put("msg", "修改成功！");
        return hashMap;
    }
    @ResponseBody
    @RequestMapping(value = "/findAddress", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Map findAddress(@ModelAttribute("accountNo") String accountNo){
        TbAccountInfo live = tbChackService.getlive(accountNo);
        if (live == null) {
            live = new TbAccountInfo();
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("live",live);
        return map;
    }


    /**
     * 修改联系地址
     *
     * @param tbAccountInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateLice", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Map updateLice(@ModelAttribute("tbAccountInfo") TbAccountInfo tbAccountInfo) {
        Map hashMap = new HashMap();
        if (tbAccountInfo == null) {
            hashMap.put("code", "500");
            hashMap.put("msg", "请正确填写信息！");
            return hashMap;
        }

        try {
            tbChackService.updateUserAddress(tbAccountInfo);
        } catch (Exception e) {
            e.printStackTrace();
            hashMap.put("code", "500");
            hashMap.put("msg", "修改失败！");
            return hashMap;
        }

        hashMap.put("code", "200");
        hashMap.put("msg", "修改成功！");
        return hashMap;
    }



    /*
     * 增加到信审记录
     * @param student
     * @return
     @RequestMapping(value = "/stusave", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
     @ResponseBody public String addUserSave(@ModelAttribute("student") TbUser student) {
     // 添加信审记录
     int row = tbChackService.insert(student);
     return JSON.toJSONString(row);
     }*/
    /**
     * 修改状态+添加
     *
     * @param tbChackDto
     * @return
     */
    @RequestMapping(value = "/updateStu", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map updateStu(@ModelAttribute("tbChackDto") TbChackDto tbChackDto) {

        Map<String, Object> map = new HashMap<>();
        if (tbChackDto.getOpinion() ==null || tbChackDto.getOpinion().equals("")){
            map.put("code","500");
            map.put("msg","请填写具体意见！");
            return map;
        }
        if (tbChackDto.getResult().equals("undefined") || tbChackDto.getOpinion().equals("")){
            map.put("code","500");
            map.put("msg","请选择审核建议！");
            return map;
        }

        try {
            iTbChackRecordServer.saveTbChackRecord(tbChackDto);
            map.put("code","200");
            map.put("msg","审核成功！");
            return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put("code","500");
            map.put("msg","审核失败！");
            return map;
        }

    }
}