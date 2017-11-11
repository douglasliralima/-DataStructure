import java.util.Scanner;

public class exercicio6 {
    
    public static void main(String[] args) {

        Scanner leitura = new Scanner(System.in);
        int tamanho = Integer.parseInt(leitura.nextLine());
        int pai = 1;
        int b  = 0;
        ArvBin arvore = new ArvBin();
        String pre_Ordem, pos_Ordem, in_Ordem, pre, in, pos;
        boolean flag = true;
        pre_Ordem = leitura.nextLine() + " ";
        pos_Ordem = leitura.nextLine() + " ";
        in_Ordem = leitura.nextLine() + " ";
        arvore.insereRaiz(pai);
        
        for(int a = 2; a <= tamanho; a++){
            if(flag){
                arvore.insereEsq(pai, a);
                flag = false;
            }
            else{
                arvore.insereDir(pai, a);
                flag = true;
                b=b+1;
                pai = a - b;
                /*
                Se o próximo valor já for o último, ele adicionará na direita 
                como mostra no spoj
                */
                if( a + 1 == tamanho){
                    arvore.insereDir(pai, a + 1 );
                    break;
                }
            }
        }
        
        pre = arvore.exibePreOrdem();
        pos = arvore.exibePosOrdem();
        in = arvore.exibeInOrdem();
        if( pre.equals(pre_Ordem) && pos.equals(pos_Ordem) && in.equals(in_Ordem)) System.out.println("yes");
        else System.out.println("no");
    }
    
}

    class No {
	private int conteudo;
	private No esq;
	private No dir;
	
	public No(){
		esq = null;
		dir = null;
	}
	
	public int getConteudo() {
		return conteudo;
	}
	public void setConteudo(int conteudo) {
		this.conteudo = conteudo;
	}
	
	public No getEsq() {
		return esq;
	}
	public void setEsq(No esq) {
		this.esq = esq;
	}
	
	public No getDir() {
		return dir;
	}
	
	public void setDir(No dir) {
		this.dir = dir;
	}
}
    class ArvBin {
	private No raiz;
        String conteudoPre = "", conteudoPos = "",conteudoIn = "";
	public ArvBin(){
		raiz = null;
	}
	
	/** Verifica se a árvore está vazia */
	public boolean vazia (){
		return (raiz == null);
	}

	/** Funcao de busca recursiva.
		Retorna o endereço do elemento se ele for encontrado.
		Caso contrario, retorna null*/
	private No busca(No T, int valor) {          
		No aux;
		
		// Condicao de parada
		if (T == null) 
			return null;  // Arvore Vazia

		// Condicao de parada
		if(T.getConteudo() == valor) 
			return T; //Elem. encontrado na raiz
	  
		// Caso recursivo
		aux = busca(T.getEsq(), valor);
		if (aux == null) 
			aux = busca(T.getDir(), valor);
	   
		return aux;
	}
	
	/** Buscar um elemento na árvore
		Retorna o endereço se o elemento for encontrado, 
		Caso contrário retorna NULL*/
	public No busca(int valor) {          
		if (vazia())
			return null;
		
		//No res = busca(raiz, valor);
		//return res;
		return busca(raiz, valor);
	}
	
	
	/** Insere um nó raiz em uma árvore vazia 
	 	Retorna true se a inserção for com sucesso.
		Caso contrário, retorna false */   
	public boolean insereRaiz(int valor) {   
		if (raiz != null) 
			return false;  //Erro: Arvore não está vazia

		No novoNo = new No();
		novoNo.setConteudo(valor);
		novoNo.setEsq(null);
		novoNo.setEsq(null);
		
		raiz = novoNo;
		return true;
	}   

	/** Insere um filho à direita de um dado nó.
    		Retorna true se a inserção for com sucesso,
    		Caso contrário false  */
	public boolean insereDir(int vPai, int vFilho ) {
		
		// Verifica se o elemento já existe
		No filho = busca(vFilho);
		if (filho != null)
	        return false;  // Err: dado já existe
	
		// Busca o pai e verifica se possui filho direito
		No pai = busca(vPai);
	  	if (pai == null)
			return false;  // Err: pai não encontrado
		
	  	if (pai.getDir() != null)
			return false;  // Err: filho dir já existe
	
		No novoNo = new No();
		novoNo.setConteudo(vFilho);
		novoNo.setEsq(null);
		novoNo.setDir(null);
		
		pai.setDir(novoNo);
		
		return true;
	}

	/** Insere um filho à esquerda de um dado nó.
		Retorna true se a inserção for com sucesso,
		Caso contrário false  */
	public boolean insereEsq(int vPai, int vFilho ) {
		
		// Verifica se o elemento já existe 
		No filho = busca(vFilho);
		if (filho != null)
	        return false;  // Err: dado já existe
	
		// Busca o pai e verifica se possui filho da esq
		No pai = busca(vPai);
	  	if (pai == null)
			return false; // Err: pai não encontrado
	  	
		if (pai.getEsq() != null)
			return false; // Err: filho esq já existe
	
		No novoNo = new No();
		novoNo.setConteudo(vFilho);
		novoNo.setEsq(null);
		novoNo.setDir(null);
		
		pai.setEsq(novoNo);
		return true;
	}

	/** Exibe o conteúdo de uma árvore em pré-ordem*/
	private String exibePreOrdem(No T) {
		if (T == null)
			return "";
                
                conteudoPre = conteudoPre + T.getConteudo() + " ";
                
		if (T.getEsq() != null)
			exibePreOrdem(T.getEsq());
	
	 	if (T.getDir() != null)
			exibePreOrdem(T.getDir());
                
                return conteudoPre;
	}

	/** Exibe o conteúdo de uma árvore em pré-ordem*/
	public String exibePreOrdem() {
		if (raiz == null)
			return "Arvore vazia";
		else
			return exibePreOrdem(raiz);
	}	
	
	
	/** Exibe o conteúdo de uma árvore em pré-ordem*/
	private String exibeInOrdem(No T) {
		if (T == null) return "";
                
		if (T.getEsq() != null)
			exibeInOrdem(T.getEsq());
                
                 conteudoIn = conteudoIn + T.getConteudo() + " ";
	
	 	if (T.getDir() != null)
			exibeInOrdem(T.getDir());
                
                return conteudoIn;
	}

	
	/** Exibe o conteúdo de uma árvore em pré-ordem*/
	public String exibeInOrdem() {
		if (raiz == null)
			return "Arvore vazia";
		else
			return exibeInOrdem(raiz);
	}	
	
	/** Exibe o conteúdo de uma árvore em pré-ordem*/
	private String exibePosOrdem(No T) {
		if (T == null)
			return "";

		if (T.getEsq() != null)
			exibePosOrdem(T.getEsq());
	
	 	if (T.getDir() != null)
			exibePosOrdem(T.getDir());

	 	return conteudoPos = conteudoPos + T.getConteudo() + " ";
	}
	
	/** Exibe o conteúdo de uma árvore em pré-ordem*/
	public String exibePosOrdem() {
		if (raiz == null)
			return "Arvore vazia";
		else
			return exibePosOrdem(raiz);
	}
	
}