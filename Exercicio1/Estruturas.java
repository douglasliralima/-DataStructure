package estruturas;
import java.util.Scanner;

/**
 *
 * @author aluno
 */
public class Estruturas {

    /**
     * @param args the command line arguments
     */
    
    //Ele quer que printemos a sequência ascendente de s que não aparece em q
    public static void main(String[] args) {
        //Atributos
        Scanner scan = new Scanner(System.in); //Leitor de dados
        int[] s;
        int[] q;
        int[] sq;//Nesse array será jogado os elementos de 's' q se repetem em 'q'
        int tamanhoarray;
        String saida = ""; //Será a saida do nosso programinha
        boolean zero = false; //Começa false porque apenas fica true, caso entre 0s na jogada
        String leitura;
        //Programa
        //Primeiro vamos definir o tamanho do array
        leitura = scan.nextLine();
        tamanhoarray = Integer.parseInt(leitura);
        s = new int[tamanhoarray];
        sq= new int[tamanhoarray]; //O máximo que pode acontecer é todos os elementos de s serem comuns em q
        
        /*Vamos pegar os números que serão jogados no array, em uma mesma linha */
         leitura = scan.nextLine();
       /*O método da classe string, split, consegue dividir um string 
         toda vez que encontra a cadeia que mandamos. Estamos então pegando 
         essas strings dividas e jogando em um array com apenas elas separadas*/
       String numeros[] = leitura.split(" ");    

       /*Pegamos então esse array de strings dos números divididos e 
       transformamos finalmente eles no array de ints */
       for(int i = 0; i<numeros.length; i++){
           s[i] = Integer.parseInt(numeros[i]);
           if(s[i] == 0){
               zero = true; //Significa que o vai ter 0s definidos pelo usuario
           }
       }
       
       //Inicializa q
       leitura = scan.nextLine();
       q = new int[Integer.parseInt(leitura)];
       
       //Lê os números para colocar no array de strings
       leitura = scan.nextLine();
       String numeros2[] = leitura.split(" ");
       
       //transforma o array de string em um array de int
       for(int i = 0; i<q.length;i++){
           q[i] = Integer.parseInt(numeros2[i]);
       }
    //Todos os elementos em 's' que não estarão em q, serão jogados em sq pelo código:
       for(int i = 0; i<s.length; i++){ //Paraca elemento de s
           boolean igual = false;
                for(int j = 0; j<q.length; j++){ //Verificamos todos os elementos de q
                    //Se ele encontrar 1 igual ele dá o break no for e sinaliza que tem igual
                    if(s[i]==q[j]){
                        igual = true;
                        break;
                    }
                }
                if(igual == false){
                    sq[i] = s[i];
                }
        }
    //Agora temos que ordenar nosso array, vamos usar o ordenação bubblesort
    int aux;
    boolean controle;
 
    for(int i = 0; i < sq.length; i++){
        controle = true;
            for(int j = 0; j < (sq.length - 1); j++){
                 if(sq[j] > sq[j + 1]){
                    aux = sq[j];
                    sq[j] = sq[j + 1];
                    sq[j + 1] = aux;
                    controle = false;
                }
            }
        if(controle){
            break;
        }
 
    }
    //Aqui termina o BubbleSort e ele já está ordenado.
   
    /*
        Vamos pegar o nosso array sq e transforma-lo em um string que condiza com 
    a forma especificada pelo juiz.
        
    */
    for (int i = 0; i< sq.length;i++){
        /*
        Aqui iremos verificar se o zero entrou na jogada alguma vez ou se está apenas sendo repetido
        loucamente por ser um array padrão
        */
        if( (sq[i] == 0) && (zero == false)){
            continue;
        }
        
        saida += "".concat(String.valueOf(sq[i])) + " ";//Pequena manipulação de array usando o método concat
        
        if(sq[i] == 0){
            zero = false;   
        }
    }
    /*Beleza, temos aqui o nosso string, lindo e maravilhoso,
    agora temos que retirar o excesso de zeros*/
    System.out.println(saida);

    
    }
       
}
