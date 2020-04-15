package com.bootdo.Check.ServiceImpl;

import com.bootdo.Check.Service.ITbChackService;
import com.bootdo.Check.dao.TbChackDao;
import com.bootdo.Check.domain.*;
import com.bootdo.system.domain.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class TbChackServiceImpl implements ITbChackService {

    @Autowired
    TbChackDao tbChackDao;

    @Override
    public String findTbChack(@Param("chackno") String chackno) {
        return tbChackDao.findTbChack(chackno);
    }
    @Override
    public List<TbChack> list(Map<String, Object> map) {
        return tbChackDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return tbChackDao.count(map);
    }

    @Override
    public List<TbUser> get(String chackno) {
        return tbChackDao.get(chackno);
    }

    @Override
    public TbTongdunResult getResult(String chackNo) {
        return tbChackDao.getResult(chackNo);
    }

    @Override
    public List<HashMap<String, Object>> CheExcelOnes(Map<String, Object> map) {
        return tbChackDao.CheExcelOnes(map);
    }

    @Override
    public TbChackRecord selFSF(String chackNo, String type) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("chackNo",chackNo);
        map.put("type",type);
        return tbChackDao.selFSF(map);
    }

    @Override
    public TbChackType selParam() {
        return tbChackDao.selParam();
    }

    @Override
    public UserDO getUser(Long userId) {
        return tbChackDao.getUser(userId);
    }

    @Override
    public int insert(TbUser student) {
        return tbChackDao.insert(student);
    }

    @Override
    public int updateLice(TbAccountInfo tbAccountInfo) {
        return tbChackDao.updateLice(tbAccountInfo);
    }

    @Override
    public int updateState(Map<String,Object> map) {
        return tbChackDao.updateState(map);
    }

    @Override
    public int insertHistory(TbChack history) {
        return tbChackDao.insertHistory(history);
    }

    @Override
    public List<TbUserMobile> queryMobileList(String accountNo) {
        return tbChackDao.queryMobileList(accountNo);
    }

    @Override
    public TbAccountInfo getCardid(String chackNo) {
        return tbChackDao.getCardid(chackNo);
    }

    @Override
    public TbAccountInfo getlive(String accountNo) {
        return tbChackDao.getlive(accountNo);
    }

    @Override
    public List<TbUserLinkmanInfo> getUserLink(String accountNo) {
        return tbChackDao.getUserLink(accountNo);
    }

    @Override
    public Map<String, String> queryChackMap(String chackNo) {
        return tbChackDao.queryChackMap(chackNo);
    }

    @Override
    public Map<String, String> queryAccountMap(String accountNo) {
        return tbChackDao.queryAccountMap(accountNo);
    }

    @Override
    public Map<String, String> queryChackTypeMap(String chackType) {
        return tbChackDao.queryChackTypeMap(chackType);
    }

    @Override
    public TbChackRecord selGroup(String chackno, String type) {
        return tbChackDao.selGroup(chackno,type);
    }

    @Override
    public void updateAccountNo(String accountNo,String states) {
        tbChackDao.updateAccountNo(accountNo,states);
    }

    @Override
    public Map<String, String> getMobile(String chackNo) {
        return tbChackDao.getMobile(chackNo);
    }

    @Override
    public void updateUserAddress(TbAccountInfo tbAccountInfo) {
        tbChackDao.updateUserAddress(tbAccountInfo);
    }
}
