import java.util.*;

class Exercicio4{
    
    //Colocar sua pilha, no, exceção de pilha vazia aqui : D
    
    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);
        String expressao;
        int expressoes;

        //Aqui nós usamos o Scanner para definir quantas expressões teremos
        expressoes = leitor.nextInt();

        //Para cada expressao especificada, a seguinte expressao será chamada
        for (int i = 0; expressoes > i; i++) {
            expressao = leitor.next();
            /*
            Para cada expressão, uma nova pilha será criada que servirá de
            auxiliar na formatação da string para o formato desejado
             */
            Exercicio4 pilha = new Exercicio4();
            String editado = ""; //String que guardará nosso valor formatado no formato polones inverso
            char aux;
             
            for (int j = 0; j < expressao.length(); j++) { //Vamos correr a expressão

                /*   
                O método chatAt, da classe string, nos permitirá ir
                coletando cada caracter para fazer o julgamento lógico, como estamos trabalhando
                com dois algarismos dentro dos parentêses o'(' não importará muito em nossa edição,
                como no resultado final ele quer a expressão sem os parenteses, simplesmente guardamos 
                os algarismos, quando achamos uma expressão, guardamos em uma pilha e seguindo a lógica aritmética e
                por ser o inverso da polonesa, sempre que for encontrado um ')' é aonde estaria o resultado da nossa 
                expressão
                 */
                aux = expressao.charAt(j);
                switch (aux) {
                    case '(':
                        break;

                    case '+':
                        pilha.push(aux);
                        break;

                    case '-':
                        pilha.push(aux);
                        break;

                    case '*':
                        pilha.push(aux);
                        break;

                    case '/':
                        pilha.push(aux);
                        break;

                    case '^':
                        pilha.push(aux);
                        break;

                    case ')':
                        editado += pilha.pop();
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
