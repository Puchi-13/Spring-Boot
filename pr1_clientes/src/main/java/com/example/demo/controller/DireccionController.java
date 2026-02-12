package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.DireccionDTO;
import com.example.demo.service.ClienteService;
import com.example.demo.service.DireccionService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @Autowired
    private ClienteService clienteService;

    @RequestMapping("/clientes/{idCliente}/direcciones")
    public String listDireccionesClient(@PathVariable Long idCliente, Model model) {
        ClienteDTO clientedto = clienteService.getClienteById(idCliente);
        List<DireccionDTO> direcciones = direccionService.listAllDireccionesCliente(clientedto);
        model.addAttribute("list", direcciones);
        model.addAttribute("clientedto", clientedto);
        return "direccionescliente";
    }

    @RequestMapping("/clientes/{idCliente}/direcciones/new")
    public String newDireccion(@PathVariable Long idCliente, Model model) {
        ClienteDTO clienteDTO = clienteService.getClienteById(idCliente);
        model.addAttribute("direcciondto", new DireccionDTO());
        model.addAttribute("clientedto", clienteDTO);   // 🔥 ESTO FALTABA
        model.addAttribute("add", true);
        return "direccionform";
    }

    @RequestMapping("/clientes/{idCliente}/direcciones/add")
    public String addDireccion(@PathVariable Long idCliente, Model model) {
        ClienteDTO clienteDTO = clienteService.getClienteById(idCliente);
        model.addAttribute("direcciondto", new DireccionDTO());
        model.addAttribute("clientedto", clienteDTO);   // 🔥 ESTO FALTABA
        model.addAttribute("add", false);
        return "direccionform";
    }

    @PostMapping("/clientes/{idCliente}/direcciones/save")
    public String saveDireccion(@PathVariable Long idCliente,
                                @ModelAttribute("direcciondto") DireccionDTO direccionDTO) {
        direccionService.saveDireccion(direccionDTO, idCliente);
        return "redirect:/clientes/" + idCliente + "/direcciones";
    }
}
