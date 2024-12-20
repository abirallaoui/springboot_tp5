package com.example.demo.Service;

import com.example.demo.Entity.Role;
import com.example.demo.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // Récupérer un rôle par son ID
    public Role getRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElse(null);  // Retourne null si le rôle n'est pas trouvé
    }

    // Ajouter d'autres méthodes selon vos besoins (ex. récupérer tous les rôles)
    public Iterable<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
