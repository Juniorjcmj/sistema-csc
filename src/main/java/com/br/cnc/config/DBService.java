package com.br.cnc.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.cnc.model.Produto;
import com.br.cnc.repository.Produtos;

@Service
public class DBService {
	
	@Autowired
	Produtos repo ;
	
	public void instanciateTestDatabase() throws ParseException{
		
		
		String csvArquivo = "C:\\Users\\Analista\\eclipse-workspace\\aulas\\src\\casanobrePronto.txt";
		
		BufferedReader conteudoCSV = null;
		
		String linha = "";
		
		String csvSeparadorCampo =";";
		
		try {
			conteudoCSV = new BufferedReader(new FileReader(csvArquivo));
			List<Produto>produtos = new ArrayList<>();
				while((linha = conteudoCSV.readLine()) != null) {
					
					String[] moeda = linha.split(csvSeparadorCampo);
					
					Produto p = new Produto();
					p.setId(Integer.parseInt(moeda[0]));
					p.setDescricao(moeda[1]);
					p.setValor(new BigDecimal(moeda[2]));
					produtos.add(p);
					
					System.out.println("ID :"+p.getId() +"  Descrição : "+p.getDescricao()+"  Valor :"+p.getValor() );
				}
			repo.saveAll(produtos);
			
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado:  \n" +e.getMessage());
		}catch (IOException e) {
			System.out.println("IO Erro:   \n" +e.getMessage());
		}catch(ArrayIndexOutOfBoundsException e ) {
			System.out.println("IndexOutOfBounds:   \n" +e.getMessage());
		}finally {
			
			if(conteudoCSV != null) {
				try {
					conteudoCSV.close();
				}catch (IOException  e) {
					System.out.println("IO Erro:   \n" +e.getMessage());
				}
			}
		}
		
				
		
		
		
		

	}

}
