package ru.sber.functional

import java.util.function.Predicate

class StudentsGroup {

    lateinit var students: List<Student>


    fun filterByPredicate(predicate: (Student) -> (Boolean)): List<Student> {
        return students.filter { predicate(it) }

    }

    init {
        students = listOf(
            Student(firstName = "John", lastName = "Johnson", averageRate = 63.2),
            Student(firstName = "Alex", lastName = "Blackwood", averageRate = 32.0),
            Student(firstName = "Peter", lastName = "Jackson", averageRate = 18.1),
            Student(firstName = "Mike", lastName = "Mikles", averageRate = 92.2)
        )
    }
}
