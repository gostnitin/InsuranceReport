package nitin.tech.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nitin.tech.entity.CitizenPlan;

public interface CitizenplanRepository extends JpaRepository<CitizenPlan, Integer>{
	
	@Query("select distinct(planName) from CitizenPlan")
	public List<String> getPlanName();
	
	@Query("select distinct(planStatus) from CitizenPlan")
	public List<String> getPlanStatus();

}
