package dev.evv.extreading.exception

import java.util.*

class DictionaryDuplicateException(txt:String, lang:String) : RuntimeException() {
    override val message: String = "Dictionary $txt with lang: $lang already exists"
}