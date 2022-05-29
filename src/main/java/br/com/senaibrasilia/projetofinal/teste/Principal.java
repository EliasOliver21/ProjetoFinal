package br.com.senaibrasilia.projetofinal.teste;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.senaibrasilia.projeotfinal.viewswing.viewswing;
import br.com.senaibrasilia.projetofinal.dao.CategoriaDao;
import br.com.senaibrasilia.projetofinal.dao.ProdutoDao;
import br.com.senaibrasilia.projetofinal.model.Categoria;
import br.com.senaibrasilia.projetofinal.model.Produto;
import br.com.senaibrasilia.projetofinal.util.JPAUtil;

public class Principal {
	
	
 
 
    public static void main(String[] args) {
    	
    /*	
       cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
       
        Produto p = produtoDao.buscarPorId(1l);
        System.out.println(p.getPreco());
       
        List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("Notebooks");
        todos.forEach(p2 -> System.out.println(p.getNome()));
        
        System.out.println("CHUPA PYTHON!!!");
        */
   
        //List<Produto> precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
       // System.out.println("Preco do Produto: " +precoDoProduto);
    	
    	new viewswing();
    }
 
}
