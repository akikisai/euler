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

�y��z
5�͐��̘a�Ƃ���6�ʂ�ɏ������Ƃ��ł���:

4 + 1
3 + 2
3 + 1 + 1
2 + 2 + 1
2 + 1 + 1 + 1
1 + 1 + 1 + 1 + 1

2�ȏ�̐������̘a�Ƃ��Ă�100�̕\�����͉��ʂ肩�B

�y���������@�z
�Q�����ŉ��������B
����n����������ʂ����Ɂum�ȉ��̎��R���v�̘a�ɕ�����ꍇ�̐�

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