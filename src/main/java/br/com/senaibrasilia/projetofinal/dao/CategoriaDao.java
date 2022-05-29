
package br.com.senaibrasilia.projetofinal.dao;
import java.util.List;
import javax.persistence.EntityManager;
import br.com.senaibrasilia.projetofinal.model.Categoria;
import br.com.senaibrasilia.projetofinal.model.Produto;

public class CategoriaDao {
	
	private EntityManager em;
	
	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}
	public void atualizar(Categoria categoria) {
		this.em.merge(categoria);			
	}
	public void remover(Categoria categoria) {
		categoria = em.merge(categoria);
		this.em.remove(categoria);
	}
	public Categoria buscarPorId(long id) {
		return this.em.find(Categoria.class, id);
	}

}
