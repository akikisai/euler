/*
P040: Champernowne's constant
An irrational decimal fraction is created by concatenating the positive integers:
0.123456789101112131415161718192021...
It can be seen that the 12^th digit of the fractional part is 1.
If d_n represents the n^th digit of the fractional part, find the value of the following expression.
d_1 × d_10 × d_100 × d_1000 × d_10000 × d_100000 × d_1000000

(訳)
P040：シャンペルノーニュの定数
正の整数を連結すると無理数ができる。
0.123456789101112131415161718192021...
小数部の12番目の桁が1であることがわかる。
d_nが小数部のn番目の桁を表す場合、以下の式の値を求めよ。
d_1 × d_10 × d_100 × d_1000 × d_10000 × d_100000 × d_1000000

解決方法
(1)iを1から、合計桁数が1000000まで1ずつ増やすループをつくる
(2)合計桁数を表す変数を用意し、iが増えるにつれ、合計桁数に新しいiの桁数を足していく
(3)小数部のn番目に値する変数nを作り、合計桁数がn以上の場合、ほしい値はiにあるので調べて抜き出す。
*/

public class P040 {
    public static void main(String[] args) {

        int ans = 1;//答え
        int n = 1;//条件に合う桁数の〇桁目
        int digits_sum = 0;//桁数の合計

        for (int i = 1; digits_sum <= 100000; i++) {
            //桁数の合計
            String i_str = String.valueOf(i);
            digits_sum += i_str.length();
            //桁数の合計が条件の桁数以上なら、この時のiの中にほしい値が存在する
            if (digits_sum >= n) {
                //iを文字列にし、条件をオーバーした桁数によって、iの中のほしい値を抽出し、kに代入
                char num_char = i_str.charAt(i_str.length() - 1 - (digits_sum - n));
                int k = Character.getNumericValue(num_char);
                //ansに、ほしい値(k)をかける。条件の桁数を更新する(*10)
                System.out.println("d_" + n + " = " + k);
                ans *= k;
                n *= 10;
            }
        }
        System.out.println("答えは" + ans + "です。");
    }
}
