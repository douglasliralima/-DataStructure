import java.util.*;
import java.lang.*;
class ListaDGen {
   
    /*
    Basicamente aquilo mostrado em sala de aula de modo muito
    mais simplificado, pois como queremos trabalhor comparando
    se os nós são iguais, não faz sentido ficar olhando os elementos
    ao invés dos próprios nós
    */
   
    private No inicio;
    private No fim;
    private int tamanho;
 
    public ListaDGen(){
    inicio = null;
    fim = null;
    tamanho = 0;
    }
   
 
    public int tamanho() {
        return tamanho;
    }
 
   
    No pegaNo(int pos) {
    if (pos > tamanho) {
            return null;
        }
 
    No n = inicio;
    for (int i = 0; i < pos; ++i) {
            n = n.getProximo();
    }
    return n;
    }
   
    //A nossa função insere também foi mudada para os moldes desse exercício
    void insere(No n) {
     int i = 0;
        if (tamanho != 0){
            No m = inicio;
            while(m.getProximo() != null){
               m = m.getProximo();
             }
               n.setProximo(null);
               m.setProximo(n);
               n.setAnterior(fim);
               fim.setProximo(n);
               fim = n;
               ++tamanho;
       
        }else{
           n.setProximo(inicio);
           n.setAnterior(null);
           fim = n;
           inicio = n;
           tamanho++;
        }  
       
    }
}
 
class No <abstrato>{
   
    private No ant;
    private No prox;
    private abstrato conteudo;
    /*
    Esses dois não estariam em uma lista normal, mas vão ajudar na logica
    dessa lista aqui
    */
    private abstrato proximo;
    private abstrato anterior;
 
    public No(){
        ant = null;
        proximo = null;
    }
 
    public No getAnterior(){
        return ant;
    }
 
    public void setAnterior(No a){
        ant = a;
    }
 
    public No getProximo(){
        return prox;
    }
 
    public void setProximo(No p){
        prox = p;
    }
 
    public abstrato getConteudo(){
        return conteudo;
    }
 
    public void setConteudo(abstrato c){
        conteudo = c;
    }
   
    //Como dito anteriomente, esses dois métodos serão especialmente úteis na situação atual
    public abstrato getConteudoAnt(){
        return anterior;
    }
    public void setConteudoAnt(abstrato a){
        anterior = a;
    }
    public abstrato getConteudoProx(){
        return proximo;
    }
    public void setConteudoProx(abstrato a){
        proximo = a;
    }
}

class SanidadeSpoj {

