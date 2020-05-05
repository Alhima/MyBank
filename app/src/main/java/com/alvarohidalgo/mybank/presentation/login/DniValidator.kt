package com.alvarohidalgo.mybank.presentation.login

class DniValidator {

    fun isDniValid(document: String): Boolean {
        var isValid = false
        val dniLetterArray = arrayListOf(
            'T',
            'R',
            'W',
            'A',
            'G',
            'M',
            'Y',
            'F',
            'P',
            'D',
            'X',
            'B',
            'N',
            'J',
            'Z',
            'S',
            'Q',
            'V',
            'H',
            'L',
            'C',
            'K',
            'E'
        )

        if (document.length == 9) {
            // for dni
            if (dniLetterArray.contains(document[8])) {
                var numbersOk = true
                for (i in 0..7) if (!document[i].isDigit()) numbersOk = false
                if (numbersOk) {
                    var numberStr = ""
                    val number: Int
                    val rest: Int
                    val dniLetter = document[8]
                    var letterPosition = 0
                    for (i in 0..7) numberStr += document[i]
                    number = numberStr.toInt()
                    rest = number % 23
                    for (i in 0 until dniLetterArray.size) if (dniLetterArray[i] == dniLetter)
                        letterPosition = i
                    if (rest == letterPosition) {
                        isValid = true
                    }
                }
            }
        }

        return isValid
    }
}