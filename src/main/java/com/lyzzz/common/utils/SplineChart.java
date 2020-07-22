package com.lyzzz.common.utils;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @location： BlogSystem\com.lyzzz.common.utils
 * @creatTime: 2020/7/20  15:04
 * @author: Administrator
 * @remark:
 *
 * 图表数据封装
 *
 */
@Data
/**
 *
 * setter 方法返回当前对象
 *
 * */
@Accessors(chain = true)
public class SplineChart implements Serializable {

    private String time;

    private Long num;

}
