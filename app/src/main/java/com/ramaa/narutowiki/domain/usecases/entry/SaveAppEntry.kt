package com.ramaa.narutowiki.domain.usecases.entry

import com.ramaa.narutowiki.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManger: LocalUserManager
) {

    suspend operator fun invoke(){
        localUserManger.saveAppEntry()
    }

}