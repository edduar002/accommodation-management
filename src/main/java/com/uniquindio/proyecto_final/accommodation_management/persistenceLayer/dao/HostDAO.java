package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.dao;

import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.HostDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.LoginDTO;
import com.uniquindio.proyecto_final.accommodation_management.businessLayer.dto.UserDTO;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.HostEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity.UserEntity;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.mapper.HostMapper;
import com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.repository.HostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HostDAO {

    private final HostRepository hostRepository;
    private final HostMapper hostMapper;

    public HostDTO save(HostDTO dto) {
        HostEntity entity = hostMapper.toEntity(dto);
        HostEntity savedEntity = hostRepository.save(entity);
        return hostMapper.toDTO(savedEntity);
    }

    public Optional<HostDTO> findById(int id) {
        return hostRepository.findById(id)
                .map(hostMapper::toDTO);
    }

    public HostDTO login(LoginDTO login) {
        HostEntity entity = hostRepository.findByEmail(login.getEmail());
        if (entity == null) {
            return null;
        }
        if (!entity.getPassword().equals(login.getPassword())) {
            return null;
        }
        return hostMapper.toDTO(entity);
    }
}