    public static void main(String[] args) {
        Exercicio3.ListaDGen lista = new Exercicio3.ListaDGen();
        Scanner leitura = new Scanner(System.in);
        Exercicio3.No ponteiro1 = new Exercicio3.No();
        Exercicio3.No ponteiro2 = new Exercicio3.No();
        String res1 = " "; //Como cada elemento é separado pelo espaço, isso vai nos auxiliar
        int espaco1, espaco2; //Essas variaveis nos dirão a onde estarão localizados os espaços
        int aux = 0; //Vai nos ajudar na hora da comparação entre os caminhos
        String anterior, conteudo, proximo; //Valores que serão usados no Ptr1
        String anterior2, conteudo2, proximo2; //Valores qe serão usados no Ptr2
        String auxstr1;

        /*
            Basicamente a logica que é usada para criar ptr1, é usada para criar ptr2
            e todos os outros elementos. Vamos analisar a string recebida na linha e separar
            o conteudo(primeiro do elemento), o ponteiro anterior(segundo) e posterior(terceiro
            através dos espaços que são especificados na entrada da lista.
         */
        auxstr1 = leitura.nextLine(); //Pegaremos aqui os valores que serão passados como parametro
        espaco1 = auxstr1.indexOf(res1); //Definição do conteúdo
        conteudo = auxstr1.substring(0, espaco1);//Vai basicamente pegar aqui que tem no inicio
        ponteiro1.setConteudo(conteudo); //Como esse programa trabalha com strings, podemos deixar elas assim mesmo
        espaco2 = auxstr1.lastIndexOf(res1);
        anterior = auxstr1.substring(espaco1 + 1, espaco2);
        /*
            Lembra daqueles dois métodos que eu falei que seriam úteis? Veja eles aqui
            basicamente com isso conseguimos fazer numa tacada só os valores do proximo e do anterior
            já que os recebemos de uma vez a cada linha de entrada, não é a coisa recomendada a se fazer
            em listas, mas preenche um baita tempo aqui
         */
        ponteiro1.setConteudoAnt(anterior);
        proximo = auxstr1.substring(espaco2 + 1, auxstr1.length());
        ponteiro1.setConteudoProx(proximo);
        lista.insere(ponteiro1);

        //Agora vamos executar a mesma lógica de cima, só que para ptr2
        auxstr1 = leitura.nextLine();
        espaco1 = auxstr1.indexOf(res1);
        conteudo2 = auxstr1.substring(0, espaco1);
        ponteiro2.setConteudo(conteudo2);
        espaco2 = auxstr1.lastIndexOf(res1);
        anterior2 = auxstr1.substring(espaco1 + 1, espaco2);
        ponteiro2.setConteudoAnt(anterior2);
        proximo2 = auxstr1.substring(espaco2 + 1, auxstr1.length());
        ponteiro2.setConteudoProx(proximo2);
        lista.insere(ponteiro2);

        /*
            Criados ptr1 e ptr2, vamos agora criar todo o resto dos elementos:
         */
        while (true) {
            auxstr1 = leitura.nextLine();
            if (auxstr1.isEmpty()) { //Quando o usuário não digitar mais nada é que acabou a lista
                break;
            }
            Exercicio3.No linha = new Exercicio3.No(); //Vamos montar o No que estaria em nossa linha
            //Aqui é a mesma lógica usada para ptr1 e 2...
            espaco1 = auxstr1.indexOf(res1);
            conteudo = auxstr1.substring(0, espaco1);
            linha.setConteudo(conteudo);
            espaco2 = auxstr1.lastIndexOf(res1);
            anterior = auxstr1.substring(espaco1 + 1, espaco2);
            linha.setConteudoAnt(anterior);
            proximo = auxstr1.substring(espaco2 + 1, auxstr1.length());
            linha.setConteudoProx(proximo);
            lista.insere(linha);
        }


        /*
            Agora sim vem a mágica do negócio, a comparação entre duas strings
            acho que eu só vou copiar e foda-se _l_
            Um for pode ser inicializado com qualquer parametro, não apenas inteiros
            contando que ele tenha uma condição de parada válida, isso será a chave
            para a construçao da nossa string que vai de ptr1 até ptr2 e a que faz 
            o caminho contrario
         */
        String ptr12; //Vai de ptr1 para ptr2
        String ptr21; //Vai de ptr2 para ptr1
        ptr12 = (String) ponteiro1.getConteudo();
        for (Exercicio3.No ptraux = ponteiro1; ptraux != ponteiro2; aux++) {
            for (int i = 0; i < lista.tamanho(); i++) {
                if (ptraux.getConteudoProx().equals((String) lista.getNo(i).getConteudo())) {
                    ptraux = lista.getNo(i);
                    ptr12 += (String) lista.getNo(i).getConteudo();
                    break;
                }
            }
            if (aux > lista.tamanho()) {
                break;
            }
        }

        aux = 0;
        ptr21 = (String) ponteiro2.getConteudo();
        for (Exercicio3.No ptraux = ponteiro2; ptraux != ponteiro1; aux++) {
            // encontra o proximo na lista
            for (int i = 0; i < lista.tamanho(); i++) {
                if (ptraux.getConteudoAnt().equals((String) lista.getNo(i).getConteudo())) {
                    ptraux = lista.getNo(i);
                    ptr21 += (String) lista.getNo(i).getConteudo();
                    break;
                }
            }
            if (aux > lista.tamanho()) {
                break;
            }
        }


        /*
            A classe StringBuffer tem um método que é capaz de inverter uma string
            isso é perfeito para fazer a nossa verificação final e falar se ela é
            sana ou não, comparando a String resultantes que adqurimos
         */
        StringBuffer inverso = new StringBuffer(ptr21);
        if (ptr12.equals(inverso.reverse().toString())) {
            System.out.println("Sana");
        } else {
            System.out.println("Insana");
        }
    }
}
