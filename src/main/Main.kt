package com.example

import exception.StatusException
import extensions.ErrorMessage
import extensions.welcomeMessage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import repository.CourseRepository
import java.lang.Exception
import java.text.DateFormat

fun main(args: Array<String>) {

    var message = ""

    val server = embeddedServer(Netty, port = 8081) {

       install(ContentNegotiation) {
            gson {
                setDateFormat(DateFormat.LONG)
                setPrettyPrinting()
            }
        }

        routing {
            get("/") {
                call.respondText(message.welcomeMessage(), ContentType.Text.Plain)
            }
            get("/courses") {
                call.respond(CourseRepository.getAll())
            }
            get("/courses/top") {
                call.respond(CourseRepository.getTheTop())
            }
            get("/courses/{id}") {
                try {
                    val id = call.parameters["id"] ?: throw Exception()
                    call.respond(CourseRepository.getCourseById(id.toLong()))
                } catch(e: Exception){
                    call.respond(StatusException.newIstance(HttpStatusCode.NotFound.toString(),message.ErrorMessage()))
                }
            }
        }
    }
    server.start(wait = true)
}