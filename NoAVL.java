package No;

public class NoAVL<T extends Comparable> {
    private T obj;
    private NoAVL<T> left, right;
    private int balance;

    public NoAVL(T obj){
        this.obj = obj;
        this.left = null;
        this.right = null;
        this.balance = 0;
    }

    public T getObj(){
        return this.obj;
    }

    public void setObj(T obj){
        this.obj = obj;
    }

    public NoAVL<T> getLeft(){
        return this.left;
    }

    public void setLeft(NoAVL<T> left){
        this.left = left;
    }

    public NoAVL<T> getRight(){
        return this.right;
    }

    public void setRight(NoAVL<T> right){
        this.right = right;
    }

    public void addRight(){
        this.balance++;
    }

    public void addLeft(){
        this.balance--;
    }

    public int getBalance(){
        return this.balance;
    }

    public void setBalance(int n){
        this.balance = n;
    }

    public int compareTo(T obj){
        return this.obj.compareTo(obj);
    }
}