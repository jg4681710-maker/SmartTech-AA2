package co.ucompensar.smarttech.controller;

import co.ucompensar.smarttech.entity.Comment;
import co.ucompensar.smarttech.entity.Device;
import co.ucompensar.smarttech.service.BrandService;
import co.ucompensar.smarttech.service.CommentService;
import co.ucompensar.smarttech.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class DeviceController {
    private final DeviceService deviceService;
    private final BrandService brandService;
    private final CommentService commentService;

    public DeviceController(DeviceService deviceService, BrandService brandService,
                            CommentService commentService) {
        this.deviceService = deviceService;
        this.brandService = brandService;
        this.commentService = commentService;
    }

    @GetMapping("/devices/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Device device = deviceService.findById(id);
        model.addAttribute("device", device);
        model.addAttribute("comments", commentService.findByDevice(id));
        model.addAttribute("comment", new Comment());
        return "detail";
    }

    @PostMapping("/devices/{id}/comments")
public String addComment(@PathVariable Long id,
                         @Valid @ModelAttribute("comment") Comment comment,
                         BindingResult result) {

    if (result.hasErrors()) {
        return "redirect:/devices/" + id;
    }

    // Los comentarios nuevos siempre deben tener ID nulo
    comment.setId(null);

    // Asociar el comentario con el dispositivo
    comment.setDevice(deviceService.findById(id));

    // Guardar comentario nuevo
    commentService.save(comment);

    return "redirect:/devices/" + id;
    }

    @GetMapping("/admin/devices")
    public String adminDevices(Model model) {
        model.addAttribute("devices", deviceService.search(null, null, null));
        return "admin-devices";
    }

    @GetMapping("/admin/devices/new")
    public String newDevice(Model model) {
        model.addAttribute("device", new Device());
        model.addAttribute("brands", brandService.findAll());
        return "device-form";
    }

@GetMapping("/admin/devices/edit/{id}")
public String editDevice(@PathVariable Long id, Model model) {

    Device device = deviceService.findById(id);

    System.out.println("====================================");
    System.out.println("EDITANDO DISPOSITIVO: " + device.getName());
    System.out.println("FECHA DE LANZAMIENTO: " + device.getReleaseDate());
    System.out.println("====================================");

    model.addAttribute("device", device);
    model.addAttribute("brands", brandService.findAll());

    return "device-form";
}

    @PostMapping("/admin/devices/save")
    public String saveDevice(@Valid @ModelAttribute Device device, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("brands", brandService.findAll());
            return "device-form";
        }
        deviceService.save(device);
        return "redirect:/admin/devices";
    }

    @GetMapping("/admin/devices/delete/{id}")
    public String deleteDevice(@PathVariable Long id) {
        deviceService.delete(id);
        return "redirect:/admin/devices";
    }
}
