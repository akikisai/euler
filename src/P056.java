/*
P056:Powerful digit sum

A googol (10^100) is a massive number: one followed by one-hundred zeros;
100^100 is almost unimaginably large: one followed by two-hundred zeros.
Despite their size, the sum of the digits in each number is only 1.
Considering natural numbers of the form, a^b, where a, b < 100, what is the maximum digital sum?

P056:べき乗の数字和

Googol (10^100)は非常に大きな数である: 1の後に0が100個続く。
100^100は想像を絶する: 1の後に0が200回続く。
その大きさにも関わらず, 両者とも数字和 ( 桁の和 ) は1である。
a, b < 100 について自然数 a^b を考える. 数字和の最大値を答えよ。

（問題解決方法）
総当たりでa^bを全部求めて、
数字和を求める関数で数字和を求め、リストへ格納させ、
リストの最大値を出力する。


*/

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class P056 {
    //数字和 (桁の和) を求める関数　(P016の問題でまったく同じ関数を使った)
    public static BigInteger sumDigits(BigInteger n) {
        BigInteger sum = BigInteger.valueOf(0);
        while (n.compareTo(BigInteger.valueOf(0)) != 0) {
            sum = sum.add(n.remainder(BigInteger.valueOf(10)));
            n = n.divide(BigInteger.valueOf(10));
        }
        return sum;
    }
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int a = 2; a < 100; a++) {
            for (int b = 2; b < 100; b++) {
                BigInteger bi_a = BigInteger.valueOf(a);// a ⇒ BigInteger
                BigInteger num_pow = bi_a.pow(b);// a^bをnum_powに代入する
                BigInteger bi_sum = sumDigits(num_pow);// num_powの数字和を求めて、bi_sumに代入する
                int int_sum = bi_sum.intValue();// bi_sum ⇒ int_sum
                list.add(int_sum);// listに追加する
            }
        }
        Optional<Integer> opNum1 = list.stream().max(Comparator.naturalOrder());
        System.out.println("answer is " + opNum1.get());

    }
}
