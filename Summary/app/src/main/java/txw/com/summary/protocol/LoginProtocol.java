package txw.com.summary.protocol;

import txw.com.summary.entity.UserInfo;

/**
 * 登录信息
 * Created by txw on 2018/7/16.
 */
public class LoginProtocol implements Protocol {

    public LoginProtocol(UserInfo userInfo) {

    }

    @Override
    public byte[] outputData() {
        return new byte[0];
    }
}
