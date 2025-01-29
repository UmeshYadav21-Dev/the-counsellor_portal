package in.umesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.umesh.entities.Counsellor;

public interface CounsellorRepository extends JpaRepository<Counsellor, Integer>{
	
	public Counsellor findByEmail(String email);
	
	public Counsellor  findByEmailAndPassword(String email, String password);	

}
