package co.ucompensar.smarttech.service;

import co.ucompensar.smarttech.entity.Brand;
import co.ucompensar.smarttech.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    private final BrandRepository repository;

    public BrandService(BrandRepository repository) {
        this.repository = repository;
    }

    public List<Brand> findAll() { return repository.findAll(); }
    public Brand findById(Long id) { return repository.findById(id).orElseThrow(); }
    public Brand save(Brand brand) { return repository.save(brand); }
    public void delete(Long id) { repository.deleteById(id); }
}
