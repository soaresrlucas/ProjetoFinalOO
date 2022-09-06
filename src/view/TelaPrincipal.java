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
 * Classe TelaPrincipal e a principal interface grafica, exibindo a lista de Rotinas
 * @author Lucas Soares Rodigues 
 */
public class TelaPrincipal implements ActionListener, ListSelectionListener{
	private JFrame framePrincipal, frameAdiciona, frameExclui;
	private JList<String> listaRotina; //Lista de exibicao das rotinas
	private JScrollPane scroll;
	private DefaultListModel<String> listModel;
	private JButton bAdiciona, bExclui, bHist, bEdita, bConfirma;
	private JLabel texto, textoExclui, textoAdiciona;
	private int numRotina = 3, index; //numRotina e inicializada com 3 devido aos dados pre-carregados da Classe Dado
	private JTextField campoNome;
	private Rotina rotinas[] = new Rotina[5]; //Vetor que armazena as Rotinas
	private Dado dados = new Dado();
	private TelaRotina telaRotina[] = new TelaRotina[5]; //Vetor de telas de cada uma das rotinas
	private TelaHistorico telaHist; //Tela de exibicao do historico
	private boolean exclusao = false, edicao = false; //Define a intencao do usuario ao interagir com a lista. Se o usuario esta excluindo, editando ou nenhum dos dois
	
	/**
	 * Metodo construtor da Classe TelaPrincipal. Cria todos os elementos visuais da Classe.
	 */
	public TelaPrincipal() {
		framePrincipal = new JFrame();
		frameAdiciona = new JFrame();
		listModel = new DefaultListModel<String>();
		listaRotina = new JList<String>(listModel);
		scroll = new JScrollPane(listaRotina); 
		bAdiciona = new JButton("Adicionar Rotina");
		bExclui = new JButton("Excluir Rotina");
		bEdita = new JButton("Editar Rotina");
		bHist = new JButton("Historico");
		texto = new JLabel("Rotina");
		bConfirma = new JButton("Confirmar");
		textoAdiciona = new JLabel();
		telaHist = new TelaHistorico(framePrincipal);
		//Passando os dados da Classe Dado
		for(int i = 0; i <= 2; i++) {
			rotinas[i] = dados.getRotina(i);
			listModel.addElement(rotinas[i].getNome());
			telaRotina[i] = new TelaRotina(rotinas[i], rotinas[i].getNome(), framePrincipal, dados, telaHist);
		}
		
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) listaRotina.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		listaRotina.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaRotina.addListSelectionListener(this);
		
		scroll.setBounds(190, 60, 200, 200);
		texto.setBounds(245, 20, 60, 20);
		//Configurando os botoes da TelaPrincipal
		bAdiciona.addActionListener(this);
		bAdiciona.setBounds(10, 60, 170, 25);
		bExclui.addActionListener(this);
		bExclui.setBounds(10, 90, 170, 25);
		bEdita.addActionListener(this);
		bEdita.setBounds(10, 120, 170, 25);
		bHist.addActionListener(this);
		bHist.setBounds(10, 235, 170, 25);
		//Adicionando elementos da TelaPrincipal
		framePrincipal.add(texto);
		framePrincipal.add(scroll);
		framePrincipal.add(bAdiciona);
		framePrincipal.add(bExclui);
		framePrincipal.add(bEdita);
		framePrincipal.add(bHist);		
		framePrincipal.setLayout(null);
		framePrincipal.setSize(500, 350);
		framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePrincipal.setVisible(true);
		framePrincipal.setLocationRelativeTo(null);
		
		frameAdiciona.setLayout(new FlowLayout());
		//Configurando o frameAdiciona, responsavel por adicionar uma nova Rotina
		campoNome = new JTextField(20);	
		campoNome.setActionCommand("myTF"); 
		campoNome.addActionListener(this);
		
		bConfirma.addActionListener(this);
		
