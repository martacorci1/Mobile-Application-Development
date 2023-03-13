class BankAccount {
    var balance: Long = 0
        get(){ if(this.closed) throw IllegalStateException() else return field }
    var closed: Boolean = false
    @Synchronized
    fun adjustBalance(amount: Long){
        this.balance += amount
    }

    fun close() {
        this.closed = true
    }
}
