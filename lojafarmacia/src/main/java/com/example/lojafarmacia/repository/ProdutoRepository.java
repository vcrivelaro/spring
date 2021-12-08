package com.example.lojafarmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lojafarmacia.model.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long>{
	
	public List <Produto> findAllByNomeContainingIgnoreCase (String Produto); //nome em produto na model
	
	public List <Produto> findByLaboratorioAndNomeContainingIgnoreCase (String Laboratorio, String Produto);

}
