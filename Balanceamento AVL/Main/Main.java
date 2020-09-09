package Main;
import Arvore.ArvoreAVL;
import Exceptions.ItemNaoEncontradoException;
import entidades.Aluno;

public class Main {
    //criaçao da arvore
    private ArvoreAVL<Aluno> arvore = new ArvoreAVL<>();
    //criaçao de array de alunos
    static Aluno[] alunos = {
        new Aluno("Catapóbio",  "00000000000", 22, "000000", 600.00f, 0.00f),
        new Aluno("Saponácio",  "11111111111", 23, "111111", 300.00f, 0.00f),
        new Aluno("Fulustreco", "22222222222", 20, "222222", 1500.00f, 10.00f),
        new Aluno("Coníglio",   "33333333333", 17, "333333", 4400.00f, 30.00f),
        new Aluno("Austin",     "44444444444", 22, "444444", 3000.00f, 30.00f),
        new Aluno("Antonio",    "55555555555", 22, "555555", 400.00f, 0.00f),
        new Aluno("Tj",         "66666666666", 40, "666666", 545.00f, 0.00f),
        new Aluno("Márcio",     "77777777777", 12, "777777", 560.00f, 10.00f),
        new Aluno("Ana",        "88888888888", 20, "888888", 800.00f, 15.00f),
        new Aluno("Maria",      "99999999999", 15, "999999", 1500.00f, 100.00f),
    
    };
    //funçao para imprimir a arvore em 3 ordens
    public void print(){
        System.out.println("in");
        arvore.inOrder();
        System.out.println("pre");
        arvore.preOrder();
        System.out.println("pos");
        arvore.posOrder();
    }
    //teste do caso LL, seguido de teste de remoçao
    public void testLL(){
        arvore.clear();
        System.out.println("Test LL");
        arvore.add(alunos[9]);
        arvore.add(alunos[8]);
        arvore.add(alunos[7]);
        arvore.add(alunos[6]);
        arvore.add(alunos[5]);
        arvore.add(alunos[4]);
        arvore.add(alunos[3]);
        arvore.add(alunos[2]);
        arvore.add(alunos[1]);
        arvore.add(alunos[0]);
        print();
        System.out.println("Remoção em cima do LL");
        try{
            arvore.removeRec(alunos[1]);
            arvore.removeRec(alunos[0]);
            arvore.removeRec(alunos[5]);
            arvore.removeRec(alunos[6]);
        }catch(ItemNaoEncontradoException ex){
            System.out.println("ItemNaoEncontradoException");
        }
        print();
    }
    //teste caso RR
    public void testRR(){
        arvore.clear();
        System.out.println("Test RR");
        arvore.add(alunos[0]);
        arvore.add(alunos[1]);
        arvore.add(alunos[2]);
        arvore.add(alunos[3]);
        arvore.add(alunos[4]);
        arvore.add(alunos[5]);
        arvore.add(alunos[6]);
        arvore.add(alunos[7]);
        arvore.add(alunos[8]);
        arvore.add(alunos[9]);
        print();
    }
    //teste caso RL
    public void testRL(){
        arvore.clear();
        System.out.println("Test RL");
        arvore.add(alunos[0]);
        arvore.add(alunos[5]);
        arvore.add(alunos[1]);
        arvore.add(alunos[7]);
        arvore.add(alunos[6]);
        arvore.add(alunos[4]);
        print();
    }
    //teste caso LR
    public void testLR(){
        arvore.clear();
        System.out.println("Test LR");
        arvore.add(alunos[9]);
        arvore.add(alunos[6]);
        arvore.add(alunos[5]);
        arvore.add(alunos[3]);
        arvore.add(alunos[4]);
        arvore.add(alunos[7]);
        arvore.add(alunos[8]);
        print();
    }
    public static void main(String[] args){
        Main n = new Main();
        n.testLL();
        n.testRR();
        n.testLR();
        n.testRL();
        /*ArvoreAVL<Aluno> arvore = new ArvoreAVL<>();
        
        /*arvore.add(alunos[5]);
        arvore.add(alunos[2]);
        arvore.add(alunos[7]);
        arvore.add(alunos[1]);
        arvore.add(alunos[8]);
        arvore.add(alunos[3]);
        arvore.add(alunos[6]);
        arvore.add(alunos[0]);
        arvore.add(alunos[9]);
        arvore.add(alunos[4]);*/
        
        
        /*arvore.add(alunos[6]);
        arvore.add(alunos[8]);
        arvore.add(alunos[7]);
        arvore.add(alunos[9]);
        arvore.add(alunos[4]);
        arvore.add(alunos[5]);
        arvore.add(alunos[2]);
        arvore.add(alunos[3]);
        arvore.add(alunos[1]);
        arvore.add(alunos[0]);

        arvore.inOrder();
        arvore.preOrder();
        arvore.posOrder();*/
        
    }
}