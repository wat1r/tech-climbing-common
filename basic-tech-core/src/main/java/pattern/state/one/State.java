package pattern.state.one;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/29 9:54
 * @description:
 */
public abstract class State {
    // 扣除积分 - 50
    public abstract void deductMoney();

    // 是否抽中奖品
    public abstract boolean raffle();

    // 发放奖品
    public abstract void dispensePrize();

}
