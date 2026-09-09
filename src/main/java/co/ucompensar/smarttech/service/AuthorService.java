package co.ucompensar.smarttech.service;

import co.ucompensar.smarttech.entity.Author;
import co.ucompensar.smarttech.repository.AuthorRepository;
import co.ucompensar.smarttech.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository repository;
    private final CommentRepository commentRepository;

    public AuthorService(
            AuthorRepository repository,
            CommentRepository commentRepository) {

        this.repository = repository;
        this.commentRepository = commentRepository;
    }

    public List<Author> findAll() {
        return repository.findAll();
    }

    public Author findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Autor no encontrado: " + id));
    }

    public Author findOrCreate(String name) {

        String cleanName = name == null ? "" : name.trim();

        if (cleanName.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre del autor no puede estar vacío.");
        }

        return repository.findByNameIgnoreCase(cleanName)
                .orElseGet(() -> {
                    Author author = new Author();
                    author.setName(cleanName);
                    return repository.save(author);
                });
    }

    public Author save(Author author) {
        return repository.save(author);
    }

    public void delete(Long id) {

        if (commentRepository.existsByAuthorId(id)) {
            throw new IllegalStateException(
                    "No se puede eliminar el autor porque tiene comentarios asociados.");
        }

        repository.deleteById(id);
    }
}