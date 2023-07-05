package com.mcfly.springtemp.algorithms;

import java.util.Arrays;

/**
 * Ищет медиану двух массивов.
 * 2 шага:
 * слияние массивов
 * получение и вычисление среднего арифметического 2-х срединных элементов
 */
public final class TwoArraysMedianFinder extends BaseAlgorithm<TwoArraysMedianFinder.TwoArrays> {

    static final class TwoArrays {
        int[] nums1;
        int[] nums2;

        TwoArrays(int[] nums1, int[] nums2) {
            this.nums1 = nums1;
            this.nums2 = nums2;
        }

        @Override
        public String toString() {
            return Arrays.toString(nums1) + " " + Arrays.toString(nums2);
        }
    }

    @Override
    TwoArrays[] getArguments() {
        return new TwoArrays[] {
                new TwoArrays(new int[] {1, 3}, new int[]{2}),
                new TwoArrays(new int[] {1, 2}, new int[]{3, 4}),
                new TwoArrays(new int[] {1000}, new int[]{1001}),
        };
    }

    @Override
    Double calculate(TwoArrays twoArrays) {
        final int[] nums1 = twoArrays.nums1;
        final int[] nums2 = twoArrays.nums2;
        final int[] merged = new int[nums1.length + nums2.length];
        int i1 = 0, i2 = 0;
        while (i1 != nums1.length || i2 != nums2.length) {
            int v1 = i1 >= nums1.length ? Integer.MAX_VALUE : nums1[i1];
            int v2 = i2 >= nums2.length ? Integer.MAX_VALUE : nums2[i2];
            merged[i1 + i2] = Math.min(v1, v2);
            if (v1 < v2) {
                i1++;
            } else {
                i2++;
            }
        }
        int mergedLength = merged.length;
        if (mergedLength % 2 == 0) {
            final int x1 = (int)Math.floor(mergedLength / 2d) - 1;
            int t1 = merged[x1];
            int t2 = merged[x1 + 1];
            return (t1 + t2) / 2d;
        } else {
            return (double) merged[ (int)Math.floor(mergedLength / 2d) ];
        }
    }
}