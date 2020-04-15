package com.bootdo.Check.ServiceImpl;

import com.bootdo.Check.Service.ITbUserLinkmanInfoServer;
import com.bootdo.Check.dao.TbUserLinkmanInfoDao;
import com.bootdo.Check.domain.TbUserLinkmanInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class TbUserLinkmanInfoServerImpl implements ITbUserLinkmanInfoServer {

    @Autowired
    private TbUserLinkmanInfoDao tbUserLinkmanInfoDao;

    @Override
    public List<TbUserLinkmanInfo> get(TbUserLinkmanInfo tbUserLinkmanInfo) {
        return tbUserLinkmanInfoDao.get(tbUserLinkmanInfo);
    }

    @Override
    public TbUserLinkmanInfo find(String id) {
        return tbUserLinkmanInfoDao.find(id);
    }

    @Override
    public int insert(TbUserLinkmanInfo tbUserLinkmanInfo) throws Exception {
        return tbUserLinkmanInfoDao.insert(tbUserLinkmanInfo);
    }

    @Override
    public int update(TbUserLinkmanInfo tbUserLinkmanInfo) throws Exception {
        return tbUserLinkmanInfoDao.update(tbUserLinkmanInfo);
    }

    @Override
    public int delete(String id) throws Exception{
        return tbUserLinkmanInfoDao.delete(id);
    }

    @Override
    public void updateUserLinkmanInfo(TbUserLinkmanInfo tbUserLinkmanInfo) {
        tbUserLinkmanInfoDao.updateUserLinkmanInfo(tbUserLinkmanInfo);
    }
}
