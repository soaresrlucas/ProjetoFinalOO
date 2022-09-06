package view;

import javax . swing .*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Classe TelaRotina e a interface grafica que exibe a lista de Exercicios de cada instancia de rotina.
 * @author Lucas Soares Rodigues 
 */
public class TelaRotina implements ActionListener, ItemListener, ListSelectionListener{
	JFrame frameRotina, frameRetorno, frameAdiciona, frameMaquina, frameCardio, frameExclui;
	DefaultListModel<String> listModel;
	JList<String> listaExercicio;
	JScrollPane scroll;
	JTextField campoNome, valorDistCardio, valorTempoCardio, ValorIdMaquina, valorPesoMaquina, valorSerieMaquina, valorRepeticaoMaquina;
	JButton bInicia, bExclui, bAdiciona, bVolta, bConfirma, bEdita;
	JLabel texto, distCardio, tempoCardio, idMaquina, pesoMaquina, serieMaquina, repeticaoMaquina, textoExclui, textoAdiciona;
	Rotina rotina;
	TelaHistorico telaHist;
	JRadioButton opMaquina, opCardio;
	String tipo, aux, aux2, aux3;
	ButtonGroup radioGroup;
	int check = 0, index;
	Exercicio exercicios[] = new Exercicio[7];
	/**
	 * exclusao e edicao sao variaveis do tipo boolean. São responsaveis para diferenciar a intencao do usuario ao interagir com a lista.
	 */
	boolean exclusao = false, edicao = false;
	/**
	 * Metodo construtor da Classe TelaRotina. Cria todos os elementos vizuais da Classe.
	 * @param rotina Armazena dados da rotina referente a essa instancia de TelaRotina.
	 * @param nome Define o texto que sera exibido no JLabel 'texto'.
	 * @param framePrincipal JFrame da TelaPrincipal para retornar ao clicar no botao 'Voltar'.
	 * @param dados Dados para serem pre-carregados.
	 * @param telaHist Instancia da Classe TelaHistorico. 
	 */
	public TelaRotina(Rotina rotina, String nome, JFrame framePrincipal, Dado dados, TelaHistorico telaHist) {
		this.rotina = rotina;
		this.telaHist = telaHist;
		frameRotina = new JFrame();
		frameAdiciona = new JFrame("Adicionar Exercicio");
		frameExclui = new JFrame();
		frameRetorno = framePrincipal;
		listModel = new DefaultListModel<String>();
		listaExercicio = new JList<String>(listModel);
		scroll = new JScrollPane();
		bInicia = new JButton("Iniciar Rotina");
		bExclui = new JButton("Excluir Exercicio");
		bAdiciona = new JButton("Adicionar Exercicio");
		bVolta = new JButton("Voltar");
		bConfirma = new JButton("Confirmar");
		bExclui = new JButton("Excluir Exercicio");
		bEdita = new JButton("Editar Exercicio");
		texto = new JLabel(nome);
		textoExclui = new JLabel("Selecione um Exercicio para excluir:");
		//Passando exercicios registrados na classe Dado
		switch (nome) {
		case "Superiores":
			for(int j = 0; j <= 3 ; j++) {
				listModel.addElement(dados.getRotina(0).getExercicio(j).getNome());
				exercicios[j] = rotina.getExercicio(j);
			}
			break;
		case "Inferiores":
			for(int j = 0; j <= 4 ; j++) {
				listModel.addElement(dados.getRotina(1).getExercicio(j).getNome());
				exercicios[j] = rotina.getExercicio(j);
			}
			break;
		case "Projeto verao":
			for(int j = 0; j <= 5 ; j++) {
				listModel.addElement(dados.getRotina(2).getExercicio(j).getNome());
				exercicios[j] = rotina.getExercicio(j);
			}
			break;
		}
		
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) listaExercicio.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		scroll = new JScrollPane(listaExercicio); 
		scroll.setBounds(190, 60, 200, 200);
		texto.setBounds(235, 20, 200, 20);
		//Configurando o tamanho e posicoes dos botoes
		bAdiciona.addActionListener(this);
		bAdiciona.setBounds(10, 60, 170, 25);
		bExclui.addActionListener(this);
		bExclui.setBounds(10, 90, 170, 25);
		bEdita.addActionListener(this);
		bEdita.setBounds(10, 120, 170, 25);
		bInicia.addActionListener(this);
		bInicia.setBounds(10, 205, 170, 25);
		bVolta.addActionListener(this);
		bVolta.setBounds(10, 235, 170, 25);
		//Adicionando os elementos no JFrame da Rotina
		frameRotina.add(texto);
		frameRotina.add(scroll);
		frameRotina.add(bAdiciona);
		frameRotina.add(bExclui);
		frameRotina.add(bEdita);
		frameRotina.add(bInicia);
		frameRotina.add(bVolta);
		
