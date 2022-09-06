/**
 * Pacote da interface grafica
 * @author Lucas Soares Rodrigues
 */
package view;

import javax . swing .*;
import modelo.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Classe TelaInicia e a interface grafica que exibe a execucao dos Exercicios.
 * @author Lucas Soares Rodigues 
 */
public class TelaInicia implements ActionListener{
	private JFrame frameCardio, frameMaquina, frameAtual;
	private JButton bFeito;
	private JLabel texto;
	private Rotina rotina;
	private Exercicio exercicios[] = new Exercicio[10];
	private int exercicioAtual = 0;
	private TelaHistorico telaHist;
	private String filtro;
	/**
	 * Metodo construtor da Classe TelaInicia. Cria todos os elementos vizuais da Classe.
	 * @param rotina Instancia de Rotina referente a essa tela.
	 * @param telaHist Instancia de TelaHistorico.
	 */
	public TelaInicia(Rotina rotina, TelaHistorico telaHist, String filtro) {
		frameCardio = new JFrame();
		frameMaquina = new JFrame();
		bFeito = new JButton("Feito");
		texto = new JLabel();
		this.rotina = rotina;
		this.telaHist = telaHist;
		this.filtro = filtro;
		//Passa todos os exercicios da Rotina para o vetor exercicios
		for(int i = 0; i < rotina.getQtdExercicio(); i++) {
			this.exercicios[i] = rotina.getExercicio(i);
		}
		bFeito.addActionListener(this);
		//Configurando a tela de execucao de Exercicio do tipo Cardio
		frameCardio.setLayout(new FlowLayout());
		frameCardio.setSize(250, 80);
		//Configurando a tela de execucao de Exercicio do tipo Maquina
		frameMaquina.setLayout(new FlowLayout());
		frameMaquina.setSize(300, 100);
		//Centraliza as telas
		frameCardio.setLocationRelativeTo(null);
		frameMaquina.setLocationRelativeTo(null);
		
		execucao();
	}
	/**
	 * Implementacao do metodo abstrato da Interface ActionListener. Realiza eventos com origem nos botoes.
	 * @param event Evento detectado.
	 */
	@Override //acoes dos botoes
	public void actionPerformed(ActionEvent event) {
		Object src = event.getSource();
		
		if(src == bFeito) { //Botao feito clicado
			exercicioAtual++;
			frameAtual.dispose();
			execucao();
			
		}
	}
	/**
	 * Metodo responsavel pela execucao da Rotina. Exibindo um exercicio de cada vez e como o mesmo deve ser executado.
	 */
	public void execucao() {
		int cont;
		cont = contaExercicios();
		if(exercicioAtual < cont) { //Caso ainda tenha algum Exercicio a ser executado
			if(exercicios[exercicioAtual].getTipo() == "Cardio") { //Caso o Exercicio a ser executado seja do tipo Cardio
				frameCardio.setTitle(exercicios[exercicioAtual].getNome());
				texto.setText("Percorra " + exercicios[exercicioAtual].getDistancia() + " km");
				frameCardio.add(texto);
				frameCardio.add(bFeito);
				frameAtual = frameCardio;
				frameAtual.setLocationRelativeTo(null);
				frameAtual.setVisible(true);
			}else if(exercicios[exercicioAtual].getTipo() == "Maquina") { //Caso o Exercicio a ser executado seja do tipo Maquina
				frameMaquina.setTitle(exercicios[exercicioAtual].getNome());
				texto.setText("Peso: " + exercicios[exercicioAtual].getPeso() + " kg" + "    Repeticoes: " +
						exercicios[exercicioAtual].getRepeticao() + "    Series: " + exercicios[exercicioAtual].getSerie());
				frameMaquina.add(texto);
				frameMaquina.add(bFeito);
				frameAtual = frameMaquina;
				frameAtual.setLocationRelativeTo(null);
				frameAtual.setVisible(true);
			}
		} else if(cont == 0) { //Caso nao tenha Exercicio adicionado a Rotina
			JOptionPane.showMessageDialog(null, "Por favor, adicione um exercicio");
		} else if(exercicioAtual == cont){ //Caso todos os exercicios da Rotina tenham sido executados
			JOptionPane.showMessageDialog(null, "Rotina realizada!");
			telaHist.adicionaHist(rotina.getNome(), cont); //Registra a execucao no historico
		}
	}
	/**
	 * Metodo para executar a Rotina de acordo com a filtragem. Retorna a quantidade de exercicios que correspondem ao tipo do filtro.
	 * @return int
	 */
	public int contaExercicios() {
		int cont = 0;
		if (filtro == "Todos") {
			return rotina.getQtdExercicio();
		}
		for(int i = 0; i < rotina.getQtdExercicio(); i++) {
			if(rotina.getExercicio(i).getTipo() == filtro) {
				this.exercicios[cont] = rotina.getExercicio(i);
				cont++;
			}
		}
		return cont;
	}
}