package com.java.domain;






import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;


@Data
public class TbUser implements Serializable {

    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String phone;
    @NotBlank
    private String email;
    private Date created;
    private Date updated;


}
