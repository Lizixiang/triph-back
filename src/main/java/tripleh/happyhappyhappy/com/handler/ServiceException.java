package tripleh.happyhappyhappy.com.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: zixli
 * Date: 2020/8/20 16:35
 * FileName: ServiceException
 * Description: 业务异常
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceException extends RuntimeException{

    private String message;

}
