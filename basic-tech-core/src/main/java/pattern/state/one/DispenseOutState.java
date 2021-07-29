package pattern.state.one;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/29 10:03
 * @description: 奖品发放完毕状态
 * 说明，当我们 activity 改变成 DispenseOutState， 抽奖活动结束
 */
public class DispenseOutState extends State {

    // 初始化时传入活动引用
    RaffleActivity activity;


    public DispenseOutState(RaffleActivity activity) {
        this.activity = activity;
    }

    @Override
    public void deductMoney() {
        System.out.println("奖品发送完了，请下次再参加");
    }


    @Override
    public boolean raffle() {
        System.out.println("奖品发送完了，请下次再参加");
        return false;


    }


    @Override
    public void dispensePrize() {
        System.out.println("奖品发送完了，请下次再参加");
    }
}