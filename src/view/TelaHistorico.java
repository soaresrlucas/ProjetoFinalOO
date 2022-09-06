package view;

import javax . swing .*;
import java.awt.event.*;
/**
 * Classe TelaHistorico e a interface grafica que exibe uma lista de todas as Rotinas que foram executadas.
 * @author Lucas Soares Rodigues 
 */
public class TelaHistorico implements ActionListener{
	JFrame frameHist, frameRetorno;
	JButton bVolta;
	JList<String> listaHist;
	DefaultListModel<String> listModel;
	JScrollPane scroll;
	JLabel texto[] = new JLabel[20];
	int numHist = 0;
	int posY = 10;
	/**
	 * Metodo construtor da Classe TelaHistorico. Cria todos os elementos vizuais da Classe.
	 * @param framePrincipal JFrame da TelaPrincipal.
	 */
	public TelaHistorico(JFrame framePrincipal) {
		frameHist = new JFrame("Historico");
		frameRetorno = framePrincipal;
		bVolta = new JButton("Voltar");
		listModel = new DefaultListModel<String>();
		listaHist = new JList<String>(listModel);
		scroll = new JScrollPane(listaHist);
		
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) listaHist.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		scroll.setBounds(190, 60, 200, 200);
		
		bVolta.addActionListener(this);
		bVolta.setBounds(10, 235, 170, 25);
		
		
		//frameHist.add(scroll);
		frameHist.add(bVolta);
		
		frameHist.setLayout(null);
		frameHist.setSize(700, 350);
		frameHist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameHist.setLocationRelativeTo(null);
		frameHist.setVisible(false);
	}
	/**
	 * Implementacao do metodo abstrato da Interface ActionListener. Realiza eventos com origem nos botoes.
	 * @param event Evento detectado.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		frameHist.setVisible(false);
		frameRetorno.setVisible(true);
	}
	/**
	 * Metodo responsavel por tornar a TelaHistorico visivel.
	 */
	public void setVisible() {
		frameHist.setVisible(true);
	}
	/**
	 * Metodo responsavel por adicionar as informacoes no historico, registrando o nome da Rotina e quantos exercicios foram executados.
	 * Para garantir a posicao correta do JLabel e utilizada a variavel posY.
	 * @param nome Nome da Rotina que foi executada.
	 * @param numeroExercicio Quantidade de exercicios que foram executados.
	 */
	public void adicionaHist(String nome, int numeroExercicio) {
		texto[numHist] = new JLabel("Rotina: " + nome);
		texto[numHist].setBounds(190, posY, 200, 20);
		texto[numHist + 1] = new JLabel("Numero de exercicios: " + numeroExercicio);
		texto[numHist + 1].setBounds(350, posY, 200, 20);
		frameHist.add(texto[numHist]);
		frameHist.add(texto[numHist + 1]);
		
		numHist += 2;
		posY += 25;
	}
}
