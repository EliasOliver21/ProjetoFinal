package br.com.senaibrasilia.projeotfinal.viewswing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.com.senaibrasilia.projetofinal.dao.CategoriaDao;
import br.com.senaibrasilia.projetofinal.dao.ClienteDao;
import br.com.senaibrasilia.projetofinal.dao.ProdutoDao;
import br.com.senaibrasilia.projetofinal.model.Categoria;
import br.com.senaibrasilia.projetofinal.model.Cliente;
import br.com.senaibrasilia.projetofinal.model.Produto;
import br.com.senaibrasilia.projetofinal.util.JPAUtil;

//Passo 3-Verificar se implementou ActionListener

public class viewswing implements ActionListener {

	private JTextField txtCategoria;
	private JTextField txtCategoriaId;
	private JTextField txtCliente;
	private JTextField txtClienteId;
	private JTextField txtProduto;
	private JTextField txtProdutoId;
	private JTextField txtEscolha;

	private JLabel lblCategoria = new JLabel("Categoria:");
	private JLabel lblCategoriaId = new JLabel("       Categoria Id:");
	private JLabel lblCliente = new JLabel("Cliente:");
	private JLabel lblClienteId = new JLabel("       Cliente Id:");
	private JLabel lblProduto = new JLabel("Produto:");
	private JLabel lblProdutoId = new JLabel("       Produto Id:");
	private JLabel lblEscolha = new JLabel("Digite para fazer a busca: P Para produto, C Para Categoria e CL para Cliente.");

	private JButton btnCadastrar = new JButton("Cadastrar");
	private JButton btnRemover = new JButton("Remover");
	private JButton btnAtualizar = new JButton("Atualizar");
	private JButton btnBuscarId = new JButton("Buscar Id");
	private JButton btnBuscarNome = new JButton("Buscar por Nome");
	private JButton btnBuscarTodos = new JButton("Buscar Todos");
	private JButton btnBuscarNomeCategoria = new JButton("Buscar por categoria");
	private JButton btnLimpar = new JButton("Limpar");

	EntityManager em = JPAUtil.getEntityManager();

