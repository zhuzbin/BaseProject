package com.zhuzb.util;

import java.util.HashSet;
import java.util.Set;

/**
 * Desc：字符串工具类
 * User：ZhuZhiBin
 * Date：2018/1/24
 * Time：13:58
 */
public class StringUtil {

    /**
     * Desc：判断字符串是否为空
     * User：ZhuZhiBin
     * Date：2018/1/24
     * Time：13:58
     */
    public static boolean str(String str){
        if(str!=null&&!"".equals(str)){
            return true;
        }
        return false;
    }

    public static Set<Long> retSetLong(String strs){
        String[] s = strs.split(",");
        Set<Long> set = new HashSet<Long>();
        for(int i =0;i<s.length;i++){
            if(str(s[i])){
               set.add(Long.valueOf(s[i]));
            }
        }
        return set;
    }
}
