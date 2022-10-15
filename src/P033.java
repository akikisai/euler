/*
P033:Digit cancelling fractions

The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may
incorrectly believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.

We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
There are exactly four non-trivial examples of this type of fraction,
less than one in value, and containing two digits in the numerator and denominator.

If the product of these four fractions is given in its lowest common terms, find the value of the denominator.

（訳）
P033:桁消し分数

49/98という分数は不思議な分数で，経験の浅い数学者がこれを単純化しようとすると
49/98 = 4/8 と確信していて、これは9を消すことで得られると考えるからです。

30/50 = 3/5のような分数は自明な例であると考えることにする。
また、このような分数で、自明でない例は、ちょうど4つある。
分子と分母に2桁の数字が含まれ、値が1未満である。

これらの4つの分数の積が最小公倍数で与えられる場合、分母の値を求めよ。

条件
(1)問題条件より、2桁の分子をab, 2桁の分母bcで表すと、ab/bc = a/cになる。
(2)30/50 = 3/5　など、自明である例を除く（例えば　22/44 = 2/4）
(3)分子と分母に2桁の数字が含まれ、値が1未満である。
(4)条件にあてはまる分数は4つのみ

解決方法
ab/bc = a/cにおいて、
左辺と右辺両方を約分し、等しいかどうかを確認する。
条件に満たす分子、分母を見つけたら、リストへ格納し、
4つの分数を全部掛け算してから、約分する。分母は答えになる。

約分方法としては、分子分母をそれらの最大公約数でわる。

*/


import java.util.ArrayList;
import java.util.List;

public class P033 {

    //2数の最大公約数を求めるメソッド
    public static int gcd(int m, int n) {
        int r;
        while (n > 0) {
            r = m % n;
            m = n;
            n = r;
        }
        return m;
    }


    public static void main(String[] args) {

        //分子
        int molecule;
        //分母
        int denominator;
        //2桁の分子をab, 2桁の分母bcで表す。問題より、ab/bc = a/cになるはず
        int a, b, c;
        //条件にあう分子を格納する
        List<Integer> molecule_list = new ArrayList<>();
        //条件にあう分母を格納する
        List<Integer> denominator_list = new ArrayList<>();
        //最大公約数
        int gcd_n1, gcd_n2;

        //aのループ、a:1～9
        for (a = 1; a <= 9; a++) {
            //bのループ、b:1～9
            for (b = 1; b <= 9; b++) {
                //cのループ、c:1～9
                for (c = 1; c <= 9; c++) {
                    molecule = a * 10 + b;
                    denominator = b * 10 + c;
                    gcd_n1 = gcd(molecule, denominator);
                    gcd_n2 = gcd(a, c);
                    if (
                            molecule < denominator &&   //分子は分母より小さい
                            molecule / gcd_n1 == a / gcd_n2 &&  //約分したとき、分子の比較
                            denominator / gcd_n1 == c / gcd_n2  //約分したとき、分母の比較
                    ) {
                        molecule_list.add(molecule);
                        denominator_list.add(denominator);
                    }


                }
            }
        }

        //積のための計算用
        int n1 = 1, n2 = 1, ans;

        System.out.print("分子：");
        for (Integer integer : molecule_list) {
            System.out.print(integer + " ");
            n1 *= integer;
        }
        System.out.println();
        System.out.print("分母：");
        for (Integer integer : denominator_list) {
            System.out.print(integer + " ");
            n2 *= integer;
        }
        System.out.println();

        //答え
        ans = n2 / gcd(n1, n2);
        System.out.println("答えは" + ans + "です。");


    }
}