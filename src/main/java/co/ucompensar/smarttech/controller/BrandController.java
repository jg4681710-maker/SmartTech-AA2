package co.ucompensar.smarttech.controller;

import co.ucompensar.smarttech.entity.Brand;
import co.ucompensar.smarttech.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/brands")
public class BrandController {
    private final BrandService service;

    public BrandController(BrandService service) { this.service = service; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("brands", service.findAll());
        model.addAttribute("brand", new Brand());
        return "admin-brands";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Brand brand) {
        service.save(brand);
        return "redirect:/admin/brands";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin/brands";
    }
}
