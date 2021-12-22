package shvyn22.flexingtranslations.util

import shvyn22.flexingtranslations.data.local.model.TranslationModel
import shvyn22.flexingtranslations.data.remote.TranslationDTO

fun fromDTOToModel(translationDTO: TranslationDTO) =
    TranslationModel(
        text = translationDTO.text,
        translateTo = translationDTO.translateTo,
        translation = translationDTO.translation
    )