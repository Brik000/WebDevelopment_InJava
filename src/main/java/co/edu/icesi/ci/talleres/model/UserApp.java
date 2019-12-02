package co.edu.icesi.ci.talleres.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


import co.edu.icesi.ci.talleres.validation.Step1;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class UserApp {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank(groups = { Step1.class })
	@NonNull
	private String username;

	@NotBlank(groups = Step1.class)
	@NonNull
	private String password;

	private UserType type;
}
