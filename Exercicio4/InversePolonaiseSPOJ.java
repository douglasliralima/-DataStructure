package Exercicio4;

import java.util.Scanner;

/**
 *
 * @author Douglas
 */
class InversePolonaiseSPOJ {

//Aqui temos a nossa classe No sem tirar nem por daquilo que sabemos
class No<t>{
    private t conteudo;
    private No prox;
    
    public No(t conteudo){
        prox = null;  //O próximo inicialmente não estará conectado a nada
        this.conteudo = conteudo;
    }
    
    public void setConteudo(t conteudo){
        this.conteudo = conteudo;
    }
    
    public t getConteudo(){
        return conteudo;
    }
    
    public No getProx(){
        return prox;
    }
    
    public void setProx(No prox){
        this.prox = prox;
    }
}

public class Pilha<abstrato>{
    private int nElementos;
    private No topo;
    
    public Pilha(){
        nElementos = 0;
        topo = null;
    }
    //Primeiro precisamos ver quando a pilha está vazia
    public boolean pilhaVazia(){
       if(nElementos == 0)return true;
       else return false;
    }
    

    public void adicionaPilha(abstrato valor) {
        No aux = new No(valor); //É criada a nova caixinha
        /*
        Nova caixinha passa a apontar ao que o topo apontava
        (Se for o primeiro elemento o auxiliar apenas setara o proximo como null)
        */
        aux.setProx(topo);
        topo = aux;
        nElementos++;
    }
    
    /*
    Aqui vamos remover da pilha, para isso a cabeça vai ter que 
    se tornar o próximo endereço e o que estava sendo guardado 
    na antiga caixinha será retornado.
    */
    public abstrato removePilha() throws PilhaVaziaException{
          if(nElementos == 0){
              throw new PilhaVaziaException();
          }
          else{
              No aux = topo;
              topo = topo.getProx();
              nElementos--;
              return (abstrato)aux.getConteudo();
          }
    }
    
    public abstrato top() throws PilhaVaziaException{
        if(nElementos == 0) throw new PilhaVaziaException(); //Caso a pilha esteja vazia
        else return (abstrato) topo.getConteudo(); //Aqui basta retornar o valor que está no topo
    }
    
    public int tamanho(){
        return nElementos;
    }
}
class PilhaVaziaException extends Exception{
    public void detail(){
        System.out.println("Lista se encontra vazia");
    }
}

    public static void main(String[] args)  throws Exercicio4.PilhaVaziaException {
     
        Scanner leitor = new Scanner(System.in);
        String expressao;
        int expressoes;

        //Aqui nós usamos o Scanner para definir quantas expressões teremos
        expressoes = Integer.parseInt(leitor.nextLine());

        //Para cada expressao especificada, a seguinte expressao será chamada
        for (int i = 0; expressoes > i; i++) {
            expressao = leitor.nextLine();
            /*
            Para cada expressão, uma nova pilha será criada que servirá de
            auxiliar na formatação da string para o formato desejado
             */
            Exercicio4.Pilha pilha = new Exercicio4.Pilha();
            String editado = ""; //String que guardará nosso valor formatado no formato polones inverso
            char aux;
             
            for (int j = 0; j < expressao.length(); j++) { //Vamos correr a expressão

                /*   
                O método chatAt, da classe string, nos permitirá ir
                coletando cada caracter para fazer o julgamento lógico, como estamos trabalhando
                com dois algarismos dentro dos parentêses o'(' não importará muito em nossa edição,
                como no resultado  public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);
        Stringfinal ele quer a expressão sem os parenteses, simplesmente guardamos 
                os algarismos, quando achamos uma expressão, guardamos em uma pilha e seguindo a lógica aritmética e
                por ser o inverso da polonesa, sempre que for encontrado um ')' é aonde estaria o resultado da nossa 
                expressão
                 */
                aux = expressao.charAt(j);
                switch (aux) {
                    case '(':
                        break;

                    case '+':
                        pilha.adicionaPilha(aux);
                        break;

                    case '-':
                        pilha.adicionaPilha(aux);
                        break;

                    case '*':
                        pilha.adicionaPilha(aux);
                        break;

                    case '/':
                        pilha.adicionaPilha(aux);
                        break;

                    case '^':
                        pilha.adicionaPilha(aux);
                        break;

                    case ')':
                        /*
                        Pelos propósitos da pilha que eu dimencionei, 
                        deveria ter um try..catch aqui, mas como o exercicio
                        no spoj não liga para essas exceções, apenas joguei o throws no main
                        */
                        editado += pilha.removePilha();
                        break;

                    default:
                        editado += "".concat(String.valueOf(aux));
                        break;
                }
            }
            System.out.println(editado);
        }
    }
}