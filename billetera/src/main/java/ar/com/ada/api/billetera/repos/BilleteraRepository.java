package ar.com.ada.api.billetera.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.ada.api.billetera.entities.Billetera;

public interface BilleteraRepository extends JpaRepository<Billetera, Integer> {
    Billetera findByBilleteraId(Integer id);
}