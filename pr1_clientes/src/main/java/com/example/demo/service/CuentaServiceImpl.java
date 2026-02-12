package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.CuentaDTO;
import com.example.demo.model.Cuenta;
import com.example.demo.repository.CuentaRepository;

@Service
public class CuentaServiceImpl implements CuentaService{
    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public List<CuentaDTO> listAllCuentasCliente(ClienteDTO clientedto) {
        List<Cuenta> lista = (List<Cuenta>) cuentaRepository.getCuentasByCliente(clientedto.getIdCliente());
        List<CuentaDTO> listadoResultado = new ArrayList<CuentaDTO>();
        for (Cuenta cue : lista) {
            listadoResultado.add(CuentaDTO.convertToDTO(cue, clientedto));
        }
        return listadoResultado;
    }

}
