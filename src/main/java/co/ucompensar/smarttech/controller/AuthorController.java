package co.ucompensar.smarttech.controller;

import co.ucompensar.smarttech.entity.Author;
import co.ucompensar.smarttech.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/authors")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("authors", service.findAll());
        return "admin-authors";
    }

    @GetMapping("/new")
    public String newAuthor(Model model) {
        model.addAttribute("author", new Author());
        return "author-form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("author", service.findById(id));
        return "author-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Author author) {
        service.save(author);
        return "redirect:/admin/authors";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        try {
            service.delete(id);
            return "redirect:/admin/authors";

        } catch (IllegalStateException e) {
            return "redirect:/admin/authors?error=used";
        }
    }
}