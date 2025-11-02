package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    // Inyección del servicio de envío de correo
    @Autowired
    private JavaMailSender mailSender;

    // Método para enviar correo de bienvenida
    public void enviarCorreoBienvenida(String destinatario, String nombreUsuario) {

        try {

            // Crear mensaje MIME para envío de correo
            MimeMessage emailMessage = mailSender.createMimeMessage();

            // Helper para construir el mensaje (permite HTML y UTF-8)
            MimeMessageHelper messageHelper = new MimeMessageHelper(emailMessage, true, "UTF-8");

            // Establecer destinatario
            messageHelper.setTo(destinatario);

            // Establecer asunto del correo
            messageHelper.setSubject("¡Bienvenido a Stay Finder!");

            // Cuerpo HTML del correo
            String contenidoHtml =
                    "<div style=\"font-family: Arial, sans-serif; background-color: #f7f9fc; padding: 20px;\">" +
                            "<table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" " +
                            "style=\"max-width: 600px; background: white; border-radius: 10px; overflow: hidden; box-shadow: 0 4px 10px rgba(0,0,0,.1);\">" +
                            "<tr>" +
                            "<td style=\"background:#0d6efd; padding: 20px; text-align:center; color:white;\">" +
                            "<h1 style=\"margin: 0; font-size: 28px;\">Stay Finder</h1>" +
                            "<p style=\"margin: 5px 0 0;\">Encuentra el lugar perfecto para tu estadía</p>" +
                            "</td>" +
                            "</tr>" +
                            "<tr>" +
                            "<td style=\"padding: 25px; color:#333;\">" +
                            "<h2 style=\"margin-top:0;\">Hola, <strong>" + nombreUsuario + "</strong> 👋</h2>" +
                            "<p>¡Gracias por registrarte en <strong>Stay Finder</strong>! Estamos muy felices de que formes parte de nuestra comunidad.</p>" +
                            "<p>A partir de ahora podrás:</p>" +
                            "<ul>" +
                            "<li>Explorar alojamientos disponibles</li>" +
                            "<li>Publicar tus propios alojamientos</li>" +
                            "<li>Gestionar tus reservas fácilmente</li>" +
                            "</ul>" +
                            "<p>Si necesitas ayuda, estamos aquí para ti.</p>" +
                            "<center style=\"margin: 30px 0;\">" +
                            "<a href=\"https://stayfinder.com\" " +
                            "style=\"background:#0d6efd; padding:12px 25px; color:white; text-decoration:none; border-radius:5px; font-weight:bold;\">" +
                            "Ir a la aplicación" +
                            "</a>" +
                            "</center>" +
                            "<p style=\"margin-top: 25px;\">Saludos,<br><strong>El equipo de Stay Finder</strong></p>" +
                            "</td>" +
                            "</tr>" +
                            "<tr>" +
                            "<td style=\"background:#f0f0f0; padding: 15px; text-align:center; font-size:12px; color:#666;\">" +
                            "© 2025 Stay Finder — Todos los derechos reservados." +
                            "</td>" +
                            "</tr>" +
                            "</table>" +
                            "</div>";

            // Establecer cuerpo HTML
            messageHelper.setText(contenidoHtml, true);

            // Enviar correo
            mailSender.send(emailMessage);

            log.info("Correo de bienvenida enviado a {}", destinatario);

        } catch (MessagingException e) {

            // Loguear error si falla
            log.error("Error enviando correo a {}: {}", destinatario, e.getMessage());
        }
    }
}