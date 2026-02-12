package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.dto.DireccionDTO;
import com.example.demo.model.Cliente;
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
        List<Direccion> lista = direccionRepository.getDireccionesByCliente(clientedto.getIdCliente());
        List<DireccionDTO> resultado = new ArrayList<>();

        for (Direccion dir : lista) {
            resultado.add(DireccionDTO.convertToDTO(dir, clientedto));
        }

        return resultado;
    }

    @Override
    public void saveDireccion(DireccionDTO direccionDTO, Long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow();
        Direccion direccion = new Direccion();
        direccion.setDescripcion(direccionDTO.getDescripcion());
        direccion.setPais(direccionDTO.getPais());
        direccion.setCp(direccionDTO.getCp());
        direccion = direccionRepository.save(direccion);
        cliente.getListaDirecciones().add(direccion);
        clienteRepository.save(cliente);
    }
}
