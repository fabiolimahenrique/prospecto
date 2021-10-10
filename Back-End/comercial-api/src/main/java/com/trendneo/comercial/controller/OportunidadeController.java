package com.trendneo.comercial.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.trendneo.comercial.model.Oportunidade;
import com.trendneo.comercial.repository.OportunidadeRepository;


@CrossOrigin
@RestController
@RequestMapping(value = "/oportunidades")
public class OportunidadeController {

	@Autowired
    private OportunidadeRepository oportunidades;
	
	@GetMapping
	public List<Oportunidade> listar() {
 		return oportunidades.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Oportunidade> buscar(@PathVariable Integer id){
		
		Oportunidade oportunidade = oportunidades.findById(id);
		if (oportunidade == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oportunidade);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Oportunidade adicionar(@Valid @RequestBody Oportunidade oportunidade) {
		Oportunidade opdExiste = oportunidades.findByDescricaoAndNomeProspecto(oportunidade.getDescricao(), oportunidade.getNomeProspecto());
		
		if (opdExiste != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já Existe esta oportunidade");
		}
		
		return oportunidades.save(oportunidade);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Oportunidade> atualizar(@RequestBody Oportunidade oportunidade, @PathVariable Integer id) {
		Oportunidade opd = oportunidades.findById(id); 
		
		if (opd == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não Existe esta oportunidade");
		}
		oportunidade.setId(id);
		oportunidades.save(oportunidade);
		return new ResponseEntity<Oportunidade>(oportunidade, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
      Oportunidade opd = oportunidades.findById(id); 
		if (opd == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não Existe esta oportunidade");
		}
	  oportunidades.delete(opd);
	  return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
	@PostMapping("/upload")
	public ResponseEntity uploadToLocalFileSystem(@RequestParam("file") MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Path path = Paths.get(fileName);
		try {
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/download/")
				.path(fileName)
				.toUriString();
		return ResponseEntity.ok(fileDownloadUri);
	}	
	
	
	
	@PostMapping("/upload/db")
	public ResponseEntity uploadToDB(@RequestParam("file") MultipartFile file) {
		Oportunidade opd = new Oportunidade();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		opd.setDescricao("Boleto");
		opd.setNomeProspecto("Kubernates");
		try {
			opd.setFile(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		oportunidades.save(opd);
	
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/download/")
				.path(fileName).path("/db")
				.toUriString();
		return ResponseEntity.ok(fileDownloadUri);
	}	
	
	
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) {
		Path path = Paths.get(fileName);
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("IMAGE_PNG"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	
	@GetMapping("/download/{fileName}/db")
	public ResponseEntity downloadFromDB(@PathVariable String fileName) {
		Oportunidade opd =  oportunidades.findById(11);  
		return ResponseEntity.ok()
				//.contentType(MediaType.parseMediaType("IMAGE_PNG"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
				.body(opd.getFile());
	}	
	
	
	@GetMapping("/downloadporid/{id}/db")
	public ResponseEntity downloadIdDB(@PathVariable Integer id) {
		Oportunidade opd =  oportunidades.findById(id);  
		return ResponseEntity.ok()
				//.contentType(MediaType.parseMediaType("IMAGE_PNG"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + id + ".pdf" + "\"")
				.body(opd.getFile());
	}	
	
}
