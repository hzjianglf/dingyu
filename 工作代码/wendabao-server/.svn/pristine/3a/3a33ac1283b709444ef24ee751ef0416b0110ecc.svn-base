package com.handany.bm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.handany.base.common.PageUtil;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.bm.dao.BmClassroomMapper;
import com.handany.bm.model.BmClassroom;
import com.handany.bm.service.BmClassroomService;

@Service
public class BmClassroomServiceImpl implements BmClassroomService {

    @Autowired
    private BmClassroomMapper bmClassroomMapper;

    @Autowired
    private SerialNumberManager serialNumberManager;

    @Override
    public BmClassroom queryClassroomByUserId(String userId) {
        return bmClassroomMapper.queryClassroomByUserId(userId);
    }

    @Override
    public int saveClassroom(BmClassroom classroom) {
        if (classroom.getId().isEmpty()) {
            try {
                classroom.setId(serialNumberManager.nextSeqNo("bm_classroom"));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return bmClassroomMapper.saveClassroom(classroom);
    }

    @Override
    public int updateStatus(String userId, String status) {
        HashMap<String, String> map = new HashMap<String, String>();

        map.put("userId", userId);
        map.put("status", status);

        return bmClassroomMapper.updateStatus(map);
    }

    @Override
    public BmClassroom queryClassroomById(String id) {
        return bmClassroomMapper.queryClassroomById(id);
    }

    @Override
    public PageInfo<BmClassroom> queryClassrooms(Map<String, Object> queryMap) {
        PageUtil.startPage();
        List<BmClassroom> list = bmClassroomMapper.queryClassrooms(queryMap);
        PageInfo<BmClassroom> page = new PageInfo<BmClassroom>(list);
        
        return page;
    }
}
