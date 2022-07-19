package br.com.canhette.pfood.infrastructure.web.controller;

import br.com.canhette.pfood.domain.restaurante.CategoriaRestaurante;
import br.com.canhette.pfood.domain.restaurante.CategoriaRestauranteRespository;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import java.util.List;

public class ControllerHelper {

    public static void setEditMode(Model model,boolean isEdit){
        model.addAttribute("editMode", isEdit);
    }

    public static void addCategoriasToRequest(CategoriaRestauranteRespository repository, Model model){
        List<CategoriaRestaurante> categorias = repository.findAll(Sort.by("nome"));
        model.addAttribute("categorias", categorias);
    }
}
