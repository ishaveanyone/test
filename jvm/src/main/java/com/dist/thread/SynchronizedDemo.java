/**
 * Date: 2020-07-23 16:05
 * Author: xupp
 */

package com.dist.thread;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SynchronizedDemo {
    public static void main(String[] args) {
        lengthOfLongestSubstring("abcabcbb");

    }
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        List<Character> set = new ArrayList();
        int result = 0, i = 0, j = 0;
        while (i < n && j < n) {
            //charAt：返回指定位置处的字符
            if (!set.contains(s.charAt(j))) {
                set.add((Character)s.charAt(j));
                j++;
                result = Math.max(result, j - i);
            } else {
                set.remove((Character)s.charAt(i));
                i++;
            }
        }
        return result;

    }
    private synchronized static void method() {
    }
}
