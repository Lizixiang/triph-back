//package tripleh.happyhappyhappy.com.config;
//
//import org.springframework.security.core.userdetails.User;
//import tripleh.happyhappyhappy.com.tripleh.happy.entity.HUser;
//
//import java.util.Collections;
//
///**
// * Author: zixli
// * Date: 2020/8/26 13:03
// * FileName: CustomerUserDetails
// * Description: oauth2 用户对象
// */
//public class CustomerUserDetails extends User {
//
//    private HUser hUser;
//
//    public CustomerUserDetails(HUser hUser) {
//        super(hUser.getUsername(), hUser.getPassword(), true, true, true, true, Collections.EMPTY_SET);
//        this.hUser = hUser;
//    }
//
//    public HUser gethUser() {
//        return hUser;
//    }
//
//    public void sethUser(HUser hUser) {
//        this.hUser = hUser;
//    }
//}
