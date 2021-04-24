package br.com.alura.mudi.repository;

import br.com.alura.mudi.model.Pedido;
import br.com.alura.mudi.model.StatusPedido;
import br.com.alura.mudi.model.Usuario;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByStatus(StatusPedido status);

    @Cacheable("pedidos")
    List<Pedido> findByStatus(StatusPedido status, Pageable paginacao);

    List<Pedido> findByUsuario(Usuario usuario);

    List<Pedido> findByUsuarioAndStatus(Usuario usuario, StatusPedido status);
}
