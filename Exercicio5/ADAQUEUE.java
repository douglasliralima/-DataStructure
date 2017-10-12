package Exercicio5;
import java.util.*;

/**
 *
 * @author Douglas
 */
class No<generico>{
    generico conteudo;
    No prox;
    No ant;
    
    public generico getConteudo() {
        return conteudo;
    }

    public void setConteudo(generico conteudo) {
        this.conteudo = conteudo;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }
    
    public No getAnt() {
        return ant;
    }

    public void setAnt(No ant) {
        this.ant = ant;
    }
}
 class PilhaFila<generico> {
    private No cabeca;
    private No calda;
    private int nElementos;
    
    public PilhaFila(){
    cabeca = null;
    calda = null;
    nElementos = 0;
    }
    
    /*
    Bom, a primeira coisa será um método que adiciona no cabeca
    */
    public boolean vazia() {
        if (nElementos == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int tamanho() {
        return nElementos;
    }
    
    /*
    Por algum motivo ainda não está conectado
    Aqui estamos pegando se um elemento está vazio, tanto o cabeca quanto o calda
    vão apontar para ele e ele apontará para ambos. Caso já haja algum elemento
    o que for adicionado no cabeca terá como próximo o cabeca e como anterior aquele que já
    havia sido previamente adicionado
    */
    public void AddInicio(generico elemento){
        No aux = new No();
        if(vazia()){
            aux.setConteudo(elemento);
            cabeca = aux;
            calda = aux;
            aux.setProx(cabeca);
            aux.setAnt(calda);
            nElementos++;
        }
        else{
            aux.setConteudo(elemento);
            aux.setAnt(cabeca); //Primeiro apontamos onde a cabeça aponta
            cabeca.setProx(aux); //Agora o elemento que a cabeça aponta, recebe o nosso novo no, como depois dele
            cabeca = aux; //Agora sim. o cabeca pode começar a apontar para aux
            nElementos++;
        }
    }
    
    /*
    Aqui estamos pegando se um elemento está vazio, tanto o cabeca quanto o calda
    vão apontar para ele e ele apontará para ambos. Caso já haja algum elemento
    o que for adicionado no calda terá como próximo o calda e como anterior aquele
    que já havia sido previamente adicionado
    */
    public void addFim(generico elemento){
        No aux = new No();
        if(vazia()){
            aux.setConteudo(elemento);
            cabeca = aux;
            calda = aux;
            aux.setProx(cabeca);
            aux.setAnt(calda);
            nElementos++;
        }
        else{
            aux.setConteudo(elemento);
            aux.setProx(calda); //Primeiro o anterior começa a apontar para a ligação presente no calda
            calda.setAnt(aux); //Agora o que estava sendo apontado pelo calda, começa a apontar para o novo no
            calda = aux; //Finalmente o calda começa a apontar para o novo no
            nElementos++;
        }
    }
    
    public generico removeInicio(){
        if(vazia()){
            return null;
        }
        else{
            No aux = cabeca; //Aux recebe a cabeça
            //A cabeça vai receber o anterior ao elemento corrente, e ele será deixdo ao lixeiro
            cabeca = cabeca.getAnt();
            nElementos--;
            return (generico) aux.getConteudo();
        }
    }
    
    public generico removeFim(){
        if(vazia()){
            return null;
        }
        else{
            No aux = calda;
            calda = calda.getProx(); //O proximo elemento é o que começará a dar valor a calda
            nElementos--;
            return (generico) aux.getConteudo();
        }
    }
}

public class ADAQUEUE {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        /*
        Basicamente temos uma fusão entre a pilha e fila
        aqui, onde nos adicionamos na frente ou atrás e 
        quando pedimos esse elemento, ele apaga assim como
        em uma pilha ou fila, só que ele continua
        */
        PilhaFila pilhafila = new PilhaFila();
        String comando;
        //Vamos primeiro pegar o número de tarefas
        int tarefas;
        tarefas = Integer.parseInt(leitor.nextLine());
        boolean revert = false;
        /*
        Agora vamos fazer o comando que vai fazer em todas as linhas especificadas
        */
        for(int i = 0; i < tarefas; i++){
            comando = leitor.nextLine();
            String palavras[] = comando.split(" ");
            /*
            Basicamente nesse grande switch, baseado no comando que o usuário passar
            ele vai executar a sequência lógica que executa o especificado no site do
            spoj
            */
            switch(palavras[0]){
                case "toFront":
                    if(revert){
                        //Caso a pilhafila tenha sido invertida, precisa realizar a operação inversa
                        pilhafila.addFim(palavras[1]);
                        break;
                    }
                        pilhafila.AddInicio(palavras[1]);
                    break;
                case "push_back":
                    if(revert){
                        //Caso a pilhafila esteja invertida, o push_back adicionaria no inicio
                        pilhafila.AddInicio(palavras[1]); 
                        break;
                    }
                    pilhafila.addFim(palavras[1]);
                    break;
                case "front":
                    if(pilhafila.vazia()){
                        System.out.println("No job for Ada?");
                        break;
                    }
                    else if(revert){
                    //Caso a pilhafila tenha sido invertida, precisa realizar a operação inversa
                    System.out.println(pilhafila.removeFim()); //Assim apenas o elemento vai revertido
                    break;
                    }
                    else{
                    System.out.println(pilhafila.removeInicio());
                    break;
                    }
                case "back":
                    if(pilhafila.vazia()){
                        System.out.println("No job for Ada?");
                        break;
                    }
                    else if(revert){
                    //Caso a pilhafila tenha sido invertida, precisa realizar a operação inversa
                    System.out.println(pilhafila.removeInicio());
                    break;
                    }
                    else{
                    System.out.println(pilhafila.removeFim());
                    break;
                    }
                case "remove":
                    System.out.println("Removido: " + (String) pilhafila.removeInicio());
                    break;
                case "reverse":
                    revert = !revert;
                    break;
            }
        }
    }
}
