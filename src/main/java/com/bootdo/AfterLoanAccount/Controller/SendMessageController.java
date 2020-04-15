package com.bootdo.AfterLoanAccount.Controller;

import com.bootdo.AfterLoanAccount.Service.AfterLoanAccountService;
import com.bootdo.AfterLoanAccount.domain.SmsLoanOrder;
import com.bootdo.Check.Service.ITbChackService;
import com.bootdo.Loan.service.JXInterService;
import com.bootdo.ULoan.Service.LoanCService;
import com.bootdo.Utils.StringUtil;
import com.bootdo.Utils.UserChannelType;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.system.domain.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sendMessageController")
public class SendMessageController extends BaseController {
    private Logger LOG = LoggerFactory.getLogger(SendMessageController.class);
    @Resource
    private AfterLoanAccountService afterLoanAccountService;
    @Autowired
    private JXInterService jxInterService;
    @Autowired
    private ITbChackService tbChackService;
    @GetMapping("/afterLoanAccount")
    String account() {
        return "AfterLoanAccount/SendMessageRecord";
    }

    @ResponseBody
    @RequestMapping("/sendMessage")
    public String sendMessage(HttpServletRequest request) {
        String message = request.getParameter("message");
        String mobile = request.getParameter("mobile");
        String mobile1 = request.getParameter("mobile1");
        String loanName = request.getParameter("loanName");
        String userChannelId = request.getParameter("userChannelId");
        LOG.info("借款人姓名："+loanName+"借款人手机号："+mobile1+"接受短信手机号："+mobile+"短信内容："+message+"借款人userChannelId："+userChannelId);
        try {
            String signName = UserChannelType.getName(userChannelId);
            jxInterService.sendMessages(mobile, message, signName);
        }catch (Exception e){
            e.printStackTrace();
            return "1009";
        }
        Date d=new Date();
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime= s.format(d);
        String uname= getUser().getName();  //当前操作人人员姓名
        // 查询当前用户
        UserDO userDO = tbChackService.getUser(getUserId());
        SmsLoanOrder smsLoanOrder = new SmsLoanOrder();
        smsLoanOrder.setCreateTime(createTime);
        smsLoanOrder.setOperator(uname);
        smsLoanOrder.setPhone(mobile);
        smsLoanOrder.setOperatorId(userDO.getUserId()+"");
        smsLoanOrder.setSendContent(message);
        smsLoanOrder.setLoanName(loanName);
        smsLoanOrder.setLoanPhone(mobile1);
        afterLoanAccountService.SmsLoanOrder(smsLoanOrder);
        return "0000";
    }

    //查询
    @ResponseBody
    @RequestMapping("/aftersendMessagelist")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        if(!"".equals(params.get("mobile")) && params.get("mobile") != null){
            params.put("mobile", StringUtil.formatLike(String.valueOf(params.get("mobile"))));
        }
        if(!"".equals(params.get("loanName")) && params.get("loanName") != null){
            params.put("loanName", StringUtil.formatLike(String.valueOf(params.get("loanName"))));
        }
        Query query = new Query(params);
        List<SmsLoanOrder> saleChanceList= afterLoanAccountService.findAfterSmsLoanOrder(query);
        Long total1= afterLoanAccountService.getTotaAfterSmsLoanOrder(query);
        int total= new Long(total1).intValue();
        PageUtils pageUtils = new PageUtils(saleChanceList, total);
        return pageUtils;
    }
}
