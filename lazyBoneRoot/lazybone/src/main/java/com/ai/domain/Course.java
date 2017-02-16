package com.ai.domain;


import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by eason on 2017/2/15.
 */
@Entity
@Table(name="lazy_bone_course")
public class Course {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(unique = true,nullable = false)
    private String id;
    @Column(name = "description")
    private String courseDesc;
    @Column(name = "\"name\"")
    private String name;
    private String url;
    @Column(name = "create_date")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "update_date")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "course_days")
    private int courseDays;
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CourseItem> courseItems=new ArrayList<>();
    public void addCourseItem(CourseItem item){
        courseItems.add(item);
        item.setCourse(this);
    }
    public void removeCourseItem(CourseItem item){
        courseItems.remove(item);
        item.setCourse(null);
    }
    public Course(String courseDesc, String name, String url, Date createDate, Date updateDate, int courseDays, List<CourseItem> courseItems) {
        this.courseDesc = courseDesc;
        this.name = name;
        this.url = url;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.courseDays = courseDays;
        this.courseItems = courseItems;
    }

    public List<CourseItem> getCourseItems() {

        return courseItems;
    }

    public void setCourseItems(List<CourseItem> courseItems) {
        this.courseItems = courseItems;
    }

    public int getCourseDays() {
        return courseDays;
    }

    public void setCourseDays(int courseDays) {
        this.courseDays = courseDays;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Course() {
    }
    public Course(String id){
        this.id=id;
    }

}
