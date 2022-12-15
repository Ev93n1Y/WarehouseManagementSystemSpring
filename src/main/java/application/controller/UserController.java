package application.controller;

import application.model.dto.UserDto;
import application.service.RoleService;
import application.service.UserService;
import application.service.converter.RoleConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashSet;
import java.util.UUID;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService service;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    @GetMapping
    public ModelAndView get() {
        ModelAndView result = new ModelAndView("users");
        try {
            result.addObject("users", service.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping
    public RedirectView add(@ModelAttribute("user") UserDto user, @ModelAttribute("roleId") UUID roleId) {
        try {
            user.setRoles(new HashSet<>());
            user.getRoles().add(new RoleConverter().toDao(roleService.findById(roleId)));
            user.setPassword(encoder.encode(user.getPassword()));
            service.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return redirectToUsersList();
    }

    @GetMapping("/add")
    public ModelAndView addForm() {
        ModelAndView result = new ModelAndView("addUser");
        try {
            result.addObject("roles", roleService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/delete")
    public RedirectView delete(@RequestParam(name = "id") UUID id) {
        try {
            service.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return redirectToUsersList();
    }

    @GetMapping("/update")
    public ModelAndView editForm(@RequestParam(name = "id") UUID id) {
        ModelAndView result = new ModelAndView("editUser");
        try {
            result.addObject("user", service.findById(id));
            result.addObject("roles", roleService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/update")
    public RedirectView update(@ModelAttribute("user") UserDto user, @ModelAttribute("roleId") UUID roleId) {
        try {
            user.setRoles(new HashSet<>());
            user.getRoles().add(new RoleConverter().toDao(roleService.findById(roleId)));
            service.save(user.getId(), user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return redirectToUsersList();
    }


    private RedirectView redirectToUsersList() {
        return redirect("/users");
    }

    private RedirectView redirect(String path) {
        return new RedirectView(path);
    }
}
