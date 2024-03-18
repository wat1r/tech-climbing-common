package algorithm.classic.two;

public class Player {
    Card[] card = new Card[13];

    Player(Card card[], int flag[]) {
        for (int i = 0; i < 13; i++) {
            int x = (int) (Math.random() * 52);
            if (flag[x] == 0) {
                this.card[i] = card[x];
                flag[x] = 1;
            } else i--;
        }
    }

    public void print() {
        for (int i = 0; i < 13; i++) {
            System.out.print(this.card[i].str + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Card[] cards = new Card[52];
        Poke poke = new Poke();
        poke.getpoke(cards);
        int[] flag = new int[52];
        Player[] players = new Player[4];
        for (int i = 0; i < 4; i++) {
            System.out.print("第" + i + "牌手：");
            players[i] = new Player(cards, flag);
            players[i].print();
        }
    }
}
