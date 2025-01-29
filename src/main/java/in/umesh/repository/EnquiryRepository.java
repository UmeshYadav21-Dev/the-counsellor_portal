package in.umesh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.umesh.entities.Enquriy;

public interface EnquiryRepository extends JpaRepository<Enquriy, Integer>{
	
	@Query(value = "select * from enquriy_tbl where counsellor_id = :counsellorId", nativeQuery = true)

	public List<Enquriy> getEnquiriesByCounsellorId(Integer counsellorId);

}
