package com.example.sge.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @EventListener
    public void surInscription(InscriptionEvent event) {
        System.out.println(
                "[SGE] Nouvelle inscription : "
                        + event.getNomEtudiant()
                        + " --- Filiere : "
                        + event.getFiliere());
    }
}