package br.com.senaibrasilia.projetofinal.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.senaibrasilia.projetofinal.model.Produto;

public class ProdutoDao {
	
	private EntityManager em;
	
	public ProdutoDao(EntityManager em) {
		this.em = em;
	}
	
	
	public EntityManager getEm() {
		return em;
	}


	public void setEm(EntityManager em) {
		this.em = em;
	}


	public void cadastrar(Produto produto) {
		this.em.persist(produto);
		
	}
	public void atualizar(Produto produto) {//0,5
		this.em.merge(produto);
	}
	public void remover(Produto produto) {
		produto = em.merge(produto);
		this.em.remove(produto);
	}
	public Produto buscarPorId(long id) {
		return this.em.find(Produto.class, id);
	}	
	public List<Produto> buscarTodos() {
		
		String jpql = "SELECT p FROM Produto p";
		
		return em.createQuery(jpql,Produto.class).getResultList();
		//query - JPQL
		//ArrayList - List
		//Lambda - Java8
	}
	public List<Produto> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		return em.createQuery(jpql,Produto.class)
				.setParameter("nome",nome)
				.getResultList();
	}
	public List<Produto> buscarPorNomeDaCategoria(String nome){
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
		return em.createQuery(jpql,Produto.class)
				.setParameter("nome",nome)
				.getResultList();
	}


	public List<Produto> buscarPrecoDoProdutoComNome(BigDecimal preco) {
		// TODO Auto-generated method stub
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.preco = :preco";
		return em.createQuery(jpql,Produto.class)
				.setParameter("preco",preco)
				.getResultList();
	}
}
