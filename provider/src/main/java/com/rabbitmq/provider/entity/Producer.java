package com.rabbitmq.provider.entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @auther River
 * @date 2021/3/29 0:43
 */
@Data
@ToString
public class Producer {

    private Integer id;

    private Integer name;

    private BigDecimal price;
}
