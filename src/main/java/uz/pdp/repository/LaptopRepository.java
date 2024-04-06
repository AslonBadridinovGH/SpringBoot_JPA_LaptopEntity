package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.model.Laptop;

@Repository
public interface LaptopRepository extends JpaRepository <Laptop,Integer>{
}
