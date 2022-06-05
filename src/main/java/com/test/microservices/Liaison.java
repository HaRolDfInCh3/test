package com.test.microservices;

import java.util.List;

import org.springframework.stereotype.Component;

import com.test.microservices.pojos.*;
import com.test.microservices.repositories.*;

@Component
public class Liaison {

EvresultatRepository srcRepo;
	EvenementRepository destRepo;
	public Liaison(EvresultatRepository s, EvenementRepository dest) {
		this.srcRepo = s;
		this.destRepo = dest;
	}
	public void creer() {
		System.out.println("Creation de liens entre Evresultat et Evenement en cours...");

		int trouves=0;
		int nontrouves=0;
		List<Evresultat>liste=srcRepo.findAll();
		for(Evresultat element:liste) {
			if(element.getEvenementID()!=0 &&destRepo.existsById(element.getEvenementID())) {
				Evenement c=destRepo.findById(element.getEvenementID());
				element.setEvenement2(c);
				trouves++;
				srcRepo.save(element);
			}else{
				element.setEvenement2(null);
				nontrouves++;
			}
			
		}
		System.out.println("Creation de liens entre Evresultat et Evenement finie trouves: "+trouves+" non trouves: "+nontrouves+"...");
	}
}
