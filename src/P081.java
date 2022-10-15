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

�y��z
P081:�o�H�̘a:2����
���L��5���̐����s���, ����̃Z������J�n���E���̃Z���ŏI���p�X��T������B�������������ƉE�����ɂ݈̂ړ��ł�����̂Ƃ���B
�ʉ߂����Z���̘a���ŏ��ƂȂ�p�X�́y131��201��96��342��746��422��121��37��331�z��, ���̍��v��2427�ł���B

131 673	234	103	18
201	96	342	965	150
630	803	746	422	111
537	699	497	121	956
805	732	524	37	331

��, 31K�̃e�L�X�g�t�@�C��matrix.txt�ɂ�80�~80�̍s�񂪏�����Ă��āA���l�ɍ���̃Z������J�n���E���̃Z���ŏI���,
���E�����Ɖ������ɂ݈̂ړ�����Ƃ��̍ŏ��̃p�X�̘a�����߂�B

�y���������@�z

131 673	234	103	18
201	96	342	965	150
630	803	746	422	111
537	699	497	121	956
805	732	524	37	331

�����i�[�����񎟌��z���p�ӂ���B
1�s�ځA1��ڂ��ꂼ��v�Z�������l���i�[����B
���ォ��E���܂�1�s���v�Z���Ă����A��r���Ȃ���ŏ��l���i�[����B


���킩��₷�������ƁA�ό`����ƈȉ��̌`�ɂȂ�A�����P067�ő�o�H�̘a�Ǝ����悤�Ȗ��ŁA�����l�����ŉ�����B

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

        //100�s�A100��̃f�[�^���i�[����z��
        int[][] data = new int[80][80];
        //�t�@�C���ǂݍ���
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

        //1�s��,1��� (��r����K�v�Ȃ�)
        for (int i = 1; i < 80; i++) {
            data[0][i] += data[0][i - 1];
            data[i][0] += data[i - 1][0];
        }
        //2�s��,2��ڈȍ~ (��r����K�v����)
        for (int i = 1; i < 80; i++) {
            for (int k = 1; k < 80; k++) {
                data[i][k] += Math.min(data[i - 1][k], data[i][k - 1]);
            }
        }

        System.out.println(data[79][79]);

    }
}