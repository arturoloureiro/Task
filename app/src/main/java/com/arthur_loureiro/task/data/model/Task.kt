package com.arthur_loureiro.task.data.model

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Task (
    var id: String = "",
    var description: String = "",
    @Exclude
    var status: Status = Status.TODO,
    var statusName: String = Status.TODO.name
): Parcelable {
    fun syncStatus(){
        status = Status.valueOf(statusName)
    }
}