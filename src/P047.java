/*
P047: Distinct primes factors
The first two consecutive numbers to have two distinct prime factors are:
14 = 2 × 7
15 = 3 × 5
The first three consecutive numbers to have three distinct prime factors are:
644 = 2² × 7 × 23
645 = 3 × 5 × 43
646 = 2 × 17 × 19.
Find the first four consecutive integers to have four distinct prime factors each. What is the first of these numbers?

(訳)
P047：異なる素因数
それぞれ2つの異なる素因数を持つ連続する2つの数が最初に現れるのは:
14 = 2 × 7
15 = 3 × 5
それぞれ3つの異なる素因数を持つ連続する3つの数が最初に現れるのは:
644 = 2² × 7 × 23
645 = 3 × 5 × 43
646 = 2 × 17 × 19
最初に現れるそれぞれ4つの異なる素因数を持つ連続する4つの数を求めよ. その最初の数はいくつか?

解決方法
素因数の数が４つあるかどうかを判定するメソッドを作成し、
それを使って問題を解決しました。
*/

public class P047 {

    //異なる素因数の数が4であるか、戻り値　True False
    public static boolean Counting_prime_factors(int n) {
        int count = 0;//異なる素因数の数
        for (int i = 2; i <= n; i++) {
            //nがiで割り切れるかどうか、割り切れたらn /= i
            if (n % i == 0) {
                count++;
                if (count >= 5) return false;
                n /= i;
                //同じ素因数でできるだけ割り続ける
                while (n % i == 0) {
                    n /= i;
                    if (n == 1) break;
                }
                if (n == 1) break;
            }
        }
        return count == 4;
    }

    public static void main(String[] args) {

        int count = 0, ans, i = 210;//2×3×5×7=210

        while (true) {
            if (Counting_prime_factors(i)) count++;
            else count = 0;

            if (count == 4) {
                ans = i - 3;
                break;
            }
            i++;
        }
        System.out.println(ans);
    }
}
