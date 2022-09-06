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
	JFrame frameCardio, frameMaquina, frameAtual;
	JButton bFeito;
	JLabel texto, textoR, textoS;
	Rotina rotina;
	Exercicio exercicios[] = new Exercicio[7];
	Cardio auxCardio;
	Maquina auxMaquina;
	int exercicioAtual = 0;
	TelaHistorico telaHist;
	/**
	 * Metodo construtor da Classe TelaInicia. Cria todos os elementos vizuais da Classe.
	 * @param rotina Instancia de Rotina referente a essa tela.
	 * @param telaHist Instancia de TelaHistorico.
	 */
	public TelaInicia(Rotina rotina, TelaHistorico telaHist) { //implementar tempoDescando entre exercicios se possivel
		frameCardio = new JFrame();
		frameMaquina = new JFrame();
		bFeito = new JButton("Feito");
		texto = new JLabel();
		textoR = new JLabel();
		textoS = new JLabel();
		this.rotina = rotina;
		this.telaHist = telaHist;
		
		for(int i = 0; i < rotina.getQtdExercicio(); i++) {
			this.exercicios[i] = rotina.getExercicio(i);
		}
		bFeito.addActionListener(this);
		frameCardio.setLayout(new FlowLayout());
		frameMaquina.setLayout(new FlowLayout());
		
		frameCardio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMaquina.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frameCardio.setSize(250, 80);
		frameMaquina.setSize(300, 100);

		execucao();
		frameCardio.setLocationRelativeTo(null);
		frameMaquina.setLocationRelativeTo(null);
		
	}
	/**
	 * Implementacao do metodo abstrato da Interface ActionListener. Realiza eventos com origem nos botoes.
	 * @param event Evento detectado.
	 */
	@Override //acoes dos botoes
	public void actionPerformed(ActionEvent event) {
		Object src = event.getSource();
		
		if(src == bFeito) {
			exercicioAtual++;
			frameAtual.dispose();
			execucao();
			
		}
	}
	/**
	 * Metodo responsavel pela execucao da Rotina. Exibindo um exercicio de cada vez e como o mesmo deve ser executado.
	 */
	public void execucao() {
		if(exercicioAtual < rotina.getQtdExercicio()) {
			if(exercicios[exercicioAtual].getTipo() == "Cardio") {
				frameCardio.setTitle(exercicios[exercicioAtual].getNome());
				texto.setText("Percorra " + exercicios[exercicioAtual].getDistancia() + "km");
				frameCardio.add(texto);
				frameCardio.add(bFeito);
				frameAtual = frameCardio;
				frameAtual.setLocationRelativeTo(null);
				frameAtual.setVisible(true);
			}else if(exercicios[exercicioAtual].getTipo() == "Maquina") {
				frameMaquina.setTitle(exercicios[exercicioAtual].getNome());
				texto.setText("Peso: " + exercicios[exercicioAtual].getPeso());
				textoR.setText("Repeticoes: " + exercicios[exercicioAtual].getRepeticao());
				textoS.setText("Series: " + exercicios[exercicioAtual].getSerie());
				frameMaquina.add(texto);
				frameMaquina.add(textoR);
				frameMaquina.add(textoS);
				frameMaquina.add(bFeito);
				frameAtual = frameMaquina;
				frameAtual.setLocationRelativeTo(null);
				frameAtual.setVisible(true);
			}
		} else if(rotina.getQtdExercicio() == 0) {
			JOptionPane.showMessageDialog(null, "Por favor, adicione um exercicio");
		} else if(exercicioAtual == rotina.getQtdExercicio()){
			JOptionPane.showMessageDialog(null, "Rotina realizada!");
			telaHist.adicionaHist(rotina.getNome(),rotina.getQtdExercicio());
		}
	}
}