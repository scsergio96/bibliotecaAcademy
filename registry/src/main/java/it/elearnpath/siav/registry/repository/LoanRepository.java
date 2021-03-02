package it.elearnpath.siav.registry.repository;

import it.elearnpath.siav.registry.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

    Loan save(Loan loan);

}
