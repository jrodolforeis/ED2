package Arvore;

import No.NoAVL;
import Exceptions.ItemNaoEncontradoException;

public class ArvoreAVL<T extends Comparable<T>> {
    private NoAVL<T> root;
    //apaga a referencia basica(raiz) da arvore
    public void clear(){
        this.root = null;
    }
    //inicia o processo de inserçao do objeto - insere na raiz caso a arvore esteja vazia ou chama a função recursiva
    public void add(T obj){
        if(this.root == null){
            this.root = new NoAVL<>(obj);
        }else{
            add(obj, this.root);
        }
    }
    //função recursiva que navega até uma folha para adicionar um item
    //manipula o balanceamento dos nós que percorre ao adicionar e pode chamar uma rotação para balancear a arvore
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
    //funçao que define o tipo de rotação a ser realizada
    private void rotate(NoAVL<T> parent, NoAVL<T> child){
        System.out.println("Target = "+child.getObj()+", Balance = "+child.getBalance());
        if(child.getBalance() > 1){
            if(child.getRight() != null && child.getRight().getBalance() >= 0){
                RR(parent, child);
            }else if(child.getRight() != null && child.getRight().getBalance() < 0){
                RL(parent, child);
            }
        }else{
            if(child.getLeft() != null && child.getLeft().getBalance() > 0){
                LR(parent, child);
            }else if(child.getLeft() != null && child.getLeft().getBalance() <= 0){
                LL(parent, child);
            }
        }
        if(parent != null){
            balanceFix(parent, child, this.root);
        }
    }
    //reduz ou aumenta em 1 o balanceamento dos nós superiores, após rotaçao.
    private void balanceFix(NoAVL<T> parent, NoAVL<T> child, NoAVL<T> current){
        if(parent != current){
            if(parent.compareTo(current.getObj()) > 0){
                current.addLeft();
                balanceFix(parent, child, current.getRight());
            }else{
                current.addRight();
                balanceFix(parent, child, current.getLeft());
            }
        }else{
            if(child.compareTo(parent.getObj()) > 0){
                parent.addLeft();
            }else{
                parent.addRight();
            }
        }
    }
    //rotação RR faz uma rotação para a esquerda e balanceia os nós envolvidos, ao final do procedimento
    private void RR(NoAVL<T> parent, NoAVL<T> child){
        NoAVL<T> temp = child.getRight().getLeft();
        NoAVL<T> aux = child.getRight();
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
        child.setBalance();
        aux.setBalance();
    }
    //rotaçao RL utiliza duas rotaçoes simples com diferentes parametros
    private void RL(NoAVL<T> parent, NoAVL<T> child){
        LL(child, child.getRight());
        RR(parent, child);
    }
    //rotaçao LR utiliza duas rotações simples com diferentes parametros
    private void LR(NoAVL<T> parent, NoAVL<T> child){
        RR(child, child.getLeft());
        LL(parent, child);
    }
    //rotaçao LL faz uma rotaçao para a direita e balanceia os nós envolvidos, ao final do procedimento
    private void LL(NoAVL<T> parent, NoAVL<T> child){
        NoAVL<T> temp = child.getLeft().getRight();
        NoAVL<T> aux = child.getLeft();
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
        child.setBalance();
        aux.setBalance();
    }
    //chamada da ordenação em ordem
    public void inOrder(){
        if(this.root != null){
            inOrder(this.root);
        }else{
            System.out.println("Arvore Vazia");
        }
        System.out.println();
    }
    //chamada recursiva do em ordem
    private void inOrder(NoAVL<T> current){
        if(current.getLeft() != null){
            inOrder(current.getLeft());
        }
        System.out.println(current.getObj()+","+current.getBalance());
        if(current.getRight() != null){
            inOrder(current.getRight());
        }
    }
    //chamada da ordenaçao pre ordem
    public void preOrder(){
        if(this.root != null){
            preOrder(this.root);
        }else{
            System.out.println("Arvore Vazia");
        }
        System.out.println();
    }
    //chamada recursiva da pre ordem
    private void preOrder(NoAVL<T> current){
        System.out.println(current.getObj()+","+current.getBalance());
        if(current.getLeft() != null){
            preOrder(current.getLeft());
        }
        if(current.getRight() != null){
            preOrder(current.getRight());
        }
    }
    //chamada da ordenaçao pos ordem
    public void posOrder(){
        if(this.root != null){
            posOrder(this.root);
        }else{
            System.out.println("Arvore Vazia");
        }
        System.out.println();
    }
    //chamada recursiva da pos ordem
    private void posOrder(NoAVL<T> current){
        if(current.getLeft() != null){
            posOrder(current.getLeft());
        }
        if(current.getRight() != null){
            posOrder(current.getRight());
        }
        System.out.println(current.getObj()+","+current.getBalance());
    }
    //função para obter o pai do maior nó dentre os menores, do nó que sera removido
    private NoAVL<T> maiorDad(NoAVL<T> current, NoAVL<T> dad){
        if(current.getRight() != null) return maiorDad(current.getRight(), current);
        else return dad;
    }
    //chamada da funçao remover
    public void removeRec(T obj) throws ItemNaoEncontradoException{
        removeRec(obj, root, null);
    }
    //chamada recursiva da funçao remover
    //também invoca a funçao de balanceamento
    private void removeRec(T obj, NoAVL<T> current, NoAVL<T> dad) throws ItemNaoEncontradoException{
        if(current == null) throw new ItemNaoEncontradoException();
        int i = current.compareTo(obj);
        if(i == 0){
            NoAVL<T> target;
            if(current.getLeft() == null){
                current = current.getRight();
                target = dad;
            }
            else if(current.getRight() == null){
                current = current.getLeft();
                target = dad;
            }
            else{
                NoAVL<T> maiorDad = maiorDad(current.getLeft(), null);
                if(maiorDad == null){
                    current.getLeft().setRight(current.getRight());
                    current = current.getLeft();
                    target = current;
                }else{
                    current.setObj(maiorDad.getRight().getObj());
                    maiorDad.setRight(maiorDad.getRight().getLeft());
                    target = maiorDad;
                }
            }
            if(dad == null) root = current;
            else if(dad.getLeft().getObj().equals(obj)) dad.setLeft(current);
            else dad.setRight(current);
            if(root != null){
                if(target == null) defineBalance(root, root);
                else defineBalance(target, root);
            }
        }else if(i < 0) removeRec(obj, current.getRight(), current);
        else removeRec(obj, current.getLeft(), current);
    }
    //funçao de balanceamento do remover, ajustando os indices e podendo provocar as rotações necessárias
    private void defineBalance(NoAVL<T> child, NoAVL<T> current){
        if(child == current) current.setBalance();
        else if(child.compareTo(current.getObj()) >= 0){
            defineBalance(child, current.getRight());
            current.setBalance();
        }else if(child.compareTo(current.getObj()) < 0){
            defineBalance(child, current.getLeft());
            current.setBalance();
        }
        if(current.getRight() != null && (current.getRight().getBalance() < -1 || current.getRight().getBalance() > 1)){
            rotate(current, current.getRight());
        }else if(current.getLeft() != null && (current.getLeft().getBalance() < -1 || current.getLeft().getBalance() > 1)){
            rotate(current, current.getLeft());
        }else if(current == root && (current.getBalance() < -1 || current.getBalance() > 1)){
            rotate(null, current);
        }
    }
}