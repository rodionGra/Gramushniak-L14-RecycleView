package com.a6acdhmwrecycleviewfamily

class Person(var name: String, var age: Int, var generation: Int = 1) {

    var mother: Person? = null
        set(value) {
            field = value
            mother?.generation = this.generation + 1
        }

    var father: Person? = null
        set(value) {
            field = value
            father?.generation = this.generation + 1
        }

    var adult: Boolean = age >= 18

    private val siblings: MutableSet<Person> = mutableSetOf()     //list of brothers and sisters

    //add a sibling to the calling object
    public fun addSib(sib: Person) {
        sib.father = this.father
        sib.mother = this.mother
        //assign a sibling a link to the same parents
        siblings.add(sib)
    }

    fun getSiblings(): Set<Person> = this.siblings

    /*fun getParentsList(list: MutableList<Person>): List<Person> {
        this.mother?.also {
            list.add(it)
            list.plus(it.getParentsList(list))
        }
        this.father?.also {
            list.add(it)
            list.plus(it.getParentsList(list))
        }
        return list
    }*/

    fun getParentsList(): MutableList<Person> {
        val listPerson: MutableList<Person> = mutableListOf((Person(this.name, this.age, generation)))
        mother?.also { listPerson.addAll(it.getParentsList()) }
        father?.also { listPerson.addAll(it.getParentsList()) }
        return listPerson
    }

    private fun countRelativesWithMe(): Int {
        var count = 1 + this.getSiblings().size
        this.father?.let {
            count += it.countRelativesWithMe()
        }
        this.mother?.let {
            count += it.countRelativesWithMe()
        }

        return count
    }

    fun countRelatives(): Int = countRelativesWithMe() - 1

    override fun toString(): String {
        return "Person(name='$name', age=$age, generation=$generation)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (name != other.name) return false
        if (age != other.age) return false
        if (generation != other.generation) return false
        if (mother != other.mother) return false
        if (father != other.father) return false
        if (adult != other.adult) return false
        if (siblings != other.siblings) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + age
        result = 31 * result + generation
        result = 31 * result + (mother?.hashCode() ?: 0)
        result = 31 * result + (father?.hashCode() ?: 0)
        result = 31 * result + adult.hashCode()
        result = 31 * result + siblings.hashCode()
        return result
    }


    companion object {
        fun getMe(): Person {
            val me = Person("Rodion", 18)
            me.father = Person("My father is Olexandr", 51)
            me.mother = Person("My mother is Tania", 50)

            //mom`s parents
            me.mother?.also { it.father = Person("My  (по мамі) grandfather is Daniel", 70) }
                    ?: noInfo()
            me.mother?.also { it.mother = Person("My (по мамі) grandmother is Anna", 70) }
                    ?: noInfo()
            //father`s parents
            me.father?.also { it.father = Person("My (по тату) grandfather is Oleg", 71) }
                    ?: noInfo()
            me.father?.also { it.mother = Person("My (по тату) grandmother is Iana", 72) }
                    ?: noInfo()
            //parents of grandmother
            me.mother?.mother?.also { it.father = Person("mom of grandmother: Mother", 85) }
                    ?: noInfo()
            me.mother?.father?.also { it.mother = Person("father of grandmother: Father", 85) }
                    ?: noInfo()
            //parents of grandfather
            me.father?.mother?.also { it.father = Person("mom of grandfather: Mother", 85) }
                    ?: noInfo()
            me.father?.father?.also { it.mother = Person("father of grandfather: Father", 85) }
                    ?: noInfo()
            return me
        }

        private fun noInfo() = println("NO ABOUT PARANT INFO!!!")

    }
}