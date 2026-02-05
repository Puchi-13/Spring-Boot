package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository clienteRepository;
		
	@Override
	public List <ClienteDTO> listAllClientes(){
		List<Cliente> lista_cli=clienteRepository.findAll();
		
		List<ClienteDTO> listaResultado=new ArrayList<ClienteDTO>();
		for(Cliente cliente: lista_cli)
		{listaResultado.add(ClienteDTO.convertToDTO(cliente));
		}
		return listaResultado;
					
	}
	@Override
	public void saveCliente(ClienteDTO clientDTO) {
		Cliente cliente=ClienteDTO.convertToEntity(clientDTO);
		clienteRepository.save(cliente);	
	}

	@Override
	public ClienteDTO getClienteById(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			return ClienteDTO.convertToDTO(cliente.get());
		}
		return null;
	}

	@Override
	public void deleteCliente(Long id) {
		clienteRepository.deleteById(id);
	}

	@Override
	public void updateCliente(ClienteDTO clientDTO) {
		Cliente cliente=ClienteDTO.convertToEntity(clientDTO);
		Optional<Cliente> clienteExistente = clienteRepository.findById(cliente.getId());
		if (clienteExistente.isPresent()) {
			Cliente clientebd = clienteExistente.get();
			clientebd.setNombre(cliente.getNombre());
			clientebd.setApellidos(cliente.getApellidos());
			clientebd.setEmail(cliente.getEmail());
			clientebd.setClaveSeguridad(cliente.getClaveSeguridad());
			clientebd.setNif(cliente.getNif());
			clienteRepository.save(clientebd);
		} else {
			clienteRepository.save(cliente);
		}
	}
}
