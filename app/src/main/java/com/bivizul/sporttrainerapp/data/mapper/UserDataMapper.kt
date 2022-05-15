package com.bivizul.sporttrainerapp.data.mapper

import com.bivizul.sporttrainerapp.data.network.dbmodels.UserDbModel
import com.bivizul.sporttrainerapp.domain.user.User

class UserDataMapper {

    fun mapEntityToDbModel(user: User) = UserDbModel(
        name = user.name,
        height = user.height,
        weight = user.weight,
        progress = user.progress,
        distance = user.distance,
        squats = user.squats,
        date = user.date
    )

    fun mapDbModelToEntity(userDbModel: UserDbModel) = User(
        name = userDbModel.name,
        height = userDbModel.height,
        weight = userDbModel.weight,
        progress = userDbModel.progress,
        distance = userDbModel.distance,
        squats = userDbModel.squats,
        date = userDbModel.date
    )
}