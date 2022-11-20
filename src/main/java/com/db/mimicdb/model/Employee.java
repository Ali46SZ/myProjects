package com.db.mimicdb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

    private long id;
    private String name;
    private String phone;
    private String email;

}
