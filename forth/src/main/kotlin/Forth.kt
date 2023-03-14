class Forth {
    private var stack = mutableListOf<Int>()
    private var opMap = mutableMapOf<String, String>()
    fun evaluate(vararg line: String): List<Int> {
        line.forEach{
            if(it[0] == ':'){
                val s = it.split(" ")
                if(s[1].toIntOrNull() != null){
                    throw Exception("illegal operation")
                }
                var value = it.substringAfter(s[1] + " ").substringBefore(" ;")
                value.split(" ").forEach{v ->
                    if(opMap.containsKey(v.trim())){
                        value = value.replace(v,opMap.getOrDefault(v, ""))
                    }
                }
                opMap[s[1].lowercase()] = value.lowercase()
            }else{
            it.split(" ").forEach{i->
                if(i.toIntOrNull() != null) {
                    stack.add(i.toInt())
                }else {
                    if(!opMap[i.lowercase()].isNullOrBlank()){
                    opMap[i.lowercase()]?.let { it1 -> evaluate(it1) }
                }else{
                    when (i.lowercase()) {

                        "+" -> {
                            checkSize()
                            val a = stack.removeLast()
                            val b = stack.removeLast()
                            stack.add(b + a)
                        }

                        "-" -> {
                            checkSize()
                            val a = stack.removeLast()
                            val b = stack.removeLast()
                            stack.add(b - a)
                        }

                        "*" -> {
                            checkSize()
                            val a = stack.removeLast()
                            val b = stack.removeLast()
                            stack.add(b * a)
                        }

                        "/" -> {
                            checkSize()
                            val a = stack.removeLast()
                            val b = stack.removeLast()
                            if (a == 0) {
                                throw Exception("divide by zero")
                            }
                            stack.add(b / a)
                        }

                        "dup" -> {
                            checkEmpty()
                            val a = stack.last()
                            stack.add(a)
                        }

                        "drop" -> {
                            checkEmpty()
                            stack.removeLast()
                        }

                        "swap" -> {
                            checkSize()
                            val a = stack.removeLast()
                            val b = stack.removeLast()
                            stack.add(a)
                            stack.add(b)
                        }

                        "over" -> {
                            checkSize()
                            val a = stack[stack.lastIndex - 1]
                            stack.add(a)
                        }
                        else -> {
                            throw Exception("undefined operation")
                        }
                    }
                }
                }
                }
            }
        }
        return stack
    }
    private fun checkSize() {
        checkEmpty()
        if(stack.size == 1){
            throw Exception("only one value on the stack")
        }
    }
    private fun checkEmpty(){
        if(stack.isEmpty()){
            throw Exception("empty stack")
        }
    }
}
