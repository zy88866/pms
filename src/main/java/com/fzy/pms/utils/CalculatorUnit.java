package com.fzy.pms.utils;

import java.math.BigDecimal;

/**
 * @program: CalculatorUnit
 * @description: 数据计算工具类
 * @author: fzy
 * @date: 2019-04-19 20:18
 **/
public class CalculatorUnit {

    /**
     * 计算两个数之和
     * @param sum
     * @param addend
     * @return 和值
     */
    public static BigDecimal sum (BigDecimal sum, BigDecimal addend) {
        return sum.add(addend);
    }

    /**
     * 计算两个数之差
     * @param minuend
     * @param subtrahend
     * @return
     */
    public static BigDecimal sub (BigDecimal minuend, BigDecimal subtrahend) {
        return minuend.subtract(subtrahend);
    }

    /**
     * 计算两个数积
     * @param multiplicand
     * @param multiplier
     * @return
     */
    public static BigDecimal mulPlus(BigDecimal multiplicand, BigDecimal multiplier) {
        return multiplicand.multiply(multiplier);
    }

    /**
     * 计算两个数商
     * @param dividend
     * @param divisor
     * @return
     */
    public static BigDecimal div(BigDecimal dividend, BigDecimal divisor) {
        if(0 == divisor.compareTo(BigDecimal.ZERO)){
            return BigDecimal.ZERO;
        }
        return dividend.divide(divisor);
    }


}