		frameAdiciona.add(textoAdiciona);
		frameAdiciona.add(campoNome);
		frameAdiciona.add(bConfirma);
		frameAdiciona.setLocationRelativeTo(null);
		frameAdiciona.setSize(300, 120);
		//Configurando o frameExclui, responsavel por excluir uma Rotina
		frameExclui = new JFrame();
		textoExclui = new JLabel();
		frameExclui.setLayout(new FlowLayout());
		frameExclui.add(textoExclui);
		frameExclui.setSize(300, 300);
		frameExclui.setLocationRelativeTo(null);
	}
	/**
	 * Implementacao do metodo abstrato da Interface ListSelectionListener. Realiza eventos com origem na lista.
	 * @param event Evento detectado.
	 */
	public void valueChanged(ListSelectionEvent event) {  
		int idx = listaRotina.getSelectedIndex(); 
		this.index = idx;
		if(idx != -1) {
			if(exclusao == false && edicao == false) { //Caso o usuario nao esteja nem excluindo nem editando a lista
				framePrincipal.setVisible(false);
				telaRotina[idx].setVisible();
			} else if(exclusao == true) { //Caso o usuario esteja excluindo algum item da lista
				removeRotina(idx);
				exclusao = false;
				frameExclui.dispose();
				for(int i = idx; i < numRotina; i++) {
					telaRotina[i] = telaRotina[i+1];
				}
				scroll.setBounds(190, 60, 200, 200);
				framePrincipal.add(scroll);
			} else if(edicao == true) { //Caso o usuario esteja editando algum item da lista
				textoAdiciona.setText("Escolha o novo nome da sua Rotina:");
				frameAdiciona.setTitle("Editar Rotina");
				frameAdiciona.setVisible(true);
			}
		}
		else
			texto.setText("Rotina"); 
	}
	/**
	 * Implementacao do metodo abstrato da Interface ActionListener. Realiza eventos com origem nos botoes.
	 * @param event Evento detectado.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		Object src = event.getSource();
		
		if(src == bAdiciona) { //Caso o botao de adicionar Rotina seja clicado
			textoAdiciona.setText("Escolha o nome da sua Rotina:");
			frameAdiciona.setTitle("Adicionar Rotina");
			frameAdiciona.setVisible(true);
		}
		
		if(src == bExclui) { //Caso o botao de excluir Rotina seja clicado
			exclusao = true;
			textoExclui.setText("Selecione uma Rotina para excluir:");
			scroll.setBounds(190, 60, 200, 200);
			frameExclui.add(scroll);
			frameExclui.setVisible(true);
		}
		
		if(src == bEdita) { //Caso o botao de editar Rotina seja clicado
			edicao = true;
			textoExclui.setText("Selecione uma Rotina para editar:");
			scroll.setBounds(190, 60, 200, 200);
			frameExclui.add(scroll);
			frameExclui.setVisible(true);
		}
		
		if(src == bHist) { //Caso o botao de acessar o historico seja clicado
			framePrincipal.setVisible(false);
			telaHist.setVisible();
		}
		
		if(event.getActionCommand().equals("Confirmar")) { //Caso o botao de confirmar seja clicado. Esse botao esta presente em diversos contextos
			if(campoNome.getText() != "") {
				if(edicao == false) { //Botao confirmar foi clicado no contexto de adicionar Rotina
					rotinas[numRotina] = new Rotina(); 
					rotinas[numRotina].setNome(campoNome.getText()); //Passando o nome da nova Rotina
					listModel.addElement(rotinas[numRotina].getNome()); //Atualizando a lista
					telaRotina[numRotina] = new TelaRotina(rotinas[numRotina], rotinas[numRotina].getNome(), framePrincipal, dados, telaHist);//Criando a telaRotina
					numRotina ++; //Acrescentando o contador de Rotinas
				} else { //Caso o usuario clique em confirmar no contexto de editar Rotina
					rotinas[index].setNome(campoNome.getText());
					listModel.setElementAt(rotinas[index].getNome(), index);//Atualiza visualmente a lista
					frameExclui.dispose();//Fecha o frameExclui, reutilizado na edicao
					scroll.setBounds(190, 60, 200, 200);
					framePrincipal.add(scroll);//Adiciona a lista de volta no framePrincipal
					for(int i = 0; i < numRotina; i++) {
						telaRotina[i].setLabel(rotinas[i].getNome()); //Atualiza o texto das telaRotina
					}
					
					edicao = false; //Retorna para false indicando que o usuario nao deseja mais editar
				}
				campoNome.setText(""); 
				frameAdiciona.dispose(); //Fecha o frame de adicionar Rotina, reutilizado para edicao
			}
		}
	}
	/**
	 * Metodo responsavel por remover um item da lista de rotinas.
	 * @param idx Posicao do elemento a ser removido.
	 */
	public void removeRotina(int idx) {
			for(int i = idx; i < (numRotina-1); i++) {
				rotinas[i] = rotinas[i + 1];
			}
			numRotina--;
			listModel.removeElementAt(idx);
	}
	
	public static void main(String[] args) {
		new TelaPrincipal();
	}
}