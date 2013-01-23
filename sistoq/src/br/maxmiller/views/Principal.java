package br.maxmiller.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;

public class Principal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 923, 538);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mnArquivo.add(mntmSair);
		
		JMenu mnEstoque = new JMenu("Estoque");
		menuBar.add(mnEstoque);
		
		JMenuItem mntmProduto = new JMenuItem("Produto");
		mnEstoque.add(mntmProduto);
		
		JMenuItem mntmSadaDeEstoque = new JMenuItem("Sa\u00EDda de Estoque");
		mnEstoque.add(mntmSadaDeEstoque);
		
		JMenuItem mntmEntradaDeEstoque = new JMenuItem("Entrada de estoque");
		mnEstoque.add(mntmEntradaDeEstoque);
		
		JMenu mnVendas = new JMenu("Vendas");
		menuBar.add(mnVendas);
		
		JMenuItem mntmFormaDePagamento = new JMenuItem("Forma de Pagamento");
		mnVendas.add(mntmFormaDePagamento);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mnVendas.add(mntmClientes);
		
		JMenuItem mntmVendas = new JMenuItem("Vendas");
		mnVendas.add(mntmVendas);
		
		JMenu mnRelatrios = new JMenu("Relat\u00F3rios");
		menuBar.add(mnRelatrios);
		
		JMenuItem mntmVendasPorCliente = new JMenuItem("Vendas por Cliente");
		mnRelatrios.add(mntmVendasPorCliente);
		
		JMenuItem mntmVendasPorForma = new JMenuItem("Vendas por forma de pagamento");
		mnRelatrios.add(mntmVendasPorForma);
		
		JMenuItem mntmVendasPorMs = new JMenuItem("Vendas por m\u00EAs");
		mnRelatrios.add(mntmVendasPorMs);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Sistoq", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
	}

}
