package com.vane.service.impl;

import com.vane.dao.AreaDao;
import com.vane.entity.Area;
import com.vane.service.AreaService;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wenshaobo on 2018/6/25.
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;
    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }

    @Override
    public Area getAreaById(int areaId) {
//        int a = 1/0;
        return areaDao.queryAreaById(areaId);
    }

    //使用默认的异常处理，因此不需要增加rollback
//    @Transactional(rollbackFor = Exception.class)
    @Transactional
    @Override
    public boolean addArea(Area area) {
        if(area.getAreaName() !=null && !"".equals(area.getAreaName())){
            area.setCreateTime(new Date());
            area.setLastEditTime(new Date());
            try {
                int effectedNum = areaDao.insertArea(area);
                if(effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("查入区域信息失败！");
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        } else {
            throw new RuntimeException("区域信息不能为空！");
        }
    }

    @Override
    public boolean modifyArea(Area area) {
        if(area.getAreaId() != null && area.getAreaId() > 0){
            area.setLastEditTime(new Date());
            try {
                int effectedNum = areaDao.updateArea(area);
                if(effectedNum > 0){
                    return true;
                } else {
                    throw new RuntimeException("更新区域失败！");
                }
            } catch (Exception e) {
                throw new RuntimeException("更新区域信息失败！" + e.toString());
            }
        } else {
            throw new RuntimeException("区域信息不能为空！");
        }
    }

    @Override
    public boolean deleteArea(int areaId) {
        if(areaId > 0) {
            try {
                int effectedNum = areaDao.deleteArea(areaId);
                if(effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("删除区域信息失败");
                }
            }catch (Exception e) {
                throw new RuntimeException("删除区域信息失败" + e.toString());
            }
        } else {
            throw new RuntimeException("区域Id不能为空！");
        }
    }
}
