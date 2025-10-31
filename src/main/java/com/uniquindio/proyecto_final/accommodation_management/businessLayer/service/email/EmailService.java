package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreoBienvenida(String destinatario, String nombreUsuario) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject("¡Bienvenido a nuestra aplicación!");
        mensaje.setText(
                "Hola " + nombreUsuario + ",\n\n" +
                        "¡Gracias por registrarte en nuestra aplicación!\n" +
                        "Estamos muy contentos de tenerte con nosotros.\n\n" +
                        "Saludos,\n" +
                        "El equipo de [Nombre de tu App]"
        );
        mailSender.send(mensaje);
    }
}
