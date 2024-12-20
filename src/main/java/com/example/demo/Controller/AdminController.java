package com.example.demo.Controller;

import com.example.demo.Service.UserService;
import com.example.demo.Service.RoleService;
import com.example.demo.Entity.User;
import com.example.demo.Entity.Role;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    // Affiche la liste des utilisateurs
    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin"; // Vue "admin.html"
    }

    // Formulaire pour ajouter un nouvel utilisateur
    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles()); // Ajouter les rôles disponibles à la vue
        return "add-user"; // Vue "add-user.html"
    }

    // Soumission du formulaire pour ajouter un utilisateur
    @PostMapping("/add")
    public String addUser(@ModelAttribute User user, @RequestParam("role.id") Long roleId) {
        // Récupérer le rôle via son ID
        Role role = roleService.getRoleById(roleId);

        // Sauvegarder l'utilisateur
        userService.saveUser(user);

        // Ajouter le rôle à l'utilisateur
        userService.addRoleToUser(user, role);

        // Rediriger vers la page admin après l'ajout
        return "redirect:/admin";
    }

    // Formulaire pour modifier un utilisateur existant
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles()); // Ajouter les rôles disponibles à la vue
        return "edit-user"; // Vue "edit-user.html"
    }

    // Soumission du formulaire pour modifier un utilisateur
    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, @ModelAttribute User user) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    // Suppression d'un utilisateur
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
