package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.DireccionDTO;
import com.example.demo.model.Direccion;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.DireccionRepository;

@Service
public class DireccionServiceImpl implements DireccionService {
    @Autowired
    private DireccionRepository direccionRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<DireccionDTO> listAllDireccionesCliente(ClienteDTO clientedto) {
        List<Direccion> lista = (List<Direccion>) direccionRepository.getDireccionesByCliente(clientedto.getIdCliente());
        List<DireccionDTO> listadoResultado = new ArrayList<DireccionDTO>();
        for (Direccion dir : lista) {
            listadoResultado.add(DireccionDTO.convertToDTO(dir, clientedto));
        }
        return listadoResultado;
    }

}
