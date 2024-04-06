package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.Laptop;
import uz.pdp.repository.LaptopRepository;
import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
    static List<Laptop> allLaptops;

    @Autowired
    LaptopRepository laptopRepository;

    @RequestMapping(value = "/laptop", method = RequestMethod.GET)
    public List<Laptop> getLaptop() {
        allLaptops = laptopRepository.findAll();
        return allLaptops;
    }

    @RequestMapping(value = "/laptop", method = RequestMethod.POST)
    public String addLaptop(@RequestBody Laptop laptop) {
        boolean isThere = false;
        for (Laptop currentLaptop : allLaptops) {
            if (currentLaptop.getMacAddress().equals(laptop.getMacAddress())) {
                isThere = true;
                break;
            }
        }
        if (isThere) {
            return "Laptop not saved, please enter another MacAddress";
        } else {
            laptopRepository.save(laptop);
            return "Laptop added";
        }
    }

    @RequestMapping(value = "/laptop/{id}", method = RequestMethod.PUT)
    public String editLaptop(@PathVariable Integer id, @RequestBody Laptop laptop)
    {
        boolean isThere = false;
        for (Laptop currentLaptop : allLaptops) {

            if (currentLaptop.getMacAddress().equals(laptop.getMacAddress())&& !currentLaptop.getId().equals(id)){
                isThere = true;
                break;  }
        }
            if (isThere) {
                return "Laptop not edited, please another MacAddress";
            } else {
                Optional<Laptop> optionalLaptop = laptopRepository.findById(id);
                if (optionalLaptop.isPresent()) {
                    Laptop editingLaptop = optionalLaptop.get();
                    editingLaptop.setName(laptop.getName());
                    editingLaptop.setBrandName(laptop.getBrandName());
                    editingLaptop.setModel(laptop.getModel());
                    editingLaptop.setRam(laptop.getRam());
                    editingLaptop.setMacAddress(laptop.getMacAddress());
                    editingLaptop.setStorage(laptop.getStorage());
                    laptopRepository.save(editingLaptop);
                    return "Laptop edited";
                }
                return "Laptop not found";
            }
        }

    @RequestMapping(value = "/laptop/{id}",method = RequestMethod.DELETE)
    public String deleteLaptop(@PathVariable Integer id){
        laptopRepository.deleteById(id);
        return "Laptop deleted";
    }


}
