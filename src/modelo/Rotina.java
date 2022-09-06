
package modelo;
/**
 * Classe Rotina armazena uma lista de exercicios.
 * @author Lucas Soares Rodrigues
 */
public class Rotina {
	private String nome;
	private Exercicio exercicios[] = new Exercicio[10];
	private int qtdExercicio = 0;
	
	/**
	 * Metodo adiciona um novo exercicio na lista de exercicios levando em conta o tipo de exercicio.
	 * @param nome Nome do Exercicio.
	 * @param tipo Tipo do Exercicio. Cardio ou Maquina.
	 */
	public void adicionaExercicio(String nome, String tipo) {
		if("cardio" == tipo.intern()) {
			exercicios[qtdExercicio] = new Cardio(nome);
		}else if("maquina" == tipo.intern()) {
			exercicios[qtdExercicio] = new Maquina(nome);
		}
		qtdExercicio ++;
	}
	/**
	 * Edita o exercicio escolhido. Esse exercicio e anulado e o metodo adicionaExercicio e chamado com o valor de qtdExercicio igual ao index.
	 * @param index Posicao do Exercicio a ser editado.
	 * @param nome Novo nome do Exercicio.
	 * @param tipo Novo tipo do Exercicio.
	 */
	public void editaExercicio(int index, String nome, String tipo) {
		int temp;
		temp = qtdExercicio;
		exercicios[index] = null;
		qtdExercicio = index;
		this.adicionaExercicio(nome, tipo);
		qtdExercicio = temp;
	}
	
	/**
	 * Remove um exercicios de uma posicao especifica da lista.
	 * @param index Posicao do exercicio a ser removido.
	 */
	public void removeExercicio(int index) { //Apagar exercicios especifico 
		for(int i = index; i < qtdExercicio; i++) {
			exercicios[i] = exercicios[i + 1];
		}
		qtdExercicio--;
	}
	
	public Exercicio getExercicio(int index) {
		return this.exercicios[index];
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQtdExercicio() {
		return qtdExercicio;
	}

	public void setQtdExercicio(int qtdExercicio) {
		this.qtdExercicio = qtdExercicio;
	}
}
