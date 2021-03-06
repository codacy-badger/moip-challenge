package br.com.ms.moipchallenge.builder

import br.com.ms.moipchallenge.controllers.PaymentController
import org.springframework.hateoas.Link
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn

class ResponseBuilder {

    private val body: LinkedHashMap<String, Any> = linkedMapOf()
    private val links = mutableListOf<Link>()

    fun addObject(key: String, builder: EntityBuilder): ResponseBuilder = this.apply { body[key] = builder.entity }

    fun add(key: String, value: Any): ResponseBuilder =  this.apply {  body[key] = value }

    fun addSelfLink(id: Long): ResponseBuilder = this.apply {
        links.add(linkTo(methodOn(PaymentController::class.java).getPayment(id)).withSelfRel())
    }

    fun response(): Map<String, Any> = body.apply { if(!links.isEmpty()) body["links"] = links}
}