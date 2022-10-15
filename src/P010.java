/*
 * P010: Summation of primes
 *
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * Find the sum of all the primes below two million.
 * 10未満の素数の合計は2+3+5+7=17です。
 * 200万未満のすべての素数の合計を求めます。
 *
 * 素数判定条件
 * (1) 2, 3は素数
 * (2) 偶数は素数でない
 * (3) 数nがnの平方根以下の、3以上の奇数(3,5,7,9,11,...)で1回でも割れたら素数でない
 * (4) (2)(3)以外は素数である
 *
 * 考察：(3)の判定方法が成り立つ理由
 * 例えば、53が素数かどうか調べる。割り切れるかどうかの数が、もし約数があったとすると、その約数の一つは必ず、√53以下になっているはず。
 * よって、割る数は√53以下で調べればよいことになる。
 * 7<√53<8 なので、53の場合は、2, 3, 4, 5, 6, 7で割り切れるかどうかを調べればよい。
 * さらに、2, 3, 4, 5, 6, 7のうち、4や6は割り切れるか調べる必要はない。
 * 例えば、4で割り切れたとしたら、それは2でも割り切れるはずで、2で割り切れるかどうかを試しているのであれば、4は試す必要がない。
 * つまり、7以下の奇数だけで割り切れるかどうかを調べればよいことになる。
 * (参考)https://math-jp.net/2017/03/10/how-to-find-a-prime-number/
 *
 * 問題解決方法
 *（1）素数を判定する関数を作る
 *（2）その関数を用いて、2～1999999の間の数を判定し、素数なら足していく
 *
 */
public class P010 {

    //素数を判定する関数
    public static boolean isPrime(long num) {
        //numが2以下⇒素数でない
        if (num < 2) return false;
        //numが2の時⇒素数
        else if (num == 2) return true;
        //numが偶数の時⇒素数でない
        else if (num % 2 == 0) return false;
        //nがnの平方根以下の、3以上の奇数(3,5,7,9,11,...)で1回でも割れたら素数でない
        double sqrtNum = Math.sqrt(num);
        for (int i = 3; i <= sqrtNum; i += 2) {//偶数取り除いているので、2から始める必要ない
            if (num % i == 0) {
                // 素数ではない
                return false;
            }
        }
        // 素数である
        return true;

    }

    public static void main(String[] args) {

        long num = 2000000L;
        long sum_prime = 0L;

        for (long i = 2L; i < num; i++) {
            if (isPrime(i)) sum_prime += i;
        }

        System.out.println( num + "未満のすべての素数の合計は　" + sum_prime + "　です。");

    }
}