package algorithm.classic.two;

public class Poke {
    static String[] color = {"黑桃", "红桃", "梅花", "方片"};
    static String[] num = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    public void getpoke(Card card[]) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                card[i * 13 + j] = new Card(color[i], num[j]);
            }
        }
    }
}
