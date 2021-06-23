package shvyn22.translationapplication.util

import shvyn22.translationapplication.data.local.model.TranslationModel
import shvyn22.translationapplication.data.remote.TranslationDTO

fun fromDTOToModel(translationDTO: TranslationDTO) =
    TranslationModel(
        text = translationDTO.text,
        translateTo = translationDTO.translateTo,
        translation = translationDTO.translation
    )