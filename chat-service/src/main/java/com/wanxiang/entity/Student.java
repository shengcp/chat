package com.wanxiang.entity;

import java.io.Serializable;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author single
 * @since 2018-03-26
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String type;
    private Integer score;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
        ", id=" + id +
        ", name=" + name +
        ", type=" + type +
        ", score=" + score +
        "}";
    }
}
