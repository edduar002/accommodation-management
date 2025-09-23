package com.uniquindio.proyecto_final.accommodation_management.persistenceLayer.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="accommodations")
public class AccommodationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "detailed_description")
    private String detailedDescription;

    private String direction;

    @Column(name = "exact_location")
    private String exactLocation;

    private Integer price;

    @Column(name = "maximum_capacity")
    private Integer maximumCapacity;

    @Column(name = "hosts_id")
    private Integer hostsId;

    private String available;

    @Column(name = "qualifications_id")
    private Integer qualificationsId;

    @Column(name = "departments_id")
    private Integer departmentsId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "hosts_id", insertable = false, updatable = false)
    private HostEntity host;

    @ManyToOne
    @JoinColumn(name = "qualifications_id", insertable = false, updatable = false)
    private QualificationEntity qualification;

    @ManyToOne
    @JoinColumn(name = "departments_id", insertable = false, updatable = false)
    private DepartmentEntity department;

    @OneToMany(mappedBy = "accommodation")
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "accommodation")
    private List<FavoriteAccommodationEntity> favoriteAccommodations;

    @OneToMany(mappedBy = "accommodation")
    private List<AccommodationServiceEntity> accommodationServices;

    @OneToMany(mappedBy = "accommodation")
    private List<ReservationEntity> reservations;

    public AccommodationEntity() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getExactLocation() {
        return exactLocation;
    }

    public void setExactLocation(String exactLocation) {
        this.exactLocation = exactLocation;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(Integer maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public Integer getHostsId() {
        return hostsId;
    }

    public void setHostsId(Integer hostsId) {
        this.hostsId = hostsId;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public Integer getQualificationsId() {
        return qualificationsId;
    }

    public void setQualificationsId(Integer qualificationsId) {
        this.qualificationsId = qualificationsId;
    }

    public Integer getDepartmentsId() {
        return departmentsId;
    }

    public void setDepartmentsId(Integer departmentsId) {
        this.departmentsId = departmentsId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

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
