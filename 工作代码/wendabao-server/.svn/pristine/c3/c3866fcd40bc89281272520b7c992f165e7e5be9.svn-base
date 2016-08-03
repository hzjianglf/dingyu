package com.handany.bm.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.handany.base.cache.ICache;
import com.handany.base.cache.ICacheManager;
import com.handany.base.common.CommonUtils;
import com.handany.base.controller.BaseController;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.rbac.common.UserContextManager;
import com.handany.rbac.model.PmUser;
import com.handany.bm.model.BmClassroom;
import com.handany.bm.model.BmClassroomCourse;
import com.handany.bm.model.BmClassroomGrade;
import com.handany.bm.model.BmTeacher;
import com.handany.bm.service.BmClassroomCourseService;
import com.handany.bm.service.BmClassroomGradeService;
import com.handany.bm.service.BmClassroomService;
import com.handany.bm.service.BmTeacherService;

@Controller
@RequestMapping(value = "/bm/classroom")
public class BmClassroomController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(BmClassroomController.class);

    @Autowired
    private BmClassroomService classroomService;

    @Autowired
    private BmClassroomGradeService classroomGradeService;

    @Autowired
    private BmClassroomCourseService classroomCourseService;

    @Autowired
    private BmTeacherService teacherService;

    @Autowired
    private SerialNumberManager serialNumberManager;

    @Autowired
    private ICacheManager cacheManager;

    private static final String CACHE_NAME = "PANEL_HISTORY";

    /**
     * 根据登录用户，获取登录用户的教室信息
     */
    @RequestMapping(value = "/getClassroom")
    public void getClassInfo() {
        try {
            PmUser user = UserContextManager.getLoginUser();

            BmClassroom classroom = classroomService.queryClassroomByUserId(user.getId());

            if (classroom != null) {
                writeObject("classroom", classroom);
                setSuccess(true);
                setErrorCode("0000");
            } else {
                setSuccess(false);
                setErrorCode("0001");
                setMessage("教室信息不存在");
                logger.debug("教室信息不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            setSuccess(false);
            setErrorCode("0002");
            setMessage("获取失败");
        }
    }

    /**
     * 添加教室信息
     *
     */
    @RequestMapping(value = "/saveClassroom")
    public void saveClassroom(String chatroomId, String meetingId,
            String name,
            String id, String courses, String grades) {
        try {
            BmClassroom classroom = null;
            String newId = id;
            if (!id.isEmpty() && id.length() == 10) {
                classroom = classroomService.queryClassroomById(id);
            } else {
                PmUser user = UserContextManager.getLoginUser();

                classroom = classroomService.queryClassroomByUserId(user.getId());
                if (classroom == null) {
                    newId = serialNumberManager.nextSeqNo("bm_classroom");
                    classroom = new BmClassroom();
                    classroom.setId(newId);
                    classroom.setUserId(user.getId());
                    classroom.setCreateTime(CommonUtils.getCurrentDate());
                }
            }

            classroom.setName(name);

            // 设置会议室id及聊天室id
            classroom.setChatroomId(chatroomId);
            classroom.setMeetingId(meetingId);

            // 保存辅导课程
            ArrayList<BmClassroomCourse> courseList = new ArrayList<BmClassroomCourse>();
            String[] courseArray = courses.split(",");
            for (int i = 0; i < courseArray.length; i++) {
                BmClassroomCourse course = new BmClassroomCourse();
                course.setClassroomId(newId);
                course.setCourseId(courseArray[i]);
                courseList.add(course);
            }
            classroomCourseService.deleteClassroomCoursesByClassroomId(newId);
            classroomCourseService.saveClassroomCourses(courseList);

            // 保存辅导年级
            ArrayList<BmClassroomGrade> gradeList = new ArrayList<BmClassroomGrade>();
            String[] gradeArray = grades.split(",");
            for (int i = 0; i < gradeArray.length; i++) {
                BmClassroomGrade grade = new BmClassroomGrade();
                grade.setClassroomId(newId);
                grade.setGradeId(gradeArray[i]);
                gradeList.add(grade);
            }
            classroomGradeService.deleteClassroomGradeByClassroomId(newId);
            classroomGradeService.saveClassroomGrades(gradeList);

            // 保存教室信息
            classroom.setLastModified(CommonUtils.getCurrentDate());
            classroomService.saveClassroom(classroom);

            BmTeacher teacher = teacherService.queryByUserId(classroom.getUserId());
            teacher.setStatus("6"); // 将教师状态设置为正常使用中（6）
            teacherService.saveTeacherInfo(teacher);

            writeObject("classroomInfo", classroomService.queryClassroomById(classroom.getId()));
            setSuccess(true);
            setErrorCode("0000");
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            setSuccess(false);
            setErrorCode("0001");
            setMessage("参数不正确");
        } catch (Exception ex) {
            ex.printStackTrace();
            setSuccess(false);
            setErrorCode("0002");
            setMessage("未知错误");
        }
    }

    /**
     * 教师上线
     */
    @RequestMapping(value = "/online")
    public void online() {
        try {
            PmUser user = UserContextManager.getLoginUser();
            classroomService.updateStatus(user.getId(), "3");
            setSuccess(true);
            setErrorCode("0000");
        } catch (Exception e) {
            e.printStackTrace();
            setSuccess(false);
            setErrorCode("0002");
            setMessage("设置失败");
        }
    }

    /**
     * 教师下线
     */
    @RequestMapping(value = "/offline")
    public void offline() {
        try {
            PmUser user = UserContextManager.getLoginUser();
            classroomService.updateStatus(user.getId(), "2");
            setSuccess(true);
            setErrorCode("0000");
        } catch (Exception e) {
            e.printStackTrace();
            setSuccess(false);
            setErrorCode("0002");
            setMessage("设置失败");
        }
    }

    /**
     * 放置消息
     * @param classroomId
     * @param type
     * @param message
     * @param tokenId 
     */
    @RequestMapping("/putMessage")
    public void putMessage(
            @RequestParam(required = true) String classroomId,
            @RequestParam(required = true) String type,
            @RequestParam(required = true) String message,
            @RequestParam(required = true) String tokenId) {
        try {

            ICache cache = cacheManager.getCache(CACHE_NAME);
            String key = classroomId + "_" + type;

            @SuppressWarnings("unchecked")
            ArrayList<String> msgList = (ArrayList<String>) cache.get(key);
            if (msgList == null) {
                msgList = new ArrayList<String>();
            }

            msgList.add(message);
            cache.put(key, msgList);
            setSuccess(true);
            setErrorCode("0000");
        } catch (Exception e) {
            String msg = "消息保存失败";
            logger.error(msg, e);
            setSuccess(false);
            setErrorCode("0002");
            setMessage(msg);
        }
    }

    /**
     * 获取缓存消息
     * @param tokenId
     * @param classroomId 
     */
    @RequestMapping("/getMessages")
    public void getMessages(
            @RequestParam(required = true) String tokenId,
            @RequestParam(required = true) String classroomId) {
        try {
            ICache cache = cacheManager.getCache(CACHE_NAME);

            String keyLine = classroomId + "_" + "line";
            String keyImage = classroomId + "_" + "image";

            @SuppressWarnings("unchecked")
            ArrayList<String> lineList = (ArrayList<String>) cache.get(keyLine);
            if (lineList == null) {
                lineList = new ArrayList<String>();
            }

            @SuppressWarnings("unchecked")
            ArrayList<String> imageList = (ArrayList<String>) cache.get(keyImage);
            if (imageList == null) {
                imageList = new ArrayList<String>();
            }

            writeObject("lineMessages", lineList);
            writeObject("imageMessages", imageList);
            setSuccess(true);
            setErrorCode("0000");
        } catch (Exception e) {
            String msg = "获取消息失败";
            logger.error(msg, e);
            setSuccess(false);
            setErrorCode("0002");
            setMessage(msg);
        }
    }

    /**
     * 删除教室对应的缓存消息
     * @param tokenId
     * @param classroomId 
     */
    @RequestMapping("/clearMessages")
    public void clearMessages(
            @RequestParam(required = true) String tokenId,
            @RequestParam(required = true) String classroomId) {
        try {
            ICache cache = cacheManager.getCache(CACHE_NAME);

            String keyLine = classroomId + "_" + "line";
            String keyImage = classroomId + "_" + "image";

            cache.remove(keyLine);
            cache.remove(keyImage);
            setSuccess(true);
            setErrorCode("0000");
        } catch (Exception e) {
            String msg = "清除消息失败";
            logger.error(msg, e);
            setSuccess(false);
            setErrorCode("0002");
            setMessage(msg);
        }
    }
    
    /**
     * 删除最后一条消息
     * @param type
     * @param classroomId 
     */
    @RequestMapping("/popMessage")
    public void popMessage(
            @RequestParam(required = true) String type,
            @RequestParam(required = true) String classroomId) {
        try {
            ICache cache = cacheManager.getCache(CACHE_NAME);

            String key = classroomId + "_" + type;
            ArrayList<String> messages = (ArrayList<String>) cache.get(key);
            String msg = messages.remove(messages.size() - 1);
 
            setSuccess(true);
            setErrorCode("0000");
        } catch (Exception e) {
            String msg = "清除消息失败";
            logger.error(msg, e);
            setSuccess(false);
            setErrorCode("0002");
            setMessage(msg);
        }
    }
    
    /**
     * 查询教室列表
     * @param courseId
     * @param status 
     */
    @RequestMapping("/queryClassrooms")
    public void queryClassrooms(
            String courseId, 
            String status,String region1,String region2,String region3) {
        try {
            HashMap<String, Object> map = new HashMap<String, Object>();

            if (courseId != null && courseId.trim().length() > 0) {
                map.put("courseId", courseId);
            }
            
            if (status != null && status.trim().length() > 0) {
                map.put("status", status);
            }
            if(region1 != null && region1.length()>0){
            	map.put("region1", region1);
            }
            if(region2 != null && region2.length()>0){
            	map.put("region2", region2);
            }
            if(region3 != null && region3.length()>0){
            	map.put("region3", region3);
            }

            PageInfo<BmClassroom> classroomList = classroomService.queryClassrooms(map);
            setSuccess(true);
            writeData(classroomList);
        } catch (Exception e) {
        	String msg = "查询教室列表失败";
            setSuccess(false);
            setErrorCode("0002");
            logger.error(msg, e);
            setMessage(msg);
        }
    }
    /**
	 * 得到教室列表哦
	 */
	@RequestMapping("/getClassroomList")
	public String getClassroomList(){
		return transView("/manage/teacher/classroomMgmt");
	}
}
