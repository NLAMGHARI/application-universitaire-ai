package com.example.demo.Service;

import com.example.demo.Model.*;
import com.example.demo.Model.Module;
import com.example.demo.repository.FiliereModuleRepository;
import com.example.demo.repository.FiliereRepository;
import com.example.demo.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FiliereModuleService {

    @Autowired
    private FiliereRepository filiereRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private FiliereModuleRepository filiereModuleRepository;

    public void save(FiliereModule filiereModule) {
        filiereModuleRepository.save(filiereModule); // Implémentation de la méthode save
    }

	public List<FiliereModule> findAll() {
		// TODO Auto-generated method stub
		return filiereModuleRepository.findAll();
	}



}

