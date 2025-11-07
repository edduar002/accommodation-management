package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entidad que representa a un usuario en el sistema.
 * Contiene información personal, de contacto y relaciones con otras entidades
 * como Ciudad, Rol y Departamento.
 */
@Entity
@Table(name = "users")
public class UserEntity {

    // Identificador único del usuario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Información personal del usuario
    private String name;
    private String surname;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String phone;
    private LocalDate birthday;

    // URL de la imagen de perfil
    @Column(name = "img_url")
    private String imgUrl;

    // Llave foránea al rol del usuario
    @Column(name = "roles_id")
    private Integer rolesId;

    // Llave foránea a la ciudad del usuario
    @Column(name = "cities_id")
    private Integer citiesId;

    // Relación Many-to-One con CityEntity
    // insertable/updatable=false porque se usa la columna cities_id
    @ManyToOne
    @JoinColumn(name = "cities_id", insertable = false, updatable = false)
    private CityEntity city;

    // Llave foránea a departamento (relación Many-to-One)
    @ManyToOne
    @JoinColumn(name = "departments_id")
    private DepartmentEntity department;

    // Relación Many-to-One con RoleEntity
    @ManyToOne
    @JoinColumn(name = "roles_id", insertable = false, updatable = false)
    private RoleEntity role;

    // Campos de auditoría
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Estado del usuario (activo/inactivo)
    private boolean active;

    public UserEntity() {
        // Constructor vacío requerido por JPA
    }

    // Getters y Setters
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

    public Integer getCitiesId() { return citiesId; }
    public void setCitiesId(Integer citiesId) { this.citiesId = citiesId; }

    public CityEntity getCity() { return city; }
    public void setCity(CityEntity city) { this.city = city; }

    public DepartmentEntity getDepartment() { return department; }
    public void setDepartment(DepartmentEntity department) { this.department = department; }

    public RoleEntity getRole() { return role; }
    public void setRole(RoleEntity role) { this.role = role; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    /**
     * Método llamado automáticamente antes de persistir la entidad.
     * Inicializa las fechas de creación y actualización.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    /**
     * Método llamado automáticamente antes de actualizar la entidad.
     * Actualiza la fecha de modificación.
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}