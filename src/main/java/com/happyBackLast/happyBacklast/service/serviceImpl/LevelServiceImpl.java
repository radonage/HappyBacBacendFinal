package com.happyBackLast.happyBacklast.service.serviceImpl;

import com.happyBackLast.happyBacklast.DTO.LevelDTO;
import com.happyBackLast.happyBacklast.DTO.LevelResponseDTO;
import com.happyBackLast.happyBacklast.DTO.mapper.LevelMapper;
import com.happyBackLast.happyBacklast.model.Country;
import com.happyBackLast.happyBacklast.model.Filiere;
import com.happyBackLast.happyBacklast.model.Level;
import com.happyBackLast.happyBacklast.repository.FiliereRepository;
import com.happyBackLast.happyBacklast.repository.LevelRepository;
import com.happyBackLast.happyBacklast.service.LevelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;
    private final FiliereRepository filiereRepository;

    public LevelServiceImpl(LevelRepository levelRepository,
                            FiliereRepository filiereRepository) {
        this.levelRepository = levelRepository;
        this.filiereRepository = filiereRepository;
    }

    public List<LevelResponseDTO> getAll(Long countryId) {
        List<Level> levels = levelRepository.findByCountryIdNative(countryId);
        return levels.stream()
                .map(LevelMapper::toDTO)
                .toList();
    }
    public List<LevelResponseDTO> getByCountryAndFiliere(Long countryId, Long filiereId) {
        return levelRepository.findByCountryIdAndFiliereId(countryId, filiereId)
                .stream()
                .map(LevelMapper::toDTO)
                .toList();
    }
    public LevelResponseDTO create(LevelDTO dto, Long countryId) {

        Filiere filiere = filiereRepository.findById(dto.getFiliereId())
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));
        if (filiere.getCountry() == null || !filiere.getCountry().getId().equals(countryId)) {
            throw new RuntimeException("La filière ne correspond pas au pays fourni");
        }
        Country country = filiere.getCountry();
        Level level = new Level();
        level.setName(dto.getName());
        level.setFiliere(filiere);
        level.setCountry(country);

        Level saved = levelRepository.save(level);

        return LevelMapper.toDTO(saved);
    }

    public LevelResponseDTO update(Long id, LevelDTO dto) {
        Level level = levelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Level introuvable"));

        Filiere filiere = filiereRepository.findById(dto.getFiliereId())
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));

        level.setName(dto.getName());
        level.setFiliere(filiere);

        Level updated = levelRepository.save(level);

        return LevelMapper.toDTO(updated);
    }

    public void delete(Long id) {
        levelRepository.deleteById(id);
    }


}