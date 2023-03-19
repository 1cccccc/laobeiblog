package com.xi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ToEmail implements Serializable {
    private final static long serialVersionUID=1L;

    private String[] tos;

    private String subject;

    private String content;
}
