package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.bean.Filme;
import model.dao.FilmeDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFListarFilme extends JFrame {

	private JPanel contentPane;
	private JTable JTFilmes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFListarFilme frame = new JFListarFilme();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFListarFilme() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 467);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Listar Filmes");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(15, 16, 143, 20);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 52, 706, 289);
		contentPane.add(scrollPane);
		
		JTFilmes = new JTable();
		JTFilmes.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"IDFilme", "T\u00EDtulo", "Tempo em min", "Classifica\u00E7\u00E3o", "Categoria"
			}
		));
		JTFilmes.getColumnModel().getColumn(2).setPreferredWidth(127);
		JTFilmes.getColumnModel().getColumn(3).setPreferredWidth(109);
		JTFilmes.getColumnModel().getColumn(4).setPreferredWidth(85);
		scrollPane.setViewportView(JTFilmes);
		
		JButton btnCadastrar = new JButton("Cadastrar Filme");
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCadastrar.setBounds(15, 366, 173, 29);
		contentPane.add(btnCadastrar);
		
		JButton btnEditar = new JButton("Editar Filme");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// verificar se uma linha foi selecionada
				if(JTFilmes.getSelectedRow() != -1) {
					JFAtualizarFilme af = new JFAtualizarFilme(
					(int)JTFilmes.getValueAt(JTFilmes.getSelectedRow(), 0)); 
					// passo o id do filme que quero editar
					af.setVisible(true); // deixa a tela de edi��o vis�vel
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um filme!");
				}
				readJTable(); // d� um refresh na tabela com os dados atualizados
			}
		});
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEditar.setBounds(203, 366, 143, 29);
		contentPane.add(btnEditar);
		
		JButton btnNewButton_1 = new JButton("Excluir Filme");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(361, 366, 153, 29);
		contentPane.add(btnNewButton_1);
		
		readJTable();
	}
	
	public void readJTable() {
		DefaultTableModel modelo = (DefaultTableModel) JTFilmes.getModel(); // pega o modelo da tabela
		modelo.setNumRows(0); // n�mero inicial de linhas
		FilmeDAO fdao = new FilmeDAO();	
		for(Filme f : fdao.read()) {
			modelo.addRow(new Object[] {
					f.getIdFilme(),
					f.getTitulo(),
					f.getTempo(),
					//f.getImagem3d(),
					//f.getDublado(),
					f.getClassificacao(),
					//f.getStatus_filme(),
					f.getCategoria()
					
			});
		}
		
	}
	
}