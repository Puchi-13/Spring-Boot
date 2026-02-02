package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.DireccionDTO;
import com.example.demo.service.ClienteService;
import com.example.demo.service.DireccionService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DireccionController {
    @Autowired
    private DireccionService direccionService;
    @Autowired
    private ClienteService clienteService;

    @RequestMapping("/clientes/{idCliente}/direcciones/")
    public String listDireccionesClient(@PathVariable Long idCliente, Model model) {
        ClienteDTO clientedto = clienteService.getClienteById(idCliente);
        List<DireccionDTO> direcciones = direccionService.listAllDireccionesCliente(clientedto);
        model.addAttribute("list", direcciones);
        model.addAttribute("clientedto", clientedto);
        return "direccionescliente";
    }
    
}