package shvyn22.flexingtranslations.data.util

import shvyn22.flexingtranslations.data.local.model.TranslationModel
import shvyn22.flexingtranslations.data.remote.dto.TranslationDTO

fun fromDTOToModel(item: TranslationDTO) =
    TranslationModel(
        text = item.text,
        translateTo = item.translateTo,
        translation = item.translation
    )