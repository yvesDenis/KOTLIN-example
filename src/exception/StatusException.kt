package exception

data class StatusException (val status: String , val message: String){

    companion object{
        fun newIstance(status: String , message: String) = StatusException(status,message)
    }
}