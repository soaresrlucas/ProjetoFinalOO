/**
 * Pacote de negocio
 * @author Lucas Soares Rodrigues
 */
package modelo;
/**
 * Classe Cardio e responsavel pelos Exercicios do tipo cardio e herda da Classe Exercicio.
 * @author Lucas Soares Rodigues 
 */
public class Cardio extends Exercicio {
	private double distancia; //distancia em km
	/**
	 * Metodo construtor da Classe Cardio
	 * @param nome Nome do Exercicio do tipo Cardio.
	 */
	public Cardio(String nome) {
		super.setNome(nome);
		super.setTipo("Cardio");
	}
	/**
	 * Sobrescrita do metodo abstrato da Classe Pai Exercicio. Responsavel por setar a distancia a ser percorrida no exercicio de Cardio.
	 * @param distancia
	 * @param num2 Parametro nao utilizado.
	 * @param num3 Parametro nao utilizado.
	 */
	@Override
	public void configuraExercicio(double distancia, int num2, int num3) { //MODIFICAR
		this.distancia = distancia;
	}
	
	public double getDistancia() {
		return distancia;
	}
	
}
