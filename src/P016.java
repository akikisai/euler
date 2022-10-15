import java.math.BigInteger;

/*
 * P016: Power digit sum
 *
 * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
 * What is the sum of the digits of the number 2^1000?
 * 2^15 = 32768で、各桁の数の合計は 3 + 2 + 7 + 6 + 8 = 26
 * 数字2^1000の各桁の数の合計は何か？
 *
 *
 * 問題解決方法
 * (1) BigIntegerを使用する。long型だと、2^1000は上限を超えてしまうため。
 * (2) for文で2^1000を求める。
 * (3) 各桁の数を足し算し、合計sumを戻り値とするメソッドを作る。
 * (4) 剰余演算子%を使って一桁目（最右端）の数を取得し、/=10を使用しもとの数を一桁右へずらし、
 * 　  再度剰余演算子%を使って一桁目（最右端）の数を取得する、というループで、それぞれ取得した数をnumに足していく
 *
 */

public class P016 {
    //各桁の数を足し算するメソッド
    public static BigInteger sumDigits(BigInteger n) {
        //sum = 0
        BigInteger sum = BigInteger.valueOf(0);
        //自然数の各桁の和を計算
        //n != 0
        while (n.compareTo(BigInteger.valueOf(0)) != 0) {
            //再右端の桁を得るために%10を利用する。
            // sum += n % 10と同じ意味
            sum = sum.add(n.remainder(BigInteger.valueOf(10)));

            //再右端の桁を取り除くためにn/=10を利用する。
            //n /= 10と同じ意味
            n = n.divide(BigInteger.valueOf(10));
        }

        return sum;//合計を返す
    }

    public static void main(String[] args) {

        BigInteger num = BigInteger.valueOf(1);
        for (int i = 0; i < 1000; i++) {
            num = num.multiply(BigInteger.valueOf(2));
        }
        System.out.println("2の1000乗は　" + num + "　です。");
        System.out.println("2の1000乗の桁数の合計は　" + sumDigits(num) + "　です。");



    }
}
