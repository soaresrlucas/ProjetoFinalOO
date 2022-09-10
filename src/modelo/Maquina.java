/**
 * Pacote de negocio
 * @author Lucas Soares Rodrigues
 */
package modelo;
/**
 * Classe Maquina e responsavel pelos Exercicios do tipo Maquina e herda da Classe Exercicio.
 * @author Lucas Soares Rodigues 
 */
public class Maquina extends Exercicio {
	private double peso;
	private int repeticao;
	private int serie;
	/**
	 * Metodo construtor da Classe Maquina.
	 * @param nome Nome do Exercicio.
	 */
	public Maquina(String nome) {
		super.setNome(nome);
		super.setTipo("maquina");
	}
	/**
	 * Sobrescrita do metodo abstrato da Classe Pai Exercicio. Resposavel por setar os valores do exercicio.
	 * @param peso Peso a ser definido para esse exercicio.
	 * @param repeticao Repeticoes a serem realizadas nesse exercicio.
	 * @param serie Series a serem realizadas nesse exercicio.
	 */
	@Override
	public void configuraExercicio(double peso, int repeticao, int serie) { //MODIFICAR
		this.peso = peso;
		this.repeticao = repeticao;
		this.serie = serie;
	}
	
	public double getPeso() {
		return peso;
	}
	
	public void setPeso(float peso) {
		this.peso = peso;
	}
	
	public int getRepeticao() {
		return repeticao;
	}
	public void setRepeticao(int repeticao) {
		this.repeticao = repeticao;
	}
	public int getSerie() {
		return serie;
	}
	public void setSerie(int serie) {
		this.serie = serie;
	}
}
