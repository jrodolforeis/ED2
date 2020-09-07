package Arvore;

import No.NoAVL;

public class ArvoreAVL<T extends Comparable<T>> {
    private NoAVL<T> root;

    public void add(T obj){
        if(this.root == null){
            this.root = new NoAVL<>(obj);
        }else{
            add(obj, this.root);
        }
    }

    private void add(T obj, NoAVL<T> current){
        if(obj.compareTo(current.getObj()) < 0){
            current.addLeft();
            if(current.getLeft() == null){
                current.setLeft(new NoAVL<T>(obj));
            }else{
                add(obj, current.getLeft());
            }
        }else{
            current.addRight();
            if(current.getRight() == null){
                current.setRight(new NoAVL<T>(obj));
            }else{
                add(obj, current.getRight());
            }
        }
        if(current.getRight() != null && (current.getRight().getBalance() < -1 || current.getRight().getBalance() > 1)){
            rotate(current, current.getRight());
        }else if(current.getLeft() != null && (current.getLeft().getBalance() < -1 || current.getLeft().getBalance() > 1)){
            rotate(current, current.getLeft());
        }else if(current == root && (current.getBalance() < -1 || current.getBalance() > 1)){
            rotate(null, current);
        }
    }

    private void rotate(NoAVL<T> parent, NoAVL<T> child){
        System.out.println("Target = "+child.getObj()+", Balance = "+child.getBalance());
        if(child.getBalance() > 1){
            if(child.getRight() != null && child.getRight().getBalance() > 0){
                RR(parent, child);
            }else if(child.getRight() != null && child.getRight().getBalance() < 0){
                RL(parent, child);
            }
        }else{
            if(child.getLeft() != null && child.getLeft().getBalance() > 0){
                LR(parent, child);
            }else if(child.getLeft() != null && child.getLeft().getBalance() < 0){
                LL(parent, child);
            }
        }
        if(parent != null){
            balanceCount(parent, child, this.root);
        }
    }

    private void balanceCount(NoAVL<T> parent, NoAVL<T> child, NoAVL<T> current){
        if(parent != current){
            if(parent.compareTo(current.getObj()) > 0){
                current.addLeft();
                balanceCount(parent, child, current.getRight());
            }else{
                current.addRight();
                balanceCount(parent, child, current.getLeft());
            }
        }else{
            if(child.compareTo(parent.getObj()) > 0){
                parent.addLeft();
            }else{
                parent.addRight();
            }
        }
    }

    private void resetBalance(NoAVL<T> x, NoAVL<T> y){
        x.setBalance(0);
        y.setBalance(0);
    }

    private void RR(NoAVL<T> parent, NoAVL<T> child){
        resetBalance(child, child.getRight());
        NoAVL<T> temp = child.getRight().getLeft();
        child.getRight().setLeft(child);
        if(parent == null){
            this.root = child.getRight();
        }else{
            if(parent.getRight() == child){
                parent.setRight(child.getRight());
            }else{
                parent.setLeft(child.getRight());
            }
        }
        child.setRight(temp);
    }
    
    private void RL(NoAVL<T> parent, NoAVL<T> child){
        resetBalance(child, child.getRight());
        NoAVL<T> temp;
        if(child.getRight().getLeft().getLeft() != null){
            temp = child.getRight().getLeft().getLeft();
        }else{
            temp = child.getRight().getLeft().getRight();
        }
        child.getRight().getLeft().setLeft(child);
        child.getRight().getLeft().setRight(child.getRight());
        if(parent == null){
            this.root = child.getRight().getLeft();
        }else{
            if(parent.getRight() == child){
                parent.setRight(child.getRight().getLeft());
            }else{
                parent.setLeft(child.getRight().getLeft());
            }
        }
        child.getRight().setLeft(temp);
        child.setRight(null);
    }
    
    private void LR(NoAVL<T> parent, NoAVL<T> child){
        resetBalance(child, child.getLeft());
        NoAVL<T> temp;
        if(child.getLeft().getRight().getLeft() != null){
            temp = child.getLeft().getRight().getLeft();
        }else{
            temp = child.getLeft().getRight().getRight();
        }
        child.getLeft().getRight().setRight(child);
        child.getLeft().getRight().setLeft(child.getLeft());
        if(parent == null){
            this.root = child.getLeft().getRight();
        }else{
            if(parent.getRight() == child){
                parent.setRight(child.getLeft().getRight());
            }else{
                parent.setLeft(child.getLeft().getRight());
            }
        }
        child.getLeft().setRight(temp);
        child.setLeft(null);
    }
    
    private void LL(NoAVL<T> parent, NoAVL<T> child){
        resetBalance(child, child.getLeft());
        NoAVL<T> temp = child.getLeft().getRight();
        child.getLeft().setRight(child);
        if(parent == null){
            this.root = child.getLeft();
        }else{
            if(parent.getRight() == child){
                parent.setRight(child.getLeft());
            }else{
                parent.setLeft(child.getLeft());
            }
        }
        child.setLeft(temp);
    }

    public void inOrder(){
        if(this.root != null){
            inOrder(this.root);
        }else{
            System.out.println("Arvore Vazia");
        }
        System.out.println();
    }

    private void inOrder(NoAVL<T> current){
        if(current.getLeft() != null){
            inOrder(current.getLeft());
        }
        System.out.println(current.getObj()+","+current.getBalance());
        if(current.getRight() != null){
            inOrder(current.getRight());
        }
    }

    public void preOrder(){
        if(this.root != null){
            preOrder(this.root);
        }else{
            System.out.println("Arvore Vazia");
        }
        System.out.println();
    }

    private void preOrder(NoAVL<T> current){
        System.out.println(current.getObj()+","+current.getBalance());
        if(current.getLeft() != null){
            preOrder(current.getLeft());
        }
        if(current.getRight() != null){
            preOrder(current.getRight());
        }
    }

    public void posOrder(){
        if(this.root != null){
            posOrder(this.root);
        }else{
            System.out.println("Arvore Vazia");
        }
        System.out.println();
    }

    private void posOrder(NoAVL<T> current){
        if(current.getLeft() != null){
            posOrder(current.getLeft());
        }
        if(current.getRight() != null){
            posOrder(current.getRight());
        }
        System.out.println(current.getObj()+","+current.getBalance());
    }
}