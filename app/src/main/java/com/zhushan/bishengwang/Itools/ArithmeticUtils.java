package com.zhushan.bishengwang.Itools;

import android.util.ArrayMap;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/12/26.
 */
public class ArithmeticUtils {


  public   static int binarySearch(int[] array, int size, int value) {
        int lo = 0;
        int hi = size - 1;
        while (lo <= hi) {

            int mid = (lo + hi) >>> 1;
            int midVal = array[mid];
            if (midVal < value) {
                lo = mid + 1;
            } else if (midVal > value) {
                hi = mid - 1;
            } else {
                return mid;  // value found
            }
        }
        return ~lo;  // value not present
    }

}
