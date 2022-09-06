/**
 * Pacote da interface grafica
 * @author Lucas Soares Rodrigues
 */
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
	private JFrame frameRotina, frameRetorno, frameAdiciona, frameMaquina, frameCardio, frameExclui;
	private DefaultListModel<String> listModel;
	private JList<String> listaExercicio; //Lista de exibicao dos exercicios
	private JScrollPane scroll;
	private JTextField campoNome, valorDistCardio, valorPesoMaquina, valorSerieMaquina, valorRepeticaoMaquina;
	private JButton bInicia, bExclui, bAdiciona, bVolta, bConfirma, bEdita;
	private JLabel texto, distCardio, pesoMaquina, serieMaquina, repeticaoMaquina, textoExclui, textoAdiciona, textoFiltro;
	private Rotina rotina;
	private TelaHistorico telaHist;
	private JRadioButton opMaquina, opCardio, filtroCardio, filtroMaquina, filtroTodos;
	private ButtonGroup radioGroup, grupoFiltro;
	private String tipo, aux, aux2, aux3;
	private int check = 0, index;
	private String filtro = "Todos";
	 // exclusao e edicao sao variaveis do tipo boolean. Sao responsaveis para diferenciar a intencao do usuario ao interagir com a lista.
	private boolean exclusao = false, edicao = false;
	/**
	 * Metodo construtor da Classe TelaRotina. Cria todos os elementos visuais da Classe.
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
		textoFiltro = new JLabel("Filtar:");
		filtroTodos = new JRadioButton("Todos", true);
		filtroCardio = new JRadioButton("Cardio");
		filtroMaquina = new JRadioButton("Maquina");
		grupoFiltro = new ButtonGroup();
		
		//Passando exercicios registrados na Classe Dado
		switch (nome) {
		case "Superiores":
			for(int j = 0; j <= 3 ; j++) {
				listModel.addElement(dados.getRotina(0).getExercicio(j).getNome());
			}
			break;
		case "Inferiores":
			for(int j = 0; j <= 4 ; j++) {
				listModel.addElement(dados.getRotina(1).getExercicio(j).getNome());
			}
			break;
		case "Projeto verao":
			for(int j = 0; j <= 5 ; j++) {
				listModel.addElement(dados.getRotina(2).getExercicio(j).getNome());
			}
			break;
		}
		
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) listaExercicio.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		scroll = new JScrollPane(listaExercicio); 
		scroll.setBounds(190, 60, 200, 200);
		texto.setBounds(235, 20, 200, 20);
		//Configurando os botoes de filtragem
		filtroTodos.addItemListener(this);
		filtroCardio.addItemListener(this);
		filtroMaquina.addItemListener(this);
		textoFiltro.setBounds(400, 60, 150, 20);
		filtroTodos.setBounds(400, 80, 150, 20);
		filtroCardio.setBounds(400, 100, 150, 20);
		filtroMaquina.setBounds(400, 120, 150, 20);
		grupoFiltro.add(filtroTodos);
		grupoFiltro.add(filtroCardio);
		grupoFiltro.add(filtroMaquina);

		
		//Configurando os botoes da TelaRotina
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
		frameRotina.add(textoFiltro);
		frameRotina.add(filtroTodos);
		frameRotina.add(filtroCardio);
		frameRotina.add(filtroMaquina);
		
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
		//Configurando os JFrame de adicionar exercicio. Exercicios Cardio tem uma tela de adicao diferente de Exercicios Maquina
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
		
		if(src == bInicia) { //Caso o botao de iniciar Rotina seja clicado
			new TelaInicia(rotina, telaHist, filtro);
		}
		
		if(src == bAdiciona) { //Caso o botao de adicionar Exercicio seja clicado
			textoAdiciona.setText("Escolha o nome do seu Exercicio:");
			frameAdiciona.setVisible(true);
		}
		
		if(src == bExclui) { //Caso o botao de excluir Exercicio seja clicado
			exclusao = true;
			textoExclui.setText("Selecione um Exercicio para excluir:");
			frameExclui.setTitle("Excluir Exercicio");
			scroll.setBounds(190, 60, 200, 200);
			frameExclui.add(scroll);
			frameExclui.setVisible(true);
		}
		
		if(src == bEdita) { //Caso o botao de editar Exercicio seja clicado
			edicao = true;
			textoExclui.setText("Selecione um Exercicio para editar:");
			scroll.setBounds(190, 60, 200, 200);
			frameExclui.setTitle("Editar Exercicio");
			frameExclui.add(scroll);
			frameExclui.setVisible(true);
		}
		
		if (src == bVolta) { //Caso o botao de voltar para tela principal seja clicado
			frameRetorno.setVisible(true);
			frameRotina.setVisible(false);
		}
		
		if (event.getActionCommand().equals("Confirmar")) { //Caso o botao de confirmar seja clicado. Esse botao esta presente em diversos contextos
			if(edicao == false) {
				if(campoNome.getText() != "") {	
					if(check == 0) { //Botao confirmar foi clicado no contexto de adicionar Exercicio
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
					}else if(check == 1) { //Botao confirmar foi clicado no contexto de adicionar Exercicio do tipo Cardio
						aux = valorDistCardio.getText();
						rotina.getExercicio(rotina.getQtdExercicio()-1).setDistancia(Double.parseDouble(aux));
						check = 0;
						frameCardio.dispose();
						frameAdiciona.add(bConfirma);
					}else if(check == 2) { //Botao confirmar foi clicado no contexto de adicionar Exercicio do tipo Maquina
						aux = valorPesoMaquina.getText();
						aux2 = valorRepeticaoMaquina.getText();
						aux3 = valorSerieMaquina.getText();
						rotina.getExercicio(rotina.getQtdExercicio()-1).setMaquina(Double.parseDouble(aux), Integer.parseInt(aux2), Integer.parseInt(aux3));
						check = 0;
						frameMaquina.dispose();
						frameAdiciona.add(bConfirma);	
					}
				}
			} else { //Botao confirmar foi clicado no contexto de editar Exercicio
				if(check == 0) { //Dentro desse if, o Exercicio escolhido e anulado e substituido
					rotina.editaExercicio(index, campoNome.getText(), tipo);
					listModel.setElementAt(rotina.getExercicio(index).getNome(), index);
					frameExclui.dispose();
					frameAdiciona.dispose();
					scroll.setBounds(190, 60, 200, 200);
					frameRotina.add(scroll);
					if(tipo == "cardio") { //Caso o Exercicio que esta substituindo seja do tipo Cardio
						check = 3;
						frameCardio.add(bConfirma);
						frameCardio.setVisible(true);
					}
					if(tipo == "maquina") { //Caso o Exercicio que esta substituindo seja do tipo Maquina
						check = 4;
						frameMaquina.add(bConfirma);
						frameMaquina.setVisible(true);
					}
				}else if(check == 3) { //Botao confirmar foi clicado no contexto de editar Exercicio do tipo Cardio
					check = 0;
					aux = valorDistCardio.getText();
					rotina.getExercicio(index).setDistancia(Double.parseDouble(aux));
					frameCardio.dispose();
					frameAdiciona.add(bConfirma);
					edicao = false;
				}else if(check == 4) { //Botao confirmar foi clicado no contexto de editar Exercicio do tipo Maquina
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
		   }
		   if(opMaquina.isSelected()) {
			   tipo = "maquina";
		   }
		   if(filtroTodos.isSelected()) {
			   listModel.removeAllElements();
			   for(int i = 0; i < rotina.getQtdExercicio(); i++) {
				   listModel.addElement(rotina.getExercicio(i).getNome());
			   }
			   filtro = "Todos";
		   }
		   if(filtroCardio.isSelected()) {
			   listModel.removeAllElements();
			   for(int i = 0; i < rotina.getQtdExercicio(); i++) {
				   if(rotina.getExercicio(i).getTipo() == "Cardio") {
					   listModel.addElement(rotina.getExercicio(i).getNome());
				   }
			   }
			   filtro = "Cardio";
		   }
		   if(filtroMaquina.isSelected()){
			   listModel.removeAllElements();
			   for(int i = 0; i < rotina.getQtdExercicio(); i++) {
				   if(rotina.getExercicio(i).getTipo() == "Maquina") {
					   listModel.addElement(rotina.getExercicio(i).getNome());
				   }
			   }
			   filtro = "Maquina";
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
		if(idx != -1) {
			this.index = idx;
			if(exclusao == true) { //Caso o usuario interaja com a lista na intencao de excluir um item
				listModel.removeElementAt(index);
				rotina.removeExercicio(index);
				exclusao = false;
				frameExclui.dispose();
				scroll.setBounds(190, 60, 200, 200);
				frameRotina.add(scroll);
			}
			if(edicao == true) { //Caso o usuario interaja com a lista na intencao de editar um item
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