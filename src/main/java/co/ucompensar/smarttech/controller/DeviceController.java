package co.ucompensar.smarttech.controller;

import co.ucompensar.smarttech.entity.Comment;
import co.ucompensar.smarttech.entity.Device;
import co.ucompensar.smarttech.service.AuthorService;
import co.ucompensar.smarttech.service.BrandService;
import co.ucompensar.smarttech.service.CommentService;
import co.ucompensar.smarttech.service.DeviceService;
import co.ucompensar.smarttech.service.DeviceTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeviceController {

    private final DeviceService deviceService;
    private final BrandService brandService;
    private final CommentService commentService;
    private final DeviceTypeService deviceTypeService;
    private final AuthorService authorService;

    public DeviceController(
            DeviceService deviceService,
            BrandService brandService,
            CommentService commentService,
            DeviceTypeService deviceTypeService,
            AuthorService authorService) {

        this.deviceService = deviceService;
        this.brandService = brandService;
        this.commentService = commentService;
        this.deviceTypeService = deviceTypeService;
        this.authorService = authorService;
    }

    @GetMapping("/devices/{id}")
    public String detail(
            @PathVariable Long id,
            Model model) {

        Device device = deviceService.findById(id);

        model.addAttribute(
                "device",
                device
        );

        model.addAttribute(
                "comments",
                commentService.findByDevice(id)
        );

        model.addAttribute(
                "comment",
                new Comment()
        );

        return "detail";
    }

    @PostMapping("/devices/{id}/comments")
    public String addComment(
            @PathVariable Long id,
            @RequestParam String author,
            @RequestParam Integer rating,
            @RequestParam String content) {

        Comment comment = new Comment();

        comment.setAuthor(
                authorService.findOrCreate(author)
        );

        comment.setRating(rating);

        comment.setContent(content);

        comment.setDevice(
                deviceService.findById(id)
        );

        commentService.save(comment);

        return "redirect:/devices/" + id;
    }

    @GetMapping("/admin/devices")
    public String adminDevices(Model model) {

        model.addAttribute(
                "devices",
                deviceService.search(null, null, null)
        );

        return "admin-devices";
    }

    @GetMapping("/admin/devices/new")
    public String newDevice(Model model) {

        model.addAttribute(
                "device",
                new Device()
        );

        model.addAttribute(
                "brands",
                brandService.findAll()
        );

        model.addAttribute(
                "deviceTypes",
                deviceTypeService.findAll()
        );

        return "device-form";
    }

    @GetMapping("/admin/devices/edit/{id}")
    public String editDevice(
            @PathVariable Long id,
            Model model) {

        Device device = deviceService.findById(id);

        model.addAttribute(
                "device",
                device
        );

        model.addAttribute(
                "brands",
                brandService.findAll()
        );

        model.addAttribute(
                "deviceTypes",
                deviceTypeService.findAll()
        );

        return "device-form";
    }

    @PostMapping("/admin/devices/save")
    public String saveDevice(
            @org.springframework.web.bind.annotation.ModelAttribute Device device,
            Model model) {

        deviceService.save(device);

        return "redirect:/admin/devices";
    }

    @GetMapping("/admin/devices/delete/{id}")
    public String deleteDevice(
            @PathVariable Long id) {

        deviceService.delete(id);

        return "redirect:/admin/devices";
    }
}