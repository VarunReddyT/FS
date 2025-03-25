class Fenwick{
    int[] bit;
    int n;
    Fenwick(int size){
        this.n = size;
        this.bit = new int[n+1];
    }
    public void update(int i, int val){
        while(i<=n){
            bit[i] += val;
            System.out.println("i & -i : " + (i & -i));
            i += (i & -i);
        }
    }
    public int query(int i){
        int sum = 0;
        while(i > 0){
            sum += bit[i];
            i -= (i & -i);
        }
        return sum;
    }
    public int rangeSum(int left, int right){
        return query(right) - query(left-1);
    }
}
public class FenwickTree {
    public static void main(String[] args) {
        Fenwick ft = new Fenwick(10);
        ft.update(1, 3);
        ft.update(2, 2);
        ft.update(3, 5);
        ft.update(4, 1);
        ft.update(5, 9);
        ft.update(6, 4);
        ft.update(7, 6);
        System.out.println("Sum from 1 to 3 : " + ft.query(3));
        System.out.println("Sum from 2 to 3 : " + ft.rangeSum(2,3));
    }
}
