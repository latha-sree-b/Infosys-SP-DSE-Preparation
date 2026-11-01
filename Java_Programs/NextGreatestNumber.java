import java.util.*;
public class NextGreatestNumber{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        char[] digits=sc.next().toCharArray();
        int i=digits.length-2;
        while(i>0 && digits[i]>=digits[i+1]){
            i--;
        }
        if(i<0){
            System.out.println(-1);
        }
        int j=digits.length-1;
        while(digits[j]<=digits[i]){
            j--;
        }
        char temp=digits[i];
        digits[i]=digits[j];
        digits[j]=temp;
        Arrays.sort(digits, i+1, digits.length);
        for(char c:digits){
            System.out.print(c);
        }

    }
}