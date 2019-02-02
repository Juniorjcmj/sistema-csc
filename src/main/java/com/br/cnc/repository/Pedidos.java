package com.br.cnc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.cnc.model.Pedido;

@Repository
public interface Pedidos extends JpaRepository<Pedido, Integer> {

	@Query(value = "select * from pedido p inner join pedido_produtos pp on p.id = pp.pedido_id  where pp.produtos_id =?1", nativeQuery=true)
	List<Pedido> findByProdutos(Integer id);

}