		frameRotina.setLayout(null);
		frameRotina.setSize(500, 350);
		frameRotina.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameRotina.setVisible(false);
		frameRotina.setLocationRelativeTo(null);
		//Configurando o frame de adicionar exercicio
		frameAdiciona.setLayout(new FlowLayout());
		campoNome = new JTextField(20);
		textoAdiciona = new JLabel();
		opCardio = new JRadioButton("Cardio");
		opMaquina = new JRadioButton("Maquina");
		radioGroup = new ButtonGroup();
		opMaquina.addItemListener(this);
		opCardio.addItemListener(this);
		radioGroup.add(opCardio);
		radioGroup.add(opMaquina);
		campoNome.setActionCommand("myTF"); 
		campoNome.addActionListener(this);
		bConfirma.addActionListener(this);
		//Adicionando os elementos no JFrame
		frameAdiciona.add(textoAdiciona);
		frameAdiciona.add(campoNome);
		frameAdiciona.add(opMaquina);
		frameAdiciona.add(opCardio);
		frameAdiciona.add(bConfirma);
		frameAdiciona.setLocationRelativeTo(null);
		
		frameAdiciona.setSize(300, 130);
		//Configurando os JFrame de adicionar exercicio. Exercicios Cardio tem uma tela de adicao diferente de Exercicios Cardio
		frameCardio = new JFrame("Configurando Exercicio");
		frameMaquina = new JFrame("Configurando Exercicio");
		frameCardio.setLayout(new FlowLayout());
		frameMaquina.setLayout(new FlowLayout());
		frameCardio.setLocationRelativeTo(null);
		frameMaquina.setLocationRelativeTo(null);
		
		distCardio = new JLabel("Distancia(km):");
		pesoMaquina = new JLabel("Peso(kg):");
		repeticaoMaquina = new JLabel("Repeticoes:");
		serieMaquina = new JLabel("Series:");
		valorDistCardio = new JTextField(20);
		valorPesoMaquina = new JTextField(10);
		valorRepeticaoMaquina = new JTextField(10);
		valorSerieMaquina = new JTextField(10);
		
		valorDistCardio.setActionCommand("myTF");
		valorDistCardio.addActionListener(this);
		valorPesoMaquina.addActionListener(this);
		valorRepeticaoMaquina.addActionListener(this);
		valorSerieMaquina.addActionListener(this);
		
		frameCardio.add(distCardio);
		frameCardio.add(valorDistCardio);
		frameCardio.setSize(300, 120);
		
		frameMaquina.add(pesoMaquina);
		frameMaquina.add(valorPesoMaquina);
		frameMaquina.add(repeticaoMaquina);
		frameMaquina.add(valorRepeticaoMaquina);
		frameMaquina.add(serieMaquina);
		frameMaquina.add(valorSerieMaquina);
		frameMaquina.setSize(600, 100);
		//Configurando tela de Exclusao de Exercicio
		frameExclui.setLayout(new FlowLayout());
		frameExclui.add(textoExclui);
		frameExclui.setSize(300, 300);
		
