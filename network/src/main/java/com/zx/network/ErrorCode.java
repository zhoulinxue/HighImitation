package com.zx.network;

/**
 * Name: ErrorCode
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-14 13:58
 */
public enum ErrorCode {
    SUC(200, "操作成功"),
    FAIL(300, "操作失败"),
    PARAM_ERROR(10401, "请求参数错误"),
    PARAM_EMPTY(10402, "参数缺失"),
    TOKEN_ERROR(10403, "缺少token参数"),
    METHOD_GET(10405, "需要GET请求"),
    METHOD_POST(10406, "需要POST请求"),
    REQUEST_BUSY(10407, "请求过于太频繁，请稍候再试"),
    USER_ERROR(10409, "用户受限"),
    UNKNOWN_ERROR(10500, "未知错误"),
    CONNECT_SQLLITE_FAIL(10501, "连接数据库失败"),
    OPEN_SQLIITE_ERROR(10502, "打开数据库失败"),
    SQL_EXCUTE_ERROR(1050, "执行SQL语句失败"),
    PROMISS_FAIL(10504, "事务执行失败"),
    SYSTEM_BUSY(10505, "系统繁忙，请稍候再试"),
    SYSTEM_ERROR(10506, "系统错误");

    private int errorCode;
    private String errorMsg;

    ErrorCode(int code, String msg) {
        this.errorCode = code;
        this.errorMsg = msg;
    }

//    操作成功
//300	操作失败
//10401	请求参数错误
//10402	参数缺失
//10403	缺少token参数
//10405	需要GET请求
//10406	请需要POST请求
//10407	请求过于太频繁，请稍候再试
//10408	用户未授权
//10409	用户受限，可能是违规被封禁
//10500	未知错误
//10501	连接数据库失败
//10502	打开数据库失败
//10503	执行SQL语句失败
//10504	事物执行失败
//10505	系统繁忙，请稍候再试
//10506	系统错误

}
