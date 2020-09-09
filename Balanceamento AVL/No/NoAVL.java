package No;

public class NoAVL<T extends Comparable> {
    //objeto armazenado no nó
    private T obj;
    //referencias esqueda e direita do nó(filhos)
    private NoAVL<T> left, right;
    //valor do fator de balanço
    private int balance;

    public NoAVL(T obj){
        this.obj = obj;
        this.left = null;
        this.right = null;
        this.balance = 0;
    }
    //retorna objeto armazenado no nó
    public T getObj(){
        return this.obj;
    }
    //define objeto armazenado no nó
    public void setObj(T obj){
        this.obj = obj;
    }
    //retorna o filho a esquerda
    public NoAVL<T> getLeft(){
        return this.left;
    }
    //define o filho a esquerda
    public void setLeft(NoAVL<T> left){
        this.left = left;
    }
    //retorna filho a direita
    public NoAVL<T> getRight(){
        return this.right;
    }
    //define filho a direita
    public void setRight(NoAVL<T> right){
        this.right = right;
    }
    //adiciona uma unidade ao fator de balanceamento
    public void addRight(){
        this.balance++;
    }
    //diminui uma unidade do fator de balanceamento
    public void addLeft(){
        this.balance--;
    }
    //retorna o fator de balanceamento
    public int getBalance(){
        return this.balance;
    }
    //define o fator de balanceamento como a diferença da altura do filho a direita e do filho a esquerda
    public void setBalance(){
        this.balance = getHeight(this.right)-getHeight(this.left);
    }
    //funçao recursiva para obter a altura de um nó
    private int getHeight(NoAVL<T> current){
        if(current != null){
            int left, right;
            if(current.getLeft() != null){
                left = getHeight(current.getLeft())+1;
            }else{
                left = 0;
            }
            if(current.getRight() != null){
                right = getHeight(current.getRight())+1; 
            }else{
                right = 0;
            }
            if(left > right){
                return left;
            }else{
                return right;
            }
        }else{
            return -1;
        }
    }
    //comparação do objeto armazenado no nó com outro objeto, nesta ordem
    public int compareTo(T obj){
        return this.obj.compareTo(obj);
    }
}