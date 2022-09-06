
package modelo;
/**
 * Classe abstrata Exercicio e a forma primaria de todos os exercicios, sendo Classe pai de Cardio e Maquina.
 * @author Lucas Soares Rodigues 
 */
public abstract class Exercicio {
	private String nome;
	private String tipo;
	private double distancia = 0, peso = 0;
	private int repeticao = 0, serie = 0;
	/**
	 * Metodo abstrato para inserir os valores dos exercicios a depender do tipo (cada tipo corresponde a uma classe filha).
	 * @param num1 Para a Classe Cardio, se refere a distancia. Para a Classe Maquina, se refere ao peso.
	 * @param num2 Para a Classe Maquina, se refere as repeticoes que serao realizadas.
	 * @param num3 Para a Classe Maquina, se refere as series que serao realizadas.
	 */
	public abstract void configuraExercicio(double num1, int num2, int num3);
	
	public void setDistancia(double distancia) {
		this.distancia = distancia;
		configuraExercicio(distancia, 0, 0);
	}
	
	public void setMaquina(double peso, int repeticao, int serie) {
		this.peso = peso;
		this.repeticao = repeticao;
		this.serie = serie;
		configuraExercicio(peso, repeticao, serie);
	}
	
	public double getDistancia() {
		return this.distancia;
	}
	
	public double getPeso() {
		return this.peso;
	}
	
	public int getRepeticao() {
		return this.repeticao;
	}
	
	public int getSerie() {
		return this.serie;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
