import java.util.*;

public class EncodeBinaryCode2{
    public static String getBinary(int n){
        String s = "";
        while(n>0){
            if(n%2 == 0){
                s = '1' + s;
            }
            else{
                s = '0' + s;
            }
            n = (n-1)/2;
        }
        return s;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(getBinary(n));
        sc.close();
    }
}