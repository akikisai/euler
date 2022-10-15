/*
P067:Maximum path sum Ⅱ
By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.

    3
   7 4
  2 4 6
 8 5 9 3

That is, 3 + 7 + 4 + 9 = 23.

Find the maximum total from top to bottom in triangle.txt (right click and 'Save Link/Target As...'),
a 15K text file containing a triangle with one-hundred rows.

NOTE: This is a much more difficult version of Problem 18. It is not possible to try every route to solve this problem,
as there are 2^99 altogether!
If you could check one trillion (10^12) routes every second it would take over twenty billion years to check them all.
There is an efficient algorithm to solve it. ;o)

P067: 最大経路の和 Ⅱ
以下の三角形の頂点から下まで移動するとき, その数値の合計の最大値は23になる。

    3
   7 4
  2 4 6
 8 5 9 3

それは、3 + 7 + 4 + 9 = 23
100列の三角形を含んでいる15Kのテキストファイル triangle.txt の上から下まで最大合計を見つけよ.

注：これは, Problem 18より難しいバージョンです。
全部で2^99通りの組み合わせがあるので, この問題を解決するためにすべてのルートをためすことは可能ではない
あなたが毎秒1兆本の(10^12)ルートをチェックすることができたとしても, 全てをチェックするために200億年以上かかるでしょう.
解決するための効率的なアルゴリズムがあります。


（問題解決方法）
問題のデータ格納用の配列を用意する
下から比較しながら足していく


*/



import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class P067 {

    public static void main(String[] args) {
        //100行、100列のデータを格納する配列
        int[][] triangle = new int[100][100];
        //ファイル読み込み
        Path file = Paths.get("./src/p067_triangle.txt");
        try (BufferedReader br = Files.newBufferedReader(file)) {
            String text ;
            int i = 0;
            while ((text = br.readLine()) != null) {
                String[] text_str = text.split(" ", 0);
                for(int j = 0;j< text_str.length;j++){
                    triangle[i][j]= Integer.parseInt(text_str[j]);
                }
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //計算
        for (int i=98; i >= 0; i--){
            for (int j=0; j <= i; j++){
                triangle[i][j] += Math.max(triangle[i+1][j], triangle[i+1][j+1]);
            }
        }
        System.out.println(triangle[0][0]);


    }
}
