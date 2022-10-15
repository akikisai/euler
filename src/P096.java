/*
P096:su doku
Su Doku (Japanese meaning number place) is the name given to a popular puzzle concept.
Its origin is unclear, but credit must be attributed to Leonhard Euler who invented a similar,
and much more difficult, puzzle idea called Latin Squares. The objective of Su Doku puzzles, however,
is to replace the blanks (or zeros) in a 9 by 9 grid in such that each row, column,
and 3 by 3 box contains each of the digits 1 to 9. Below is an example of a typical starting puzzle grid and its solution grid.

003 | 020 | 600             483 | 921 | 657
900 | 305 | 001             967 | 345 | 821
001 | 806 | 400             251 | 876 | 493
008 | 102 | 900             548 | 132 | 976
700 | 000 | 008             729 | 564 | 138
006 | 708 | 200             136 | 798 | 245
002 | 609 | 500             372 | 689 | 514
800 | 203 | 009             814 | 253 | 769
005 | 010 | 300             695 | 417 | 382

A well constructed Su Doku puzzle has a unique solution and can be solved by logic,
although it may be necessary to employ "guess and test" methods in order to eliminate options (there is much contested opinion over this).
The complexity of the search determines the difficulty of the puzzle;
the example above is considered easy because it can be solved by straight forward direct deduction.
The 6K text file, sudoku.txt (right click and 'Save Link/Target As...'),
contains fifty different Su Doku puzzles ranging in difficulty,
but all with unique solutions (the first puzzle in the file is the example above).
By solving all fifty puzzles find the sum of the 3-digit numbers found in the top left corner of each solution grid;
for example, 483 is the 3-digit number found in the top left corner of the solution grid above.


【訳】

"数独"(日本語でナンバープレイスのこと)とは人気があるパズルの名前である。起源は不明ですが、その評判はラテン語で"Squares"と呼ばれる同様な、
そしてはるかに難しいパズルを考案したレオンハルト・オイラーの貢献によるものに違いない。しかしながら、
"数独"パズルの目的はそれぞれの行、列が3×3の枠を含む9×9の格子の空白（もしくは0）をそれぞれ1から9の数字で置き換えることである、
下に, 一般的なパズルの開始状態とその解答の例がある。

003 | 020 | 600             483 | 921 | 657
900 | 305 | 001             967 | 345 | 821
001 | 806 | 400             251 | 876 | 493
008 | 102 | 900             548 | 132 | 976
700 | 000 | 008             729 | 564 | 138
006 | 708 | 200             136 | 798 | 245
002 | 609 | 500             372 | 689 | 514
800 | 203 | 009             814 | 253 | 769
005 | 010 | 300             695 | 417 | 382


うまく作られている"数独"パズルは、選択肢を消去するために"仮定とテスト"方式を用いる必要があるかもしれないが、
ただ一つの解を持ち, 論理によって解くことができる(これについては様々な意見がある)
探索の複雑さがパズルの難易度を決定する;上に挙げた例は、単純で直接的な推論によって解く事ができるため、簡単であると考えられる。
6kバイトのテキストファイルにはただ一つの解を持つ、様々な難易度の50の"数独"パズルが含まれている(上の例題はこのファイルにおける最初のパズルである)
50すべてのパズルを解き、それぞれの解答の左上隅にある3桁の数の合計を求めよ; 例えば483は上の解答例の左上隅の3桁の数である。

【問題解決方法】
0を見つけたら、for文で各マスに1-9を入れてみる。
for文の中には再帰関数を使用し、行、列、3×3ブロックに重複した数字をのぞく数字を一旦答えとみなし格納する。
行、列、3×3ブロックに重複した数字を全部のぞくと数字が残らなければ、前に埋めた数字が間違っているので、バックトラックで0に戻し、
やり直す。これを答えが全部導くまでに繰り返す。

答え：24702
実行時間：約2s

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class P096 {
    //問題を格納する：puzzle[問題番号0-49][列][行]
    public static int[][][] puzzle = new int[50][9][9];
    //それぞれの問題の先頭3数合計を格納する：sum[0-49]
    public static int[] sum = new int[50];

    public static void main(String[] args) {
        //ファイル読み込み
        Path file = Paths.get("./src/p096_sudoku.txt");
        try (BufferedReader br = Files.newBufferedReader(file)) {
            String text;
            int i = 0, num = 0;
            while ((text = br.readLine()) != null) {
                if (text.length() != 9) {
                    i = 0;
                    num = Integer.parseInt(text.substring(5, 7)) - 1;
                    continue;
                }
                for (int j = 0; j < text.length(); j++) {
                    puzzle[num][i][j] = Character.getNumericValue(text.charAt(j));
                }
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int ans = 0;//答え
        for (int i = 0; i < 50; i++) {
            solve(i, 0, 0);//solve関数を呼び出す
            ans += sum[i];//sumの合計が答えになる
        }
        System.out.println(ans);//24702

    }

    public static void solve(int n, int i, int k) {
        //最後の行の最後の値より（8行目、9列目）、次の行に行く場合、無事解き終わったことを意味する
        if (i == 8 && k == 9) {
            //解けたので、答えをsumに代入する
            sum[n] = puzzle[n][0][0] * 100 + puzzle[n][0][1] * 10 + puzzle[n][0][2];
            return;
        }
        //最終列であれば、行数+1、列数は0
        if (k == 9) {
            i++;
            k = 0;
        }
        //各マスについて検索し、0であれば細かい判定をする
        if (puzzle[n][i][k] == 0) {
            for (int m = 1; m <= 9; m++) {
                if (judge(n, i, k, m)) {
                    puzzle[n][i][k] = m;
                    solve(n, i, k + 1);
                    //もし次のマス1-9の数字がだめだったら、前のマスの数字を0に戻す（バックトラック）
                    puzzle[n][i][k] = 0;
                }
            }
        } else {
            //次のマスに0以外の数字があれば、列数+1で次のマスの判定に入る
            solve(n, i, k + 1);
        }

    }

    //数字は入れるかどうかを判定するメソッド
    public static boolean judge(int n, int x, int y, int v) {
        for (int i = 0; i < 9; i++) {
            //同じ行、同じ列に重複した数字はあるか
            if (puzzle[n][x][i] == v || puzzle[n][i][y] == v) {
                return false;
            }
        }
        //3×3ブロックに重複した数字はあるか
        int row = x / 3;
        int col = y / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[n][row * 3 + i][col * 3 + j] == v) {
                    return false;
                }
            }
        }
        return true;
    }
}