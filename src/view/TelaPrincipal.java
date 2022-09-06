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
	JFrame framePrincipal, frameAdiciona, frameExclui;
	JList<String> listaRotina;
	JScrollPane scroll;
	JButton bAdiciona, bExclui, bHist, bEdita, bConfirma;
	JLabel texto, textoExclui, textoAdiciona;
	int numRotina = 3, index;
	JTextField campoNome;
	DefaultListModel<String> listModel;
	Rotina rotinas[] = new Rotina[5];
	Dado dados = new Dado();
	TelaRotina telaRotina[] = new TelaRotina[5];
	TelaHistorico telaHist;
	boolean exclusao = false, edicao = false;
	
	/**
	 * Metodo construtor da Classe TelaPrincipal. Cria todos os elementos vizuais da Classe.
	 */
	public TelaPrincipal() {
		framePrincipal = new JFrame();
		frameAdiciona = new JFrame();
		listModel = new DefaultListModel<String>();
		bAdiciona = new JButton("Adicionar Rotina");
		bExclui = new JButton("Excluir Rotina");
		bEdita = new JButton("Editar Rotina");
		bHist = new JButton("Historico");
		texto = new JLabel("Rotina");
		listaRotina = new JList<String>(listModel);
		scroll = new JScrollPane(listaRotina); 
		bConfirma = new JButton("Confirmar");
		textoAdiciona = new JLabel();
		telaHist = new TelaHistorico(framePrincipal);
		
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
		
		bAdiciona.addActionListener(this);
		bAdiciona.setBounds(10, 60, 170, 25);
		bExclui.addActionListener(this);
		bExclui.setBounds(10, 90, 170, 25);
		bEdita.addActionListener(this);
		bEdita.setBounds(10, 120, 170, 25);
		bHist.addActionListener(this);
		bHist.setBounds(10, 235, 170, 25);
		
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
		
		campoNome = new JTextField(20);	
		campoNome.setActionCommand("myTF"); 
		campoNome.addActionListener(this);
		
		bConfirma.addActionListener(this);
		
		frameAdiciona.add(textoAdiciona);
		frameAdiciona.add(campoNome);
		frameAdiciona.add(bConfirma);
		frameAdiciona.setLocationRelativeTo(null);
		frameAdiciona.setSize(300, 120);
		
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
		// Get the index of the changed item. 
		int idx = listaRotina.getSelectedIndex(); 
		this.index = idx;

		// Mostra o item, caso algum seja selecionada 
		if(idx != -1) {
			if(exclusao == false && edicao == false) {
				framePrincipal.setVisible(false);
				telaRotina[idx].setVisible();
			} else if(exclusao == true) {
				removeRotina(idx);
				exclusao = false;
				frameExclui.dispose();
				for(int i = idx; i < numRotina; i++) {
					telaRotina[i] = telaRotina[i+1];
				}
				scroll.setBounds(190, 60, 200, 200);
				framePrincipal.add(scroll);
			} else if(edicao == true) {
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
		
		if(src == bAdiciona) { 
			textoAdiciona.setText("Escolha o nome da sua Rotina:");
			frameAdiciona.setTitle("Adicionar Rotina");
			frameAdiciona.setVisible(true);
		}
		
		if(src == bExclui) { 
			exclusao = true;
			textoExclui.setText("Selecione uma Rotina para excluir:");
			scroll.setBounds(190, 60, 200, 200);
			frameExclui.add(scroll);
			frameExclui.setVisible(true);
		}
		
		if(src == bEdita) {
			edicao = true;
			textoExclui.setText("Selecione uma Rotina para editar:");
			scroll.setBounds(190, 60, 200, 200);
			frameExclui.add(scroll);
			frameExclui.setVisible(true);
		}
		
		if(src == bHist) {
			framePrincipal.setVisible(false);
			telaHist.setVisible();
		}
		
		if(event.getActionCommand().equals("Confirmar")) {
			if(campoNome.getText() != "") {
				if(edicao == false) {
					rotinas[numRotina] = new Rotina();
					rotinas[numRotina].setNome(campoNome.getText());
					listModel.addElement(rotinas[numRotina].getNome());
					telaRotina[numRotina] = new TelaRotina(rotinas[numRotina], rotinas[numRotina].getNome(), framePrincipal, dados, telaHist);
					numRotina ++;
				} else {
					rotinas[index].setNome(campoNome.getText());
					listModel.setElementAt(rotinas[index].getNome(), index);
					frameExclui.dispose();
					scroll.setBounds(190, 60, 200, 200);
					framePrincipal.add(scroll);
					for(int i = 0; i < numRotina; i++) {
						telaRotina[i].setLabel(rotinas[i].getNome());
					}
					
					edicao = false;
				}
				campoNome.setText("");
				frameAdiciona.dispose();
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
