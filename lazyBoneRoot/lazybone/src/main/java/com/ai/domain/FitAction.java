package com.ai.domain;

import com.ai.util.consts.CommonConst;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jerry on 2017/2/16.
 */
@Entity
@Table(name="fit_action")
public class FitAction {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(unique = true,nullable = false)
    private String id;

    @Column(name="action_name")
    private String actionName;

    @Column(name="action_type")
    private String actionType;

    @Column(name="count_unit")
    private String countUnit;

    @Column(name="create_date")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name="state")
    @Enumerated(EnumType.ORDINAL)
    private CommonConst.State state;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public CommonConst.State getState() {
        return state;
    }

    public void setState(CommonConst.State state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getCountUnit() {
        return countUnit;
    }

    public void setCountUnit(String countUnit) {
        this.countUnit = countUnit;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    private String actionDescription;


    public FitAction() {
    }

    public FitAction(String actionName, String actionType, String countUnit, Date createDate, CommonConst.State state, String actionDescription) {
        this.actionName = actionName;
        this.actionType = actionType;
        this.countUnit = countUnit;
        this.createDate = createDate;
        this.state = state;
        this.actionDescription = actionDescription;
    }
}
