package model

 data class Course (var id: Long, var title: String, var level: Long, var isActive: Boolean) : Comparable<Course>{

     override fun compareTo(other: Course): Int {
        return this.level.compareTo(other.level)
     }

     companion object{
         fun newIstance(id: Long , title: String , level: Long , isActive: Boolean) = Course(id,title,level,isActive)
     }
 }