package repository

import extensions.ErrorMessage
import extensions.titleCourse1
import extensions.titleCourse2
import extensions.titleCourse3
import model.Course
import java.lang.Exception
import java.util.*

object CourseRepository {


    var message = ""

    val courses = Collections.synchronizedList(mutableListOf(
        Course.newIstance(1, message.titleCourse1(), 5, true),
        Course.newIstance(2, message.titleCourse2(), 1, false),
        Course.newIstance(3, message.titleCourse3(), 3, true)
    ))

    fun getAll() : List<Course>{
        return this.courses.toList()
    }

    fun getTheTop(): Course{
        return this.courses.toList().last()
    }

    fun getCourseById(id: Long) : Course{
        if(id.compareTo(0) > 0 && id.compareTo(this.courses.size) <= 0) {
            for(course in this.courses.toList()){
                if(course.id.compareTo(id) == 0){
                    return course
                }
            }
        } else {
            throw IllegalArgumentException(message.ErrorMessage())
        }
        return throw IllegalArgumentException(message.ErrorMessage())
    }
}