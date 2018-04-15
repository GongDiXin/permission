package com.mmall.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author GongDiXin
 * @version 1.0
 * @created 2018/4/10 22:07
 */
public class LevelUtil {

    private LevelUtil() {}

    public static final String SEPARATOR = ".";

    public static final String ROOT = "0";

    /**
     * 层级计算
     *
     * @author GongDiXin
     * @date 2018/4/10 22:11
     * @param parentLevel
     * @param parentId
     * @return
    */
    public static String calculateLevel(String parentLevel, int parentId) {

        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        }
        return StringUtils.join(parentLevel, SEPARATOR, parentId);
    }
}
