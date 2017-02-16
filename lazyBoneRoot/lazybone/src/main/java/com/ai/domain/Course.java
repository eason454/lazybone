package com.ai.domain;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


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

    public Course() {
    }

    public Course(String courseDesc, String name, String url, Date createDate, Date updateDate) {
        this.courseDesc = courseDesc;
        this.name = name;
        this.url = url;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
