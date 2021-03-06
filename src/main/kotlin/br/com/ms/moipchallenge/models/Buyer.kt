package br.com.ms.moipchallenge.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY
import com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.hibernate.validator.constraints.br.CPF
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
@JsonPropertyOrder(value = ["id", "name", "email", "cpf"])
class Buyer(@field:NotBlank(message = "{buyer.name.not.blank}")
            @Column(nullable = false)
            val name: String,
            @field:NotBlank(message = "{buyer.email.not.blank}")
            @field:Email(message = "{buyer.email.invalid}")
            @Column(nullable = false)
            val email: String,
            cpf: String) {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JsonProperty(access = READ_ONLY)
    val id: Long = 0

    @CPF(message = "{buyer.cpf.invalid}")
    @NotBlank(message = "{buyer.cpf.not.blank}")
    @Column(nullable = false)
    @JsonProperty(access = WRITE_ONLY)
    val cpf: String = cpf.replace("[^0-9]+".toRegex(), "")

    @JsonProperty("cpf")
    private fun getCpfFormatted() = StringBuilder(cpf)
            .insert(3, ".")
            .insert(7, ".")
            .insert(11, "-")
            .toString()
}