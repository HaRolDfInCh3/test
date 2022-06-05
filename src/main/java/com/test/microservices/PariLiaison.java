package com.test.microservices;

import java.util.List;

import org.springframework.stereotype.Component;

import com.test.microservices.pojos.Pari_composition;
import com.test.microservices.pojos.Pari_compositionElement;
import com.test.microservices.repositories.Pari_compositionElementRepository;
import com.test.microservices.repositories.Pari_compositionRepository;

@Component
public class PariLiaison {
Pari_compositionElementRepository repo;
Pari_compositionRepository pcRepo;

public PariLiaison(Pari_compositionRepository p,Pari_compositionElementRepository repo) {
	this.repo = repo;
	this.pcRepo=p;
}
public void start(){
	System.out.println("demarrage de pari compostition liaisons ...");
	List<Pari_composition>liste=pcRepo.findAll();
	for(Pari_composition p:liste) {
		List<Pari_compositionElement>elements=p.getElements();
		String chaineparticipants=p.getParticipant().replaceAll("\\s{2,}","");
		if(chaineparticipants.length()<1) {
			continue;
		}
		String []noms_et_ids=chaineparticipants.split(";");
		int pos=1;
		for(String nom_et_id:noms_et_ids) {
			try {
				if(nom_et_id.length()>1) {
					String nom=nom_et_id.split(":")[1];
					String id=nom_et_id.split(":")[0];
					Pari_compositionElement element=new Pari_compositionElement();
					element.setParticipant(nom);
					if(!(p.getPodium_final().length()<1) && p.getPodium_final().contains(id)) {
						element.setPodium(true);
					}if(!(p.getPremier_final().length()<1)  &&p.getPremier_final().contains(id)) {
						element.setPremier(true);
					}
					element.setId(pos);
					element.setParicompositionid(p.getId());
					pos++;
					repo.save(element);
					elements.add(element);
				}
			}catch (Exception e) {
				System.out.println("position: "+p.getId()+" erreur: "+nom_et_id+" "+e.getMessage());
				
			}
			
			
			
			
		}
		p.setElements(elements);
		pcRepo.save(p);
	}
	System.out.println("fin de pari compostition liaisons ...");
}
}
