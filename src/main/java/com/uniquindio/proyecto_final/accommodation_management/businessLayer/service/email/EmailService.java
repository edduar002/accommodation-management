package com.uniquindio.proyecto_final.accommodation_management.businessLayer.service.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreoBienvenida(String destinatario, String nombreUsuario) {
        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

            helper.setTo(destinatario);
            helper.setSubject("¡Bienvenido a Stay Finder!");

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


            helper.setText(contenidoHtml, true);
            mailSender.send(mensaje);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error enviando correo: " + e.getMessage());
        }
    }

}
