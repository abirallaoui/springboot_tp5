package com.example.demo.Service;

import com.example.demo.Entity.User;
import com.example.demo.Entity.Role;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Injection du PasswordEncoder
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    // Ajouter un utilisateur
    public void saveUser(User user) {
        // Encoder le mot de passe avant de l'enregistrer
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    // Ajouter un rôle à un utilisateur
    public void addRoleToUser(User user, Role role) {
        Set<Role> roles = new HashSet<>(user.getRoles());
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
    }

    // Récupérer un utilisateur par son ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Mettre à jour un utilisateur
    public void updateUser(Long id, User updatedUser) {
        User user = getUserById(id);
        if (user != null) {
            user.setUsername(updatedUser.getUsername());
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword())); // Re-encoder le mot de passe
            user.setEmail(updatedUser.getEmail());
            user.setRoles(updatedUser.getRoles());
            userRepository.save(user);
        }
    }

    // Supprimer un utilisateur
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Récupérer tous les utilisateurs
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
