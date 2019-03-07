package txw.com.summary.entity;

import java.io.Serializable;

/**
 * 用户信息
 * Created by txw on 2018/7/16.
 */
public class UserInfo implements Serializable {
    private String userName;

    private String password;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
