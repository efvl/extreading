package dev.evv.extreading.exception

class DictionaryDuplicateException(txt:String, lang:String) : RuntimeException() {
    override val message: String = "Dictionary $txt with lang: $lang already exists"
}