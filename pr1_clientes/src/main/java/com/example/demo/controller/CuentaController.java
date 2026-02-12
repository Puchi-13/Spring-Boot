package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.CuentaDTO;
import com.example.demo.service.ClienteService;
import com.example.demo.service.CuentaService;

@Controller
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;
    @Autowired
    private ClienteService clienteService;

    @RequestMapping("/clientes/{idCliente}/cuentas")
    public String listCuentasClient(@PathVariable Long idCliente, Model model) {
        ClienteDTO clientedto = clienteService.getClienteById(idCliente);
        List<CuentaDTO> cuentas = cuentaService.listAllCuentasCliente(clientedto);
        model.addAttribute("list", cuentas);
        model.addAttribute("clientedto", clientedto);
        return "cuentascliente";
    }
}
