//package tripleh.happyhappyhappy.com.api.feign;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.Map;
//
///**
// * Author: zixli
// * Date: 2020/8/26 21:02
// * FileName: FeignApi
// * Description: 调用自身feign
// */
//@FeignClient(name = "feignServer", url = "http://localhost:8889")
//public interface FeignApi {
//
//    /**
//     * @RequestParam 可以将map参数转换成k-v形式传递  如果不加会报[401] during [POST]...
//     * @param parameters
//     * @return
//     */
//    @RequestMapping(value = "/oauth/token", method = RequestMethod.POST)
//    ResponseEntity<OAuth2AccessToken> postAccessToken(@RequestParam Map<String, String> parameters);
//
//}
