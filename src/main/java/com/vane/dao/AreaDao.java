package com.vane.dao;

import com.vane.entity.Area;

import java.util.List;

/**
 * Created by wenshaobo on 2018/6/25.
 */
public interface AreaDao {
    List<Area> queryArea();
    Area queryAreaById(int areaId);
    int insertArea(Area area);
    int updateArea(Area area);
    int deleteArea(int areaId);
}
