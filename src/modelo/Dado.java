/**
 * Pacote de negocio
 * @author Lucas Soares Rodrigues
 */
package modelo;
/**
 * Classe Dado simula um banco de dados para ser pre-carregado dentro do programa.
 * @author Lucas Soares Rodigues 
 */
public class Dado {

	private Rotina rotinas[] = new Rotina[5];
/**
 * Metodo construtor da Classe Dado. Instancia rotinas e exercicios para serem pre-carregados no programa.
 */
	public Dado() {
		rotinas[0] = new Rotina();
		rotinas[1] = new Rotina();
		rotinas[2] = new Rotina();
		//Criando as Rotinas a serem pre-carregadas
		rotinas[0].setNome("Superiores");
		rotinas[1].setNome("Inferiores");
		rotinas[2].setNome("Projeto verao");
		//Adicionando os Exercicios a serem pre-carregados
		rotinas[0].adicionaExercicio("Esteira", "cardio");
		rotinas[0].adicionaExercicio("Supino", "maquina");
		rotinas[0].adicionaExercicio("Crucifixo", "maquina");
		rotinas[0].adicionaExercicio("Bicicleta", "cardio");
		rotinas[1].adicionaExercicio("Esteira", "cardio");
		rotinas[1].adicionaExercicio("Leg Press", "maquina");
		rotinas[1].adicionaExercicio("Extensora", "maquina");
		rotinas[1].adicionaExercicio("Flexora", "maquina");
		rotinas[1].adicionaExercicio("Bicicleta", "cardio");
		rotinas[2].adicionaExercicio("Esteira", "cardio");
		rotinas[2].adicionaExercicio("Leg Press", "maquina");
		rotinas[2].adicionaExercicio("Crucifixo", "maquina");
		rotinas[2].adicionaExercicio("Esteira", "cardio");
		rotinas[2].adicionaExercicio("Flexora", "maquina");
		rotinas[2].adicionaExercicio("Bicicleta", "cardio");
		//Adicionando os valores da cada exercicios adicionado
		rotinas[0].getExercicio(0).setDistancia(10);
		rotinas[0].getExercicio(1).setMaquina(40, 11, 3);
		rotinas[0].getExercicio(2).setMaquina(10.0, 11, 3);
		rotinas[0].getExercicio(3).setDistancia(20);
		rotinas[1].getExercicio(0).setDistancia(10.0);
		rotinas[1].getExercicio(1).setMaquina(100.5, 15, 3);
		rotinas[1].getExercicio(2).setMaquina(40.0, 13, 3);
		rotinas[1].getExercicio(3).setMaquina(40.0, 13, 3);
		rotinas[1].getExercicio(4).setDistancia(20.0);
		rotinas[2].getExercicio(0).setDistancia(10.0);
		rotinas[2].getExercicio(1).setMaquina(100.0, 15, 3);
		rotinas[2].getExercicio(2).setMaquina(10.0, 11, 3);
		rotinas[2].getExercicio(3).setDistancia(10.0);
		rotinas[2].getExercicio(4).setMaquina(40.0, 13, 3);
		rotinas[2].getExercicio(5).setDistancia(20.0);
		
	}
	/**
	 * Metodo auxiliar para outras Classes teres acesso as rotinas pre-carregadas.
	 * @param idx Posicao da Rotina desejada.
	 * @return Rotina
	 */
	public Rotina getRotina(int idx) {
		return rotinas[idx];
	}
}
