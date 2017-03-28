package asw.dbManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import asw.dbManagement.model.Suggestion;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long>{
	
	public Suggestion findByIdentificador(String identificador);

}
