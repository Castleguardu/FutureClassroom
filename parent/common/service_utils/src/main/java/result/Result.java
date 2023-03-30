package result;

import lombok.Data;

@Data
public class Result<T> {  //用T代表泛类型，因为不知道data到底是什么数据，所以就用T泛类型表示

    private Integer code;

    private String message;

    private T data;

    public Result() {

    }

    //成功的方法
    public static<T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        if (data!=null)result.setData(data);
        result.setCode(200);
        result.setMessage("成功");
        return result;
    }

    //失败的方法
    public static<T> Result<T> fail(T data) {
        Result<T> result = new Result<>();
        if (data!=null)result.setData(data);
        result.setCode(201);
        result.setMessage("失败");
        return result;
    }



}
