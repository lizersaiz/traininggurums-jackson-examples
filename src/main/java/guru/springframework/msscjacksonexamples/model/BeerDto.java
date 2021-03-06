package guru.springframework.msscjacksonexamples.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {

	//Important things to note:
	//1. Serializing: When explicitly setting up property names, it will override any PropertyNamingStrategies applied
	//2. Deserializing: The property is expected to be called as stated in @JsonProperty. Otherwise, as it won´t be recognise,
	//	it will return null
	@JsonProperty("beerId")
	@Null
	private UUID id;
	
	@NotBlank
	private String beerName;
	
	@NotBlank
	private String beerStyle;
	
	@Positive
	private Long upc;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal price;

	//TODO "custom deserializer" check why this does not work
	//TODO document @JsonFormat behaviour
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
	//@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
	private OffsetDateTime createdDate;
	
	private OffsetDateTime lastUpdateDate;

	//TODO "custom deserializer" check why this does not work
	//Using JsonSerialize with a custom serializer class is an alternative to JsonFormat to format fields
	////@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate myLocalDate;
}
