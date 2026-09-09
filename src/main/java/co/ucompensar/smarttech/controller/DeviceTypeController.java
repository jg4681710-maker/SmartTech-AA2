package co.ucompensar.smarttech.controller;

import co.ucompensar.smarttech.entity.DeviceType;
import co.ucompensar.smarttech.service.DeviceTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/device-types")
public class DeviceTypeController {

    private final DeviceTypeService service;

    public DeviceTypeController(DeviceTypeService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("deviceTypes", service.findAll());
        return "admin-device-types";
    }

    @GetMapping("/new")
    public String newDeviceType(Model model) {
        model.addAttribute("deviceType", new DeviceType());
        return "device-type-form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("deviceType", service.findById(id));
        return "device-type-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute DeviceType deviceType) {
        service.save(deviceType);
        return "redirect:/admin/device-types";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return "redirect:/admin/device-types";
        } catch (IllegalStateException e) {
            return "redirect:/admin/device-types?error=used";
        }
    }
}