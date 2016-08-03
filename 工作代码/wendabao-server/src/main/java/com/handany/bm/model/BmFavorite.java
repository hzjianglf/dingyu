package com.handany.bm.model;

import java.util.Date;

public class BmFavorite {
    private String studentId;

    private String teacherId;

    private Date lastModified;
    
    private BmClassroom classroom;


    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getLastModified() {
        return lastModified;
    }

	public BmClassroom getClassroom() {
		return classroom;
	}

	public void setClassroom(BmClassroom classroom) {
		this.classroom = classroom;
	}
}
