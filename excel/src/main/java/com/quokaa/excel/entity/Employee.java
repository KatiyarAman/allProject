package com.quokaa.excel.entity;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "employee")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String employeeName;
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt = new Timestamp((new Date()).getTime());

    @Column(name = "modified_at", nullable = false)
    @UpdateTimestamp
    private Timestamp modifiedAt;
    public Employee(){}
    public Employee(Long id,String employeeName){
        this.id=id;
        this.employeeName=employeeName;
    }
}
