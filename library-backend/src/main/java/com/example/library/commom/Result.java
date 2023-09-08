package com.example.library.commom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public Result(T data) {
        this.data = data;
    }

    public static Result<Object> success() {
        Result<Object> result = new Result<>();
        result.setCode("0");
        result.setMsg("success");
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(data);
        result.setCode("0");
        result.setMsg("success");
        return result;
    }

    public static Result<Object> error(String code, String msg) {
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
