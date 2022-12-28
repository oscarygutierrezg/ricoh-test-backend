package com.ricoh.test.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "perfil")
@Getter
@Setter
public class Perfil {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
	
	private UUID id;
	@Column(name = "nombre")
	private String perfil;
	@ManyToMany
	@JoinTable(name = "usuario_perfil", joinColumns = @JoinColumn(name = "perfil_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"))
	Set<Usuario> usuarios;
}
