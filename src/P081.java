/*
P081:Path sum: two ways
In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom right,
by only moving to the right and down, is indicated in bold red and is equal to 2427.

131	673	234	103	18
201	96	342	965	150
630	803	746	422	111
537	699	497	121	956
805	732	524	37	331


Find the minimal path sum from the top left to the bottom right by only moving right and down in matrix.
txt (right click and "Save Link/Target As..."), a 31K text file containing an 80 by 80 matrix.

【訳】
P081:経路の和:2方向
下記の5次の正方行列で, 左上のセルから開始し右下のセルで終わるパスを探索する。ただし下方向と右方向にのみ移動できるものとする。
通過したセルの和が最小となるパスは【131→201→96→342→746→422→121→37→331】で, その合計は2427である。

131 673	234	103	18
201	96	342	965	150
630	803	746	422	111
537	699	497	121	956
805	732	524	37	331

今, 31Kのテキストファイルmatrix.txtには80×80の行列が書かれていて、同様に左上のセルから開始し右下のセルで終わり,
かつ右方向と下方向にのみ移動するときの最小のパスの和を求めよ。

【問題解決方法】

131 673	234	103	18
201	96	342	965	150
630	803	746	422	111
537	699	497	121	956
805	732	524	37	331

問題を格納した二次元配列を用意する。
1行目、1列目それぞれ計算した数値を格納する。
左上から右下まで1行ずつ計算していき、比較しながら最小値を格納する。


※わかりやすく示すと、変形すると以下の形になり、これはP067最大経路の和と似たような問題で、同じ考え方で解ける。

        131
      201 673
    630 96  234
  537 803 342 103
805 699 746 965 18
  732 497 422 150
    524 121 111
      37  956
        331



*/


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class P081 {

    public static void main(String[] args) {

        //100行、100列のデータを格納する配列
        int[][] data = new int[80][80];
        //ファイル読み込み
        Path file = Paths.get("./src/p096_sudoku.txt");
        try (BufferedReader br = Files.newBufferedReader(file)) {
            String text;
            int i = 0;
            while ((text = br.readLine()) != null) {
                String[] text_str = text.split(",", 0);
                for (int j = 0; j < text_str.length; j++) {
                    data[i][j] = Integer.parseInt(text_str[j]);
                }
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //1行目,1列目 (比較する必要なし)
        for (int i = 1; i < 80; i++) {
            data[0][i] += data[0][i - 1];
            data[i][0] += data[i - 1][0];
        }
        //2行目,2列目以降 (比較する必要あり)
        for (int i = 1; i < 80; i++) {
            for (int k = 1; k < 80; k++) {
                data[i][k] += Math.min(data[i - 1][k], data[i][k - 1]);
            }
        }

        System.out.println(data[79][79]);

    }
}