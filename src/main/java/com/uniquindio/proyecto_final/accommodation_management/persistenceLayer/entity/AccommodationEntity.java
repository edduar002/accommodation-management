package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad que representa un alojamiento en la aplicación.
 *
 * <p>Incluye información como descripción detallada, ubicación, precio, capacidad,
 * disponibilidad, servicios y las relaciones con otras entidades:
 * ciudad, departamento, host, comentarios, servicios de alojamiento y reservas.</p>
 */
@Entity
@Table(name="accommodations")
public class AccommodationEntity {

    /** Identificador único del alojamiento */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Descripción detallada del alojamiento */
    @Column(name = "detailed_description")
    private String detailedDescription;

    /** Dirección del alojamiento */
    private String direction;

    /** Ubicación exacta (coordenadas o referencia adicional) */
    @Column(name = "exact_location")
    private String exactLocation;

    /** Precio por noche o estadía */
    private Integer price;

    /** Servicios disponibles en el alojamiento */
    private String services;

    /** URL de la imagen principal del alojamiento */
    private String imgUrl;

    /** Capacidad máxima de huéspedes */
    @Column(name = "maximum_capacity")
    private Integer maximumCapacity;

    /** ID del host propietario del alojamiento */
    @Column(name = "hosts_id")
    private Integer hostsId;

    /** Disponibilidad del alojamiento */
    private boolean available;

    /** ID del departamento donde se encuentra el alojamiento */
    @Column(name = "departments_id")
    private Integer departmentsId;

    /** Fecha y hora de creación del registro */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /** Fecha y hora de la última actualización del registro */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /** Indica si el alojamiento está activo */
    private boolean active;

    /** ID de la ciudad donde se encuentra el alojamiento */
    @Column(name = "cities_id")
    private Integer citiesId;

    // Relaciones con otras entidades

    /** Ciudad asociada al alojamiento */
    @ManyToOne
    @JoinColumn(name = "cities_id", insertable = false, updatable = false)
    private CityEntity city;

    /** Host propietario del alojamiento */
    @ManyToOne
    @JoinColumn(name = "hosts_id", insertable = false, updatable = false)
    private HostEntity host;

    /** Departamento al que pertenece el alojamiento */
    @ManyToOne
    @JoinColumn(name = "departments_id", insertable = false, updatable = false)
    private DepartmentEntity department;

    /** Lista de comentarios asociados al alojamiento */
    @OneToMany(mappedBy = "accommodation")
    private List<CommentEntity> comments;

    /** Lista de servicios adicionales del alojamiento */
    @OneToMany(mappedBy = "accommodation")
    private List<AccommodationServiceEntity> accommodationServices;

    /** Lista de reservas asociadas al alojamiento */
    @OneToMany(mappedBy = "accommodation")
    private List<ReservationEntity> reservations;

    /** Constructor vacío requerido por JPA */
    public AccommodationEntity() {}

    // --- Getters y Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDetailedDescription() { return detailedDescription; }
    public void setDetailedDescription(String detailedDescription) { this.detailedDescription = detailedDescription; }
    public String getDirection() { return direction; }
    public void setDirection(String direction) { this.direction = direction; }
    public Integer getCitiesId() { return citiesId; }
    public void setCitiesId(Integer citiesId) { this.citiesId = citiesId; }
    public CityEntity getCity() { return city; }
    public void setCity(CityEntity city) { this.city = city; }
    public String getImgUrl() { return imgUrl; }
    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }
    public String getExactLocation() { return exactLocation; }
    public void setExactLocation(String exactLocation) { this.exactLocation = exactLocation; }
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }
    public Integer getMaximumCapacity() { return maximumCapacity; }
    public void setMaximumCapacity(Integer maximumCapacity) { this.maximumCapacity = maximumCapacity; }
    public Integer getHostsId() { return hostsId; }
    public void setHostsId(Integer hostsId) { this.hostsId = hostsId; }
    public boolean getAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public DepartmentEntity getDepartment() { return department; }
    public void setDepartment(DepartmentEntity department) { this.department = department; }
    public Integer getDepartmentsId() { return departmentsId; }
    public void setDepartmentsId(Integer departmentsId) { this.departmentsId = departmentsId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public String getServices() { return services; }
    public void setServices(String services) { this.services = services; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    // --- Callbacks de JPA ---
    /**
     * Se ejecuta automáticamente antes de insertar un registro en la base de datos.
     * Establece createdAt y updatedAt con la fecha y hora actuales.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    /**
     * Se ejecuta automáticamente antes de actualizar un registro en la base de datos.
     * Actualiza updatedAt con la fecha y hora actuales.
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}