package br.com.canhette.pfood.infrastructure.web.controller;

import br.com.canhette.pfood.application.ClienteService;
import br.com.canhette.pfood.application.ValidationException;
import br.com.canhette.pfood.domain.cliente.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping(path = "/public")
public class PublicController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/cliente/new")
    public String newCliente(Model model){
        model.addAttribute("cliente",new Cliente());
        ControllerHelper.setEditMode(model, false);

        return "cliente-cadastro";
    }

    @PostMapping(path = "/cliente/save")
    public String saveCliente(@ModelAttribute("cliente") @Valid Cliente cliente,
                              Errors errors,
                              Model model){

        if(!errors.hasErrors()){
            try{
                clienteService.saveCliente(cliente);
                model.addAttribute("msg","Cliente Gravado com Sucesso" );
            } catch (ValidationException e){
                errors.rejectValue("email", null, e.getMessage());
            }

        }

        ControllerHelper.setEditMode(model, false);
        return "cliente-cadastro";
    }

}
