package algorithm.classic.one;

import java.util.ArrayList;
import java.util.Random;

public class Poker1 {

    public static void main(String[] args) {
        process();
    }


    public static void process() {
        //1、用一个String[]数组存花色
        String[] hs = {"黑桃♠", "红桃♥", "梅花♣", "方片♦"};
        //2、用一个String[]数组存点数
        String[] num = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        //3、用一个String[]数组存大王、小王
        String[] Ww = {"大王", "小王"};

        Random random = new Random();
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                String Card = hs[i].concat(num[j]); //4.花色和点数 拼成一张牌
                list.add(Card);                     //5.将52添加到list集合中
            }
        }
        list.add(Ww[0]);
        list.add(Ww[1]);   //54张牌都已添加到list集合中

        /*
        6、模拟给4个人随机发牌，每个人11张牌
        */
        System.out.println("发牌：");
        //每人11张牌,并显示手牌,A用户
        ArrayList<String> AList = new ArrayList<>();
        assemble(random, list, "A", AList);

        //B用户
        ArrayList<String> BList = new ArrayList<>();
        assemble(random, list, "B", BList);

        //C用户
        ArrayList<String> CList = new ArrayList<>();
        assemble(random, list, "C", CList);

        //D用户
        ArrayList<String> DList = new ArrayList<>();
        assemble(random, list, "D", DList);

         /*
      7、显示剩余的牌
     */
        //剩余牌
        ArrayList<String> S = new ArrayList<>();
        int s = list.size();//获取剩余牌数
        for (int i = 0; i < s; i++) {

            String S_card = list.remove(random.nextInt(list.size()));

            S.add(S_card); //将剩余牌添加到S集合
        }
        System.out.println("剩余的牌:" + S);

    }


    private static void assemble(Random random, ArrayList<String> list, String userName, ArrayList<String> userList) {
        for (int i = 0; i < 11; i++) {
            String handcard = list.remove(random.nextInt(list.size()));  //删除并返回指定位置的元素
            userList.add(handcard); //将手牌添加到用户A集合
        }
        System.out.printf("用户%s:%s\n", userName, userList);
    }
}
