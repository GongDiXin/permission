package com.mmall.common.constant;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/4/26 23:39
 */
public class Constant {

    /**
     * 用户相关
     * */


    /**
     * 用户状态 0-冻结
     * */
    public static final Integer STATUS_1 = 0;

    /**
     * 用户状态 1-正常
     * */
    public static final Integer STATUS_2 = 1;

    /**
     * 用户状态 2-删除
     * */
    public static final Integer STATUS_3 = 2;

    /**
     * 邮件相关
     * */

    /**
     * 邮件主题
     * */
    public static final String MAIL_SUBJECT = "系统登录密码";

    /**
     * 邮件消息
     * */
    public static final String MAIL_MESSAGE = "登录初始密码为：";

    /**
     * 邮件提示
     * */
    public static final String MAIL_TIP = " 请及时登录系统修改初始密码";
}
