/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.service;

import com.tienda.domain.Categoria;
import com.tienda.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Transactional(readOnly = true)   //aqui el read only es basicamente diciendole que no quiere que se modifique la lista, solo leer y mostrar 
    public List<Categoria> getCategorias(boolean activo) {
        var lista = categoriaRepository.findAll();   //findAll es funcion de JpaRepository importado del springframework
        if (activo) {
            lista.removeIf(e -> !e.getActivo());
        }
        return lista;
    }
    
    @Transactional
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }
    
    @Transactional
    public boolean delete(Categoria categoria) {
        try {
            categoriaRepository.delete(categoria);
            categoriaRepository.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        return categoriaRepository.findById(categoria.getIdCategoria()).orElse(null);
    }
    
}
