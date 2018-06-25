package com.vane.service;

import com.vane.entity.Area;

import java.util.List;

/**
 * Created by wenshaobo on 2018/6/25.
 */
public interface AreaService {
    List<Area> getAreaList();
    Area getAreaById(int areaId);
    boolean addArea(Area area);
    boolean modifyArea(Area area);
    boolean deleteArea(int areaId);
}
