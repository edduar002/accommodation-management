package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad que representa a un anfitrión (host) dentro de la aplicación.
 *
 * <p>Un host puede tener múltiples alojamientos, respuestas a comentarios y reservas asociadas.</p>
 */
@Entity
@Table(name="hosts")
public class HostEntity {

    /** Identificador único del host */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Nombre del host */
    private String name;

    /** Apellido del host */
    private String surname;

    /** Correo electrónico del host */
    private String email;

    /** Contraseña del host (almacenada como hash) */
    private String password;

    /** Número de teléfono del host */
    private String phone;

    /** Fecha de nacimiento del host */
    private LocalDate birthday;

    /** URL de la imagen de perfil del host */
    @Column(name = "img_url")
    private String imgUrl;

    /** Id del rol asociado */
    @Column(name = "roles_id")
    private Integer rolesId;

    /** Descripción personal del host */
    @Column(name = "personal_description")
    private String personalDescription;

    /** Id del departamento donde se encuentra el host */
    @Column(name = "departments_id")
    private Integer departmentsId;

    /** Id de la ciudad donde se encuentra el host */
    @Column(name = "cities_id")
    private Integer citiesId;

    /** Fecha y hora de creación del registro */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /** Fecha y hora de última actualización del registro */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /** Estado del host (activo/inactivo) */
    private boolean active;

    // --- Relaciones ---

    /** Rol asociado al host */
    @ManyToOne
    @JoinColumn(name = "roles_id", insertable = false, updatable = false)
    private RoleEntity role;

    /** Ciudad asociada al host */
    @ManyToOne
    @JoinColumn(name = "cities_id", insertable = false, updatable = false)
    private CityEntity city;

    /** Departamento asociado al host */
    @ManyToOne
    @JoinColumn(name = "departments_id", insertable = false, updatable = false)
    private DepartmentEntity department;

    /** Lista de alojamientos del host */
    @OneToMany(mappedBy = "host")
    private List<AccommodationEntity> accommodations;

    /** Lista de respuestas del host a comentarios */
    @OneToMany(mappedBy = "host")
    private List<ResponseCommentEntity> responses;

    /** Lista de reservas gestionadas por el host */
    @OneToMany(mappedBy = "host")
    private List<ReservationEntity> reservations;

    /** Constructor vacío requerido por JPA */
    public HostEntity() {}

    // --- Getters y Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

    public String getImgUrl() { return imgUrl; }
    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }

    public Integer getRolesId() { return rolesId; }
    public void setRolesId(Integer rolesId) { this.rolesId = rolesId; }

    public String getPersonalDescription() { return personalDescription; }
    public void setPersonalDescription(String personalDescription) { this.personalDescription = personalDescription; }

    public Integer getDepartmentsId() { return departmentsId; }
    public void setDepartmentsId(Integer departmentsId) { this.departmentsId = departmentsId; }

    public Integer getCitiesId() { return citiesId; }
    public void setCitiesId(Integer citiesId) { this.citiesId = citiesId; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public RoleEntity getRole() { return role; }
    public void setRole(RoleEntity role) { this.role = role; }

    public CityEntity getCity() { return city; }
    public void setCity(CityEntity city) { this.city = city; }

    public DepartmentEntity getDepartment() { return department; }
    public void setDepartment(DepartmentEntity department) { this.department = department; }

    public List<AccommodationEntity> getAccommodations() { return accommodations; }
    public void setAccommodations(List<AccommodationEntity> accommodations) { this.accommodations = accommodations; }

    public List<ResponseCommentEntity> getResponses() { return responses; }
    public void setResponses(List<ResponseCommentEntity> responses) { this.responses = responses; }

    public List<ReservationEntity> getReservations() { return reservations; }
    public void setReservations(List<ReservationEntity> reservations) { this.reservations = reservations; }

    // --- Callbacks de JPA ---
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}