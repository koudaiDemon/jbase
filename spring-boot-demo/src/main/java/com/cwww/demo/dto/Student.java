package com.cwww.demo.dto;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2019/7/30  11:11
 */
public class Student extends User {

    private String studentId = "defaultStudentId";

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
