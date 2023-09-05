package com.fish.rpm.dao.util;

public class ResultResp {
    private Integer code;

    // 错误信息
    private String msg;

    // 数据
    private Object data;

    public static int successCode = 0;

    public static int failCode = -1;

    public Integer getCode() {
        if (code == null) {
            return failCode;
        }
        return code;
    }

    public ResultResp setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultResp setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResultResp respResult(Integer code, String message, Object data) {
        ResultResp vo = new ResultResp();
        vo.setCode(code);
        vo.setMsg(message);
        vo.setData(data);
        return vo;
    }

    public static ResultResp respSucc(Object data) {
        ResultResp vo = new ResultResp();
        vo.setCode(successCode);
        vo.setMsg("成功");
        vo.setData(data);
        return vo;
    }

    public static ResultResp respSucc(Object data, String msg) {
        ResultResp vo = new ResultResp();
        vo.setCode(successCode);
        vo.setMsg(msg);
        vo.setData(data);
        return vo;
    }

    public static ResultResp respFailed(String msg) {
        ResultResp vo = new ResultResp();
        vo.setCode(failCode);
        vo.setMsg(msg);
        return vo;
    }

    public boolean isSuccess(){
        return getCode() == successCode;
    }

}
