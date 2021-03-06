package com.vane.dao;

import com.vane.entity.Area;
import com.vane.service.AreaService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wenshaobo on 2018/6/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {
    @Autowired
    private AreaDao areaDao;
    @Autowired
    private AreaService areaService;

    @Test
//    @Ignore
    public void queryArea() throws Exception {
        List<Area> areaList = areaDao.queryArea();
        assertEquals(2,areaList.size());
    }

    @Test
//    @Ignore
    public void queryAreaById() throws Exception {
        Area area = areaService.getAreaById(1);
        assertEquals("东苑",area.getAreaName());
    }

    @Test
    @Ignore
    public void insertArea() throws Exception {
        Area area = new Area();
        area.setAreaName("南苑");
        area.setPriority(1);
        int areaId = areaDao.insertArea(area);
        assertEquals(1,areaId);
    }

    @Test
    @Ignore
    public void updateArea() throws Exception {
        Area area = new Area();
        area.setAreaName("西苑");
        area.setAreaId(3);
        area.setLastEditTime(new Date());
        int effectedNum = areaDao.updateArea(area);
        assertEquals(1,effectedNum);
    }

    @Test
    @Ignore
    public void deleteArea() throws Exception {
        int effectedNum = areaDao.deleteArea(3);
        assertEquals(1,effectedNum);
    }

}