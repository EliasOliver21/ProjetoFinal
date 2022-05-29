package br.com.senaibrasilia.projetofinal.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.senaibrasilia.projetofinal.model.Categoria;
import br.com.senaibrasilia.projetofinal.model.Cliente;
import br.com.senaibrasilia.projetofinal.model.Produto;

public class ClienteDao {
	
	private EntityManager em;
	
	public ClienteDao(EntityManager em) {
		this.em = em;
	}
		
	public void cadastrar(Cliente cliente) {
		this.em.persist(cliente);
		//persist	
	}
	public void atualizar(Cliente cliente) {
		this.em.merge(cliente);
		//merge	
	}
	public void remover(Cliente cliente) {
		cliente = em.merge(cliente);
		this.em.remove(cliente);
	}
	public Cliente buscarPorId(long id) {
		return this.em.find(Cliente.class, id);
		//find
	}
	public Cliente pesquisarPorNome(String nome) {
		return this.em.find(Cliente.class, nome);
		//?
	}
	public List<Cliente> pesquisarTodos(String nome) {
		
		String jpql = "SELECT c FROM Categoria c";
		
		return em.createQuery(jpql,Cliente.class).setParameter("nome",nome).getResultList();
		//query - JPQL
		//ArrayList - List
		//Lambda - Java8
	}

}
