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


�y��z

"����"(���{��Ńi���o�[�v���C�X�̂���)�Ƃ͐l�C������p�Y���̖��O�ł���B�N���͕s���ł����A���̕]���̓��e�����"Squares"�ƌĂ΂�铯�l�ȁA
�����Ă͂邩�ɓ���p�Y�����l�Ă������I���n���g�E�I�C���[�̍v���ɂ����̂ɈႢ�Ȃ��B�������Ȃ���A
"����"�p�Y���̖ړI�͂��ꂼ��̍s�A��3�~3�̘g���܂�9�~9�̊i�q�̋󔒁i��������0�j�����ꂼ��1����9�̐����Œu�������邱�Ƃł���A
����, ��ʓI�ȃp�Y���̊J�n��ԂƂ��̉𓚂̗Ⴊ����B

003 | 020 | 600             483 | 921 | 657
900 | 305 | 001             967 | 345 | 821
001 | 806 | 400             251 | 876 | 493
008 | 102 | 900             548 | 132 | 976
700 | 000 | 008             729 | 564 | 138
006 | 708 | 200             136 | 798 | 245
002 | 609 | 500             372 | 689 | 514
800 | 203 | 009             814 | 253 | 769
005 | 010 | 300             695 | 417 | 382


���܂�����Ă���"����"�p�Y���́A�I�������������邽�߂�"����ƃe�X�g"������p����K�v�����邩������Ȃ����A
������̉�������, �_���ɂ���ĉ������Ƃ��ł���(����ɂ��Ă͗l�X�Ȉӌ�������)
�T���̕��G�����p�Y���̓�Փx�����肷��;��ɋ�������́A�P���Œ��ړI�Ȑ��_�ɂ���ĉ��������ł��邽�߁A�ȒP�ł���ƍl������B
6k�o�C�g�̃e�L�X�g�t�@�C���ɂ͂�����̉������A�l�X�ȓ�Փx��50��"����"�p�Y�����܂܂�Ă���(��̗��͂��̃t�@�C���ɂ�����ŏ��̃p�Y���ł���)
50���ׂẴp�Y���������A���ꂼ��̉𓚂̍�����ɂ���3���̐��̍��v�����߂�; �Ⴆ��483�͏�̉𓚗�̍������3���̐��ł���B

�y���������@�z
0����������Afor���Ŋe�}�X��1-9�����Ă݂�B
for���̒��ɂ͍ċA�֐����g�p���A�s�A��A3�~3�u���b�N�ɏd�������������̂�����������U�����Ƃ݂Ȃ��i�[����B
�s�A��A3�~3�u���b�N�ɏd������������S���̂����Ɛ������c��Ȃ���΁A�O�ɖ��߂��������Ԉ���Ă���̂ŁA�o�b�N�g���b�N��0�ɖ߂��A
��蒼���B����𓚂����S�������܂łɌJ��Ԃ��B

�����F24702
���s���ԁF��2s

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class P096 {
    //�����i�[����Fpuzzle[���ԍ�0-49][��][�s]
    public static int[][][] puzzle = new int[50][9][9];
    //���ꂼ��̖��̐擪3�����v���i�[����Fsum[0-49]
    public static int[] sum = new int[50];

    public static void main(String[] args) {
        //�t�@�C���ǂݍ���
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
        int ans = 0;//����
        for (int i = 0; i < 50; i++) {
            solve(i, 0, 0);//solve�֐����Ăяo��
            ans += sum[i];//sum�̍��v�������ɂȂ�
        }
        System.out.println(ans);//24702

    }

    public static void solve(int n, int i, int k) {
        //�Ō�̍s�̍Ō�̒l���i8�s�ځA9��ځj�A���̍s�ɍs���ꍇ�A���������I��������Ƃ��Ӗ�����
        if (i == 8 && k == 9) {
            //�������̂ŁA������sum�ɑ������
            sum[n] = puzzle[n][0][0] * 100 + puzzle[n][0][1] * 10 + puzzle[n][0][2];
            return;
        }
        //�ŏI��ł���΁A�s��+1�A�񐔂�0
        if (k == 9) {
            i++;
            k = 0;
        }
        //�e�}�X�ɂ��Č������A0�ł���΍ׂ������������
        if (puzzle[n][i][k] == 0) {
            for (int m = 1; m <= 9; m++) {
                if (judge(n, i, k, m)) {
                    puzzle[n][i][k] = m;
                    solve(n, i, k + 1);
                    //�������̃}�X1-9�̐��������߂�������A�O�̃}�X�̐�����0�ɖ߂��i�o�b�N�g���b�N�j
                    puzzle[n][i][k] = 0;
                }
            }
        } else {
            //���̃}�X��0�ȊO�̐���������΁A��+1�Ŏ��̃}�X�̔���ɓ���
            solve(n, i, k + 1);
        }

    }

    //�����͓���邩�ǂ����𔻒肷�郁�\�b�h
    public static boolean judge(int n, int x, int y, int v) {
        for (int i = 0; i < 9; i++) {
            //�����s�A������ɏd�����������͂��邩
            if (puzzle[n][x][i] == v || puzzle[n][i][y] == v) {
                return false;
            }
        }
        //3�~3�u���b�N�ɏd�����������͂��邩
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