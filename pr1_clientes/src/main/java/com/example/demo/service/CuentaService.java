package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.CuentaDTO;

public interface CuentaService {
    List<CuentaDTO> listAllCuentasCliente(ClienteDTO clientedto);
}
