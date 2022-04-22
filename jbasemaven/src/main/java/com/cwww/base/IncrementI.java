package com.cwww.base;

import lombok.extern.slf4j.Slf4j;

/**
 * 递增I
 *
 * @author wei.cai@hand-china.com 2022/4/22
 */
@Slf4j
public class IncrementI {

    private int i = 0;

    /**
     * 先进行I的递增,然后返回I值
     * @return 返回值
     */
    public int increaseI() {
        return ++i;
    }

    /**
     * 先返回I值,然后递增I
     * @return 返回值
     */
    public int iIncrease() {
        return i++;
    }

    /**
     * 普通I递增,将1加到i，将其写入i
     * @return 返回值
     */
    public int normalIncrease() {
        i = i + 1;
        return i;
    }

    public int getI() {
        return i;
    }

    public static void main(String[] args) {
        IncrementI incrementI = new IncrementI();

        log.info("Before increaseI, value:[{}]", incrementI.getI());
        final int increaseIReturn = incrementI.increaseI();
        log.info("increaseIReturn, value:[{}]", increaseIReturn);
        log.info("After increaseI, value:[{}]", incrementI.getI());

        log.info("Split Line ============================");

        log.info("Before iIncrease, value:[{}]", incrementI.getI());
        final int iIncreaseReturn = incrementI.iIncrease();
        log.info("iIncreaseReturn, value:[{}]", iIncreaseReturn);
        log.info("After iIncrease, value:[{}]", incrementI.getI());

        log.info("Split Line ============================");

        log.info("Before normalIncrease, value:[{}]", incrementI.getI());
        final int normalIncreaseReturn = incrementI.normalIncrease();
        log.info("normalIncreaseReturn, value:[{}]", normalIncreaseReturn);
        log.info("After normalIncrease, value:[{}]", incrementI.getI());

    }

}