		listaExercicio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaExercicio.addListSelectionListener(this);
		//Centralizando as telas
		frameAdiciona.setLocationRelativeTo(null);
		frameCardio.setLocationRelativeTo(null);
		frameMaquina.setLocationRelativeTo(null);
		frameExclui.setLocationRelativeTo(null);
		
	}
	/**
	 * Implementacao do metodo abstrato da Interface ActionListener. Realiza eventos com origem nos botoes.
	 * @param event Evento detectado.
	 */
	@Override //acoes dos botoes
	public void actionPerformed(ActionEvent event) {
		Object src = event.getSource();
		
		if(src == bInicia) { //Usuario clicou no botao de iniciar rotina
			new TelaInicia(rotina, telaHist);
		}
		
		if(src == bAdiciona) { //Usuario clicou no botao de adicionar exercicio 
			textoAdiciona.setText("Escolha o nome do seu Exercicio:");
			frameAdiciona.setVisible(true);
		}
		
		if(src == bExclui) { //Usuario clicou no botao de excluir exercicio
			exclusao = true;
			textoExclui.setText("Selecione um Exercicio para excluir:");
			frameExclui.setTitle("Excluir Exercicio");
			scroll.setBounds(190, 60, 200, 200);
			frameExclui.add(scroll);
			frameExclui.setVisible(true);
		}
		
		if(src == bEdita) { //Usuario clicou no botao de editar exercicio
			edicao = true;
			textoExclui.setText("Selecione um Exercicio para editar:");
			scroll.setBounds(190, 60, 200, 200);
			frameExclui.setTitle("Editar Exercicio");
			frameExclui.add(scroll);
			frameExclui.setVisible(true);
		}
		
		if (src == bVolta) { //Usuario clicou no botao de voltar para tela principal
			frameRetorno.setVisible(true);
			frameRotina.setVisible(false);
		}
		
		if (event.getActionCommand().equals("Confirmar")) { //Usuario clicou no botao confirmar (presente em diversos contextos)
			if(edicao == false) {
				if(campoNome.getText() != "") {	
					if(check == 0) { //Confirmando o nome e o tipo do exercicio
						listModel.addElement(campoNome.getText());
						rotina.adicionaExercicio(campoNome.getText(), tipo);
						if(tipo == "cardio") {
							check = 1;
							frameCardio.add(bConfirma);
							frameCardio.setVisible(true);
						}
						if(tipo == "maquina") {
							check = 2;
							frameMaquina.add(bConfirma);
							frameMaquina.setVisible(true);
						}
						frameAdiciona.dispose();
						//Confirmando dados do exercicio de Cardio
					}else if(check == 1) {
						aux = valorDistCardio.getText();
						rotina.getExercicio(rotina.getQtdExercicio()-1).setDistancia(Double.parseDouble(aux));
						check = 0;
						frameCardio.dispose();
						frameAdiciona.add(bConfirma);
					}
					 //Confirmando dados do exercicio de Maquina
					if(check == 2) { //Ao adicionar um Exercicio de Maquina aparece uma serie de erros no console,
						//porem nao foi detectada nenhuma alteracao na funcionalidade do programa
						aux = valorPesoMaquina.getText();
						aux2 = valorRepeticaoMaquina.getText();
						aux3 = valorSerieMaquina.getText();
						rotina.getExercicio(rotina.getQtdExercicio()-1).setMaquina(Double.parseDouble(aux), Integer.parseInt(aux2), Integer.parseInt(aux3));
						check = 0;
						frameMaquina.dispose();
						frameAdiciona.add(bConfirma);	
					}
				}
			} else { //Botao confirmar na edicao
				if(check == 0) {
					rotina.editaExercicio(index, campoNome.getText(), tipo);
					listModel.setElementAt(rotina.getExercicio(index).getNome(), index);
					frameExclui.dispose();
					frameAdiciona.dispose();
					scroll.setBounds(190, 60, 200, 200);
					frameRotina.add(scroll);
					if(tipo == "cardio") {
						check = 3;
						frameCardio.add(bConfirma);
						frameCardio.setVisible(true);
					}
					if(tipo == "maquina") {
						check = 4;
						frameMaquina.add(bConfirma);
						frameMaquina.setVisible(true);
					}
				}else if(check == 3) { //Editando exercicio de cardio
					check = 0;
					aux = valorDistCardio.getText();
					rotina.getExercicio(index).setDistancia(Double.parseDouble(aux));
					frameCardio.dispose();
					frameAdiciona.add(bConfirma);
					edicao = false;
				}else if(check == 4) { //Editando exercicio de maquina
					check = 0;
					aux = valorPesoMaquina.getText();
					aux2 = valorRepeticaoMaquina.getText();
					aux3 = valorSerieMaquina.getText();
					rotina.getExercicio(index).setMaquina(Double.parseDouble(aux), Integer.parseInt(aux2), Integer.parseInt(aux3));
					frameMaquina.dispose();
					frameAdiciona.add(bConfirma);
					edicao = false;
				}
			}
			campoNome.setText("");
			valorDistCardio.setText("");
			valorPesoMaquina.setText("");
			valorRepeticaoMaquina.setText("");
			valorSerieMaquina.setText("");
		}
	}
	/**
	 * Implementacao do metodo abstrato da Interface ItemListener. Realiza eventos com origem nos botoes do tipo radio.
	 * @param event Evento detectado.
	 */
	@Override
	public void itemStateChanged(ItemEvent event) {
		   if(opCardio.isSelected()) {
			   tipo = "cardio";
		   }else if(opMaquina.isSelected()) {
			   tipo = "maquina";
		   }else {
			   opCardio.setEnabled(true);
			   opMaquina.setEnabled(true);
		   }
	}
	/**
	 * Metodo responsavel por tornar a TelaRotina visivel.
	 */
	public void setVisible() {
		frameRotina.setVisible(true);
	}
	/**
	 * Implementacao do metodo abstrato da Interface ListSelectionListener. Realiza eventos com origem na lista.
	 * @param event Evento detectado.
	 */
	@Override //acoes da lista
	public void valueChanged(ListSelectionEvent event) {
		int idx = listaExercicio.getSelectedIndex(); 
		this.index = idx;
		if(idx != -1) {
			if(exclusao == true) {
				listModel.removeElementAt(idx);
				rotina.removeExercicio(idx);
				exclusao = false;
				frameExclui.dispose();
				scroll.setBounds(190, 60, 200, 200);
				frameRotina.add(scroll);
			}
			if(edicao == true) {
				frameExclui.dispose();
				frameRotina.add(scroll);
				textoAdiciona.setText("Escolha o novo nome do seu Exercicio:");
				frameAdiciona.setVisible(true);
			}
		}
	}
	/**
	 * Metodo responsavel por alterar o texto do JLabel texto
	 * @param str Texto a ser introduzido no JLabel.
	 */
	public void setLabel(String str) {
		this.texto.setText(str);
	}
}