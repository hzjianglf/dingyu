package com.handany.bm.model;

import java.util.Date;
import java.util.List;

public class BmClassroom {

    private String id;

    private String name;

    private String intro;

    private String courses;

    private String grades;

    private String userId;

    private String chatroomId;

    private String meetingId;
    
    private Date createTime;

    private String status;

    private Date lastModified;

    private List<BmClassroomCourse> classroomCourses;

    private List<BmClassroomGrade> classroomGrades;

    private String userPic;
    
    private String school;
    
    private String teacherName;
    
    public String getTeacherName() {
		return teacherName;
	}
    
    public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
    
    public String getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(String chatroomId) {
        this.chatroomId = chatroomId;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIntro() {
        return intro;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getCourses() {
        return courses;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public String getGrades() {
        return grades;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public List<BmClassroomGrade> getClassroomGrades() {
        return classroomGrades;
    }

    public void setClassroomGrades(List<BmClassroomGrade> classroomGrades) {
        this.classroomGrades = classroomGrades;
    }

    public List<BmClassroomCourse> getClassroomCourses() {
        return classroomCourses;
    }

    public void setClassroomCourses(List<BmClassroomCourse> classroomCourses) {
        this.classroomCourses = classroomCourses;
    }
}
