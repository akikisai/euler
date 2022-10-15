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

y–óz
5‚Í”‚Ì˜a‚Æ‚µ‚Ä6’Ê‚è‚É‘‚­‚±‚Æ‚ª‚Å‚«‚é:

4 + 1
3 + 2
3 + 1 + 1
2 + 2 + 1
2 + 1 + 1 + 1
1 + 1 + 1 + 1 + 1

2‚ÂˆÈã‚Ì³®”‚Ì˜a‚Æ‚µ‚Ä‚Ì100‚Ì•\‚µ•û‚Í‰½’Ê‚è‚©B

y–â‘è‰ğŒˆ•û–@z
‘Q‰»®‚Å‰ğ‚­‚â‚è•ûB
®”n‚ğ‡˜‚ğ‹æ•Ê‚¹‚¸‚ÉumˆÈ‰º‚Ì©‘R”v‚Ì˜a‚É•ª‚¯‚éê‡‚Ì”

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