	public viewswing() {

		JFrame frmCategoria = new JFrame("Escola Java- SENAI Brasília");

		txtCategoria = new JTextField(18);
		txtCategoriaId = new JTextField(7);
		txtCliente = new JTextField(20);
		txtClienteId = new JTextField(7);
		txtProduto = new JTextField(20);
		txtProdutoId = new JTextField(7);
		txtEscolha = new JTextField(5);

		frmCategoria.setLayout(new FlowLayout());

		frmCategoria.setSize(600, 250);

		btnCadastrar.setActionCommand("cadastrar");
		btnRemover.setActionCommand("remover");
		btnAtualizar.setActionCommand("atualizar");
		btnBuscarId.setActionCommand("buscarId");
		btnBuscarNome.setActionCommand("buscarPorNome");
		btnBuscarTodos.setActionCommand("buscarTodos");
		btnBuscarNomeCategoria.setActionCommand("buscarPorNomeCategoria");
		btnLimpar.setActionCommand("limpar");

		btnCadastrar.addActionListener(this);
		btnRemover.addActionListener(this);
		btnAtualizar.addActionListener(this);
		btnBuscarId.addActionListener(this);
		btnBuscarNome.addActionListener(this);
		btnBuscarTodos.addActionListener(this);
		btnBuscarNomeCategoria.addActionListener(this);
		btnLimpar.addActionListener(this);

		frmCategoria.add(lblEscolha);
		frmCategoria.add(txtEscolha);

		frmCategoria.add(lblClienteId);
		frmCategoria.add(txtClienteId);
		frmCategoria.add(lblCategoriaId);
		frmCategoria.add(txtCategoriaId);
		frmCategoria.add(lblProdutoId);
		frmCategoria.add(txtProdutoId);
		frmCategoria.add(lblCategoria);
		frmCategoria.add(txtCategoria);
		frmCategoria.add(lblProduto);
		frmCategoria.add(txtProduto);
		frmCategoria.add(lblCliente);
		frmCategoria.add(txtCliente);
		frmCategoria.add(btnCadastrar);
		frmCategoria.add(btnAtualizar);
		frmCategoria.add(btnBuscarId);
		frmCategoria.add(btnRemover);
		frmCategoria.add(btnLimpar);
		frmCategoria.add(btnBuscarNomeCategoria);

		frmCategoria.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frmCategoria.setLocationRelativeTo(null);

		frmCategoria.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Categoria c = new Categoria();
		Produto p = new Produto();
		Cliente cl = new Cliente();

		if (e.getActionCommand().equals("cadastrar")) {

			CategoriaDao cDao = new CategoriaDao(em);
			ProdutoDao pDao = new ProdutoDao(em);
			ClienteDao clDao = new ClienteDao(em);
			String escolha = txtEscolha.getText();
			
			em.getTransaction().begin();
			
			if (escolha.equalsIgnoreCase("P")) {
				
				int idp = Integer.parseInt(txtProdutoId.getText());
				int idc = Integer.parseInt(txtCategoriaId.getText());
				int idcl = Integer.parseInt(txtClienteId.getText());
			
				//PRODUTO
				p.setNome(txtProduto.getText());
				pDao.cadastrar(p);
				c.setId(idc);
				cl.setId(idcl);
				p.setCategoria(c);
				p.setCliente(cl);
				
				
			}else if (escolha.equalsIgnoreCase("C")) {
				
				//CATEGORIA
				c.setNome(txtCategoria.getText());
				cDao.cadastrar(c);
				
			}else if (escolha.equalsIgnoreCase("CL")) {
				
				//CLIENTE
				cl.setNome(txtCliente.getText());
				clDao.cadastrar(cl);
				
			}
			
			em.getTransaction().commit();
			txtCategoria.setText("");
			txtProduto.setText("");
			txtCliente.setText("");
			em.clear();
			
		} else if (e.getActionCommand().equals("buscarId")) {

			String escolha = txtEscolha.getText();

			// CATEGORIA
			if (escolha.equalsIgnoreCase("C")) {
				CategoriaDao cDao = new CategoriaDao(em);
				int id = Integer.parseInt(txtCategoriaId.getText());
				c = cDao.buscarPorId(id);
				if (c == null) {
					txtCategoria.setText("Id nulo digite outro id.");
				} else {
					txtCategoria.setText(c.getNome());
				}
				// PRODUTO
			} else if (escolha.equalsIgnoreCase("P")) {

				ProdutoDao pDao = new ProdutoDao(em);
				int id1 = Integer.parseInt(txtProdutoId.getText());
				p = pDao.buscarPorId(id1);
				if (p == null) {
					txtProduto.setText("Id nulo digite outro id.");
				} else {
					txtProduto.setText(p.getNome());
				}

			} else if (escolha.equalsIgnoreCase("CL")) {

				ClienteDao clDao = new ClienteDao(em);
				int id2 = Integer.parseInt(txtClienteId.getText());
				cl = clDao.buscarPorId(id2);
				if (cl == null) {
					txtCliente.setText("Id nulo digite outro id.");
				} else {
					txtCliente.setText(cl.getNome());
				}

			}
			

		} else if (e.getActionCommand().equals("remover")) {
			
			c.setId(0);
			p.setId(0);
			cl.setId(0);

			CategoriaDao cDao = new CategoriaDao(em);
			ProdutoDao pDao = new ProdutoDao(em);
			ClienteDao clDao = new ClienteDao(em);
			String escolha = txtEscolha.getText();
			
			em.getTransaction().begin();
			
			if (escolha.equalsIgnoreCase("P")) {
				
				int id = Integer.parseInt(txtProdutoId.getText());
				p = pDao.buscarPorId(id);
				pDao.remover(p);
				
			}else if (escolha.equalsIgnoreCase("C")) {
				
				int id = Integer.parseInt(txtCategoriaId.getText());
				c = cDao.buscarPorId(id);
				cDao.remover(c);
				
			}else if (escolha.equalsIgnoreCase("CL")) {
				
				int id = Integer.parseInt(txtClienteId.getText());
				cl = clDao.buscarPorId(id);
				clDao.remover(cl);
				
			}
			
			em.getTransaction().commit();
			txtCategoria.setText("");
			txtProduto.setText("");
			txtCliente.setText("");
			em.clear();

		}else if (e.getActionCommand().equals("atualizar")) {
			
			c.setId(0);
			p.setId(0);
			cl.setId(0);

			CategoriaDao cDao = new CategoriaDao(em);
			ProdutoDao pDao = new ProdutoDao(em);
			ClienteDao clDao = new ClienteDao(em);
			String escolha = txtEscolha.getText();
			
			em.getTransaction().begin();
			
			if (escolha.equalsIgnoreCase("P")) {
				
				int id = Integer.parseInt(txtProdutoId.getText());
				p = pDao.buscarPorId(id);
				p.setId(id);
				p.setNome(txtProduto.getName());
				pDao.atualizar(p);
				
			}else if (escolha.equalsIgnoreCase("C")) {
				
				int id = Integer.parseInt(txtCategoriaId.getText());
				c = cDao.buscarPorId(id);
				c.setId(id);
				c.setNome(txtCategoria.getText());
				cDao.atualizar(c);
				
			}else if (escolha.equalsIgnoreCase("CL")) {
				
				int id = Integer.parseInt(txtClienteId.getText());
				cl = clDao.buscarPorId(id);
				cl.setId(id);
				cl.setNome(txtCliente.getText());
				clDao.atualizar(cl);
				
			}
			
			em.getTransaction().commit();
			txtCategoria.setText("");
			txtProduto.setText("");
			txtCliente.setText("");
			em.clear();
			
		
		} else if(e.getActionCommand().equals("limpar")) {
			
			txtCategoria.setText("");
			txtCategoriaId.setText("");
			txtCliente.setText("");
			txtClienteId.setText("");
			txtProduto.setText("");
			txtProdutoId.setText("");
			txtEscolha.setText("");
		
		}else if (e.getActionCommand().equals("buscarPorNomeCategoria")) {
			
			String escolha = txtEscolha.getText();

			// CATEGORIA
			if (escolha.equalsIgnoreCase("C")) {
				CategoriaDao cDao = new CategoriaDao(em);
				int id = Integer.parseInt(txtCategoriaId.getText());
				c = cDao.buscarPorId(id);
				if (c == null) {
					txtCategoria.setText("Id nulo digite outro id.");
				} else {
					txtCategoria.setText(c.getNome());
				}
				// PRODUTO
			} else if (escolha.equalsIgnoreCase("P")) {

				ProdutoDao pDao = new ProdutoDao(em);
				List <Produto> p1 = pDao.buscarPorNomeDaCategoria(txtCategoria.getText());
				
				if (p1 == null) {
					txtCategoria.setText("Id nulo digite outro id.");
				} else {
					System.out.println(p1.toString());
				}

			} else if (escolha.equalsIgnoreCase("CL")) {

				ClienteDao clDao = new ClienteDao(em);
				int id2 = Integer.parseInt(txtClienteId.getText());
				cl = clDao.buscarPorId(id2);
				if (cl == null) {
					txtCliente.setText("Id nulo digite outro id.");
				} else {
					txtCliente.setText(cl.getNome());
				}

			}
			
		}
		
	}

}
