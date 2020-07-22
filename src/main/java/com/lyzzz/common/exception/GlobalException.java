package com.lyzzz.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @location： BlogSystem\com.lyzzz.common.exception
 * @creatTime: 2020/7/18  9:45
 * @author: Administrator
 * @remark:
 *
 * 全局异常处理器
 *
 */
public class GlobalException extends RuntimeException{

    @Getter
    @Setter
    private String msg;

    public GlobalException(String message) {
        super(message);
        this.msg = message;
    }
}
