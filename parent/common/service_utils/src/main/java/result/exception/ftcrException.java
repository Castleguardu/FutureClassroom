package result.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * Param:
 * return:
 * Author: KIKI
 * Date: 2023-03-29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ftcrException extends RuntimeException{
    private Integer code;
    private String msg;

}
