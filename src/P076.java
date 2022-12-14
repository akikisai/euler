/*
P076:Counting summations
It is possible to write five as a sum in exactly six different ways:

4 + 1
3 + 2
3 + 1 + 1
2 + 2 + 1
2 + 1 + 1 + 1
1 + 1 + 1 + 1 + 1

How many different ways can one hundred be written as a sum of at least two positive integers?

【訳】
5は数の和として6通りに書くことができる:

4 + 1
3 + 2
3 + 1 + 1
2 + 2 + 1
2 + 1 + 1 + 1
1 + 1 + 1 + 1 + 1

2つ以上の正整数の和としての100の表し方は何通りか。

【問題解決方法】
漸化式で解くやり方。
整数nを順序を区別せずに「m以下の自然数」の和に分ける場合の数

f(n, m)=
1;                  (n=1 or m=1)
f(n,n);             (n<m)
1+f(n,m-1);         (n=m)
f(n-m,m)+f(n,m-1);  (n>m)

*/


public class P076 {
    public static int f(int n, int m) {
        if (n == 1 || m == 1) {
            return 1;
        }
        if (n < m) {
            return f(n, n);
        } else if (n == m) {
            return 1 + f(n, n - 1);
        } else {
            return f(n - m, m) + f(n, m - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(f(100, 99));

    }
}