package dev.evv.extreading.dto

data class ExrWordListDto(
    var wordList: List<ExrWordDto>,
    var pageNum: Int,
    var book: BookDto,
)