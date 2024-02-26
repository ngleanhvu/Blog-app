package com.ngleanhvu.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String fullName;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String username;
	@Column(nullable = false, unique = true)
	private String email;
}
