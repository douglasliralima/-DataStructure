import java.util.*;
import java.lang.*;

class Main
{
	public static void main (String[] args) throws java.lang.Exception{
        try{
            Scanner leitura = new Scanner(System.in);
            boolean[] corrente;
            char[] arvoreC;
            int tamanho;
            tamanho = Integer.parseInt (leitura.nextLine());

            for(int i = 0; i < tamanho; i++){
                int aux = 0, saida = 0;
                String arvore = leitura.nextLine();
                corrente = new boolean [arvore.length()];
                if (arvore.charAt(0) == 'l') {
                    System.out.println(0);
                    continue;
                }
                corrente[0] = true;
                arvoreC = new char[arvore.length()];

                for(int j = 0; j < arvore.length(); j++){
                    arvoreC[j] = arvore.charAt(j);
                }

                for (int j = 0; j < arvoreC.length; j++) {
                    if (arvoreC[j] == 'n'){
                        aux++;
                        corrente[aux] = true;
                    } 
                        if (arvoreC[j] == 'l'){
                            while(corrente[aux] != true)aux--; 
                            corrente[aux] = false;
                    }
                    saida = Math.max(saida, aux);
                }
                System.out.println(saida);
            }
        }
        catch(Exception ex){
        }
    }
}