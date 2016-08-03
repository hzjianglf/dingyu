package com.handany.bm.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.handany.bm.model.BmClassroom;

/**
 *
 * @author Administrator
 *
 */
public interface BmClassroomService {

    BmClassroom queryClassroomByUserId(String userId);

    BmClassroom queryClassroomById(String id);

    int saveClassroom(BmClassroom classroom);

    int updateStatus(String userId, String status);

    /**
     * 分页查询教室列表
     * @param queryMap
     * @return 
     */
    PageInfo<BmClassroom> queryClassrooms(Map<String, Object> queryMap);
}
