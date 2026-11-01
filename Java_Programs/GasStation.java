import java.util.*;
public class GasStation {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] gas=new int[n];
        for(int i=0;i<n;i++){
            gas[i]=sc.nextInt();
        }
        int[] cost=new int[n];
        for(int i=0;i<n;i++){
            cost[i]=sc.nextInt();
        }
        int totalGas=0;
        int totalCost=0;
        for(int i=0;i<n;i++){
            totalGas+=gas[i];
            totalCost+=cost[i];
        }
        if(totalGas<totalCost){
            System.out.println(-1);
        }
        else{
            int currentGas=0;
            int start=0;
            for(int i=0;i<n;i++){
                currentGas+=gas[i]-cost[i];
                if(currentGas<0){
                    start=i+1;
                    currentGas=0;
                }
            }
            System.out.println(start);
        }
    }
    
}
