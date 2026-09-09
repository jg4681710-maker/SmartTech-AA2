package co.ucompensar.smarttech.controller;

import co.ucompensar.smarttech.entity.Comment;
import co.ucompensar.smarttech.service.AuthorService;
import co.ucompensar.smarttech.service.CommentService;
import co.ucompensar.smarttech.service.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/comments")
public class CommentController {

    private final CommentService service;
    private final DeviceService deviceService;
    private final AuthorService authorService;

    public CommentController(
            CommentService service,
            DeviceService deviceService,
            AuthorService authorService) {

        this.service = service;
        this.deviceService = deviceService;
        this.authorService = authorService;
    }

    @GetMapping
    public String list(Model model) {

        model.addAttribute(
                "comments",
                service.findAll()
        );

        return "admin-comments";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "comment",
                service.findById(id)
        );

        model.addAttribute(
                "devices",
                deviceService.search(null, null, null)
        );

        model.addAttribute(
                "authors",
                authorService.findAll()
        );

        return "comment-form";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute Comment comment) {

        Comment existingComment =
                service.findById(comment.getId());

        existingComment.setAuthor(
                authorService.findById(
                        comment.getAuthor().getId()
                )
        );

        existingComment.setContent(
                comment.getContent()
        );

        existingComment.setRating(
                comment.getRating()
        );

        if (comment.getDevice() != null
                && comment.getDevice().getId() != null) {

            existingComment.setDevice(
                    deviceService.findById(
                            comment.getDevice().getId()
                    )
            );
        }

        service.save(existingComment);

        return "redirect:/admin/comments";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        service.delete(id);

        return "redirect:/admin/comments";
    }
}