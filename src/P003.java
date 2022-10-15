/*
 * P003: Largest prime factor
 *
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143
 * 13195の素因数は5、7、13、29です。
 * 数600851475143の最大の素因数は何ですか？
 *
 * 前提
 * 素因数：約数でありかつ素数である　　　約数：割り切れる数     素数：1と自身のみ割れる数
 *
 * 問題解決方法
 *（1）素因数分解を行う
 *（2）最後に来た素因数の値を取得し、printする
 *
 * 具体的なやり方
 *（1）i=2から(調べる数自身)までループし、iで割り切れるかどうかを調べる
 *（2）割り切れるなら(調べる数自身)をその数で割る、再度その数を2～ループして割り切れるかを調べる
 *（3）割り切れた後の値が1ならそれが最大な素因数になる
 *
 *（注意点）2からループしているのでたとえ4という素数でない因数が来たとしてもその前に2×2という形で分解され、結局素因数になる
 */


public class P003 {

    public static void main(String[] args) {

        //調べたい数
        long num = 600851475143L;
        //最大素因数
        int max_prime_factor = 0;

        System.out.print(num + "=");

        //2～その数自身までループ
        for (int i = 2; i <= num; i++) {
            //numがiで割り切れるかどうか、割り切れたらnum /= i
            if (num % i == 0) {
                //numアップデートされる
                num /= i;
                //割った後：1 ⇒　このiが最後の素因数を意味する
                if (num == 1) {
                    System.out.println(i);
                    max_prime_factor = i;//最大素因数へ格納する
                }
                //割った後：1でない　⇒　この素因数を表示し、右に掛け算マーク
                else {
                    System.out.print(i + "×");
                }
                //アップデートされたnumを次のfor文で2～割り切れるかどうかを判定する
                //i = 1とし、次のfor文ではi++でiが2からスタートする
                i = 1;
            }
        }

        //最大素因数の表示
        System.out.println("最大の素因数は：" + max_prime_factor);

    }

}
