package asw.dbManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import asw.dbManagement.model.Commentary;

public interface CommentaryRepository extends JpaRepository<Commentary, Long>{

	public Commentary findByIdentificador(String identificador);
}
