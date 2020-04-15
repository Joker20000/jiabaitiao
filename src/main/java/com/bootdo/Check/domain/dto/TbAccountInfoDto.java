package com.bootdo.Check.domain.dto;

import com.bootdo.Check.domain.TbAccountInfo;
import com.bootdo.Check.domain.TbUserLinkmanInfo;
import com.bootdo.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TbAccountInfoDto {
    private TbAccountInfo tbAccountInfo;
    private String chackno;
    private String accountNo;
    private List<TbUserLinkmanInfo> tbUserLinkmanInfos;

    public String getChackno() {
        return chackno;
    }

    public void setChackno(String chackno) {
        this.chackno = chackno;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public List<TbUserLinkmanInfo> getTbUserLinkmanInfos() {
        return tbUserLinkmanInfos;
    }

    public void setTbUserLinkmanInfos(List<TbUserLinkmanInfo> tbUserLinkmanInfos) {
        if (tbUserLinkmanInfos != null ){
            Iterator<TbUserLinkmanInfo> iterator = tbUserLinkmanInfos.iterator();
            while (iterator.hasNext()) {
                TbUserLinkmanInfo tbUserLinkmanInfo = iterator.next();
                String mobile= tbUserLinkmanInfo.getMobile();
                String realname= tbUserLinkmanInfo.getRealname();
                if (StringUtils.isBlank(realname) || StringUtils.isBlank(mobile)) {
                    iterator.remove();
                }
            }
        }else{
            tbUserLinkmanInfos = new ArrayList<TbUserLinkmanInfo>();
        }
        this.tbUserLinkmanInfos = tbUserLinkmanInfos;
    }
}
