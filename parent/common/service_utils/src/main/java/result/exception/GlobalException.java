package result.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import result.Result;

/**
 * Description:
 * Param:
 * return:
 * Author: KIKI
 * Date: 2023-03-29
 */
@ControllerAdvice  //aop
public class GlobalException {

//    全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail(null);
    }
//    特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e) {
        e.printStackTrace();
//        return Result.fail(null).message("执行全局异常处理");//不知道为啥这里会出错
        return Result.fail(null);
    }
    //    自定义异常处理
    @ExceptionHandler(ftcrException.class)
    @ResponseBody
    public Result error(ftcrException e) {
        e.printStackTrace();
//        return Result.fail(null).message("执行ftcrException异常处理");  //跟上面的同样问题
        return Result.fail(null);
    }
}
