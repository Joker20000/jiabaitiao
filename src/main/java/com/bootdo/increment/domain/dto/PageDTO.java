package com.bootdo.increment.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : oysy
 * @Version: 0.0.1
 * @Date : 2020-04-14 17:16
 **/
@Data
public class PageDTO implements Serializable {
    private Integer offset;
    private Integer limit;


}
