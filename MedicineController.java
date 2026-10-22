package com.janani.medicalshop.medicine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@CrossOrigin
public class MedicineController {
    private final MedicineRepository repository;
    public MedicineController(MedicineRepository repository) { this.repository = repository; }

    @GetMapping
    public List<Medicine> all() { return repository.findAll(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Medicine add(@RequestBody Medicine medicine) {
        if (medicine.getName() == null || medicine.getName().isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Medicine name is required");
        if (medicine.getStock() == null || medicine.getStock() < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock must be zero or greater");
        return repository.save(medicine);
    }

    @PutMapping("/{id}/stock")
    public Medicine reduceStock(@PathVariable Long id, @RequestParam int quantity) {
        if (quantity <= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity must be positive");
        Medicine medicine = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medicine not found"));
        if (medicine.getStock() == null || medicine.getStock() < quantity)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Insufficient stock");
        medicine.setStock(medicine.getStock() - quantity);
        return repository.save(medicine);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { repository.deleteById(id); }
}
