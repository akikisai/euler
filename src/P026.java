/*
P026: Reciprocal cycles
A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:
1/2	= 	0.5
1/3	= 	0.(3)
1/4	= 	0.25
1/5	= 	0.2
1/6	= 	0.1(6)
1/7	= 	0.(142857)
1/8	= 	0.125
1/9	= 	0.(1)
1/10	= 	0.1
Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.
Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.

（訳）
P026: Reciprocal cycles
単位分数には分子に1が含まれる。分母が2〜10の単位分数の10進数表記は以下のようになる。
1/2 = 0.5
1/3 = 0.(3)
1/4 = 0.25
1/5 = 0.2
1/6 = 0.1(6)
1/7 = 0.(142857)
1/8 = 0.125
1/9 = 0.(1)
1/10 = 0.1

ここで、0.1(6)は0.166666...を意味し、1桁の繰り返しの周期を持つ(循環節が6)。1/7は6桁の周期で繰り返される(循環節が142857)ことがわかる。
1/dの小数の部分に最も長い循環周期が含まれるd＜1000の値を求めよ。

前提
(1)二つの整数 a,b (ただし b は 0 でない)を用いて a/b という分数で表せる数は有理数である。(単位分数を含む)
(2)有理数は循環しない無限小数にならない、つまり今回の問題の単位分数は必ず循環小数か、有限小数になる。
(3)dは1～999

問題解決方法
(1)循環節の長さを求めるメソッドを用意する。
割っていく過程で、余りを全部リストに格納する。
出た余りが前にも同じあまりが出てきたら、循環小数だとわかる
その後同じあまりにおいて、リストのインデックス番号を使って計算すると、循環節の大きさが求められる。

(2)メソッドを利用しdが1～999までどの値で一番循環節が大きいかを求める。


*/

import java.util.ArrayList;
import java.util.List;

public class P026 {

    // n÷mの循環節の長さを求めるメソッド
    public static int cycles_number(int n, int m) {

        n = n % m;  // n=1, m=7の場合、n: 1
        //余りを格納するためのリスト
        List<Integer> remainder_list = new ArrayList<Integer>();
        while (true) {
            // n=1, m=7の場合、リスト: 1 3 2 6 4 5 1
            remainder_list.add(n);

            // n=1, m=7の場合、n: 10 30 20 60 40 50 10
            n *= 10;

            // n=1, m=7の場合、n: 3 2 6 4 5 1
            n = n % m;

            //余りがなければ、割り切れた⇒循環節の長さが0
            if (n == 0)
                return 0;

            //新しく求められた余りがすでにリストに存在すれば⇒循環小数確定
            if (remainder_list.contains(n))

                //リストの大きさ－最初の同じの余りのインデックス番号=循環節の長さになる
                // n=1, m=7の場合、 リストの大きさ6-インデックス番号0=6
                return remainder_list.size() - remainder_list.indexOf(n);

        }
    }

    public static void main(String[] args) {

        int d = 0;
        for (int i = 1; i < 1000; i++) {
            if (cycles_number(1, i) >= d)
                d = i;
        }
        System.out.println(d);

    }
}
